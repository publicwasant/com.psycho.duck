package com.psycho.database.schema.dao.core

import androidx.room.Dao
import androidx.room.Query
import com.psycho.database.schema.dao.BaseDao
import com.psycho.database.schema.entities.core.Account
import com.psycho.database.schema.entities.core.Net

@Dao
abstract class NetDao: BaseDao<Net>() {
    @Query("SELECT * FROM net")
    abstract override fun get(): List<Net>

    @Query("SELECT * FROM net WHERE id=:id")
    abstract override fun getById(id: Int?): Net?

    @Query("SELECT * FROM net WHERE netWorth_id=:netWorthId")
    abstract fun getByNetWorthId(netWorthId: Int?): List<Net>

    @Query("SELECT * FROM net WHERE netWorth_id=:netWorthId AND netType_id=:netTypeId")
    abstract fun getByNetWorthIdAndNetTypeId(netWorthId: Int?, netTypeId: Int?): List<Net>

    @Query("SELECT * FROM net WHERE netWorth_id=:netWorthId AND netStatus_id=:netStatusId")
    abstract fun getByNetWorthIdAndNetStatusId(netWorthId: Int?, netStatusId: Int?): List<Net>

    @Query("SELECT * FROM net WHERE id=(SELECT max(id) FROM net)")
    abstract override fun getLast(): Net?

    @Query("SELECT * FROM net WHERE id=(SELECT max(id) FROM net) AND netWorth_id=:netWorthId")
    abstract fun getLastByNetWorthId(netWorthId: Int?): Net?

    @Query("SELECT * FROM net WHERE id=(SELECT max(id) FROM net) AND netWorth_id=:netWorthId AND netType_id=:netTypeId")
    abstract fun getLastByNetWorthIdAndNetTypeId(netWorthId: Int?, netTypeId: Int?): Net?

    @Query("SELECT * FROM net WHERE id=(SELECT max(id) FROM net) AND netWorth_id=:netWorthId AND netStatus_id=:netStatusId")
    abstract fun getLastByNetWorthIdAndNetStatusId(netWorthId: Int?, netStatusId: Int?): Net?
}