package org.ak.nfn

import android.app.Application
import org.ak.nfn.data.db.AppDatabase
import org.ak.nfn.data.network.MyApi
import org.ak.nfn.data.network.NetworkConnectionInterceptor
import org.ak.nfn.data.repository.UserRepository
import org.ak.nfn.ui.auth.AuthViewModelFactory

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication: Application(), KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}