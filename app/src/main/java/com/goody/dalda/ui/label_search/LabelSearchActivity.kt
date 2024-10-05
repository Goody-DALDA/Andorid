package com.goody.dalda.ui.label_search

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.UseCaseGroup
import androidx.camera.core.ViewPort
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.goody.dalda.base.BaseActivity
import com.goody.dalda.databinding.ActivityLabelSearchBinding
import com.goody.dalda.extention.cropBitmap
import com.goody.dalda.extention.resizeWidth
import com.goody.dalda.extention.rotate
import com.goody.dalda.extention.toBitmap
import com.goody.dalda.ui.custom.GraphicOverlay
import com.goody.dalda.ui.custom.TextGraphic
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@AndroidEntryPoint
class LabelSearchActivity : BaseActivity<ActivityLabelSearchBinding>() {

    private val viewModel: LabelSearchViewModel by viewModels()
    private var imageCapture: ImageCapture? = null
    private lateinit var photoFile: File
    private lateinit var cameraExecutor: ExecutorService

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Handle Permission granted/rejected
            var permissionGranted = true

            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && !it.value)
                    permissionGranted = false
            }

            if (permissionGranted) {
                startCamera()
            } else {
                Toast.makeText(baseContext, "Permission request denied", Toast.LENGTH_SHORT).show()
            }
        }

    override val bindingInflater: (LayoutInflater) -> ActivityLabelSearchBinding
        get() = ActivityLabelSearchBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        cameraExecutor = Executors.newSingleThreadExecutor()

        createCacheFile()
        setupCaptureClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        photoFile.deleteOnExit()
    }

    private fun createCacheFile() {
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.KOREA)
            .format(System.currentTimeMillis())
        photoFile = File(cacheDir, "${name}.jpg")
    }

    private fun setupCaptureClickListener() {
        binding.imageCaptureButton.setOnClickListener {
            photoFile.delete()
            takePhoto()
        }
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val bitmap = image.toBitmap()
                        .rotate(image.imageInfo.rotationDegrees.toFloat())
                        .resizeWidth(binding.viewFinder)
                        .cropBitmap(binding.viewFinder, binding.labelSearchGuideBox)

                    image.close()
                    runTextRecognition(bitmap)
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            // Crop rect
            val cropWidth = binding.viewFinder.width
            val cropHeight = binding.viewFinder.height
            val cropRotation = ContextCompat.getDisplayOrDefault(this).rotation

            val viewPort = ViewPort.Builder(Rational(cropWidth, cropHeight), cropRotation).build()
            val useCaseGroup = UseCaseGroup.Builder()
                .addUseCase(preview)
                .addUseCase(imageCapture!!)
                .setViewPort(viewPort)
                .build()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, useCaseGroup)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 텍스트 인식 감지기를 구성하고 processTextRecognitionResult 응답으로 함수를 호출
     */
    private fun runTextRecognition(selectedImage: Bitmap) {
        val image = InputImage.fromBitmap(selectedImage, 0)
        val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        binding.imageCaptureButton.isEnabled = false
        recognizer.process(image)
            .addOnSuccessListener { texts ->
                binding.imageCaptureButton.isEnabled = true
                processTextRecognitionResult(texts)
            }
            .addOnFailureListener { e -> // Task failed with an exception
                binding.imageCaptureButton.isEnabled = true
                e.printStackTrace()
            }
    }

    private fun processTextRecognitionResult(texts: Text) {
        val blocks = texts.textBlocks
        if (blocks.size == 0) {
            Toast.makeText(baseContext, "No text found", Toast.LENGTH_SHORT).show()
            return
        }

        binding.graphicOverlay.clear()
        for (i in blocks.indices) {
            val lines = blocks[i].lines
            for (j in lines.indices) {
                val elements = lines[j].elements
                val rect = lines[j].boundingBox

                if (rect != null) {
                    Log.d(TAG, "kch [" + lines[j].text + "] rect Height : " + (rect.bottom - rect.top))
                }

                for (k in elements.indices) {
                    val textGraphic: GraphicOverlay.Graphic =
                        TextGraphic(binding.graphicOverlay, elements[k])
                    binding.graphicOverlay.add(textGraphic)

                    Log.d(TAG, "kch [" + lines[j].text + "] element : " + elements[k].text)
                }
            }
        }
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}