package com.host;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.plugin.standard.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class PluginExActivityHandler {
    private static final PluginExActivityHandler ourInstance = new PluginExActivityHandler();
    private List<String> pluginExActivtys = null;

    public static PluginExActivityHandler getInstance() {
        return ourInstance;
    }

    private PluginExActivityHandler() {
    }

    public void handlerActivityIntent(Context context, Intent intent, ClassLoader classLoader) {
        if (intent.getComponent() == null) {
            return;
        }
        String className = intent.getComponent().getClassName();
        try {
            if (classLoader.loadClass(className).getSuperclass() == BaseActivity.class) {
                intent.putExtra(TTDownloadField.TT_ACTIVITY, className);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startPluginActivity(context, intent);
    }

    public void startPluginActivity(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(TTDownloadField.TT_ACTIVITY);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        if (isPluginExActivty(stringExtra)) {
            intent.setClass(context, PluginExActivity.class);
            return;
        }
        String stringExtra2 = intent.getStringExtra("screenOrientation");
        Class<PluginActivity> cls = null;
        if (TextUtils.equals(stringExtra2, "portrait")) {
            cls = PluginActivity.class;
        } else if (TextUtils.equals(stringExtra2, "landscape")) {
            cls = LandscapePluginActivity.class;
        } else if (TextUtils.equals(stringExtra2, "behind")) {
            cls = BehindPluginActivity.class;
        }
        if (cls == null) {
            cls = PluginActivity.class;
        }
        intent.setClass(context, cls);
    }

    private boolean isPluginExActivty(String str) {
        if (this.pluginExActivtys == null) {
            this.pluginExActivtys = getPluginExActivtysAuto();
        }
        List<String> list = this.pluginExActivtys;
        if (list != null) {
            return list.contains(str);
        }
        return false;
    }

    private List<String> getPluginExActivtysAuto() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.sqwan.common.dialog.PlatformAnnouncementActivity");
        arrayList.add("com.sqwan.liveshow.huya.activity.ChatInputActivity");
        return arrayList;
    }
}
