package cn.thinkingdata.android;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.utils.r;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class f extends ThinkingAnalyticsSDK {
    private String a;
    private String b;
    private final JSONObject c;
    private boolean d;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ThinkingAnalyticsSDK.TATrackStatus.values().length];
            a = iArr;
            try {
                iArr[ThinkingAnalyticsSDK.TATrackStatus.PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ThinkingAnalyticsSDK.TATrackStatus.STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ThinkingAnalyticsSDK.TATrackStatus.SAVE_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ThinkingAnalyticsSDK.TATrackStatus.NORMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    f(TDConfig tDConfig) {
        super(tDConfig, true);
        this.d = true;
        this.c = new JSONObject();
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void clearSuperProperties() {
        if (hasDisabled()) {
            return;
        }
        synchronized (this.c) {
            Iterator<String> itKeys = this.c.keys();
            while (itKeys.hasNext()) {
                itKeys.next();
                itKeys.remove();
            }
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void enableAutoTrack(List<ThinkingAnalyticsSDK.AutoTrackEventType> list) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void enableTracking(boolean z) {
        this.d = z;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public String getDistinctId() {
        String str = this.a;
        return str != null ? str : getRandomID();
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    String getLoginId() {
        return this.b;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public JSONObject getSuperProperties() {
        return this.c;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public boolean hasOptOut() {
        return false;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void identify(String str) {
        this.a = str;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void ignoreAutoTrackActivities(List<Class<?>> list) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void ignoreAutoTrackActivity(Class<?> cls) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void ignoreView(View view) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void ignoreViewType(Class cls) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public boolean isEnabled() {
        return this.d;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void login(String str) {
        if (hasDisabled()) {
            return;
        }
        this.b = str;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void logout() {
        if (hasDisabled()) {
            return;
        }
        this.b = null;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void optInTracking() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void optOutTracking() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void optOutTrackingAndDeleteUser() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setJsBridge(WebView webView) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setJsBridgeForX5WebView(Object obj) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setNetworkType(ThinkingAnalyticsSDK.ThinkingdataNetworkType thinkingdataNetworkType) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setSuperProperties(JSONObject jSONObject) {
        if (hasDisabled() || jSONObject == null) {
            return;
        }
        try {
            if (cn.thinkingdata.android.utils.h.a(jSONObject)) {
                synchronized (this.c) {
                    r.a(jSONObject, this.c, this.mConfig.getDefaultTimeZone());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setTrackStatus(ThinkingAnalyticsSDK.TATrackStatus tATrackStatus) {
        int i = a.a[tATrackStatus.ordinal()];
        if (i == 1) {
            this.mMessages.a(getToken(), false);
            enableTracking(false);
            return;
        }
        if (i == 2) {
            this.d = true;
            this.mMessages.a(getToken(), false);
            optOutTracking();
        } else if (i == 3) {
            this.d = true;
            this.mMessages.a(getToken(), true);
        } else {
            if (i != 4) {
                return;
            }
            this.d = true;
            this.mMessages.a(getToken(), false);
            flush();
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setViewID(Dialog dialog, String str) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setViewID(View view, String str) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setViewProperties(View view, JSONObject jSONObject) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void trackFragmentAppViewScreen() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void trackViewScreen(Activity activity) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void trackViewScreen(Fragment fragment) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void trackViewScreen(Object obj) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void unsetSuperProperty(String str) {
        if (hasDisabled() || str == null) {
            return;
        }
        try {
            synchronized (this.c) {
                this.c.remove(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
