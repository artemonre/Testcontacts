package com.artemonre.testcontacts.interfaces

import com.artemonre.testcontacts.http.ContactGson
import retrofit2.Call
import retrofit2.http.*

interface RequestApi {

    @GET
    fun getContacts(@Url url: String): Call<List<ContactGson>>
}