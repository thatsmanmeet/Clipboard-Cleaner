package com.thatsmanmeet.clipboardcleaner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme

class ShowIntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val mainViewModel = MainViewModel(context = context)
            ClipboardCleanerTheme {
                val state = remember {
                    mutableStateOf(false)
                }
                state.value = true
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(10.dp)
                ) {
                    AlertDialog(
                        modifier = Modifier.padding(5.dp),
                        onDismissRequest = {
                            state.value = false
                            this@ShowIntentActivity.onDestroy()
                            finish()
                        },
                        title = { Text(text = "Clipboard Data") },
                        text = {
                            val clipboardData = mainViewModel.returnClipboardData(context)
                            Text(text = clipboardData)
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    state.value = false
                                    this@ShowIntentActivity.onDestroy()
                                    finish()
                                }) {
                                Text(text = "OK")
                            }
                        })
                }
            }
        }
        }
    }