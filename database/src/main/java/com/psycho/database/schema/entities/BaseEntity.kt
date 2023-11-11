package com.psycho.database.schema.entities

abstract class BaseEntity {
    abstract var id: Int

    abstract fun from(baseEntity: BaseEntity)
    abstract fun copy(): BaseEntity

    override fun toString(): String {
        return "%s(id=%d)".format(this.javaClass.simpleName, id)
    }
}