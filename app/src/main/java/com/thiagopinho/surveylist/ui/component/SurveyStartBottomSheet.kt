package com.thiagopinho.surveylist.ui.component

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.thiagopinho.surveylist.R

class SurveyStartBottomSheet(
    context: Context,
    private val onStart: () -> Unit
) : BottomSheetDialog(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_survey_start, null)
        setContentView(view)

        val btnStart = view.findViewById<Button>(R.id.btnStart)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        btnStart.setOnClickListener {
            dismiss()
            Toast.makeText(context, "Pesquisa iniciada...", Toast.LENGTH_SHORT).show()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }
}
