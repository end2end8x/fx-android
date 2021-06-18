package com.fxeye.foreignexchangeeye;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fxeye.foreignexchangeeye.controller.UserController;
import com.fxeye.foreignexchangeeye.controller.fristpage.TraderController;
import com.fxeye.foreignexchangeeye.util_tool.https_controller.NetworkConnectionController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;

    public static final String TAG = "MainActivity";

    public static int index = 1;
    public static int size = 100;
    public static String lc = "vi";
    public static String cc = "vn";

    public static String categorycode = "zx_trader_total";

    public final static int GetTianYan_Zhida_Information = 72;
    public final static int api_general_advertise = 75;
    public final static int GetTraderSurveys = 96;
    public final static int GetTraderNewsList = 43;

    public final static int GetSpecifiedTrader = 1;
    public final static int GetMT4Items = 4;
    public final static int GetTraderAccount = 7;
    public final static int GetTraderDomain = 8;
    public final static int GetFakeTraders = 10;
    public final static int GetEpcProduct = 48;

    public final static int getNotice = 1001;
    public final static int GetTraderTransfer = 1002;
    public final static int GetTraderEvidence = 1003;
    public final static int GetTraderComplaint = 1004;
    public final static int GetTraderInterests = 1005;
    public final static int GetTraderRegulation = 1006;
    public final static int GetSkyRisk = 1007;
    public final static int GetAgents = 1008;
    public final static int GetLightMarkets = 1009;
    public final static int GetTraderFamilyTree = 1010;

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
                            String traderCode = "6821266314"; // trader.getString("traderCode");
                            Log.i(TAG, "api_general_advertise " + msg.what + " length " + result.length() + " " + traderCode + " " + trader);
                            String userId = UserController.getNewBDUserInfo(MainActivity.this).getUserId();
                            Log.i(TAG, "userId " + userId );
//                            TraderController.GetTraderSurveys(traderCode, index, size, lc, cc, mHandler, GetTraderSurveys);
//                            TraderController.GetSpecifiedTrader(traderCode, lc, cc, mHandler, GetSpecifiedTrader);
//                            TraderController.GetTraderNewsList(traderCode, categorycode, index, size, mHandler, GetTraderNewsList);
//                            TraderController.getNotice(traderCode, mHandler, getNotice);
//                            TraderController.GetTraderTransfer(traderCode, mHandler, GetTraderTransfer); AUTHEN
//                            TraderController.GetTraderEvidence(traderCode, mHandler, GetTraderEvidence); AUTHEN
//                            TraderController.GetMT4Items(traderCode, lc, cc, mHandler, GetMT4Items);
//                            TraderController.GetTraderAccount(traderCode, lc, cc, mHandler, GetTraderAccount);
//                            TraderController.GetTraderComplaint(traderCode, mHandler, GetTraderComplaint); AUTHEN
//                            TraderController.GetTraderDomain(traderCode, lc, mHandler, GetTraderDomain);
//                            TraderController.GetTraderInterests(traderCode, mHandler, GetTraderInterests); AUTHEN
//                            TraderController.GetTraderRegulation(traderCode, mHandler, GetTraderRegulation); AUTHEN
//                            TraderController.GetFakeTraders(MainActivity.this, traderCode, mHandler, GetFakeTraders);
//                            TraderController.GetSkyRisk(traderCode, traderCode, mHandler, GetFakeTraders); AUTHEN
//                            TraderController.GetAgents(traderCode, String.valueOf(index), String.valueOf(size), mHandler, GetAgents); AUTHEN
//                            TraderController.GetLightMarkets(userId, traderCode, mHandler, GetAgents); AUTHEN
//                            TraderController.GetEpcProduct(userId, traderCode, mHandler, GetEpcProduct);
                            TraderController.GetTraderFamilyTree(traderCode, mHandler, GetTraderFamilyTree);
                            break;
                        case GetTraderSurveys:
                            // khảo sát thực tế
                            Log.i(TAG, "GetTraderSurveys " + msg.what + " " + msg.obj);
                            break;
                        case GetSpecifiedTrader:
                            // Trader summary
                            Log.i(TAG, "GetSpecifiedTrader "+ msg.what + " " + msg.obj);
                            break;
                        case GetTraderNewsList:
                            // Trader News
                            Log.i(TAG, "GetTraderNewsList " + msg.what + " " + msg.obj);
                            break;
                        case getNotice:
                            // Trader Notice
                            Log.i(TAG, "getNotice " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderTransfer:
                            // Trader GetTraderTransfer
                            Log.i(TAG, "GetTraderTransfer " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderEvidence:
                            // Trader GetTraderEvidence
                            Log.i(TAG, "GetTraderEvidence " + msg.what + " " + msg.obj);
                            break;
                        case GetMT4Items:
                            // Trader GetMT4Items
                            Log.i(TAG, "GetMT4Items " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderAccount:
                            // Trader GetTraderAccount
                            Log.i(TAG, "GetTraderAccount " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderDomain:
                            // Trader GetTraderDomain
                            Log.i(TAG, "GetTraderDomain " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderInterests:
                            // Trader GetTraderInterests
                            Log.i(TAG, "GetTraderInterests " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderRegulation:
                            // Trader GetTraderRegulation
                            Log.i(TAG, "GetTraderRegulation " + msg.what + " " + msg.obj);
                            break;
                        case GetFakeTraders:
                            // Trader GetFakeTraders
                            Log.i(TAG, "GetFakeTraders " + msg.what + " " + msg.obj);
                            break;
                        case GetSkyRisk:
                            // Trader GetSkyRisk
                            Log.i(TAG, "GetSkyRisk " + msg.what + " " + msg.obj);
                            break;
                        case GetAgents:
                            // Trader GetAgents
                            Log.i(TAG, "GetAgents " + msg.what + " " + msg.obj);
                            break;
                        case GetLightMarkets:
                            // Trader GetLightMarkets
                            Log.i(TAG, "GetLightMarkets " + msg.what + " " + msg.obj);
                            break;
                        case GetEpcProduct:
                            // Trader GetEpcProduct
                            Log.i(TAG, "GetEpcProduct " + msg.what + " " + msg.obj);
                            break;
                        case GetTraderFamilyTree:
                            // Trader GetTraderFamilyTree
                            Log.i(TAG, "GetTraderFamilyTree " + msg.what + " " + msg.obj);
                            break;
                        default:
                            if(msg.what  < 0) {
                                String Code = json.getString("Code");
                                String Message = json.getString("Message");
                                Log.e(TAG, "handleMessage Code: " + Code + " Message: " + Message);
                            } else {
                                Log.i(TAG, "handleMessage " + msg.what + " " + msg.obj);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException", e);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
//        NetworkConnectionController.JiaoYiShang_Search(obj, this.mHandler, 1);
//        NetworkConnectionController.GetSearch_Find(this.mHandler, 1);
//        NetworkConnectionController.GetTianYan_Zhida_Information(this.mHandler, GetTianYan_Zhida_Information);
        NetworkConnectionController.api_general_advertise(this.mHandler, api_general_advertise);
    }
}