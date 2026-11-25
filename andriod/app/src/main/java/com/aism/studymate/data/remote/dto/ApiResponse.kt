package com.aism.studymate.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null,
    val traceId: String? = null,
    val timestamp: String? = null
)
