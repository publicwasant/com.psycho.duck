package com.psycho.database.schema.dao.core

import androidx.room.Dao
import androidx.room.Query
import com.psycho.database.schema.dao.BaseDao
import com.psycho.database.schema.entities.core.Account
import com.psycho.database.schema.entities.core.NetWorth

@Dao
abstract class AccountDao: BaseDao<Account>() {
    @Query("SELECT * FROM account")
    abstract override fun get(): List<Account>

    @Query("SELECT * FROM account WHERE id=:id")
    abstract override fun getById(id: Int?): Account?

    @Query("SELECT * FROM account WHERE id=(SELECT max(id) FROM account)")
    abstract override fun getLast(): Account?
}