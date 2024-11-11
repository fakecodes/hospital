package com.example.hospital

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private lateinit var hospitalAdapter: HospitalAdapter
    private lateinit var recyclerView: RecyclerView
    private val hospitalApiService = HospitalApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi recyclerView sebagai properti kelas
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menambahkan Divider
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!) // Use custom divider drawable
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Memanggil fungsi untuk mengambil data rumah sakit
        fetchHospitalData()
    }

    private fun fetchHospitalData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val hospitals = hospitalApiService.getHospitals()
                withContext(Dispatchers.Main) {
                    // Inisialisasi adapter dengan data yang diperoleh
                    hospitalAdapter = HospitalAdapter(hospitals) { hospital ->
                        openMapActivity(hospital.province)
                    }
                    recyclerView.adapter = hospitalAdapter
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openMapActivity(province: String) {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("PROVINCE_NAME", province)
        startActivity(intent)
    }
}
