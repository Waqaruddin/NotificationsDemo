package com.example.notificationsdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
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
        button_click.setOnClickListener(this)
        button_action.setOnClickListener(this)
        button_large_text.setOnClickListener(this)
        button_pic.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view){
            button_simple_notification -> createSimpleNotification()
            button_click -> createNotificationWithClick()
            button_action -> createNotificationWithActionButton()
            button_large_text -> createNotificationWithBigText()
            button_pic -> createNotificationWithBigPicture()
        }

    }


    private fun createNotificationWithBigPicture(){
        createNotificationChannel()

        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.amazon)


        var builder = NotificationCompat.Builder(this, channelId)
        builder
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setSmallIcon(R.drawable.ic_baseline_add_ic_call_24)
            .setContentTitle("Big Picture")
            .setContentText("Example of big picture notification...")
//
            .setAutoCancel(true)
            .priority = NotificationCompat.PRIORITY_DEFAULT

        var notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())

    }

    private fun createNotificationWithBigText(){
        createNotificationChannel()

        var text = "This is a practice for big text notifications. You can display very long messages on this type of notifications. I can keep on going but you get the idea."

        var builder = NotificationCompat.Builder(this, channelId)
        builder
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setSmallIcon(R.drawable.ic_baseline_add_ic_call_24)
            .setContentTitle("Long Text")
            .setContentText("Example of long text...")
//
            .setAutoCancel(true)
            .priority = NotificationCompat.PRIORITY_DEFAULT

        var notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())

    }



    private fun createNotificationWithActionButton(){
        createNotificationChannel()

        var yesIntent = Intent(this, YesActivity::class.java)
        var noIntent = Intent(this, NoActivity::class.java)

        var yesPendingIntent = PendingIntent.getActivity(this, 0,yesIntent, PendingIntent.FLAG_ONE_SHOT)
        var noPendingIntent = PendingIntent.getActivity(this,0,  noIntent, PendingIntent.FLAG_ONE_SHOT)



        var intent = Intent(this, LandingActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        var builder = NotificationCompat.Builder(this, channelId)
        builder
            .setSmallIcon(R.drawable.ic_baseline_add_ic_call_24)
            .setContentTitle("Fake Call")
            .setContentText("You are getting a call...")
//            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_baseline_check_24, "Yes", yesPendingIntent)
            .addAction(R.drawable.ic_baseline_cancel_24, "No", noPendingIntent)
            .setAutoCancel(true)
            .priority = NotificationCompat.PRIORITY_DEFAULT

        var notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())

    }


    private fun createNotificationWithClick(){
        createNotificationChannel()
        var intent = Intent(this, LandingActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        var builder = NotificationCompat.Builder(this, channelId)
        builder
            .setSmallIcon(R.drawable.ic_baseline_add_ic_call_24)
            .setContentTitle("Fake Call")
            .setContentText("You are getting a call...")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .priority = NotificationCompat.PRIORITY_DEFAULT

        var notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())

    }

    private fun createSimpleNotification(){
        createNotificationChannel()


        var builder = NotificationCompat.Builder(this, channelId)
        builder
            .setSmallIcon(R.drawable.ic_baseline_add_ic_call_24)
            .setContentTitle("Fake Call")
            .setContentText("You are getting a call...")
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