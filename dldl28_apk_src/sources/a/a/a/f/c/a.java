package a.a.a.f.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: ASUSDeviceIDHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f120a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    public ServiceConnection c = new ServiceConnectionC0009a();

    /* JADX INFO: renamed from: a.a.a.f.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASUSDeviceIDHelper.java */
    public class ServiceConnectionC0009a implements ServiceConnection {
        public ServiceConnectionC0009a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                a.this.b.put(iBinder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public a(Context context) {
        this.f120a = context;
    }

    public String a() throws Throwable {
        Context context;
        String string;
        ServiceConnection serviceConnection;
        String str = "";
        try {
            Intent intent = new Intent();
            intent.setAction("com.asus.msa.action.ACCESS_DID");
            intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
            try {
                if (!this.f120a.bindService(intent, this.c, 1)) {
                    return "";
                }
                try {
                    IBinder iBinderTake = this.b.take();
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken(IDidAidlInterface.Stub.DESCRIPTOR);
                        iBinderTake.transact(3, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } catch (Throwable th) {
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                        th.printStackTrace();
                        string = null;
                    }
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                } catch (Exception e) {
                    e = e;
                }
                try {
                    a.a.a.g.b.a("ASUSDeviceIDHelper", "getOAID oaid:" + string);
                    try {
                        context = this.f120a;
                        serviceConnection = this.c;
                    } catch (Exception e2) {
                        e = e2;
                        str = string;
                        a.a.a.g.b.a("ASUSDeviceIDHelper", "getOAID asus service not found;");
                        e.printStackTrace();
                        return str;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str = string;
                    e.printStackTrace();
                    context = this.f120a;
                    string = str;
                    serviceConnection = this.c;
                } catch (Throwable th2) {
                    th = th2;
                    this.f120a.unbindService(this.c);
                    throw th;
                }
                context.unbindService(serviceConnection);
                return string;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }
}
