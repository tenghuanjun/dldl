package com.bytedance.dr.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.bytedance.dr.aidl.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0151a extends Binder implements a {

        /* JADX INFO: renamed from: com.bytedance.dr.aidl.a$a$a, reason: collision with other inner class name */
        public static class C0152a implements a {
            public static a b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f367a;

            public C0152a(IBinder iBinder) {
                this.f367a = iBinder;
            }

            public String a(String str) {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.coolpad.deviceidsupport.IDeviceIdManager");
                    parcelObtain.writeString(str);
                    if (this.f367a.transact(2, parcelObtain, parcelObtain2, 0) || AbstractBinderC0151a.a() == null) {
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } else {
                        string = ((C0152a) AbstractBinderC0151a.a()).a(str);
                    }
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f367a;
            }
        }

        public static a a() {
            return C0152a.b;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.coolpad.deviceidsupport.IDeviceIdManager");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0152a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }
}
