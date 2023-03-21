package com.thatsmanmeet.clipboardcleaner

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ShowIntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.actionBar!!.hide()
            val state = remember {
                mutableStateOf(false)
            }
            state.value = true
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .blur(10.dp)
                    .background(Color.Transparent)
                    ){
                AlertDialog(
                    modifier = Modifier.padding(5.dp),
                    onDismissRequest = {
                        state.value = false
                        finish()
                    },
                title = { Text(text = "Clipboard Data")},
                text = {
                   val clipService = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val currentClip = clipService.primaryClip?.getItemAt(0)?.text.toString()
                    val secClip = clipService.hasPrimaryClip()
                    Log.d("TAG", "onCreate: $secClip ")
                    if(currentClip == "null"){
                        Text(text = "Clipboard is Empty")
                    }else{
                        Text(text = currentClip)
                    }
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.background(Color.Transparent),
                        onClick = {
                        state.value = false
                        finish()
                    }) {
                        Text(text = "OK")
                    }
                })
            }
            }
        }
    }