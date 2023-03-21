package com.thatsmanmeet.clipboardcleaner

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class IntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
                clipboard.clearPrimaryClip()
                Toast.makeText(this,
                    "Clipboard Cleaned", Toast.LENGTH_SHORT).show()
            }else{
                val clipData = ClipData.newPlainText("","")
                clipboard.setPrimaryClip(clipData)
                Toast.makeText(this,
                    "Clipboard Cleaned", Toast.LENGTH_SHORT).show()
            }
            this.onDestroy()
            finish()
        }
    }
}