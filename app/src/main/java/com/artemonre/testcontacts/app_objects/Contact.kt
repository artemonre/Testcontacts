package com.artemonre.testcontacts.app_objects

data class Contact(var id: String,
                   var name: String,
                   var phone: String,
                   var height: Float,
                   var biography: String,
                   var temperament: Temperament,
                   var educationPeriod: EducationPeriod) {

}

enum class Temperament(val title: String){
    MELANCHOLIC("melancholic"), PHLEGMATIC("phlegmatic"), SANGUINE("sanguine"), CHOLERIC("choleric")
}

object EducationPeriod{

    var start = ""
    var end = ""
}