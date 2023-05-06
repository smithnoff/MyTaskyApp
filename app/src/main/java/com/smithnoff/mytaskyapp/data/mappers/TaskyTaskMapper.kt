package com.smithnoff.mytaskyapp.data.mappers

import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity
import com.smithnoff.mytaskyapp.data.models.TaskyTask

fun TaskyTask.toDomain():TaskyTaskEntity {
    return TaskyTaskEntity(
        title = this.title,
        description = this.description,
        remindAt = this.remindAt,
        time = this.time,
        isDone = this.isDone
    )
}