package com.bumptech.glide.manager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ConnectivityMonitor extends LifecycleListener {

    public interface ConnectivityListener {
        void onConnectivityChanged(boolean z);
    }
}
