package com.psycho.database.utility.core

import java.io.Serializable

data class NetStatus(val string: String, val id: Int): Serializable {
    companion object {
        @JvmField
        val ACTIVE = NetStatus("active", 0)

        @JvmField
        val CLOSED = NetStatus("closed", 1)

        fun fromString(string: String?): NetStatus? {
            return when (string) {
                ACTIVE.string -> ACTIVE
                CLOSED.string -> CLOSED
                else -> null
            }
        }

        fun fromId(id: Int?): NetStatus? {
            return when (id) {
                ACTIVE.id -> ACTIVE
                CLOSED.id -> CLOSED
                else -> null
            }
        }
    }

    override fun toString(): String {
        return "%s(string=%s, id=%d)".format(javaClass.simpleName, string, id)
    }
}