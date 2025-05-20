package com.example.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.ui.theme.DashboardTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DashboardTheme {
                val navController = rememberNavController()
                var startDestination by remember { mutableStateOf<String?>(null) }

                // Load the remember me preference and auth status
                LaunchedEffect(Unit) {
                    val rememberMe = DataStoreHelper.getRememberMe(applicationContext)
                    val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null

                    startDestination = if (rememberMe && isUserLoggedIn) {
                        Screen.DashScreen.route
                    } else {
                        Screen.Signin.route
                    }
                }

                if (startDestination != null) {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        Navigation(
                            innerPadding = it,
                            navController = navController,
                            startDestination = startDestination!!
                        )
                    }
                }
            }
        }
    }
}
