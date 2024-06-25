package com.weather.weather

data class WeatherResponse(
    val location: Location ,
    val current: Current ,
)

data class Location(
    val name: String ,
    val country: String ,
    val localtime: String ,
)

data class Current(
    val temp_c: Double ,
    val condition: Condition ,
    val wind_mph: Double ,
    val humidity: Double ,
    val wind_dir: String ,
)

data class Condition(
    val text: String ,
    val icon: String ,
)
