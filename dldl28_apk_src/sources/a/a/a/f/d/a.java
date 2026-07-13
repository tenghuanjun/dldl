package a.a.a.f.d;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: HWIDInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: a.a.a.f.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HWIDInterface.java */
    public static final class C0010a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IBinder f132a;

        public C0010a(IBinder iBinder) {
            this.f132a = iBinder;
        }

        public boolean a() {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            boolean z = false;
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                this.f132a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                if (parcelObtain2.readInt() == 0) {
                    z = true;
                }
            } catch (Throwable unused) {
                parcelObtain.recycle();
                parcelObtain2.recycle();
            }
            parcelObtain.recycle();
            parcelObtain2.recycle();
            return z;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f132a;
        }

        public String b() {
            String string;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                try {
                    parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                    this.f132a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    string = parcelObtain2.readString();
                } catch (Exception e) {
                    e.printStackTrace();
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                    string = null;
                }
                return string;
            } finally {
                parcelObtain.recycle();
                parcelObtain2.recycle();
            }
        }
    }
}
