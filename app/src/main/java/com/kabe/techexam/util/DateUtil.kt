package com.kabe.techexam.util

import com.kabe.techexam.constant.AppConstants
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtil {
    fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)

        return if (date != null) {
            outputFormat.format(date)
        } else {
            AppConstants.INVALID_DATE
        }
    }
}