package com.fs.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fs.compose.login.ui.LoginActivity
import com.fs.compose.ui.theme.ComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Column {
                    loginButton()

                }

            }
        }
    }


    @Composable
    private fun loginButton() {
        Button(onClick = { doLogin() }) {
            Text(text = "跳转")
        }

    }


    @Preview(showBackground = true)
    @Composable
    private fun showLoginButton() {

        Button(onClick = { doLogin() }) {
            Text(text = "跳转")
        }


    }

    private fun doLogin() {
        val intent = Intent(MainActivity@this,LoginActivity::class.java)
       startActivity(intent)

    }
}

