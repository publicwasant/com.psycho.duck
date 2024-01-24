package com.psycho.database.schema.converters.core

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.psycho.utility.core.NetType

@ProvidedTypeConverter
class NetTypeConverter {
    @TypeConverter
    fun fromString(string: String?): NetType? {
        return NetType.fromString(string)
    }

    @TypeConverter
    fun fromId(id: Int?): NetType? {
        return NetType.fromId(id)
    }

    @TypeConverter
    fun fromNetState(netType: NetType?): String? {
        return netType?.toString()
    }
}