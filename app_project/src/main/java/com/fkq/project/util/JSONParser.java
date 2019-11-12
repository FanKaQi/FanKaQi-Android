package com.fkq.project.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 解析对象基类
 *
 * Created by yuzhenbei on 2015/7/25.
 */
public class JSONParser {

    /**
     * 从JSON字符串中反序列化T对象
     *
     * @param pJsonStr JSON字符串
     * @param pClass 对象的Class
     * @param <T> 将要反序列化成的T对象
     * @return T对象
     */
    public static <T> T parserTFromJson(String pJsonStr, final Class<T> pClass){
        T t;
        try{
            if (!TextUtils.isEmpty(pJsonStr)){
                Gson gson = new Gson();
                t = gson.fromJson(pJsonStr, pClass);
            }else {
                t = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            t = null;
        }
        return t;
    }

    /**
     * 序列化T对象为JSON字符串
     *
     * @param pT T对象
     * @param <T> 将要序列化的T对象
     * @return JSON字符串
     */
    public static <T> String parserTToJson(T pT){
        String josnStr;
        try{
            if (pT != null) {
                Gson gson = new Gson();
                josnStr = gson.toJson(pT);
            }else {
                josnStr = "";
            }
        }catch (Exception e){
            e.printStackTrace();
            josnStr = "";
        }
        return josnStr;
    }

    /**
     * 从JSON字符串中反序列化List<T>集合对象
     *
     * @param pJsonData JSON字符串
     * @param pClass T对象的Class
     * @param <T> 对象类型
     * @return List<T>集合对象
     */
    public static <T> List<T> parserListTFromJson(final String pJsonData, final Class<T> pClass){
        List<T> tList;
        try{
            if (!TextUtils.isEmpty(pJsonData)){
                Gson gson = new Gson();
                tList = gson.fromJson(pJsonData, new ListOfJson<T>(pClass));
            }else {
                tList = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            tList = null;
        }
        return tList;
    }

    /**
     * 序列化List<T>集合对象为JSON字符串
     *
     * @param pListT List<T>集合对象
     * @param <T> 对象类型
     * @return JSON字符串
     */
    public static <T> String parserListTToJson(List<T> pListT){
        String tosnStr;
        try{
            if (pListT != null) {
                Gson gson = new Gson();
                tosnStr = gson.toJson(pListT);
            }else {
                tosnStr = "";
            }
        }catch (Exception e){
            e.printStackTrace();
            tosnStr = "";
        }
        return tosnStr;
    }


    /**
     * Json 转成 Map<>
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> getMapForJson(String jsonStr){
        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(jsonStr);
            Iterator<String> keyIter= jsonObject.keys();
            String key;
            Object value ;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Json 转成 List<Map<>>
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> getlistForJson(String jsonStr){
        List<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            JSONObject jsonObj ;
            list = new ArrayList<Map<String,Object>>();
            for(int i = 0 ; i < jsonArray.length() ; i ++){
                jsonObj = (JSONObject)jsonArray.get(i);
                list.add(getMapForJson(jsonObj.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}