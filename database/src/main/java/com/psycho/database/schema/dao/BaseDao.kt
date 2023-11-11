package com.psycho.database.schema.dao

import android.util.Log
import android.util.Log.INFO
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.psycho.database.utility.Constants.DATABASE_MANAGER_SDK_TAG
import com.psycho.database.utility.print.Cell

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