package com.huawei.nfc.sdk.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.nfc.sdk.service.ICUPOnlinePayCallBackService;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ICUPOnlinePayService extends IInterface {

    public abstract class Stub extends Binder implements ICUPOnlinePayService {
        private static final String DESCRIPTOR = "com.huawei.nfc.sdk.service.ICUPOnlinePayService";
        static final int TRANSACTION_getUnionOnlinePayStatus = 2;
        static final int TRANSACTION_supportCapacity = 1;

        class Proxy implements ICUPOnlinePayService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.huawei.nfc.sdk.service.ICUPOnlinePayService
            public void getUnionOnlinePayStatus(ICUPOnlinePayCallBackService iCUPOnlinePayCallBackService) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCUPOnlinePayCallBackService != null ? iCUPOnlinePayCallBackService.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.ICUPOnlinePayService
            public boolean supportCapacity(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICUPOnlinePayService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICUPOnlinePayService)) ? new Proxy(iBinder) : (ICUPOnlinePayService) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zSupportCapacity = supportCapacity(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zSupportCapacity ? 1 : 0);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            getUnionOnlinePayStatus(ICUPOnlinePayCallBackService.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    void getUnionOnlinePayStatus(ICUPOnlinePayCallBackService iCUPOnlinePayCallBackService);

    boolean supportCapacity(String str);
}
