package com.goody.dalda.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.goody.dalda.R
import com.goody.dalda.databinding.DialogNoResultsBinding

class NoResultsDialog(context: Context) : Dialog(context, R.style.SearchLabel_EmptyDialog) {

    private val binding = DialogNoResultsBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        setContentView(binding.root)
    }

    override fun show() {
        binding.dialogNoResultsConfirmBtn.setOnClickListener {
            dismiss()
        }

        binding.dialogNoResultsSuggestionBtn.setOnClickListener {
            // TODO : 문의하기 화면으로 이동
            dismiss()
        }

        super.show()
    }
}
