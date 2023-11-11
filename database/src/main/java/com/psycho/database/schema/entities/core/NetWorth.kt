package com.psycho.database.schema.entities.core

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.psycho.database.managers.di.DatabaseManagerService
import com.psycho.database.utility.core.NetWorthStatus
import com.psycho.database.schema.converters.core.NetWorthStatusConverter
import com.psycho.database.schema.entities.BaseEntity
import com.psycho.database.utility.core.NetType
import com.psycho.database.utility.financial.Amount
import com.psycho.database.utility.financial.FinancialUtils
import java.io.Serializable

@Entity(tableName="netWorth")
class NetWorth: BaseEntity(), Serializable {
    @PrimaryKey(autoGenerate=true)
    override var id: Int = 0

    @TypeConverters(NetWorthStatusConverter::class)
    @Embedded(prefix="netWorthStatus_")
    var netWorthStatus: NetWorthStatus = NetWorthStatus.BALANCE

    override fun from(baseEntity: BaseEntity) {
        if (baseEntity is NetWorth) {
            id = baseEntity.id
            netWorthStatus = baseEntity.netWorthStatus
        }
    }

    override fun copy(): NetWorth {
        val newNetWorth = NetWorth()
        newNetWorth.id = id
        newNetWorth.netWorthStatus = NetWorthStatus.fromId(netWorthStatus.id)!!
        return newNetWorth
    }
}