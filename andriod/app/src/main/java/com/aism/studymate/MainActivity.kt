package com.aism.studymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.aism.studymate.ui.screen.CaptureScreen
import com.aism.studymate.ui.theme.AIStudyMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AIStudyMateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 将首页设置为我们的链接提交页面
                    CaptureScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}