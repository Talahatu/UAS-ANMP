package com.example.midterm_160420014.util

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

class CheckoutWorker(val context: Context, val params: WorkerParameters):Worker(context,params) {
    override fun doWork(): ListenableWorker.Result {
        NotificationHelper(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString()
        )
        return ListenableWorker.Result.success()
    }
}