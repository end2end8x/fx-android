package com.fxeye.foreignexchangeeye.util_tool.https_controller;

//import Decoder.BASE64Encoder;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.appsflyer.share.Constants;
//import com.facebook.appevents.internal.ViewHierarchyConstants;
//import com.facebook.internal.ServerProtocol;
import com.fxeye.foreignexchangeeye.MyApplication;
import com.fxeye.foreignexchangeeye.controller.AboutController;
import com.fxeye.foreignexchangeeye.controller.BaseController;
import com.fxeye.foreignexchangeeye.controller.UserController;
import com.fxeye.foreignexchangeeye.controller.fristpage.FristPageController;
import com.fxeye.foreignexchangeeye.entity.could_entity.ProductEntity;
import com.fxeye.foreignexchangeeye.entity.could_entity.ProductssBean;
import com.fxeye.foreignexchangeeye.entity.newmy.DanggeEntity;
import com.fxeye.foreignexchangeeye.util_tool.BasicUtils;
import com.fxeye.foreignexchangeeye.util_tool.MyBasic;
import com.fxeye.foreignexchangeeye.util_tool.UrlUtil;
import com.fxeye.foreignexchangeeye.util_tool.analysis.GsonUtil;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttp;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpClientManager;
import com.fxeye.foreignexchangeeye.util_tool.okhttps.OkHttpUtils;
import com.fxeye.foreignexchangeeye.view.im_chatutils.IMOkHttpRequestManager;
import com.wiki.exposure.framework.utils.LanguageType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import okhttp3.Request;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
//import com.google.android.exoplayer2.text.ttml.TtmlDecoder.*;

public class NetworkConnectionController extends BaseController {
    public static final String ALLDATA = "82100014";
    public static final String BAOGUANTAI = "9210000017";
    public static final String BAOZHANGPINGTAI = "9210000015";
    public static final String CALENDAR = "82100024";
    public static final String CLOUD_OPEN = "82100036";
    public static final String DALISHANG = "9210000001";
    public static final String DATA_QUICK = "82100023";
    public static final String DIANCHABIAO = "82100005";
    public static final String DIANCHA_HUANJING = "92100027";
    public static final String EXPOSURE = "82100033";
    public static final String FIELD_SURVEY = "82400010";
    public static final String GEYE_CHENGBEN = "92100029";
    public static final String HANGQING_HUANJING = "92100028";
    public static final String HEIPINGTAI = "82300010";
    public static final String HUILVJISUAN = "82100008";
    public static final String HUI_BA = "82100034";
    public static final String JIAOYI_HUANJING = "92100025";
    public static final String JIAOYI_TIYAN = "92100026";
    public static final String JIEHUIJISUAN = "9210000010";
    public static final String JISHI = "9210000013";
    public static final String QIJIANDIAN = "82100016";
    public static final String REGULATORLIST = "82100017";
    public static final String RENQI_PAIHANG = "82100019";
    public static final String SHOUCANG = "82100010";
    public static final String SHUCHENG_CODE = "82100035";
    public static final String TESE_PINGTAI = "9210000100";
    public static final String TIANYANBANGZHU = "9210000008";
    public static final String TIANYANJIANKONG = "9210000003";
    public static final String TIANYANPAIHANG = "82200010";
    public static final String TIANYANZHIDA = "82100003";
    public static final String VIPTEQUAN = "9210000005";
    public static final String WAIHUIHANGQING = "82100006";
    public static final String XINWEN = "9210000012";
    public static final String ZAIANPAIHANG = "82500010";

    interface PlaceFields {
        String PHONE = "phone";
    }

    class TtmlNode {
        public static final String TAG_TT = "tt";
        public static final String TAG_HEAD = "head";
        public static final String TAG_BODY = "body";
        public static final String TAG_DIV = "div";
        public static final String TAG_P = "p";
        public static final String TAG_SPAN = "span";
        public static final String TAG_BR = "br";
        public static final String TAG_STYLE = "style";
        public static final String TAG_STYLING = "styling";
        public static final String TAG_LAYOUT = "layout";
        public static final String TAG_REGION = "region";
        public static final String TAG_METADATA = "metadata";
        public static final String TAG_IMAGE = "image";
        public static final String TAG_DATA = "data";
        public static final String TAG_INFORMATION = "information";

        public static final String ANONYMOUS_REGION_ID = "";
        public static final String ATTR_ID = "id";
        public static final String ATTR_TTS_ORIGIN = "origin";
        public static final String ATTR_TTS_EXTENT = "extent";
        public static final String ATTR_TTS_DISPLAY_ALIGN = "displayAlign";
        public static final String ATTR_TTS_BACKGROUND_COLOR = "backgroundColor";
        public static final String ATTR_TTS_FONT_STYLE = "fontStyle";
        public static final String ATTR_TTS_FONT_SIZE = "fontSize";
        public static final String ATTR_TTS_FONT_FAMILY = "fontFamily";
        public static final String ATTR_TTS_FONT_WEIGHT = "fontWeight";
        public static final String ATTR_TTS_COLOR = "color";
        public static final String ATTR_TTS_RUBY = "ruby";
        public static final String ATTR_TTS_RUBY_POSITION = "rubyPosition";
        public static final String ATTR_TTS_TEXT_DECORATION = "textDecoration";
        public static final String ATTR_TTS_TEXT_ALIGN = "textAlign";
        public static final String ATTR_TTS_TEXT_COMBINE = "textCombine";
        public static final String ATTR_TTS_TEXT_EMPHASIS = "textEmphasis";
        public static final String ATTR_TTS_WRITING_MODE = "writingMode";
        public static final String ATTR_TTS_SHEAR = "shear";
        public static final String ATTR_EBUTTS_MULTI_ROW_ALIGN = "multiRowAlign";

        // Values for ruby
        public static final String RUBY_CONTAINER = "container";
        public static final String RUBY_BASE = "base";
        public static final String RUBY_BASE_CONTAINER = "baseContainer";
        public static final String RUBY_TEXT = "text";
        public static final String RUBY_TEXT_CONTAINER = "textContainer";
        public static final String RUBY_DELIMITER = "delimiter";

        // Values for text annotation (i.e. ruby, text emphasis) position
        public static final String ANNOTATION_POSITION_BEFORE = "before";
        public static final String ANNOTATION_POSITION_AFTER = "after";
        public static final String ANNOTATION_POSITION_OUTSIDE = "outside";

        // Values for textDecoration
        public static final String LINETHROUGH = "linethrough";
        public static final String NO_LINETHROUGH = "nolinethrough";
        public static final String UNDERLINE = "underline";
        public static final String NO_UNDERLINE = "nounderline";
        public static final String ITALIC = "italic";
        public static final String BOLD = "bold";

        // Values for textAlign
        public static final String LEFT = "left";
        public static final String CENTER = "center";
        public static final String RIGHT = "right";
        public static final String START = "start";
        public static final String END = "end";

        // Values for textCombine
        public static final String COMBINE_NONE = "none";
        public static final String COMBINE_ALL = "all";

        // Values for writingMode
        public static final String VERTICAL = "tb";
        public static final String VERTICAL_LR = "tblr";
        public static final String VERTICAL_RL = "tbrl";

        // Values for textEmphasis
        public static final String TEXT_EMPHASIS_NONE = "none";
        public static final String TEXT_EMPHASIS_AUTO = "auto";
        public static final String TEXT_EMPHASIS_MARK_DOT = "dot";
        public static final String TEXT_EMPHASIS_MARK_SESAME = "sesame";
        public static final String TEXT_EMPHASIS_MARK_CIRCLE = "circle";
        public static final String TEXT_EMPHASIS_MARK_FILLED = "filled";
        public static final String TEXT_EMPHASIS_MARK_OPEN = "open";
    }

