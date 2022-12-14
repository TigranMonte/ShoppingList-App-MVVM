package ru.tikodvlp.shoppinglist

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.tikodvlp.shoppinglist.data.db.ShoppingDatabase
import ru.tikodvlp.shoppinglist.data.repositories.ShoppingRepository
import ru.tikodvlp.shoppinglist.ui.shoppingList.ShoppingViewModelFactory

class ShoppingApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton { ShoppingDatabase(instance())}
        bind() from singleton { ShoppingRepository(instance())}
        bind() from provider {
            ShoppingViewModelFactory(instance())
        }
    }
}