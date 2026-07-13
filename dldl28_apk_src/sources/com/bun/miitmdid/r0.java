package com.bun.miitmdid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface r0 extends IInterface {

    public static abstract class a extends Binder implements r0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int f171a = 0;

        /* JADX INFO: renamed from: com.bun.miitmdid.r0$a$a, reason: collision with other inner class name */
        public static class C0147a implements r0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f172a;

            public C0147a(IBinder iBinder) {
                this.f172a = iBinder;
            }

            @Override // com.bun.miitmdid.r0
            public native void a(q0 q0Var);

            @Override // android.os.IInterface
            public native IBinder asBinder();

            @Override // com.bun.miitmdid.r0
            public native void b(q0 q0Var);
        }
    }

    void a(q0 q0Var);

    void b(q0 q0Var);
}
