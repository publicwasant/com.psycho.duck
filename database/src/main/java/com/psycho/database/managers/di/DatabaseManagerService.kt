package com.psycho.database.managers.di

import org.koin.core.component.inject

object DatabaseManagerService : DatabaseManagerKoinComponent {
    @JvmStatic
    val provider: IDatabaseManagerProvider by inject()

    override fun toString(): String {
        return this.javaClass.simpleName
    }
}