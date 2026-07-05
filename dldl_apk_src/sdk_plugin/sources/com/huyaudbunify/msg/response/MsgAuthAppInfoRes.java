package com.huyaudbunify.msg.response;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgAuthAppInfoRes {
    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private String appLogo;
        private String appName;
        private String appScope;
        private String sessionId;

        public String getSessionId() {
            return this.sessionId;
        }

        public void setSessionId(String str) {
            this.sessionId = str;
        }

        public String getAppName() {
            return this.appName;
        }

        public void setAppName(String str) {
            this.appName = str;
        }

        public String getAppLogo() {
            return this.appLogo;
        }

        public void setAppLogo(String str) {
            this.appLogo = str;
        }

        public String getAppScope() {
            return this.appScope;
        }

        public void setAppScope(String str) {
            this.appScope = str;
        }

        public String toString() {
            return "DataBean{sessionId='" + this.sessionId + "', appName='" + this.appName + "', appLogo='" + this.appLogo + "', appScope='" + this.appScope + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public MsgAuthAppInfoRes(int i, String str, DataBean dataBean) {
        this.code = i;
        this.msg = str;
        this.data = dataBean;
    }

    public String toString() {
        return "MsgAuthAppInfoRes{code=" + this.code + ", msg='" + this.msg + "', data=" + this.data + AbstractJsonLexerKt.END_OBJ;
    }
}
