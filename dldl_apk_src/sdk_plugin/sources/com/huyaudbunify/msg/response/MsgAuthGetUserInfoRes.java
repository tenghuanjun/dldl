package com.huyaudbunify.msg.response;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgAuthGetUserInfoRes {
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
        private String avatar;
        private String nick_name;

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }

        public String getNick_name() {
            return this.nick_name;
        }

        public void setNick_name(String str) {
            this.nick_name = str;
        }

        public DataBean(String str, String str2) {
            this.avatar = str;
            this.nick_name = str2;
        }

        public String toString() {
            return "DataBean{avatar='" + this.avatar + "', nick_name='" + this.nick_name + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public String toString() {
        return "MsgAuthGetUserInfoRes{code=" + this.code + ", msg='" + this.msg + "', data=" + this.data + AbstractJsonLexerKt.END_OBJ;
    }

    public MsgAuthGetUserInfoRes(int i, String str, DataBean dataBean) {
        this.code = i;
        this.msg = str;
        this.data = dataBean;
    }
}
