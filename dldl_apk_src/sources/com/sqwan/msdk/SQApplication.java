package com.sqwan.msdk;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.sqwan.msdk.api.PurchaseReportBean;
import com.sqwan.msdk.api.RegisterReportBean;
import com.sqwan.msdk.api.SQAppApi;
import com.sqwan.msdk.api.SQMediaReportInterface;
import com.sqwan.msdk.api.SQReportInterface;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SQApplication extends MultiDexApplication implements SQAppApi {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Object sInstance;
    private final SQAppApi mDelegate = getDelegate();

    @Override // com.sqwan.msdk.api.SQAppApi
    public void insertAppContext(Application application) {
    }

    @Deprecated
    public static Object getInstance() {
        return sInstance;
    }

    @Override // android.support.multidex.MultiDexApplication, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        sInstance = this;
        this.mDelegate.attachBaseContext(context);
    }

    @Override // android.app.Application, com.sqwan.msdk.api.SQAppApi
    public void onCreate() {
        super.onCreate();
        this.mDelegate.insertAppContext(this);
        this.mDelegate.onCreate();
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void setReporter(SQReportInterface sQReportInterface) {
        this.mDelegate.setReporter(sQReportInterface);
    }

    @Override // com.sqwan.msdk.api.SQAppApi
    public void setMediaReporter(SQMediaReportInterface sQMediaReportInterface) {
        this.mDelegate.setMediaReporter(sQMediaReportInterface);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return SQwanCore.getInstance().getResources(super.getResources());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return SQwanCore.getInstance().getAssets(super.getAssets());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return SQwanCore.getInstance().getClassLoader(super.getClassLoader());
    }

    private SQAppApi getDelegate() {
        Object objNewInstance;
        Object objInvoke;
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            Log.e("sqsdk", "无法获取class loader");
            return null;
        }
        String delegateClassName = getDelegateClassName();
        try {
            Class<?> clsLoadClass = classLoader.loadClass(delegateClassName);
            try {
                objInvoke = clsLoadClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th) {
                Log.w("sqsdk", "无法通过getInstance获取目标类实例, " + th);
            }
            if (objInvoke instanceof SQAppApi) {
                return (SQAppApi) objInvoke;
            }
            Log.e("sqsdk", "getInstance返回的实例类型异常: " + objInvoke);
            try {
                objNewInstance = clsLoadClass.newInstance();
            } catch (Throwable th2) {
                Log.w("sqsdk", "无法通过newInstance获取目标类实例, " + th2);
            }
            if (objNewInstance instanceof SQAppApi) {
                return (SQAppApi) objNewInstance;
            }
            Log.e("sqsdk", "无参数构造函数返回的实例类型异常: " + objNewInstance);
            return null;
        } catch (Throwable th3) {
            Log.e("sqsdk", "无法加载目标类: " + delegateClassName + ", " + th3);
            return null;
        }
    }

    /* JADX INFO: renamed from: com.sqwan.msdk.SQApplication$1, reason: invalid class name */
    class AnonymousClass1 implements InvocationHandler {
        final /* synthetic */ SQMediaReportInterface val$listener;

        AnonymousClass1(SQMediaReportInterface sQMediaReportInterface) {
            this.val$listener = sQMediaReportInterface;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (method.getName().equals("mediaRefer")) {
                return this.val$listener.mediaRefer();
            }
            return null;
        }
    }

    private String getDelegateClassName() {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader != null) {
            try {
                Field declaredField = classLoader.loadClass("com.sqwan.msdk._Delegate").getDeclaredField("APP_NAME");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(null);
                if (obj instanceof String) {
                    Log.d("sqsdk", "使用代理类: " + obj);
                    return (String) obj;
                }
            } catch (ClassNotFoundException unused) {
            } catch (Throwable th) {
                Log.e("sqsdk", "获取代理类名失败, " + th);
            }
        } else {
            Log.e("sqsdk", "无法获取class loader");
        }
        Log.d("sqsdk", "SQApplication使用默认代理类");
        return "com.sqwan.msdk.SQApplicationImpl";
    }

    /* JADX INFO: renamed from: com.sqwan.msdk.SQApplication$2, reason: invalid class name */
    class AnonymousClass2 implements InvocationHandler {
        final /* synthetic */ SQReportInterface val$listener;

        AnonymousClass2(SQReportInterface sQReportInterface) {
            this.val$listener = sQReportInterface;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            if (name.equals("init")) {
                this.val$listener.init((Context) objArr[0]);
                return null;
            }
            if (name.equals("afterPermission")) {
                this.val$listener.afterPermission((Context) objArr[0]);
                return null;
            }
            if (name.equals("eventRegister")) {
                Class<?> cls = objArr[0].getClass();
                Method method2 = cls.getMethod("getType", new Class[0]);
                Method method3 = cls.getMethod("isSuccess", new Class[0]);
                String str = (String) method2.invoke(objArr[0], new Object[0]);
                boolean zBooleanValue = ((Boolean) method3.invoke(objArr[0], new Object[0])).booleanValue();
                Log.i("report", "report bean: type:  " + str + ", isSuccess" + zBooleanValue);
                this.val$listener.eventRegister(new RegisterReportBean(str, zBooleanValue));
                return null;
            }
            Class<?> cls2 = objArr[0].getClass();
            Method method4 = cls2.getMethod("getProductType", new Class[0]);
            Method method5 = cls2.getMethod("getProductName", new Class[0]);
            Method method6 = cls2.getMethod("getProductId", new Class[0]);
            Method method7 = cls2.getMethod("getCount", new Class[0]);
            Method method8 = cls2.getMethod("getChannel", new Class[0]);
            Method method9 = cls2.getMethod("getCurrency", new Class[0]);
            Method method10 = cls2.getMethod("isSuccess", new Class[0]);
            Method method11 = cls2.getMethod("getPrice", new Class[0]);
            Method method12 = cls2.getMethod("getOrderId", new Class[0]);
            String str2 = (String) method4.invoke(objArr[0], new Object[0]);
            String str3 = (String) method5.invoke(objArr[0], new Object[0]);
            String str4 = (String) method6.invoke(objArr[0], new Object[0]);
            int iIntValue = ((Integer) method7.invoke(objArr[0], new Object[0])).intValue();
            String str5 = (String) method8.invoke(objArr[0], new Object[0]);
            String str6 = (String) method9.invoke(objArr[0], new Object[0]);
            boolean zBooleanValue2 = ((Boolean) method10.invoke(objArr[0], new Object[0])).booleanValue();
            int iIntValue2 = ((Integer) method11.invoke(objArr[0], new Object[0])).intValue();
            String str7 = (String) method12.invoke(objArr[0], new Object[0]);
            Log.i("report", "report bean: productType:  " + str2 + ", productName" + str3 + ", productId" + str4 + ", count" + iIntValue + ", channel" + str5 + ", currency" + str6 + ", success" + zBooleanValue2 + ", price" + iIntValue2 + ", orderId" + str7);
            PurchaseReportBean purchaseReportBean = new PurchaseReportBean();
            purchaseReportBean.setProductType(str2);
            purchaseReportBean.setProductName(str3);
            purchaseReportBean.setProductId(str4);
            purchaseReportBean.setCount(iIntValue);
            purchaseReportBean.setChannel(str5);
            purchaseReportBean.setCurrency(str6);
            purchaseReportBean.setSuccess(zBooleanValue2);
            purchaseReportBean.setPrice(iIntValue2);
            purchaseReportBean.setOrderId(str7);
            this.val$listener.eventPurchase(purchaseReportBean);
            return null;
        }
    }
}
