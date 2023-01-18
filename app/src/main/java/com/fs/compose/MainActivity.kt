package com.fs.compose

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.ext.SdkExtensions.getExtensionVersion
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fs.compose.login.ui.LoginActivity
import com.fs.compose.ui.theme.ComposeTheme

class MainActivity : AppCompatActivity() {
    private lateinit var pickMultipleMedia: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Column {
                    loginButton()

                }

            }
        }

        pickMultipleMedia =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->

                if (uris.isNotEmpty()) {
                    Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                    uris.forEach {
                        Log.d("PhotoPicker", "dizhi$it")
                    }
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }
    }


    @Composable
    private fun loginButton() {
        Button(onClick = {

//            doLogin()
            doMedia()

        }) {
            Text(text = "跳转")
        }

    }


    @Preview(showBackground = true)
    @Composable
    private fun showLoginButton() {

        Button(onClick = {
//            doLogin()
            doMedia()
        }) {
            Text(text = "跳转")
        }


    }

    private fun doLogin() {
        val intent = Intent(MainActivity@ this, LoginActivity::class.java)
        startActivity(intent)

    }


    private fun isPhotoPickerAvailable(): Boolean {
        Log.e("xiebin", Build.VERSION.SDK_INT.toString())
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            true
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getExtensionVersion(Build.VERSION_CODES.R) >= 2

        } else {
            false
        }
    }

    private fun doMedia() {
        if (isPhotoPickerAvailable()) {
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        } else {
            // Consider implementing fallback functionality so that users can still
            // select images and videos.
            Log.e("xiebin", "bbbb")
        }


    }
}

