package com.thatsmanmeet.clipboardcleaner



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thatsmanmeet.InfoCard
import com.thatsmanmeet.clipboardcleaner.ui.theme.ClipboardCleanerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier){
    val context = LocalContext.current
    val mainViewModel = MainViewModel(context = context)
    val scrollState = rememberScrollState()
    ClipboardCleanerTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Clipboard Cleaner")},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF4CAF50),
                        titleContentColor = Color.White
                    )
                )
            }
        ) {
            Column(
                modifier
                    .verticalScroll(scrollState)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = modifier.height(25.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.brush), contentDescription = null,modifier = modifier.size(55.dp))
                    Spacer(modifier = modifier.width(25.dp))
                    Column {
                        Text(text = "Clipboard Cleaner", fontSize = 25.sp)
                        Spacer(modifier = modifier.height(8.dp))
                        Text(
                            modifier = modifier.padding(2.dp),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            text = "This app allows you to see or clean your android's clipboard. This app may not work on some devices."
                        )
                        Spacer(modifier = modifier.height(8.dp))
                    }
                }
                Spacer(modifier = modifier.height(15.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        // TODO
                         mainViewModel.showClipboard(context)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(start = 5.dp)
                ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Note Info Icon")
                Spacer(modifier = modifier.width(5.dp))
                Text(text = "Notes", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = modifier.height(8.dp))
                InfoCard(
                    text = "Hey! Now you can see or clean your clipboard from quick panels. Happy Cleaning!",
                    backgroundColor = Color(0xFF4CAF50)
                )
                InfoCard(
                    text = "Please Keep in mind that this app cannot clean the inbuilt clipboard of various keyboard apps.",
                    backgroundColor = Color(0xFFDB4343)
                )
                
            }
        }
    }
}