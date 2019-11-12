package com.fkq.project.custom;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/12/5.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> tClass;

    public JsonCallBack(Type type) {
        this.type = type;
    }

    public JsonCallBack(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) {
            data = gson.fromJson(jsonReader, type);
        }
        if (tClass != null) {
            data = gson.fromJson(jsonReader, tClass);
        }
        return data;
    }
}
