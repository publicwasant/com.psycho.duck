package com.psycho.database.schema.converters.core

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.psycho.database.utility.core.NetWorthStatus

@ProvidedTypeConverter
class NetWorthStatusConverter {
    @TypeConverter
    fun fromString(string: String?): NetWorthStatus? {
        return NetWorthStatus.fromString(string)
    }

    @TypeConverter
    fun fromId(id: Int?): NetWorthStatus? {
        return NetWorthStatus.fromId(id)
    }

    @TypeConverter
    fun fromNetWorth(netWorthStatus: NetWorthStatus?): String? {
        return netWorthStatus?.string
    }
}