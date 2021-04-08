package com.example.soundhubApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class DevicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        val button1: Button = findViewById(R.id.ad_1)
        button1.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }

        val button2: Button = findViewById(R.id.ad_2)
        button2.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
        val button3: Button = findViewById(R.id.ad_3)
        button3.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
        val button4: Button = findViewById(R.id.ad_4)
        button4.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
        val button5: Button = findViewById(R.id.ad_5)
        button5.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
    }
}
