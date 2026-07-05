package com.alicom.tools.networking;

import android.content.Context;
import android.util.Log;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class SSLFactory {
    private static final String KEY_STORE_CLIENT_PATH = "KEY_STORE_CLIENT_PATH";
    private static final String KEY_STORE_PASSWORD = "KEY_STORE_PASSWORD";
    private static final String KEY_STORE_TRUST_PASSWORD = "KEY_STORE_TRUST_PASSWORD";
    private static final String KEY_STORE_TRUST_PATH = "KEY_STORE_TRUST_PATH";
    private static final String KEY_STORE_TYPE_BKS = "KEY_STORE_TYPE_BKS";
    private static final String KEY_STORE_TYPE_P12 = "KEY_STORE_TYPE_P12";
    private String typeBks = "bks";
    private String typePkcs = "PKCS12";
    private String client = "client.p12";
    private String trust = "client.truststore";
    private String pass = "123456";
    private String trustPass = "123456";

    private SSLContext getSSLContext(Context context) {
        try {
            KeyStore keyStore = KeyStore.getInstance(this.typePkcs);
            KeyStore keyStore2 = KeyStore.getInstance(this.typeBks);
            InputStream inputStreamOpen = context.getResources().getAssets().open(this.client);
            InputStream inputStreamOpen2 = context.getResources().getAssets().open(this.trust);
            try {
                try {
                    keyStore.load(inputStreamOpen, this.pass.toCharArray());
                    keyStore2.load(inputStreamOpen2, this.trustPass.toCharArray());
                } catch (Throwable th) {
                    try {
                        inputStreamOpen.close();
                    } catch (Exception unused) {
                    }
                    try {
                        inputStreamOpen2.close();
                        throw th;
                    } catch (Exception unused2) {
                        throw th;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                inputStreamOpen.close();
            } catch (Exception unused3) {
            }
            try {
                inputStreamOpen2.close();
            } catch (Exception unused4) {
            }
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore2);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, this.pass.toCharArray());
            sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            return sSLContext;
        } catch (Exception e2) {
            Log.e("pop request tag", e2.getMessage(), e2);
            return null;
        }
    }

    private void parseAttr(HashMap<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        if (map.get(KEY_STORE_TYPE_BKS) != null) {
            this.typeBks = map.get(KEY_STORE_TYPE_BKS);
        }
        if (map.get(KEY_STORE_TYPE_P12) != null) {
            this.typePkcs = map.get(KEY_STORE_TYPE_P12);
        }
        if (map.get(KEY_STORE_CLIENT_PATH) != null) {
            this.client = map.get(KEY_STORE_CLIENT_PATH);
        }
        if (map.get(KEY_STORE_TRUST_PATH) != null) {
            this.trust = map.get(KEY_STORE_TRUST_PATH);
        }
        if (map.get(KEY_STORE_PASSWORD) != null) {
            this.pass = map.get(KEY_STORE_PASSWORD);
        }
        if (map.get(KEY_STORE_TRUST_PASSWORD) != null) {
            this.trustPass = map.get(KEY_STORE_TRUST_PASSWORD);
        }
    }
}
