package com.futureyakutia.hackathon.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore.AUTHORITY
import androidx.core.content.FileProvider
import java.io.File

class SharingManager() {

    fun shareFile(context: Context, fileName: String) {
        val path = File(Environment.getDataDirectory(), "downloads")
        val file = File(path,"/" + fileName)
        val contentUri = FileProvider.getUriForFile(context, AUTHORITY, file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, contentUri)
        intent.type = "application/*"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(Intent.createChooser(intent, "Send to"))
    }

    fun sharePhoto(context: Context, photo: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("image/jpg")
    }
}