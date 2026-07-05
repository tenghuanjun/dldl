package com.ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadForbiddenAidlCallback extends IInterface {

    public static class Default implements IDownloadForbiddenAidlCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
        public boolean hasCallback() throws RemoteException {
            return false;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
        public void onCallback(List<String> list) throws RemoteException {
        }
    }

    boolean hasCallback() throws RemoteException;

    void onCallback(List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadForbiddenAidlCallback {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback";
        static final int TRANSACTION_hasCallback = 2;
        static final int TRANSACTION_onCallback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadForbiddenAidlCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDownloadForbiddenAidlCallback)) {
                return (IDownloadForbiddenAidlCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onCallback(parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zHasCallback = hasCallback();
            parcel2.writeNoException();
            parcel2.writeInt(zHasCallback ? 1 : 0);
            return true;
        }

        private static class Proxy implements IDownloadForbiddenAidlCallback {
            public static IDownloadForbiddenAidlCallback sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
            public void onCallback(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallback(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
            public boolean hasCallback() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasCallback();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) {
            if (Proxy.sDefaultImpl != null || iDownloadForbiddenAidlCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadForbiddenAidlCallback;
            return true;
        }

        public static IDownloadForbiddenAidlCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
