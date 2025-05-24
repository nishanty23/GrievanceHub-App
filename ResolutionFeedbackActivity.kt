package com.example.grievienceapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.grievienceapplication.databinding.ActivityResolutionFeedbackBinding

class ResolutionFeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResolutionFeedbackBinding
    private lateinit var grievance: Grievance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResolutionFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        grievance = intent.getSerializableExtra("grievance") as? Grievance ?: run {
            finish()
            return
        }

        showDetails()

        binding.buttonSubmitFeedback.setOnClickListener {
            val rating = binding.ratingBar.rating
            val feedback = binding.editTextFeedback.text.toString().trim()

            if (feedback.isNotEmpty()) {
                grievance.feedback = feedback
                grievance.rating = rating
                saveUpdatedGrievance()
                Toast.makeText(this, "Feedback submitted!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please provide feedback.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDetails() {
        binding.textViewTitle.text = grievance.title
        binding.textViewDescription.text = grievance.description
        binding.textViewResolution.text = grievance.response ?: "No resolution yet"
    }

    private fun saveUpdatedGrievance() {
        val fileHelper = FileHelper(this)
        val grievances = fileHelper.readGrievances().toMutableList()
        val index = grievances.indexOfFirst { it.id == grievance.id }
        if (index != -1) {
            grievances[index] = grievance
            fileHelper.saveGrievances(grievances)
        }
    }
}