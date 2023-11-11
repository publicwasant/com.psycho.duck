package com.psycho.database.managers.interfaces.core

import com.psycho.database.managers.interfaces.IBaseManager
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.database.utility.financial.Amount

interface INetWorthManager<T>: IBaseManager<T> {
    fun getNetAssetsAmount(netWorth: NetWorth): Amount
    fun getNetDebtsAmount(netWorth: NetWorth): Amount
    fun getNetAmount(netWorth: NetWorth): Amount
}