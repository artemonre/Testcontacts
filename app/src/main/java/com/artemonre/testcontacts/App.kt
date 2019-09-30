package com.artemonre.testcontacts

import android.app.Application
import com.artemonre.testcontacts.interfaces.RequestApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application(){

    private lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        val gson = GsonBuilder()
                .setLenient()
                .create()

//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
                .baseUrl(applicationContext.resources.getString(R.string.url_base))
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        api = retrofit.create(RequestApi::class.java)
    }

    companion object{
        const val MAIN_LOG = "mainLog"
        const val UPDATE_CONTACTS_DELAY_MILLIS = 60 * 1000L

        var contactsOld = false

        lateinit var api: RequestApi
            private set
    }
}