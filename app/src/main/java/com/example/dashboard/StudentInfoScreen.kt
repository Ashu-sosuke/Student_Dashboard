package com.example.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentInfoScreen(navController: NavHostController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    val userId = auth.currentUser?.uid

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var rollNumber by remember { mutableStateOf("") }
    var fatherName by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    // Fetch user data from Firestore
    LaunchedEffect(userId) {
        userId?.let {
            db.collection("user").document(it).get()
                .addOnSuccessListener { doc ->
                    name = doc.getString("name") ?: ""
                    email = doc.getString("email") ?: ""
                    rollNumber = doc.getString("rollNumber") ?: ""
                    fatherName = doc.getString("fatherName") ?: ""
                }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Student Details") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { navController.navigate(Screen.DashScreen.route) }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            StudentInfoCard(label = "Name", value = name)
            StudentInfoCard(label = "Roll Number", value = rollNumber)
            StudentInfoCard(label = "Father's Name", value = fatherName)
            StudentInfoCard(label = "Email", value = email)
            StudentInfoCard(label = "Department", value = "Computer Science & Engineering")
            StudentInfoCard(label = "Semester", value = "4th")
            StudentInfoCard(label = "Contact", value = "+91 9876543210") // Optional: make dynamic
        }
    }
}

@Composable
fun StudentInfoCard(label: String, value: String) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = label, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value.ifBlank { "N/A" }, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
