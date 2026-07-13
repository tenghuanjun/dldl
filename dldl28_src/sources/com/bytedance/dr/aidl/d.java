package com.bytedance.dr.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;

/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {

    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: com.bytedance.dr.aidl.d$a$a, reason: collision with other inner class name */
        public static class C0155a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f370a;

            public C0155a(IBinder iBinder) {
                this.f370a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f370a;
            }
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IDidAidlInterface.Stub.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0155a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }
    }
}
