package com.example.presentation_comic

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider.getUriForFile
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ShareImageFromUrl @Inject constructor() {

    suspend fun share(context: Context, imageUrlPath: String) {
        val bitmap = getBitmapFromUrl(context, imageUrlPath)

        if (isExternalStorageWritable() && bitmap != null) {
            val uri = saveImageExternal(context, bitmap)
            shareImageUri(context, uri)
        }
    }

    /**
     * Saves the image as PNG to the app's private external storage folder.
     * @param image Bitmap to save.
     * @return Uri of the saved file or null
     */
    private fun saveImageExternal(context: Context, image: Bitmap): Uri {
        val imagePath = File(context.filesDir, "my_images")
        val newFile = File(imagePath, "to_share.png")
        imagePath.mkdirs()
        val contentUri: Uri = getUriForFile(context, "com.example.xkcd_app", newFile)

        val stream = FileOutputStream(newFile)
        image.compress(Bitmap.CompressFormat.PNG, 90, stream)
        stream.close()

        return contentUri
    }

    /**
     * Checks if the external storage is writable.
     * @return true if storage is writable, false otherwise
     */
    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    /**
     * Shares the PNG image from Uri.
     * @param uri Uri of image to share.
     */
    private fun shareImageUri(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/png"
        context.startActivity(intent)
    }

    private suspend fun getBitmapFromUrl(context: Context, imageUrlPath: String) : Bitmap? {
        val loader = ImageLoader(context)
        val req = ImageRequest.Builder(context)
            .data(imageUrlPath)
            .build()

        val result = loader.execute(req)

        return result.drawable?.toBitmap()
    }
}