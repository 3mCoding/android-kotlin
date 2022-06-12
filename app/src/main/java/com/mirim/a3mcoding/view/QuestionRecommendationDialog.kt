package com.mirim.a3mcoding.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.databinding.DialogQuestionRecommendationBinding
import kotlin.math.exp

class QuestionRecommendationDialog : DialogFragment() {
    lateinit var binding: DialogQuestionRecommendationBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(context)
            binding = DialogQuestionRecommendationBinding.inflate(requireActivity().layoutInflater)

            val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.level, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerLevel.adapter = adapter

            binding.btnConfirm.setOnClickListener {
                val expectMinutesInput = binding.editExpectMinutes.text.toString()
                if(expectMinutesInput.isEmpty()) {
                    Toast.makeText(activity, "예상 소요 시간을 적어주세요", Toast.LENGTH_SHORT).show()

                }
                else {
                    val expectMinutes = Integer.parseInt(expectMinutesInput)
                    dialog?.dismiss()
                }
            }

            binding.btnSkip.setOnClickListener {
                dialog?.dismiss()
            }


            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}