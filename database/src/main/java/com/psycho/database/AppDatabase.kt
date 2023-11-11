package com.psycho.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.psycho.database.schema.converters.financial.AmountConverter
import com.psycho.database.schema.converters.core.NetStatusConverter
import com.psycho.database.schema.converters.core.NetTypeConverter
import com.psycho.database.schema.converters.core.NetWorthStatusConverter
import com.psycho.database.schema.dao.core.AccountDao
import com.psycho.database.schema.dao.core.NetDao
import com.psycho.database.schema.dao.core.NetRecordDao
import com.psycho.database.schema.dao.core.NetWorthDao
import com.psycho.database.schema.entities.core.Account
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetRecord
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.utility.Constants.DATABASE_EXPORTED_VERSION
import com.psycho.database.utility.Constants.DATABASE_NAME
import com.psycho.database.utility.Constants.DATABASE_VERSION

@Database(entities=[Account::class, Net::class, NetRecord::class, NetWorth::class], version=DATABASE_EXPORTED_VERSION, exportSchema=false)
@TypeConverters(AmountConverter::class, NetTypeConverter::class, NetStatusConverter::class, NetWorthStatusConverter::class)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            return when (instance) {
                is AppDatabase -> instance!!
                else -> {
                    synchronized(AppDatabase::class) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .addMigrations()
                            .build()
                    }
                    instance!!
                }
            }
        }

        fun getVersion(): String {
            return String.format("AppDatabase(name=%s, version=%s, exportedVersion=%s)", DATABASE_NAME, DATABASE_VERSION, DATABASE_EXPORTED_VERSION)
        }

        override fun toString(): String {
            return "./AppDatabase"
        }
    }

    abstract fun getAccountDao(): AccountDao
    abstract fun getNetDao(): NetDao
    abstract fun getNetRecordDao(): NetRecordDao
    abstract fun getNetWorthDao(): NetWorthDao
}