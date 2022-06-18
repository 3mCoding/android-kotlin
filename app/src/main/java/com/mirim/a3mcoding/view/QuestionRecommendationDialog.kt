package com.mirim.a3mcoding.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.databinding.DialogQuestionRecommendationBinding
import com.mirim.a3mcoding.model.LevelProblem
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
                    val level = LevelProblem.levelStringConverter(binding.spinnerLevel.selectedItem.toString())
                    val intent = Intent(context, ProblemActivity::class.java)
                    intent.putExtra("problemType", "recommendation")
                    intent.putExtra("time", expectMinutes)
                    intent.putExtra("level", level)
                    context?.startActivity(intent)

                    dialog?.dismiss()
                }
            }

            binding.btnSkip.setOnClickListener {
                val intent = Intent(context, ProblemActivity::class.java)
                intent.putExtra("problemType", "recommendation")
                intent.putExtra("time", -1)
                intent.putExtra("level", "-1")
                context?.startActivity(intent)
                dialog?.dismiss()
            }


            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}