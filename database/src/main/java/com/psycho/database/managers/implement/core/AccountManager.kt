package com.psycho.database.managers.implement.core

import com.psycho.database.AppDatabase
import com.psycho.database.schema.entities.core.Account
import com.psycho.database.managers.implement.BaseManager
import com.psycho.database.managers.interfaces.core.IAccountManager
import com.psycho.database.schema.dao.BaseDao

class AccountManager(appDatabase: AppDatabase): IAccountManager<Account>, BaseManager<Account>(appDatabase.getAccountDao()) {
    override val dao: BaseDao<Account> = appDatabase.getAccountDao()
}