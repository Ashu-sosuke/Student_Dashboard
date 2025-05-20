package com.example.dashboard

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var rollNumber by remember { mutableStateOf("") }
    var fatherName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(8.dp))

        OutlinedTextField(value = rollNumber, onValueChange = { rollNumber = it }, label = { Text("Roll Number") },
            modifier = Modifier.fillMaxWidth().padding(8.dp))

        OutlinedTextField(value = fatherName, onValueChange = { fatherName = it }, label = { Text("Father's Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp))

        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle password visibility")
                }
            }
    )

        Text("Already have an account? Sign In", modifier = Modifier.padding(8.dp).clickable {
            navController.navigate(Screen.Signin.route)
        })

        Button(
            onClick = {
                viewModel.signup(
                    context, name, email, rollNumber, fatherName, password,
                    onSuccess = { navController.navigate(Screen.Signin.route) },
                    onError = { message -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
                )
            },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)
        ) {
            Text("Submit")
        }
    }
}

