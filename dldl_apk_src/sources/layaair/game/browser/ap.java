package layaair.game.browser;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.tencent.open.SocialConstants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.microedition.khronos.opengles.GL10;
import layaair.game.Notifycation.LayaAlarmReceiver;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ap {
    private static NotificationManager e;
    private static Intent f;
    int a = 10;
    aq[] b = new aq[10];
    public float c = 1.0f;
    public float d = 1.0f;

    public ap() {
        for (int i = 0; i < 10; i++) {
            this.b[i] = new aq();
            aq[] aqVarArr = this.b;
            aqVarArr[i].d = -1;
            aqVarArr[i].f = 0;
            aqVarArr[i].g = 0;
            aqVarArr[i].e = 0;
        }
        a(0.8f);
    }

    public static void a(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(com.igexin.push.core.b.l);
        e = notificationManager;
        notificationManager.cancelAll();
    }

    public static void a(Context context, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(com.igexin.push.core.b.l);
        e = notificationManager;
        notificationManager.cancel(i);
    }

    public static void a(Context context, int i, long j, long j2, String str, String str2, String str3) {
        Log.i("0", "========setRepeatingAlarm");
        if (context == null) {
            return;
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, (Class<?>) LayaAlarmReceiver.class);
        intent.putExtra("id", i);
        intent.putExtra("ticker", str);
        intent.putExtra("title", str2);
        intent.putExtra(SocialConstants.PARAM_APP_DESC, str3);
        alarmManager.setRepeating(0, System.currentTimeMillis() + j, j2, PendingIntent.getBroadcast(context, i, intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25));
    }

    public static void a(Context context, int i, String str, String str2, String str3) {
        e = (NotificationManager) context.getSystemService(com.igexin.push.core.b.l);
        Notification notification = new Notification(context.getResources().getIdentifier("ic_launcher", "drawable", context.getPackageName()), str, System.currentTimeMillis());
        notification.defaults = -1;
        f = new Intent(context, context.getClass());
        notification.setLatestEventInfo(context, str2, str3, PendingIntent.getActivity(context, 0, f, 0));
        e.notify(i, notification);
    }

    public static byte[] a(GL10 gl10, int i, int i2) {
        byte[] bArrArray;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i3 = (i * i2) << 2;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i3);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        gl10.glReadPixels(0, 0, i, i2, 6408, 5121, byteBufferAllocateDirect);
        try {
            bArrArray = byteBufferAllocateDirect.array();
        } catch (UnsupportedOperationException unused) {
            bArrArray = new byte[i3];
            byteBufferAllocateDirect.get(bArrArray);
        }
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = 0; i5 < i2 / 2; i5++) {
                int i6 = ((i * i5) + i4) << 2;
                int i7 = ((((i2 - i5) - 1) * i) + i4) << 2;
                byte b = bArrArray[i6];
                int i8 = i6 + 1;
                byte b2 = bArrArray[i8];
                int i9 = i6 + 2;
                byte b3 = bArrArray[i9];
                int i10 = i6 + 3;
                byte b4 = bArrArray[i10];
                bArrArray[i6] = bArrArray[i7];
                int i11 = i7 + 1;
                bArrArray[i8] = bArrArray[i11];
                int i12 = i7 + 2;
                bArrArray[i9] = bArrArray[i12];
                int i13 = i7 + 3;
                bArrArray[i10] = bArrArray[i13];
                bArrArray[i7] = b;
                bArrArray[i11] = b2;
                bArrArray[i12] = b3;
                bArrArray[i13] = b4;
            }
        }
        Log.e("temp", ">>>>>>>>>>>>>>>>>>>>>>>useTimenew" + (System.currentTimeMillis() - jCurrentTimeMillis));
        return bArrArray;
    }

    public final void a(float f2) {
        this.a = Math.round((float) (((double) (f2 / 10.0f)) * 0.3937008d));
    }

    final void a(int i, int i2, int i3) {
        for (int i4 = 0; i4 < 10; i4++) {
            if (this.b[i4].d < 0) {
                aq[] aqVarArr = this.b;
                aqVarArr[i4].d = i;
                aqVarArr[i4].f = i2;
                aqVarArr[i4].g = i3;
                aqVarArr[i4].e = aq.b;
                return;
            }
        }
    }
}
