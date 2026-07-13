package com.bytedance.bdtracker;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.bytedance.applog.encryptor.EncryptorUtil;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class e4 {
    public static final String[] b = {"tt_data", "device_platform"};
    public static final String[] c = {"aid", "version_code", "ab_version", "iid", "device_platform"};
    public static final String[] d = {"aid", "app_version", "tt_data", MonitorConstants.KEY_DEVICE_ID};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f248a;

    public e4(d dVar) {
        this.f248a = dVar;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; bArr != null && i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
            int length = str.length();
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr2[i] = (byte) str.charAt(i);
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            int length2 = str2.length();
            byte[] bArr3 = new byte[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                bArr3[i2] = (byte) str2.charAt(i2);
            }
            cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String[] a() {
        String[] strArr = new String[2];
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(128, secureRandom);
            strArr[0] = a(keyGenerator.generateKey().getEncoded());
            byte[] bArr = new byte[8];
            secureRandom.nextBytes(bArr);
            strArr[1] = a(bArr);
            if (TextUtils.isEmpty(strArr[0]) || strArr[0].length() != 32 || TextUtils.isEmpty(strArr[1])) {
                return null;
            }
            if (strArr[1].length() == 16) {
                return strArr;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] b(byte[] bArr) throws Throwable {
        Throwable th;
        GZIPInputStream gZIPInputStream;
        ByteArrayInputStream byteArrayInputStream = null;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int i = gZIPInputStream.read(bArr2);
                        if (i < 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, i);
                    }
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayInputStream = byteArrayInputStream2;
                    n0.a((Closeable) gZIPInputStream);
                    n0.a((Closeable) byteArrayInputStream);
                    throw th;
                }
            } catch (IOException unused2) {
                gZIPInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                gZIPInputStream = null;
            }
            byteArrayInputStream = byteArrayInputStream2;
        } catch (IOException unused3) {
            gZIPInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            gZIPInputStream = null;
        }
        n0.a((Closeable) gZIPInputStream);
        n0.a((Closeable) byteArrayInputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] b(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        GZIPOutputStream gZIPOutputStream = null;
        try {
            if (this.f248a.E) {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream2.write(str.getBytes("UTF-8"));
                    gZIPOutputStream = gZIPOutputStream2;
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    try {
                        this.f248a.D.error(Collections.singletonList("EncryptUtils"), "Convert string to bytes failed", th, new Object[0]);
                    } finally {
                        n0.a((Closeable) gZIPOutputStream);
                    }
                }
            } else {
                byteArrayOutputStream.write(str.getBytes("UTF-8"));
            }
        } catch (Throwable th2) {
            th = th2;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return this.f248a.E ? (this.f248a.getInitConfig() == null || this.f248a.getInitConfig().getEncryptor() == null) ? EncryptorUtil.encrypt(byteArray, byteArray.length) : this.f248a.getInitConfig().getEncryptor().encrypt(byteArray, byteArray.length) : byteArray;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || !this.f248a.E) {
            return str;
        }
        Uri uri = Uri.parse(str);
        String encodedQuery = uri.getEncodedQuery();
        ArrayList<Pair> arrayList = new ArrayList();
        for (String str2 : d) {
            String queryParameter = uri.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                arrayList.add(new Pair(str2, queryParameter));
            }
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.clearQuery();
        for (Pair pair : arrayList) {
            builderBuildUpon.appendQueryParameter((String) pair.first, (String) pair.second);
        }
        builderBuildUpon.appendQueryParameter("tt_info", new String(Base64.encode(b(encodedQuery), 8)));
        return builderBuildUpon.build().toString();
    }
}
