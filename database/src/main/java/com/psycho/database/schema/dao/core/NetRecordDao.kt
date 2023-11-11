package com.psycho.database.schema.dao.core

import androidx.room.Dao
import androidx.room.Query
import com.psycho.database.schema.dao.BaseDao
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetRecord

@Dao
abstract class NetRecordDao: BaseDao<NetRecord>() {
    @Query("SELECT * FROM netRecord")
    abstract override fun get(): List<NetRecord>

    @Query("SELECT * FROM netRecord WHERE id=:id")
    abstract override fun getById(id: Int?): NetRecord?

    @Query("SELECT * FROM netRecord WHERE net_id=:netId")
    abstract fun getByNetId(netId: Int?): List<NetRecord>

    @Query("SELECT * FROM netRecord WHERE net_id IN (:netIdList)")
    abstract fun getByNetId(netIdList: List<Int>): List<NetRecord>

    @Query("SELECT * FROM netRecord WHERE id=(SELECT max(id) FROM netRecord)")
    abstract override fun getLast(): NetRecord?
}