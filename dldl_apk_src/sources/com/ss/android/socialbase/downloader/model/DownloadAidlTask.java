package com.ss.android.socialbase.downloader.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ss.android.socialbase.downloader.depend.IChunkCntAidlCalculator;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlInterceptor;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlListener;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback;
import com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface DownloadAidlTask extends IInterface {

    public static class Default implements DownloadAidlTask {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IChunkCntAidlCalculator getChunkStrategy() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadAidlDepend getDepend() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadDiskSpaceAidlHandler getDiskSpaceHandler() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadCompleteAidlHandler getDownloadCompleteAidlHandlerByIndex(int i) throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public int getDownloadCompleteHandlerSize() throws RemoteException {
            return 0;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public DownloadInfo getDownloadInfo() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadAidlListener getDownloadListenerByIndex(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public int getDownloadListenerSize(int i) throws RemoteException {
            return 0;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadNotificationEventAidlListener getDownloadNotificationEventListener() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadAidlFileProvider getFileProvider() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadForbiddenAidlHandler getForbiddenHandler() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadAidlInterceptor getInterceptor() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadAidlMonitorDepend getMonitorDepend() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public INotificationClickAidlCallback getNotificationClickCallback() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IRetryDelayTimeAidlCalculator getRetryDelayTimeCalculator() throws RemoteException {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
        public IDownloadAidlListener getSingleDownloadListener(int i) throws RemoteException {
            return null;
        }
    }

    IChunkCntAidlCalculator getChunkStrategy() throws RemoteException;

    IDownloadAidlDepend getDepend() throws RemoteException;

    IDownloadDiskSpaceAidlHandler getDiskSpaceHandler() throws RemoteException;

    IDownloadCompleteAidlHandler getDownloadCompleteAidlHandlerByIndex(int i) throws RemoteException;

    int getDownloadCompleteHandlerSize() throws RemoteException;

    DownloadInfo getDownloadInfo() throws RemoteException;

    IDownloadAidlListener getDownloadListenerByIndex(int i, int i2) throws RemoteException;

    int getDownloadListenerSize(int i) throws RemoteException;

    IDownloadNotificationEventAidlListener getDownloadNotificationEventListener() throws RemoteException;

    IDownloadAidlFileProvider getFileProvider() throws RemoteException;

    IDownloadForbiddenAidlHandler getForbiddenHandler() throws RemoteException;

    IDownloadAidlInterceptor getInterceptor() throws RemoteException;

    IDownloadAidlMonitorDepend getMonitorDepend() throws RemoteException;

    INotificationClickAidlCallback getNotificationClickCallback() throws RemoteException;

    IRetryDelayTimeAidlCalculator getRetryDelayTimeCalculator() throws RemoteException;

    IDownloadAidlListener getSingleDownloadListener(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements DownloadAidlTask {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.model.DownloadAidlTask";
        static final int TRANSACTION_getChunkStrategy = 2;
        static final int TRANSACTION_getDepend = 9;
        static final int TRANSACTION_getDiskSpaceHandler = 12;
        static final int TRANSACTION_getDownloadCompleteAidlHandlerByIndex = 16;
        static final int TRANSACTION_getDownloadCompleteHandlerSize = 15;
        static final int TRANSACTION_getDownloadInfo = 1;
        static final int TRANSACTION_getDownloadListenerByIndex = 4;
        static final int TRANSACTION_getDownloadListenerSize = 3;
        static final int TRANSACTION_getDownloadNotificationEventListener = 6;
        static final int TRANSACTION_getFileProvider = 14;
        static final int TRANSACTION_getForbiddenHandler = 10;
        static final int TRANSACTION_getInterceptor = 8;
        static final int TRANSACTION_getMonitorDepend = 13;
        static final int TRANSACTION_getNotificationClickCallback = 7;
        static final int TRANSACTION_getRetryDelayTimeCalculator = 11;
        static final int TRANSACTION_getSingleDownloadListener = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static DownloadAidlTask asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof DownloadAidlTask)) {
                return (DownloadAidlTask) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo downloadInfo = getDownloadInfo();
                    parcel2.writeNoException();
                    if (downloadInfo != null) {
                        parcel2.writeInt(1);
                        downloadInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IChunkCntAidlCalculator chunkStrategy = getChunkStrategy();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(chunkStrategy != null ? chunkStrategy.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int downloadListenerSize = getDownloadListenerSize(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(downloadListenerSize);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadAidlListener downloadListenerByIndex = getDownloadListenerByIndex(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(downloadListenerByIndex != null ? downloadListenerByIndex.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadAidlListener singleDownloadListener = getSingleDownloadListener(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(singleDownloadListener != null ? singleDownloadListener.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadNotificationEventAidlListener downloadNotificationEventListener = getDownloadNotificationEventListener();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(downloadNotificationEventListener != null ? downloadNotificationEventListener.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    INotificationClickAidlCallback notificationClickCallback = getNotificationClickCallback();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(notificationClickCallback != null ? notificationClickCallback.asBinder() : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadAidlInterceptor interceptor = getInterceptor();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(interceptor != null ? interceptor.asBinder() : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadAidlDepend depend = getDepend();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(depend != null ? depend.asBinder() : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadForbiddenAidlHandler forbiddenHandler = getForbiddenHandler();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(forbiddenHandler != null ? forbiddenHandler.asBinder() : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    IRetryDelayTimeAidlCalculator retryDelayTimeCalculator = getRetryDelayTimeCalculator();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(retryDelayTimeCalculator != null ? retryDelayTimeCalculator.asBinder() : null);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadDiskSpaceAidlHandler diskSpaceHandler = getDiskSpaceHandler();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(diskSpaceHandler != null ? diskSpaceHandler.asBinder() : null);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadAidlMonitorDepend monitorDepend = getMonitorDepend();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(monitorDepend != null ? monitorDepend.asBinder() : null);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadAidlFileProvider fileProvider = getFileProvider();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(fileProvider != null ? fileProvider.asBinder() : null);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int downloadCompleteHandlerSize = getDownloadCompleteHandlerSize();
                    parcel2.writeNoException();
                    parcel2.writeInt(downloadCompleteHandlerSize);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    IDownloadCompleteAidlHandler downloadCompleteAidlHandlerByIndex = getDownloadCompleteAidlHandlerByIndex(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(downloadCompleteAidlHandlerByIndex != null ? downloadCompleteAidlHandlerByIndex.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements DownloadAidlTask {
            public static DownloadAidlTask sDefaultImpl;
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

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public DownloadInfo getDownloadInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IChunkCntAidlCalculator getChunkStrategy() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChunkStrategy();
                    }
                    parcelObtain2.readException();
                    return IChunkCntAidlCalculator.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public int getDownloadListenerSize(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadListenerSize(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlListener getDownloadListenerByIndex(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadListenerByIndex(i, i2);
                    }
                    parcelObtain2.readException();
                    return IDownloadAidlListener.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlListener getSingleDownloadListener(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSingleDownloadListener(i);
                    }
                    parcelObtain2.readException();
                    return IDownloadAidlListener.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadNotificationEventAidlListener getDownloadNotificationEventListener() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadNotificationEventListener();
                    }
                    parcelObtain2.readException();
                    return IDownloadNotificationEventAidlListener.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public INotificationClickAidlCallback getNotificationClickCallback() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationClickCallback();
                    }
                    parcelObtain2.readException();
                    return INotificationClickAidlCallback.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlInterceptor getInterceptor() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterceptor();
                    }
                    parcelObtain2.readException();
                    return IDownloadAidlInterceptor.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlDepend getDepend() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDepend();
                    }
                    parcelObtain2.readException();
                    return IDownloadAidlDepend.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadForbiddenAidlHandler getForbiddenHandler() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getForbiddenHandler();
                    }
                    parcelObtain2.readException();
                    return IDownloadForbiddenAidlHandler.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IRetryDelayTimeAidlCalculator getRetryDelayTimeCalculator() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRetryDelayTimeCalculator();
                    }
                    parcelObtain2.readException();
                    return IRetryDelayTimeAidlCalculator.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadDiskSpaceAidlHandler getDiskSpaceHandler() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDiskSpaceHandler();
                    }
                    parcelObtain2.readException();
                    return IDownloadDiskSpaceAidlHandler.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlMonitorDepend getMonitorDepend() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMonitorDepend();
                    }
                    parcelObtain2.readException();
                    return IDownloadAidlMonitorDepend.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadAidlFileProvider getFileProvider() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFileProvider();
                    }
                    parcelObtain2.readException();
                    return IDownloadAidlFileProvider.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public int getDownloadCompleteHandlerSize() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadCompleteHandlerSize();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.model.DownloadAidlTask
            public IDownloadCompleteAidlHandler getDownloadCompleteAidlHandlerByIndex(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadCompleteAidlHandlerByIndex(i);
                    }
                    parcelObtain2.readException();
                    return IDownloadCompleteAidlHandler.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(DownloadAidlTask downloadAidlTask) {
            if (Proxy.sDefaultImpl != null || downloadAidlTask == null) {
                return false;
            }
            Proxy.sDefaultImpl = downloadAidlTask;
            return true;
        }

        public static DownloadAidlTask getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
