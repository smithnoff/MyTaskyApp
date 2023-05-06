package com.smithnoff.mytaskyapp.data.mappers

import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import java.text.SimpleDateFormat
import java.util.*

fun TaskyTask.toDomain():TaskyTaskEntity {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return TaskyTaskEntity(
        title = this.title,
        description = this.description,
        remindAt = this.remindAt,
        time = this.time,
        isDone = this.isDone,
        date = formatter.format(time)
    )
}

fun TaskyTaskEntity.toData():TaskyTask {
    return TaskyTask(
        id = this.id.toString(),
        title = this.title,
        description = this.description,
        remindAt = this.remindAt,
        time = this.time,
        isDone = this.isDone,
    )
}