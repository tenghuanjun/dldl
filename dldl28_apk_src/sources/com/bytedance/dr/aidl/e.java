package com.bytedance.dr.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface e extends IInterface {

    public static abstract class a extends Binder implements e {

        /* JADX INFO: renamed from: com.bytedance.dr.aidl.e$a$a, reason: collision with other inner class name */
        public static class C0156a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f371a;

            public C0156a(IBinder iBinder) {
                this.f371a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f371a;
            }
        }

        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof e)) ? new C0156a(iBinder) : (e) iInterfaceQueryLocalInterface;
        }
    }
}
