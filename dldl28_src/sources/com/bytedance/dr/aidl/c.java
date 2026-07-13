package com.bytedance.dr.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {

        /* JADX INFO: renamed from: com.bytedance.dr.aidl.c$a$a, reason: collision with other inner class name */
        public static class C0154a implements c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f369a;

            public C0154a(IBinder iBinder) {
                this.f369a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f369a;
            }
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0154a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }
    }
}
