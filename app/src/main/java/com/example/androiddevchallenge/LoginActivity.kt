package com.example.androiddevchallenge

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.MyTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                LoginPage()
            }
        }
    }
}

@Composable
fun LoginPage() {
    val emailText = remember { mutableStateOf(TextFieldValue("")) }
    val passwordText = remember { mutableStateOf(TextFieldValue("")) }

    ConstraintLayout(
        modifier = Modifier
            .background(color = MaterialTheme.colors.onSecondary)
            .fillMaxSize()
    ) {
        val (title, email, password, infoText, loginButton) = createRefs()
        Text(
            "Log in with email",
            style = TextStyle(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .constrainAs(title) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                }
                .paddingFromBaseline(184.dp)
        )

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.secondary
            ),
            value = emailText.value,
            onValueChange = { emailText.value = it },
            label = { Text("Email address") },
            modifier = Modifier
                .constrainAs(email) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
        )

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.secondary
            ),
            value = passwordText.value,
            onValueChange = { passwordText.value = it },
            label = { Text("Password (8+ characters)") },
            modifier = Modifier
                .constrainAs(password) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(email.bottom, 8.dp)
                }
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                ),
            visualTransformation = PasswordVisualTransformation()
        )

        Text(
            "By clicking below you agree to Terms of Use and consent to our Privacy policy.",
            style = TextStyle(textAlign = TextAlign.Center),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .constrainAs(infoText) {
                    top.linkTo(password.bottom, 16.dp)
                    start.linkTo(password.start)
                    end.linkTo(password.end)
                }
                .padding(
                    horizontal = 16.dp
                )

        )

        Button(onClick = { null },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .constrainAs(loginButton) {
                    top.linkTo(infoText.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 24.dp, end = 24.dp, top = 8.dp)

        ) {
            Text("Log in", color = MaterialTheme.colors.onSecondary, fontWeight = FontWeight.Normal)
        }
    }
}
