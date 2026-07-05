package com.taptap.sdk.okhttp3.internal.connection;

import com.taptap.sdk.okhttp3.Route;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class RouteDatabase {
    private final Set<Route> failedRoutes = new LinkedHashSet();

    public synchronized void failed(Route route) {
        this.failedRoutes.add(route);
    }

    public synchronized void connected(Route route) {
        this.failedRoutes.remove(route);
    }

    public synchronized boolean shouldPostpone(Route route) {
        return this.failedRoutes.contains(route);
    }
}
