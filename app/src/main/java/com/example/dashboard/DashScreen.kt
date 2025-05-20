package com.example.dashboard

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val showLogoutDialog = remember { mutableStateOf(false) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menu",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Divider()

                LazyColumn {
                    item {
                        DrawerItem("Student Info", Icons.Default.Person) {
                            coroutineScope.launch {
                                navController.navigate(Screen.StudentInfoScreen.route)
                                drawerState.close()
                            }
                        }
                    }
                    item {
                        DrawerItem("Attendance", Icons.Default.CheckCircle) {
                            coroutineScope.launch {
                                navController.navigate(Screen.AttendanceScreen.route)
                                drawerState.close()
                            }
                        }
                    }
                    item {
                        DrawerItem("Results", Icons.Default.Refresh) {
                            coroutineScope.launch {
                                navController.navigate(Screen.ResultScreen.route)
                                drawerState.close()
                            }
                        }
                    }
                    item {
                        DrawerItem("Fees", Icons.Default.Build) {
                            coroutineScope.launch {
                                navController.navigate(Screen.FeeScreen.route)
                                drawerState.close()
                            }
                        }
                    }
                    item {
                        DrawerItem("Logout", Icons.Default.Logout) {
                            showLogoutDialog.value = true
                            coroutineScope.launch { drawerState.close() }
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Student Detail") },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    coroutineScope.launch { drawerState.open() }
                                }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF2196F3),
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                BottomAppBar(containerColor = Color(0xFF00BCD4)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        BottomBarItem("Home", R.drawable.baseline_home_filled_24) {
                            // Navigate to Home
                        }

                        BottomBarItem("Settings", R.drawable.baseline_settings_24) {
                            // Navigate to Settings
                        }

                        BottomBarItem("Mentor", R.drawable.baseline_call_24) {
                            navController.navigate(Screen.MentorContact.route)
                        }
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.ChatBot.route)
                    },
                    containerColor = Color(0xFF2196F3)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_chat_bubble_24),
                        contentDescription = "ChatBot"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(scaffoldPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Quick Access",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxHeight(),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        QuickAccessCard("Student Info", Icons.Default.Person) {
                            navController.navigate(Screen.StudentInfoScreen.route)
                        }
                    }
                    item {
                        QuickAccessCard("Attendance", Icons.Default.CheckCircle) {
                            navController.navigate(Screen.AttendanceScreen.route)
                        }
                    }
                    item {
                        QuickAccessCard("Results", Icons.Default.Refresh) {
                            navController.navigate(Screen.ResultScreen.route)
                        }
                    }
                    item {
                        QuickAccessCard("Fees", Icons.Default.Build) {
                            navController.navigate(Screen.FeeScreen.route)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }


    if (showLogoutDialog.value) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog.value = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                TextButton(onClick = {
                    auth.signOut()
                    Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }) {
                    Text("Logout", modifier = Modifier.clickable{
                        navController.navigate(Screen.Signin.route)
                    })
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun QuickAccessCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(36.dp), tint = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}

@Composable
fun BottomBarItem(label: String, iconId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }

    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = Color.White
        )
        Text(text = label, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun DrawerItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Icon(icon, contentDescription = label, tint = Color.Black)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontSize = 16.sp, color = Color.Black)
    }
}
