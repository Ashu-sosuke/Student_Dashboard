package com.example.dashboard

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun signup(
        context: Context,
        name: String,
        email: String,
        rollNumber: String,
        fatherName: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (name.isBlank() || email.isBlank() || rollNumber.isBlank() || fatherName.isBlank() || password.isBlank()) {
            onError("Please fill all fields")
            return
        }

        if (password.length < 6) {
            onError("Password must be at least 6 characters")
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onError("Please enter a valid email address")
            return
        }

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        val userMap = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "rollNumber" to rollNumber,
                            "fatherName" to fatherName
                        )
                        db.collection("user").document(userId!!)
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Signup successful", Toast.LENGTH_SHORT).show()
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                onError("Firestore error: ${e.localizedMessage}")
                            }
                    } else {
                        val exception = task.exception
                        when (exception) {
                            is FirebaseAuthWeakPasswordException -> {
                                onError("Weak password: ${exception.reason}")
                            }

                            is FirebaseAuthInvalidCredentialsException -> {
                                onError("Invalid email format")
                            }

                            is FirebaseAuthUserCollisionException -> {
                                onError("User with this email already exists")
                            }

                            else -> {
                                onError("Signup failed: ${exception?.localizedMessage}")
                            }
                        }
                    }
                }
        }
    }

    fun signInUser(
        context: Context,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            onError("Please fill all fields")
            return
        }

        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        onSuccess()
                    } else {
                        onError("Login failed: ${task.exception?.localizedMessage}")
                    }
                }
        }
    }

    fun signOut(context: Context, onSignedOut: () -> Unit) {
        auth.signOut()
        viewModelScope.launch {
            UserPreferences(context).clearRememberMe()
        }
        onSignedOut()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}

