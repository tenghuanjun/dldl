package com.huya.mtp.hyns;

import com.huya.mtp.data.DataListener;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class NSMethod {
    protected Object[] mArgs;
    protected Method mMethod;
    protected Object mProxy;
    protected Class<?> mProxyCls;

    public abstract Object getIdentifier();

    public abstract NSRequest readRequest();

    public abstract NSResponse<?> readResponse(NSResult nSResult, DataListener dataListener) throws NSException;

    public NSMethod(Class<?> cls, Object obj, Method method, Object[] objArr) {
        this.mProxyCls = cls;
        this.mProxy = obj;
        this.mMethod = method;
        this.mArgs = objArr;
    }

    public Class<?> getProxyCls() {
        return this.mProxyCls;
    }

    public Object getProxy() {
        return this.mProxy;
    }

    public Method getMethod() {
        return this.mMethod;
    }

    public Object[] getArgs() {
        return this.mArgs;
    }
}
