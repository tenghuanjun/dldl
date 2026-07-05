package cn.thinkingdata.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class o implements f {
    private final TimeZone a;
    private final Date b;
    private boolean c = true;

    public o(Date date, TimeZone timeZone) {
        this.b = date == null ? new Date() : date;
        this.a = timeZone;
    }

    @Override // cn.thinkingdata.android.utils.f
    public Double a() {
        if (!this.c || this.a == null) {
            return null;
        }
        return Double.valueOf(r.a(this.b.getTime(), this.a));
    }

    @Override // cn.thinkingdata.android.utils.f
    public String b() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
            if (this.a != null) {
                simpleDateFormat.setTimeZone(this.a);
            }
            String str = simpleDateFormat.format(this.b);
            return !Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}").matcher(str).find() ? r.a(this.b, this.a) : str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void c() {
        this.c = false;
    }
}
