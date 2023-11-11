package com.psycho.database.schema.dao.core

import androidx.room.Dao
import androidx.room.Query
import com.psycho.database.schema.dao.BaseDao
import com.psycho.database.schema.entities.core.NetWorth

@Dao
abstract class NetWorthDao: BaseDao<NetWorth>() {
    @Query("SELECT * FROM netWorth")
    abstract override fun get(): List<NetWorth>

    @Query("SELECT * FROM netWorth WHERE id=:id")
    abstract override fun getById(id: Int?): NetWorth?

    @Query("SELECT * FROM netWorth WHERE id=(SELECT max(id) FROM netWorth)")
    abstract override fun getLast(): NetWorth?
}