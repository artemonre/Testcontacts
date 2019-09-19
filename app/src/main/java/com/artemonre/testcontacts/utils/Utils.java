package com.artemonre.testcontacts.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils{

    public static boolean isEmpty(Object o){
        if(o instanceof String)
            return isEmpty((String)o);

        return o == null;
    }

    public static boolean isEmpty(String s){

        return (s == null || s.length() == 0);
    }

    public static boolean isEmpty(int i){
        return (i <= 0);
    }

    public static boolean isEmpty(long l){
        return (l <= 0);
    }

    public static boolean isEmpty(Object[] array){
        return array == null || array.length <= 0;
    }

    public static boolean isEmpty(HashMap hashMap){
        return hashMap == null || hashMap.isEmpty();
    }

    public static boolean isEmpty(List list){
        return list == null || list.isEmpty();
    }

    public static boolean isEmail(String email){
        Pattern p = Pattern.compile(".+@.+\\..+");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPhoneNumber(String phone){
        return (phone.length() >= 10 && phone.length() <= 12);
    }

    public static boolean isGoodPasword(String password){
        return password.length() > 7/*|| isStrong(password)*/;
    }

    public static String getNumberWithLeadingZero(int number){
        if(number < 10)
            return "0" + number;
        else
            return "" + number;
    }

    public static HashMap<String, Object> putIfExists(HashMap<String, Object> hashMap, String fieldName, Object fieldValue){
        if(!Utils.isEmpty(fieldValue)){
            hashMap.put(fieldName, fieldValue);
        }
        return hashMap;
    }

    private static String getJsonStringFromHashMap(HashMap<String, Boolean> hashMap){
        if(!Utils.isEmpty(hashMap)){
            JSONObject jsonObject = new JSONObject(hashMap);
            return jsonObject.toString();
        }else
            return "";
    }

    public static String getStringFromArray(String[] stringArray){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < stringArray.length; i++){
            if(i > 0)
                stringBuilder.append(",");
            stringBuilder.append(stringArray[i]);
        }
        return stringBuilder.toString();
    }

}
