package com.artemonre.testcontacts.model

import android.content.Context
import com.artemonre.testcontacts.interfaces.Callback
import com.artemonre.testcontacts.interfaces.MainContract

class Model(var context: Context) : MainContract.Model{

    private lateinit var repository: Repository

    init {
        repository = Repository()
    }

    override fun downloadContacts(callback: Callback) {
        repository.downloadContacts(callback)
    }
}