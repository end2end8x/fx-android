package com.fxeye.foreignexchangeeye.controller;

import com.fxeye.foreignexchangeeye.util_tool.UrlUtil;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class BaseController {
    public static List<NameValuePair> addEncryptionGETPublicParams(List<NameValuePair> list) {
        list.add(new BasicNameValuePair("Format", "JSON"));
        list.add(new BasicNameValuePair("Pattern", "encryption"));
        list.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        list.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        list.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        list.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(list, "GET", UrlUtil.TEST_Secret)));
        return list;
    }

    public static List<NameValuePair> addEncryptionPOSTPublicParams(List<NameValuePair> list) {
        list.add(new BasicNameValuePair("Format", "JSON"));
        list.add(new BasicNameValuePair("Pattern", "encryption"));
        list.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        list.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        list.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        list.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(list, "POST", UrlUtil.TEST_Secret)));
        return list;
    }

    public static List<NameValuePair> addImplicitPublicParams(List<NameValuePair> list) {
        list.add(new BasicNameValuePair("Format", "JSON"));
        list.add(new BasicNameValuePair("Pattern", "implicit"));
        list.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        list.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        list.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        return list;
    }

//    public static void showLoginTis(Context context) {
//        NoticeDialog noticeDialog = new NoticeDialog(context, R.style.song_option_dialog, "登录即可查看更多精彩内容", " ", 2, "确定", "", "");
//        noticeDialog.setCanceledOnTouchOutside(false);
//        noticeDialog.show();
//        noticeDialog.tv_confirm.setOnClickListener(new View.OnClickListener(context) {
//            private final /* synthetic */ Context f$1;
//
//            {
//                this.f$1 = r2;
//            }
//
//            public final void onClick(View view) {
//                BaseController.lambda$showLoginTis$0(NoticeDialog.this, this.f$1, view);
//            }
//        });
//    }
//
//    static /* synthetic */ void lambda$showLoginTis$0(NoticeDialog noticeDialog, Context context, View view) {
//        noticeDialog.dismiss();
//        context.startActivity(new Intent(context, InternationalLoginActivity.class));
//    }
}
