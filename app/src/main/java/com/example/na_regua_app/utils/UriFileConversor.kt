package com.example.na_regua_app.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import java.io.File

fun uriToFile(uri: Uri, context: Context): File? {
    return if (uri.scheme == "content") {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (it.moveToFirst()) {
                val filePath = it.getString(columnIndex)
                File(filePath) // Retorna o arquivo
            } else {
                null
            }
        }
    } else if (uri.scheme == "file") {
        uri.path?.let { File(it) }
    } else {
        null // Retorna nulo se não conseguir converter
    }
}