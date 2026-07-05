package com.huya.mtp.hyns.retrofit;

import com.huya.mtp.hyns.NSProtocol;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import retrofit2.Retrofit;
import retrofit2.http.Url;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RetrofitProtocol extends NSProtocol {
    private static final Object mRetrofitLock = new Object();
    private boolean isDebug;
    private Map<String, Retrofit> mMultiRetrofit = new ConcurrentHashMap();
    private OnRetrofitLoader mOnRetrofitLoader;

    public interface OnRetrofitLoader {
        Retrofit onLoad(String str);
    }

    public void setOnRetrofitLoader(OnRetrofitLoader onRetrofitLoader) {
        this.mOnRetrofitLoader = onRetrofitLoader;
    }

    public void setDebug(boolean z) {
        this.isDebug = z;
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public <T> T get(Class<T> cls) {
        Retrofit retrofit = getRetrofit(cls);
        if (retrofit == null) {
            throw new RuntimeException("check if retrofit loader has been added.");
        }
        return (T) retrofit.create(cls);
    }

    private <T> Retrofit getRetrofit(Class<T> cls) {
        String strReleaseUrl;
        Retrofit retrofitOnLoad;
        BaseUrl baseUrl = (BaseUrl) cls.getAnnotation(BaseUrl.class);
        if (baseUrl == null) {
            strReleaseUrl = "";
        } else if (this.isDebug) {
            strReleaseUrl = baseUrl.debugUrl();
        } else {
            strReleaseUrl = baseUrl.releaseUrl();
        }
        if (this.mMultiRetrofit.containsKey(strReleaseUrl)) {
            return this.mMultiRetrofit.get(strReleaseUrl);
        }
        if (this.mOnRetrofitLoader == null) {
            return null;
        }
        synchronized (mRetrofitLock) {
            retrofitOnLoad = this.mOnRetrofitLoader.onLoad(strReleaseUrl);
            this.mMultiRetrofit.put(strReleaseUrl, retrofitOnLoad);
        }
        return retrofitOnLoad;
    }

    @Override // com.huya.mtp.hyns.NSProtocol
    public boolean accept(Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (checkIsRetrofitAnnotation(method.getDeclaredAnnotations())) {
                return true;
            }
            for (Annotation[] annotationArr : method.getParameterAnnotations()) {
                if (checkIsRetrofitAnnotation(annotationArr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIsRetrofitAnnotation(Annotation[] annotationArr) {
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().getPackage().equals(Url.class.getPackage())) {
                return true;
            }
        }
        return false;
    }
}
