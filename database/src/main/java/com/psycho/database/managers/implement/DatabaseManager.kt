package com.psycho.database.managers.implement

import com.psycho.database.AppDatabase
import com.psycho.database.schema.entities.core.Account
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetRecord
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.managers.implement.core.AccountManager
import com.psycho.database.managers.implement.core.NetManager
import com.psycho.database.managers.implement.core.NetRecordManager
import com.psycho.database.managers.implement.core.NetWorthManager
import com.psycho.database.managers.interfaces.IDatabaseManager
import com.psycho.database.managers.interfaces.core.IAccountManager
import com.psycho.database.managers.interfaces.core.INetManager
import com.psycho.database.managers.interfaces.core.INetRecordManager
import com.psycho.database.managers.interfaces.core.INetWorthManager

class DatabaseManager(private val appDatabase: AppDatabase): IDatabaseManager {
    override val accountManager: IAccountManager<Account> by lazy {
        AccountManager(appDatabase)
    }

    override val netManager: INetManager<Net> by lazy {
        NetManager(appDatabase)
    }

    override val netRecordManager: INetRecordManager<NetRecord> by lazy {
        NetRecordManager(appDatabase)
    }

    override val netWorthManager: INetWorthManager<NetWorth> by lazy {
        NetWorthManager(appDatabase)
    }
}