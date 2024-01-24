package com.psycho.database.schema.converters.financial

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.psycho.utility.financial.Amount

@ProvidedTypeConverter
class AmountConverter {
    @TypeConverter
    fun fromString(string: String?): Amount {
        return Amount.from(string)
    }

    @TypeConverter
    fun fromDouble(double: Double?): Amount {
        return Amount.from(double)
    }

    @TypeConverter
    fun fromInt(int: Int?): Amount {
        return Amount.from(int)
    }

    @TypeConverter
    fun fromLong(long: Long?): Amount {
        return Amount.from(long)
    }

    @TypeConverter
    fun fromAmount(amount: Amount): String {
        return amount.toString()
    }
}