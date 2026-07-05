package com.getui.gtc.dyc;

import android.util.Base64;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.util.io.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class h implements Serializable {
    private String a;
    private long c;
    private String d;
    private Map<String, String> e;

    public static h d(String str) throws Throwable {
        Throwable th;
        ObjectInputStream objectInputStream;
        try {
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(SecureCryptTools.getInstance().decrypt(Base64.decode(str.getBytes(), 0))));
                try {
                    h hVar = (h) objectInputStream.readObject();
                    IOUtils.safeClose(objectInputStream);
                    return hVar;
                } catch (IOException | ClassNotFoundException e) {
                    e = e;
                    com.getui.gtc.dyc.a.a.a.a(e);
                    IOUtils.safeClose(objectInputStream);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.safeClose(null);
                throw th;
            }
        } catch (IOException | ClassNotFoundException e2) {
            e = e2;
            objectInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.safeClose(null);
            throw th;
        }
    }

    public long a() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(Map<String, String> map) {
        this.e = map;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public Map<String, String> d() {
        return this.e;
    }

    public String e() throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            objectOutputStream.writeObject(this);
            IOUtils.safeClose(objectOutputStream);
        } catch (IOException e2) {
            e = e2;
            objectOutputStream2 = objectOutputStream;
            com.getui.gtc.dyc.a.a.a.a(e);
            IOUtils.safeClose(objectOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            IOUtils.safeClose(objectOutputStream2);
            throw th;
        }
        objectOutputStream2 = null;
        return Base64.encodeToString(SecureCryptTools.getInstance().encrypt(byteArrayOutputStream.toByteArray()), 0);
    }
}
