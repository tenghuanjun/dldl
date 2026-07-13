package com.volcengine.common.config;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.config.b;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.j.c;
import com.volcengine.j.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a implements ConfigService, b.a {
    private final Map<String, List<ConfigService.ConfigObserver>> a;
    private com.volcengine.common.config.b b;
    private final Map<String, String> c;
    private final Map<String, String> d;
    private int e;

    private static final class b {
        private static final a a = new a();
    }

    private a() {
        this.a = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
    }

    public static a b() {
        return b.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, String str) {
        this.b.a(this, i, str);
    }

    private com.volcengine.common.config.b d() {
        if (this.b == null) {
            this.b = new AppSettingsPlatform(this.e);
        }
        return this.b;
    }

    public void a() {
        AcLog.d("ConfigService", "clearAllConfigs");
        Iterator<String> it = d().a().iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    public void a(int i) {
        this.e = i;
        if (c.b()) {
            a();
        } else if (c.a()) {
            a("plugin_config");
        }
    }

    @Override // com.volcengine.common.config.b.a
    public void a(final int i, final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b.a(this, i, str);
        } else {
            SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.common.config.a$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.b(i, str);
                }
            });
        }
    }

    public void a(String str) {
        AcLog.d("ConfigService", "clearConfig: configName = [" + str + "]");
        this.c.remove(str);
        n.a().edit().remove(str).apply();
    }

    public void c() {
        d().a(this);
    }

    @Override // com.volcengine.common.innerapi.ConfigService
    public void dispatchConfig(String str, String str2) {
        AcLog.v("ConfigService", "dispatchConfig: configName = " + str + ", data = " + str2);
        List<ConfigService.ConfigObserver> list = this.a.get(str);
        if (list == null || list.isEmpty()) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.d.put(str, str2);
        } else {
            Iterator it = new ArrayList(list).iterator();
            while (it.hasNext()) {
                ((ConfigService.ConfigObserver) it.next()).onReceiveConfig(str, str2);
            }
        }
    }

    @Override // com.volcengine.common.innerapi.ConfigService
    public String getConfig(String str) {
        String str2 = this.c.get(str);
        return !TextUtils.isEmpty(str2) ? str2 : n.a().getString(str, null);
    }

    @Override // com.volcengine.common.innerapi.ConfigService
    public JSONObject getConfigJson(String str) {
        String config = getConfig(str);
        if (SDKContext.isEmptyConfig(config)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(config);
        } catch (JSONException e) {
            AcLog.w("ConfigService", "getConfigJson: " + str + ", " + Log.getStackTraceString(e));
            return new JSONObject();
        }
    }

    @Override // com.volcengine.common.innerapi.ConfigService
    public void register(String str, ConfigService.ConfigObserver configObserver) {
        List<ConfigService.ConfigObserver> arrayList = this.a.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.a.put(str, arrayList);
        }
        if (!arrayList.contains(configObserver)) {
            arrayList.add(configObserver);
        }
        String str2 = this.d.get(str);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        configObserver.onReceiveConfig(str, str2);
        this.d.remove(str);
    }

    @Override // com.volcengine.common.innerapi.ConfigService
    public void storeConfig(String str, String str2) {
        if (SDKContext.isEmptyConfig(str2)) {
            return;
        }
        this.c.put(str, str2);
        n.a().edit().putString(str, str2).apply();
    }

    @Override // com.volcengine.common.innerapi.ConfigService
    public void unregister(String str, ConfigService.ConfigObserver configObserver) {
        List<ConfigService.ConfigObserver> list = this.a.get(str);
        if (list != null) {
            list.remove(configObserver);
            if (list.isEmpty()) {
                this.a.remove(str);
            }
        }
    }
}
