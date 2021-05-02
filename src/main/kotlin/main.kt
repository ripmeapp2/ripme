import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.svgResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp



fun main() = Window(title = "RipMe") {
    MaterialTheme {

        Column {
            TextInput()
            NavigationBar()

        }
    }

}

private enum class NavType {
    LOG, HISTORY, QUEUE, SETTINGS
}

@Composable
private fun NavigationBar() {
    Spacer(modifier = Modifier.height(16.dp))
    val navItemState = remember { mutableStateOf(NavType.LOG) }

    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    svgResource("black-comments-bubble-svgrepo-com.svg"),
                    contentDescription = "Log",
                    modifier = Modifier.fillMaxSize()
                )
            },
            selected = navItemState.value == NavType.LOG,
            onClick = { navItemState.value = NavType.LOG },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    svgResource("history-svgrepo-com.svg"),
                    contentDescription = "History",
                    modifier = Modifier.fillMaxSize()
                )
            },
            selected = navItemState.value == NavType.HISTORY,
            onClick = { navItemState.value = NavType.HISTORY },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    svgResource("list-with-bullets-svgrepo-com.svg"),
                    contentDescription = "Queue",
                    modifier = Modifier.fillMaxSize()
                )
            },
            selected = navItemState.value == NavType.QUEUE,
            onClick = { navItemState.value = NavType.QUEUE },
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    svgResource("gear-symbol-svgrepo-com.svg"),
                    contentDescription = "Settings",
                    modifier = Modifier.fillMaxSize()
                )
            },
            selected = navItemState.value == NavType.SETTINGS,
            onClick = { navItemState.value = NavType.SETTINGS },
        )
    }
}




@Composable
private fun TextInput() {
    var ripUrl by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = ripUrl,
        trailingIcon = {
            IconButton(onClick = {
                var url: java.net.URL? = java.net.URL(ripUrl.toString())
            }) {
                Icon(
                    svgResource("download-from-cloud-svgrepo-com.svg"),
                    contentDescription = "Download",
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = "URL:") },
        placeholder = { Text(text = "Download URL.") },
        onValueChange = {
            ripUrl = it
        }
    )
}
