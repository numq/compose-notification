package notification.queue

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.channels.Channel
import notification.NotificationItem

class DefaultQueue : NotificationQueue {
    override val notifications = Channel<NotificationItem>(Channel.BUFFERED)

    override fun push(message: String, icon: ImageVector?) {
        notifications.trySend(NotificationItem(message = message))
    }

    override fun close() {
        notifications.close()
    }
}