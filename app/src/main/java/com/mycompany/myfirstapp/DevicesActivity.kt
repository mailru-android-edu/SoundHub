package com.mycompany.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class DevicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        val Button1: Button = findViewById(R.id.ad_1)
        Button1.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }

        val Button2: Button = findViewById(R.id.ad_2)
        Button2.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
        val Button3: Button = findViewById(R.id.ad_3)
        Button3.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
        val Button4: Button = findViewById(R.id.ad_4)
        Button4.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
        val Button5: Button = findViewById(R.id.ad_5)
        Button5.setOnClickListener {
            Toast.makeText(this, "Идёт подключение", Toast.LENGTH_SHORT).show()
        }
    }
}
