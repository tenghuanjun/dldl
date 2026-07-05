package net.security.device.api.id.oaid;

import android.content.Context;
import java.lang.reflect.Method;
import net.security.device.api.id.IOAID;
import net.security.device.api.id.IOAIDGetter;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class XiaomiImpl implements IOAID {
    private final Context context;
    private Class<?> idProvider;

    public XiaomiImpl(Context context) {
        this.context = context;
        try {
            this.idProvider = Class.forName("com.android.id.impl.IdProviderImpl");
        } catch (Exception unused) {
        }
    }

    @Override // net.security.device.api.id.IOAID
    public boolean supportOAID() {
        return this.idProvider != null;
    }

    @Override // net.security.device.api.id.IOAID
    public void doGet(IOAIDGetter iOAIDGetter) {
        if (this.context == null) {
            iOAIDGetter.onOAIDGetError(new NullPointerException("OAID context is null"));
            return;
        }
        if (this.idProvider == null) {
            iOAIDGetter.onOAIDGetError(new NullPointerException("Xiaomi IdProvider not exists"));
            return;
        }
        try {
            String strInvokeMethod = invokeMethod("getDefaultUDID");
            if (strInvokeMethod != null && strInvokeMethod.length() > 0) {
                iOAIDGetter.onOAIDGetComplete(strInvokeMethod);
                return;
            }
            String strInvokeMethod2 = invokeMethod("getOAID");
            if (strInvokeMethod2 != null && strInvokeMethod2.length() > 0) {
                iOAIDGetter.onOAIDGetComplete(strInvokeMethod2);
                return;
            }
            throw new RuntimeException("Xiaomi OAID get failed");
        } catch (Exception e) {
            iOAIDGetter.onOAIDGetError(e);
        }
    }

    private String invokeMethod(String str) {
        Class<?> cls;
        if (str == null || str.isEmpty() || (cls = this.idProvider) == null) {
            return null;
        }
        try {
            Method method = cls.getMethod(str, Context.class);
            if (method != null) {
                return (String) method.invoke(this.idProvider.newInstance(), this.context);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
