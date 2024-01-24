package com.psycho.database.managers.interfaces.core

import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetRecord
import com.psycho.database.managers.interfaces.IBaseManager

interface INetRecordManager<T>: IBaseManager<T> {
    fun get(net: Net?): List<NetRecord>
    fun get(netList: List<Net>): List<NetRecord>
}