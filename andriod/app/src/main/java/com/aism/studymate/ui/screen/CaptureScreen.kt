package com.aism.studymate.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aism.studymate.ui.viewmodel.CaptureViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptureScreen(modifier: Modifier = Modifier, viewModel: CaptureViewModel = viewModel()) {
    var linkText by remember { mutableStateOf("") }
    // 收集 ViewModel 中的状态，并触发重组
    val statusText by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AI StudyMate",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = linkText,
            onValueChange = { linkText = it },
            label = { Text("输入B站分享链接或文案") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.submitLink(linkText) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("提交解析")
        }

        Spacer(modifier = Modifier.height(48.dp))

        // 状态展示区（带背景卡片）
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "运行状态：", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = statusText, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}