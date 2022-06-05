package com.takg.nbay.ui.screens.welcome

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.takg.nbay.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(animationDurationMills: Int = 3000, onComplete: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = animationDurationMills
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        onComplete.invoke()
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.nbay_cropped),
            modifier = Modifier.alpha(alpha),
            contentDescription = "Logo"
        )

    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}
