package com.ishumei.smantifraud;

import android.content.Context;
import com.ishumei.O0000O000000oO.O0000O000000oO;
import com.ishumei.O0000O000000oO.O000O0000OoO;
import com.ishumei.O000O00000OoO.O000O00000oO;
import com.ishumei.O000O0000OOoO.O000O00000o0O;
import com.ishumei.O000O0000OOoO.O000O0000Oo0O;
import com.ishumei.dfp.SMSDK;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SmAntiFraud {
    public static final int SM_AF_ASYN_MODE = 1;
    public static final int SM_AF_SUCCESS = 0;
    public static final int SM_AF_SYN_MODE = 0;
    public static final int SM_AF_UNINIT = 1;
    private static final String TAG = "SmAntiFraud";
    private static int initStatus = 1;
    private static boolean isInited;
    private static IServerSmidCallback mServerIdCallback;
    public static SmOption option;

    public interface IServerSmidCallback {
        void onError(int i);

        void onSuccess(String str);
    }

    public static class SmOption {
        private String confUrl;
        private String contactUrl;
        private boolean first;
        private Set<String> notCollect;
        private String traceUrl;
        private String url;
        private boolean synMode = false;
        private String organization = "";
        private String channel = "";
        private String privk = "";
        private boolean transport = true;
        private boolean cloudConf = true;
        private boolean encrypt = true;
        private boolean usingMD5 = false;
        private int httpType = 1;
        private IServerSmidCallback callback = null;
        private String appId = "";
        private String publicKey = "MIIDOzCCAiOgAwIBAgIBMDANBgkqhkiG9w0BAQUFADA4MQswCQYDVQQGEwJDTjENMAsGA1UECwwEQ05DQjEaMBgGA1UEAwwRZS5iYW5rLmVjaXRpYy5jb20wHhcNMTgwMjExMDg0NTIyWhcNMzgwMjA2MDg0NTIyWjA4MQswCQYDVQQGEwJDTjENMAsGA1UECwwEQ05DQjEaMBgGA1UEAwwRZS5iYW5rLmVjaXRpYy5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCkF+2AicVKj7SaHw3dbJt3i6fkL1WfLw1WRqe8r8Cc7qJOshaqNvCzW1qRX6E5H/umtl1Uj99V07uewUFk96xY/+s/GuBnbGoSrcu3OAHDgEGuY5atZo+umIk7LufAif2VUcNGY3nWxGcig20ExO/6nAf/G3Xxo4QL8fBdPG/prOXxSvtJiPls1Qg9zzSgAH+HMCAINMsuJmzDQiTt6Me8k7YHts+jWQF7KF25plITcW1Qmy3Aw8qYjVhbHn8KTAEeuQhmM5RS6KP1Hu71q4DYOWcx44QThSbiAYwG1JQBBwM8XnBfVYMpr6Qi0owibNYoZ/S6xwfRFGB0W1HeG9WfAgMBAAGjUDBOMB0GA1UdDgQWBBT0iLEXY9HIKNy5DG4d72l+R7Nf1zAfBgNVHSMEGDAWgBT0iLEXY9HIKNy5DG4d72l+R7Nf1zAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA4IBAQB5MWz1RGFG537rJCtHp+LqxR9iJSFsHiW3ZoLIAeyD0oJ69RcL2gE/TNWmE9zYUkd9TdNtXqxlNPpj1P1/+x781neWnGou/n/XFS82T5S339X3DIjHc/IqOzwnxEOKH2V0NmK9iKgx6H05Q9MMvUXFsL3QK2hDMAVY28roRiC4S1yfJJaA08DfvXZf6cVx1xfWl+ks57+3knkoWap1rjwh1RdGk5ChPbzD0AnAcWTMWRCbjuJnttlmWZnI1I6mhcQUKUEMoj8sR8m11YJ5woscYPsIle/rJOOosuMghczD1vRcg3eLUaWn1A5rsBa82RyxhiuYocEQVX59Hy6v3npT";

        public SmOption() {
            this.url = null;
            this.contactUrl = null;
            this.confUrl = null;
            this.traceUrl = null;
            this.url = "http://fp-it.fengkongcloud.com/v3/profile/android";
            this.confUrl = "http://fp-it.fengkongcloud.com/v3/cloudconf";
            this.traceUrl = "http://fp-it.fengkongcloud.com/v3/tracker?os=android";
            this.contactUrl = "http://fp-it.fengkongcloud.com/v3/profile/android";
        }

        public String getAppId() {
            return this.appId;
        }

        public String getChannel() {
            return this.channel;
        }

        public String getConfUrl() {
            return this.confUrl;
        }

        public String getContactUrl() {
            return this.contactUrl;
        }

        public int getHttpType() {
            return this.httpType;
        }

        public Set<String> getNotCollect() {
            return this.notCollect;
        }

        public String getOrganization() {
            return this.organization;
        }

        public String getPrivKey() {
            return this.privk;
        }

        public String getPublicKey() {
            return this.publicKey;
        }

        public IServerSmidCallback getServerIdCallback() {
            return this.callback;
        }

        public String getTraceUrl() {
            return this.traceUrl;
        }

        public String getUrl() {
            return this.url;
        }

        public boolean isCloudConf() {
            return this.cloudConf;
        }

        public boolean isFirst() {
            return this.first;
        }

        public boolean isSynMode() {
            return this.synMode;
        }

        public boolean isTransport() {
            return this.transport;
        }

        public boolean needEncrypt() {
            return this.encrypt;
        }

        public boolean needUsingMD5() {
            return this.usingMD5;
        }

        public void setAppId(String str) {
            this.appId = str;
        }

        public void setChannel(String str) {
            this.channel = str;
        }

        public void setCloudConf(boolean z) {
            this.cloudConf = z;
        }

        public void setConfUrl(String str) {
            this.confUrl = str;
        }

        public void setContactUrl(String str) {
            this.contactUrl = str;
        }

        public void setEncrypt(boolean z) {
            this.encrypt = z;
        }

        public void setFirst(boolean z) {
            this.first = z;
        }

        public void setHttpType(int i) {
            this.httpType = i;
        }

        public void setNotCollect(Set<String> set) {
            this.notCollect = set;
        }

        public void setOrganization(String str) {
            this.organization = str;
        }

        public void setPrivKey(String str) {
            this.privk = str;
        }

        public void setPublicKey(String str) {
            this.publicKey = str;
        }

        public void setServerIdCallback(IServerSmidCallback iServerSmidCallback) {
            this.callback = iServerSmidCallback;
        }

        public void setSynMode(boolean z) {
            this.synMode = z;
        }

        public void setTraceUrl(String str) {
            this.traceUrl = str;
        }

        public void setTransport(boolean z) {
            this.transport = z;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setUsingMD5(boolean z) {
            this.usingMD5 = z;
        }
    }

    public static int checkDeviceIdType(String str) {
        int iIdType;
        try {
            iIdType = SMSDK.idType(str);
        } catch (IOException unused) {
        }
        if (iIdType == 1 || iIdType == 2) {
            return 3;
        }
        if (iIdType == 0) {
            return 2;
        }
        return iIdType == -1 ? 1 : -1;
    }

    public static boolean cleanSmid() {
        return O000O0000OoO.O0000O000000oO().O000O0000O0oO();
    }

    public static void create(Context context, SmOption smOption) {
        if (smOption == null || smOption.getOrganization() == null) {
            throw new IllegalArgumentException("SmOption and organization could not be null.");
        }
        try {
            try {
                O000O00000o0O.O0000O000000oO().O000O00000OoO();
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    O000O00000oO.O0000O000000oO = applicationContext;
                    com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO(smOption.transport);
                    if (!O000O0000Oo0O.O0000O000000oO(unsafeCreate(smOption))) {
                    } else {
                        com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO(new Exception(O000O0000Oo0O.O000O0000Oo0O("9c8d9a9e8b9adf8d9a8b8a8d91df8c92969bdf9a928f8b86")));
                    }
                }
            } catch (Exception e) {
                com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO(e);
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
            }
        } finally {
            O000O00000o0O.O0000O000000oO().O000O00000o0O();
        }
    }

    public static String getBase(int i) {
        if (i != 0 && 1 != i) {
            return "";
        }
        try {
            if (initStatus == 0) {
                com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O000O00000OoO(i);
            }
            return getBaseSyn();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getBaseSyn() {
        return getBaseSyn(false);
    }

    public static String getBaseSyn(boolean z) {
        return com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O0000O000000oO(O0000O000000oO.O0000O000000oO(), 0);
    }

    public static String getContact(int i) {
        if (i != 0 && 1 != i) {
            return "";
        }
        try {
            if (initStatus == 0) {
                com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O000O00000o0O(i);
            }
            return getContactSyn();
        } catch (Exception e) {
            com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO(e);
            return "";
        }
    }

    public static String getContactSyn() {
        return getContactSyn(false);
    }

    public static String getContactSyn(boolean z) {
        return com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O000O00000OoO(z);
    }

    public static String getCore() {
        return getCore(false);
    }

    public static String getCore(boolean z) {
        return com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O0000O000000oO(z);
    }

    public static String getDeviceId() {
        return O000O0000OoO.O0000O000000oO().O000O00000o0O();
    }

    public static String getSDKVersion() {
        return "2.8.4";
    }

    public static IServerSmidCallback getServerIdCallback() {
        return mServerIdCallback;
    }

    public static String getXXXJsonInfo(com.ishumei.O0000O000000oO.O000O00000oO o000O00000oO, int i) {
        try {
            return com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Map<?, ?>) o000O00000oO.O0000O000000oO(i)).toString();
        } catch (Exception unused) {
            return "";
        }
    }

    private static void init(SmOption smOption) throws Exception {
        if (smOption == null) {
            throw new Exception("option null");
        }
        option = smOption;
        if (O000O0000Oo0O.O0000O000000oO(smOption.getOrganization())) {
            throw new Exception("organization empty");
        }
        com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O000O00000o0O();
        com.ishumei.O000O00000OoO.O000O00000o0O.O000O00000OoO(option.getOrganization());
        com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO(smOption.getTraceUrl());
        com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O0000O000000oO(option.getOrganization(), option.getConfUrl());
        if (option.getServerIdCallback() != null) {
            mServerIdCallback = option.getServerIdCallback();
        }
        com.ishumei.O000O00000OoO.O000O0000O0oO.O0000O000000oO.O0000O000000oO().O000O00000OoO();
    }

    public static synchronized void registerServerIdCallback(IServerSmidCallback iServerSmidCallback) {
        mServerIdCallback = iServerSmidCallback;
    }

    public static boolean setCloudConfigWithStr(String str) {
        synchronized (SmAntiFraud.class) {
            if (O000O0000Oo0O.O0000O000000oO(str)) {
                return false;
            }
            return com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O0000O000000oO(str);
        }
    }

    public static boolean setDeviceIdWithStr(String str) {
        if (O000O0000Oo0O.O0000O000000oO(str)) {
            return false;
        }
        synchronized (SmAntiFraud.class) {
            O000O0000OoO.O0000O000000oO().O0000O000000oO(str);
        }
        return true;
    }

    public static String unsafeCreate(SmOption smOption) throws Exception {
        if (!isInited) {
            synchronized (SmAntiFraud.class) {
                if (!isInited) {
                    isInited = true;
                    init(smOption);
                    initStatus = 0;
                }
            }
        }
        if (initStatus != 0) {
            throw new IOException();
        }
        com.ishumei.O000O0000OOoO.O0000O000000oO o0000O000000oO = new com.ishumei.O000O0000OOoO.O0000O000000oO();
        o0000O000000oO.O0000O000000oO();
        String strO000O00000o0O = O000O0000OoO.O0000O000000oO().O000O00000o0O();
        if (strO000O00000o0O == null || strO000O00000o0O.isEmpty()) {
            strO000O00000o0O = O000O0000OoO.O0000O000000oO().O000O0000OOoO();
            if (O000O0000Oo0O.O0000O000000oO(strO000O00000o0O)) {
                throw new Exception();
            }
            O000O0000OoO.O0000O000000oO().O0000O000000oO(strO000O00000o0O);
        }
        o0000O000000oO.O0000O000000oO();
        int iIdType = SMSDK.idType(strO000O00000o0O);
        boolean zO000O00000OoO = com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO.O0000O000000oO().O000O00000OoO();
        if (iIdType != 1) {
            if (zO000O00000OoO) {
                com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O0000O000000oO(1);
            }
        } else if (mServerIdCallback != null) {
            synchronized (SmAntiFraud.class) {
                mServerIdCallback.onSuccess(strO000O00000o0O);
            }
        }
        if (zO000O00000OoO) {
            com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO().O000O00000OoO();
        }
        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(TAG, "unsafeCreate finish.");
        return O000O0000OoO.O0000O000000oO().O000O00000o0O();
    }
}
