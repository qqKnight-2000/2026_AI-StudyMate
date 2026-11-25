// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Top-level build file
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.2" apply false // 使用你本地实际的 AGP 版本，如果 8.5.2 报错，可以试试 8.2.0 或你原来的
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" apply false
}
