package a.a.a.f.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.Signature;
import android.os.IBinder;
import java.security.MessageDigest;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.UByte;

/* JADX INFO: compiled from: OppoDeviceIDHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f126a;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    public ServiceConnection c = new a();

    /* JADX INFO: compiled from: OppoDeviceIDHelper.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.a.a.g.b.a("OppoDeviceIDHelper", "onServiceConnected");
            try {
                d.this.b.put(iBinder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public d(Context context) {
        this.f126a = context;
    }

    public final String a() {
        try {
            Signature[] signatureArr = this.f126a.getPackageManager().getPackageInfo(this.f126a.getPackageName(), 64).signatures;
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    sb.append(Integer.toHexString((b & UByte.MAX_VALUE) | 256).substring(1, 3));
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