    class JThirdPlatFormInterface {
        public static final String KEY_PLATFORM = "platform";
    }

    class HwIDConstant {
        class Req_access_token_parm {
            public static final String LANGUAGE_LABEL = "lang";
        }
    }

    class HwPayConstant {
        public static final String KEY_AMOUNT = "amount";
    }

    class MessengerShareContentUtility {
        public static final String MEDIA_IMAGE = "image";
    }

    class ServerProtocol {
        private static final String DIALOG_AUTHORITY_FORMAT = "m.%s";
        public static final String DIALOG_PATH = "dialog/";
        public static final String DIALOG_PARAM_ACCESS_TOKEN = "access_token";
        public static final String DIALOG_PARAM_APP_ID = "app_id";
        public static final String DIALOG_PARAM_AUTH_TYPE = "auth_type";
        public static final String DIALOG_PARAM_CBT = "cbt";
        public static final String DIALOG_PARAM_CLIENT_ID = "client_id";
        public static final String DIALOG_PARAM_CUSTOM_TABS_PREFETCHING = "cct_prefetching";
        public static final String DIALOG_PARAM_DISPLAY = "display";
        public static final String DIALOG_PARAM_DISPLAY_TOUCH = "touch";
        public static final String DIALOG_PARAM_E2E = "e2e";
        public static final String DIALOG_PARAM_IES = "ies";
        public static final String DIALOG_PARAM_LEGACY_OVERRIDE = "legacy_override";
        public static final String DIALOG_PARAM_LOGIN_BEHAVIOR = "login_behavior";
        public static final String DIALOG_PARAM_REDIRECT_URI = "redirect_uri";
        public static final String DIALOG_PARAM_RESPONSE_TYPE = "response_type";
        public static final String DIALOG_PARAM_RETURN_SCOPES = "return_scopes";
        public static final String DIALOG_PARAM_SCOPE = "scope";
        public static final String DIALOG_PARAM_SSO_DEVICE = "sso";
        public static final String DIALOG_PARAM_DEFAULT_AUDIENCE = "default_audience";
        public static final String DIALOG_PARAM_SDK_VERSION = "sdk";
        public static final String DIALOG_PARAM_STATE = "state";
        public static final String DIALOG_PARAM_FAIL_ON_LOGGED_OUT = "fail_on_logged_out";
        public static final String DIALOG_PARAM_CCT_OVER_LOGGED_OUT_APP_SWITCH = "cct_over_app_switch";
        public static final String DIALOG_REREQUEST_AUTH_TYPE = "rerequest";
        public static final String DIALOG_RESPONSE_TYPE_TOKEN_AND_SIGNED_REQUEST =
                "token,signed_request,graph_domain";
        public static final String DIALOG_RETURN_SCOPES_TRUE = "true";
        public static final String DIALOG_REDIRECT_URI = "fbconnect://success";
        public static final String DIALOG_REDIRECT_CHROME_OS_URI = "fbconnect://chrome_os_success";
        public static final String DIALOG_CANCEL_URI = "fbconnect://cancel";

        public static final String FALLBACK_DIALOG_PARAM_APP_ID = "app_id";
        public static final String FALLBACK_DIALOG_PARAM_BRIDGE_ARGS = "bridge_args";
        public static final String FALLBACK_DIALOG_PARAM_KEY_HASH = "android_key_hash";
        public static final String FALLBACK_DIALOG_PARAM_METHOD_ARGS = "method_args";
        public static final String FALLBACK_DIALOG_PARAM_METHOD_RESULTS = "method_results";
        public static final String FALLBACK_DIALOG_PARAM_VERSION = "version";
        public static final String FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH = "touch";

        // URL components
        private static final String GRAPH_VIDEO_URL_FORMAT = "https://graph-video.%s";
        private static final String GRAPH_URL_FORMAT = "https://graph.%s";
        private static final String GRAPH_API_VERSION = "v8.0";
    }

    class ViewHierarchyConstants {
        public static final String ID_KEY = "id";
        public static final String CLASS_NAME_KEY = "classname";
        public static final String CLASS_TYPE_BITMASK_KEY = "classtypebitmask";
        public static final String TEXT_KEY = "text";
        public static final String DESC_KEY = "description";
        public static final String DIMENSION_KEY = "dimension";
        public static final String IS_USER_INPUT_KEY = "is_user_input";
        public static final String TAG_KEY = "tag";
        public static final String CHILDREN_VIEW_KEY = "childviews";
        public static final String HINT_KEY = "hint";
        public static final String DIMENSION_TOP_KEY = "top";
        public static final String DIMENSION_LEFT_KEY = "left";
        public static final String DIMENSION_WIDTH_KEY = "width";
        public static final String DIMENSION_HEIGHT_KEY = "height";
        public static final String DIMENSION_SCROLL_X_KEY = "scrollx";
        public static final String DIMENSION_SCROLL_Y_KEY = "scrolly";
        public static final String DIMENSION_VISIBILITY_KEY = "visibility";
        public static final String TEXT_SIZE = "font_size";
        public static final String TEXT_IS_BOLD = "is_bold";
        public static final String TEXT_IS_ITALIC = "is_italic";
        public static final String TEXT_STYLE = "text_style";
        public static final String ICON_BITMAP = "icon_image";
        public static final String INPUT_TYPE_KEY = "inputtype";
        public static final String IS_INTERACTED_KEY = "is_interacted";
        public static final String SCREEN_NAME_KEY = "screenname";
        public static final String VIEW_KEY = "view";

        public static final String ENGLISH = "ENGLISH";
        public static final String GERMAN = "GERMAN";
        public static final String SPANISH = "SPANISH";
        public static final String JAPANESE = "JAPANESE";

        public static final String VIEW_CONTENT = "VIEW_CONTENT";
        public static final String SEARCH = "SEARCH";
        public static final String ADD_TO_CART = "ADD_TO_CART";
        public static final String ADD_TO_WISHLIST = "ADD_TO_WISHLIST";
        public static final String INITIATE_CHECKOUT = "INITIATE_CHECKOUT";
        public static final String ADD_PAYMENT_INFO = "ADD_PAYMENT_INFO";
        public static final String PURCHASE = "PURCHASE";
        public static final String LEAD = "LEAD";
        public static final String COMPLETE_REGISTRATION = "COMPLETE_REGISTRATION";

        public static final String BUTTON_TEXT = "BUTTON_TEXT";
        public static final String PAGE_TITLE = "PAGE_TITLE";
        public static final String RESOLVED_DOCUMENT_LINK = "RESOLVED_DOCUMENT_LINK";
        public static final String BUTTON_ID = "BUTTON_ID";

        public static final int TEXTVIEW_BITMASK = 0;
        public static final int IMAGEVIEW_BITMASK = 1;
        public static final int BUTTON_BITMASK = 2;
        public static final int CLICKABLE_VIEW_BITMASK = 5;
        public static final int REACT_NATIVE_BUTTON_BITMASK = 6;
        public static final int ADAPTER_VIEW_ITEM_BITMASK = 9;
        public static final int LABEL_BITMASK = 10;
        public static final int INPUT_BITMASK = 11;
        public static final int PICKER_BITMASK = 12;
        public static final int SWITCH_BITMASK = 13;
        public static final int RADIO_GROUP_BITMASK = 14;
        public static final int CHECKBOX_BITMASK = 15;
        public static final int RATINGBAR_BITMASK = 16;
    }

