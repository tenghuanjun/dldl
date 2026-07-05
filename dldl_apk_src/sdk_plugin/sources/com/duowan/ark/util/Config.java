package com.duowan.ark.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Config {
    private static final int HANDLER_MESSAGE_KEY = 22118;
    private static final String HANDLER_REPORT_ANR_NAME_KEY = "key";
    public static final String TAG = "com.duowan.ark.util.Config";
    private static final int TIME_WAIT_FOR_REPORT_ANR = 2000;
    private static IConfig sConfigImpl;
    private String mName;
    private SharedPreferences mPreferences;
    private static Map<String, Config> mConfigMaps = new HashMap();
    private static String DEFAULT_NAME = null;
    private static HashMap<String, Object> mSharePreferenceCache = new HashMap<>();

    public interface IConfig {
        SharedPreferences getSpImpl(Context context, String str);
    }

    public static void init(Context context, IConfig iConfig) {
        sConfigImpl = iConfig;
    }

    public static synchronized Config getInstance(Context context) {
        if (DEFAULT_NAME == null) {
            DEFAULT_NAME = Utils.getProcessName(context) + ".configuration.configuration";
        }
        return getConfig(context, DEFAULT_NAME);
    }

    public static synchronized Config getInstance(Context context, String str) {
        if (str == null) {
            str = "";
        }
        return getConfig(context, str);
    }

    private static Config getConfig(Context context, String str) {
        Config config = mConfigMaps.containsKey(str) ? mConfigMaps.get(str) : null;
        if (config != null) {
            return config;
        }
        Config config2 = new Config(context, str);
        mConfigMaps.put(str, config2);
        return config2;
    }

    private Config(Context context, String str) {
        this.mName = "";
        this.mName = str;
        this.mPreferences = sConfigImpl.getSpImpl(context, str);
    }

    public void setOnChangeListenner(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (onSharedPreferenceChangeListener != null) {
            this.mPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    public synchronized void remove(String str) {
        this.mPreferences.edit().remove(str).apply();
    }

    public synchronized boolean setString(String str, String str2) {
        SharedPreferences.Editor editorPutString;
        if (str2 == null) {
            editorPutString = this.mPreferences.edit().remove(str);
        } else {
            editorPutString = this.mPreferences.edit().putString(str, str2);
        }
        return doApply(str, str2, editorPutString);
    }

    public synchronized boolean setStringSync(String str, String str2) {
        return doCommit(this.mPreferences.edit().putString(str, str2));
    }

    public synchronized boolean setInt(String str, int i) {
        return doApply(str, Integer.valueOf(i), this.mPreferences.edit().putInt(str, i));
    }

    public synchronized boolean setIntSync(String str, int i) {
        return doCommit(this.mPreferences.edit().putInt(str, i));
    }

    public synchronized boolean setLong(String str, long j) {
        return doApply(str, Long.valueOf(j), this.mPreferences.edit().putLong(str, j));
    }

    public synchronized boolean setLongSync(String str, long j) {
        return doCommit(this.mPreferences.edit().putLong(str, j));
    }

    public synchronized boolean setBoolean(String str, boolean z) {
        return doApply(str, Boolean.valueOf(z), this.mPreferences.edit().putBoolean(str, z));
    }

    public synchronized boolean setBooleanSync(String str, boolean z) {
        return doCommit(this.mPreferences.edit().putBoolean(str, z));
    }

    public synchronized boolean setFloat(String str, float f) {
        return doApply(str, Float.valueOf(f), this.mPreferences.edit().putFloat(str, f));
    }

    public synchronized boolean setFloatSync(String str, float f) {
        return doCommit(this.mPreferences.edit().putFloat(str, f));
    }

    public synchronized boolean setStringSet(String str, Set<String> set) {
        return doApply(str, set, this.mPreferences.edit().putStringSet(str, set));
    }

    public synchronized boolean setStringSetSync(String str, Set<String> set) {
        return doCommit(this.mPreferences.edit().putStringSet(str, set));
    }

    public synchronized String getString(String str, String str2) {
        String string;
        string = (String) getFromCache(this.mName, str, String.class);
        if (string == null) {
            string = this.mPreferences.getString(str, str2);
        }
        return string;
    }

    public synchronized int getInt(String str, int i) {
        Integer num;
        num = (Integer) getFromCache(this.mName, str, Integer.class);
        return num != null ? num.intValue() : this.mPreferences.getInt(str, i);
    }

    public synchronized long getLong(String str, long j) {
        Long l;
        l = (Long) getFromCache(this.mName, str, Long.class);
        return l != null ? l.longValue() : this.mPreferences.getLong(str, j);
    }

    public synchronized boolean getBoolean(String str, boolean z) {
        Boolean bool;
        bool = (Boolean) getFromCache(this.mName, str, Boolean.class);
        return bool != null ? bool.booleanValue() : this.mPreferences.getBoolean(str, z);
    }

    public synchronized float getFloat(String str, float f) {
        Float f2;
        f2 = (Float) getFromCache(this.mName, str, Float.class);
        return f2 != null ? f2.floatValue() : this.mPreferences.getFloat(str, f);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006d A[Catch: all -> 0x0075, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:19:0x006d, B:18:0x0066, B:5:0x000d, B:7:0x0013, B:9:0x001d, B:11:0x002d, B:13:0x003e), top: B:27:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.util.Set<java.lang.String> getStringSet(java.lang.String r4, java.util.Set<java.lang.String> r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = r3.mName     // Catch: java.lang.Throwable -> L75
            java.lang.Class<java.util.Set> r1 = java.util.Set.class
            java.lang.Object r0 = getFromCache(r0, r4, r1)     // Catch: java.lang.Throwable -> L75
            java.util.Set r0 = (java.util.Set) r0     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L6d
            int r1 = r0.size()     // Catch: java.lang.Throwable -> L65
            if (r1 <= 0) goto L6d
            java.lang.Class r1 = r0.getClass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type r1 = r1.getGenericSuperclass()     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L6d
            java.lang.Class r1 = r0.getClass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type r1 = r1.getGenericSuperclass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L6d
            java.lang.Class r1 = r0.getClass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type r1 = r1.getGenericSuperclass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()     // Catch: java.lang.Throwable -> L65
            int r1 = r1.length     // Catch: java.lang.Throwable -> L65
            if (r1 <= 0) goto L6d
            java.lang.Class r1 = r0.getClass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type r1 = r1.getGenericSuperclass()     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1     // Catch: java.lang.Throwable -> L65
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()     // Catch: java.lang.Throwable -> L65
            r2 = 0
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L65
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L65
            java.lang.Class r1 = r1.getClass()     // Catch: java.lang.Throwable -> L65
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L65
            java.lang.String r2 = "class java.lang.String"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L6d
            monitor-exit(r3)
            return r0
        L65:
            r0 = move-exception
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L75
            com.duowan.ark.util.KLog.error(r0)     // Catch: java.lang.Throwable -> L75
        L6d:
            android.content.SharedPreferences r0 = r3.mPreferences     // Catch: java.lang.Throwable -> L75
            java.util.Set r4 = r0.getStringSet(r4, r5)     // Catch: java.lang.Throwable -> L75
            monitor-exit(r3)
            return r4
        L75:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.ark.util.Config.getStringSet(java.lang.String, java.util.Set):java.util.Set");
    }

    public synchronized void clearAllSync() {
        doCommit(this.mPreferences.edit().clear());
    }

    public synchronized void clearAllAsync() {
        this.mPreferences.edit().clear().apply();
    }

    private boolean doCommit(SharedPreferences.Editor editor) {
        try {
            return editor.commit();
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean doApply(String str, Object obj, SharedPreferences.Editor editor) {
        try {
            editor.apply();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static <T> T getFromCache(String str, String str2, Class<T> cls) {
        T t = (T) mSharePreferenceCache.get(mergeNameAndKey(str, str2));
        if (t == null || !cls.isInstance(t)) {
            return null;
        }
        return t;
    }

    private static String mergeNameAndKey(String str, String str2) {
        return str + "_" + str2;
    }
}
