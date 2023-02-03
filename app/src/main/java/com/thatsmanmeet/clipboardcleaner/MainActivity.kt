package com.thatsmanmeet.clipboardcleaner


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MyApp()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable fun MyApp(){
    val context = LocalContext.current
    val clipService: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    ClipboardCleanerTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.brush),
                    contentDescription = null,
                    modifier = Modifier.size(130.dp).padding(5.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Clipboard Cleaner",
                    fontSize = 25.sp,
                fontWeight = FontWeight.Light)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "This app allows you to see or clean your android's clipboard. This app might not work on some devices.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        val currentClip = clipService.primaryClip?.getItemAt(0)?.text.toString()
                        Toast.makeText(context,"$currentClip",Toast.LENGTH_SHORT).show()
                    }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "Show Clipboard")
                    }
                    Button(onClick = {
                        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
                            clipService.clearPrimaryClip()
                            Toast.makeText(context,
                                "Clipboard Cleaned",Toast.LENGTH_SHORT).show()
                        }else{
                            val clipData = ClipData.newPlainText("","")
                            clipService.setPrimaryClip(clipData)
                            Toast.makeText(context,
                                "Clipboard Cleaned",Toast.LENGTH_SHORT).show()
                        }
                    }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "Clean Clipboard")
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f),
                    contentAlignment = Alignment.BottomCenter){
                        Text(
                            text = "Made with ❤️ by Manmeet",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                                .padding(10.dp),
                            fontWeight = FontWeight.Light
                        )
                    }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun PreviewApp(){
    MyApp()
}