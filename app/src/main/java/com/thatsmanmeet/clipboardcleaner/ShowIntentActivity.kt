package com.thatsmanmeet.clipboardcleaner

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ShowIntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val clipService : ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipText = remember {
                mutableStateOf("")
            }
            val scroll = rememberScrollState(0)
            val focusRequester = remember{FocusRequester()}
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
                        title = { Text(text = "Clipboard Data 📋") },
                        text = {
                           Column(
                               modifier = Modifier.heightIn(100.dp).focusRequester(focusRequester),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Center
                           ) {
                               if(clipText.value.isEmpty()){
                                   CircularProgressIndicator()
                               }else{
                                   Text(
                                       text = if(clipText.value == "null") "Clipboard is empty." else clipText.value,
                                       modifier = Modifier
                                           .fillMaxSize()
                                           .verticalScroll(scroll),
                                       fontSize = 14.sp
                                   )
                               }
                               LaunchedEffect(Dispatchers.IO) {
                                   focusRequester.requestFocus()
                                   val clipboardData = clipService.primaryClip?.getItemAt(0)?.text.toString()
                                   withContext(Dispatchers.Main){
                                       delay(1000)
                                       clipText.value = clipboardData
                                   }
                               }
                           }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    state.value = false
                                    this@ShowIntentActivity.onDestroy()
                                    finish()
                                }) {
                                Text(text = "Okay!")
                            }
                        })
                }
            }
        }
        }
    }