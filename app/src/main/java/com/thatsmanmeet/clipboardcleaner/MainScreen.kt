package com.thatsmanmeet.clipboardcleaner



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier){
    val context = LocalContext.current
    val mainViewModel = MainViewModel(context = context)
    ClipboardCleanerTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Clipboard Cleaner")},
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) {
            Column(
                modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(25.dp))
                Image(painter = painterResource(id = R.drawable.brush), contentDescription = null)
                Spacer(modifier = modifier.height(25.dp))
                Text(text = "Clipboard Cleaner", fontSize = 30.sp)
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    modifier = modifier.padding(10.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    text = "This app allows you to see or clean your android's clipboard. This app may not work on some devices."
                )
                Spacer(modifier = modifier.height(8.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                         mainViewModel.showClipboard(context)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF05A394),
                        contentColor = Color.White
                    )) {
                        Text(text = "Show Clipboard")
                    }
                    Button(onClick = {
                         mainViewModel.cleanClipboard(context)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF84F42),
                        contentColor = Color.White
                    )) {
                        Text(text = "Clean Clipboard")
                    }
                }
                Spacer(modifier = modifier.height(8.dp))
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    elevation = CardDefaults.cardElevation(3.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Hey! Now you can see or clean your clipboard from quick panels. Happy Cleaning!",
                        modifier = modifier.padding(10.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}