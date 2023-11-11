package com.psycho.database.managers.interfaces

import com.psycho.database.schema.entities.core.Account
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetRecord
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.managers.interfaces.core.IAccountManager
import com.psycho.database.managers.interfaces.core.INetManager
import com.psycho.database.managers.interfaces.core.INetRecordManager
import com.psycho.database.managers.interfaces.core.INetWorthManager

interface IDatabaseManager {
    val accountManager: IAccountManager<Account>
    val netManager: INetManager<Net>
    val netRecordManager: INetRecordManager<NetRecord>
    val netWorthManager: INetWorthManager<NetWorth>
}