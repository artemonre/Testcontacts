package com.artemonre.testcontacts.interfaces

import retrofit2.Call
import retrofit2.http.*

interface DBRequestApi {

    @GET
    fun getToken(@Url url: String,
                 @Query("action")action: String): Call<String>

}