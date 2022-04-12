package com.adl.belajarsharedpreferencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG).show()

        val sharedPreference = getSharedPreferences("login_setting",Context.MODE_PRIVATE)

        val username = sharedPreference.getString("username","")
        val isChecked = sharedPreference.getBoolean("checkbox",false)

        txtUsername.setText(username)

        checkBox.setChecked(isChecked)

        btnLogin.setOnClickListener({
            var editor = sharedPreference.edit()
            if (checkBox.isChecked){
                //var editor = sharedPreference.edit()
                editor.putString("username", txtUsername.text.toString())
                editor.putBoolean("checkbox", true)
                editor.commit()
            }else{
                editor.clear()
                editor.commit()
            }
        })

    }
}