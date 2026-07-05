package com.duowan.live.one.util;

import android.util.LruCache;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TimeLogUtil {
    private static LruCache<String, Value> mTimeKeyMap = new LruCache<>(10000);

    private static class Value {
        public long count;
        public long time;

        private Value() {
        }
    }

    public static void info(String str, long j, String str2, String str3, Object... objArr) {
        Value value = mTimeKeyMap.get(str);
        if (value != null && System.currentTimeMillis() < value.time + j) {
            value.count++;
            return;
        }
        if (value == null) {
            value = new Value();
            mTimeKeyMap.put(str, value);
        }
        value.count++;
        L.info(str2, String.format(str3, objArr) + ", timeKey=" + str + ", count=" + value.count);
        value.time = System.currentTimeMillis();
        value.count = 0L;
    }
}
