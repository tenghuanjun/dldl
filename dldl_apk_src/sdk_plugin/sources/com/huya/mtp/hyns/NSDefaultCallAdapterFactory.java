package com.huya.mtp.hyns;

import com.huya.mtp.hyns.NSCallAdapter;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSDefaultCallAdapterFactory extends NSCallAdapter.Factory {
    @Override // com.huya.mtp.hyns.NSCallAdapter.Factory
    public NSCallAdapter getAdapter(Method method) {
        if (NSCall.class.isAssignableFrom(getRawType(method.getGenericReturnType()))) {
            return new NSCallAdapter<NSCall, NSCall>() { // from class: com.huya.mtp.hyns.NSDefaultCallAdapterFactory.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.huya.mtp.hyns.NSCallAdapter
                public NSCall adapt(NSCall nSCall) {
                    return nSCall;
                }
            };
        }
        return null;
    }
}
