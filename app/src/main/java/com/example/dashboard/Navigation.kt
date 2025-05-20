package com.example.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.Boolean

@Composable
fun Navigation(
    innerPadding: PaddingValues,
    navController: NavHostController,
    startDestination: String){

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Signin.route) {
            SignInScreen(innerPadding = innerPadding, navController = navController)
        }
        composable(Screen.Signup.route) {
            SignUpScreen(innerPadding = innerPadding, navController = navController)
        }
        composable(Screen.DashScreen.route) {
            DashScreen(innerPadding = innerPadding, navController = navController)
        }
        composable(Screen.AttendanceScreen.route){
            AttendanceScreen(navController = navController)
        }
        composable(Screen.StudentInfoScreen.route){
            StudentInfoScreen(navController = navController)
        }
        composable(Screen.ResultScreen.route){
            ResultScreen(navController = navController)
        }
        composable(Screen.FeeScreen.route){
            FeesScreen(navController)
        }
        composable(Screen.ChatBot.route){
            ChatBot(navController = navController)
        }
        composable(Screen.MentorContact.route){
            MentorContactScreen()
        }
    }


}
