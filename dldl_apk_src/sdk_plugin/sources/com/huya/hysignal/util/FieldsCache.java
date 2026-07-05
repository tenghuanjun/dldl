package com.huya.hysignal.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.huya.hysignal.bizreq.HySignalLaunch;
import com.huya.hysignal.wrapper.PushRegister;
import com.huya.hysignal.wrapper.PushUnRegister;
import com.huya.mtp.deviceid.utils.DeviceUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class FieldsCache {
    private static final String DEVICE_ID_KEY = "HYSIGNAL_DEVICE_ID_KEY";
    private static final String PROXY_CA_VERSION_CONFIG = "PROXY_CA_VERSION";
    private static final String TAG = "HySignalFieldsCache";
    private static FieldsCache sInstance;
    private boolean sInited;
    private SharedPreferences settings;
    private final String GUID_KEY = "GUID";
    private String sGuid = "";
    private String sDeviceId = "";
    private String sMid = "";
    private String sAppSrc = "";
    private String caVersion = "";
    private long uid = -1;
    private Map<String, PushRegister> mGroupPushRegisterMap = new ConcurrentHashMap();
    private Map<String, PushUnRegister> mGroupPushUnRegisterMap = new ConcurrentHashMap();

    private FieldsCache() {
    }

    public static FieldsCache getInstance() {
        if (sInstance == null) {
            sInstance = new FieldsCache();
        }
        return sInstance;
    }

    public synchronized void init(Context context, String str, String str2) {
        if (this.sInited) {
            HySignalLog.error(TAG, "has inited, return");
            return;
        }
        if (context == null) {
            HySignalLog.error(TAG, "init context is null");
            return;
        }
        this.sAppSrc = str2;
        this.settings = context.getSharedPreferences("HySignalGUIDCache", 0);
        loadCache();
        this.sInited = true;
        saveDeviceId(str);
        try {
            this.sMid = DeviceUtils.getAndroidId(context);
        } catch (Exception e) {
            HySignalLog.error(TAG, "get mid failed:" + e.getMessage());
        }
    }

    private void loadCache() {
        String string = this.settings.getString("GUID", "");
        if (HySignalLaunch.getInstance().isGuidApproved(string)) {
            this.sGuid = string;
        }
        this.sDeviceId = this.settings.getString(DEVICE_ID_KEY, "");
        this.caVersion = this.settings.getString(PROXY_CA_VERSION_CONFIG, "");
    }

    public void saveGuid(String str) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "save GUID need init, return");
            return;
        }
        if (!HySignalLaunch.getInstance().isGuidApproved(str)) {
            HySignalLog.error(TAG, "save GUID is err: %s, return", str);
            return;
        }
        String guid = getGuid();
        if (str.equals(getGuid())) {
            HySignalLog.debug(TAG, "set same GUID:%s, return", guid);
            return;
        }
        try {
            SharedPreferences.Editor editorEdit = this.settings.edit();
            editorEdit.putString("GUID", str);
            editorEdit.apply();
        } catch (Exception e) {
            HySignalLog.error(TAG, "save guid occur exception: %s", e.getMessage());
        }
        this.sGuid = str;
        HySignalLog.debug(TAG, "save GUID success, old:%s, new:%s", guid, str);
    }

    private boolean saveDeviceId(String str) {
        if (str == null || str.isEmpty()) {
            HySignalLog.error(TAG, "init deviceID is empty, return");
            return false;
        }
        String str2 = this.sDeviceId;
        if (str2 != null && !str2.isEmpty()) {
            return false;
        }
        SharedPreferences.Editor editorEdit = this.settings.edit();
        editorEdit.putString(DEVICE_ID_KEY, str);
        editorEdit.apply();
        this.sDeviceId = str;
        return true;
    }

    public void saveCaVersion(String str) {
        if (!this.sInited) {
            this.caVersion = str;
            HySignalLog.error(TAG, "save CA version need init, return");
        } else {
            if (this.caVersion.equals(str)) {
                return;
            }
            SharedPreferences.Editor editorEdit = this.settings.edit();
            editorEdit.putString(PROXY_CA_VERSION_CONFIG, str);
            editorEdit.apply();
            this.caVersion = str;
        }
    }

    public boolean setUid(long j) {
        if (j < 0 || this.uid == j) {
            return false;
        }
        this.uid = j;
        return true;
    }

    public synchronized void updateAppSrc(String str) {
        this.sAppSrc = str;
    }

    public String getGuid() {
        return this.sGuid;
    }

    public String getDeviceId() {
        return this.sDeviceId;
    }

    public String getCaVersion() {
        return this.caVersion;
    }

    public String getMid() {
        return this.sMid;
    }

    public String getAppSrc() {
        return this.sAppSrc;
    }

    public boolean isGroupPushMsgRegisterred() {
        return !this.mGroupPushRegisterMap.isEmpty();
    }

    public void addGroupPushRegister(String str, PushRegister pushRegister) {
        this.mGroupPushRegisterMap.put(str, pushRegister);
    }

    public void removeGroupPushRegister(String str) {
        this.mGroupPushRegisterMap.remove(str);
    }

    public Map<String, PushRegister> getGroupPushRegisterMap() {
        return this.mGroupPushRegisterMap;
    }

    public ArrayList<String> getRegisteredGroupIdList() {
        return new ArrayList<>(this.mGroupPushRegisterMap.keySet());
    }

    public void addGroupPushUnRegister(String str, PushUnRegister pushUnRegister) {
        this.mGroupPushUnRegisterMap.put(str, pushUnRegister);
    }

    public void removeGroupPushUnRegister(String str) {
        this.mGroupPushUnRegisterMap.remove(str);
    }

    public Map<String, PushUnRegister> getGroupPushUnRegisterMap() {
        return this.mGroupPushUnRegisterMap;
    }

    public ArrayList<String> getUnRegisteredGroupIdList() {
        return new ArrayList<>(this.mGroupPushUnRegisterMap.keySet());
    }
}
