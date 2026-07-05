package com.ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadForbiddenAidlHandler extends IInterface {

    public static class Default implements IDownloadForbiddenAidlHandler {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler
        public boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException {
            return false;
        }
    }

    boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadForbiddenAidlHandler {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler";
        static final int TRANSACTION_onForbidden = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadForbiddenAidlHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDownloadForbiddenAidlHandler)) {
                return (IDownloadForbiddenAidlHandler) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean zOnForbidden = onForbidden(IDownloadForbiddenAidlCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(zOnForbidden ? 1 : 0);
            return true;
        }

        private static class Proxy implements IDownloadForbiddenAidlHandler {
            public static IDownloadForbiddenAidlHandler sDefaultImpl;
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

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler
            public boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDownloadForbiddenAidlCallback != null ? iDownloadForbiddenAidlCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onForbidden(iDownloadForbiddenAidlCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadForbiddenAidlHandler iDownloadForbiddenAidlHandler) {
            if (Proxy.sDefaultImpl != null || iDownloadForbiddenAidlHandler == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadForbiddenAidlHandler;
            return true;
        }

        public static IDownloadForbiddenAidlHandler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
