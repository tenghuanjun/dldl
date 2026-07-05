package com.sy37sdk.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.TelephonyInfoUtils;
import com.sy37sdk.core.INewUrl;
import com.sy37sdk.widget.AbstractView;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Util {
    private static final String ANDROID = "android";
    private static final String APPKEY = "appkey";
    private static final String AUTOISSAVE = "auto_Issave";
    private static final String AUTONAME = "auto_name";
    private static final String AUTOPASSWORD = "auto_pwd";
    private static final String AUTOSTATE = "auto_state";
    private static final String BBS_URL = "bbsurl";
    private static final String CHANGE_ID = "ci";
    private static String CODE_FLOAT_VIEW_PERMISSION_TIPS = "float_view_permission_tips";
    private static final String CODE_LOGIN = "login_cut";
    private static final String CODE_PAY = "pay_cut";
    private static final String DEV = "dev";
    private static final String DEV_IMEI = "dev_imei";
    private static final String DEV_MAC = "dev_mac";
    private static final String GET_VERIFY_CODE_LAST_TIME = "time_last_get_verify_code";
    private static final String GID = "gid";
    private static final String HARMONY = "harmony";
    private static final String LOGIN_NURL = "login_nurl";
    private static final String LOGIN_TYPE = "login_type";
    public static boolean LOG_DEBUG = Log.isLoggable("sysdk.debug.log", 3);
    private static final String OAUTH_AccessToken = "oauth_accesstoken";
    private static final String OAUTH_NickName = "oauth_nickname";
    private static final String OAUTH_OpenID = "oauth_openid";
    private static final String OAUTH_RefreshToken = "oauth_refreshtoken";
    private static final String PASSWORD = "pd";
    private static final String PAY_NEW = "pay_url_new";
    private static final String PAY_WAY = "pay_way";
    private static final String PID = "pid";
    private static final String REFER = "refer";
    private static final String SQ_LESS_FUNC_SDK = "scode";
    private static final String SQ_PREFS = "sq_prefs";
    private static final String TOKEN = "token";
    private static String USERALIAS = "useralias";
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String USERNICK = "usernick";
    private static final String USER_BIND_EMAIL = "bemail";
    private static final String USER_BIND_PHONE = "bphone";
    private static HashMap<String, String> roleInfos;
    private static boolean sLogPrintEnable;

    public static void setFloatViewPermissionTipsNum(Context context, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putInt(CODE_FLOAT_VIEW_PERMISSION_TIPS, i);
        editorEdit.commit();
    }

    public static int getFloatViewPermissionTipsNum(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getInt(CODE_FLOAT_VIEW_PERMISSION_TIPS, -1);
    }

    @Deprecated
    public static void setCodeOfLogin(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(CODE_LOGIN, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getCodeOfLogin(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(CODE_LOGIN, "0");
    }

    @Deprecated
    public static void setIsLessFunctionSDK(Context context, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putBoolean("scode", z);
        editorEdit.commit();
    }

    @Deprecated
    public static boolean getIsLessFunctionSDK(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getBoolean("scode", false);
    }

    @Deprecated
    public static boolean getIsSpecialSDK(Context context) {
        return !TextUtils.isEmpty(getCodeOfLogin(context)) && "1".equals(getCodeOfLogin(context));
    }

    public static void setVerifyCodeLastTime(Context context, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putLong(GET_VERIFY_CODE_LAST_TIME, j);
        editorEdit.commit();
    }

    public static long getVerifyCodeLastTime(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getLong(GET_VERIFY_CODE_LAST_TIME, 0L);
    }

    public static String getNewPayUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(PAY_NEW, INewUrl.PAY);
    }

    public static void setCodeOfPay(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(CODE_PAY, str);
        editorEdit.commit();
    }

    public static String getCodeOfPay(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(CODE_PAY, "0");
    }

    public static void setUserBindEmail(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(USER_BIND_EMAIL, str);
        editorEdit.commit();
    }

    public static String getUserBindEmail(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(USER_BIND_EMAIL, "");
    }

    public static void setUserBindPhone(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(USER_BIND_PHONE, str);
        editorEdit.commit();
    }

    public static String getUserBindPhone(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(USER_BIND_PHONE, "");
    }

    @Deprecated
    public static String getDevid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("dev", getLocalDev(context));
    }

    public static void setGameID(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("gid", str);
        editorEdit.commit();
    }

    public static String getGameID(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("gid", "");
    }

    public static void setPaternerID(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("pid", str);
        editorEdit.commit();
    }

    public static String getPaternerID(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("pid", "");
    }

    public static void setRefer(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("refer", str);
        editorEdit.commit();
    }

    public static String getRefer(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("refer", "");
    }

    public static void setAppKey(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("appkey", str);
        editorEdit.commit();
    }

    public static String getAppKey(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("appkey", "");
    }

    public static void setUserid(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("userid", str);
        editorEdit.commit();
    }

    public static String getUserid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("userid", "");
    }

    public static void setUsername(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("username", str);
        editorEdit.commit();
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("username", "");
    }

    public static void setUsernick(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(USERNICK, str);
        editorEdit.commit();
    }

    public static String getUsernick(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(USERNICK, "");
    }

    public static void setPassword(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(PASSWORD, str);
        editorEdit.commit();
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(PASSWORD, "");
    }

    public static void setToken(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("token", str);
        editorEdit.commit();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("token", "");
    }

    public static void setBBS(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(BBS_URL, str);
        editorEdit.commit();
    }

    public static void setAutoName(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(AUTONAME, str);
        editorEdit.commit();
    }

    public static String getAutoName(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(AUTONAME, "");
    }

    public static void setAutoPassword(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(AUTOPASSWORD, str);
        editorEdit.commit();
    }

    public static String getAutoPassword(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(AUTOPASSWORD, "");
    }

    public static void setAutoState(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(AUTOSTATE, str);
        editorEdit.commit();
    }

    public static String getAutoState(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(AUTOSTATE, "0");
    }

    public static void setAutoIssave(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(AUTOISSAVE, str);
        editorEdit.commit();
    }

    public static String getAutoIssave(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(AUTOISSAVE, "0");
    }

    public static void setLoginType(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("login_type", str);
        editorEdit.commit();
    }

    public static String getLoginType(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("login_type", "sq");
    }

    public static void setOauthAccessToken(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(OAUTH_AccessToken, str);
        editorEdit.commit();
    }

    public static String getOauthAccessToken(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(OAUTH_AccessToken, "");
    }

    public static void setOauthRefreshToken(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(OAUTH_RefreshToken, str);
        editorEdit.commit();
    }

    public static String getOauthRefreshToken(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(OAUTH_RefreshToken, "");
    }

    public static void setOauthOpenID(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(OAUTH_OpenID, str);
        editorEdit.commit();
    }

    public static String getOauthOpenID(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(OAUTH_OpenID, "");
    }

    public static void setOauthNickName(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(OAUTH_NickName, str);
        editorEdit.commit();
    }

    public static String getOauthNickName(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(OAUTH_NickName, "");
    }

    @Deprecated
    public static void setRoleInfos(HashMap<String, String> map) {
        roleInfos = map;
    }

    @Deprecated
    public static HashMap<String, String> getRoleInfos() {
        return roleInfos;
    }

    public static void setAccountAlias(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(USERALIAS, str);
        editorEdit.commit();
    }

    public static String getAccountAlias(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(USERALIAS, "");
    }

    public static void putYear(Context context, int i) {
        context.getSharedPreferences(SQ_PREFS, 0).edit().putInt("year", i).commit();
    }

    public static int getYear(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getInt("year", 1990);
    }

    public static void putMonth(Context context, int i) {
        context.getSharedPreferences(SQ_PREFS, 0).edit().putInt("month", i).commit();
    }

    public static int getMonth(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getInt("month", 3);
    }

    public static void putMonthOfday(Context context, int i) {
        context.getSharedPreferences(SQ_PREFS, 0).edit().putInt("monthOfday", i).commit();
    }

    public static int getMonthOfday(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getInt("monthOfday", 7);
    }

    public static String getBBS(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(BBS_URL, "http://bbs.m.37.com");
    }

    public static void setChangeId(Context context, String str) {
        if (!"".equals(str)) {
            str = ZipString.json2ZipString(str);
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(CHANGE_ID, str);
        editorEdit.commit();
    }

    public static String getChangeId(Context context) {
        String string = context.getSharedPreferences(SQ_PREFS, 0).getString(CHANGE_ID, "");
        return !"".equals(string) ? ZipString.zipString2Json(string) : string;
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            if (bundle.get(str) instanceof String) {
                if (z) {
                    z = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(str) + SimpleComparison.EQUAL_TO_OPERATION + URLEncoder.encode(bundle.getString(str)));
            }
        }
        return sb.toString();
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split(SimpleComparison.EQUAL_TO_OPERATION);
                if (strArrSplit.length == 2) {
                    bundle.putString(URLDecoder.decode(strArrSplit[0]), URLDecoder.decode(strArrSplit[1]));
                }
            }
        }
        return bundle;
    }

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str.replace("fgwanhttp", IDataSource.SCHEME_HTTP_TAG));
            Bundle bundleDecodeUrl = decodeUrl(url.getQuery());
            bundleDecodeUrl.putAll(decodeUrl(url.getRef()));
            return bundleDecodeUrl;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    @Deprecated
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static String Md5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }

    public static String openUrl(String str, String str2, Bundle bundle) throws IOException {
        if (str2.equalsIgnoreCase("GET")) {
            str = str + "?" + encodeUrl(bundle);
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " FGWANAndroidSDK");
        if (!str2.equalsIgnoreCase("GET")) {
            Bundle bundle2 = new Bundle();
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof byte[]) {
                    bundle2.putByteArray(str3, (byte[]) obj);
                }
            }
            if (!bundle.containsKey("method")) {
                bundle.putString("method", str2);
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=ArYfORhtTP3i2ndDfv2rTHiSisAbouNdEefj3q2f");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.connect();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(("--ArYfORhtTP3i2ndDfv2rTHiSisAbouNdEefj3q2f\r\n").getBytes());
            bufferedOutputStream.write(encodePostBody(bundle, "ArYfORhtTP3i2ndDfv2rTHiSisAbouNdEefj3q2f").getBytes());
            bufferedOutputStream.write(("\r\n--ArYfORhtTP3i2ndDfv2rTHiSisAbouNdEefj3q2f\r\n").getBytes());
            if (!bundle2.isEmpty()) {
                for (String str4 : bundle2.keySet()) {
                    bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + str4 + "\"\r\n").getBytes());
                    StringBuilder sb = new StringBuilder();
                    sb.append("Content-Type: content/unknown");
                    sb.append("\r\n");
                    sb.append("\r\n");
                    bufferedOutputStream.write(sb.toString().getBytes());
                    bufferedOutputStream.write(bundle2.getByteArray(str4));
                    bufferedOutputStream.write(("\r\n--ArYfORhtTP3i2ndDfv2rTHiSisAbouNdEefj3q2f\r\n").getBytes());
                }
            }
            bufferedOutputStream.flush();
        }
        try {
            return read(httpURLConnection.getInputStream());
        } catch (FileNotFoundException unused) {
            return read(httpURLConnection.getErrorStream());
        }
    }

    private static String read(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1000);
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
            } else {
                inputStream.close();
                return sb.toString();
            }
        }
    }

    public static String encodePostBody(Bundle bundle, String str) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : bundle.keySet()) {
            Object obj = bundle.get(str2);
            if (obj instanceof String) {
                sb.append("Content-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n" + ((String) obj));
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\r\n--");
                sb2.append(str);
                sb2.append("\r\n");
                sb.append(sb2.toString());
            }
        }
        return sb.toString();
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getWH(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x + "x" + point.y;
    }

    public static String getIMEI(Context context) {
        return TelephonyInfoUtils.getDeviceId(context);
    }

    public static String getSIM(Context context) {
        return TelephonyInfoUtils.getSimSerialNumber(context);
    }

    public static String getIMSI(Context context) {
        return TelephonyInfoUtils.getSubscriberId(context);
    }

    public static String getMac(Context context) {
        String macAddress = SensitiveInfoManager.getInstance().getMacAddress(context);
        Pattern patternCompile = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？]");
        if (macAddress == null) {
            macAddress = "";
        }
        Matcher matcher = patternCompile.matcher(macAddress);
        return matcher != null ? matcher.replaceAll("").trim() : "";
    }

    public static String getSerial(Context context) {
        String imei = getIMEI(context);
        String imsi = getIMSI(context);
        if (!imsi.equals("")) {
            return imei + imsi;
        }
        String mac = getMac(context);
        if (!mac.equals("")) {
            return imei + mac;
        }
        String sim = getSIM(context);
        if (sim.equals("")) {
            return "";
        }
        return imei + sim;
    }

    public static String getMAC(Context context) {
        String macAddress = SensitiveInfoManager.getInstance().getMacAddress(context);
        Pattern patternCompile = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？]");
        if (macAddress == null) {
            macAddress = "";
        }
        Matcher matcher = patternCompile.matcher(macAddress);
        if (!getIMEI(context).equals("")) {
            return getIMEI(context);
        }
        if (!getIMSI(context).equals("")) {
            return getIMSI(context);
        }
        if (getSIM(context).equals("")) {
            return !matcher.replaceAll("").equals("") ? matcher.replaceAll("").trim() : "";
        }
        return getSIM(context);
    }

    public static String getOsVersion() {
        return Build.VERSION.SDK;
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0.0";
        }
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        int type = activeNetworkInfo.getType();
        if (type != 0) {
            return type == 1 ? "wifi" : "";
        }
        String extraInfo = activeNetworkInfo.getExtraInfo();
        return (extraInfo == null || extraInfo.equals("")) ? "" : extraInfo.toLowerCase();
    }

    public static boolean isfirst(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("first_launch", 0);
        boolean z = sharedPreferences.getBoolean("first", true);
        if (z) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean("first", false);
            editorEdit.commit();
        }
        return z;
    }

    public static boolean copyString2System(Context context, String str, String str2) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT > 11) {
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("code", str.trim()));
            } else {
                ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(str);
            }
            ViewController.showToast(context, str2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getIdByName(String str, String str2, String str3, Context context) {
        try {
            return SqResUtils.getIdByName(str, str2, context);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    public static int getIdByNameHostFirst(String str, String str2, String str3, Context context) {
        try {
            return SqResUtils.getIdByNameHostFirst(str, str2, context);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
    }

    public static View getViewByIdName(AbstractView abstractView, String str) {
        try {
            return abstractView.findViewById(SqResUtils.getId(abstractView.getActivity(), str));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("找不到" + str + "资源");
            return null;
        }
    }

    public static View getViewByName(View view, String str) {
        return view.findViewById(getIdByName(str, SqTrackCommonKey.id, view.getContext()));
    }

    public static int getIdByName(String str, String str2, Context context) {
        return getIdByName(str, str2, context.getPackageName(), context);
    }

    public static void hideSystemKeyBoard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String gbEncoding(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str2 + "\\u" + Integer.toHexString(str.charAt(i));
        }
        return str2;
    }

    public static String encodingtoStr(String str) {
        Matcher matcher = Pattern.compile("(\\\\u(\\p{XDigit}{4}))").matcher(str);
        while (matcher.find()) {
            char c = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), c + "");
        }
        return str;
    }

    public static boolean checkAppInstalled(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return !packageManager.queryIntentActivities(packageManager.getLaunchIntentForPackage(str), 65536).isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setPackage(context.getPackageName());
        if (Build.VERSION.SDK_INT >= 23) {
            Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 131072).iterator();
            if (it.hasNext()) {
                ResolveInfo next = it.next();
                LogUtil.d("sqsdk", "[activtiy]" + next.activityInfo.name + " screenOrientation: " + next.activityInfo.screenOrientation);
                return next.activityInfo.screenOrientation == 1 || next.activityInfo.screenOrientation == 7 || next.activityInfo.screenOrientation == 9;
            }
        } else {
            Iterator<ResolveInfo> it2 = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
            if (it2.hasNext()) {
                ResolveInfo next2 = it2.next();
                LogUtil.d("sqsdk", "[activtiy]" + next2.activityInfo.name + " screenOrientation: " + next2.activityInfo.screenOrientation);
                return next2.activityInfo.screenOrientation == 1 || next2.activityInfo.screenOrientation == 7 || next2.activityInfo.screenOrientation == 9;
            }
        }
        return false;
    }

    public static boolean isSkipSQChangeAccountLogin(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return false;
            }
            String string = applicationInfo.metaData.getString("SQwanSkipSwitchLogin");
            System.out.println("是否跳过切换账号登录框：" + string);
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return "yes".equals(string);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getLocalDev(Context context) {
        return Md5(getDevMac(context) + getDevImei(context) + "-:&d4@zXqm-pLgW").toLowerCase();
    }

    public static void setDevMac(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(DEV_MAC, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getDevMac(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(DEV_MAC, "");
    }

    public static void setDevImei(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(DEV_IMEI, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getDevImei(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(DEV_IMEI, "");
    }

    @Deprecated
    public static void setPayWay(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(PAY_WAY, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getPayWay(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(PAY_WAY, "");
    }

    public static void setNurl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(LOGIN_NURL, str);
        editorEdit.commit();
    }

    public static String getNurl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(LOGIN_NURL, "");
    }

    public static void setLogPrintEnable(boolean z) {
        sLogPrintEnable = z;
    }

    public static boolean isLogPrintEnable() {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        return sLogPrintEnable || LOG_DEBUG || (applicationContext != null && (applicationContext.getApplicationInfo().flags & 2) != 0);
    }

    public static int getWpixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.valueOf(displayScreenResolution.substring(0, displayScreenResolution.indexOf(Marker.ANY_MARKER))).intValue();
    }

    public static int getHpixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.valueOf(displayScreenResolution.substring(displayScreenResolution.indexOf(Marker.ANY_MARKER) + 1)).intValue();
    }

    private static String getDisplayScreenResolution(Context context) {
        int i;
        try {
            i = context.getResources().getDisplayMetrics().heightPixels;
            try {
                int i2 = context.getResources().getDisplayMetrics().widthPixels;
                LogUtil.d("Run2 Calibration  resolution:" + i2 + Marker.ANY_MARKER + i);
                return i2 + Marker.ANY_MARKER + i;
            } catch (Exception unused) {
                return 0 + Marker.ANY_MARKER + i;
            }
        } catch (Exception unused2) {
            i = 0;
        }
    }

    public static String getAppName(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return packageManager.getApplicationLabel(applicationInfo).toString();
    }

    public static String getLocaleLanguage() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage() + "-" + locale.getCountry();
    }

    public static String getOs() {
        return isHarmony() ? HARMONY : "android";
    }

    public static boolean isHarmony() {
        try {
            try {
                Class.forName("ohos.app.Application");
                return true;
            } catch (Throwable unused) {
                Class.forName("ohos.system.version.SystemVersion");
                return true;
            }
        } catch (Throwable unused2) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                Method method = cls.getMethod("getOsBrand", new Class[0]);
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null || classLoader.getParent() == null) {
                    return false;
                }
                return HARMONY.equals(method.invoke(cls, new Object[0]));
            } catch (Throwable unused3) {
                return false;
            }
        }
    }
}
