package com.anthonycr.grant;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PermissionsManager {
    private static final String TAG = PermissionsManager.class.getSimpleName();
    private static PermissionsManager mInstance = null;
    private final Set<String> mPendingRequests = new HashSet(1);
    private final Set<String> mPermissions = new HashSet(1);
    private final List<WeakReference<PermissionsResultAction>> mPendingActions = new ArrayList(1);

    public static PermissionsManager getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionsManager();
        }
        return mInstance;
    }

    private PermissionsManager() {
        initializePermissionsMap();
    }

    private synchronized void initializePermissionsMap() {
        for (Field field : Manifest.permission.class.getFields()) {
            String str = null;
            try {
                str = (String) field.get("");
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Could not access field", e);
            }
            this.mPermissions.add(str);
        }
    }

    private synchronized String[] getManifestPermissions(Activity activity) {
        ArrayList arrayList;
        String[] strArr;
        PackageInfo packageInfo = null;
        arrayList = new ArrayList(1);
        try {
            Log.d(TAG, activity.getPackageName());
            packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "A problem occurred when retrieving permissions", e);
        }
        if (packageInfo != null && (strArr = packageInfo.requestedPermissions) != null) {
            for (String str : strArr) {
                Log.d(TAG, "Manifest contained permission: " + str);
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private synchronized void addPendingAction(String[] strArr, PermissionsResultAction permissionsResultAction) {
        if (permissionsResultAction == null) {
            return;
        }
        permissionsResultAction.registerPermissions(strArr);
        this.mPendingActions.add(new WeakReference<>(permissionsResultAction));
    }

    private synchronized void removePendingAction(PermissionsResultAction permissionsResultAction) {
        Iterator<WeakReference<PermissionsResultAction>> it = this.mPendingActions.iterator();
        while (it.hasNext()) {
            WeakReference<PermissionsResultAction> next = it.next();
            if (next.get() == permissionsResultAction || next.get() == null) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean hasPermission(android.content.Context r1, java.lang.String r2) {
        /*
            r0 = this;
            monitor-enter(r0)
            if (r1 == 0) goto L16
            int r1 = android.support.v4.app.ActivityCompat.checkSelfPermission(r1, r2)     // Catch: java.lang.Throwable -> L13
            if (r1 == 0) goto L11
            java.util.Set<java.lang.String> r1 = r0.mPermissions     // Catch: java.lang.Throwable -> L13
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Throwable -> L13
            if (r1 != 0) goto L16
        L11:
            r1 = 1
            goto L17
        L13:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L16:
            r1 = 0
        L17:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anthonycr.grant.PermissionsManager.hasPermission(android.content.Context, java.lang.String):boolean");
    }

    public synchronized boolean hasAllPermissions(Context context, String[] strArr) {
        if (context == null) {
            return false;
        }
        boolean zHasPermission = true;
        for (String str : strArr) {
            zHasPermission &= hasPermission(context, str);
        }
        return zHasPermission;
    }

    public synchronized void requestAllManifestPermissionsIfNecessary(Activity activity, PermissionsResultAction permissionsResultAction) {
        if (activity == null) {
            return;
        }
        requestPermissionsIfNecessaryForResult(activity, getManifestPermissions(activity), permissionsResultAction);
    }

    public synchronized void requestPermissionsIfNecessaryForResult(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        if (activity == null) {
            return;
        }
        addPendingAction(strArr, permissionsResultAction);
        if (Build.VERSION.SDK_INT < 23) {
            doPermissionWorkBeforeAndroidM(activity, strArr, permissionsResultAction);
        } else {
            List<String> permissionsListToRequest = getPermissionsListToRequest(activity, strArr, permissionsResultAction);
            if (permissionsListToRequest.isEmpty()) {
                removePendingAction(permissionsResultAction);
            } else {
                String[] strArr2 = (String[]) permissionsListToRequest.toArray(new String[permissionsListToRequest.size()]);
                this.mPendingRequests.addAll(permissionsListToRequest);
                ActivityCompat.requestPermissions(activity, strArr2, 1);
            }
        }
    }

    public synchronized void requestPermissionsIfNecessaryForResult(Fragment fragment, String[] strArr, PermissionsResultAction permissionsResultAction) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        addPendingAction(strArr, permissionsResultAction);
        if (Build.VERSION.SDK_INT < 23) {
            doPermissionWorkBeforeAndroidM(activity, strArr, permissionsResultAction);
        } else {
            List<String> permissionsListToRequest = getPermissionsListToRequest(activity, strArr, permissionsResultAction);
            if (permissionsListToRequest.isEmpty()) {
                removePendingAction(permissionsResultAction);
            } else {
                String[] strArr2 = (String[]) permissionsListToRequest.toArray(new String[permissionsListToRequest.size()]);
                this.mPendingRequests.addAll(permissionsListToRequest);
                fragment.requestPermissions(strArr2, 1);
            }
        }
    }

    public synchronized void notifyPermissionsChange(String[] strArr, int[] iArr) {
        int i;
        int length = strArr.length;
        if (iArr.length < length) {
            length = iArr.length;
        }
        Iterator<WeakReference<PermissionsResultAction>> it = this.mPendingActions.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PermissionsResultAction permissionsResultAction = it.next().get();
            while (i < length) {
                i = (permissionsResultAction == null || permissionsResultAction.onResult(strArr[i], iArr[i])) ? 0 : i + 1;
                it.remove();
                break;
            }
        }
        while (i < length) {
            this.mPendingRequests.remove(strArr[i]);
            i++;
        }
    }

    private void doPermissionWorkBeforeAndroidM(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        for (String str : strArr) {
            if (permissionsResultAction != null) {
                if (!this.mPermissions.contains(str)) {
                    permissionsResultAction.onResult(str, Permissions.NOT_FOUND);
                } else if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                    permissionsResultAction.onResult(str, Permissions.DENIED);
                } else {
                    permissionsResultAction.onResult(str, Permissions.GRANTED);
                }
            }
        }
    }

    private List<String> getPermissionsListToRequest(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (this.mPermissions.contains(str)) {
                if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                    arrayList.add(str);
                } else if (permissionsResultAction != null) {
                    permissionsResultAction.onResult(str, Permissions.GRANTED);
                }
            } else if (permissionsResultAction != null) {
                permissionsResultAction.onResult(str, Permissions.NOT_FOUND);
            }
        }
        return arrayList;
    }
}
