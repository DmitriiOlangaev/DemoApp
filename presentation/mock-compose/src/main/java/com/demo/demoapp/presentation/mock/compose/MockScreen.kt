package com.demo.demoapp.presentation.mock.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.demo.demoapp.core.designsystem.compose.Black
import com.demo.demoapp.core.designsystem.compose.White

@Composable
fun MockScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Image(painter = painterResource(id = R.drawable.mem), contentDescription = null)
    }
}


@Preview
@Composable
fun MockScreenPreview() {
    MockScreen()
}
