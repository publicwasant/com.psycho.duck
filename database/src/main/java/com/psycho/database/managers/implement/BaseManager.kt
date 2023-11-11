package com.psycho.database.managers.implement

import com.psycho.database.schema.dao.BaseDao
import com.psycho.database.managers.di.DatabaseManagerService
import com.psycho.database.managers.interfaces.IBaseManager
import java.sql.SQLClientInfoException

abstract class BaseManager<T>(open val dao: BaseDao<T>): IBaseManager<T> {
    val databaseService = DatabaseManagerService.provider.databaseManager

    override fun get(): List<T> {
        return dao.get()
    }

    override fun get(id: Int?): T? {
        return dao.getById(id)
    }

    override fun getLast(): T? {
        return dao.getLast()
    }

    override fun delete(objList: List<T>) {
        dao.delete(objList)
    }

    override fun delete(obj: T) {
        dao.delete(obj)
    }

    override fun update(objList: List<T>) {
        dao.update(objList)
    }

    override fun update(obj: T) {
        dao.update(obj)
    }

    override fun insert(objList: List<T>) {
        dao.insert(objList)
    }

    override fun insert(obj: T) {
        dao.insert(obj)
    }

    open fun insertOrUpdate(objList: List<T>) {
        try {
            insert(objList)
        } catch (e: SQLClientInfoException) {
            update(objList)
        }
    }

    open fun insertOrUpdate(obj: T) {
        try {
            insert(obj)
        } catch (e: SQLClientInfoException) {
            update(obj)
        }
    }
}

