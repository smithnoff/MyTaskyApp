package com.smithnoff.mytaskyapp.domain.validators

import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.utils.ValidationResult
import javax.inject.Inject

class TaskValidator @Inject constructor() {

    fun createValidTask(tasky: TaskyTask):ValidationResult {
        with(tasky) {
            val (isValid, errorMesage) = if (tasky.title.isEmpty()) {
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