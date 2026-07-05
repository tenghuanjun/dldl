package cn.thinkingdata.android.utils;

import android.os.SystemClock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class p implements f {
    private final long a = SystemClock.elapsedRealtime();
    private final TimeZone b;
    private final e c;
    private Date d;

    public p(e eVar, TimeZone timeZone) {
        this.c = eVar;
        this.b = timeZone;
    }

    private synchronized Date c() {
        if (this.d == null) {
            this.d = this.c.a(this.a);
        }
        return this.d;
    }

    @Override // cn.thinkingdata.android.utils.f
    public Double a() {
        return Double.valueOf(r.a(c().getTime(), this.b));
    }

    @Override // cn.thinkingdata.android.utils.f
    public String b() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
            simpleDateFormat.setTimeZone(this.b);
            String str = simpleDateFormat.format(c());
            return !Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}").matcher(str).find() ? r.a(c(), this.b) : str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
