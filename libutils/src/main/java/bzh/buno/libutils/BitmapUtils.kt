package bzh.buno.libutils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

/**
 * Definition of the BitmapUtils object.
 */
object BitmapUtils {
    const val ONE_KO = 1024
    const val ONE_MO = ONE_KO * ONE_KO

    /**
     * Compress, if needed, an image file to be lower than or equal to 1 Mo
     *
     * @param filePath Image file path
     *
     * @return Stream containing data of the compressed image. Can be null
     */
    fun compressedImageFile(filePath: String): InputStream? {
        var quality = 100
        var inputStream: InputStream? = null
        if (filePath.isNotEmpty()) {
            var bufferSize = Integer.MAX_VALUE
            val byteArrayOutputStream = ByteArrayOutputStream()
            try {
                val bitmap = BitmapFactory.decodeFile(filePath)
                do {
                    if (bitmap != null) {
                        byteArrayOutputStream.reset()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
                        bufferSize = byteArrayOutputStream.size()
                        logD { "quality: $quality -> length: $bufferSize" }
                        quality -= 10
                    }
                } while (bufferSize > ONE_MO)
                inputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
                byteArrayOutputStream.close()
            } catch (e: Exception) {
                logE { "Exception when compressing file image: ${e.message}" }
            }
        }
        return inputStream
    }
}