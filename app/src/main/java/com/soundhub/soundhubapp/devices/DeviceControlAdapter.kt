package com.soundhub.soundhubapp.devices


import android.bluetooth.BluetoothDevice
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soundhub.soundhubapp.extensions.inflate
import com.soundhub.soundhubapp.R
import kotlinx.android.synthetic.main.adapter_searching_devices.view.*


class DeviceControlAdapter : RecyclerView.Adapter<DeviceControlAdapter.ViewHolder>() {
    
    private val devices = ArrayList<BluetoothDevice>()
    
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) = ViewHolder(
        container.inflate(R.layout.adapter_searching_devices)
    )
    
    override fun getItemCount() = devices.size
    
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(devices[position])
    }
    
    fun addDevice(device: BluetoothDevice) {
        devices.add(device)
        notifyItemInserted(itemCount)
    }
    
    fun clearDevices() {
        devices.clear()
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(device: BluetoothDevice) {
            view.text_view_device_name.text = device.name ?: device.address
            Log.d("Device name: ", "${device.name}")
            Log.d("Device address: ", device.address)
        }
    }
    
}