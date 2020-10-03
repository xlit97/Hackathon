package com.futureyakutia.hackathon.model

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.futureyakutia.hackathon.R
import java.io.File
import java.io.FileOutputStream
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

    fun sharePhoto(context: Context, photo: Drawable?) {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.share_image)
        var path = "${Environment.getExternalStorageDirectory().path}/Download/share_image.jpg"
        val file = File(path)
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {}
        path = file.path
        val bmpUri = Uri.parse("content:/$path")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, bmpUri)
        intent.setType("image/jpg")
        context.startActivity(Intent.createChooser(intent, "Send to"))
    }
}