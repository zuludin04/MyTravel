package com.app.zuludin.mytravel.utils

import android.content.Context
import kotlin.text.Charsets.UTF_8

object JsonUtils {

    fun readJsonFile(context: Context, jsonFile: String): String {
        val manager = context.assets
        val inputStream = manager.open(jsonFile)

        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        return String(buffer, UTF_8)
    }
}