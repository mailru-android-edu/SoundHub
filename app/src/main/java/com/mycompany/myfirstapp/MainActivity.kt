package com.mycompany.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.Avatar)
        val imgResId = R.drawable.face
        var resId = imgResId
        imageView.setImageResource(imgResId)

        btn_da.setOnClickListener {
            val intent = Intent(this, DevicesActivity::class.java)
            startActivity(intent)
        }
        fab_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        // Set a SeekBar change listener
        seekBarVolume.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar:SeekBar,
                i:Int,
                b:Boolean
            ) {
                // Display the current progress of SeekBar
                text_view.text = "Volume : $i"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                Toast.makeText(applicationContext, "start tracking", Toast.LENGTH_SHORT).show()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
                Toast.makeText(applicationContext, "stop tracking", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun clickSettings(view: View) {}
}
