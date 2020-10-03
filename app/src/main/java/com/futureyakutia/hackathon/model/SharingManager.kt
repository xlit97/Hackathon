package com.futureyakutia.hackathon.model

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore.AUTHORITY
import androidx.core.content.FileProvider
import java.io.File
import javax.inject.Inject

class SharingManager @Inject constructor() {

    fun shareFile(context: Context, fileName: String) {
        val dir = Environment.getExternalStorageDirectory()
        val file = File("${dir.path}/Download/", fileName)
        val contentUri = FileProvider.getUriForFile(context, "com.futureyakutia.hackathon.fileprovider", file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, contentUri)
        intent.setType("application/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(Intent.createChooser(intent, "Send to"))
    }

    fun sharePhoto(context: Context, photo: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("image/jpg")
    }
}