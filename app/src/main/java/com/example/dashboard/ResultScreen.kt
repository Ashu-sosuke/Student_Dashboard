package com.example.dashboard


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Result")},
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
            modifier = Modifier.padding(innerpadding).fillMaxSize()
                .padding(8.dp)
        ) {

            Text("Semester Results", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            val results = listOf(
                "Mathematics" to "A",
                "Data Structures" to "A+",
                "Operating Systems" to "B+",
                "Database Systems" to "A"
            )

            results.forEach { (subject, grade) ->
                ResultCard(subject, grade)
            }

        }

    }
}

@Composable
fun ResultCard(subject: String, grade: String) {
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
            Text(grade, style = MaterialTheme.typography.bodyLarge)
        }
    }
}