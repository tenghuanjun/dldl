package com.bytedance.dr.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;
import com.bytedance.bdtracker.d5;
import com.bytedance.dr.aidl.d;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends b<com.bytedance.dr.aidl.d> {

    /* JADX INFO: renamed from: com.bytedance.dr.impl.a$a, reason: collision with other inner class name */
    public class C0159a implements d5.b<com.bytedance.dr.aidl.d, String> {
        public C0159a(a aVar) {
        }

        @Override // com.bytedance.bdtracker.d5.b
        public com.bytedance.dr.aidl.d a(IBinder iBinder) {
            return d.a.a(iBinder);
        }

        @Override // com.bytedance.bdtracker.d5.b
        public String a(com.bytedance.dr.aidl.d dVar) {
            com.bytedance.dr.aidl.d dVar2 = dVar;
            if (dVar2 == null) {
                return null;
            }
            d.a.C0155a c0155a = (d.a.C0155a) dVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(IDidAidlInterface.Stub.DESCRIPTOR);
                c0155a.f370a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public a() {
        super("com.asus.msa.SupplementaryDID");
    }

    @Override // com.bytedance.dr.impl.b
    public Intent a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        return intent;
    }

    @Override // com.bytedance.dr.impl.b
    public d5.b<com.bytedance.dr.aidl.d, String> a() {
        return new C0159a(this);
    }

    @Override // com.bytedance.dr.OaidApi
    public String getName() {
        return "ASUS";
    }
}
