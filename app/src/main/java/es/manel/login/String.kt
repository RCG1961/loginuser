/*
 * Copyright (C) 2020.  Manel Cabezas
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package es.app.laliguilla.core.extension

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.google.android.material.textview.MaterialTextView

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.Companion.empty() = ""

fun String.toBitmap(): Bitmap?{
    Base64.decode(this,Base64.DEFAULT).apply {
        return BitmapFactory.decodeByteArray(this,0,size)
    }
}

/**
 * Extension method to check if String is Phone Number.
 */
fun String.isPhone(): Boolean {
    val p = "^1([34578])\\d{9}\$".toRegex()
    return matches(p)
}

fun String.isValidPass(): Boolean {
    return this.length > 5
}

/**
 * Extension method to check if String is Email.
 */
fun String.isEmail(): Boolean {

        val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
        return this.matches(p)
}

@ExperimentalStdlibApi
fun String.capitalize(): String {
    return this.capitalize(Locale.ROOT)
    //return Character.toUpperCase(text.charAt(0)) + str.substring(1);
}

@ExperimentalStdlibApi
@SuppressLint("DefaultLocale")
fun String.capitalizeWords(): String {
    return split(" ").joinToString(" ") {
        it.toLowerCase().capitalize()
    }
}

/**
 * Extension method to check if String is Number.
 */
fun String.isNumeric(): Boolean {
    val p = "^[0-9]+$".toRegex()
    return matches(p)
}

fun String.Companion.customDateNow(formatDate: String?): String {
    formatDate?.let {
        val sdf = SimpleDateFormat(formatDate, Locale("es", "ES"))
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }
    return String.empty()
}

fun String.Companion.customDate(formatDate: String?, dataTime: String): String {
    formatDate?.let {
        val sdf = SimpleDateFormat(formatDate, Locale("es", "ES"))
        val calendar = dateInFormat("yyyy-MM-dd", dataTime)
        return sdf.format(calendar)
    }
    return String.empty()
}

/**
 * Extension method to get Date for String with specified format.
 */
fun dateInFormat(format: String, dataTime: String): Date {
    val dateFormat = SimpleDateFormat(format, Locale("es", "ES"))
    val convertedDate = Date()
    return try {
        dateFormat.parse(dataTime)!!
    } catch (e: ParseException) {
        e.printStackTrace()
        convertedDate
    }
}