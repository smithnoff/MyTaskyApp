package com.smithnoff.mytaskyapp.domain.validators

import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.utils.ValidationResult
import javax.inject.Inject

class ReminderValidator @Inject constructor() {

    fun createValidReminder(reminder: TaskyReminder):ValidationResult {
        with(reminder) {
            val (isValid, errorMesage) = if (reminder.title.isEmpty()) {
                false to "Title is empty"
            } else {
                true to ""
            }

            return if (isValid) {
                ValidationResult.Success
            } else {
                ValidationResult.Failure(errorMesage)
            }
        }
    }
}