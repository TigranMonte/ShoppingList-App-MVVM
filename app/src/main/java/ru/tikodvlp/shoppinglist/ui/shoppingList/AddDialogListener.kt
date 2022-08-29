package ru.tikodvlp.shoppinglist.ui.shoppingList

import ru.tikodvlp.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}