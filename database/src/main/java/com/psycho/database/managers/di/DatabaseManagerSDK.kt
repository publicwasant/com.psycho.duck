package com.psycho.database.managers.di

import android.content.Context
import android.util.Log
import android.util.Log.INFO
import com.psycho.database.AppDatabase
import com.psycho.database.utility.Constants.DATABASE_MANAGER_SDK_TAG
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

object DatabaseManagerSDK {
    private lateinit var appContext: Context
    private lateinit var databaseManagerFactory: DatabaseManagerFactory

    val koin: Koin by lazy {
        koinApplication {
            androidContext(appContext)
            modules(sdkModule(databaseManagerFactory))
        }.koin
    }

    @Synchronized
    fun init(context: Context) {
        val dataManagerFactory: DatabaseManagerFactory = {
            DatabaseManageProvider(context)
        }
        check(!::appContext.isInitialized) {
            Log.println(INFO, DATABASE_MANAGER_SDK_TAG, "%s/%s: Initial %s".format(DatabaseManagerService.toString(), toString(), AppDatabase.getVersion()))
        }
        this.appContext = context.applicationContext
        this.databaseManagerFactory = dataManagerFactory
        Log.println(INFO, DATABASE_MANAGER_SDK_TAG, "%s/%s: Initial %s".format(DatabaseManagerService.toString(), toString(), AppDatabase.getVersion()))
    }

    override fun toString(): String {
        return this.javaClass.simpleName
    }
}

fun sdkModule(dataManagerFactory: DatabaseManagerFactory): Module {
    return module {
        single {
            dataManagerFactory.invoke(get())
        }
    }
}

