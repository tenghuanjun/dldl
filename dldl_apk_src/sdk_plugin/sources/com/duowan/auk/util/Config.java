package com.duowan.auk.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.huya.mtp.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Config {
    private static volatile IConfig sConfigImpl;
    private SharedPreferences mPreferences;
    private static Map<String, Config> mConfigMaps = new HashMap();
    private static String NAME = null;

    public interface IConfig {
        SharedPreferences getSpImpl(Context context, String str);
    }

    public static void init(Context context, IConfig iConfig) {
        if (sConfigImpl == null) {
            sConfigImpl = iConfig;
        }
    }

    public static synchronized Config getInstance(Context context) {
        if (NAME == null) {
            NAME = Utils.getProcessName(context) + ".configuration";
        }
        return getConfig(context, NAME);
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

    public static synchronized Config getInstance(Context context, String str) {
        if (str == null) {
            str = "";
        }
        return getConfig(context, str);
    }

    private Config(Context context, String str) {
        this.mPreferences = sConfigImpl.getSpImpl(context, str + ".configuration");
    }

    public void setOnChangeListenner(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (onSharedPreferenceChangeListener != null) {
            this.mPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    public synchronized boolean setString(String str, String str2) {
        return doCommit(this.mPreferences.edit().putString(str, str2));
    }

    public synchronized boolean setStringAsync(String str, String str2) {
        return doApply(this.mPreferences.edit().putString(str, str2));
    }

    public synchronized boolean setInt(String str, int i) {
        return doCommit(this.mPreferences.edit().putInt(str, i));
    }

    public synchronized boolean setIntAsync(String str, int i) {
        return doApply(this.mPreferences.edit().putInt(str, i));
    }

    public synchronized boolean setLong(String str, long j) {
        return doCommit(this.mPreferences.edit().putLong(str, j));
    }

    public synchronized boolean setLongAsync(String str, long j) {
        return doApply(this.mPreferences.edit().putLong(str, j));
    }

    public synchronized boolean setBoolean(String str, boolean z) {
        return doCommit(this.mPreferences.edit().putBoolean(str, z));
    }

    public synchronized boolean setBooleanAsync(String str, boolean z) {
        return doApply(this.mPreferences.edit().putBoolean(str, z));
    }

    public synchronized boolean setFloat(String str, float f) {
        return doCommit(this.mPreferences.edit().putFloat(str, f));
    }

    public synchronized boolean setFloatAsync(String str, float f) {
        return doApply(this.mPreferences.edit().putFloat(str, f));
    }

    public synchronized boolean setStringSet(String str, Set<String> set) {
        return doCommit(this.mPreferences.edit().putStringSet(str, set));
    }

    public synchronized boolean setStringSetAsync(String str, Set<String> set) {
        return doApply(this.mPreferences.edit().putStringSet(str, set));
    }

    public synchronized String getString(String str, String str2) {
        return this.mPreferences.getString(str, str2);
    }

    public synchronized int getInt(String str, int i) {
        return this.mPreferences.getInt(str, i);
    }

    public synchronized long getLong(String str, long j) {
        return this.mPreferences.getLong(str, j);
    }

    public synchronized boolean getBoolean(String str, boolean z) {
        return this.mPreferences.getBoolean(str, z);
    }

    public synchronized float getFloat(String str, float f) {
        return this.mPreferences.getFloat(str, f);
    }

    public synchronized Set<String> getStringSet(String str, Set<String> set) {
        return this.mPreferences.getStringSet(str, set);
    }

    public synchronized void clearAllSync() {
        doCommit(this.mPreferences.edit().clear());
    }

    public synchronized void clearAllAsync() {
        this.mPreferences.edit().clear().apply();
    }

    public synchronized boolean contains(String str) {
        return this.mPreferences.contains(str);
    }

    public synchronized boolean remove(String str) {
        return doCommit(this.mPreferences.edit().remove(str));
    }

    public synchronized boolean removeAsync(String str) {
        return doApply(this.mPreferences.edit().remove(str));
    }

    private boolean doCommit(SharedPreferences.Editor editor) {
        try {
            return editor.commit();
        } catch (Throwable th) {
            L.error("Config", th);
            return false;
        }
    }

    private boolean doApply(SharedPreferences.Editor editor) {
        try {
            editor.apply();
            return true;
        } catch (Throwable th) {
            L.error("Config", th);
            return false;
        }
    }
}
