package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cookie.SerializableCookie;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Constant.kt */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\bJ\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020jJ\u000e\u0010k\u001a\u00020h2\u0006\u0010i\u001a\u00020jR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001a\u0010\u0019\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001a\u0010%\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\bR\u001a\u0010(\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R\u001a\u0010+\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0013\"\u0004\b-\u0010\u0015R\u001a\u0010.\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\bR\u001a\u00101\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001f\"\u0004\b3\u0010!R\u001a\u00104\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001f\"\u0004\b6\u0010!R\u001a\u00107\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0013\"\u0004\b9\u0010\u0015R\u001a\u0010:\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0006\"\u0004\b<\u0010\bR\u001a\u0010=\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0006\"\u0004\b?\u0010\bR\u001a\u0010@\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001f\"\u0004\bB\u0010!R\u001a\u0010C\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0006\"\u0004\bE\u0010\bR\u001a\u0010F\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0006\"\u0004\bH\u0010\bR\u001a\u0010I\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001f\"\u0004\bK\u0010!R\u001a\u0010L\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0006\"\u0004\bN\u0010\bR\u001a\u0010O\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0006\"\u0004\bQ\u0010\bR\u001a\u0010R\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u001f\"\u0004\bT\u0010!R\u001a\u0010U\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0006\"\u0004\bW\u0010\bR\u001a\u0010X\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u0006\"\u0004\bZ\u0010\bR\u001a\u0010[\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0006\"\u0004\b]\u0010\bR\u001a\u0010^\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0006\"\u0004\b`\u0010\bR\u001a\u0010a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u0006\"\u0004\bc\u0010\bR\u001a\u0010d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0006\"\u0004\bf\u0010\b¨\u0006l"}, d2 = {"Lcom/cy/yyjia/zhe28/util/Constant;", "", "()V", "GAME_ID", "", "getGAME_ID", "()Ljava/lang/String;", "setGAME_ID", "(Ljava/lang/String;)V", "JS_NAME", "getJS_NAME", "LOGIN_REQUEST_CODE", "", "LOGIN_SUCCESS_CODE", "androidId", "getAndroidId", "setAndroidId", "appTheme", "getAppTheme", "()I", "setAppTheme", "(I)V", "avatar", "getAvatar", "setAvatar", "bdvid", "getBdvid", "setBdvid", "cancellation", "", "getCancellation", "()Z", "setCancellation", "(Z)V", "channelUid", "getChannelUid", "setChannelUid", "cookieString", "getCookieString", "setCookieString", "deleteApk", "getDeleteApk", "setDeleteApk", "firstGameId", "getFirstGameId", "setFirstGameId", "formType", "getFormType", "setFormType", "hideCard", "getHideCard", "setHideCard", "hideTrade", "getHideTrade", "setHideTrade", "id", "getId", "setId", "imei", "getImei", "setImei", CacheEntity.KEY, "getKey", "setKey", "logged", "getLogged", "setLogged", "myId", "getMyId", "setMyId", "nickname", "getNickname", "setNickname", "noPin", "getNoPin", "setNoPin", "oaid", "getOaid", "setOaid", "qqAppId", "getQqAppId", "setQqAppId", "showMainAd", "getShowMainAd", "setShowMainAd", "token", "getToken", "setToken", "username", "getUsername", "setUsername", "vip", "getVip", "setVip", "wxAppId", "getWxAppId", "setWxAppId", "wxAppSecret", "getWxAppSecret", "setWxAppSecret", "wxLoginCode", "getWxLoginCode", "setWxLoginCode", "getDeviceImei", "", "context", "Landroid/content/Context;", "logout", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Constant {
    public static final int LOGIN_REQUEST_CODE = 6514;
    public static final int LOGIN_SUCCESS_CODE = 1001;
    private static int appTheme;
    private static boolean cancellation;
    private static boolean deleteApk;
    private static int firstGameId;
    private static boolean hideTrade;
    private static int id;
    private static boolean logged;
    private static boolean noPin;
    private static boolean showMainAd;
    public static final Constant INSTANCE = new Constant();
    private static final String JS_NAME = "BOX";
    private static String GAME_ID = "0";
    private static String wxAppId = "";
    private static String wxAppSecret = "";
    private static String qqAppId = "";
    private static String myId = "";
    private static String oaid = "";
    private static String imei = "";
    private static String androidId = "";
    private static String key = "";
    private static String username = "";
    private static String token = "";
    private static String channelUid = "";
    private static String bdvid = "";
    private static String formType = "";
    private static String cookieString = "";
    private static String wxLoginCode = "";
    private static String nickname = "";
    private static String avatar = "";
    private static String vip = "0";
    private static boolean hideCard = true;
    public static final int $stable = 8;

    private Constant() {
    }

    public final int getAppTheme() {
        return appTheme;
    }

    public final void setAppTheme(int i) {
        appTheme = i;
    }

    public final String getJS_NAME() {
        return JS_NAME;
    }

    public final int getFirstGameId() {
        return firstGameId;
    }

    public final void setFirstGameId(int i) {
        firstGameId = i;
    }

    public final String getGAME_ID() {
        return GAME_ID;
    }

    public final void setGAME_ID(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        GAME_ID = str;
    }

    public final String getWxAppId() {
        return wxAppId;
    }

    public final void setWxAppId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        wxAppId = str;
    }

    public final String getWxAppSecret() {
        return wxAppSecret;
    }

    public final void setWxAppSecret(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        wxAppSecret = str;
    }

    public final String getQqAppId() {
        return qqAppId;
    }

    public final void setQqAppId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        qqAppId = str;
    }

    public final String getMyId() {
        return myId;
    }

    public final void setMyId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        myId = str;
    }

    public final String getOaid() {
        return oaid;
    }

    public final void setOaid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        oaid = str;
    }

    public final String getImei() {
        return imei;
    }

    public final void setImei(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        imei = str;
    }

    public final String getAndroidId() {
        return androidId;
    }

    public final void setAndroidId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        androidId = str;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int i) {
        id = i;
    }

    public final String getKey() {
        return key;
    }

    public final void setKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        key = str;
    }

    public final boolean getLogged() {
        return logged;
    }

    public final void setLogged(boolean z) {
        logged = z;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        username = str;
    }

    public final String getToken() {
        return token;
    }

    public final void setToken(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        token = str;
    }

    public final String getChannelUid() {
        return channelUid;
    }

    public final void setChannelUid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        channelUid = str;
    }

    public final String getBdvid() {
        return bdvid;
    }

    public final void setBdvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        bdvid = str;
    }

    public final String getFormType() {
        return formType;
    }

    public final void setFormType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        formType = str;
    }

    public final boolean getNoPin() {
        return noPin;
    }

    public final void setNoPin(boolean z) {
        noPin = z;
    }

    public final String getCookieString() {
        return cookieString;
    }

    public final void setCookieString(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        cookieString = str;
    }

    public final boolean getHideTrade() {
        return hideTrade;
    }

    public final void setHideTrade(boolean z) {
        hideTrade = z;
    }

    public final String getWxLoginCode() {
        return wxLoginCode;
    }

    public final void setWxLoginCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        wxLoginCode = str;
    }

    public final boolean getDeleteApk() {
        return deleteApk;
    }

    public final void setDeleteApk(boolean z) {
        deleteApk = z;
    }

    public final boolean getShowMainAd() {
        return showMainAd;
    }

    public final void setShowMainAd(boolean z) {
        showMainAd = z;
    }

    public final String getNickname() {
        return nickname;
    }

    public final void setNickname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        nickname = str;
    }

    public final String getAvatar() {
        return avatar;
    }

    public final void setAvatar(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        avatar = str;
    }

    public final String getVip() {
        return vip;
    }

    public final void setVip(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        vip = str;
    }

    public final boolean getHideCard() {
        return hideCard;
    }

    public final void setHideCard(boolean z) {
        hideCard = z;
    }

    public final boolean getCancellation() {
        return cancellation;
    }

    public final void setCancellation(boolean z) {
        cancellation = z;
    }

    public final void logout(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.e("wancms", "退出登录");
        context.getContentResolver().delete(Uri.parse("content://com.cy.yyjia.zhe28.loginprovider/login"), null, null);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.flush();
        WebStorage.getInstance().deleteAllData();
        id = 0;
        username = "";
        token = "";
        cookieString = "";
        logged = false;
        cancellation = false;
        nickname = "";
        avatar = "";
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("user", 0).edit();
        editorEdit.putBoolean("logged", false);
        editorEdit.putString("token", "");
        editorEdit.putString(SerializableCookie.COOKIE, "");
        editorEdit.commit();
    }

    public final void getDeviceImei(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        androidId = string;
    }
}
