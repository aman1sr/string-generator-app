package com.aman.randomstringgenerator.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aman.randomstringgenerator.data.local.RandomStringEntity
import com.aman.randomstringgenerator.viewmodel.StringGeneratorViewModel
import com.aman.randomstringgenerator.viewmodel.UIState
import com.aman.randomstringgenerator.ui.theme.RandomStringGeneratorTheme
import com.aman.randomstringgenerator.util.ShowError
import com.aman.randomstringgenerator.util.ShowLoading

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: StringGeneratorViewModel = hiltViewModel()
) {
    val stringState by viewModel.allStrings.collectAsState()
    val generateState by viewModel.generateState.collectAsState()

    if (generateState is UIState.Loading) {
        ShowLoading()
    } else if (generateState is UIState.Failure) {
        ShowError(modifier, "Failed to fetch string: ${(generateState as UIState.Failure).throwable.message}")
    }

    var inputLength by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = inputLength,
                        onValueChange = { newValue ->
                            if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                inputLength = newValue
                                showError = false
                            } else {
                                showError = true
                            }
                        },
                        label = { Text("Length") },
                        isError = showError,
                        supportingText = {
                            if (showError) {
                                Text(
                                    text = "Please enter only digits",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (inputLength.isNotEmpty() && !showError) {
                            inputLength.toIntOrNull()?.let {
                                viewModel.fetchRandomString(it)
                            }
                        } else {
                            showError = true
                        }
                    }
                ) {
                    Text("Generate")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (stringState) {
                is UIState.Loading -> {
                    ShowLoading()
                }
                is UIState.Failure -> {
                    ShowError(modifier, "Something went wrong")
                }
                is UIState.Success -> {
                    val strings = (stringState as UIState.Success<List<RandomStringEntity>>).data
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 72.dp) // Add padding for the button
                    ) {
                        items(strings) { item ->
                            RandomStringItem(
                                entity = item,
                                onDelete = { viewModel.deleteString(item.id) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }

        Button(
            onClick = { viewModel.deleteAll() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Delete All")
        }
    }
}



