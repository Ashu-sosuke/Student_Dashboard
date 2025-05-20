package com.example.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogOutDialog(dialogOpen: MutableState<Boolean>) {
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = { dialogOpen.value = false },
            confirmButton = {
                TextButton(onClick = {
                    // Perform logout here
                    FirebaseAuth.getInstance().signOut()
                    dialogOpen.value = false
                    // Optional: Navigate to sign-in screen if needed
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogOpen.value = false
                }) {
                    Text("Cancel")
                }
            },
            title = { Text("Logout") },
            text = { Text("Are you sure you want to logout?") },
            shape = RoundedCornerShape(8.dp),
            containerColor = Color.White,
            tonalElevation = 8.dp
        )
    }
}
