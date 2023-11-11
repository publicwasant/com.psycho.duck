package com.psycho.database.managers.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface DatabaseManagerKoinComponent : KoinComponent {
    override fun getKoin(): Koin = DatabaseManagerSDK.koin
}