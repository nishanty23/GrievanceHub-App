package com.example.grievienceapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.grievienceapplication.databinding.ActivityAddGrievanceBinding
import java.util.*

class AddGrievanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGrievanceBinding
    private var isEditMode = false
    private var existingGrievance: Grievance? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGrievanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if we're editing an existing grievance
        existingGrievance = intent.getSerializableExtra("grievance") as? Grievance
        isEditMode = existingGrievance != null

        setupSpinner()
        setupButtons()

        if (isEditMode) populateFields()
    }

    private fun setupSpinner() {
        val statuses = arrayOf("New", "In Progress", "Resolved", "Rejected")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.statusSpinner.adapter = adapter
    }

    private fun setupButtons() {
        binding.buttonSubmit.text = if (isEditMode) "Update" else "Submit"

        binding.buttonSubmit.setOnClickListener {
            if (isEditMode) updateGrievance() else submitGrievance()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun populateFields() {
        existingGrievance?.let { grievance ->
            binding.editTextTitle.setText(grievance.title)
            binding.editTextCategory.setText(grievance.category)
            binding.editTextDescription.setText(grievance.description)

            val statuses = arrayOf("New", "In Progress", "Resolved", "Rejected")
            val index = statuses.indexOf(grievance.status)
            binding.statusSpinner.setSelection(if (index >= 0) index else 0)
        }
    }

    private fun submitGrievance() {
        val title = binding.editTextTitle.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        val category = binding.editTextCategory.text.toString().trim()
        val status = binding.statusSpinner.selectedItem.toString()

        if (title.isEmpty() || description.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val grievance = Grievance(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            category = category,
            status = status,
            timestamp = System.currentTimeMillis()
        )

        val fileHelper = FileHelper(this)
        val grievances = fileHelper.readGrievances().toMutableList()
        grievances.add(grievance)
        fileHelper.saveGrievances(grievances)

        Toast.makeText(this, "Grievance submitted successfully", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun updateGrievance() {
        val title = binding.editTextTitle.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        val category = binding.editTextCategory.text.toString().trim()
        val status = binding.statusSpinner.selectedItem.toString()

        if (title.isEmpty() || description.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedGrievance = existingGrievance!!.copy(
            title = title,
            description = description,
            category = category,
            status = status,
            timestamp = System.currentTimeMillis()
        )

        val fileHelper = FileHelper(this)
        val grievances = fileHelper.readGrievances().toMutableList()
        val index = grievances.indexOfFirst { it.id == updatedGrievance.id }

        if (index != -1) {
            grievances[index] = updatedGrievance
            fileHelper.saveGrievances(grievances)
            Toast.makeText(this, "Grievance updated successfully", Toast.LENGTH_SHORT).show()
        }

        finish()
    }
}