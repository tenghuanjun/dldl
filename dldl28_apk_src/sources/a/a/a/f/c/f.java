package a.a.a.f.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: ZTEDeviceIDHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f130a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    public ServiceConnection c = new a();

    /* JADX INFO: compiled from: ZTEDeviceIDHelper.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                a.a.a.g.b.a("ZTEDeviceIDHelper", "onServiceConnected");
                f.this.b.put(iBinder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public f(Context context) {
        this.f130a = context;
    }

    public String a() throws Throwable {
        Context context;
        ServiceConnection serviceConnection;
        String string;
        String str = "";
        try {
            Intent intent = new Intent();
            intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
            intent.setAction("com.bun.msa.action.bindto.service");
            intent.putExtra("com.bun.msa.param.pkgname", this.f130a.getPackageName());
            boolean zBindService = this.f130a.bindService(intent, this.c, 1);
            a.a.a.g.b.a("ZTEDeviceIDHelper", "getOAID isBind=" + zBindService);
            try {
                if (!zBindService) {
                    return "";
                }
                try {
                    IBinder iBinderTake = this.b.take();
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        try {
                            parcelObtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                            iBinderTake.transact(1, parcelObtain, parcelObtain2, 0);
                            parcelObtain2.readException();
                            string = parcelObtain2.readString();
                            parcelObtain.recycle();
                        } catch (Throwable th) {
                            parcelObtain.recycle();
                            parcelObtain2.recycle();
                            throw th;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        parcelObtain.recycle();
                        string = null;
                    }
                    parcelObtain2.recycle();
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    a.a.a.g.b.a("ZTEDeviceIDHelper", "getOAID oaid:" + string);
                    try {
                        context = this.f130a;
                        serviceConnection = this.c;
                    } catch (Exception e3) {
                        e = e3;
                        str = string;
                        a.a.a.g.b.a("ZTEDeviceIDHelper", "getOAID hw service not found");
                        e.printStackTrace();
                        return str;
                    }
                } catch (Exception e4) {
                    e = e4;
                    str = string;
                    e.printStackTrace();
                    context = this.f130a;
                    String str2 = str;
                    serviceConnection = this.c;
                    string = str2;
                } catch (Throwable th2) {
                    th = th2;
                    this.f130a.unbindService(this.c);
                    throw th;
                }
                context.unbindService(serviceConnection);
                return string;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }
}
