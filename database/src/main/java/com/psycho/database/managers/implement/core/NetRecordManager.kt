package com.psycho.database.managers.implement.core

import com.psycho.database.AppDatabase
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetRecord
import com.psycho.database.managers.implement.BaseManager
import com.psycho.database.managers.interfaces.core.INetRecordManager
import com.psycho.database.schema.dao.core.NetRecordDao

class NetRecordManager(appDatabase: AppDatabase): INetRecordManager<NetRecord>, BaseManager<NetRecord>(appDatabase.getNetRecordDao()) {
    override val dao: NetRecordDao = appDatabase.getNetRecordDao()

    override fun get(net: Net?): List<NetRecord> {
        return dao.getByNetId(net?.id)
    }

    override fun get(netList: List<Net>): List<NetRecord> {
        val tempNetIdList: ArrayList<Int> = arrayListOf()
        netList.forEach { net ->
            tempNetIdList.add(net.id)
        }
        return dao.getByNetId(tempNetIdList)
    }
}