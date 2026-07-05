package com.huya.berry.gamesdk.wup;

import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class WupHelper {
    public static final String CLIENT_TYPE = "adr_game_sdk";
    private static final String CONFIG_WUP_GUID = "wup_guid";
    public static final String FORMAT_SHUYAUA = "%s&%s&%s&%s";
    private static final String TAG = "WupHelper";
    public static final String TOKEN = "5060";
    private static String mGuid;

    public static String getClientType() {
        return "adr_game_sdk";
    }

    public static String getGUID() {
        if (TextUtils.isEmpty(mGuid)) {
            mGuid = Config.getInstance(ArkValue.gContext).getString(CONFIG_WUP_GUID, "");
        }
        return mGuid;
    }

    public static void setGUID(String str) {
        if (TextUtils.isEmpty(mGuid) || !mGuid.equals(str)) {
            mGuid = str;
            L.info(TAG, "update guid :%s", str);
        } else {
            mGuid = str;
        }
        Config.getInstance(ArkValue.gContext).setString(CONFIG_WUP_GUID, str);
    }

    public static String getVersion() {
        return AppUtils.getVersion();
    }

    public static String getChannel() {
        return AppUtils.getChannel();
    }

    public static String getSHuYaUA() {
        return String.format("%s&%s&%s&%s", getClientType(), getVersion(), SdkProperties.appId.get(), SdkProperties.gameId.get());
    }

    public static String toDateTimeFormat(long j) {
        try {
            return new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT).format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
        }
        return sb.toString();
    }
}
