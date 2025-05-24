package com.example.grievienceapplication

import java.io.Serializable

data class Grievance(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    var status: String,
    val timestamp: Long,
    var response: String? = null,
    var feedback: String? = null,       // ✅ Required for employee feedback
    var rating: Float? = null           // ✅ Required for employee rating
) : Serializable