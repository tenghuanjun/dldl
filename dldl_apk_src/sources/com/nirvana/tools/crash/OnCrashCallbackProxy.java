package com.nirvana.tools.crash;

import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class OnCrashCallbackProxy {
    private HashMap<String, OnCrashCallback> crashCallbackHashMap;

    public void onCrashOccurred(String str, String str2, String str3, String str4, boolean z, String str5) {
        OnCrashCallback onCrashCallback;
        HashMap<String, OnCrashCallback> map = this.crashCallbackHashMap;
        if (map == null || (onCrashCallback = map.get(str2)) == null) {
            return;
        }
        onCrashCallback.onCrashOccurred(str, str2, str3, str4, z, str5);
    }

    public void onCrashUploadFailed(String str, String str2, String str3) {
        OnCrashCallback onCrashCallback;
        HashMap<String, OnCrashCallback> map = this.crashCallbackHashMap;
        if (map == null || (onCrashCallback = map.get(str)) == null) {
            return;
        }
        onCrashCallback.onCrashUploadFailed(str, str2, str3);
    }

    public void registerCrashCallback(String str, OnCrashCallback onCrashCallback) {
        if (this.crashCallbackHashMap == null) {
            this.crashCallbackHashMap = new HashMap<>();
        }
        this.crashCallbackHashMap.put(str, onCrashCallback);
    }

    public void unRegisterCrashCallback(String str) {
        HashMap<String, OnCrashCallback> map = this.crashCallbackHashMap;
        if (map != null && map.containsKey(str)) {
            this.crashCallbackHashMap.remove(str);
        }
    }
}
