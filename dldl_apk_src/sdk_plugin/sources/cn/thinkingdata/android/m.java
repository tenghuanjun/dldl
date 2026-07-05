package cn.thinkingdata.android;

import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class m {
    private static final Map<Context, m> d = new HashMap();
    private String a;
    private int b;
    private int c;

    private m(Context context) {
        this.b = 10;
        this.c = 10000;
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        try {
            this.a = packageName;
            this.a = resources.getString(resources.getIdentifier("TADeFaultMainProcessName", "string", packageName));
        } catch (Exception unused) {
        }
        try {
            this.b = resources.getInteger(resources.getIdentifier("TARetentionDays", "integer", packageName));
        } catch (Exception unused2) {
        }
        try {
            this.c = resources.getInteger(resources.getIdentifier("TADatabaseLimit", "integer", packageName));
        } catch (Exception unused3) {
        }
        TDPresetProperties.initDisableList(context);
    }

    public static m a(Context context) {
        m mVar;
        synchronized (d) {
            mVar = d.get(context);
            if (mVar == null) {
                mVar = new m(context);
                d.put(context, mVar);
            }
        }
        return mVar;
    }

    long a() {
        int i = this.b;
        if (i > 10 || i < 0) {
            i = 10;
        }
        return 86400000 * ((long) i);
    }

    public String b() {
        return this.a;
    }

    int c() {
        return Math.max(this.c, 5000);
    }
}
