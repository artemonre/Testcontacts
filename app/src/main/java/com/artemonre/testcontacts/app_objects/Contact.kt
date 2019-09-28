package com.artemonre.testcontacts.app_objects

import com.artemonre.testcontacts.utils.CommonException

data class Contact(var id: String,
                   var name: String,
                   var phone: String,
                   var height: Float,
                   var biography: String,
                   var temperament: Temperament,
                   var educationPeriod: EducationPeriod) {

}

enum class Temperament(val title: String){

    MELANCHOLIC("melancholic"), PHLEGMATIC("phlegmatic"), SANGUINE("sanguine"), CHOLERIC("choleric");

    @Throws(CommonException::class)
    fun getTemperament(title: String): Temperament{
        for(temperament in values()){
            if(title.equals(temperament.title))
                return temperament
        }

        throw CommonException()
    }
}

data class EducationPeriod(var start: String,
                           var end: String){
}