package com.psycho.database.utility.core

import java.io.Serializable

data class NetType(val string: String, val id: Int): Serializable {
    companion object {
        @JvmField
        val ASSET: NetType = NetType("asset", 0)

        @JvmField
        val DEBT: NetType = NetType("debt", 1)

        fun fromString(string: String?): NetType? {
            return when (string) {
                ASSET.string -> ASSET
                DEBT.string -> DEBT
                else -> null
            }
        }

        fun fromId(id: Int?): NetType? {
            return when (id) {
                ASSET.id -> ASSET
                DEBT.id -> DEBT
                else -> null
            }
        }
    }

    override fun toString(): String {
        return "%s(string=%s, id=%d)".format(javaClass.simpleName, string, id)
    }
}