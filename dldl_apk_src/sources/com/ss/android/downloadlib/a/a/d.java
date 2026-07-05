package com.ss.android.downloadlib.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.k;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface d extends IInterface {
    void a(b bVar) throws RemoteException;

    public static abstract class a extends Binder implements d {
        private static String a = "";

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(a);
            if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) {
                return new C0091a(iBinder);
            }
            return (d) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(a);
                a(parcel.readInt() != 0 ? b.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(a);
            return true;
        }

        /* JADX INFO: renamed from: com.ss.android.downloadlib.a.a.d$a$a, reason: collision with other inner class name */
        private static class C0091a implements d {
            private IBinder a;

            C0091a(IBinder iBinder) {
                if (TextUtils.isEmpty(a.a)) {
                    JSONObject jSONObjectJ = k.j();
                    String unused = a.a = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("t"), jSONObjectJ.optString(com.igexin.push.core.d.c.d));
                }
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // com.ss.android.downloadlib.a.a.d
            public void a(b bVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.a);
                    if (bVar != null) {
                        parcelObtain.writeInt(1);
                        bVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
