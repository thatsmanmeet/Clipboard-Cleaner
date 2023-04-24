package com.thatsmanmeet.clipboardcleaner

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.ViewModel

class MainViewModel(context:Context) : ViewModel() {
    private val clipService : ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    fun showClipboard(context: Context){
        val currentItemInClipboard = clipService.primaryClip?.getItemAt(0)?.text.toString()
        if(currentItemInClipboard == "null"){
            Toast.makeText(context,"Clipboard is Empty",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,currentItemInClipboard,Toast.LENGTH_LONG).show()
        }
    }
    fun returnClipboardData(context: Context):String{
        val currentItemInClipboard = clipService.primaryClip?.getItemAt(0)?.text.toString()
        return if(currentItemInClipboard == "null"){
            "Clipboard is empty"
        }else{
            currentItemInClipboard
        }
    }
    fun cleanClipboard(context: Context){
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