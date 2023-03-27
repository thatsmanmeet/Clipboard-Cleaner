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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
           MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable fun MyApp(){
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val clipService: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    // App Main Screen starts from here
    ClipboardCleanerTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Clipboard Cleaner")},
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = Color(0xFF018786),
                            titleContentColor = Color.White
                        )
                    )
                }
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Image(
                        painter = painterResource(id = R.drawable.brush),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .padding(5.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Clipboard Cleaner",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Light)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "This app allows you to see or clean your android's clipboard. This app might not work on some devices.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        horizontalArrangement = Arrangement.Center) {
                        Button(onClick = {
                            val currentClip = clipService.primaryClip?.getItemAt(0)?.text.toString()
                            if(currentClip == "null"){
                                Toast.makeText(context, "Clipboard is empty",Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(context, currentClip,Toast.LENGTH_LONG).show()
                            }
                        },
                            modifier = Modifier.padding(5.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009688), contentColor = Color.White)) {
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
                        }, modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336), contentColor = Color.White)) {
                            Text(text = "Clean Clipboard")
                        }
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(18.dp),
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodySmall,
                            text = "Hey, Now you can view and clean your clipboard using tiles from quick panel. Happy Cleaning!"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f),
                        contentAlignment = Alignment.BottomCenter){
                        Text(
                            text = "Made with ❤️ by Manmeet",
                            textAlign = TextAlign.Center,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    uriHandler.openUri("https://github.com/thatsmanmeet")
                                },
                            fontWeight = FontWeight.SemiBold
                        )
                    }
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