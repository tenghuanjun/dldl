package com.snail.antifake.deviceid;

import android.os.RemoteException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BinderUtil {
    public static int getTransactionId(Object obj, String str) throws IllegalAccessException, NoSuchFieldException, RemoteException {
        Field declaredField = obj.getClass().getEnclosingClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return ((Integer) declaredField.get(obj)).intValue();
    }

    public static String getInterfaceDescriptor(Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return (String) obj.getClass().getDeclaredMethod("getInterfaceDescriptor", new Class[0]).invoke(obj, new Object[0]);
    }
}
