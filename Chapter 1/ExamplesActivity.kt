package com.codingtroops.dummycomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.concurrent.timer

class ExamplesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var seconds by mutableStateOf(0)
        val stopWatchTimer = timer(period = 1000) { seconds++ }

        super.onCreate(savedInstanceState)
        setContent {
            TimerText(seconds)
        }
    }
}

@Composable
fun FriendlyMessage(name: String) {
    Text(text = "Greetings $name!")
}

@Composable
fun SuggestiveButton() {
    Button(onClick = { }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )
            Text(text = "Press me", style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun MailButton(mailId: Int, mailPressedCallback: (Int) -> Unit) {
    Button(onClick = { mailPressedCallback(mailId) }) {
        Text(text = "Expand mail $mailId")
    }
}

@Composable
fun TimerText(seconds: Int) {
    Text(text = "Elapsed: $seconds")
}

@Preview(
    name = "Greeting preview",
    showSystemUi = true,
    device = Devices.PIXEL_2_XL
)
@Composable
fun FriendlyMessagePreview() {
    Text(text = "Greetings!")
}

@Preview(
    showSystemUi = true,
    device = Devices.NEXUS_5
)
@Composable
fun FriendlyMessagePreview2() {
    Text(text = "Goodbye!")
}

@Composable
fun MyAppText() {
    Text(
        text = stringResource(id = R.string.app_name),
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        color = Color.Magenta,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun ClickableButton() {
    Button(
        onClick = { /* callback */ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.Red
        ),
        shape = MaterialTheme.shapes.medium
    ) { Text("Press me") }
}

@Composable
fun NameInput() {
    val textState = remember { mutableStateOf("") }
    TextField(
        value = textState.value,
        onValueChange = { newValue -> textState.value = newValue },
        label = { Text("Your name") })
}


@Composable
fun BeautifulImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "My app icon",
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ColoredBox() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(Color.Green)
            .padding(16.dp)
            .clip(RoundedCornerShape(size = 20.dp))
            .background(Color.Red)
    )
}

@Composable
fun HorizontalNumbersList() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("1", fontSize = 36.sp)
        Text("2", fontSize = 36.sp)
        Text("3", fontSize = 36.sp)
        Text("4", fontSize = 36.sp)
    }
}

@Composable
fun VerticalNamesList() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("John", fontSize = 36.sp)
        Text("Amanda", fontSize = 36.sp)
        Text("Mike", fontSize = 36.sp)
        Text("Alma", fontSize = 36.sp)
    }
}

@Composable
fun MyFloatingActionButton() {
    Box {
        Surface(
            modifier = Modifier.size(32.dp),
            color = Color.Green,
            shape = CircleShape
        ) { }
        Text(
            text = "+",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}




























