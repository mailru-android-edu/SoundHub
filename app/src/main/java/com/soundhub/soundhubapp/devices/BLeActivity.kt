@file:Suppress("DEPRECATION")

package com.soundhub.soundhubapp.devices

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.searching_device.*
import com.soundhub.soundhubapp.extensions.invisible
import com.soundhub.soundhubapp.extensions.visible
import com.soundhub.soundhubapp.extensions.toast
import com.soundhub.soundhubapp.R

// ЗАПУСКАЕТСЯ ПРИ НАЖАТИИ НА АВАТАРКУ(ВРЕМЕННО)
class BLeActivity: AppCompatActivity() {
    
    companion object {
        const val REQUEST_ENABLE_BT = 1
        const val PERMISSION_REQUEST_COARSE_LOCATION = 1
    }

    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothLeScanner: BluetoothLeScanner

    private val deviceListAdapter = DeviceControlAdapter()

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            if (result != null) {
                deviceListAdapter.addDevice(result.device)
                Log.d("Result: ", "${result.device}")
            } else {
                Log.d("Result: ", "IS NULL")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searching_device)

        initBle()
        initUI()
    }
    private fun initUI() {

        title = getString(R.string.app_name)

        recycler_view_devices.adapter = deviceListAdapter
        recycler_view_devices.layoutManager = LinearLayoutManager(this)
        recycler_view_devices.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        button_discover.setOnClickListener {
            with(button_discover) {
                if (text == getString(R.string.start_scanning)) {
                    deviceListAdapter.clearDevices()
                    text = getString(R.string.stop_scanning)
                    progress_bar.visible()
                    startScanning()
                } else {
                    text = getString(R.string.start_scanning)
                    progress_bar.invisible()
                    stopScanning()
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initBle() {
        bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

        if (!bluetoothAdapter.isEnabled) {
            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSION_REQUEST_COARSE_LOCATION
            )
        }
    }

    private fun startScanning() {
        AsyncTask.execute() {
            bluetoothLeScanner.startScan(leScanCallback)
        }
        Log.d("Scan", "HAS STARTED")
    }

    private fun stopScanning() {
        AsyncTask.execute() {
            bluetoothLeScanner.stopScan(leScanCallback)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
        PERMISSION_REQUEST_COARSE_LOCATION -> {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Location permission granted
            } else {
                toast("Without location access, this app cannot discover beacons.")
            }
        }
        }
    }

    
}
    




