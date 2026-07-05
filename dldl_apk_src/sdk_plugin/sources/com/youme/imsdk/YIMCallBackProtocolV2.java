package com.youme.imsdk;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMCallBackProtocolV2 implements Runnable {
    private static final String TAG = YIMCallBackProtocolV2.class.getSimpleName();
    private String mMethodName;
    private Object mObject;
    private Class<?>[] mParamTypes = null;
    private Object[] mParams;

    public YIMCallBackProtocolV2(Object obj, String str, Object... objArr) {
        this.mObject = null;
        this.mMethodName = "";
        this.mParams = null;
        if (obj != null) {
            this.mObject = obj;
            this.mMethodName = str;
            this.mParams = objArr;
            getParamTypeV2(objArr);
            Log.d(TAG, "object lass : " + this.mObject.getClass().getName() + ", method : " + this.mMethodName);
            for (int i = 0; i < this.mParams.length; i++) {
                Log.d(TAG, "param[" + i + "]" + this.mParams[i] + ", type:" + this.mParamTypes[i].getSimpleName() + "， param： " + this.mParamTypes[i]);
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

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void getParamTypeV2(Object[] objArr) {
        this.mParamTypes = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            String simpleName = objArr[i].getClass().getSimpleName();
            byte b = -1;
            switch (simpleName.hashCode()) {
                case -672261858:
                    if (simpleName.equals("Integer")) {
                        b = 0;
                    }
                    break;
                case 2374300:
                    if (simpleName.equals("Long")) {
                        b = 1;
                    }
                    break;
                case 67973692:
                    if (simpleName.equals("Float")) {
                        b = 3;
                    }
                    break;
                case 79860828:
                    if (simpleName.equals("Short")) {
                        b = 5;
                    }
                    break;
                case 1729365000:
                    if (simpleName.equals("Boolean")) {
                        b = 4;
                    }
                    break;
                case 2052876273:
                    if (simpleName.equals("Double")) {
                        b = 2;
                    }
                    break;
            }
            if (b == 0) {
                this.mParamTypes[i] = Integer.TYPE;
            } else if (b == 1) {
                this.mParamTypes[i] = Long.TYPE;
            } else if (b == 2) {
                this.mParamTypes[i] = Double.TYPE;
            } else if (b == 3) {
                this.mParamTypes[i] = Float.TYPE;
            } else if (b == 4) {
                this.mParamTypes[i] = Boolean.TYPE;
            } else if (b == 5) {
                this.mParamTypes[i] = Short.TYPE;
            } else {
                this.mParamTypes[i] = objArr[i].getClass();
            }
        }
    }
}
