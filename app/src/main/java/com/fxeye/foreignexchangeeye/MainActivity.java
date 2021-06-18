package com.fxeye.foreignexchangeeye;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fxeye.foreignexchangeeye.util_tool.https_controller.NetworkConnectionController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "handleMessage what " + msg.what + " arg1 " + msg.arg1);
                String str = msg.obj instanceof String ? msg.obj.toString() : null;
//                Log.i(TAG, "handleMessage " + msg.obj);
                try {
                    JSONObject json = new JSONObject(str);
                    JSONArray result;
                    switch (msg.what) {
                        case 72:
                            JSONObject content = json.getJSONObject("Content");
                            result = content.getJSONArray("result");
                            Log.i(TAG, "result what: " + msg.what + " length " + result.length() + " " + result.get(0).toString());
                            break;
                        case 75:
                            JSONObject data = json.getJSONObject("Data");
                            JSONObject traderRanking = data.getJSONObject("traderranking");
                            result = traderRanking.getJSONArray("result");
                            Log.i(TAG, "result what: " + msg.what + " length " + result.length() + " " + result.get(0).toString());
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        String obj = "lite";
//        NetworkConnectionController.JiaoYiShang_Search(obj, this.mHandler, 1);
//        NetworkConnectionController.GetSearch_Find(this.mHandler, 1);
        NetworkConnectionController.GetTianYan_Zhida_Information(this.mHandler, 72);
        NetworkConnectionController.api_general_advertise(this.mHandler, 75);
    }
}