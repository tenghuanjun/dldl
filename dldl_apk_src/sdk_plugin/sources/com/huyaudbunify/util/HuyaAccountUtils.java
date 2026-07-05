package com.huyaudbunify.util;

import android.net.Uri;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.account.SdkComType;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAccountUtils {
    public static boolean QRCodeCheckReq(String str, String str2) {
        SdkComType sdkComType;
        SdkComType sdkComType2 = SdkComType.HYTPYE_MAJOR_YY;
        Uri uri = Uri.parse(str);
        String queryParameter = uri.getQueryParameter("uri");
        SdkComType sdkComType3 = SdkComType.HYTPYE_MAJOR_YY;
        if (queryParameter != null) {
            sdkComType = SdkComType.HYTPYE_MAJOR_YY;
        } else {
            queryParameter = uri.getQueryParameter("k");
            sdkComType = SdkComType.HYTPYE_HY;
            if (queryParameter == null) {
                return false;
            }
        }
        SdkComType type = HuyaAccountSaveUtils.getInstance().getType();
        if (type == SdkComType.HYTPYE_MAJOR_YY && sdkComType == SdkComType.HYTPYE_MAJOR_YY) {
            return true;
        }
        if (type != SdkComType.HYTPYE_HY || sdkComType != SdkComType.HYTPYE_HY) {
            return false;
        }
        HuyaAuth.getInstance().QRCodeCheckReq(HuyaAccountSaveUtils.getInstance().getUid(), queryParameter, str2);
        return true;
    }

    public static boolean QRCodeConfirmReq(String str, String str2) {
        SdkComType sdkComType;
        SdkComType sdkComType2 = SdkComType.HYTPYE_MAJOR_YY;
        Uri uri = Uri.parse(str);
        String queryParameter = uri.getQueryParameter("uri");
        SdkComType sdkComType3 = SdkComType.HYTPYE_MAJOR_YY;
        if (queryParameter != null) {
            sdkComType = SdkComType.HYTPYE_MAJOR_YY;
        } else {
            queryParameter = uri.getQueryParameter("k");
            sdkComType = SdkComType.HYTPYE_HY;
            if (queryParameter == null) {
                return false;
            }
        }
        SdkComType type = HuyaAccountSaveUtils.getInstance().getType();
        if ((type == SdkComType.HYTPYE_MAJOR_YY || type == SdkComType.HYTPYE_MAJOR_HY) && sdkComType == SdkComType.HYTPYE_MAJOR_YY) {
            return true;
        }
        if (type != SdkComType.HYTPYE_HY || sdkComType != SdkComType.HYTPYE_HY) {
            return false;
        }
        HuyaAuth.getInstance().QRCodeConfirmReq(HuyaAccountSaveUtils.getInstance().getUid(), queryParameter, str2);
        return true;
    }

    public static boolean QRCodeCancelReq(String str, String str2) {
        SdkComType sdkComType;
        SdkComType sdkComType2 = SdkComType.HYTPYE_MAJOR_YY;
        Uri uri = Uri.parse(str);
        String queryParameter = uri.getQueryParameter("uri");
        SdkComType sdkComType3 = SdkComType.HYTPYE_MAJOR_YY;
        if (queryParameter != null) {
            sdkComType = SdkComType.HYTPYE_MAJOR_YY;
        } else {
            queryParameter = uri.getQueryParameter("k");
            sdkComType = SdkComType.HYTPYE_HY;
            if (queryParameter == null) {
                return false;
            }
        }
        SdkComType type = HuyaAccountSaveUtils.getInstance().getType();
        if ((type == SdkComType.HYTPYE_MAJOR_YY || type == SdkComType.HYTPYE_MAJOR_HY) && sdkComType == SdkComType.HYTPYE_MAJOR_YY) {
            return true;
        }
        if (type != SdkComType.HYTPYE_HY || sdkComType != SdkComType.HYTPYE_HY) {
            return false;
        }
        HuyaAuth.getInstance().QRCodeCancelReq(HuyaAccountSaveUtils.getInstance().getUid(), queryParameter, str2);
        return true;
    }
}
