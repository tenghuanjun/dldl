package com.sqwan.bugless.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ChannelUtil {
    private static final String CHANNEL_KEY = "info-";
    private static final String KEY_CHANNEL_VERSION = "cztchannel_version";
    private static String mChannel;

    public static String getChannel(Context context) {
        return getChannel(context, "");
    }

    public static String getChannel(Context context, String defaultChannel) {
        if (!TextUtils.isEmpty(mChannel)) {
            Toast.makeText(context, "内存中获取 ok = " + mChannel, 0).show();
            return mChannel;
        }
        String channelBySharedPreferences = getChannelBySharedPreferences(context);
        mChannel = channelBySharedPreferences;
        if (!TextUtils.isEmpty(channelBySharedPreferences)) {
            Toast.makeText(context, "磁盘缓存中获取 ok = " + mChannel, 0).show();
            return mChannel;
        }
        String channelFromAssets = getChannelFromAssets(context);
        mChannel = channelFromAssets;
        if (!TextUtils.isEmpty(channelFromAssets)) {
            Toast.makeText(context, "从apk中解析获取 ok = " + mChannel, 0).show();
            saveChannelBySharedPreferences(context, mChannel);
            return mChannel;
        }
        Toast.makeText(context, "获取失败", 0).show();
        return defaultChannel;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0104  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getChannelFromAssets(android.content.Context r11) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.bugless.util.ChannelUtil.getChannelFromAssets(android.content.Context):java.lang.String");
    }

    private static void saveChannelBySharedPreferences(Context context, String channel) {
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putString(CHANNEL_KEY, channel);
        editorEdit.putInt(KEY_CHANNEL_VERSION, getVersionCode(context));
        editorEdit.commit();
    }

    private static String getChannelBySharedPreferences(Context context) {
        int i;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int versionCode = getVersionCode(context);
        return (versionCode == -1 || (i = defaultSharedPreferences.getInt(KEY_CHANNEL_VERSION, -1)) == -1 || versionCode != i) ? "" : defaultSharedPreferences.getString(CHANNEL_KEY, "");
    }

    private static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
