package application

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import notification.Notification
import notification.queue.rememberNotificationQueue

fun main() = singleWindowApplication {
    val notificationQueue = rememberNotificationQueue()

    var i by remember { mutableIntStateOf(0) }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.Black), contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                notificationQueue.push("${i++}", Icons.Default.Info)
            }) {
                Text("Notify")
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Notification(notificationQueue = notificationQueue) { notification ->
                    Card {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 8.dp,
                                alignment = Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.weight(1f))
                            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                Text(notification.message, modifier = Modifier.padding(8.dp))
                            }
                            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                                notification.label?.let { label ->
                                    Image(label, null)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}