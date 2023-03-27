package com.thatsmanmeet.clipboardcleaner

import android.content.ClipboardManager
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
import androidx.compose.ui.unit.dp

class ShowIntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = remember {
                mutableStateOf(false)
            }
            state.value = true
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .blur(10.dp)
                    ){
                AlertDialog(
                    modifier = Modifier.padding(5.dp),
                    onDismissRequest = {
                        state.value = false
                        this@ShowIntentActivity.onDestroy()
                        finish()
                    },
                title = { Text(text = "Clipboard Data")},
                text = {
                   val clipService = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    val currentClip = clipService.primaryClip?.getItemAt(0)?.text.toString()
                    if(currentClip == "null"){
                        Text(text = "Clipboard is Empty")
                    }else{
                        Text(text = currentClip)
                    }
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