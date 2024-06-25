package com.weather.weather

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.weather.weather.databinding.ActivityWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Weather : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val API_KEY = "9857663abe584adf93670010241402"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val q = intent.getStringExtra("data")
        val call = ApiClient.apiService.getData(API_KEY , q.toString())
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse> ,
                response: Response<WeatherResponse> ,
            ) {


                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    weatherResponse?.let {
                        Log.d("Weather" , "Location name is ${it.location.name}")
                        Log.d("Weather" , "Country name is ${it.location.country}")
                        Log.d("Weather" , "Current temp is ${it.current.temp_c}")
                        Log.d("Weather" , "Current temp is https:${it.current.condition.icon}")
                        Log.d("Weather" , "Condition is name is ${it.current.condition.text}")
                        binding.txtDayDate.text = "Date || Time ${it.location.localtime}"
                        binding.txtTemp.text = it.current.temp_c.toString()
                        binding.txtCity.text = it.location.name.toString()
                        binding.txtHumidity.text = it.current.humidity.toString()
                        binding.txtWind.text = it.current.wind_mph.toString()
                        binding.txtWindSpeed.text = it.current.wind_dir
                        Glide.with(this@Weather).load("https:"+it.current.condition.icon)
                            .into(binding.imgSymbol)

                    }
                } else {
                    Log.e("Weather" , "Failed to get data: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse> , t: Throwable) {
                Log.e("Weather" , "Error fetching data" , t)
                Toast.makeText(
                    this@Weather ,
                    "NOT DONE !! CHECK AGAIN" ,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
