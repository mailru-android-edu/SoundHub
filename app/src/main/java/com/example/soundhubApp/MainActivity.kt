package com.example.soundhubApp

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val requestEnableBt = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, MainActivity::class.java)
        val connectButton: Button = findViewById(R.id.connectButton)
        connectButton.setOnClickListener {
            var bluetooth: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
            var status: String
            if (bluetooth != null) {
                if (bluetooth.isEnabled) {
                    val myDeviceAddress: String = bluetooth.getAddress()
                    val myDeviceName: String = bluetooth.name
                    status = "$myDeviceName : $myDeviceAddress"
                } else {
                    // Предложим включить
                    status = "Bluetooth выключен"
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtIntent, requestEnableBt)
                }
                Toast.makeText(this, status, Toast.LENGTH_LONG).show()
            }
        }
    }
}
