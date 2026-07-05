package com.anthonycr.grant;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class PermissionsResultAction {
    private static final String TAG = PermissionsResultAction.class.getSimpleName();
    private Looper mLooper;
    private final Set<String> mPermissions;

    public abstract void onDenied(String str);

    public abstract void onGranted();

    public PermissionsResultAction() {
        this.mPermissions = new HashSet(1);
        this.mLooper = Looper.getMainLooper();
    }

    public PermissionsResultAction(Looper looper) {
        this.mPermissions = new HashSet(1);
        this.mLooper = Looper.getMainLooper();
        this.mLooper = looper;
    }

    public synchronized boolean shouldIgnorePermissionNotFound(String str) {
        Log.d(TAG, "Permission not found: " + str);
        return true;
    }

    protected final synchronized boolean onResult(String str, int i) {
        if (i == 0) {
            return onResult(str, Permissions.GRANTED);
        }
        return onResult(str, Permissions.DENIED);
    }

    protected final synchronized boolean onResult(final String str, Permissions permissions) {
        this.mPermissions.remove(str);
        if (permissions == Permissions.GRANTED) {
            if (this.mPermissions.isEmpty()) {
                new Handler(this.mLooper).post(new Runnable() { // from class: com.anthonycr.grant.PermissionsResultAction.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PermissionsResultAction.this.onGranted();
                    }
                });
                return true;
            }
        } else {
            if (permissions == Permissions.DENIED) {
                new Handler(this.mLooper).post(new Runnable() { // from class: com.anthonycr.grant.PermissionsResultAction.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PermissionsResultAction.this.onDenied(str);
                    }
                });
                return true;
            }
            if (permissions == Permissions.NOT_FOUND) {
                if (shouldIgnorePermissionNotFound(str)) {
                    if (this.mPermissions.isEmpty()) {
                        new Handler(this.mLooper).post(new Runnable() { // from class: com.anthonycr.grant.PermissionsResultAction.3
                            @Override // java.lang.Runnable
                            public void run() {
                                PermissionsResultAction.this.onGranted();
                            }
                        });
                        return true;
                    }
                } else {
                    new Handler(this.mLooper).post(new Runnable() { // from class: com.anthonycr.grant.PermissionsResultAction.4
                        @Override // java.lang.Runnable
                        public void run() {
                            PermissionsResultAction.this.onDenied(str);
                        }
                    });
                    return true;
                }
            }
        }
        return false;
    }

    protected final synchronized void registerPermissions(String[] strArr) {
        Collections.addAll(this.mPermissions, strArr);
    }
}
