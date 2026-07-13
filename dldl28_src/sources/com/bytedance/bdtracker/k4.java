package com.bytedance.bdtracker;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.IPageMeta;
import com.bytedance.applog.annotation.PageMeta;
import com.bytedance.applog.log.LoggerImpl;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class k4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f285a;
    public static final List<String> b;
    public static final List<Class<?>> c;
    public static final List<Class<?>> d;
    public static final List<String> e;

    static {
        List<String> listSingletonList = Collections.singletonList("android.app.Activity");
        f285a = listSingletonList;
        b = Arrays.asList("android.app.Fragment", "androidx.fragment.app.Fragment", "android.support.v4.app.Fragment");
        c = new ArrayList();
        d = new ArrayList();
        e = Collections.singletonList("PageUtils");
        Iterator<String> it = listSingletonList.iterator();
        while (it.hasNext()) {
            Class<?> clsB = n0.b(it.next());
            if (clsB != null) {
                c.add(clsB);
            }
        }
        Iterator<String> it2 = b.iterator();
        while (it2.hasNext()) {
            Class<?> clsB2 = n0.b(it2.next());
            if (clsB2 != null) {
                d.add(clsB2);
            }
        }
    }

    public static View a(Object obj) {
        try {
            Method method = obj.getClass().getMethod("getView", null);
            if (method != null) {
                Object objInvoke = method.invoke(obj, null);
                if (objInvoke instanceof View) {
                    return (View) objInvoke;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static String b(Object obj) {
        PageMeta pageMeta;
        if (obj == null) {
            return "";
        }
        if (obj instanceof IPageMeta) {
            try {
                return ((IPageMeta) obj).path();
            } catch (Throwable th) {
                LoggerImpl.global().error(e, "Cannot get path from IPageMeta", th, new Object[0]);
            }
        }
        return (!obj.getClass().isAnnotationPresent(PageMeta.class) || (pageMeta = (PageMeta) obj.getClass().getAnnotation(PageMeta.class)) == null || TextUtils.isEmpty(pageMeta.path())) ? obj.getClass().getCanonicalName() : pageMeta.path();
    }

    public static String c(Object obj) {
        Object objInvoke;
        CharSequence charSequence;
        PageMeta pageMeta;
        if (obj == null) {
            return "";
        }
        if (obj instanceof IPageMeta) {
            try {
                return ((IPageMeta) obj).title();
            } catch (Throwable th) {
                LoggerImpl.global().error(e, "Cannot get title from IPageMeta", th, new Object[0]);
            }
        }
        if (obj.getClass().isAnnotationPresent(PageMeta.class) && (pageMeta = (PageMeta) obj.getClass().getAnnotation(PageMeta.class)) != null && !TextUtils.isEmpty(pageMeta.title())) {
            return pageMeta.title();
        }
        if (obj instanceof Activity) {
            Activity activity = (Activity) obj;
            if (!TextUtils.isEmpty(activity.getTitle())) {
                return activity.getTitle().toString();
            }
            ActionBar actionBar = activity.getActionBar();
            String string = null;
            if (actionBar == null) {
                try {
                    Class<?> clsA = n0.a("android.support.v7.app.AppCompatActivity", "androidx.appcompat.app.AppCompatActivity");
                    if (clsA != null && clsA.isInstance(activity) && (objInvoke = activity.getClass().getMethod("getSupportActionBar", null).invoke(activity, null)) != null && (charSequence = (CharSequence) objInvoke.getClass().getMethod("getTitle", null).invoke(objInvoke, null)) != null) {
                        string = charSequence.toString();
                    }
                } catch (Exception unused) {
                }
            } else if (!TextUtils.isEmpty(actionBar.getTitle())) {
                string = actionBar.getTitle().toString();
            }
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            try {
                PackageManager packageManager = ((Activity) obj).getPackageManager();
                if (packageManager != null) {
                    CharSequence charSequenceLoadLabel = packageManager.getActivityInfo(((Activity) obj).getComponentName(), 0).loadLabel(packageManager);
                    if (!TextUtils.isEmpty(charSequenceLoadLabel)) {
                        return charSequenceLoadLabel.toString();
                    }
                }
            } catch (Exception e2) {
                LoggerImpl.global().error(e, "Cannot get title from activity label", e2, new Object[0]);
            }
        }
        return obj.getClass().getName();
    }

    public static JSONObject d(Object obj) {
        if (!(obj instanceof IPageMeta)) {
            return null;
        }
        try {
            return ((IPageMeta) obj).pageProperties();
        } catch (Throwable th) {
            LoggerImpl.global().error(e, "Cannot get track properties from activity", th, new Object[0]);
            return null;
        }
    }
}
