package com.sqinject.core;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqInject {
    private static final String TAG = SqInject.class.getSimpleName();

    public static void bindView(Activity activity) {
        bindView(activity, activity.getWindow().getDecorView());
    }

    public static void bindView(Dialog dialog) {
        bindView(dialog, dialog.getWindow().getDecorView());
    }

    public static void bindView(Fragment fragment) {
        bindView(fragment, fragment.getView());
    }

    public static void bindView(Object obj, View view) {
        try {
            ((ViewBinder) findBinderClass(obj.getClass(), ViewBinder.class.getSimpleName()).newInstance()).bindView(obj, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindView(Object obj, Class cls, View view) {
        try {
            ((ViewBinder) Class.forName(cls.getName() + "$" + ViewBinder.class.getSimpleName()).newInstance()).bindView(obj, view);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "不存在" + cls.getSimpleName() + "$" + ViewBinder.class.getSimpleName() + "类");
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    public static void bindIds(Object obj, Context context) {
        try {
            ((IdsBinder) findBinderClass(obj.getClass(), IdsBinder.class.getSimpleName()).newInstance()).bind(obj, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindIds(Activity activity) {
        bindIds(activity, activity);
    }

    public static void bindIds(Dialog dialog) {
        bindIds(dialog, dialog.getContext());
    }

    public static void bindIds(Fragment fragment) {
        bindIds(fragment, fragment.getActivity());
    }

    public static void bindIds(Object obj, Class cls, Context context) {
        try {
            ((IdsBinder) Class.forName(cls.getName() + "$" + IdsBinder.class.getSimpleName()).newInstance()).bind(obj, context);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "不存在" + cls.getSimpleName() + "$" + IdsBinder.class.getSimpleName() + "类");
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    public static void bind(Object obj, View view) {
        bindIds(obj, view.getContext());
        bindView(obj, view);
    }

    public static void bind(Activity activity) {
        bind(activity, activity.getWindow().getDecorView());
    }

    public static void bind(Dialog dialog) {
        bind(dialog, dialog.getWindow().getDecorView());
    }

    public static void bind(Fragment fragment) {
        bind(fragment, fragment.getView());
    }

    private static Class findBinderClass(Class cls, String str) {
        String name = cls.getName();
        if (name.startsWith("android.") || name.startsWith("java.") || name.startsWith("androidx.")) {
            Log.d(TAG, "找不到绑定类，搜索已到达framework层，终止查找类");
            return null;
        }
        String str2 = cls.getName() + "$" + str;
        try {
            return Class.forName(str2);
        } catch (ClassNotFoundException e) {
            Log.d(TAG, str2 + "不存在，开始搜索是否存在父类绑定");
            Class clsFindBinderClass = findBinderClass(cls.getSuperclass(), str);
            e.printStackTrace();
            return clsFindBinderClass;
        }
    }
}
