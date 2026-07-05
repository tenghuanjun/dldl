package com.huyaudbunify.msg.response;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgAuthAccessTokenRes {
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
        private String access_token;
        private String open_id;
        private String union_id;

        public String getAccess_token() {
            return this.access_token;
        }

        public void setAccess_token(String str) {
            this.access_token = str;
        }

        public String getOpen_id() {
            return this.open_id;
        }

        public void setOpen_id(String str) {
            this.open_id = str;
        }

        public String getUnion_id() {
            return this.union_id;
        }

        public void setUnion_id(String str) {
            this.union_id = str;
        }

        public DataBean(String str, String str2, String str3) {
            this.access_token = str;
            this.open_id = str2;
            this.union_id = str3;
        }

        public String toString() {
            return "DataBean{access_token=" + this.access_token + ", open_id=" + this.open_id + ", union_id=" + this.union_id + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public MsgAuthAccessTokenRes(int i, String str, DataBean dataBean) {
        this.code = i;
        this.msg = str;
        this.data = dataBean;
    }

    public String toString() {
        return "MsgAuthAccessTokenRes{code=" + this.code + ", msg='" + this.msg + "', data=" + this.data + AbstractJsonLexerKt.END_OBJ;
    }
}
