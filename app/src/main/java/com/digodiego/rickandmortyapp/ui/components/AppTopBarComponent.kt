package com.digodiego.rickandmortyapp.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digodiego.rickandmortyapp.ui.theme.PrimaryColor
import com.digodiego.rickandmortyapp.ui.theme.WhiteColor
import com.digodiego.rickandmortyapp.ui.theme.chicleFamily

@Composable
fun AppTopBarComponent(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(color = WhiteColor, fontSize = 30.sp, fontFamily = chicleFamily)
            )
        },
        elevation = 0.dp,
        backgroundColor = PrimaryColor,

        )
}