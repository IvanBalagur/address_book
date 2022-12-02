package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class Change_contact: AppCompatActivity() {


    private val list = mutableListOf<Todo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_contact)
        title = "Изменение контакта"
        val dbHelper = DBHelper(this)
        val button = findViewById<Button>(R.id.button)

        val editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        val editTextName = findViewById<EditText>(R.id.editTextTextMultiLine2)

        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)


        val intent = intent
        val id = intent.getLongExtra("Id",0)
        val allData = dbHelper.getById(id)

        editText.setText(allData?.last_name)
        editTextName.setText(allData?.first_name)

        editTextPhone.setText(allData?.telephone)

        val buttonChange = findViewById<Button>(R.id.button3)
        buttonChange.setOnClickListener {
            val last_name = editText.text.toString()
            val first_name = editTextName.text.toString()

            val phone = editTextPhone.text.toString()
            //val cool = dbHelper.update(id.toString().toLong(),title,name,phone)
            list.add(Todo(id.toString().toLong(),last_name,first_name,phone))
            val intent_change = Intent(this@Change_contact, MainActivity::class.java)
            startActivity(intent_change)
        }

        button.setOnClickListener {
            val intent_back = Intent(this@Change_contact, MainActivity::class.java)
            startActivity(intent_back)
        }

    }
}