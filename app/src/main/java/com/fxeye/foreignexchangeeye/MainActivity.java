package com.fxeye.foreignexchangeeye;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fxeye.foreignexchangeeye.util_tool.https_controller.NetworkConnectionController;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.handler = new Handler() {
            public void handleMessage(Message message) {
                Log.i("test", "handleMessage what" + message.what + " " + message.arg1);
                if (message.what == 1) {
                    Log.i("test", "handleMessage " + message.obj);
                }
                super.handleMessage(message);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        String obj = "lite";
        NetworkConnectionController.JiaoYiShang_Search(obj, MainActivity.this.handler, 1);
//        NetworkConnectionController.GetSearch_Find(this.handler, 1);
    }
}