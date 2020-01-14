package bzh.buno.libutils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Definition of the UIExtensions object.
 */


fun View.setVisibleElseGone(newVisibility: Boolean) {
    visibility = if (newVisibility) View.VISIBLE else View.GONE
}

fun View.setVisibleElseInvisible(newVisibility: Boolean) {
    visibility = if (newVisibility) View.VISIBLE else View.INVISIBLE
}

fun Context.makeCall(phoneNumber: CharSequence) {
    ContextCompat.startActivity(
        this,
        Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber")),
        null
    )
}

fun Context.makeHiddenCall(phoneNumber: CharSequence) {
    ContextCompat.startActivity(
        this, Intent(
            Intent.ACTION_CALL,
            Uri.parse("tel:${Uri.encode("#")}31${Uri.encode("#")}$phoneNumber")
        ), null
    )
}

fun Context.showToast(@StringRes text: Int) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

/**
 * Convert px value to dp value
 * @return Value in dp
 */
fun Int.toDp() = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * Convert dp value to px value
 * @return Value in px
 */
fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
