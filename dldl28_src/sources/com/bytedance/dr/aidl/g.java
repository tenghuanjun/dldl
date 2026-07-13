package com.bytedance.dr.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface g extends IInterface {

    public static abstract class a extends Binder implements g {

        /* JADX INFO: renamed from: com.bytedance.dr.aidl.g$a$a, reason: collision with other inner class name */
        public static class C0158a implements g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f373a;

            public C0158a(IBinder iBinder) {
                this.f373a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f373a;
            }
        }

        public static g a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof g)) ? new C0158a(iBinder) : (g) iInterfaceQueryLocalInterface;
        }
    }
}
