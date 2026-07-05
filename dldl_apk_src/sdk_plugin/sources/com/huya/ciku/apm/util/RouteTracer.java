package com.huya.ciku.apm.util;

import android.os.SystemClock;
import android.text.TextUtils;
import com.duowan.monitor.utility.MonitorLog;
import com.duowan.monitor.utility.MonitorThread;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RouteTracer {
    private static final int MAX_TTL = 30;
    private static final String TAG = RouteTracer.class.getSimpleName();
    private static RouteTracer mInstance;

    public interface RouteTraceCallback {
        void onComplete(List<RouteTrace> list);
    }

    public static RouteTracer getInstance() {
        if (mInstance == null) {
            mInstance = new RouteTracer();
        }
        return mInstance;
    }

    public void startTrace(String str, int i, final RouteTraceCallback routeTraceCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("ip address should not be null!");
        }
        if (routeTraceCallback != null) {
            if (i <= 0) {
                i = 30;
            }
            MonitorThread.execute(new RouteTracerRunnable(i, str, new RouteTraceCallback() { // from class: com.huya.ciku.apm.util.RouteTracer.1
                @Override // com.huya.ciku.apm.util.RouteTracer.RouteTraceCallback
                public void onComplete(final List<RouteTrace> list) {
                    MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.huya.ciku.apm.util.RouteTracer.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            routeTraceCallback.onComplete(list);
                        }
                    });
                }
            }));
        }
    }

    private static class RouteTracerRunnable implements Runnable {
        private static final String EXCEED_PING = "exceed";
        private static final String FROM_PING = "From";
        private static final String PING = "PING";
        private static final String SMALL_FROM_PING = "from";
        private static final String TIME_PING = "time=";
        private static final String UNREACHABLE_PING = "100%";
        private float elapsedTime;
        private String ipToPing;
        private RouteTraceCallback mCallback;
        private int maxTtl;
        private String url;
        private int ttl = 1;
        private List<RouteTrace> traces = new ArrayList();

        public RouteTracerRunnable(int i, String str, RouteTraceCallback routeTraceCallback) {
            this.maxTtl = i;
            this.url = str;
            this.mCallback = routeTraceCallback;
        }

        private String build() {
            String strLaunchPing;
            RouteTrace routeTrace;
            try {
                strLaunchPing = launchPing(this.url);
            } catch (IOException e) {
                MonitorLog.e(RouteTracer.TAG, e.getMessage());
                strLaunchPing = "";
            }
            if (strLaunchPing.contains(UNREACHABLE_PING) && !strLaunchPing.contains(EXCEED_PING)) {
                routeTrace = new RouteTrace("", parseIpFromPing(strLaunchPing), this.elapsedTime);
            } else {
                routeTrace = new RouteTrace("", parseIpFromPing(strLaunchPing), (this.ttl != this.maxTtl || TextUtils.isEmpty(strLaunchPing)) ? this.elapsedTime : parseTimeFromPing(strLaunchPing));
            }
            this.traces.add(routeTrace);
            return strLaunchPing;
        }

        private String launchPing(String str) throws IOException {
            BufferedReader bufferedReader;
            String str2 = "";
            try {
                String str3 = String.format("ping -c 1 -w 4 -t %d ", Integer.valueOf(this.ttl));
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str3 + str).getInputStream()));
                this.elapsedTime = (float) (SystemClock.elapsedRealtime() - jElapsedRealtime);
            } catch (Exception e) {
                MonitorLog.e(RouteTracer.TAG, e.getMessage());
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                str2 = str2 + line + ShellAdbUtils.COMMAND_LINE_END;
                return str2;
            }
            if (this.ttl == 1) {
                this.ipToPing = parseIpToPingFromPing(str2);
            }
            return str2;
        }

        private void handleResult(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            List<RouteTrace> list = this.traces;
            if (list.get(list.size() - 1).getIp().equals(this.ipToPing)) {
                int i = this.ttl;
                int i2 = this.maxTtl;
                if (i < i2) {
                    this.ttl = i2;
                    if (this.traces.size() > 1) {
                        List<RouteTrace> list2 = this.traces;
                        list2.remove(list2.size() - 1);
                        return;
                    }
                    return;
                }
                return;
            }
            int i3 = this.ttl;
            if (i3 < this.maxTtl) {
                this.ttl = i3 + 1;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            while (this.ttl < this.maxTtl) {
                handleResult(build());
            }
            onComplete(this.mCallback);
        }

        private void onComplete(RouteTraceCallback routeTraceCallback) {
            ArrayList arrayList = new ArrayList(this.traces.size());
            arrayList.addAll(this.traces);
            List<RouteTrace> list = this.traces;
            if (list != null) {
                list.clear();
                this.traces = null;
            }
            routeTraceCallback.onComplete(arrayList);
        }

        private String parseIpFromPing(String str) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            if (str.contains(FROM_PING)) {
                return parseIpFromString(str.substring(str.indexOf(FROM_PING)));
            }
            return parseIpFromString(str);
        }

        private String parseIpToPingFromPing(String str) {
            return str.contains(PING) ? parseIpFromString(str.substring(str.indexOf(PING))) : "";
        }

        private String parseIpFromString(String str) {
            Matcher matcher = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d))\\.){3}(?:25[0-5]|2[0-4]\\d|(?:1\\d{2}|[1-9]?\\d)))").matcher(str);
            return matcher.find() ? matcher.group() : "";
        }

        private float parseTimeFromPing(String str) {
            String strSubstring;
            try {
                strSubstring = "";
                if (str.contains(TIME_PING)) {
                    String strSubstring2 = str.substring(str.indexOf(TIME_PING) + 5);
                    strSubstring = strSubstring2.substring(0, strSubstring2.indexOf(" "));
                }
            } catch (Exception e) {
                MonitorLog.e(RouteTracer.TAG, e.getMessage());
            }
            float f = !TextUtils.isEmpty(strSubstring) ? Float.parseFloat(strSubstring) : 0.0f;
            return f == 0.0f ? this.elapsedTime : f;
        }
    }

    public static class RouteTrace {
        private float elapsedTime;
        private String hostname;
        private String ip;

        public RouteTrace(String str, String str2, float f) {
            this.hostname = str;
            this.ip = str2;
            this.elapsedTime = f;
        }

        public String getIp() {
            return this.ip;
        }

        public void setHostname(String str) {
            this.hostname = str;
        }

        public float getElapsedTime() {
            return this.elapsedTime;
        }

        public String toString() {
            return this.ip + ":  " + this.elapsedTime + "   ms";
        }
    }
}
