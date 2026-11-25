package com.aism.studymate.data.remote

import com.aism.studymate.data.remote.dto.ApiResponse
import com.aism.studymate.data.remote.dto.CaptureTaskData
import com.aism.studymate.data.remote.dto.CreateCaptureTaskRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AismApi {
    @POST("api/v1/capture-tasks")
    suspend fun createCaptureTask(@Body req: CreateCaptureTaskRequest): ApiResponse<CaptureTaskData>

    @GET("api/v1/capture-tasks/{taskId}")
    suspend fun getCaptureTask(@Path("taskId") taskId: String): ApiResponse<CaptureTaskData>
}

