package layaair.game.Notifycation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.tencent.open.SocialConstants;
import java.util.Calendar;
import layaair.game.browser.ap;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class LayaNotifyManager {
    public static final int ALARM_REPEAT_TYPE_DAY = 2;
    public static final int ALARM_REPEAT_TYPE_HOUR = 3;
    public static final int ALARM_REPEAT_TYPE_MINUTE = 4;
    public static final int ALARM_REPEAT_TYPE_MONTH = 1;
    public static final int ALARM_REPEAT_TYPE_SECOND = 5;
    public static final int ALARM_REPEAT_TYPE_YEAR = 0;
    private static LayaNotifyManager g_spLayaNotifyManager;

    public static LayaNotifyManager GetInstance() {
        if (g_spLayaNotifyManager == null) {
            g_spLayaNotifyManager = new LayaNotifyManager();
        }
        return g_spLayaNotifyManager;
    }

    public static void deleteAlarmAndNotify(int i) {
        Context context = LayaConch5.GetInstance().mCtx;
        if (context != null) {
            Log.i("0", "========deleteAlarm");
            ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, i, new Intent(context, (Class<?>) LayaAlarmReceiver.class), DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25));
        }
        removeNotify(i);
    }

    public static void removeAllNotify() {
        Log.i("0", "==============removeAllNotify");
        ap.a(LayaConch5.GetInstance().mCtx);
    }

    public static void removeNotify(int i) {
        Log.i("0", "==============removeNotify");
        ap.a(LayaConch5.GetInstance().mCtx, i);
    }

    public static void setOnceNotify(int i, int i2, String str, String str2, String str3) {
        Log.i("0", "==============setOnceNotify id=" + i + ",p_nStartTime=" + i2);
        Context context = LayaConch5.GetInstance().mCtx;
        long j = (long) i2;
        if (context != null) {
            Log.i("0", "========setOnceAlarm id=" + i + ",p_nStartTime=" + j);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, (Class<?>) LayaAlarmReceiver.class);
            intent.putExtra("id", i);
            intent.putExtra("ticker", str);
            intent.putExtra("title", str2);
            intent.putExtra(SocialConstants.PARAM_APP_DESC, str3);
            alarmManager.set(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(context, i, intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25));
        }
    }

    public static void setRepeatWithHourMinute(int i, int i2, int i3, int i4, String str, String str2, String str3) {
        long j;
        long j2;
        long timeInMillis;
        Log.i("0", "==============setRepeatWithHourMinute");
        switch (i4) {
            case 0:
                j = 946080000000L;
                j2 = j;
                break;
            case 1:
                j = 2592000000L;
                j2 = j;
                break;
            case 2:
                j2 = 86400000;
                break;
            case 3:
                j = 3600000;
                j2 = j;
                break;
            case 4:
                j = 60000;
                j2 = j;
                break;
            case 5:
                j = 1000;
                j2 = j;
                break;
            default:
                Log.i("0", "setRepeatingNotify repeattype 错误");
                j = 0;
                j2 = j;
                break;
        }
        Calendar calendar = Calendar.getInstance();
        int i5 = calendar.get(11);
        int i6 = calendar.get(12);
        if (i5 >= i2 && (i5 > i2 || i6 >= i3)) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(11, i2);
            calendar2.set(12, i3);
            calendar2.set(13, 0);
            calendar2.set(14, 0);
            timeInMillis = 86400000 - (calendar.getTimeInMillis() - calendar2.getTimeInMillis());
        } else {
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(11, i2);
            calendar3.set(12, i3);
            calendar3.set(13, 0);
            calendar3.set(14, 0);
            timeInMillis = calendar3.getTimeInMillis() - calendar.getTimeInMillis();
        }
        ap.a(LayaConch5.GetInstance().mCtx, i, timeInMillis, j2, str, str2, str3);
    }

    public static void setRepeatingNotify(int i, int i2, int i3, String str, String str2, String str3) {
        long j;
        Log.i("0", "==============setRepeatingNotify");
        switch (i3) {
            case 0:
                j = 1187194880;
                break;
            case 1:
                j = -1702967296;
                break;
            case 2:
                j = 86400000;
                break;
            case 3:
                j = 3600000;
                break;
            case 4:
                j = 60000;
                break;
            case 5:
                j = 1000;
                break;
            default:
                Log.i("0", "setRepeatingNotify repeattype 错误");
                j = 0;
                break;
        }
        ap.a(LayaConch5.GetInstance().mCtx, i, i2, j, str, str2, str3);
    }
}
