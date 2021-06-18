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
    public static String lc = "en";
    public static String cc = "us";

    public static String categorycode = "zx_trader_total";

    public final static int GetTianYan_Zhida_Information = 72;
    public final static int api_general_advertise = 75;
    public final static int GetTraderSurveys = 96;
    public final static int GetSpecifiedTrader = 1;
    public final static int GetTraderNewsList = 43;

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
                        case GetTianYan_Zhida_Information:
                            JSONObject content = json.getJSONObject("Content");
                            result = content.getJSONArray("result");
                            trader = (JSONObject) result.get(0);
                            String code = trader.getString("code");
                            Log.i(TAG, "GetTianYan_Zhida_Information " + msg.what + " length " + result.length() + " " + code + " " + trader);
                            break;
                        case api_general_advertise:
                            JSONObject data = json.getJSONObject("Data");
                            JSONObject traderRanking = data.getJSONObject("traderranking");
                            result = traderRanking.getJSONArray("result");
                            trader = (JSONObject) result.get(0);
                            String traderCode = trader.getString("traderCode");
                            Log.i(TAG, "api_general_advertise " + msg.what + " length " + result.length() + " " + traderCode + " " + trader);

                            TraderController.GetTraderSurveys(traderCode, index, size, cc, lc, mHandler, GetTraderSurveys);

                            TraderController.GetSpecifiedTrader(traderCode, lc, cc, mHandler, GetSpecifiedTrader);

                            TraderController.GetTraderNewsList(traderCode, categorycode, index, size, mHandler, GetTraderNewsList);

                            break;

                        case GetTraderSurveys:
                            // khảo sát thực tế
                            Log.i(TAG, "handleMessage GetTraderSurveys " + msg.what + " " + msg.obj);
                            break;

                        case GetSpecifiedTrader:
                            // Trader summary
                            Log.i(TAG, "handleMessage GetSpecifiedTrader "+ msg.what + " " + msg.obj);
                            break;

                        case GetTraderNewsList:
                            // Trader summary
                            Log.i(TAG, "handleMessage GetTraderNewsList " + msg.what + " " + msg.obj);
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