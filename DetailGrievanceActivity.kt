package com.example.grievienceapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.grievienceapplication.databinding.ActivityDetailGrievanceBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailGrievanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGrievanceBinding
    private lateinit var grievance: Grievance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGrievanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        grievance = intent.getSerializableExtra("grievance") as? Grievance ?: run {
            finish()
            return
        }

        setupToolbar()
        displayGrievanceDetails()

        binding.buttonRespond.setOnClickListener {
            showResolutionDialog()
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Grievance Details"
    }

    private fun displayGrievanceDetails() {
        binding.textViewTitle.text = grievance.title
        binding.textViewCategory.text = grievance.category
        binding.textViewDescription.text = grievance.description
        binding.textViewStatus.text = grievance.status
        binding.textViewResponse.text = grievance.response ?: "No resolution provided yet"
        binding.textViewFeedback.text = grievance.feedback ?: "No feedback yet"
        binding.ratingBarFeedback.rating = grievance.rating ?: 0f

        val dateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        val date = Date(grievance.timestamp.toLong())
        binding.textViewTimestamp.text = dateFormat.format(date)
    }

    private fun showResolutionDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_resolution, null)
        val resolutionEditText = dialogView.findViewById<EditText>(R.id.editTextResolution)

        AlertDialog.Builder(this)
            .setTitle("Provide Resolution")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val resolution = resolutionEditText.text.toString()
                if (resolution.isNotBlank()) {
                    grievance.response = resolution
                    grievance.status = "Resolved"
                    saveUpdatedGrievance()
                    displayGrievanceDetails()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}