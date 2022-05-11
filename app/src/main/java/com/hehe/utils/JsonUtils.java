package com.hehe.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes.dex */
public class JsonUtils {
    private static Gson mGson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object obj) {
        return mGson.toJson(obj);
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) mGson.fromJson(str, (Class<Object>) cls);
    }

    public static boolean isJsonObject(String str) {
        Object obj;
        try {
            obj = new JSONTokener(str).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
            obj = null;
        }
        return obj instanceof JSONObject;
    }
}
