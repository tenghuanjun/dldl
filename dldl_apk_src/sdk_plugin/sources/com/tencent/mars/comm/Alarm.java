package com.tencent.mars.comm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.tencent.mars.xlog.Log2;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Alarm extends BroadcastReceiver {
    private static final String KEXTRA_ID = "ID";
    private static final String KEXTRA_PID = "PID";
    private static final String TAG = "MicroMsg.Alarm";
    private static TreeSet<Object[]> alarm_waiting_set = new TreeSet<>(new ComparatorAlarm());
    private static Alarm bc_alarm;
    private static WakerLock wakerlock;

    private enum TSetData {
        ID,
        WAITTIME,
        PENDINGINTENT
    }

    private native void onAlarm(long j);

    private void onAlarmWithTryCatch(long j) {
        try {
            onAlarm(j);
        } catch (UnsatisfiedLinkError unused) {
            onAlarm(j);
        }
    }

    private static class ComparatorAlarm implements Comparator<Object[]> {
        private ComparatorAlarm() {
        }

        @Override // java.util.Comparator
        public int compare(Object[] objArr, Object[] objArr2) {
            return (int) (((Long) objArr[TSetData.ID.ordinal()]).longValue() - ((Long) objArr2[TSetData.ID.ordinal()]).longValue());
        }
    }

    public static void resetAlarm(Context context) {
        synchronized (alarm_waiting_set) {
            Iterator<Object[]> it = alarm_waiting_set.iterator();
            while (it.hasNext()) {
                cancelAlarmMgr(context, (PendingIntent) it.next()[TSetData.PENDINGINTENT.ordinal()]);
            }
            alarm_waiting_set.clear();
            if (bc_alarm != null) {
                context.unregisterReceiver(bc_alarm);
                bc_alarm = null;
            }
        }
    }

    public static boolean start(long j, int i, Context context) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (i < 0) {
            Log2.e(TAG, "id:%d, after:%d", Long.valueOf(j), Integer.valueOf(i));
            return false;
        }
        if (context == null) {
            Log2.e(TAG, "null==context, id:%d, after:%d", Long.valueOf(j), Integer.valueOf(i));
            return false;
        }
        synchronized (alarm_waiting_set) {
            if (wakerlock == null) {
                wakerlock = new WakerLock(context);
                Log2.i(TAG, "start new wakerlock");
            }
            if (bc_alarm == null) {
                Alarm alarm = new Alarm();
                bc_alarm = alarm;
                context.registerReceiver(alarm, new IntentFilter("ALARM_ACTION(" + String.valueOf(Process.myPid()) + ")"));
            }
            Iterator<Object[]> it = alarm_waiting_set.iterator();
            while (it.hasNext()) {
                if (((Long) it.next()[TSetData.ID.ordinal()]).longValue() == j) {
                    Log2.e(TAG, "id exist=%d", Long.valueOf(j));
                    return false;
                }
            }
            if (i >= 0) {
                jElapsedRealtime += (long) i;
            }
            PendingIntent alarmMgr = setAlarmMgr(j, jElapsedRealtime, context);
            if (alarmMgr == null) {
                return false;
            }
            alarm_waiting_set.add(new Object[]{Long.valueOf(j), Long.valueOf(jElapsedRealtime), alarmMgr});
            return true;
        }
    }

    public static boolean stop(long j, Context context) {
        if (context == null) {
            Log2.e(TAG, "context==null");
            return false;
        }
        synchronized (alarm_waiting_set) {
            if (wakerlock == null) {
                wakerlock = new WakerLock(context);
                Log2.i(TAG, "stop new wakerlock");
            }
            if (bc_alarm == null) {
                bc_alarm = new Alarm();
                context.registerReceiver(bc_alarm, new IntentFilter());
                Log2.i(TAG, "stop new Alarm");
            }
            Iterator<Object[]> it = alarm_waiting_set.iterator();
            while (it.hasNext()) {
                Object[] next = it.next();
                if (((Long) next[TSetData.ID.ordinal()]).longValue() == j) {
                    cancelAlarmMgr(context, (PendingIntent) next[TSetData.PENDINGINTENT.ordinal()]);
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    private static PendingIntent setAlarmMgr(long j, long j2, Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager == null) {
            Log2.e(TAG, "am == null");
            return null;
        }
        Intent intent = new Intent();
        intent.setAction("ALARM_ACTION(" + String.valueOf(Process.myPid()) + ")");
        intent.putExtra("ID", j);
        intent.putExtra(KEXTRA_PID, Process.myPid());
        PendingIntent broadcast = PendingIntent.getBroadcast(context, (int) j, intent, 268435456);
        if (Build.VERSION.SDK_INT < 19) {
            alarmManager.set(2, j2, broadcast);
        } else {
            alarmManager.set(2, j2, broadcast);
        }
        return broadcast;
    }

    private static boolean cancelAlarmMgr(Context context, PendingIntent pendingIntent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager == null) {
            Log2.e(TAG, "am == null");
            return false;
        }
        if (pendingIntent == null) {
            Log2.e(TAG, "pendingIntent == null");
            return false;
        }
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
        return true;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        boolean z;
        if (context == null || intent == null) {
            return;
        }
        Long lValueOf = Long.valueOf(intent.getLongExtra("ID", 0L));
        Integer numValueOf = Integer.valueOf(intent.getIntExtra(KEXTRA_PID, 0));
        if (0 == lValueOf.longValue() || numValueOf.intValue() == 0) {
            return;
        }
        if (numValueOf.intValue() != Process.myPid()) {
            Log2.w(TAG, "onReceive id:%d, pid:%d, mypid:%d", lValueOf, numValueOf, Integer.valueOf(Process.myPid()));
            return;
        }
        synchronized (alarm_waiting_set) {
            try {
                Iterator<Object[]> it = alarm_waiting_set.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    Object[] next = it.next();
                    Long l = (Long) next[TSetData.ID.ordinal()];
                    Log2.i(TAG, "onReceive id=%d, curId=%d", lValueOf, l);
                    if (l.equals(lValueOf)) {
                        Log2.i(TAG, "onReceive find alarm id:%d, pid:%d, delta miss time:%d", lValueOf, numValueOf, Long.valueOf(SystemClock.elapsedRealtime() - ((Long) next[TSetData.WAITTIME.ordinal()]).longValue()));
                        it.remove();
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    Log2.e(TAG, "onReceive not found id:%d, pid:%d, alarm_waiting_set.size:%d", lValueOf, numValueOf, Integer.valueOf(alarm_waiting_set.size()));
                }
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
        WakerLock wakerLock = wakerlock;
        if (wakerLock != null) {
            wakerLock.lock(200L);
        }
        if (z) {
            onAlarmWithTryCatch(lValueOf.longValue());
        }
    }
}
