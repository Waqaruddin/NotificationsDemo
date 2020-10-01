package com.example.notificationsdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val notificationId = 1
    var channelId = "personal_info"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_simple_notification.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view){
            button_simple_notification -> createSimpleNotification()
        }
    }

    private fun createSimpleNotification(){
        createNotificationChannel()
        var builder = NotificationCompat.Builder(this, channelId)
        builder
            .setSmallIcon(R.drawable.ic_baseline_add_ic_call_24)
            .setContentTitle("Notification Title")
            .setContentText("You are getting a call")
            .priority = NotificationCompat.PRIORITY_DEFAULT

        var notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())

    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            var name = "personal_info"
            var description = "include all your personal information"
            var importance = NotificationManager.IMPORTANCE_DEFAULT
            var notificationChannel = NotificationChannel(channelId, name , importance)
            notificationChannel.description = description


            var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


}