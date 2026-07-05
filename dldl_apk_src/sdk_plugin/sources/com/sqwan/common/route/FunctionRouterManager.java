package com.sqwan.common.route;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.sq.tool.logger.SQLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FunctionRouterManager implements FunctionRouter {
    private static volatile FunctionRouterManager sInstance;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final List<FunctionRouter> mRouters = new ArrayList();

    public static FunctionRouterManager getInstance() {
        if (sInstance == null) {
            synchronized (FunctionRouterManager.class) {
                if (sInstance == null) {
                    sInstance = new FunctionRouterManager();
                }
            }
        }
        return sInstance;
    }

    private FunctionRouterManager() {
    }

    public void register(FunctionRouter functionRouter) {
        if (functionRouter == null || this.mRouters.contains(functionRouter)) {
            return;
        }
        SQLog.i("【Router】增加路由: " + functionRouter);
        this.mRouters.add(functionRouter);
    }

    @Override // com.sqwan.common.route.FunctionRouter
    /* JADX INFO: renamed from: call, reason: merged with bridge method [inline-methods] */
    public void lambda$call$0$FunctionRouterManager(final Activity activity, final String str, final Bundle bundle) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            this.mHandler.post(new Runnable() { // from class: com.sqwan.common.route.-$$Lambda$FunctionRouterManager$SNyneZQWjLpli6R-KOjcqsAY2LA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$call$0$FunctionRouterManager(activity, str, bundle);
                }
            });
            return;
        }
        SQLog.d("【Router】通过路由调用: (" + activity + ")" + str + ", data=" + bundle);
        boolean z = false;
        for (FunctionRouter functionRouter : this.mRouters) {
            if (functionRouter != null) {
                functionRouter.lambda$call$0$FunctionRouterManager(activity, str, bundle);
                z = true;
            }
        }
        if (z) {
            return;
        }
        SQLog.w("【Router】无路由响应调用: (" + activity + ")" + str + ", data=" + bundle);
    }
}
