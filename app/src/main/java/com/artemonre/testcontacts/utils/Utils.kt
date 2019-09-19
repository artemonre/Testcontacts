package com.artemonre.testcontacts.utils

import org.json.JSONObject

import java.util.HashMap
import java.util.regex.Pattern

object Utils {

    fun isEmpty(o: Any?): Boolean {
        return if (o is String) isEmpty(o as String?) else o == null

    }

    fun isEmpty(s: String?): Boolean {

        return s == null || s.length == 0
    }

    fun isEmpty(i: Int): Boolean {
        return i <= 0
    }

    fun isEmpty(l: Long): Boolean {
        return l <= 0
    }

    fun isEmpty(array: Array<Any>?): Boolean {
        return array == null || array.size <= 0
    }

    fun isEmpty(hashMap: HashMap<*, *>?): Boolean {
        return hashMap == null || hashMap.isEmpty()
    }

    fun isEmpty(list: List<*>?): Boolean {
        return list == null || list.isEmpty()
    }

    fun isEmail(email: String): Boolean {
        val p = Pattern.compile(".+@.+\\..+")
        val m = p.matcher(email)
        return m.matches()
    }

    fun isPhoneNumber(phone: String): Boolean {
        return phone.length >= 10 && phone.length <= 12
    }

    fun isGoodPasword(password: String): Boolean {
        return password.length > 7/*|| isStrong(password)*/
    }

    fun getNumberWithLeadingZero(number: Int): String {
        return if (number < 10)
            "0$number"
        else
            "" + number
    }

    fun putIfExists(hashMap: HashMap<String, Any>, fieldName: String, fieldValue: Any): HashMap<String, Any> {
        if (!Utils.isEmpty(fieldValue)) {
            hashMap[fieldName] = fieldValue
        }
        return hashMap
    }

    private fun getJsonStringFromHashMap(hashMap: HashMap<String, Boolean>): String {
        if (!Utils.isEmpty(hashMap)) {
            val jsonObject = JSONObject(hashMap)
            return jsonObject.toString()
        } else
            return ""
    }

    fun getStringFromArray(stringArray: Array<String>): String {
        val stringBuilder = StringBuilder()
        for (i in stringArray.indices) {
            if (i > 0)
                stringBuilder.append(",")
            stringBuilder.append(stringArray[i])
        }
        return stringBuilder.toString()
    }

}