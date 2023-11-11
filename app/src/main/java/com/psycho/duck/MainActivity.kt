package com.psycho.duck

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.psycho.database.managers.di.DatabaseManagerSDK
import com.psycho.database.managers.di.DatabaseManagerService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseManagerSDK.init(this)
        DatabaseManagerService.provider.databaseManager.netManager.get()
    }
}