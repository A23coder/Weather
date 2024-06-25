package com.weather.weather

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.weather.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetWeather.setOnClickListener {
            val condition = binding.edtGetCity.text.isNotEmpty()
            if (condition) {
                val intent = Intent(this , Weather::class.java)
                intent.putExtra("data" , binding.edtGetCity.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this , "Please insert city name.." , Toast.LENGTH_SHORT).show()
            }
        }
    }
}