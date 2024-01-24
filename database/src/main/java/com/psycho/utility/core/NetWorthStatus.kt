package com.psycho.utility.core

import java.io.Serializable

class NetWorthStatus(val string: String, val id: Int): Serializable {
    companion object {
        @JvmField
        val WEALTHY: NetWorthStatus = NetWorthStatus("wealthy", 0)

        @JvmField
        val SAFETY: NetWorthStatus = NetWorthStatus("safety", 1)

        @JvmField
        val BALANCE: NetWorthStatus = NetWorthStatus("balance", 2)

        @JvmField
        val RISKY: NetWorthStatus = NetWorthStatus("risky", 3)

        @JvmField
        val POORLY: NetWorthStatus = NetWorthStatus("poorly", 4)

        fun fromString(string: String?): NetWorthStatus? {
            return when (string) {
                WEALTHY.string -> WEALTHY
                SAFETY.string -> SAFETY
                BALANCE.string -> BALANCE
                RISKY.string -> RISKY
                POORLY.string -> POORLY
                else -> null
            }
        }

        fun fromId(id: Int?): NetWorthStatus? {
            return when (id) {
                WEALTHY.id -> WEALTHY
                SAFETY.id -> SAFETY
                BALANCE.id -> BALANCE
                RISKY.id -> RISKY
                POORLY.id -> POORLY
                else -> null
            }
        }
    }

    override fun toString(): String {
        return "%s(string=%s, id=%d)".format(javaClass.simpleName, string, id)
    }
}