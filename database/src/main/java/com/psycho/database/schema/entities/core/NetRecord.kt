package com.psycho.database.schema.entities.core

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.psycho.database.schema.converters.financial.AmountConverter
import com.psycho.database.schema.entities.BaseEntity
import com.psycho.database.utility.financial.Amount
import java.io.Serializable

@Entity(tableName="netRecord")
class NetRecord(net: Net): BaseEntity(), Serializable {
    @PrimaryKey(autoGenerate=true)
    override var id: Int = 0

    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix="net_")
    var net: Net = net

    var title: String = ""
    var description: String = ""
    var dateRecorded: String = ""
    var dateUpdated: String = ""

    @TypeConverters(AmountConverter::class)
    @Embedded(prefix="amount_")
    var amount: Amount = Amount.from()

    var receiptUrl: String? = null

    override fun from(baseEntity: BaseEntity) {
        if (baseEntity is NetRecord) {
            id = baseEntity.id
            title = baseEntity.title
            description = baseEntity.description
            dateRecorded = baseEntity.dateRecorded
            dateUpdated = baseEntity.dateUpdated
            amount = baseEntity.amount
            receiptUrl = baseEntity.receiptUrl
        }
    }

    override fun copy(): NetRecord {
        val newNetRecord = NetRecord(net.copy())
        newNetRecord.id = id
        newNetRecord.title = title
        newNetRecord.description = description
        newNetRecord.dateRecorded = dateRecorded
        newNetRecord.dateUpdated = dateUpdated
        newNetRecord.amount = Amount.from(amount.toDouble())
        newNetRecord.receiptUrl = receiptUrl
        return newNetRecord
    }
}