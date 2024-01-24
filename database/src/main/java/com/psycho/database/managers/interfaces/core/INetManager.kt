package com.psycho.database.managers.interfaces.core

import com.psycho.utility.core.NetStatus
import com.psycho.utility.core.NetType
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.managers.interfaces.IBaseManager
import com.psycho.database.schema.entities.core.Net
import com.psycho.utility.financial.Amount

interface INetManager<T>: IBaseManager<T> {
    fun get(netWorth: NetWorth?): List<T>
    fun get(netWorth: NetWorth?, netType: NetType?): List<T>
    fun get(netWorth: NetWorth?, netStatus: NetStatus?): List<T>
    fun getLast(netWorth: NetWorth?): T?
    fun getLast(netWorth: NetWorth?, netType: NetType?): T?
    fun getLast(netWorth: NetWorth?, netStatus: NetStatus?): T?
    fun getNetAmount(net: Net): Amount
    fun getNetAmount(netList: List<Net>): Amount
}