package com.thatsmanmeet.clipboardcleaner

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ShowIntentActivity : ComponentActivity() {
    private lateinit var clipService : ClipboardManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val clipText = remember {
                mutableStateOf("")
            }
            val scroll = rememberScrollState(0)
            val focusRequester = remember{FocusRequester()}
            ClipboardCleanerTheme {
                val state = remember {
                    mutableStateOf(false)
                }
                clipService = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                state.value = true
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AlertDialog(
                        modifier = Modifier
                            .padding(5.dp)
                            .focusRequester(focusRequester),
                        onDismissRequest = {
                            state.value = false
                            this@ShowIntentActivity.onDestroy()
                            finish()
                        },
                        title = {
                            Text(text = "Clipboard Data 📋", fontSize = 22.sp)
                                },
                        text = {
                           Column(
                               modifier = Modifier.heightIn(150.dp).padding(5.dp),
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
                                       fontSize = 14.sp,
                                       fontWeight = FontWeight.SemiBold
                                   )
                               }
                               LaunchedEffect(Dispatchers.IO) {
                                   withContext(Dispatchers.Main){
                                       focusRequester.requestFocus()
                                       delay(1000)
                                       clipText.value =  clipService.primaryClip?.getItemAt(0)?.text.toString()
                                   }
                               }
                           }
                        },
                        dismissButton = {
                            Button(onClick = {
                                state.value = false
                                this@ShowIntentActivity.onDestroy()
                                finish()
                            },
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color(
                                    0xFFF44336
                                )
                                )
                            ) {
                                Text(text = "Dismiss")
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    state.value = false
                                    cleanClipboard(this@ShowIntentActivity)
                                    this@ShowIntentActivity.onDestroy()
                                    finish()
                                },
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color(
                                    0xFF4CAF50
                                )
                                )
                            ) {
                                Text(text = "Clean clipboard")
                            }
                        })
                }
            }
        }
        }
    private fun cleanClipboard(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            clipService.clearPrimaryClip()
            Toast.makeText(context, "Clipboard Cleaned", Toast.LENGTH_SHORT).show()
        }else{
            val newClipboardData = ClipData.newPlainText("","")
            clipService.setPrimaryClip(newClipboardData)
            Toast.makeText(context, "Clipboard Cleaned", Toast.LENGTH_SHORT).show()
        }
    }

}