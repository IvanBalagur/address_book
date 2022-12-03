package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar


import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        title = "Телефонная книга"



        val dbHelper = DBHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // заполнить list из базы
        var data_once = dbHelper.getAll()
        val  editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        list.addAll(data_once)
        editText.doAfterTextChanged { it ->
            var data = data_once.filter  { item -> item.last_name.contains(it.toString(), true) || item.first_name.contains(it.toString(), true) }
            list.clear()
            list.addAll(data)
            adapter.notifyDataSetChanged()
        }
        adapter = RecyclerAdapter(list) {
            val id = list[it].id
            val intent = Intent(this@MainActivity, Contact_window::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, Add_contact_menu::class.java)
            startActivity(intent)
        }

    }
}


