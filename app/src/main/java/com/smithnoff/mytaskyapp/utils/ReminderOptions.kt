package com.smithnoff.mytaskyapp.utils

import com.smithnoff.mytaskyapp.R

sealed class ReminderOptions(val title:Int,val time:Long){
    object TenMinutes : ReminderOptions(title = R.string.txt_10_minutes_before, time = 600000)
    object HalfHour : ReminderOptions(title = R.string.txt_30_minutes_before, time = 1800000)
    object OneHour : ReminderOptions(title = R.string.txt_1_hour_before, time = 3600000)
    object SixHour : ReminderOptions(title = R.string.txt_6_hours_before, time = 21600000)
    object OneDay : ReminderOptions(title = R.string.txt_1_day_before, time = 86400000)
}
