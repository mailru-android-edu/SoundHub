package com.soundhub.soundhubapp

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.soundhub.soundhubapp.devices.BLeActivity

class MainActivity : AppCompatActivity() {
    private val requestEnableBt = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView: ImageView = findViewById(R.id.Avatar)
        val imgResId = R.drawable.face
        val seekBarVolume: SeekBar = findViewById(R.id.seekBarVolume)
        val btnDa: Button = findViewById(R.id.btnDa)
        val fabSettings: FloatingActionButton = findViewById(R.id.fabSettings)
        val textViewMain: TextView = findViewById(R.id.text_view)
        var resId = imgResId
        imageView.setImageResource(imgResId)
        val mainDevice: Button = findViewById(R.id.mainDevice)
        mainDevice.setOnClickListener {
            val bluetooth: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
            var status: String
            if (bluetooth != null) {
                // Проверим включен ли BT
                if (bluetooth.isEnabled) {
                    val myDeviceAddress: String = bluetooth.address
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
        btnDa.setOnClickListener {
            val intent = Intent(this, DevicesActivity::class.java)
            startActivity(intent)
        }
        fabSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        // при нажатии на аватарку, открывается поиск устройств
        // потом переделаю, когда получится сделать плавающие круги на главном экране
        imageView.setOnClickListener{
            val intent = Intent(this, BLeActivity::class.java)
            startActivity(intent)
        }
        // Set a SeekBar change listener
        seekBarVolume.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    textViewMain.text = "Volume : $i"
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    Toast.makeText(applicationContext, "start tracking", Toast.LENGTH_SHORT)
                        .show()
                }
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    Toast.makeText(applicationContext, "stop tracking", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }
    
    
    
}