    public static void AddTraderBrowseStatistics(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.AddTraderBrowseStatistics));
        if (AboutController.getAppThreeDayLock()) {
            arrayList.add(new BasicNameValuePair("sandbox", "1"));
        } else {
            arrayList.add(new BasicNameValuePair("sandbox", "0"));
        }
        arrayList.add(new BasicNameValuePair("equipId", BasicUtils.getIMEI(MyApplication.getInstance())));
        String newBDUserId = UserController.getNewBDUserId(MyApplication.getInstance());
        if (!UserController.isNewUserLogin(MyApplication.getInstance()) || TextUtils.isEmpty(newBDUserId)) {
            arrayList.add(new BasicNameValuePair("userid", "0"));
        } else {
            arrayList.add(new BasicNameValuePair("userid", newBDUserId));
        }
        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "3"));
        arrayList.add(new BasicNameValuePair("apptype", "1"));
        String GetIp = BasicUtils.GetIp(MyApplication.getContext());
        if (TextUtils.isEmpty(GetIp)) {
            GetIp = "";
        }
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, GetIp));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("equipinfo", "Android;手机型号" + Build.MODEL + ";手机厂商" + Build.MANUFACTURER));
        arrayList.add(new BasicNameValuePair("country", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("type", "1"));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("spots", "5"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void sendYanZhengMa(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SEND_YAN_ZHENG_MA));
        arrayList.add(new BasicNameValuePair("areaCode", UserController.getNewBDUserInfo(MyApplication.getInstance()).getAreaCode()));
        arrayList.add(new BasicNameValuePair("userId", UserController.getNewBDUserInfo(MyApplication.getInstance()).getUserId()));
        arrayList.add(new BasicNameValuePair(PlaceFields.PHONE, UserController.getNewBDUserInfo(MyApplication.getInstance()).getMdp()));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.CLOUD_VERIFICATION);
    }

    public static void YanZhengMa(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.YAN_ZHENG_YAN_ZHENG_MA));
        arrayList.add(new BasicNameValuePair("areaCode", UserController.getNewBDUserInfo(MyApplication.getInstance()).getAreaCode()));
        arrayList.add(new BasicNameValuePair("userId", UserController.getNewBDUserInfo(MyApplication.getInstance()).getUserId()));
        arrayList.add(new BasicNameValuePair("smscode", str));
        arrayList.add(new BasicNameValuePair("phoneNumber", UserController.getNewBDUserInfo(MyApplication.getInstance()).getMdp()));
        arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.CLOUD_VERIFICATION);
    }

    public static void LiulanJilu(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.LiuLanJilu));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("userId", str + ""));
        } else {
            arrayList.add(new BasicNameValuePair("userId", "0"));
        }
        arrayList.add(new BasicNameValuePair("traderCode", str2 + ""));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersion()));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "1"));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.getIPAddress(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("udid", BasicUtils.getIMEI(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("equipment", "Android;手机型号" + Build.MODEL + ";手机厂商" + Build.MANUFACTURER));
        arrayList.add(new BasicNameValuePair("type", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTianYan_Zhida_Information(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.TIANYAN_ZHIDA));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTianYan_Zhida_Second(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.TIANYAN_ZHIDA_SECOND));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetHomeAd(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetHomeAd));
        arrayList.add(new BasicNameValuePair("app", "7"));
        arrayList.add(new BasicNameValuePair("ver", str));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("cty", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("cty", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("lan", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("lan", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetJiaoyiShang_rank(Handler handler, String str, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JIAOYISHANGRANK));
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("code", str));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetJishi_List(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JISHI_LIST));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetJiaoyiShang_Hei(Handler handler, String str, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.HEIPINGTAI));
        if (!TextUtils.isEmpty(str.trim())) {
            arrayList.add(new BasicNameValuePair("id", str));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetQiJianDian(int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.QIJIANDIAN));
        arrayList.add(new BasicNameValuePair("index", i + ""));
        arrayList.add(new BasicNameValuePair("size", i2 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void GetBaozhang_List(int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEWBAOZHANG_LIST));
        arrayList.add(new BasicNameValuePair("index", i + ""));
        arrayList.add(new BasicNameValuePair("size", i2 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void GetShoucang_List(String str, int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SHOUCANG_LIST));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        if (i != 0) {
            if (!TextUtils.isEmpty(i + "")) {
                arrayList.add(new BasicNameValuePair("pageIndex", i + ""));
            }
        }
        if (i2 != 0) {
            if (!TextUtils.isEmpty(i2 + "")) {
                arrayList.add(new BasicNameValuePair("pageSize", i2 + ""));
            }
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL_USER);
    }

    public static void GetJigou_List(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "GJGetUserHistoryBrowseList"));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Goumai_Yanzheng_Data(String str, String str2, boolean z, String str3, int i, String str4, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("id", str));
        arrayList.add(new BasicNameValuePair("userId", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("num", str2));
        arrayList.add(new BasicNameValuePair("IsSponsor", z + ""));
        String GetIp = BasicUtils.GetIp(MyApplication.getContext());
        if (!TextUtils.isEmpty(GetIp)) {
            arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, GetIp));
        }
        arrayList.add(new BasicNameValuePair("country", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(IjkMediaMeta.IJKM_KEY_LANGUAGE, BasicUtils.GetLanguageString(MyApplication.getContext())));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("addresscountrycode", str3));
        }
        if (i == 1 && !TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("cd", GetZhifuPass_Data(str4)));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.EXPOSURE_AccessKey));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i2, "https://oce.fx696.com:5200/Bookstore/gobalproduct/buyvalidate");
    }

    public static String GetZhifuPass_Data(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + "");
            jSONObject.put("cd", str + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString().trim();
    }

    public static void GJGetUserHistoryBrowseList(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "GJGetUserHistoryBrowseList"));
        arrayList.add(new BasicNameValuePair("userId", str));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        new OkHttp().OkHttpGetMethod(addEncryptionGETPublicParams(arrayList), handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_NewFunction_Information(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEW_FUNCTION));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void New_Get_AllFunction_Information(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEW_NEWALL_FUNCTION));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Extrude_Dealer(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_TESEJIAOYISHANG));
        arrayList.add(new BasicNameValuePair("id", str + ""));
        arrayList.add(new BasicNameValuePair("index", str2 + ""));
        arrayList.add(new BasicNameValuePair("size", str3 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Entrance_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_ENTRANCE));
        arrayList.add(new BasicNameValuePair("key", str + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetSearch_Find(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SEARCH_FIND));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void ShouYe_Search_Find(String str, int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SHOUYE_SEARCH_FIND));
        arrayList.add(new BasicNameValuePair("fuzzySearch", FristPageController.encodeURI(str)));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        if (i != 0) {
            if (!TextUtils.isEmpty(i + "")) {
                arrayList.add(new BasicNameValuePair("pageIndex", i + ""));
            }
        }
        if (i2 != 0) {
            if (!TextUtils.isEmpty(i2 + "")) {
                arrayList.add(new BasicNameValuePair("pageSize", i2 + ""));
            }
        }
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        String GetIp = BasicUtils.GetIp(MyApplication.getContext());
        if (!TextUtils.isEmpty(GetIp)) {
            arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, GetIp));
        }
        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "Android:" + Build.VERSION.RELEASE + "," + Build.MANUFACTURER + "," + Build.MODEL));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret, true)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void GetDaiLiShang_List(Handler handler, int i, int i2, String str, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DAILISHANG_LIST));
        if (i != 0) {
            if (!TextUtils.isEmpty(i + "")) {
                arrayList.add(new BasicNameValuePair("pageIndex", i + ""));
            }
        }
        if (i2 != 0) {
            if (!TextUtils.isEmpty(i2 + "")) {
                arrayList.add(new BasicNameValuePair("pageSize", i2 + ""));
            }
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("content", FristPageController.encodeURI(str) + ""));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret, true)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void UserCenterQRCodeScan(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "UserCenterQRCodeScann"));
        arrayList.add(new BasicNameValuePair("token", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Get_Dailishang_Data(Handler handler, String str, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DAILISHANG_XIANGQING));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_ScanDingdan_Data(Handler handler, String str, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "GetOrderDetail"));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Dailishang_Jiaoyishang_Data(String str, int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DAILISHANG_JIAOYISHANG));
        arrayList.add(new BasicNameValuePair("code", str));
        if (i != 0) {
            if (!TextUtils.isEmpty(i + "")) {
                arrayList.add(new BasicNameValuePair("pageIndex", i + ""));
            }
        }
        if (i2 != 0) {
            if (!TextUtils.isEmpty(i2 + "")) {
                arrayList.add(new BasicNameValuePair("pageSize", i2 + ""));
            }
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void GetAddress_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_ADDRESS_LIST));
        arrayList.add(new BasicNameValuePair("userId", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Get_Dailishang_JianDing(Handler handler, String str, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DAILISHANGJIANDING));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void UserScanCancer(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "UserCenterQRCodeCancelLogin"));
        arrayList.add(new BasicNameValuePair("token", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void UserCenterQRCodeAuthorization(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "UserCenterQRCodeAuthorization"));
        arrayList.add(new BasicNameValuePair("token", str));
        arrayList.add(new BasicNameValuePair("userId", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_USER));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_USER)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_USER);
    }

    public static void Add_ShouCang(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.ADD_SHOUCANG));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("traderCode", str2));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void HuiChuan_Data(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "PayCallback"));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("type", str2));
        arrayList.add(new BasicNameValuePair("pyt", str3));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "22"));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("trade_no", str4));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static void Delete_ShouCang(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DELETE_SHOUCANG));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("traderCode", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void DeleteNewsList(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("code", str));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.DELETE_NEWS_LIST);
    }

    public static void DeleteAllNewsList(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("uid", str));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.DELETE_ALL_NEWS_LIST);
    }

    public static void Delete_LiuLan(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DELETE_LIULAN));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("traderCode", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetCloud_jiaoyi_Data(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_JIAOYI_HAO));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("pyt", str2));
        arrayList.add(new BasicNameValuePair("mpt", "2"));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion() + ""));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "14"));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("pass", GetZhifuPass_Data(str3)));
        }
        arrayList.add(new BasicNameValuePair(IjkMediaMeta.IJKM_KEY_LANGUAGE, BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Zhifu_Single_Cloud(Handler handler, int i, String str, double d, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.TONGYI_DINGDAN));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("type", "32"));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "1"));
        arrayList.add(new BasicNameValuePair("cpo", "52"));
        arrayList.add(new BasicNameValuePair("cy_code", str3));
        arrayList.add(new BasicNameValuePair("cy_symbol", str2));
        arrayList.add(new BasicNameValuePair("ppt", str));
        arrayList.add(new BasicNameValuePair("mpt", "2"));
        arrayList.add(new BasicNameValuePair("delivery", "3"));
        ArrayList arrayList2 = new ArrayList();
        ProductEntity.ContentBean.ResultBean.TotalPriceBean totalPriceBean = new ProductEntity.ContentBean.ResultBean.TotalPriceBean();
        totalPriceBean.setPrice(d);
        totalPriceBean.setType(1);
        arrayList2.add(totalPriceBean);
        ProductEntity.ContentBean.ResultBean.TotalPriceBean totalPriceBean2 = new ProductEntity.ContentBean.ResultBean.TotalPriceBean();
        totalPriceBean2.setPrice(d);
        totalPriceBean2.setType(99);
        arrayList2.add(totalPriceBean2);
        arrayList.add(new BasicNameValuePair("manifests", Manifest_Data(arrayList2)));
        ProductssBean.ProductsBean productsBean = new ProductssBean.ProductsBean();
        productsBean.setBarcode("EA云主机");
        productsBean.setConcessions("EA云主机");
        productsBean.setCount(1);
        productsBean.setImage("http://eimgjys.fxeye.com/logo/2541435542/FXT_2541435542_400x226.png_fxeye-template-logo");
        productsBean.setName("EA云主机");
        productsBean.setOp(d);
        productsBean.setPid("7");
        productsBean.setProperty("EA云主机");
        productsBean.setSp(d);
        productsBean.setTag("EA云主机");
        productsBean.setType(15);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(productsBean);
        arrayList.add(new BasicNameValuePair("products", GsonUtil.objectToString(arrayList3)));
