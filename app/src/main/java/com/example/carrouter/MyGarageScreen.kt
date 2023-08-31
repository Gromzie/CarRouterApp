package com.example.carrouter

    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material.Button
    import androidx.compose.material.Text
    import androidx.compose.material.TextField
    import androidx.compose.runtime.*
    import androidx.compose.ui.ExperimentalComposeUiApi
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    import kotlinx.coroutines.launch
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory


    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MyGarageScreen() {

        val coroutineScope = rememberCoroutineScope()
        val cars = remember { mutableStateListOf<String>() }  // Dummy list to hold car models
        var registrationNumber by remember { mutableStateOf("") }

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tsopendata.azure-api.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(CarInfoAPI::class.java)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = registrationNumber,
                onValueChange = { registrationNumber = it },
                label = { Text("Enter Registration Number") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                coroutineScope.launch {
                    val response = api.getCarInfo("Artal eq '$registrationNumber'").execute()
                    if (response.isSuccessful) {
                        val carModel = response.body()?.someField ?: "Unknown"
                        cars.add(carModel)
                    } else {
                        // Handle error
                    }
                }
            }) {
                Text("Add to My Garage")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("My Garage:")

            LazyColumn {
                items(cars) { car ->
                    Text(car, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
