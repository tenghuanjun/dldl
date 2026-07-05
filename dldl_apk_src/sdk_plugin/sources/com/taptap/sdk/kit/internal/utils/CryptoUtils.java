package com.taptap.sdk.kit.internal.utils;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.taptap.sdk.kit.internal.TapLogger;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class CryptoUtils {
    public static final String DEFAULT_SECRETKEY_NAME = "default_secretkey_name";
    public static final String STORE_FILE_NAME = "crypto";
    private static final String TAG = "TapCryptoUtils";
    private static volatile CryptoUtils instance;
    private KeyStore keyStore;

    private CryptoUtils(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public static synchronized CryptoUtils getInstance(Context context) {
        if (instance == null) {
            File file = new File(context.getFilesDir(), STORE_FILE_NAME);
            try {
                KeyStore keyStore = getKeyStore(file);
                initKey(keyStore, file);
                instance = new CryptoUtils(keyStore);
            } catch (Exception e) {
                TapLogger.loge(TAG, null, e);
                try {
                    KeyStore keyStore2 = getKeyStore(file);
                    initKey(keyStore2, file);
                    instance = new CryptoUtils(keyStore2);
                } catch (Exception e2) {
                    TapLogger.loge(TAG, null, e2);
                    return null;
                }
            }
        }
        return instance;
    }

    private static void initKey(KeyStore keyStore, File file) throws Exception {
        if (keyStore.containsAlias(DEFAULT_SECRETKEY_NAME)) {
            return;
        }
        storeKey(keyStore, file, generateKeyGenerator().generateKey());
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void storeKey(java.security.KeyStore r4, java.io.File r5, javax.crypto.SecretKey r6) throws java.lang.Exception {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "default_secretkey_name"
            r2 = 0
            r3 = 23
            if (r0 < r3) goto Ld
            r4.setKeyEntry(r1, r6, r2, r2)
            goto L2c
        Ld:
            r4.setKeyEntry(r1, r6, r2, r2)
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            r4.store(r6, r2)     // Catch: java.lang.Exception -> L1c java.lang.Throwable -> L2d
            r6.close()     // Catch: java.lang.Exception -> L1c java.lang.Throwable -> L2d
            goto L29
        L1c:
            r4 = move-exception
            goto L22
        L1e:
            r4 = move-exception
            goto L2f
        L20:
            r4 = move-exception
            r6 = r2
        L22:
            java.lang.String r5 = "TapCryptoUtils"
            com.taptap.sdk.kit.internal.TapLogger.loge(r5, r2, r4)     // Catch: java.lang.Throwable -> L2d
            if (r6 == 0) goto L2c
        L29:
            r6.close()
        L2c:
            return
        L2d:
            r4 = move-exception
            r2 = r6
        L2f:
            if (r2 == 0) goto L34
            r2.close()
        L34:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.utils.CryptoUtils.storeKey(java.security.KeyStore, java.io.File, javax.crypto.SecretKey):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.security.KeyStore getKeyStore(java.io.File r7) throws java.lang.Exception {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 23
            if (r0 < r2) goto L11
            java.lang.String r7 = "AndroidKeyStore"
            java.security.KeyStore r7 = java.security.KeyStore.getInstance(r7)
            r7.load(r1)
            return r7
        L11:
            java.lang.String r0 = java.security.KeyStore.getDefaultType()
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)
            boolean r2 = r7.exists()
            if (r2 != 0) goto L31
            boolean r7 = r7.createNewFile()
            if (r7 == 0) goto L29
            r0.load(r1, r1)
            goto L5b
        L29:
            java.lang.SecurityException r7 = new java.lang.SecurityException
            java.lang.String r0 = "创建内部存储文件失败"
            r7.<init>(r0)
            throw r7
        L31:
            long r2 = r7.length()
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L3f
            r0.load(r1, r1)
            goto L5b
        L3f:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r0.load(r2, r1)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L5c
            r2.close()     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L5c
            goto L58
        L4b:
            r7 = move-exception
            goto L51
        L4d:
            r7 = move-exception
            goto L5e
        L4f:
            r7 = move-exception
            r2 = r1
        L51:
            java.lang.String r3 = "TapCryptoUtils"
            com.taptap.sdk.kit.internal.TapLogger.loge(r3, r1, r7)     // Catch: java.lang.Throwable -> L5c
            if (r2 == 0) goto L5b
        L58:
            r2.close()
        L5b:
            return r0
        L5c:
            r7 = move-exception
            r1 = r2
        L5e:
            if (r1 == 0) goto L63
            r1.close()
        L63:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.utils.CryptoUtils.getKeyStore(java.io.File):java.security.KeyStore");
    }

    private static KeyGenerator generateKeyGenerator() throws Exception {
        if (Build.VERSION.SDK_INT >= 23) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(DEFAULT_SECRETKEY_NAME, 3).setBlockModes("CBC").setUserAuthenticationRequired(false).setEncryptionPaddings("PKCS7Padding").build());
            return keyGenerator;
        }
        KeyGenerator keyGenerator2 = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        secureRandom.setSeed(generateSeed());
        keyGenerator2.init(128, secureRandom);
        return keyGenerator2;
    }

    private static byte[] generateSeed() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(System.currentTimeMillis());
            dataOutputStream.writeLong(System.nanoTime());
            dataOutputStream.writeInt(Process.myPid());
            dataOutputStream.writeInt(Process.myUid());
            dataOutputStream.write(Build.BOARD.getBytes());
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }

    public String aesEncrypt(String str) {
        try {
            SecretKey secretKey = getSecretKey(this.keyStore);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, secretKey);
            return new EncryptData(Base64.encodeToString(cipher.doFinal(StringUtils.string2BytesWithUTF8(str)), 2), cipher.getIV()).toString();
        } catch (Exception e) {
            TapLogger.loge(TAG, null, e);
            return null;
        }
    }

    public String aesDecrypt(String str) {
        try {
            EncryptData encryptData = new EncryptData(str);
            SecretKey secretKey = getSecretKey(this.keyStore);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, secretKey, new IvParameterSpec(encryptData.getIv()));
            return StringUtils.bytes2StringWithUTF8(cipher.doFinal(Base64.decode(encryptData.getEncryptString(), 2)));
        } catch (Exception e) {
            TapLogger.loge(TAG, "aesDecrypt", e);
            return null;
        }
    }

    private static SecretKey getSecretKey(KeyStore keyStore) {
        try {
            return (SecretKey) keyStore.getKey(DEFAULT_SECRETKEY_NAME, null);
        } catch (Exception e) {
            TapLogger.loge(TAG, null, e);
            return null;
        }
    }

    public static class EncryptData {
        String encryptString;
        byte[] iv;

        public EncryptData(String str, byte[] bArr) {
            this.encryptString = str;
            this.iv = bArr;
        }

        public EncryptData(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            int iIndexOf = str.indexOf("[i]");
            this.encryptString = str.substring(0, iIndexOf);
            this.iv = StringUtils.string2Bytes(str.substring(iIndexOf + 3));
        }

        public byte[] getIv() {
            return this.iv;
        }

        public String getEncryptString() {
            return this.encryptString;
        }

        public String toString() {
            return this.encryptString + "[i]" + StringUtils.bytes2String(this.iv);
        }
    }

    private static class StringUtils {
        private StringUtils() {
        }

        public static String bytes2String(byte[] bArr) {
            return new String(bArr, StandardCharsets.ISO_8859_1);
        }

        public static byte[] string2Bytes(String str) {
            return str.getBytes(StandardCharsets.ISO_8859_1);
        }

        public static String bytes2StringWithUTF8(byte[] bArr) {
            return new String(bArr, StandardCharsets.UTF_8);
        }

        public static byte[] string2BytesWithUTF8(String str) {
            return str.getBytes(StandardCharsets.UTF_8);
        }
    }
}
