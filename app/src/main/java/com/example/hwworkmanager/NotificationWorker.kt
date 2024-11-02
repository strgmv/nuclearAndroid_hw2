package com.example.hwworkmanager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val notification = NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("DO YOUR HOMEWORK")
            .setContentText("WHY IT HADN'T DONE YET")
            .build()

        Log.d("Notification Worker", "Sending notification...")
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return Result.failure()
        }
        NotificationManagerCompat.from(applicationContext).notify(1, notification)
        Log.d("Notification Worker", "Notification sent")
        return Result.success()
    }
}