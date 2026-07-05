package com.duowan.auk.ui.utils;

import android.app.Activity;
import android.app.Fragment;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UILog {
    private static boolean msLogNeedDetail;

    public static void lifecycle(String str, Activity activity) {
        if (ArkValue.debuggable()) {
            if (!msLogNeedDetail) {
                L.info(activity, "lifecycle | Activity %s", str);
            } else {
                L.info(activity, "lifecycle | ArkActivity %s | finishing : %s | restricted : %s | changingConfigurations : %s | child : %s | taskRoot : %s", str, Boolean.valueOf(activity.isFinishing()), Boolean.valueOf(activity.isRestricted()), Boolean.valueOf(activity.isChangingConfigurations()), Boolean.valueOf(activity.isChild()), Boolean.valueOf(activity.isTaskRoot()));
            }
        }
    }

    public static void lifecycle(String str, Fragment fragment) {
        if (ArkValue.debuggable()) {
            if (!msLogNeedDetail) {
                L.info(fragment, "lifecycle | Fragment %s", str);
            } else {
                L.info(fragment, "lifecycle | Fragment %s | removing : %s | resumed : %s | added : %s | visible : %s | hidden : %s | detached : %s | inLayout : %s", str, Boolean.valueOf(fragment.isRemoving()), Boolean.valueOf(fragment.isResumed()), Boolean.valueOf(fragment.isAdded()), Boolean.valueOf(fragment.isVisible()), Boolean.valueOf(fragment.isHidden()), Boolean.valueOf(fragment.isDetached()), Boolean.valueOf(fragment.isInLayout()));
            }
        }
    }
}
