package com.hihonor.ads.identifier;

import android.content.Context;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AdvertisingIdClient {

    public static final class Info {
        public String id;
        public boolean isLimit;
    }

    public static native Info getAdvertisingIdInfo(Context context);

    public static native boolean isAdvertisingIdAvailable(Context context);
}
