package com.huya.ciku.apm.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import com.duowan.monitor.utility.MonitorLog;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ArpTracer {
    public static final String TAG = ArpTracer.class.getSimpleName();
    private static ArpTracer mInstance;

    public interface ArpTracerCallback {
        void onComplete(int i);
    }

    public static ArpTracer getInstance() {
        if (mInstance == null) {
            mInstance = new ArpTracer();
        }
        return mInstance;
    }

    public void startTrace(Context context) {
        new ArpTracerTask(context).execute(new Void[0]);
    }

    private static class ArpTracerTask extends AsyncTask<Void, Void, Void> {
        private static final String FROM_PING = "From";
        private static final String SMALL_FROM_PING = "from";
        private Context mContext;
        private int mCount = 0;

        public ArpTracerTask(Context context) {
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (!HuyaNetworkUtils.isNetworkAvailable(this.mContext)) {
                return null;
            }
            if ("wifi".equals(HuyaNetworkUtils.getNetWorkType(this.mContext))) {
                String wifiIP = HuyaNetworkUtils.getWifiIP(this.mContext);
                if (TextUtils.isEmpty(wifiIP)) {
                    return null;
                }
                String strSubstring = wifiIP.substring(0, wifiIP.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR) + 1);
                for (int i = 1; i <= 255; i++) {
                    launchPing(strSubstring + i, 1);
                }
                return null;
            }
            this.mCount = 1;
            return null;
        }

        private String launchPing(String str, int i) {
            String line;
            String str2 = "";
            try {
                String str3 = String.format("ping -c 1 -w 4 -t %d ", Integer.valueOf(i));
                SystemClock.elapsedRealtime();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str3 + str).getInputStream()));
                do {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    str2 = str2 + line + ShellAdbUtils.COMMAND_LINE_END;
                    if (line.contains(FROM_PING)) {
                        break;
                    }
                } while (!line.contains("from"));
                this.mCount++;
            } catch (Exception e) {
                MonitorLog.e(ArpTracer.TAG, e.getMessage());
            }
            return str2;
        }
    }
}
