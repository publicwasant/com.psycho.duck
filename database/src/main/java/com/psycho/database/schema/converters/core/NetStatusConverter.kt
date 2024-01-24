package com.psycho.database.schema.converters.core

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.psycho.utility.core.NetStatus

@ProvidedTypeConverter
class NetStatusConverter {
    @TypeConverter
    fun fromString(string: String?): NetStatus? {
        return NetStatus.fromString(string)
    }

    @TypeConverter
    fun fromId(id: Int?): NetStatus? {
        return NetStatus.fromId(id)
    }

    @TypeConverter
    fun fromNetStatus(netStatus: NetStatus?): String? {
        return netStatus?.toString()
    }
}