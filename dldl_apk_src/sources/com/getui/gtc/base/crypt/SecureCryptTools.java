package com.getui.gtc.base.crypt;

import android.content.Context;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.io.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SecureCryptTools {
    private static final String CIPHER_FLAG_FIRST = "First";
    private static final String CIPHER_FLAG_SECOND = "Second";
    private static final String CIPHER_FLAG_SEPARATOR = "-";
    private static final String CIPHER_FLAG_STARTER = ":::";
    private volatile boolean initInvoked;
    private ReentrantLock lock;
    private d secureKeyStore;

    static class a {
        private static SecureCryptTools a = new SecureCryptTools();
    }

    private SecureCryptTools() {
        this.lock = new ReentrantLock();
        try {
            init(GtcProvider.context());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v3 */
    private synchronized byte[] doDecrypt(byte[] bArr) throws CryptException {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ?? r0;
        ByteArrayOutputStream byteArrayOutputStream2;
        ByteArrayInputStream byteArrayInputStream2;
        ?? r02;
        String cipherFlag = getCipherFlag(bArr);
        if (cipherFlag == null) {
            throw new CryptException("Cipher flag not found in cipher text!");
        }
        String[] strArrSplit = cipherFlag.split(CIPHER_FLAG_SEPARATOR);
        if (strArrSplit.length < 2) {
            throw new CryptException("Cipher flag is wrong in cipher text!");
        }
        String str = strArrSplit[0];
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, (bArr.length - cipherFlag.length()) - 3);
        ByteArrayInputStream byteArrayInputStream3 = null;
        Decrypt = 0;
        Decrypt = 0;
        Decrypt = 0;
        ?? Decrypt = 0;
        ByteArrayInputStream byteArrayInputStream4 = null;
        Decrypt = 0;
        Decrypt = 0;
        Decrypt = 0;
        ?? Decrypt2 = 0;
        if (cipherFlag.endsWith(CIPHER_FLAG_FIRST)) {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArrCopyOfRange);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        Decrypt2 = CryptTools.decrypt("AES/CBC/PKCS7Padding", this.secureKeyStore.a(str), this.secureKeyStore.c(str), byteArrayInputStream);
                        byte[] bArr2 = new byte[1048576];
                        while (true) {
                            int i = Decrypt2.read(bArr2);
                            if (i == -1) {
                                byteArrayOutputStream.flush();
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                IOUtils.safeClose(Decrypt2);
                                IOUtils.safeClose(byteArrayInputStream);
                                IOUtils.safeClose(byteArrayOutputStream);
                                return byteArray;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i);
                        }
                    } catch (Throwable th) {
                        th = th;
                        ?? r9 = Decrypt2;
                        byteArrayInputStream3 = byteArrayInputStream;
                        r0 = r9;
                        try {
                            throw new CryptException("decrypt failed!", th);
                        } catch (Throwable th2) {
                            th = th2;
                            ByteArrayInputStream byteArrayInputStream5 = byteArrayInputStream3;
                            Decrypt2 = r0;
                            byteArrayInputStream = byteArrayInputStream5;
                            IOUtils.safeClose(Decrypt2);
                            IOUtils.safeClose(byteArrayInputStream);
                            IOUtils.safeClose(byteArrayOutputStream);
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = null;
                    byteArrayInputStream3 = byteArrayInputStream;
                    r0 = byteArrayOutputStream;
                    throw new CryptException("decrypt failed!", th);
                }
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
            }
        } else {
            if (!cipherFlag.endsWith(CIPHER_FLAG_SECOND)) {
                throw new CryptException("Cipher flag not found in cipher text!");
            }
            try {
                byteArrayInputStream2 = new ByteArrayInputStream(bArrCopyOfRange);
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream2 = null;
            }
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } catch (Throwable th6) {
                th = th6;
                byteArrayOutputStream2 = null;
            }
            try {
                Decrypt = CryptTools.decrypt("AES/CBC/PKCS7Padding", this.secureKeyStore.b(str), this.secureKeyStore.c(str), byteArrayInputStream2);
                byte[] bArr3 = new byte[1048576];
                while (true) {
                    int i2 = Decrypt.read(bArr3);
                    if (i2 == -1) {
                        byteArrayOutputStream2.flush();
                        byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                        IOUtils.safeClose(Decrypt);
                        IOUtils.safeClose(byteArrayInputStream2);
                        IOUtils.safeClose(byteArrayOutputStream2);
                        return byteArray2;
                    }
                    byteArrayOutputStream2.write(bArr3, 0, i2);
                }
            } catch (Throwable th7) {
                th = th7;
                ?? r92 = Decrypt;
                byteArrayInputStream4 = byteArrayInputStream2;
                r02 = r92;
                throw new CryptException("decrypt failed!", th);
            }
        }
    }

    private byte[] doEncrypt(byte[] bArr) throws Throwable {
        InputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        InputStream inputStream2;
        Throwable th;
        InputStream inputStreamEncrypt;
        InputStream inputStream3;
        InputStream byteArrayInputStream2;
        InputStream inputStreamEncrypt2 = null;
        if (this.secureKeyStore.c != null) {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        d dVar = this.secureKeyStore;
                        inputStreamEncrypt2 = CryptTools.encrypt("AES/CBC/PKCS7Padding", dVar.a(dVar.g), this.secureKeyStore.b(), byteArrayInputStream);
                        byte[] bArr2 = new byte[1048576];
                        while (true) {
                            int i = inputStreamEncrypt2.read(bArr2);
                            if (i == -1) {
                                byteArrayOutputStream.flush();
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                byte[] bytes = (CIPHER_FLAG_STARTER + this.secureKeyStore.g + "-First").getBytes();
                                int length = bytes.length;
                                byte[] bArr3 = new byte[byteArray.length + length];
                                System.arraycopy(byteArray, 0, bArr3, 0, byteArray.length);
                                System.arraycopy(bytes, 0, bArr3, byteArray.length, length);
                                IOUtils.safeClose(inputStreamEncrypt2);
                                IOUtils.safeClose(byteArrayInputStream);
                                IOUtils.safeClose(byteArrayOutputStream);
                                return bArr3;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i);
                        }
                    } catch (Throwable unused) {
                        InputStream inputStream4 = inputStreamEncrypt2;
                        inputStreamEncrypt2 = byteArrayInputStream;
                        inputStream = inputStream4;
                        try {
                            ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(bArr);
                            try {
                                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                try {
                                    inputStreamEncrypt = CryptTools.encrypt("AES/CBC/PKCS7Padding", this.secureKeyStore.a(), this.secureKeyStore.b(), byteArrayInputStream3);
                                    try {
                                        byte[] bArr4 = new byte[1048576];
                                        while (true) {
                                            int i2 = inputStreamEncrypt.read(bArr4);
                                            if (i2 == -1) {
                                                byteArrayOutputStream2.flush();
                                                byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                                                byte[] bytes2 = (CIPHER_FLAG_STARTER + this.secureKeyStore.g + "-Second").getBytes();
                                                int length2 = bytes2.length;
                                                byte[] bArr5 = new byte[byteArray2.length + length2];
                                                System.arraycopy(byteArray2, 0, bArr5, 0, byteArray2.length);
                                                System.arraycopy(bytes2, 0, bArr5, byteArray2.length, length2);
                                                IOUtils.safeClose(inputStreamEncrypt);
                                                IOUtils.safeClose(byteArrayInputStream3);
                                                IOUtils.safeClose(byteArrayOutputStream2);
                                                return bArr5;
                                            }
                                            byteArrayOutputStream2.write(bArr4, 0, i2);
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        byteArrayOutputStream = byteArrayOutputStream2;
                                        inputStreamEncrypt2 = byteArrayInputStream3;
                                        try {
                                            throw new CryptException("encrypt failed", th);
                                        } catch (Throwable th3) {
                                            inputStream2 = inputStreamEncrypt2;
                                            inputStreamEncrypt2 = inputStreamEncrypt;
                                            th = th3;
                                            byteArrayInputStream = inputStream2;
                                            IOUtils.safeClose(inputStreamEncrypt2);
                                            IOUtils.safeClose(byteArrayInputStream);
                                            IOUtils.safeClose(byteArrayOutputStream);
                                            throw th;
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    inputStreamEncrypt2 = byteArrayInputStream3;
                                    InputStream inputStream5 = inputStream;
                                    th = th;
                                    inputStreamEncrypt = inputStream5;
                                    throw new CryptException("encrypt failed", th);
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            inputStream2 = inputStreamEncrypt2;
                            inputStreamEncrypt2 = inputStream;
                            byteArrayInputStream = inputStream2;
                            IOUtils.safeClose(inputStreamEncrypt2);
                            IOUtils.safeClose(byteArrayInputStream);
                            IOUtils.safeClose(byteArrayOutputStream);
                            throw th;
                        }
                    }
                } catch (Throwable unused2) {
                    byteArrayOutputStream = null;
                    inputStreamEncrypt2 = byteArrayInputStream;
                    inputStream = null;
                }
            } catch (Throwable th7) {
                th = th7;
                byteArrayInputStream = null;
                byteArrayOutputStream = null;
            }
        } else {
            try {
                byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                try {
                    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                    inputStreamEncrypt2 = CryptTools.encrypt("AES/CBC/PKCS7Padding", this.secureKeyStore.a(), this.secureKeyStore.b(), byteArrayInputStream2);
                    byte[] bArr6 = new byte[1048576];
                    while (true) {
                        int i3 = inputStreamEncrypt2.read(bArr6);
                        if (i3 == -1) {
                            byteArrayOutputStream3.flush();
                            byte[] byteArray3 = byteArrayOutputStream3.toByteArray();
                            byte[] bytes3 = (CIPHER_FLAG_STARTER + this.secureKeyStore.g + "-Second").getBytes();
                            int length3 = bytes3.length;
                            byte[] bArr7 = new byte[byteArray3.length + length3];
                            System.arraycopy(byteArray3, 0, bArr7, 0, byteArray3.length);
                            System.arraycopy(bytes3, 0, bArr7, byteArray3.length, length3);
                            IOUtils.safeClose(inputStreamEncrypt2);
                            IOUtils.safeClose(byteArrayInputStream2);
                            return bArr7;
                        }
                        byteArrayOutputStream3.write(bArr6, 0, i3);
                    }
                } catch (Throwable th8) {
                    th = th8;
                    IOUtils.safeClose(inputStreamEncrypt2);
                    IOUtils.safeClose(byteArrayInputStream2);
                    throw th;
                }
            } catch (Throwable th9) {
                th = th9;
                inputStream3 = null;
            }
        }
    }

    private String getCipherFlag(byte[] bArr) {
        String str = new String(bArr);
        int iLastIndexOf = str.lastIndexOf(CIPHER_FLAG_STARTER);
        if (iLastIndexOf < 0) {
            return null;
        }
        return str.substring(iLastIndexOf + 3);
    }

    public static SecureCryptTools getInstance() {
        return a.a;
    }

    private List<CryptException> init(Context context) throws CryptException {
        List<CryptException> listA;
        try {
            this.lock.lock();
            if (this.initInvoked) {
                listA = Collections.emptyList();
            } else {
                this.initInvoked = true;
                this.secureKeyStore = new d();
                listA = this.secureKeyStore.a(context);
            }
            return listA;
        } finally {
            this.lock.unlock();
        }
    }

    public byte[] decrypt(byte[] bArr) throws CryptException {
        if (!this.initInvoked) {
            throw new CryptException("SecureCryptTools: please init firstly!");
        }
        try {
            try {
                this.lock.tryLock(3000L, TimeUnit.MILLISECONDS);
                return doDecrypt(bArr);
            } catch (InterruptedException unused) {
                throw new CryptException("SecureCryptTools: wait init time out!");
            }
        } finally {
            if (this.lock.isLocked()) {
                this.lock.unlock();
            }
        }
    }

    public byte[] encrypt(byte[] bArr) throws CryptException {
        if (!this.initInvoked) {
            throw new CryptException("SecureCryptTools: please init firstly!");
        }
        try {
            try {
                this.lock.tryLock(3000L, TimeUnit.MILLISECONDS);
                return doEncrypt(bArr);
            } catch (InterruptedException unused) {
                throw new CryptException("SecureCryptTools: wait init time out!");
            }
        } finally {
            if (this.lock.isLocked()) {
                this.lock.unlock();
            }
        }
    }
}
