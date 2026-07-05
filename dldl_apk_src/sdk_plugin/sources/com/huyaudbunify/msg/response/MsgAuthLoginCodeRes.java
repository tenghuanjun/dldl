package com.huyaudbunify.msg.response;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgAuthLoginCodeRes {
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
        private String code;
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public String toString() {
            return "DataBean{url='" + this.url + "', code='" + this.code + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public MsgAuthLoginCodeRes(int i, String str, DataBean dataBean) {
        this.data = new DataBean();
        this.code = i;
        this.msg = str;
        this.data = dataBean;
    }

    public String toString() {
        return "MsgAuthLoginCodeRes{code=" + this.code + ", msg='" + this.msg + "', data=" + this.data + AbstractJsonLexerKt.END_OBJ;
    }
}
