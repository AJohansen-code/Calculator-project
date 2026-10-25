package com.example.newcalculatorproject

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Scale animation: shrinks slightly when pressed
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.92f else 1f, label = "scale")

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale) // Apply scale animation
            .clip(CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disable default ripple to use our custom color change
                onClick = onClick
            )
            .then(modifier)
    ) {
        // Overlay to lighten the button color when pressed
        if (isPressed) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White.copy(alpha = 0.25f))
            )
        }

        Text(
            text = symbol,
            fontSize = 36.sp,
            color = Color.White,
        )
    }
}
