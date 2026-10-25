package com.example.newcalculatorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newcalculatorproject.ui.theme.NewCalculatorProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<CalculatorViewModel>()
            val state = viewModel.state
            
            NewCalculatorProjectTheme(darkTheme = state.isDarkTheme) {
                val backgroundColor = if (state.isDarkTheme) Color(0xFF2E2E2E) else Color.White
                
                Calculator(
                    state = state,
                    onAction = viewModel::onAction,
                    buttonSpacing = 8.dp,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .padding(16.dp)
                )
            }
        }
    }
}
