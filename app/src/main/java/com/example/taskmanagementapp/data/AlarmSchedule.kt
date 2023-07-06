package com.example.taskmanagementapp.data

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.taskmanagementapp.R
import java.time.LocalDateTime
import java.util.*

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title")
        val detail = intent.getStringExtra("detail")

        // Tạo kênh thông báo (chỉ cần thiết cho Android 8 trở lên)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "channelId"
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, "channelId")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(detail)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Để tự động xóa thông báo sau khi được nhấn

        // Gửi thông báo ngay lập tức
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(0, notificationBuilder.build())
    }
}

// Hàm để tạo và đặt thông báo trước 10 phút
fun scheduleNotification(
    context: Context,
    eventTime: LocalDateTime,
    title: String,
    detail: String,
    minusTime : Int
) {
    // Tạo intent để gửi thông báo
    val intent = Intent(context, NotificationBroadcastReceiver::class.java)
    intent.putExtra("title", title)
    intent.putExtra("detail", detail)

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Tạo thời gian sự kiện lúc 10 giờ
    val eventStartTime = Calendar.getInstance()
    eventStartTime.set(Calendar.HOUR_OF_DAY, eventTime.hour)
    eventStartTime.set(Calendar.MINUTE, eventTime.minute)
    eventStartTime.set(Calendar.SECOND, 0)

    // Trừ đi 10 phút
    eventStartTime.add(Calendar.MINUTE, -minusTime)

    // Lấy thời gian hiện tại
    val currentTime = Calendar.getInstance()

    // So sánh với thời gian hiện tại
    if (currentTime.before(eventStartTime)) {
        // Lên lịch đặt thông báo trước 10 phút
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                eventStartTime.timeInMillis,
                pendingIntent
            )
            Log.d(
                "emiT Set 1",
                "${eventStartTime.get(Calendar.HOUR_OF_DAY)}:${eventStartTime.get(Calendar.MINUTE)}"
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                eventStartTime.timeInMillis,
                pendingIntent
            )

            Log.d(
                "emiT Set 2",
                "${eventStartTime.get(Calendar.HOUR_OF_DAY)}:${eventStartTime.get(Calendar.MINUTE)}"
            )
        }
    }
}
