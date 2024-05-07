package de.janmeckelholt.todocompose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import de.janmeckelholt.todocompose.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import de.janmeckelholt.todocompose.R
import de.janmeckelholt.todocompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import de.janmeckelholt.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(priority: Priority, onPrioritySelected: (Priority) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val angle : Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "Angle Animation")
    var parentSize by remember { mutableStateOf(IntSize.Zero) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned { parentSize = it.size }
        .background(MaterialTheme.colorScheme.background)
        .height(PRIORITY_DROP_DOWN_HEIGHT)
        .clickable { expanded = true }
        .border(width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(text = priority.name, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(8f))
        IconButton(
            onClick = { expanded = true }) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = stringResource(id = R.string.drop_down_arrow_icon_desc), modifier = Modifier.weight(1.5f))
        }
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        Priority.entries.toTypedArray().slice(0..2).forEach{priority->
            DropdownMenuItem(
                text = { PriorityItem(priority = priority)} ,
                onClick = {
                    expanded = false
                    onPrioritySelected(priority)
                }
            )
        }

    }
}

@Composable
@Preview
fun PriorityDropDownPreview() {
    PriorityDropDown(priority = Priority.MEDIUM, onPrioritySelected = {})
}