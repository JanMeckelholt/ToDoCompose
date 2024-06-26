package de.janmeckelholt.todocompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.janmeckelholt.todocompose.utils.Constants

@Entity(tableName = Constants.DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
