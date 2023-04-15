package com.smithnoff.mytaskyapp.utils

import android.util.Patterns
import java.util.regex.Pattern


fun validateLength(text: String, range: IntRange = IntRange(4, 50)):Boolean = text.length in range

fun validateEmail(text: String, pattern:Pattern = Patterns.EMAIL_ADDRESS) = pattern.matcher(text).matches()

fun validatePassword(text: String, pattern:Pattern =
    Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{9,}$"
)) = pattern.matcher(text).matches()
