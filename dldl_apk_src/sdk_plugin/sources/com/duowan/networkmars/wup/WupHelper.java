package com.duowan.networkmars.wup;

import com.duowan.auk.ArkValue;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.util.L;
import com.duowan.auk.util.VersionUtil;
import com.duowan.jce.wup.UniPacket;
import com.duowan.networkmars.hysignal.HySignalSDK;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.utils.ResourceUtils;
import com.huya.mtp.utils.StringUtils;
import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class WupHelper {
    private static String HUYA_UA = null;
    private static final String TAG = "WupHelper";
    public static OnCallback mCb;
    public static String sDefineVersion;

    public interface OnCallback {
        Exception onParseJceError(Exception exc, String str, byte[] bArr);
    }

    public static <T extends JceStruct> void sendRequest(String str, String str2, String str3, JceStruct jceStruct, WupEasyHandler<T> wupEasyHandler) {
        HttpClient.post(str, createParams(str2, str3, jceStruct), wupEasyHandler);
    }

    public static <T extends JceStruct> void sendRequest(String str, String str2, JceStruct jceStruct, WupEasyHandler<T> wupEasyHandler) {
        sendRequest(HySignalSDK.DEFAULT_URL, str, str2, jceStruct, wupEasyHandler);
    }

    public static <T extends JceStruct> HttpClient.RequestParams createParams(String str, String str2, T t) {
        UniPacket uniPacket = new UniPacket();
        uniPacket.useVersion3();
        uniPacket.setServantName(str);
        uniPacket.setFuncName(str2);
        uniPacket.put("tReq", t);
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.setBodyContentType("application/multipart-formdata");
        requestParams.putBody(uniPacket.encode());
        return requestParams;
    }

    public static <T extends JceStruct> T parseJce(byte[] bArr, T t) {
        Exception excOnParseJceError;
        if (t == null) {
            return null;
        }
        try {
            t.readFrom(new JceInputStream(bArr));
            return t;
        } catch (Exception e) {
            try {
                String simpleName = t.getClass().getSimpleName();
                if (mCb != null && (excOnParseJceError = mCb.onParseJceError(e, simpleName, bArr)) != null) {
                    L.error(TAG, (Throwable) excOnParseJceError);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }
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

    public static String getSHuYaUA() {
        if (StringUtils.isNullOrEmpty(HUYA_UA)) {
            HUYA_UA = String.format("%s&%s&%s", getClientType(), getVersion(), ArkValue.channelName());
        }
        return HUYA_UA;
    }

    public static String getVersion() {
        String str = "0.0.0";
        String str2 = sDefineVersion;
        try {
            if (str2 != null) {
                return str2;
            }
            try {
                String localName = VersionUtil.getLocalName(ArkValue.gContext);
                if (!StringUtils.isNullOrEmpty(localName)) {
                    str = localName;
                }
            } catch (Exception e) {
                L.error(TAG, (Throwable) e);
                StringUtils.isNullOrEmpty("0.0.0");
            }
            return str;
        } catch (Throwable th) {
            StringUtils.isNullOrEmpty(str);
            throw th;
        }
    }

    public static String getClientType() {
        return ResourceUtils.getMetaValue(ArkValue.gContext, "CLIENT_TYPE");
    }

    public static String getNSAppId() {
        return ResourceUtils.getMetaValue(ArkValue.gContext, "NS_APPID");
    }

    public static void setDefineVersion(String str) {
        sDefineVersion = str;
    }
}
