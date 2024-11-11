package com.example.hospital

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HospitalApiService {
    @GET("id/covid19/hospitals")
    suspend fun getHospitals(): List<Hospital>

    companion object {
        private const val BASE_URL = "https://dekontaminasi.com/api/"

        fun create(): HospitalApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(HospitalApiService::class.java)
        }
    }
}
