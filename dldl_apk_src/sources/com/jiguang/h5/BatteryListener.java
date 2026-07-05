package com.jiguang.h5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.nirvana.tools.logger.cache.db.DBHelpTool;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BatteryListener {
    public static int level;
    public static int scale;
    private Context mContext;
    private BatteryBroadcastReceiver receiver = new BatteryBroadcastReceiver();

    public BatteryListener(Context context) {
        this.mContext = context;
    }

    public void register() {
        if (this.receiver != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            this.mContext.registerReceiver(this.receiver, intentFilter);
        }
    }

    public void unregister() {
        BatteryBroadcastReceiver batteryBroadcastReceiver = this.receiver;
        if (batteryBroadcastReceiver != null) {
            this.mContext.unregisterReceiver(batteryBroadcastReceiver);
        }
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        private BatteryBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BatteryListener.level = intent.getIntExtra(DBHelpTool.RecordEntry.COLUMN_NAME_LEVEL, 0);
            BatteryListener.scale = intent.getIntExtra("scale", 0);
            SdkMgr.Logd("电量变化 level:" + BatteryListener.level + " scale:" + BatteryListener.scale);
        }
    }

    public static int getBattery() {
        int i = scale;
        if (i == 0) {
            return 100;
        }
        return (level * 100) / i;
    }
}
