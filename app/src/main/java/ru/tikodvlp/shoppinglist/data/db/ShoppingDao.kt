package ru.tikodvlp.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.tikodvlp.shoppinglist.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // onConflict - в случае наличия одинаковых item - заменить
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}