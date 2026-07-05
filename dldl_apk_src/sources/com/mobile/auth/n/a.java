package com.mobile.auth.n;

import android.util.Base64;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.model.UStruct;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {

    /* JADX INFO: renamed from: com.mobile.auth.n.a$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ long b;
        final /* synthetic */ long c;

        AnonymousClass1(boolean z, long j, long j2) {
            this.a = z;
            this.b = j;
            this.c = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.b(a.this).a(a.a(a.this).b("", Constant.ACTION_PHONE_LOGGER_ENABLE, UStruct.newUStruct().putApiParams(Constant.API_PARAMS_KEY_ENABLE, String.valueOf(this.a)).startTime(this.b).endTime(this.c).build(), String.valueOf(1)), 2);
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    public static String a(byte[] bArr, String str, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(bArr2));
            return Base64.encodeToString(cipher.doFinal(str.getBytes("utf-8")), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public static String b(byte[] bArr, String str, byte[] bArr2) {
        try {
            byte[] bArrDecode = Base64.decode(str, 0);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(bArr2));
            return new String(cipher.doFinal(bArrDecode), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
