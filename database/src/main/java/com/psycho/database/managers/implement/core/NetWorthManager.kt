package com.psycho.database.managers.implement.core

import com.psycho.database.AppDatabase
import com.psycho.database.managers.di.DatabaseManagerService
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.managers.implement.BaseManager
import com.psycho.database.managers.interfaces.core.INetWorthManager
import com.psycho.database.schema.dao.core.NetWorthDao
import com.psycho.database.schema.entities.core.Net
import com.psycho.utility.core.NetType
import com.psycho.utility.financial.Amount

class NetWorthManager(appDatabase: AppDatabase): INetWorthManager<NetWorth>, BaseManager<NetWorth>(appDatabase.getNetWorthDao()) {
    override val dao: NetWorthDao = appDatabase.getNetWorthDao()

    override fun getNetAssetsAmount(netWorth: NetWorth): Amount {
        val tempNetList: List<Net> = DatabaseManagerService.provider.databaseManager.netManager.get(netWorth, NetType.ASSET)
        return DatabaseManagerService.provider.databaseManager.netManager.getNetAmount(tempNetList)
    }

    override fun getNetDebtsAmount(netWorth: NetWorth): Amount {
        val tempNetList: List<Net> = DatabaseManagerService.provider.databaseManager.netManager.get(netWorth, NetType.DEBT)
        return DatabaseManagerService.provider.databaseManager.netManager.getNetAmount(tempNetList)
    }

    override fun getNetAmount(netWorth: NetWorth): Amount {
        val calculatedNetAmount: Double = getNetAssetsAmount(netWorth).toDouble() - getNetDebtsAmount(netWorth).toDouble()
        return Amount.from(calculatedNetAmount)
    }
}