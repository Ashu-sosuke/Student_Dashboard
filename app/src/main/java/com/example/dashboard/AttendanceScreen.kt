package com.example.dashboard


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Attendance")},
                navigationIcon = {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                            .clickable {
                                navController.navigate(Screen.DashScreen.route)
                            })
                })
        }
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Text("Attendance Record", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            val subjects = listOf(
                "Mathematics" to "85%",
                "Data Structures" to "92%",
                "Operating Systems" to "78%",
                "Database Systems" to "88%"
            )

            subjects.forEach { (subject, percent) ->
                AttendanceCard(subject, percent)
            }

        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

    }
}

@Composable
fun AttendanceCard(subject: String, percentage: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(subject, style = MaterialTheme.typography.bodyLarge)
            Text(percentage, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
