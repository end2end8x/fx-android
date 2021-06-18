package com.fxeye.foreignexchangeeye;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fxeye.foreignexchangeeye.controller.fristpage.TraderController;
import com.fxeye.foreignexchangeeye.util_tool.https_controller.NetworkConnectionController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;

    public static final String TAG = "MainActivity";

    public static int index = 1;
    public static int size = 20;
    public static String lc = "vi";
    public static String cc = "vn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "handleMessage what " + msg.what + " arg1 " + msg.arg1);
                String str = msg.obj instanceof String ? msg.obj.toString() : null;
                try {
                    JSONObject json = new JSONObject(str);
                    JSONArray result;
                    JSONObject trader;
                    int index = 1;
                    int size = 20;
                    switch (msg.what) {
                        case 72:
                            JSONObject content = json.getJSONObject("Content");
                            result = content.getJSONArray("result");
                            trader = (JSONObject) result.get(0);
                            String code = trader.getString("code");
                            Log.i(TAG, "result what: " + msg.what + " length " + result.length() + " " + code + " " + trader);
                            break;
                        case 75:
                            JSONObject data = json.getJSONObject("Data");
                            JSONObject traderRanking = data.getJSONObject("traderranking");
                            result = traderRanking.getJSONArray("result");
                            trader = (JSONObject) result.get(0);
                            String traderCode = trader.getString("traderCode");
                            Log.i(TAG, "result what: " + msg.what + " length " + result.length() + " " + traderCode + " " + trader);
                            TraderController.GetTraderSurveys(traderCode, index, size, cc, lc, mHandler, 96);
                            break;

                        case 96:
                            Log.i(TAG, "handleMessage GetTraderSurveys " + msg.obj);
                            break;

                        default:
                            Log.i(TAG, "handleMessage " + msg.obj);
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
//        TraderController.GetTraderNewsList();
    }
}