package com.goody.dalda.ui.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.style.SuggestionSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import coil.load
import com.goody.dalda.R
import com.goody.dalda.databinding.DialogSearchResultsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchResultsDialog(
    private val results: List<SpiritsSearchResult>
) : BottomSheetDialogFragment() {

    companion object {
        private const val SUGGESTION_URL = "https://forms.gle/f3Djzkf2ymhgcPRd6"
    }

    private var _binding: DialogSearchResultsBinding? = null
    private val binding: DialogSearchResultsBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.RoundCornerBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        results.firstOrNull()?.let {
            binding.dialogSearchResultsSpiritsName.text = it.name
            binding.dialogSearchResultsSpiritsProof.text = it.proof
            binding.dialogSearchResultsSpiritsSoju.text = it.category
            binding.dialogSearchResultsSpiritsImage.load(it.image)
        }

        binding.dialogSearchResultsConfirmBtn.setOnClickListener {
            dismiss()
        }

        binding.dialogSearchResultsSuggestionBtn.setOnClickListener {
            startSuggestionForms()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startSuggestionForms() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SUGGESTION_URL))
        startActivity(intent)
    }
}

data class SpiritsSearchResult(
    val name: String,
    val category: String,
    val proof: String,
    val image: String
)
