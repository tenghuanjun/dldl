package com.sq.libwebsocket.util;

import com.sq.tool.logger.SQLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogImpl implements Logable {
    @Override // com.sq.libwebsocket.util.Logable
    public void v(String str, String str2) {
        SQLog.vt(str, str2);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void v(String str, String str2, Throwable th) {
        SQLog.vt(str, str2, th);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void d(String str, String str2) {
        SQLog.dt(str, str2);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void d(String str, String str2, Throwable th) {
        SQLog.dt(str, str2, th);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void i(String str, String str2) {
        SQLog.it(str, str2);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void i(String str, String str2, Throwable th) {
        SQLog.it(str, str2, th);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void e(String str, String str2) {
        SQLog.et(str, str2);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void e(String str, String str2, Throwable th) {
        SQLog.et(str, str2, th);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void w(String str, Throwable th) {
        SQLog.wt(str, "", th);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void wtf(String str, String str2) {
        SQLog.et(str, str2);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void wtf(String str, Throwable th) {
        SQLog.et(str, "", th);
    }

    @Override // com.sq.libwebsocket.util.Logable
    public void wtf(String str, String str2, Throwable th) {
        SQLog.et(str, str2, th);
    }
}
