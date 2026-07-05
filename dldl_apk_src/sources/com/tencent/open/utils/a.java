package com.tencent.open.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.alicom.tools.networking.RSA;
import com.tencent.open.log.SLog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class a {
    private KeyStore a;
    private SharedPreferences b;

    /* JADX INFO: renamed from: com.tencent.open.utils.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    private static class C0119a {
        Properties a;
        byte[] b;

        private C0119a() {
            this.a = new Properties();
        }

        void a(byte[] bArr) throws IOException {
            if (bArr == null) {
                return;
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            int length = a.a().a().length;
            byte[] bArr2 = new byte[length];
            byteBufferWrap.get(bArr2);
            if (!a.a().equals(new m(bArr2))) {
                throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
            }
            if (bArr.length - length <= 2) {
                return;
            }
            byte[] bArr3 = new byte[2];
            byteBufferWrap.get(bArr3);
            int iB = new m(bArr3).b();
            if ((bArr.length - length) - 2 < iB) {
                return;
            }
            byte[] bArr4 = new byte[iB];
            byteBufferWrap.get(bArr4);
            this.a.load(new ByteArrayInputStream(bArr4));
            int length2 = ((bArr.length - length) - iB) - 2;
            if (length2 > 0) {
                this.b = new byte[length2];
                byteBufferWrap.get(this.b);
            }
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.a + ", otherData=" + Arrays.toString(this.b) + "]";
        }
    }

    public a(Context context) {
        try {
            this.b = context.getSharedPreferences("KEYSTORE_SETTING", 0);
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            this.a = keyStore;
            keyStore.load(null);
            if (this.a.containsAlias("KEYSTORE_AES")) {
                return;
            }
            c("");
            a(context);
            a();
        } catch (Exception e) {
            SLog.d("KEYSTORE", "Exception", e);
        }
    }

    private void a(Context context) throws Exception {
        SLog.d("KEYSTORE", "Build.VERSION.SDK_INT=" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= 23) {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder("KEYSTORE_AES", 3).setDigests("SHA-256", "SHA-512").setEncryptionPaddings("PKCS1Padding").build());
            keyPairGenerator.generateKeyPair();
        } else if (Build.VERSION.SDK_INT >= 18) {
            KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 30);
            keyPairGenerator2.initialize(new KeyPairGeneratorSpec.Builder(context).setAlias("KEYSTORE_AES").setSubject(new X500Principal("CN=KEYSTORE_AES")).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build());
            keyPairGenerator2.generateKeyPair();
        }
    }

    public String a(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, c(), new IvParameterSpec(b()));
            return Base64.encodeToString(cipher.doFinal(str.getBytes()), 0);
        } catch (Exception e) {
            SLog.e("KEYSTORE", "Exception", e);
            return "";
        }
    }

    public String b(String str) {
        try {
            byte[] bArrDecode = Base64.decode(str.getBytes(), 0);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, c(), new IvParameterSpec(b()));
            return new String(cipher.doFinal(bArrDecode));
        } catch (Exception e) {
            SLog.e("KEYSTORE", "Exception", e);
            return "";
        }
    }

    private void a() throws Exception {
        byte[] bArr = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bArr);
        c(Base64.encodeToString(secureRandom.generateSeed(12), 0));
        if (Build.VERSION.SDK_INT >= 18) {
            PublicKey publicKey = this.a.getCertificate("KEYSTORE_AES").getPublicKey();
            Cipher cipher = Cipher.getInstance(RSA.RSA_ALGORITHM);
            cipher.init(1, publicKey);
            d(Base64.encodeToString(cipher.doFinal(bArr), 0));
            return;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArr);
        d(Base64.encodeToString(messageDigest.digest(), 0));
    }

    private void c(String str) {
        this.b.edit().putString("PREF_KEY_IV", str).apply();
    }

    private byte[] b() {
        return Base64.decode(this.b.getString("PREF_KEY_IV", ""), 0);
    }

    private void d(String str) {
        this.b.edit().putString("PREF_KEY_AES", str).apply();
    }

    private SecretKeySpec c() throws Exception {
        String string = this.b.getString("PREF_KEY_AES", "");
        if (Build.VERSION.SDK_INT >= 18) {
            PrivateKey privateKey = (PrivateKey) this.a.getKey("KEYSTORE_AES", null);
            Cipher cipher = Cipher.getInstance(RSA.RSA_ALGORITHM);
            cipher.init(2, privateKey);
            return new SecretKeySpec(cipher.doFinal(Base64.decode(string, 0)), "AES/GCM/NoPadding");
        }
        return new SecretKeySpec(Base64.decode(string, 0), "AES/GCM/NoPadding");
    }
}
