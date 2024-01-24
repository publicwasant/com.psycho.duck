package com.psycho.database.schema.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
abstract class BaseDao <in T> {
    abstract fun get(): List<@UnsafeVariance T>

    abstract fun getById(id: Int?): @UnsafeVariance T?

    abstract fun getLast(): @UnsafeVariance T?

    @Insert(onConflict=OnConflictStrategy.ABORT)
    abstract fun insert(obj: T)

    @Insert(onConflict=OnConflictStrategy.ABORT)
    abstract fun insert(objList: List<T>)

    @Update
    abstract fun update(obj: T)

    @Update
    abstract fun update(objList: List<T>)

    @Delete
    abstract fun delete(obj: T)

    @Delete
    abstract fun delete(objList: List<T>)
}