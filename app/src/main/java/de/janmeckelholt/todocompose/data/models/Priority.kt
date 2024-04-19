package de.janmeckelholt.todocompose.data.models

import androidx.compose.ui.graphics.Color
import de.janmeckelholt.todocompose.ui.theme.HighPriorityColor
import de.janmeckelholt.todocompose.ui.theme.LowPriorityColor
import de.janmeckelholt.todocompose.ui.theme.MediumPriorityColor
import de.janmeckelholt.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor),
}