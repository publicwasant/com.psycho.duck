package com.psycho.database.schema.entities.core

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.psycho.database.schema.converters.financial.AmountConverter
import com.psycho.utility.core.NetType
import com.psycho.utility.core.NetStatus
import com.psycho.database.schema.converters.core.NetTypeConverter
import com.psycho.database.schema.converters.core.NetStatusConverter
import com.psycho.database.schema.entities.BaseEntity
import com.psycho.utility.financial.Amount
import java.io.Serializable

@Entity(tableName="net")
class Net(netWorth: NetWorth, account: Account? = null): BaseEntity(), Serializable {
    @PrimaryKey(autoGenerate=true)
    override var id: Int = 0

    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix="netWorth_")
    var netWorth: NetWorth = netWorth

    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix="account_")
    var account: Account? = account

    var title: String = ""
    var description: String = ""
    var dateCreated: String = ""
    var dateUpdated: String = ""

    @TypeConverters(AmountConverter::class)
    @Embedded(prefix="netGoalsAmount_")
    var netGoalsAmount: Amount = Amount.from()

    @TypeConverters(NetTypeConverter::class)
    @Embedded(prefix="netType_")
    var netType: NetType = NetType.ASSET

    @TypeConverters(NetStatusConverter::class)
    @Embedded(prefix="netStatus_")
    var netStatus: NetStatus = NetStatus.ACTIVE

    override fun from(baseEntity: BaseEntity) {
        if (baseEntity is Net) {
            id = baseEntity.id
            title = baseEntity.title
            description = baseEntity.description
            dateCreated = baseEntity.dateCreated
            dateUpdated = baseEntity.dateUpdated
            netGoalsAmount = baseEntity.netGoalsAmount
            netType = baseEntity.netType
            netStatus = baseEntity.netStatus
        }
    }

    override fun copy(): Net {
        val newNet = Net(netWorth.copy(), account?.copy())
        newNet.id = id
        newNet.title = title
        newNet.description = description
        newNet.dateCreated = dateCreated
        newNet.dateUpdated = dateUpdated
        newNet.netGoalsAmount = Amount.from(netGoalsAmount.toDouble())
        newNet.netType = NetType.fromId(netType.id)!!
        newNet.netStatus = NetStatus.fromId(netStatus.id)!!
        return newNet
    }
}