package com.smithnoff.mytaskyapp.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

fun ReminderOptions.getTitle(context: Context):String{
    return when(this){
        ReminderOptions.HalfHour -> context.getString(ReminderOptions.HalfHour.title)
        ReminderOptions.OneDay ->context.getString(ReminderOptions.OneDay.title)
        ReminderOptions.OneHour ->context.getString(ReminderOptions.OneHour.title)
        ReminderOptions.SixHour ->context.getString(ReminderOptions.SixHour.title)
        ReminderOptions.TenMinutes ->context.getString(ReminderOptions.TenMinutes.title)
    }
}

fun ReminderOptions.getMillis():Long{
    return when(this){
        ReminderOptions.HalfHour -> ReminderOptions.HalfHour.time
        ReminderOptions.OneDay ->ReminderOptions.OneDay.time
        ReminderOptions.OneHour ->ReminderOptions.OneHour.time
        ReminderOptions.SixHour ->ReminderOptions.SixHour.time
        ReminderOptions.TenMinutes ->ReminderOptions.TenMinutes.time
    }
}

fun getCardDateHour(date:Long):String{
    val formatter = SimpleDateFormat("MMM dd, HH:mm", Locale.ENGLISH)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    return formatter.format(calendar.time)
}