package com.bun.miitmdid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface w extends IInterface {

    public static abstract class a extends Binder implements w {

        /* JADX INFO: renamed from: com.bun.miitmdid.w$a$a, reason: collision with other inner class name */
        public static class C0148a implements w {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static w f176a;
            public IBinder b;

            public C0148a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // com.bun.miitmdid.w
            public native boolean a();

            @Override // android.os.IInterface
            public native IBinder asBinder();

            @Override // com.bun.miitmdid.w
            public native String getAAID();

            @Override // com.bun.miitmdid.w
            public native String getOAID();

            @Override // com.bun.miitmdid.w
            public native String getVAID();

            @Override // com.bun.miitmdid.w
            public native boolean isSupported();
        }

        public static native w a(IBinder iBinder);

        public static native w b();
    }

    boolean a();

    String getAAID();

    String getOAID();

    String getVAID();

    boolean isSupported();
}
