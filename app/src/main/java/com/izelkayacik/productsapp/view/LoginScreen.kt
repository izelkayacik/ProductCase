package com.izelkayacik.productsapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.izelkayacik.productsapp.R

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_screen)

        val girisYap = findViewById<Button>(R.id.girisYap)

        girisYap.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

}