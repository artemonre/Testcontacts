package com.artemonre.testcontacts.model

import android.os.Handler
import com.artemonre.testcontacts.app_objects.Contact
import com.artemonre.testcontacts.app_objects.EducationPeriod
import com.artemonre.testcontacts.app_objects.Temperament
import com.artemonre.testcontacts.interfaces.Callback

class Repository{

    private lateinit var contacts: List<Contact>

    fun downloadContacts(callback: Callback) {
        contacts = ArrayList()

        val handler = Handler()

        (contacts as ArrayList).add(Contact("0", "Bobby Bob", "+7 (987) 654-3213", 180.0f, "Biography 0", Temperament.CHOLERIC, EducationPeriod))
        (contacts as ArrayList).add(Contact("1", "Harry Harrison", "+7 (987) 654-3213", 185f, "Biography 1", Temperament.MELANCHOLIC, EducationPeriod))
        (contacts as ArrayList).add(Contact("2", "Will Wiliamson", "+7 (987) 654-3213", 190.2f, "Biography 2", Temperament.MELANCHOLIC, EducationPeriod))
        (contacts as ArrayList).add(Contact("3", "Marty March", "+7 (987) 654-3213", 166.0f, "Biography 3", Temperament.SANGUINE, EducationPeriod))

        handler.postDelayed({
            callback.callback(contacts)
        }, 3000)
    }
}