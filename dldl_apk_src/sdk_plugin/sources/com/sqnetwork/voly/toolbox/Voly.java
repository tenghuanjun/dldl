package com.sqnetwork.voly.toolbox;

import android.content.Context;
import com.sqnetwork.voly.Network;
import com.sqnetwork.voly.RequestQueue;
import com.sqnetwork.voly.ResponseDelivery;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Voly {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context, BaseHttpStack stack) {
        BasicNetwork basicNetwork;
        if (stack == null) {
            basicNetwork = new BasicNetwork(new OkHttp3Stack(null));
        } else {
            basicNetwork = new BasicNetwork(stack);
        }
        return newRequestQueue(context, basicNetwork);
    }

    private static RequestQueue newRequestQueue(Context context, Network network) {
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new File(context.getCacheDir(), DEFAULT_CACHE_DIR)), network);
        requestQueue.start();
        return requestQueue;
    }

    public static RequestQueue newRequestQueue(Context context, BaseHttpStack stack, int threadPoolSize, ResponseDelivery delivery) {
        BasicNetwork basicNetwork;
        if (stack == null) {
            basicNetwork = new BasicNetwork(new OkHttp3Stack(null));
        } else {
            basicNetwork = new BasicNetwork(stack);
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new File(context.getCacheDir(), DEFAULT_CACHE_DIR)), basicNetwork, threadPoolSize, delivery);
        requestQueue.start();
        return requestQueue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, (BaseHttpStack) null);
    }
}
