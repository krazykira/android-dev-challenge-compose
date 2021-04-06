/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(darkTheme: Boolean = isSystemInDarkTheme()) {

    val textColor = if (darkTheme)
        MaterialTheme.colors.onPrimary
    else
        MaterialTheme.colors.secondary
    ConstraintLayout(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
    ) {
        val (backgroundImage, leaf, title, subtitle, createAccountButton, loginButton) = createRefs()
        Image(
            painter = painterResource(R.drawable.ic_light_welcome_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(backgroundImage) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .fillMaxSize()
        )

        Image(
            painter = painterResource(
                if (darkTheme)
                    R.drawable.ic_dark_welcome_illos
                else
                    R.drawable.ic_light_welcome_illos
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(leaf) {
                    top.linkTo(parent.top, margin = 72.dp)
                    start.linkTo(parent.start, margin = 88.dp)
                }
        )

        Image(
            painter = painterResource(
                if (darkTheme)
                    R.drawable.ic_dark_logo
                else
                    R.drawable.ic_light_logo
            ),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(title) {
                    centerHorizontallyTo(parent)
                    top.linkTo(leaf.bottom, 48.dp)
                }
        )
        Text(
            "Beautiful home garden solutions",
            color = textColor,
            modifier = Modifier
                .constrainAs(subtitle) {
                    top.linkTo(title.bottom)
                    centerHorizontallyTo(parent)
                }
                .paddingFromBaseline(top = 32.dp, bottom = 40.dp)
        )

        Button(onClick = { null },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .constrainAs(createAccountButton) {
                    top.linkTo(subtitle.bottom)
                    centerHorizontallyTo(parent)
                }
                .padding(start = 24.dp, end = 24.dp)

        ) {
            Text("Create Account", color = MaterialTheme.colors.onSecondary)
        }

        Text("Login", color = textColor,
            modifier = Modifier
                .constrainAs(loginButton) {
                    top.linkTo(createAccountButton.bottom, 8.dp)
                    centerHorizontallyTo(parent)
                }
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
