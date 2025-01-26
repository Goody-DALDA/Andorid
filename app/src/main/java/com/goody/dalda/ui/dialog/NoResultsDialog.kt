package com.goody.dalda.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import com.goody.dalda.R
import com.goody.dalda.databinding.DialogNoResultsBinding

class NoResultsDialog(context: Context) : Dialog(context, R.style.SearchLabel_EmptyDialog) {
    companion object {
        private const val SUGGESTION_URL = "https://forms.gle/f3Djzkf2ymhgcPRd6"
    }

    private val binding = DialogNoResultsBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        setContentView(binding.root)
    }

    override fun show() {
        binding.dialogNoResultsConfirmBtn.setOnClickListener {
            dismiss()
        }

        binding.dialogNoResultsSuggestionBtn.setOnClickListener {
            startSuggestionForms()
            dismiss()
        }

        super.show()
    }

    private fun startSuggestionForms() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SUGGESTION_URL))
        context.startActivity(intent)
    }
}
