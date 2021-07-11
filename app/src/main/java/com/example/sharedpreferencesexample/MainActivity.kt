package com.example.sharedpreferencesexample

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edAge: EditText
    private lateinit var cbIsAdult: CheckBox
    private lateinit var btSave: Button
    private lateinit var btLoad: Button
    private lateinit var userPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edName = findViewById(R.id.ed_name)
        edEmail = findViewById(R.id.ed_email)
        edAge = findViewById(R.id.ed_age)
        cbIsAdult = findViewById(R.id.cb_isAdult)
        btSave = findViewById(R.id.bt_save)
        btLoad = findViewById(R.id.bt_load)
//                Code from Philipp Lackner - Youtube Link
        userPreferences = getSharedPreferences("User Preferences", MODE_PRIVATE)
//        Editor is created to put data into a Shared Preference.
        editor = userPreferences.edit()

        btSave.setOnClickListener {
            editor.apply {
//                Getting data from user interface elements from application.
                val name = edName.text.toString()
                val email = edEmail.text.toString()
                val age = edAge.text.toString().toInt()
                val isAdult = cbIsAdult.isChecked
//                Putting data into the Shared Preference using Editor.
                putString("name", name)
                putString("email", email)
                putInt("age", age)
                putBoolean("isAdult", isAdult)
                apply() // With the help of apply() you are able to save data into a Shared Preference asynchronously. This method doesn't effects our UI.
//                commit() // With the help of commit() you are able to save data into a Shared Preference synchronously. This method effects our UI.
            }
        }

        btLoad.setOnClickListener {
//            Getting data from the Shared Preference.
            val name = userPreferences.getString("name", "John Doe")
            val email = userPreferences.getString("email", "abc@gmail.com")
            val age = userPreferences.getInt("age", 1)
            val isAdult = userPreferences.getBoolean("isAdult", false)
//           Putting data from Shared Preference into the user interface elements of the application.
            edName.setText(name)
            edEmail.setText(email)
            edAge.setText(age.toString())
            cbIsAdult.isChecked = isAdult
        }
    }
}