package com.example.dashboard


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.R

@Composable
fun MentorContactScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Mentor image placeholder
        Image(
            painter = painterResource(id = R.drawable.baseline_call_24), // Replace with actual drawable
            contentDescription = "Mentor Image",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        // Mentor Name
        Text(
            text = "Dr. A. Sharma",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // Mentor Role
        Text(
            text = "Computer Science Dept.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        // Contact Button
        Button(
            onClick = {
                val phoneIntent = Intent(Intent.ACTION_DIAL)
                phoneIntent.data = Uri.parse("tel:9631*****")
                context.startActivity(phoneIntent)
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text("Contact Mentor")
        }
    }
}