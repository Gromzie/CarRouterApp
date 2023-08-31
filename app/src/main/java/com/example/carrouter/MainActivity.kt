package com.example.carrouter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carrouter.ui.theme.MainScreen
import com.example.carrouter.ui.theme.ResultsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "myGarage") {
                composable("main") { MainScreen(navController) }
                composable("main") { MainScreen(navController) }
                composable("results") { ResultsScreen() }
                composable("myGarage") { MyGarageScreen() }
            }
        }
    }
}