package com.example.soundhubApp

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        val mainDevice: Button = findViewById(R.id.mainDevice)
        val text_view: TextView = findViewById(R.id.text_view)

        var resId = imgResId
        imageView.setImageResource(imgResId)

        btnDa.setOnClickListener {
            val intent = Intent(this, DevicesActivity::class.java)
            startActivity(intent)
        }
        fabSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        mainDevice.setOnClickListener {
            val bluetooth: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
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
        // Set a SeekBar change listener
        seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                text_view.text = "Volume : $i"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext, "start tracking", Toast.LENGTH_SHORT)
                        .show()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext,"stop tracking", Toast.LENGTH_SHORT).show()
            }
        }
        )
    }
    fun clickSettings(view: View) {}
}
