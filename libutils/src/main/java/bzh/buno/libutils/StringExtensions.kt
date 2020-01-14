package bzh.buno.libutils

import android.util.Patterns

/**
 * Definition of the StringExtensions object.
 */

/**
 * Remove whitespaces contained in the string
 * @return string without whitespaces
 */
fun CharSequence.removeWhitespaces() = trim().replace("\\s".toRegex(), "_")

/**
 * Check if the current string is a valid email
 * @return true if the string is an email, false otherwise
 */
fun CharSequence.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

/**
 * Check if the current string is a valid French mobile number
 * @return true if the string is a French mobile number, false otherwise
 */
fun CharSequence.isMobileValid(): Boolean {
    val regex = "^(\\+33|0)[6-7](?:\\s*\\d{2}){4}\$"
    return regex.toRegex().matches(this)
}