package com.artemonre.testcontacts.http

import com.artemonre.testcontacts.App
import com.artemonre.testcontacts.App.Companion.MAIN_LOG
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.utils.CommonException
import com.artemonre.testcontacts.utils.MyLog
import retrofit2.Call
import retrofit2.Response

class RequestApiHelper {

    fun downloadContacts(url: String, callback: Callback){
        App.api.getContacts(url).enqueue(object : retrofit2.Callback<List<ContactGson>> {
            override fun onResponse(call: Call<List<ContactGson>>, response: Response<List<ContactGson>>) {
                if (response.body() != null)
                    callback.callback(Pair(response.body(), url))
            }

            override fun onFailure(call: Call<List<ContactGson>>, t: Throwable) {
                callback.callback(t)
            }
        })
    }
}