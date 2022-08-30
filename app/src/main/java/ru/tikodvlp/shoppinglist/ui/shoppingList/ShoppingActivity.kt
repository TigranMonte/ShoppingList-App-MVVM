package ru.tikodvlp.shoppinglist.ui.shoppingList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import ru.tikodvlp.shoppinglist.R
import ru.tikodvlp.shoppinglist.data.db.ShoppingDatabase
import ru.tikodvlp.shoppinglist.data.db.entities.ShoppingItem
import ru.tikodvlp.shoppinglist.data.repositories.ShoppingRepository
import ru.tikodvlp.shoppinglist.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        fab.setOnClickListener {
            AddShoppingItemDialog(this, object: AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                     viewModel.upsert(item)
                }
            }).show()
        }
    }
}