package com.bytedance.pangle.fragment;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class c implements Application.ActivityLifecycleCallbacks {
    Fragment a;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public c(Fragment fragment) {
        this.a = fragment;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostSaveInstanceState(Activity activity, Bundle bundle) {
        Object[] objArr;
        if (this.a.getActivity() != activity) {
            return;
        }
        try {
            Parcelable parcelable = bundle.getParcelable("android:fragments");
            if (parcelable == null || (objArr = (Object[]) FieldUtils.readField(parcelable, "mActive")) == null) {
                return;
            }
            Object obj = null;
            for (Object obj2 : objArr) {
                Object field = FieldUtils.readField(obj2, "mTag");
                if (!"androidx.lifecycle.LifecycleDispatcher.report_fragment_tag_plugin".equals(field) && (!(field instanceof String) || (!((String) field).contains("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag_pangle") && !((String) field).contains("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag_pangle")))) {
                }
                obj = obj2;
                break;
            }
            if (obj != null) {
                Object[] objArr2 = (Object[]) Array.newInstance(Class.forName("android.app.FragmentState"), objArr.length - 1);
                int[] iArr = new int[objArr.length - 1];
                int i = 0;
                for (int i2 = 0; i2 < objArr.length; i2++) {
                    if (objArr[i2] != obj) {
                        objArr2[i] = objArr[i2];
                        iArr[i] = i2;
                        i++;
                    }
                }
                FieldUtils.writeField(parcelable, "mActive", objArr2);
                FieldUtils.writeField(parcelable, "mAdded", iArr);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Object[] objArr;
        if (this.a.getActivity() != activity) {
            return;
        }
        try {
            Parcelable parcelable = bundle.getParcelable("android:fragments");
            if (parcelable == null || (objArr = (Object[]) FieldUtils.readField(parcelable, "mActive")) == null) {
                return;
            }
            Object obj = null;
            for (Object obj2 : objArr) {
                Object field = FieldUtils.readField(obj2, "mTag");
                if (!"androidx.lifecycle.LifecycleDispatcher.report_fragment_tag_plugin".equals(field) && (!(field instanceof String) || (!((String) field).contains("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag_pangle") && !((String) field).contains("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag_pangle")))) {
                }
                obj = obj2;
                break;
            }
            if (obj != null) {
                Object[] objArr2 = (Object[]) Array.newInstance(Class.forName("android.app.FragmentState"), objArr.length - 1);
                int[] iArr = new int[objArr.length - 1];
                int i = 0;
                for (int i2 = 0; i2 < objArr.length; i2++) {
                    if (objArr[i2] != obj) {
                        objArr2[i] = objArr[i2];
                        iArr[i] = i2;
                        i++;
                    }
                }
                FieldUtils.writeField(parcelable, "mActive", objArr2);
                FieldUtils.writeField(parcelable, "mAdded", iArr);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
