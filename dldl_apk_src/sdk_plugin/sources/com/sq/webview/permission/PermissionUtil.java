package com.sq.webview.permission;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PermissionUtil {
    static boolean isGrantedPermissions(Context context, List<String> permissions) {
        if (permissions.isEmpty()) {
            return false;
        }
        Iterator<String> it = permissions.iterator();
        while (it.hasNext()) {
            if (!isGrantedPermission(context, it.next())) {
                return false;
            }
        }
        return true;
    }

    static boolean isGrantedPermission(Context context, String permission) {
        return Build.VERSION.SDK_INT < 23 || context.checkSelfPermission(permission) == 0;
    }

    static List<String> getGrantedPermissions(List<String> permissions, int[] grantResults) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == 0) {
                arrayList.add(permissions.get(i));
            }
        }
        return arrayList;
    }

    static List<String> getDeniedPermissions(List<String> permissions, int[] grantResults) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == -1) {
                arrayList.add(permissions.get(i));
            }
        }
        return arrayList;
    }

    static <T> ArrayList<T> asArrayList(T... array) {
        ArrayList<T> arrayList = new ArrayList<>(array != null ? array.length : 0);
        if (array != null && array.length != 0) {
            for (T t : array) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }
}
