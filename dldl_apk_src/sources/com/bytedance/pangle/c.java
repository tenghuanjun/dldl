package com.bytedance.pangle;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.bytedance.pangle.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface c extends IInterface {
    void a(int i);

    void a(int i, d dVar);

    boolean a(String str);

    boolean a(String str, String str2);

    int b(String str);

    public static abstract class a extends Binder implements c {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "com.bytedance.pangle.IPackageManager");
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.bytedance.pangle.IPackageManager");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                return (c) iInterfaceQueryLocalInterface;
            }
            return new C0015a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            d c0016a;
            if (i == 1) {
                parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                boolean zA = a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zA ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                int iB = b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iB);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                boolean zA2 = a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zA2 ? 1 : 0);
                return true;
            }
            if (i != 4) {
                if (i != 5) {
                    if (i == 1598968902) {
                        parcel2.writeString("com.bytedance.pangle.IPackageManager");
                        return true;
                    }
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            parcel.enforceInterface("com.bytedance.pangle.IPackageManager");
            int i3 = parcel.readInt();
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                c0016a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.bytedance.pangle.IPluginInstallListener");
                if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof d)) {
                    c0016a = (d) iInterfaceQueryLocalInterface;
                } else {
                    c0016a = new d.a.C0016a(strongBinder);
                }
            }
            a(i3, c0016a);
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: renamed from: com.bytedance.pangle.c$a$a, reason: collision with other inner class name */
        static class C0015a implements c {
            public static c a;
            private IBinder b;

            C0015a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.b;
            }

            @Override // com.bytedance.pangle.c
            public final boolean a(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeString(str);
                    if (!this.b.transact(1, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().a(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.c
            public final int b(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeString(str);
                    if (!this.b.transact(2, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().b(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.c
            public final boolean a(String str, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.b.transact(3, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        return a.a().a(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.c
            public final void a(int i, d dVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.b.transact(4, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(i, dVar);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bytedance.pangle.c
            public final void a(int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bytedance.pangle.IPackageManager");
                    parcelObtain.writeInt(i);
                    if (!this.b.transact(5, parcelObtain, parcelObtain2, 0) && a.a() != null) {
                        a.a().a(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static c a() {
            return C0015a.a;
        }
    }
}
