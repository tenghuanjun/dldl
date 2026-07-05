package com.sqwan.common.request;

import com.sqwan.common.net.sq.Response;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseResponseBean {
    private String data;
    private String msg;
    private int state;

    public BaseResponseBean() {
    }

    public BaseResponseBean(Response response) {
        if (response == null) {
            return;
        }
        setMsg(response.getMessage());
        setData(response.getData());
        setState(response.getState());
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getState() {
        return this.state;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getData() {
        return this.data;
    }

    public boolean isSuccess() {
        return this.state == 1;
    }

    public String toString() {
        return "BaseResponseBean{state=" + this.state + ", msg='" + this.msg + "', data='" + this.data + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
