package com.bytedance.dr.impl;

import android.os.IBinder;
import android.os.Parcel;
import android.util.Pair;
import com.bytedance.bdtracker.d5;
import com.bytedance.dr.aidl.g;

/* JADX INFO: loaded from: classes2.dex */
public class f implements d5.b<com.bytedance.dr.aidl.g, Pair<String, Boolean>> {
    public f(e eVar) {
    }

    @Override // com.bytedance.bdtracker.d5.b
    public com.bytedance.dr.aidl.g a(IBinder iBinder) {
        return g.a.a(iBinder);
    }

    @Override // com.bytedance.bdtracker.d5.b
    public Pair<String, Boolean> a(com.bytedance.dr.aidl.g gVar) {
        com.bytedance.dr.aidl.g gVar2 = gVar;
        if (gVar2 == null) {
            return null;
        }
        g.a.C0158a c0158a = (g.a.C0158a) gVar2;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            c0158a.f373a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            String string = parcelObtain2.readString();
            parcelObtain2.recycle();
            parcelObtain.recycle();
            parcelObtain = Parcel.obtain();
            parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                c0158a.f373a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                boolean z = parcelObtain2.readInt() != 0;
                parcelObtain2.recycle();
                parcelObtain.recycle();
                return new Pair<>(string, Boolean.valueOf(z));
            } finally {
            }
        } finally {
        }
    }
}
