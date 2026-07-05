package com.mobile.auth.gatewayauth.network;

import android.content.Context;
import com.ali.security.MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5;
import com.alicom.tools.networking.ResultMsg;
import com.alicom.tools.serialization.JSONType;
import com.mobile.auth.gatewayauth.model.LimitConfig;
import com.mobile.auth.gatewayauth.model.popsdkconfig.ConfigData;
import com.mobile.auth.gatewayauth.model.popsdkconfig.UploadData;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class RequestUtil {
    public static final String PUBLIC_SECKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLShWjAtxJv3g2VPIYOOAv4rnVDdLkdseKm7+KOkCBLV9SKY5oqksFaXcLZ+nRnjnczhze5eGKhevwliUyag6x96GyXI2WagKIoB7Uwl2byl0xB5bNvYzf+x/DKHTSoGJshU6shXWXcjGFq+mUiPhM3WGZoqdY+vvqOWD+tga8XQIDAQAB";
    private static final String SERVEL_URL = "https://dypnsapi.aliyuncs.com/?";

    /* JADX INFO: renamed from: com.mobile.auth.gatewayauth.network.RequestUtil$1, reason: invalid class name */
    static class AnonymousClass1 extends JSONType<VendorRespone> {
        AnonymousClass1() {
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.gatewayauth.network.RequestUtil$2, reason: invalid class name */
    static class AnonymousClass2 extends JSONType<com.mobile.auth.gatewayauth.model.popsdkconfig.SDKConfigRespone> {
        AnonymousClass2() {
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.gatewayauth.network.RequestUtil$3, reason: invalid class name */
    static class AnonymousClass3 extends JSONType<UploadData> {
        AnonymousClass3() {
        }
    }

    static {
        MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5.SLoad("pns-2.13.2.1-LogOnlineStandardCuumRelease_alijtca_plus");
    }

    private static native String assembleCustomizeToken(Context context, String str, String str2);

    private static native LimitConfig getConfig(ConfigData configData);

    public static native String getLifeBodyVerifyCertifyID(String str, String str2) throws IOException;

    public static native ResultMsg getPrivateKey(Context context, String str, String str2);

    public static native String getSDKConfigByPop(String str, String str2);

    private static native String getSecret1();

    private static native String getSecret2();

    private static native String getSecret3();

    private static native String getSecret4();

    public static native String getVendorListByPop(String str, String str2);

    public static native String uploadUserTrackInfoByPop(String str, String str2) throws IOException;
}
