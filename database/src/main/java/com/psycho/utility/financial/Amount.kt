package com.psycho.utility.financial

import java.io.Serializable

data class Amount (val standard: String, val decorated: String, val decimal: Double): Serializable {
    fun toStandard(): String {
        return standard
    }

    fun toDouble(): Double {
        return decimal
    }
    fun toInt(): Int {
        return decimal.toInt()
    }

    fun toLong(): Long {
        return decimal.toLong()
    }

    override fun toString(): String {
        return decorated
    }

    companion object {
        fun from(source: Any? = null, fromSource: Int? = null): Amount {
            return FinancialUtils.getAmount(source, fromSource)
        }
    }
}