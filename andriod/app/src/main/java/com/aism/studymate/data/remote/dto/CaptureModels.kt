package com.aism.studymate.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCaptureTaskRequest(
    val rawShareText: String,
    val clientRequestId: String
)

@Serializable
data class CaptureTaskData(
    val taskId: String,
    val status: String,
    val videoId: String? = null,
    val noteId: String? = null,
    val reused: Boolean = false
)