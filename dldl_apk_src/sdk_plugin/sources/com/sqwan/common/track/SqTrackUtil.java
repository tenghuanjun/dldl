package com.sqwan.common.track;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.bugless.util.DateUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.TelephonyInfoUtils;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqTrackUtil {
    private static final String APPKEY = "appkey";
    private static final String AUTOISSAVE = "auto_Issave";
    private static final String AUTONAME = "auto_name";
    private static final String AUTOPASSWORD = "auto_pwd";
    private static final String AUTOSTATE = "auto_state";
    private static final String BBS_URL = "bbsurl";
    private static final String CHANGE_ID = "ci";
    private static final String CODE_LOGIN = "login_cut";
    private static final String CODE_PAY = "pay_cut";
    private static final String DEV = "dev";
    private static final String DEV_IMEI = "dev_imei";
    private static final String DEV_MAC = "dev_mac";
    private static final String GET_VERIFY_CODE_LAST_TIME = "time_last_get_verify_code";
    private static final String GID = "gid";
    private static final String INFO_ROLEID = "drid";
    private static final String INFO_ROLELEVEL = "drlevel";
    private static final String INFO_ROLENAME = "drname";
    private static final String INFO_SERVERID = "dsid";
    private static final String INFO_SERVER_NAME = "serverName";
    private static final String INFO_VIP_LEVEL = "viplevel";
    public static final String LOGINED = "logined";
    private static final String LOGIN_NURL = "login_nurl";
    private static final String LOGIN_TYPE = "login_type";
    private static final String OAUTH_AccessToken = "oauth_accesstoken";
    private static final String OAUTH_NickName = "oauth_nickname";
    private static final String OAUTH_OpenID = "oauth_openid";
    private static final String OAUTH_RefreshToken = "oauth_refreshtoken";
    public static final String ORIGINAL_SVERSION = "original_sverion";
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

    public static void setCodeOfLogin(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(CODE_LOGIN, str);
        editorEdit.commit();
    }

    public static String getCodeOfLogin(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(CODE_LOGIN, "0");
    }

    public static void setIsLessFunctionSDK(Context context, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putBoolean("scode", z);
        editorEdit.commit();
    }

    public static boolean getIsLessFunctionSDK(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getBoolean("scode", false);
    }

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

    public static String getChannelId(Context context) {
        String refer = getRefer(context);
        if (!TextUtils.isEmpty(refer) && !refer.contains("sy")) {
            try {
                return getRefer(context).split("_")[2];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
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

    public static void setRoleInfos(HashMap<String, String> map) {
        roleInfos = map;
    }

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
            if (bundle != null) {
                for (String str3 : bundle.keySet()) {
                    Object obj = bundle.get(str3);
                    if (obj instanceof byte[]) {
                        bundle2.putByteArray(str3, (byte[]) obj);
                    }
                }
                if (!bundle.containsKey("method")) {
                    bundle.putString("method", str2);
                }
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

    public static int getIdByName(String str, String str2, String str3, Context context) {
        try {
            return SqResUtils.getIdByName(str, str2, context);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" TYPE:" + str2 + ",RES:" + str + " NOT FOUND!");
            return 0;
        }
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
        return context.getResources().getConfiguration().orientation == 1;
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

    public static void setPayWay(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(PAY_WAY, str);
        editorEdit.commit();
    }

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

    public static int getWpixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.valueOf(displayScreenResolution.substring(0, displayScreenResolution.indexOf(Marker.ANY_MARKER))).intValue();
    }

    public static int getHpixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.valueOf(displayScreenResolution.substring(displayScreenResolution.indexOf(Marker.ANY_MARKER) + 1)).intValue();
    }

    public static String getTotalMemory(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String line = bufferedReader.readLine();
            String[] strArrSplit = line.split("\\s+");
            for (String str : strArrSplit) {
                Log.i(line, str + "\t");
            }
            long jLongValue = new Long(((long) Integer.valueOf(strArrSplit[1]).intValue()) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).longValue();
            bufferedReader.close();
            return Formatter.formatFileSize(context, jLongValue);
        } catch (IOException unused) {
            return "";
        }
    }

    public static String getSDTotalSize(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Formatter.formatFileSize(context, ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount()));
    }

    private static String getDisplayScreenResolution(Context context) {
        int i;
        try {
            i = context.getResources().getDisplayMetrics().heightPixels;
            try {
                return context.getResources().getDisplayMetrics().widthPixels + Marker.ANY_MARKER + i;
            } catch (Exception unused) {
                return 0 + Marker.ANY_MARKER + i;
            }
        } catch (Exception unused2) {
            i = 0;
        }
    }

    public static int getSecondTimestampTwo(Date date) {
        if (date == null) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(date.getTime() / 1000)).intValue();
    }

    public static String getNow() {
        return new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT).format(new Date());
    }

    public static String getSignature(HashMap<String, String> map, String str) throws IOException {
        Set<Map.Entry> setEntrySet = new TreeMap(map).entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : setEntrySet) {
            sb.append((String) entry.getKey());
            sb.append(SimpleComparison.EQUAL_TO_OPERATION);
            sb.append((String) entry.getValue());
        }
        sb.append(str);
        LogUtil.w("SqTrackUtil", "---------------->加密串 === " + sb.toString());
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(sb.toString().getBytes("UTF-8"));
            StringBuilder sb2 = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb2.append("0");
                }
                sb2.append(hexString);
            }
            return sb2.toString();
        } catch (GeneralSecurityException e) {
            throw new IOException(e);
        }
    }

    public static void setLogined(Context context, boolean z) {
        SpUtils.get(context, SQ_PREFS).put("logined", z);
    }

    public static boolean getLogined(Context context) {
        return SpUtils.get(context, SQ_PREFS).getBoolean("logined", false);
    }

    public static String getServerid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("dsid", "");
    }

    public static String getRoleid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("drid", "");
    }

    public static String getRolename(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("drname", "");
    }

    public static String getRolelevel(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("drlevel", "");
    }

    public static String getServerName(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("serverName", "");
    }

    public static String getVipLevel(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(INFO_VIP_LEVEL, "");
    }

    public static String getScut(Context context) {
        if (MultiSdkManager.getInstance().isScut3()) {
            return MultiSdkManager.getInstance().getScut3();
        }
        return (ConfigManager.getInstance(context).isSplashSDK() || ConfigManager.getInstance(context).isLessFunction()) ? "1" : "0";
    }

    public static String getChannelSdkVersion(Context context) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open("multiconfig");
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties.getProperty("channel_sdk_version");
        } catch (IOException unused) {
            LogUtil.i("获取渠道sdk版本出错");
            return "";
        }
    }

    public static String getChannelName(Context context) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open("multiconfig");
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties.getProperty("channelName");
        } catch (IOException unused) {
            LogUtil.i("获取渠道名称出错");
            return "";
        }
    }
}
