package com.artemonre.testcontacts.http

data class ContactGson(val id: String,
                       val name: String,
                       val phone: String,
                       val height: Float,
                       val biography: String,
                       val temperament: String,
                       val educationPeriod: EducationPeriodGson){

}

data class EducationPeriodGson(var start: String,
                               var end: String){
}