package com.huyaudbunify.dialog.msg;

import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AuthLoginWebviewBean {
    private String clientId;
    private String encryptedData;
    private String packageName;
    private String signature;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public String getEncryptedData() {
        return this.encryptedData;
    }

    public void setEncryptedData(String str) {
        this.encryptedData = str;
    }

    public Map<String, String> getMapData() {
        HashMap map = new HashMap();
        if (!getClientId().isEmpty()) {
            map.put("clientId", getClientId());
        }
        if (!getPackageName().isEmpty()) {
            map.put(BillingClientConstants.PACKAGE_NAME, getPackageName());
        }
        if (!getSignature().isEmpty()) {
            map.put("signature", getSignature());
        }
        if (!getEncryptedData().isEmpty()) {
            map.put("encryptedData", getEncryptedData());
        }
        return map;
    }

    public AuthLoginWebviewBean(String str, String str2, String str3, String str4) {
        this.clientId = "";
        this.packageName = "";
        this.signature = "";
        this.encryptedData = "";
        this.clientId = str;
        this.packageName = str2;
        this.signature = str3;
        this.encryptedData = str4;
    }
}
