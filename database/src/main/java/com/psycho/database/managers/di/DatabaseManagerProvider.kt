package com.psycho.database.managers.di

import android.content.Context
import com.psycho.database.AppDatabase
import com.psycho.database.managers.implement.DatabaseManager
import com.psycho.database.managers.interfaces.IDatabaseManager

interface IDatabaseManagerProvider {
    val databaseManager: IDatabaseManager
}

class DatabaseManageProvider(private val context: Context): IDatabaseManagerProvider {
    override val databaseManager: IDatabaseManager by lazy { DatabaseManager(AppDatabase.getInstance(context)) }
}