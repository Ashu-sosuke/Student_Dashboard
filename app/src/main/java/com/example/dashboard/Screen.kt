package com.example.dashboard

sealed class Screen(val route: String) {

    object DashScreen: Screen("dashscreen")
    object Signin: Screen("signinscreen")
    object Signup: Screen("signupscreen")
    object ChatBot: Screen("chatbot")
    object FeeScreen: Screen("feescreen")
    object ResultScreen: Screen("resultscreen")
    object StudentInfoScreen: Screen("studentinfoscreen")
    object AttendanceScreen: Screen("attendance")
    object MentorContact: Screen("mentorcontact")
}

sealed class DrawerScreen(val route: String) {
    object LogOutDialog: DrawerScreen("logout")
}
