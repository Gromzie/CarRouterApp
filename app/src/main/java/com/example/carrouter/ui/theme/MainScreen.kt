package com.example.carrouter.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var destination by remember { mutableStateOf("") }
    var selectedCarModel by remember { mutableStateOf("Tesla Model S") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destination") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenu(
            items = listOf("Tesla Model S", "Nissan Leaf", "Chevy Bolt"),
            selectedItem = selectedCarModel,
            onItemSelected = { selectedCarModel = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Navigate to ResultsScreen
            navController.navigate("results")
        }) {
            Text("Search")
        }
    }
}