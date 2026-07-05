package com.sqwan.msdk.api;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.NumberKeyListener;
import android.widget.EditText;
import com.parameters.bean.WebDialogBean;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.TelephonyInfoUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.common.web.SY37PortraitWebPage;
import com.sqwan.common.web.SY37web;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.views.SQActivationCodeDialog;
import com.sy37sdk.bean.DeviceInfo;
import com.sy37sdk.utils.DeviceTools;
import com.sy37sdk.utils.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MultiSDKUtils {
    private static final String CODE_LOGIN = "login_cut";
    private static final String CODE_PAY = "pay_cut";
    private static final String DEV = "dev";
    private static final String DEV_IMEI = "dev_imei";
    private static final String DEV_MAC = "dev_mac";
    private static final String GID = "gid";
    private static final String IDENTIFY = "identify";
    private static final String INFO_ROLEID = "drid";
    private static final String INFO_ROLELEVEL = "drlevel";
    private static final String INFO_ROLENAME = "drname";
    private static final String INFO_SERVERID = "dsid";
    private static final String INFO_SERVER_NAME = "serverName";
    private static final String INFO_VIP_LEVEL = "viplevel";
    private static final String KEY = "multiAppkey";
    public static final String LOGINED = "logined";
    private static final String PASSWORD = "pd";
    private static final String PID = "pid";
    private static final String PUSH_DELAY = "push_delay";
    private static final String PUSH_IS_DELAY = "push_is_delay";
    private static final String P_USERID = "puid";
    private static final String P_USERNAME = "puname";
    private static final String REFER = "refer";
    private static final String SQ_CHANNL_PREFS = "sq_channl_prefs";
    private static final String SQ_PREFS = "sq_prefs";
    private static final String TOKEN = "token";
    private static final String URL_M_ENTER = "eapi";
    private static final String URL_M_ORDER = "oapi";
    public static final String URL_M_POP_ACTIVE = "pop_ups_active_api";
    public static final String URL_M_POP_LOGIN = "pop_ups_login_api";
    public static final String URL_M_POP_PAY = "pop_ups_recharge_api";
    public static final String URL_M_POP_SUBMIT_ROLE = "pop_ups_enter_api";
    private static final String URL_M_SUBMIT = "lapi";
    private static final String URL_M_VERIFY_TOKEN = "vptapi";
    private static final String URL_PAY_NEW = "pay_url_new";
    private static final String URL_PAY_QUERY = "pay_url_query";
    private static final String URL_PUSH = "push_url";
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String YYB_PARAM = "yyb";
    static SQActivationCodeDialog dialog;
    private static DeviceInfo deviceInfo = new DeviceInfo();
    private static String devInfoImei = "";

    public static void showAuthDialog(Context context, String str) {
    }

    public static void setString(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }

    public static void removeString(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.remove(str);
        editorEdit.commit();
    }

    public static String getString(Context context, String str) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(str, "");
    }

    public static void setBoolean(Context context, String str, Boolean bool) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putBoolean(str, bool.booleanValue());
        editorEdit.commit();
    }

    public static void removeBoolean(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.remove(str);
        editorEdit.commit();
    }

    public static boolean getBoolean(Context context, String str) {
        return context.getSharedPreferences(SQ_PREFS, 0).getBoolean(str, false);
    }

    public static void setPushIsDelay(Context context, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putBoolean(PUSH_IS_DELAY, z);
        editorEdit.commit();
    }

    public static boolean getPushIsDelay(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getBoolean(PUSH_IS_DELAY, true);
    }

    public static void setPushDelay(Context context, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putInt(PUSH_DELAY, i);
        editorEdit.commit();
    }

    public static int getPushDelay(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getInt(PUSH_DELAY, 21600000);
    }

    public static void setPushUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("push_url", str);
        editorEdit.commit();
    }

    public static String getPushUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("push_url", IMUrl.URL_PUSH);
    }

    public static void setPayQueryUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(URL_PAY_QUERY, str);
        editorEdit.commit();
    }

    public static String getPayQueryUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(URL_PAY_QUERY, IMUrl.URL_PAY_QUERY);
    }

    public static void setPayOrderUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("oapi", str);
        editorEdit.commit();
    }

    public static String getPayOrderUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("oapi", IMUrl.URL_M_ORDER);
    }

    public static void setVerifyTokenUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("vptapi", str);
        editorEdit.commit();
    }

    public static String getVerifyTokenUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("vptapi", IMUrl.URL_M_VAREFY_TOKEN);
    }

    public static void setSubmitUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("lapi", str);
        editorEdit.commit();
    }

    public static String getSubmitUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("lapi", IMUrl.URL_M_SUBMIT);
    }

    @Deprecated
    public static void setNewPayUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(URL_PAY_NEW, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getNewPayUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(URL_PAY_NEW, IMUrl.URL_PAY_DEFAULT);
    }

    @Deprecated
    public static void setCodeOfPay(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(CODE_PAY, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getCodeOfPay(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(CODE_PAY, "0");
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

    public static void setDevID(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("dev", str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getDevID(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("dev", getLocalDev(context));
    }

    private static String getDevCache(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("dev", "");
    }

    private static boolean canGetImeiFromApi(Context context) {
        return !TextUtils.isEmpty(Util.getIMEI(context));
    }

    public static void setGID(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("gid", str);
        editorEdit.commit();
    }

    public static String getGID(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("gid", "1000001");
    }

    public static void setPID(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("pid", str);
        editorEdit.commit();
    }

    public static String getPID(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("pid", "1");
    }

    public static void setYYB(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(YYB_PARAM, str);
        editorEdit.commit();
    }

    public static String getYYB(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(YYB_PARAM, "");
    }

    @Deprecated
    public static void setKey(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(KEY, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getKey(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(KEY, "");
    }

    public static void setRefer(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("refer", str);
        editorEdit.commit();
    }

    public static String getRefer(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("refer", "sy00000_1");
    }

    public static void setToken(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("token", str);
        editorEdit.commit();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("token", "");
    }

    public static void setPlatUsername(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(P_USERNAME, str);
        editorEdit.commit();
    }

    public static String getPlatUsername(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(P_USERNAME, "");
    }

    public static void setPlatUserid(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(P_USERID, str);
        editorEdit.commit();
    }

    public static String getPlatUserid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(P_USERID, "");
    }

    public static void setMDevIds(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(IDENTIFY, str);
        editorEdit.commit();
    }

    public static String getMDevIds(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(IDENTIFY, "");
    }

    public static void setUsername(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("username", str);
        editorEdit.commit();
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("username", "");
    }

    public static void setUserid(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("userid", str);
        editorEdit.commit();
    }

    public static String getUserid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("userid", "");
    }

    public static void setPassword(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(PASSWORD, str);
        editorEdit.commit();
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(PASSWORD, "");
    }

    public static void setLogined(Context context, boolean z) {
        SpUtils.get(context, SQ_PREFS).put("logined", z);
    }

    public static boolean getLogined(Context context) {
        return SpUtils.get(context, SQ_PREFS).getBoolean("logined", false);
    }

    public static void getPayMoney(final Context context, final SQResultListener sQResultListener) {
        final EditText editText = new EditText(context);
        editText.setKeyListener(new DigitsKeyListener(false, true));
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        editText.setKeyListener(new NumberKeyListener() { // from class: com.sqwan.msdk.api.MultiSDKUtils.1
            @Override // android.text.method.KeyListener
            public int getInputType() {
                return 3;
            }

            @Override // android.text.method.NumberKeyListener
            protected char[] getAcceptedChars() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            }
        });
        editText.setHint("请输入充值金额(不超过1万元)");
        new AlertDialog.Builder(context).setTitle("请输入充值金额").setView(editText).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.sqwan.msdk.api.MultiSDKUtils.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String string = editText.getText().toString();
                if (string.length() == 0 || Integer.parseInt(string) == 0) {
                    MultiSDKUtils.showTips(context, "请输入大于0的金额");
                    try {
                        Field declaredField = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
                        declaredField.setAccessible(true);
                        declaredField.set(dialogInterface, false);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                try {
                    Field declaredField2 = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
                    declaredField2.setAccessible(true);
                    declaredField2.set(dialogInterface, true);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("money", Integer.parseInt(string));
                sQResultListener.onSuccess(bundle);
                dialogInterface.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.sqwan.msdk.api.MultiSDKUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Field declaredField = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
                    declaredField.setAccessible(true);
                    declaredField.set(dialogInterface, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sQResultListener.onFailture(206, "用户取消支付【20005】");
                dialogInterface.dismiss();
            }
        }).setCancelable(false).create().show();
    }

    public static Properties readPropertites(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties;
        } catch (Exception unused) {
            return null;
        }
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

    public static PackageInfo getPackageInfo(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
            packageInfo = null;
        }
        return packageInfo == null ? new PackageInfo() : packageInfo;
    }

    public static String getPackageInfos(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("pm list package -3").getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String strReplace = line.replace("package:", "");
                jSONObject.put(strReplace, (String) getPackageInfo(context, strReplace).applicationInfo.loadLabel(context.getPackageManager()));
            }
        } catch (Exception e) {
            System.out.println("MultiSDKUtils.runCommand,e=" + e);
        }
        return jSONObject.toString();
    }

    public static String getIMEI(Context context) {
        return getIMEI(context, true);
    }

    public static String getIMEI(Context context, boolean z) {
        String strRandomDeverceNum;
        if (!z) {
            return "";
        }
        if (Build.VERSION.SDK_INT > 28 && TextUtils.isEmpty(getDevCache(context)) && !canGetImeiFromApi(context)) {
            strRandomDeverceNum = ImeiLogic.getInstance(context).getValue();
        } else if (Build.VERSION.SDK_INT >= 23) {
            devInfoImei = Util.getIMEI(context);
            LogUtil.w("devInfoImei=" + devInfoImei);
            strRandomDeverceNum = !TextUtils.isEmpty(devInfoImei) ? devInfoImei : randomDeverceNum(context, 15, deviceInfo);
        } else {
            devInfoImei = Util.getIMEI(context);
            LogUtil.w("devInfoImei=" + devInfoImei);
            strRandomDeverceNum = !TextUtils.isEmpty(devInfoImei) ? devInfoImei : randomDeverceNum(context, 15, deviceInfo);
        }
        if (TextUtils.isEmpty(strRandomDeverceNum)) {
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.imei_empty);
        }
        return strRandomDeverceNum;
    }

    private static String randomToString(Context context, int i) {
        Random random = new Random(System.currentTimeMillis());
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + random.nextInt(9);
        }
        return str;
    }

    private static String randomDeverceNum(Context context, int i, DeviceInfo deviceInfo2) throws Throwable {
        if (deviceInfo2 == null) {
            deviceInfo2 = new DeviceInfo();
        }
        String strRandomToString = randomToString(context, i);
        List<DeviceInfo> deviceFromFile = DeviceTools.getDeviceFromFile(context);
        if (i == 12) {
            if (deviceFromFile != null && !deviceFromFile.isEmpty()) {
                String devMac = deviceFromFile.get(0).getDevMac();
                if ("".equals(devMac) || devMac == null) {
                    deviceInfo2.setDevMac(strRandomToString);
                    DeviceTools.setDeviceToFile(context, deviceInfo2);
                }
                return deviceFromFile.get(0).getDevMac();
            }
            deviceInfo2.setDevMac(strRandomToString);
            if ("".equals(devInfoImei.trim()) || devInfoImei == null) {
                deviceInfo2.setDevImei(randomToString(context, 15));
            }
        } else {
            if (deviceFromFile != null && !deviceFromFile.isEmpty()) {
                String devImei = deviceFromFile.get(0).getDevImei();
                if ("".equals(devImei) || devImei == null) {
                    deviceInfo2.setDevImei(strRandomToString);
                    DeviceTools.setDeviceToFile(context, deviceInfo2);
                }
                return deviceFromFile.get(0).getDevImei();
            }
            deviceInfo2.setDevImei(strRandomToString);
        }
        DeviceTools.setDeviceToFile(context, deviceInfo2);
        return strRandomToString;
    }

    public static String getSIM(Context context) {
        return TelephonyInfoUtils.getSimSerialNumber(context);
    }

    public static String getIMSI(Context context) {
        return TelephonyInfoUtils.getSubscriberId(context);
    }

    public static String getMac(Context context) {
        return getMac(context, true);
    }

    public static String getMac(Context context, boolean z) {
        if (!z) {
            return "";
        }
        if (Build.VERSION.SDK_INT > 28 && TextUtils.isEmpty(getDevCache(context)) && !canGetImeiFromApi(context)) {
            return MacLogic.getInstance(context).getValue();
        }
        String macAddress = SensitiveInfoManager.getInstance().getMacAddress(context);
        Pattern patternCompile = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？]");
        if (macAddress == null) {
            macAddress = "";
        }
        Matcher matcher = patternCompile.matcher(macAddress);
        return matcher != null ? matcher.replaceAll("").trim() : randomDeverceNum(context, 12, deviceInfo);
    }

    public static String getLocalDev(Context context) {
        return Md5(getDevMac(context) + getDevImei(context) + "-:&d4@zXqm-pLgW").toLowerCase();
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

    public static int getWpixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.valueOf(displayScreenResolution.substring(0, displayScreenResolution.indexOf(Marker.ANY_MARKER))).intValue();
    }

    public static int getHpixels(Context context) {
        String displayScreenResolution = getDisplayScreenResolution(context);
        return Integer.valueOf(displayScreenResolution.substring(displayScreenResolution.indexOf(Marker.ANY_MARKER) + 1)).intValue();
    }

    public static String getBrand() {
        return Build.MODEL;
    }

    public static String getNumber(Context context) {
        return TelephonyInfoUtils.getLine1Number(context);
    }

    public static int getIdByName(String str, String str2, String str3, Context context) {
        return SqResUtils.getIdByName(str, str2, context);
    }

    public static boolean checkPackInstalled(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static void launchApp(Context context, String str) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
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

    public static Drawable getAppIcon(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(context.getPackageName(), 0).applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void showTips(Context context, String str) {
        try {
            ToastUtil.showToast(context, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static String getNetType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : "NULL";
    }

    public static void showNoticeDialog(Context context, String str, String str2) {
        if (str2 == null || "".equals(str2)) {
            return;
        }
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setCancelable(false);
        sQWebViewDialog.setTitle(str);
        sQWebViewDialog.setUrl(AppUtils.constructWebUrlParam(context, str2));
        sQWebViewDialog.show();
    }

    public static void showSQWebDialog(Context context, String str) {
        WebDialogBean webDialogBean = new WebDialogBean();
        webDialogBean.setShowToolBar(false);
        webDialogBean.setUrl(str);
        showSQWebDialog(context, webDialogBean);
    }

    public static void showSQWebDialog(Context context, WebDialogBean webDialogBean) {
        showSQWebDialog(context, webDialogBean, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void showSQWebDialog(Context context, WebDialogBean webDialogBean, DialogInterface.OnDismissListener onDismissListener) {
        if (webDialogBean == null || TextUtils.isEmpty(webDialogBean.getUrl())) {
            return;
        }
        String url = webDialogBean.getUrl();
        Uri uri = Uri.parse(url);
        if (TextUtils.equals(uri.getQueryParameter("forceOrientation"), "1") && !isScreenOriatationPortrait(context)) {
            boolean zIsSupportPlugin = isSupportPlugin();
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.putExtra("url", url);
            intent.setClass(context, zIsSupportPlugin ? SY37web.class : SY37PortraitWebPage.class);
            intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
            context.startActivity(intent);
            return;
        }
        String queryParameter = uri.getQueryParameter("adaptNotchScreen");
        if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
            SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
            sQWebViewDialog.setUrl(webDialogBean.getUrl());
            sQWebViewDialog.setShowWebBar(webDialogBean.isShowToolBar());
            sQWebViewDialog.setOnDismissListener(onDismissListener);
            sQWebViewDialog.setCancelable(true);
            if (TextUtils.equals(queryParameter, "1") && isScreenOriatationPortrait(context)) {
                sQWebViewDialog.setAdaptNotchScreen(true);
            }
            sQWebViewDialog.setAllowJumpURL(false);
            sQWebViewDialog.show();
            return;
        }
        LogUtil.i("context is not an Activity or Activity is finishing ");
    }

    private static boolean isSupportPlugin() {
        try {
            Class<?> cls = Class.forName("com.sqwan.msdk.SQwanCore");
            boolean zBooleanValue = ((Boolean) cls.getMethod("isSupportPlugin", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue();
            LogUtil.i("isSupportPlugin() 返回值：" + zBooleanValue);
            return zBooleanValue;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.e("反射 isSupportPlugin 方法失败", e);
            return false;
        }
    }

    public static void showActivationCodeDialog(Context context, String str, SQActivationCodeDialog.CheckActivationCodeCallback checkActivationCodeCallback) {
        if (dialog == null) {
            dialog = new SQActivationCodeDialog(context);
        }
        dialog.setCancelable(false);
        dialog.setBetaData(str);
        dialog.setCallback(checkActivationCodeCallback);
        dialog.show();
    }

    public static void hideActivationCodeDialog() {
        SQActivationCodeDialog sQActivationCodeDialog = dialog;
        if (sQActivationCodeDialog != null) {
            sQActivationCodeDialog.dismiss();
            dialog = null;
        }
    }

    public static void downLoadBitmap(final String str, final Handler handler) {
        new Thread(new Runnable() { // from class: com.sqwan.msdk.api.MultiSDKUtils.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    if (httpURLConnection.getResponseCode() == 200) {
                        handler.obtainMessage(1, BitmapFactory.decodeStream(httpURLConnection.getInputStream())).sendToTarget();
                    } else {
                        handler.obtainMessage(-1).sendToTarget();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.obtainMessage(-1).sendToTarget();
                }
            }
        }).start();
    }

    public static boolean installApkByPath(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            File file = new File(str);
            if (file.exists() && str.endsWith(".apk")) {
                System.out.println("文件存在，开始安装" + file.getAbsolutePath());
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                context.startActivity(intent);
                return true;
            }
            System.out.println("文件不存在,或者不是apk文件!");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setPackage(context.getPackageName());
        if (Build.VERSION.SDK_INT >= 23) {
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 131072)) {
                LogUtil.d("sqsdk", "[activtiy]" + resolveInfo.activityInfo.name + " screenOrientation: " + resolveInfo.activityInfo.screenOrientation);
                if (resolveInfo.activityInfo.screenOrientation == 1 || resolveInfo.activityInfo.screenOrientation == 7 || resolveInfo.activityInfo.screenOrientation == 9 || resolveInfo.activityInfo.screenOrientation == 12) {
                    return true;
                }
            }
        } else {
            for (ResolveInfo resolveInfo2 : context.getPackageManager().queryIntentActivities(intent, 0)) {
                LogUtil.d("sqsdk", "[activtiy]" + resolveInfo2.activityInfo.name + " screenOrientation: " + resolveInfo2.activityInfo.screenOrientation);
                if (resolveInfo2.activityInfo.screenOrientation == 1 || resolveInfo2.activityInfo.screenOrientation == 7 || resolveInfo2.activityInfo.screenOrientation == 9 || resolveInfo2.activityInfo.screenOrientation == 12) {
                    return true;
                }
            }
        }
        return false;
    }

    @Deprecated
    public static void setDevMac(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(DEV_MAC, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getDevMac(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(DEV_MAC, "");
    }

    @Deprecated
    public static void setDevImei(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(DEV_IMEI, str);
        editorEdit.commit();
    }

    @Deprecated
    public static String getDevImei(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(DEV_IMEI, "");
    }

    public static void setEnterUrl(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("eapi", str);
        editorEdit.commit();
    }

    public static String getEnterUrl(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("eapi", IMUrl.URL_M_ENTER);
    }

    public static void setUrlMPopActive(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("pop_ups_active_api", str);
        editorEdit.commit();
    }

    public static String getUrlMPopActive(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("pop_ups_active_api", IMUrl.URL_M_INIT_DIALOG);
    }

    public static void setUrlMPopLogin(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("pop_ups_login_api", str);
        editorEdit.commit();
    }

    public static void setUrlSubmitRolePop(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("pop_ups_enter_api", str);
        editorEdit.commit();
    }

    public static String getUrlSubmitRolePop(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("pop_ups_enter_api", IMUrl.URL_SUBMIT_ROLE_POPUP);
    }

    public static void setUrlPayPop(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("pop_ups_recharge_api", str);
        editorEdit.commit();
    }

    public static String getUrlPayPop(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("pop_ups_recharge_api", IMUrl.URL_PAY_POPUP);
    }

    public static void setServerid(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("dsid", str);
        editorEdit.commit();
    }

    public static String getServerid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("dsid", "");
    }

    public static void setRoleid(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("drid", str);
        editorEdit.commit();
    }

    public static String getRoleid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("drid", "");
    }

    public static void setRolename(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("drname", str);
        editorEdit.commit();
    }

    public static String getRolename(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("drname", "");
    }

    public static void setRolelevel(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("drlevel", str);
        editorEdit.commit();
    }

    public static String getRolelevel(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("drlevel", "");
    }

    public static void setServerName(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString("serverName", str);
        editorEdit.commit();
    }

    public static String getServerName(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString("serverName", "");
    }

    public static void setVipLevel(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(INFO_VIP_LEVEL, str);
        editorEdit.commit();
    }

    public static String getVipLevel(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(INFO_VIP_LEVEL, "");
    }

    public static String constructCommonURL(Context context, String str) {
        String strReplace = "?gid=" + getGID(context) + "&pid=" + getPID(context) + "&dev=" + DevLogic.getInstance(context).getValue() + "&token=" + getToken(context) + "&sversion=" + VersionUtil.sdkVersion + "&refer=" + getRefer(context) + "&gwversion=4.6.7";
        if (str.contains("?")) {
            strReplace = strReplace.replace("?", "&");
        }
        return str + strReplace;
    }

    public static int VersionComparison(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < str.length() && i2 < str2.length()) {
            int[] value = getValue(str, i);
            int[] value2 = getValue(str2, i2);
            if (value[0] < value2[0]) {
                return -1;
            }
            if (value[0] > value2[0]) {
                return 1;
            }
            i = value[1] + 1;
            i2 = value2[1] + 1;
        }
        if (i == str.length() && i2 == str2.length()) {
            return 0;
        }
        return i < str.length() ? 1 : -1;
    }

    public static int[] getValue(String str, int i) {
        int[] iArr = new int[2];
        StringBuilder sb = new StringBuilder();
        while (i < str.length() && str.charAt(i) != '.') {
            sb.append(str.charAt(i));
            i++;
        }
        iArr[0] = Integer.parseInt(sb.toString());
        iArr[1] = i;
        return iArr;
    }
}
