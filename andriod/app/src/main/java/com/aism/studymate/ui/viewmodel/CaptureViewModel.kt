package com.aism.studymate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aism.studymate.data.remote.RetrofitClient
import com.aism.studymate.data.remote.dto.CreateCaptureTaskRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class CaptureViewModel : ViewModel() {
    private val api = RetrofitClient.api

    // 定义 UI 状态流，用于让界面实时更新
    private val _uiState = MutableStateFlow<String>("等待提交")
    val uiState: StateFlow<String> = _uiState

    fun submitLink(link: String) {
        if (link.isBlank()) {
            _uiState.value = "链接不能为空"
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = "正在提交..."
                // 生成请求体
                val req = CreateCaptureTaskRequest(link, UUID.randomUUID().toString())
                val response = api.createCaptureTask(req)

                if (response.code == "OK" && response.data != null) {
                    val taskId = response.data.taskId
                    _uiState.value = "任务已创建: $taskId\n状态: ${response.data.status}"
                    // 开始轮询
                    pollTaskStatus(taskId)
                } else {
                    _uiState.value = "创建失败: ${response.message}"
                }
            } catch (e: Exception) {
                _uiState.value = "网络错误: ${e.message}"
            }
        }
    }

    private suspend fun pollTaskStatus(taskId: String) {
        var delayMs = 2000L
        // 最多轮询 60 次，每次间隔按指数退避递增（最多 10 秒）
        repeat(60) {
            try {
                val resp = api.getCaptureTask(taskId)
                val data = resp.data
                if (data != null) {
                    _uiState.value = "当前状态: ${data.status}"

                    // 如果达到最终状态，停止轮询
                    if (data.status == "DONE" || data.status == "FAILED" || data.status == "DEGRADED") {
                        _uiState.value = "流程结束: ${data.status}\n笔记ID: ${data.noteId}"
                        return
                    }
                }
                delay(delayMs)
                delayMs = (delayMs * 1.5).toLong().coerceAtMost(10000L)
            } catch (e: Exception) {
                _uiState.value = "轮询出错: ${e.message}"
                return
            }
        }
        _uiState.value = "轮询超时"
    }
}

