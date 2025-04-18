package com.aman.randomstringgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aman.randomstringgenerator.screens.HomeScreen
import com.aman.randomstringgenerator.ui.theme.RandomStringGeneratorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RandomStringGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

