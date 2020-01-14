package bzh.buno.libutils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.io.File


/**
 * Definition of the LogExtensions object.
 */
inline fun Any.logD(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d(this.javaClass.simpleName, message())
    }
}

inline fun Any.logI(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.i(this.javaClass.simpleName, message())
    }
}

inline fun Any.logW(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.w(this.javaClass.simpleName, message())
    }
}

inline fun Any.logE(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.e(this.javaClass.simpleName, message())
    }
}

inline fun Any.logE(message: () -> String, e: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.e(this.javaClass.simpleName, message(), e)
    }
}

fun Any.logByMail(context: Context) {
    try {
        val fileName = "logcat_" + System.currentTimeMillis() + ".log"
        val outputFile = File(context.externalCacheDir, fileName)
        Runtime.getRuntime().exec("logcat -v time -f " + outputFile.absolutePath)
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "plain/text"
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Log")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "See attached logs")
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(outputFile))
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
    } catch (e: Exception) {
        logE { "Exception when sending log: ${e.message}" }
    }
}