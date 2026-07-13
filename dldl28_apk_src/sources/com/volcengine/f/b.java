package com.volcengine.f;

import com.lzy.okgo.model.HttpHeaders;
import com.volcengine.androidcloud.common.log.AcLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;

/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f1137a;
    private long b;

    private void a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        try {
            Date date = simpleDateFormat.parse(str);
            if (date == null) {
                return;
            }
            this.f1137a = date.getTime();
            this.b = System.currentTimeMillis();
        } catch (Throwable th) {
            AcLog.e("ServerTimeHolder", "failed to parse the server time : " + str, th);
        }
    }

    public long a(boolean z) {
        long j = this.f1137a;
        if (j == 0) {
            return 0L;
        }
        if (!z) {
            return j;
        }
        return this.f1137a + (System.currentTimeMillis() - this.b);
    }

    public void a(Map<String, String> map) {
        AcLog.v("ServerTimeHolder", "updateServerTime: responseHeader=" + map);
        if (map == null || !map.containsKey(HttpHeaders.HEAD_KEY_DATE)) {
            return;
        }
        a(map.get(HttpHeaders.HEAD_KEY_DATE));
    }
}
