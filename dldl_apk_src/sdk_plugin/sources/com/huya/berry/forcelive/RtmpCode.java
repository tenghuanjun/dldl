package com.huya.berry.forcelive;

import android.util.Base64;
import android.util.Log;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.zip.Deflater;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class RtmpCode {
    private static final String TAG = "RtmpCode";
    private static final int WUP_APP_ID = 10057;

    RtmpCode() {
    }

    static String rtmpCode(int i, int i2) {
        String streamName = getStreamName(i, LoginProperties.uid.get().longValue());
        String verifyCode = getVerifyCode(LoginApi.getDefaultToken().getToken(), LoginApi.getDefaultToken().getTokenType(), streamName, i2, LoginProperties.uid.get().longValue());
        if (verifyCode.isEmpty()) {
            return "";
        }
        String str = streamName + String.format(Locale.US, "?streamcode=%s", verifyCode);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(SdkProperties.multiStreamFlag.get().longValue() > 1 ? "&transcode=1" : "&transcode=0");
        String str2 = sb.toString() + "&huya_ticket=1";
        if (SdkProperties.streamType.get().intValue() == 1) {
            str2 = str2 + String.format(Locale.US, "&yycdnurl=%s", String.format(Locale.CHINA, "http://%s/%d_%d_%d.rtmp?uid=%d", "hls.yy.com", Long.valueOf(LoginProperties.uid.get().longValue()), Long.valueOf(LoginProperties.uid.get().longValue()), Integer.valueOf(WUP_APP_ID), LoginProperties.uid.get()));
        }
        Log.i(TAG, String.format(Locale.US, "uid %d, makeupFullVerifyCode:%s", LoginProperties.uid.get(), str2));
        return str2;
    }

    private static String getStreamName(int i, long j) {
        long jLongValue = LoginProperties.uid.get().longValue();
        long jLongValue2 = LoginProperties.uid.get().longValue();
        return String.format(Locale.US, "%d-%d-%s-%d-%d-A-%d-%d", Long.valueOf(jLongValue), Long.valueOf(jLongValue2), getLiveId(jLongValue2).toString(), Long.valueOf((j * 2) + 123456), Integer.valueOf(WUP_APP_ID), Long.valueOf(System.currentTimeMillis() / 1000), Integer.valueOf(i));
    }

    private static String getVerifyCode(String str, int i, String str2, int i2, long j) {
        long jLongValue = LoginProperties.uid.get().longValue();
        long jLongValue2 = LoginProperties.uid.get().longValue();
        return toBase64((String.format(Locale.US, "liveid=%s&uid=%d&cid=%d&scid=%d&appid=%d&streamname=%s", getLiveId(jLongValue2).toString(), Long.valueOf(j), Long.valueOf(jLongValue), Long.valueOf(jLongValue2), 10057L, str2) + "&ticket=" + str + "&ticketType=" + i) + String.format(Locale.US, "&biztype=%d", Integer.valueOf(i2)));
    }

    private static BigInteger getLiveId(long j) {
        return new BigInteger(Long.valueOf(j).toString()).shiftLeft(32);
    }

    private static String toBase64(String str) {
        String strEncodeToString = "";
        try {
            byte[] bytes = str.getBytes("UTF-8");
            Deflater deflater = new Deflater();
            deflater.setInput(bytes);
            deflater.finish();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (!deflater.finished()) {
                byteArrayOutputStream.write(bArr, 0, deflater.deflate(bArr));
            }
            deflater.end();
            strEncodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            return URLEncoder.encode(strEncodeToString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return strEncodeToString;
        }
    }
}
