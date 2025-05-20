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
fun FeesScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Fees")},
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
            Text("Fee Details", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            FeeItem("Tuition Fee", "₹45,000", true)
            FeeItem("Library Fee", "₹1,500", true)
            FeeItem("Lab Fee", "₹3,000", false)
            FeeItem("Hostel Fee", "₹25,000", true)

        }

    }
}

@Composable
fun FeeItem(feeName: String, amount: String, isPaid: Boolean) {
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
            Column {
                Text(feeName, style = MaterialTheme.typography.bodyLarge)
                Text(amount, style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = if (isPaid) "Paid" else "Pending",
                color = if (isPaid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
