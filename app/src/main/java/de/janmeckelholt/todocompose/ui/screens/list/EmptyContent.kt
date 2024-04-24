package de.janmeckelholt.todocompose.ui.screens.list

import de.janmeckelholt.todocompose.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import de.janmeckelholt.todocompose.ui.theme.SAD_FACE_SIZE

@Composable
fun EmptyContent(){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(modifier = Modifier.size(SAD_FACE_SIZE), painter = painterResource(id = R.drawable.ic_sad_face), contentDescription = stringResource(id = R.string.sad_face_icon_desc), tint = Color.Gray)
        Text(text = stringResource(id = R.string.no_task_found), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium, color = Color.Gray)
    }
}

@Preview
@Composable
fun EmptyContentPreview(){
    EmptyContent()
}