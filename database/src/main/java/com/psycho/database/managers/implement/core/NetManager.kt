package com.psycho.database.managers.implement.core

import com.psycho.database.AppDatabase
import com.psycho.database.managers.di.DatabaseManagerService
import com.psycho.database.schema.dao.core.NetDao
import com.psycho.database.utility.core.NetStatus
import com.psycho.database.utility.core.NetType
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.managers.implement.BaseManager
import com.psycho.database.managers.interfaces.core.INetManager
import com.psycho.database.schema.entities.core.NetRecord
import com.psycho.database.utility.financial.Amount

class NetManager(appDatabase: AppDatabase): INetManager<Net>, BaseManager<Net>(appDatabase.getNetDao()) {
    override val dao: NetDao = appDatabase.getNetDao()

    override fun get(netWorth: NetWorth?): List<Net> {
        return dao.getByNetWorthId(netWorth?.id)
    }

    override fun get(netWorth: NetWorth?, netType: NetType?): List<Net> {
        return dao.getByNetWorthIdAndNetTypeId(netWorth?.id, netType?.id)
    }


    override fun get(netWorth: NetWorth?, netStatus: NetStatus?): List<Net> {
        return dao.getByNetWorthIdAndNetStatusId(netWorth?.id, netStatus?.id)
    }

    override fun getLast(netWorth: NetWorth?): Net? {
        return dao.getLastByNetWorthId(netWorth?.id)
    }

    override fun getLast(netWorth: NetWorth?, netType: NetType?): Net? {
        return dao.getLastByNetWorthIdAndNetTypeId(netWorth?.id, netType?.id)
    }

    override fun getLast(netWorth: NetWorth?, netStatus: NetStatus?): Net? {
        return dao.getLastByNetWorthIdAndNetStatusId(netWorth?.id, netStatus?.id)
    }

    override fun getNetAmount(net: Net): Amount {
        val tempNetRecordList: List<NetRecord> = DatabaseManagerService.provider.databaseManager.netRecordManager.get(net)
        val sumOfNetAmount: Double = tempNetRecordList.sumOf { netRecord -> netRecord.amount.toDouble() }
        return Amount.from(sumOfNetAmount)
    }

    override fun getNetAmount(netList: List<Net>): Amount {
        val tempNetRecordList: List<NetRecord> = DatabaseManagerService.provider.databaseManager.netRecordManager.get(netList)
        val sumOfNetAmount: Double = tempNetRecordList.sumOf { netRecord -> netRecord.amount.toDouble() }
        return Amount.from(sumOfNetAmount)
    }
}