//        String phone = new BASE64Encoder().encode(UserController.getNewBDUserInfo(MyApplication.getContext()).getPhone().getBytes());
        byte[] encodedBytes = Base64.encode(UserController.getNewBDUserInfo(MyApplication.getContext()).getPhone().getBytes(), 0);
        String phone = new String(encodedBytes);
        arrayList.add(new BasicNameValuePair("address", Address_Data("156", "0086", phone, "疯狂天眼", "祖冲之路2222弄17号", "上海", "上海市", "浦东新区")));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static String Manifest_Data(List<ProductEntity.ContentBean.ResultBean.TotalPriceBean> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", list.get(i).getType() + "");
                jSONObject.put("price", list.get(i).getPrice() + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString().trim();
    }

    public static void Getjiaoyi_Data(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_JIAOYI_HAO));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("pyt", "78"));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "7"));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("pass", GetZhifuPass_Data(str2)));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static void GetSanPay_Data(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_JIAOYI_HAO));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("pyt", "76"));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "7"));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        arrayList.add(new BasicNameValuePair(IjkMediaMeta.IJKM_KEY_LANGUAGE, BasicUtils.GetLanguageString(MyApplication.getContext())));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("pass", GetZhifuPass_Data(str2)));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static void Delete_AllData(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DELETE_ALL));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static String Address_Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("country_code", str + "");
            jSONObject.put("area_code", str2 + "");
            jSONObject.put(PlaceFields.PHONE, str3 + "");
            jSONObject.put("consignee", str4.replaceAll(" +", "　"));
            jSONObject.put("detailed", str5.replaceAll(" +", "　"));
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("province", str6 + "");
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put("city", str7 + "");
            }
            if (!TextUtils.isEmpty(str8)) {
                jSONObject.put("county", str8 + "");
            }
            if (!TextUtils.isEmpty(str9)) {
                jSONObject.put("country", str9 + "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString().trim();
    }

    public static String Address_Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("country_code", str + "");
            jSONObject.put("area_code", str2 + "");
            jSONObject.put(PlaceFields.PHONE, str3 + "");
            jSONObject.put("consignee", str4.replaceAll(" +", "　"));
            jSONObject.put("detailed", str5.replaceAll(" +", "　"));
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("province", str6 + "");
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put("city", str7 + "");
            }
            if (!TextUtils.isEmpty(str8)) {
                jSONObject.put("county", str8 + "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString().trim();
    }

    public static void IsorNo_Shoucang(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SHOUCANG_ISORNO));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("traderCode", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Siheyi_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_SIHEYI));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void IsorNo_Jiankong(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JIANKONG_ISORNO));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Add_Jiankong(String str, String str2, int i, Handler handler, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JIANKONG_ADD));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("type", i + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i2, UrlUtil.TEST_URL);
    }

    public static void Prdouct_Xiangxi(String str, boolean z, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Ids", str));
        arrayList.add(new BasicNameValuePair("IsSponsor", z + ""));
        if (!TextUtils.isEmpty(UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId())) {
            arrayList.add(new BasicNameValuePair("userid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId()));
        }
        arrayList.add(new BasicNameValuePair("type", str2));
        String GetIp = BasicUtils.GetIp(MyApplication.getContext());
        if (!TextUtils.isEmpty(GetIp)) {
            arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, GetIp));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("addresscountrycode", str3));
        }
        arrayList.add(new BasicNameValuePair("Country", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Language", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.EXPOSURE_AccessKey));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, "https://oce.fx696.com:5200/Bookstore/gobalproduct/getorder");
    }

    public static void Delete_Jiankong(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JIANKONG_DELETE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetJiankong_List(String str, int i, int i2, Handler handler, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JIANKONG_LIST));
        arrayList.add(new BasicNameValuePair("userId", str));
        if (i != 0) {
            if (!TextUtils.isEmpty(i + "")) {
                arrayList.add(new BasicNameValuePair("pageIndex", i + ""));
            }
        }
        if (i2 != 0) {
            if (!TextUtils.isEmpty(i2 + "")) {
                arrayList.add(new BasicNameValuePair("pageSize", i2 + ""));
            }
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i3, UrlUtil.TEST_URL);
    }

    public static void GetTianYan_Hei(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.PINGFEN_HEI));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetQiJianDian(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GETQIJIANDIAN));
        arrayList.add(new BasicNameValuePair("countryCode", str2));
        arrayList.add(new BasicNameValuePair("languageCode", str3));
        arrayList.add(new BasicNameValuePair("traderCode", str));
        arrayList.add(new BasicNameValuePair("ver", str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Zhifu_Tongyi2(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, List<DanggeEntity> list, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.TONGYI_DINGDAN));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserId(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("type", "4"));
        arrayList.add(new BasicNameValuePair("delivery", "1"));
        arrayList.add(new BasicNameValuePair("cpo", "22"));
        String str13 = str;
        arrayList.add(new BasicNameValuePair("cy_code", str));
        String str14 = str2;
        arrayList.add(new BasicNameValuePair("cy_symbol", str2));
        String str15 = str3;
        arrayList.add(new BasicNameValuePair("ppt", str3));
        String str16 = str4;
        arrayList.add(new BasicNameValuePair("rea", str4));
        arrayList.add(new BasicNameValuePair("products", Tongyi_Data2(str5, str6, str7, str8, str9, str10, str11, str12)));
        arrayList.add(new BasicNameValuePair("manifests", Dingdan_Data2(list)));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static String Tongyi_Data2(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.URL_MEDIA_SOURCE, str + "");
            jSONObject.put("barcode", str + "");
            jSONObject.put("type", UrlUtil.SHOUYE_POP_GUANGGAO_CLICK);
            jSONObject.put("name", str2.replaceAll(" +", "　"));
            jSONObject.put(MessengerShareContentUtility.MEDIA_IMAGE, str3);
            jSONObject.put("count", "1");
            jSONObject.put("op", str4 + "");
            jSONObject.put("sp", str5 + "");
            if (!TextUtils.isEmpty(str6)) {
                jSONObject.put("concessions", str6.replaceAll(" +", "　"));
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put(ViewHierarchyConstants.TAG_KEY, str7.replaceAll(" +", "　"));
            }
            if (!TextUtils.isEmpty(str8)) {
                String str9 = str8.replaceAll(" +", "　") + "," + BasicUtils.GetCountryString(MyApplication.getContext()) + ",2,1";
                Log.e(ViewHierarchyConstants.TAG_KEY, "Tongyi_Data2: " + str9);
                jSONObject.put("property", str9);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jSONArray.put(jSONObject);
        return jSONArray.toString().trim();
    }

    public static String Dingdan_Data2(List<DanggeEntity> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", list.get(i).getType() + "");
                jSONObject.put("price", list.get(i).getPrice() + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString().trim();
    }

    public static void Zhifu_Tongyi(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, List<com.fxeye.foreignexchangeeye.entity.international_my.DanggeEntity> list, String str13, String str14, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.TONGYI_DINGDAN));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("type", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("delivery", "4"));
        arrayList.add(new BasicNameValuePair("cpo", "22"));
        String str15 = str;
        arrayList.add(new BasicNameValuePair("cy_code", str));
        String str16 = str2;
        arrayList.add(new BasicNameValuePair("cy_symbol", str2));
        String str17 = str3;
        arrayList.add(new BasicNameValuePair("ppt", str3));
        arrayList.add(new BasicNameValuePair("products", Tongyi_Data(str4, str5, str6, str7, str8, str9, str10, str11, str12, str14)));
        arrayList.add(new BasicNameValuePair("manifests", Dingdan_Data(list)));
        arrayList.add(new BasicNameValuePair("address", str13));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static String Dingdan_Data(List<com.fxeye.foreignexchangeeye.entity.international_my.DanggeEntity> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", list.get(i).getType() + "");
                jSONObject.put("price", list.get(i).getPrice() + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString().trim();
    }

    public static String Tongyi_Data(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            StringBuilder sb = new StringBuilder();
            String str11 = str;
            sb.append(str);
            sb.append("");
            jSONObject.put(Constants.URL_MEDIA_SOURCE, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            String str12 = str2;
            sb2.append(str2);
            sb2.append("");
            jSONObject.put("barcode", sb2.toString());
            jSONObject.put("type", str10 + "");
            String str13 = str3;
            jSONObject.put("name", str3.replaceAll(" +", "　"));
            String str14 = str4;
            jSONObject.put(MessengerShareContentUtility.MEDIA_IMAGE, str4);
            jSONObject.put("count", "1");
            StringBuilder sb3 = new StringBuilder();
            String str15 = str5;
            sb3.append(str5);
            sb3.append("");
            jSONObject.put("op", sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            String str16 = str6;
            sb4.append(str6);
            sb4.append("");
            jSONObject.put("sp", sb4.toString());
            if (!TextUtils.isEmpty(str7)) {
                String str17 = str7;
                jSONObject.put("concessions", str7.replaceAll(" +", "　"));
            }
            if (!TextUtils.isEmpty(str8)) {
                String str18 = str8;
                jSONObject.put(ViewHierarchyConstants.TAG_KEY, str8.replaceAll(" +", "　"));
            }
            if (!TextUtils.isEmpty(str9)) {
                jSONObject.put("property", str9.replaceAll(" +", "　"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jSONArray.put(jSONObject);
        return jSONArray.toString().trim();
    }

    public static void GetBaoguang_XinyongGooglePay(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("orderId", str));
        arrayList.add(new BasicNameValuePair(HwPayConstant.KEY_AMOUNT, str2));
        arrayList.add(new BasicNameValuePair("subject", str3));
        arrayList.add(new BasicNameValuePair("passback", "1"));
        arrayList.add(new BasicNameValuePair("notifyurl", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.XINYONG_TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/DealersPdf/api/GooglePay");
    }

    public static void JiaoYiShang_Search(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.JIANKONG_SEARCH));
        arrayList.add(new BasicNameValuePair("name", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
//        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void DailiShang_Search(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DAILISHANG_SEARCH));
        arrayList.add(new BasicNameValuePair("name", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Submit_Data_YiShoulu(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.COMMIT_YISHOULU));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("code", str2));
        if (str3 != null && !TextUtils.isEmpty(str3.trim())) {
            arrayList.add(new BasicNameValuePair("job", str3));
        }
        if (str4 != null && !TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("images", str4));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Submit_Data_WeiShoulu(String str, String str2, String str3, String str4, String str5, String str6, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.COMMIT_WEISHOULU));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("company", str2));
        if (str3 != null && !TextUtils.isEmpty(str3.trim())) {
            arrayList.add(new BasicNameValuePair("site", str3));
        }
        if (str4 != null && !TextUtils.isEmpty(str4.trim())) {
            arrayList.add(new BasicNameValuePair("contact", str4));
        }
        if (str5 != null && !TextUtils.isEmpty(str5.trim())) {
            arrayList.add(new BasicNameValuePair("job", str5));
        }
        if (str6 != null && !TextUtils.isEmpty(str6)) {
            arrayList.add(new BasicNameValuePair("images", str6));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Data_loadImage(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.DATA_LOADIMAGE));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("isHead", str2));
        arrayList.add(new BasicNameValuePair("ext", str3));
        arrayList.add(new BasicNameValuePair("base64", str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void New_TouSu_Or_Jiucuo(String str, String str2, String str3, String str4, String str5, String str6, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEW_TOUSU_ACTION));
        arrayList.add(new BasicNameValuePair("userId", str + ""));
        arrayList.add(new BasicNameValuePair("isComplaint", str2));
        arrayList.add(new BasicNameValuePair("targetCode", str3 + ""));
        arrayList.add(new BasicNameValuePair("targetName", str4 + ""));
        arrayList.add(new BasicNameValuePair("complaintContent", str5 + ""));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        if (str6 != null && !TextUtils.isEmpty(str6)) {
            arrayList.add(new BasicNameValuePair("images", str6));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void TouSu_Market(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.MARKET_TOUSU));
        arrayList.add(new BasicNameValuePair("uid", str + ""));
        arrayList.add(new BasicNameValuePair(UrlUtil.INTENT_MID, str2));
        arrayList.add(new BasicNameValuePair("content", str3 + ""));
        if (str4 != null && !TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("images", str4));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void WeiShoulu_Tousu(String str, String str2, String str3, String str4, String str5, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.WEISHOULU_TOUSU));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("isComplaint", "0"));
        if (str2 != null && !TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("targetSite", str2));
        }
        arrayList.add(new BasicNameValuePair("targetName", str3));
        arrayList.add(new BasicNameValuePair("complaintContent", str4));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        if (str5 != null && !TextUtils.isEmpty(str5)) {
            arrayList.add(new BasicNameValuePair("images", str5));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_Yaoqing_Sum(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.CANJIA_YAOQING));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_NewFabu(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.ZUIXINFABU));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetPingji_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GET_PINGJI_BANGDAN));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Get_My_Yaoqing(Handler handler, String str, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.MY_YAOQING));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static String Bound_data(String str, String str2, String str3) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("name", str2);
            jSONObject.put("url", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jSONArray.put(jSONObject);
        return jSONArray.toString().trim();
    }

    public static String Bound_Listdata(List<String> list, List<String> list2) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list2.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", list2.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString().trim();
    }

    public static String Prouctlist_Data(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jSONArray.put(list.get(i));
        }
        return jSONArray.toString().trim().replaceAll("\"", "\\\\\"");
    }

    public static String TouSu_Jishi(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "jpg");
                jSONObject.put("name", new Date().getTime() + ".jpg");
                jSONObject.put("url", list.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString().trim();
    }

    public static void GetLiuLanNewsList(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("pageindex", "1"));
        arrayList.add(new BasicNameValuePair("pagesize", "100"));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.LIULAN_NEWS_LIST);
    }

    public static void GetBank(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetBank));
        arrayList.add(new BasicNameValuePair("number", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetTradingAccount(Handler handler, int i, String str, String str2, String str3, String str4) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTradingAccount));
        arrayList.add(new BasicNameValuePair("UserId", str));
        arrayList.add(new BasicNameValuePair("TraderCode", str2));
        arrayList.add(new BasicNameValuePair("TargetCode", str3));
        arrayList.add(new BasicNameValuePair("OpenAccountType", str4));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void SaveBasicInfo(Handler handler, int i, String str, int i2, String str2, String str3, String str4, String str5, String str6) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SaveBasicInfo));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("saveType", i2 + ""));
        arrayList.add(new BasicNameValuePair("step", "1"));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("lastName", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("firstName", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("idNumber", str4));
        }
        if (!TextUtils.isEmpty(str5)) {
            arrayList.add(new BasicNameValuePair("eMail", str5));
        }
        if (!TextUtils.isEmpty(str6)) {
            arrayList.add(new BasicNameValuePair("phoneNumber", str6));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void SaveIdentity(Handler handler, int i, String str, int i2, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SaveIdentity));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("saveType", i2 + ""));
        arrayList.add(new BasicNameValuePair("step", "2"));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("idFrontImage", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("idBackImage", str3));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void SaveBankCard(Handler handler, int i, String str, int i2, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SaveBankCard));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("saveType", i2 + ""));
        arrayList.add(new BasicNameValuePair("step", "3"));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("bankCardNo", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("openBank", str3));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void SaveAddress(Handler handler, int i, String str, int i2, String str2, String str3, String str4, String str5, String str6, String str7) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.SaveAddress));
        arrayList.add(new BasicNameValuePair("userId", str));
        arrayList.add(new BasicNameValuePair("saveType", i2 + ""));
        arrayList.add(new BasicNameValuePair("step", "4"));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("TraderCode", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("provinceCode", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("cityCode", str4));
        }
        if (!TextUtils.isEmpty(str5)) {
            arrayList.add(new BasicNameValuePair("countyCode", str5));
        }
        if (!TextUtils.isEmpty(str6)) {
            arrayList.add(new BasicNameValuePair("detailedAddress", str6));
        }
        if (!TextUtils.isEmpty(str7)) {
            arrayList.add(new BasicNameValuePair("zCode", str7));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetHXSpreads(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetHXSpreads));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetHXMarket(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetHXMarket));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetHXSpreadDetails(Handler handler, int i, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetHXSpreadDetails));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("name", str2));
        arrayList.add(new BasicNameValuePair("type", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetPopularVisits(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetPopularVisits));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetMyBookShopList(final Handler handler, final int i, String str, String str2, String str3, final int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetOrders));
        arrayList.add(new BasicNameValuePair("uid", str));
        arrayList.add(new BasicNameValuePair("type", str2));
        arrayList.add(new BasicNameValuePair("status", str3));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        if (i2 > 0) {
            arrayList.add(new BasicNameValuePair("index", i2 + ""));
            arrayList.add(new BasicNameValuePair("size", i3 + ""));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        final Message message = new Message();
        OkHttpClientManager.getAsyn(UrlUtil.TEST_URL, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
//                Message message = message;
                message.what = -i;
                message.obj = iOException.getMessage();
                Message message2 = message;
                message2.arg2 = i2;
                handler.sendMessage(message2);
            }

            public void onResponse(String str) {
//                Message message = message;
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                message.arg2 = i2;
                handler.sendMessage(message);
            }
        }, arrayList);
    }

    public static void GetScanPayList(final Handler handler, final int i, String str, String str2, final int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetOrders));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserId(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("type", str));
        arrayList.add(new BasicNameValuePair("status", str2));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        if (i2 > 0) {
            arrayList.add(new BasicNameValuePair("index", i2 + ""));
            arrayList.add(new BasicNameValuePair("size", i3 + ""));
        }
        arrayList.add(new BasicNameValuePair("is_express", "1"));
        arrayList.add(new BasicNameValuePair(IjkMediaMeta.IJKM_KEY_LANGUAGE, BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_GUONEI)));
        final Message message = new Message();
        OkHttpClientManager.getAsyn(UrlUtil.TEST_URL_GUONEI, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
//                Message message = message;
                message.what = -i;
                message.obj = iOException.getMessage();
                Message message2 = message;
                message2.arg2 = i2;
                handler.sendMessage(message2);
            }

            public void onResponse(String str) {
//                Message message = message;
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                message.arg2 = i2;
                handler.sendMessage(message);
            }
        }, arrayList);
    }

    public static void GetMyBookShopDetail(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "GetOrderDetail"));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetMyBookShopKuaidi(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetOrderLogistics));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetHomeAds(Handler handler, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetHomeAds_GUO_NEI));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion() + ""));
        arrayList.add(new BasicNameValuePair("type", i2 + ""));
        if (AboutController.getAppThreeDayLock()) {
            arrayList.add(new BasicNameValuePair("pattern", "2"));
        } else {
            arrayList.add(new BasicNameValuePair("pattern", "1"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("app", "7"));
        arrayList.add(new BasicNameValuePair("country", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "implicit"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void GetGJHomeAd(Handler handler, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetGJHomeAd));
        if (AboutController.getAppThreeDayLock()) {
            arrayList.add(new BasicNameValuePair("pattern", "2"));
        } else {
            arrayList.add(new BasicNameValuePair("pattern", "1"));
        }
        arrayList.add(new BasicNameValuePair("appType", UrlUtil.TIJIAO_JIUCUO));
        arrayList.add(new BasicNameValuePair("adType", i2 + ""));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void AddTraderAdStatistics(Handler handler, int i, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.AddTraderAdStatistics));
        if (AboutController.getAppThreeDayLock()) {
            arrayList.add(new BasicNameValuePair("sandbox", "1"));
        } else {
            arrayList.add(new BasicNameValuePair("sandbox", "0"));
        }
        arrayList.add(new BasicNameValuePair("modal", "1"));
        arrayList.add(new BasicNameValuePair("equipId", BasicUtils.getIMEI(MyApplication.getInstance())));
        String newBDUserId = UserController.getNewBDUserId(MyApplication.getInstance());
        if (!UserController.isNewUserLogin(MyApplication.getInstance()) || TextUtils.isEmpty(newBDUserId)) {
            arrayList.add(new BasicNameValuePair("userid", "0"));
        } else {
            arrayList.add(new BasicNameValuePair("userid", newBDUserId));
        }
        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "3"));
        arrayList.add(new BasicNameValuePair("apptype", "1"));
        String GetIp = BasicUtils.GetIp(MyApplication.getContext());
        if (TextUtils.isEmpty(GetIp)) {
            GetIp = "";
        }
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, GetIp));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("equipinfo", "Android;手机型号" + Build.MODEL + ";手机厂商" + Build.MANUFACTURER));
        arrayList.add(new BasicNameValuePair("country", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("type", str3));
        if (TextUtils.isEmpty(str2)) {
            str2 = "-";
        }
        arrayList.add(new BasicNameValuePair("target", str2));
        arrayList.add(new BasicNameValuePair("spots", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void Record_UserData(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        if (UserController.isNewUserLogin(MyApplication.getContext())) {
            arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserId(MyApplication.getContext()) + ""));
        } else {
            arrayList.add(new BasicNameValuePair("uid", "0"));
        }
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "1"));
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, BasicUtils.getIPAddress(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("udid", BasicUtils.getIMEI(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("equipment", "Android;手机型号" + Build.MODEL + ";手机厂商" + Build.MANUFACTURER));
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("");
        arrayList.add(new BasicNameValuePair("type", sb.toString()));
        arrayList.add(new BasicNameValuePair("code", str2 + ""));
        arrayList.add(new BasicNameValuePair("spots", str3 + ""));
        arrayList.add(new BasicNameValuePair("project", "7"));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersion()));
        arrayList.add(new BasicNameValuePair("country", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(IjkMediaMeta.IJKM_KEY_LANGUAGE, BasicUtils.GetLanguageString(MyApplication.getContext())));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.DATA_SHOUJI_TEST);
    }

    public static void GetSiheyi_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.NEW_PAIHANG_FUNCTION));
        arrayList.add(new BasicNameValuePair("countryCode", BasicUtils.GetCountryString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("keyCode", str));
        arrayList.add(new BasicNameValuePair(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void api_general_advertise(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("type", "22"));
        if (AboutController.getAppThreeDayLock()) {
            arrayList.add(new BasicNameValuePair("ptn", "2"));
        } else {
            arrayList.add(new BasicNameValuePair("ptn", "1"));
        }
        arrayList.add(new BasicNameValuePair("app", "7"));
        arrayList.add(new BasicNameValuePair("keyCode", TIANYANPAIHANG));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
            arrayList.add(new BasicNameValuePair("country", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
            arrayList.add(new BasicNameValuePair("country", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.EXPOSURE_AccessKey));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/api/general/advertise");
    }

    public static void New_Get_BaoGuang_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("traderCode", str));
        if (!TextUtils.isEmpty(UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId())) {
            arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId()));
        }
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.EXPOSURE_AccessKey));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/Forum/topic/trader/list");
    }

    public static void getExposureCateData(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.EXPOSURE_AccessKey));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/Forum/topic/categories");
    }

    public static void GetTraderScore(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderScore));
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY_GUONEI));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_SECRET_GUONEI)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL_GUONEI);
    }

    public static void getSandboxTimeConfig(Handler handler, int i) {
        new OkHttp().OkHttpGetMethod((List<NameValuePair>) null, handler, i, UrlUtil.HTTP_SAND_BOX_TIME_CONFIG);
    }

    public static void GetGJSweep(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetGJSweep));
        arrayList.add(new BasicNameValuePair("code", "82100025"));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void CloudHuiChuan_Data(String str, String str2, String str3, String str4, String str5, String str6, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", "PayCallback"));
        arrayList.add(new BasicNameValuePair("oid", str));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId() + ""));
        arrayList.add(new BasicNameValuePair("type", str2));
        arrayList.add(new BasicNameValuePair("pyt", str3));
        arrayList.add(new BasicNameValuePair(TtmlNode.ATTR_TTS_ORIGIN, "22"));
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("trade_no", str4));
        }
        arrayList.add(new BasicNameValuePair("token", str5 + ""));
        arrayList.add(new BasicNameValuePair(Constants.URL_MEDIA_SOURCE, str6 + ""));
        arrayList.add(new BasicNameValuePair("ppl", "7"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void AuthActiceDomain(Handler handler, int i, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.AuthActiceDomain));
        arrayList.add(new BasicNameValuePair("url", str));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("apptype", "3"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, UrlUtil.TEST_URL);
    }

    public static void AddTraderSketchStatistics(final Handler handler, final int i, String str, String str2, final int i2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.AddTraderSketchStatistics));
        if (AboutController.getAppThreeDayLock()) {
            arrayList.add(new BasicNameValuePair("sandbox", "1"));
        } else {
            arrayList.add(new BasicNameValuePair("sandbox", "0"));
        }
        arrayList.add(new BasicNameValuePair("equipId", BasicUtils.getIMEI(MyApplication.getInstance())));
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        arrayList.add(new BasicNameValuePair("userid", str));
        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "3"));
        arrayList.add(new BasicNameValuePair("apptype", "1"));
        String iPAddress = BasicUtils.getIPAddress(MyApplication.getContext());
        if (TextUtils.isEmpty(iPAddress)) {
            iPAddress = "";
        }
        arrayList.add(new BasicNameValuePair(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, iPAddress));
        arrayList.add(new BasicNameValuePair("ver", AboutController.getAppVersion(MyApplication.getInstance())));
        arrayList.add(new BasicNameValuePair("equipinfo", "Android;手机型号" + Build.MODEL + ";手机厂商" + Build.MANUFACTURER));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("country", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("country", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair(HwIDConstant.Req_access_token_parm.LANGUAGE_LABEL, LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("type", "1"));
        arrayList.add(new BasicNameValuePair("code", str2));
        arrayList.add(new BasicNameValuePair("spots", i2 + ""));
        try {
            if (str3.contains(":\\\\")) {
                str3 = str3.replace(":\\\\", "%3A%5C%5C");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "0";
        }
        arrayList.add(new BasicNameValuePair("url", str3));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", UrlUtil.TEST_Secret)));
        final Message message = new Message();
        OkHttpClientManager.postAsyn(UrlUtil.TEST_URL, (OkHttpClientManager.StringCallback) new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
//                Message message = message;
                message.what = -i;
                message.obj = iOException.getMessage();
                Message message2 = message;
                message2.arg2 = i2;
                handler.sendMessage(message2);
            }

            public void onResponse(String str) {
//                Message message = message;
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                message.arg2 = i2;
                handler.sendMessage(message);
            }
        }, (List<NameValuePair>) arrayList);
    }

    public static void GetTraderTips(final Handler handler, final int i, final int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", UrlUtil.GetTraderTips));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("traderCode", str));
        arrayList.add(new BasicNameValuePair("type", i2 + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        final Message message = new Message();
        OkHttpClientManager.getAsyn(UrlUtil.TEST_URL, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
//                Message message = message;
                message.what = -i;
                message.obj = iOException.getMessage();
                Message message2 = message;
                message2.arg2 = i2;
                handler.sendMessage(message2);
            }

            public void onResponse(String str) {
//                Message message = message;
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                message.arg2 = i2;
                handler.sendMessage(message);
            }
        }, arrayList);
    }

    public static void GetTraderSubData(final Handler handler, final int i, final int i2, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("Action", str2));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("countryCode", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("countryCode", "156"));
        }
        String GetLanguageString = BasicUtils.GetLanguageString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetLanguageString)) {
            arrayList.add(new BasicNameValuePair("languageCode", GetLanguageString));
        } else {
            arrayList.add(new BasicNameValuePair("languageCode", LanguageType.LANGUAGE_EN_LOCA1));
        }
        arrayList.add(new BasicNameValuePair("traderCode", str));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", UrlUtil.TEST_Secret)));
        final Message message = new Message();
        OkHttpClientManager.getAsyn(UrlUtil.TEST_URL, new OkHttpClientManager.StringCallback() {
            public void onFailure(Request request, IOException iOException) {
                iOException.printStackTrace();
//                Message message = message;
                message.what = -i;
                message.obj = iOException.getMessage();
                Message message2 = message;
                message2.arg2 = i2;
                handler.sendMessage(message2);
            }

            public void onResponse(String str) {
//                Message message = message;
                message.obj = str;
                message.what = i;
                message.arg1 = 200;
                message.arg2 = i2;
                handler.sendMessage(message);
            }
        }, arrayList);
    }

    public static void GetBaoguang_Email_Data(Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserId(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "2"));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.XINYONG_TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/DealersPdf/api/Dealers");
    }

    public static void GetBaoguang_List_Data(String str, String str2, String str3, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("UserId", UserController.getNewBDUserId(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("Cursor", str));
        arrayList.add(new BasicNameValuePair("Page", str2));
        arrayList.add(new BasicNameValuePair("PageSize", str3));
        arrayList.add(new BasicNameValuePair("Order", "1"));
        arrayList.add(new BasicNameValuePair("SortBy", "1"));
        arrayList.add(new BasicNameValuePair("Fields", "1"));
        arrayList.add(new BasicNameValuePair("Platform", "2"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.XINYONG_TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/DealersPdf/api/Orders");
    }

    public static void GetBaoguang_Detail_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("orderId", str));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.XINYONG_TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/DealersPdf/api/Order");
    }

    public static void GetBaoguang_Prodoct_Data(String str, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("edition", Integer.parseInt(MyBasic.getAppInfo(MyApplication.getContext())) + ""));
        arrayList.add(new BasicNameValuePair("languageCode", BasicUtils.GetLanguageString(MyApplication.getContext()) + ""));
        arrayList.add(new BasicNameValuePair(JThirdPlatFormInterface.KEY_PLATFORM, "2"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.XINYONG_TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", UUID.randomUUID().toString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "GET", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpGetMethod(arrayList, handler, i, "https://oce.fx696.com:5200/DealersPdf/api/Product/");
    }

    public static void Xinyong_Baogao(String str, String str2, String str3, String str4, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("code", str));
        arrayList.add(new BasicNameValuePair("uid", UserController.getNewBDUserId(MyApplication.getContext())));
        arrayList.add(new BasicNameValuePair("email", str2 + ""));
        arrayList.add(new BasicNameValuePair("orderId", str3 + ""));
        arrayList.add(new BasicNameValuePair(IjkMediaMeta.IJKM_KEY_LANGUAGE, str4 + ""));
        String GetCountryString = BasicUtils.GetCountryString(MyApplication.getInstance());
        if (!TextUtils.isEmpty(GetCountryString)) {
            arrayList.add(new BasicNameValuePair("co", GetCountryString));
        } else {
            arrayList.add(new BasicNameValuePair("co", "156"));
        }
        arrayList.add(new BasicNameValuePair(TtmlNode.TAG_P, "2"));
        arrayList.add(new BasicNameValuePair("d", "1"));
        arrayList.add(new BasicNameValuePair("Format", "JSON"));
        arrayList.add(new BasicNameValuePair("Pattern", "encryption"));
        arrayList.add(new BasicNameValuePair("AccessKey", UrlUtil.XINYONG_TEST_KEY));
        arrayList.add(new BasicNameValuePair("Random", OkHttpUtils.getRandomString()));
        arrayList.add(new BasicNameValuePair("Timestamp", OkHttpUtils.local2UTC()));
        arrayList.add(new BasicNameValuePair("Token", OkHttpUtils.getToken(arrayList, "POST", "cGFzc3BvcnRfYXBpX2FuZHJvaWQtMzNfMzZjMGV")));
        new OkHttp().OkHttpPostMethod(arrayList, handler, i, "https://oce.fx696.com:5200/DealersPdf/api/Dealers");
    }

    public static void api_Permission_Login(final Handler handler, final int i) {
        HashMap hashMap = new HashMap();
        if (UserController.isNewUserLogin(MyApplication.getContext())) {
            hashMap.put("username", UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId());
            hashMap.put("password", BasicUtils.encode(UserController.getNewBDUserInfo(MyApplication.getContext()).getUserId()));
        } else {
            hashMap.put("username", "gsw");
            hashMap.put("password", BasicUtils.encode("gsw"));
        }
        IMOkHttpRequestManager.getInstance().getAccessTokenByASyn("https://oce.fx696.com:59105/api/Permission/Login", hashMap, new IMOkHttpRequestManager.ReqCallBack<String>() {
            public void onSuccess(String str) {
                Message message = new Message();
                message.obj = str;
                message.what = i;
//                Handler handler = handler;
                if (handler != null) {
                    handler.sendMessage(message);
                }
            }

            public void onFailed(String str) {
                Message message = new Message();
                message.obj = str;
                message.what = -i;
//                Handler handler = handler;
                if (handler != null) {
                    handler.sendMessage(message);
                }
            }
        });
    }
}
