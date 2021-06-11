package com.fxeye.foreignexchangeeye.util_tool.analysis;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GsonUtil {
    private static Gson mGson = new Gson();

    public static <T> T stringToObject(String str, Class<T> cls) {
        return mGson.fromJson(str, cls);
    }

    public static <T> String objectToString(T t) {
        return mGson.toJson((Object) t);
    }

    public static <T> List<T> stringToList(String str, Class<T> cls) {
        try {
            Gson gson = new Gson();
            ArrayList arrayList = new ArrayList();
            Iterator<JsonElement> it = new JsonParser().parse(str).getAsJsonArray().iterator();
            while (it.hasNext()) {
                arrayList.add(gson.fromJson(it.next(), cls));
            }
            return arrayList;
        } catch (Exception e) {
            Log.i("Gson解析", e.toString());
            return null;
        }
    }
}
