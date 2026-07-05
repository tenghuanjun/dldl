package com.youme.imsdk;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMCallBackProtocol implements Runnable {
    private static final String TAG = YIMCallBackProtocol.class.getSimpleName();
    private String mMethodName;
    private Object mObject;
    private Class<?>[] mParamTypes = null;
    private Object[] mParams;

    public YIMCallBackProtocol(Object obj, String str, Object... objArr) {
        this.mObject = null;
        this.mMethodName = "";
        this.mParams = null;
        if (obj != null) {
            this.mObject = obj;
            this.mMethodName = str;
            this.mParams = objArr;
            getParamType(objArr);
            Log.d(TAG, "object lass : " + this.mObject.getClass().getName() + ", method : " + this.mMethodName);
            for (int i = 0; i < this.mParams.length; i++) {
                Log.d(TAG, "param[" + i + "]" + this.mParams[i] + ", type:" + this.mParamTypes[i].getSimpleName());
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mObject != null) {
                this.mObject.getClass().getMethod(this.mMethodName, this.mParamTypes).invoke(this.mObject, this.mParams);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "can't find method:" + this.mMethodName);
            e3.printStackTrace();
        } catch (NullPointerException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }

    private void getParamType(Object[] objArr) {
        this.mParamTypes = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            this.mParamTypes[i] = objArr[i].getClass();
        }
    }
}
