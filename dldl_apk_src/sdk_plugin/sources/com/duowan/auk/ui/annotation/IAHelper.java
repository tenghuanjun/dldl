package com.duowan.auk.ui.annotation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.View;
import com.duowan.auk.ui.ArkView;
import com.duowan.auk.util.L;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class IAHelper {
    private static final char ID_SEPARATOR = '_';

    public static void init(Activity activity, String str) {
        for (Class<?> superclass = activity.getClass(); !superclass.getName().equals(str); superclass = superclass.getSuperclass()) {
            initFields(activity, superclass);
        }
    }

    public static void init(Fragment fragment, View view, String str) {
        for (Class<?> superclass = fragment.getClass(); !superclass.getName().equals(str); superclass = superclass.getSuperclass()) {
            initFields(fragment, view, superclass);
        }
    }

    private static void initFields(Activity activity, Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields == null) {
            return;
        }
        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            if (ArkView.class.isAssignableFrom(type)) {
                initView(activity, activity, field);
            } else if (Fragment.class.isAssignableFrom(type)) {
                initFragment(activity.getFragmentManager(), activity, field);
            }
        }
    }

    private static void initFields(Fragment fragment, View view, Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields == null) {
            return;
        }
        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            if (ArkView.class.isAssignableFrom(type)) {
                initView(view, fragment, field);
            } else if (Fragment.class.isAssignableFrom(type)) {
                initFragment(fragment.getFragmentManager(), fragment, field);
            }
        }
    }

    private static void initView(Object obj, Object obj2, Field field) {
        int iValue;
        ArkView arkView;
        IAView iAView = (IAView) field.getAnnotation(IAView.class);
        if (iAView == null || (iValue = iAView.value()) == 0) {
            arkView = new ArkView(obj, toIdName(field.getName()));
        } else {
            arkView = new ArkView(obj, iValue);
        }
        field.setAccessible(true);
        try {
            field.set(obj2, arkView);
        } catch (Exception e) {
            L.error(obj2, "initView failed. %s | %s", field, e);
        }
    }

    private static void initFragment(FragmentManager fragmentManager, Object obj, Field field) {
        IAFragment iAFragment = (IAFragment) field.getAnnotation(IAFragment.class);
        if (iAFragment == null) {
            return;
        }
        Fragment fragmentFindFragmentById = fragmentManager.findFragmentById(iAFragment.value());
        field.setAccessible(true);
        try {
            field.set(obj, fragmentFindFragmentById);
        } catch (Exception e) {
            L.error(obj, "initFragment failed. %s | %s", field, e);
        }
    }

    private static String toIdName(String str) {
        String strValueOf = String.valueOf(Character.toLowerCase(str.charAt(1)));
        int length = str.length();
        for (int i = 2; i < length; i++) {
            char cCharAt = str.charAt(i);
            strValueOf = Character.isUpperCase(cCharAt) ? strValueOf + ID_SEPARATOR + Character.toLowerCase(cCharAt) : strValueOf + cCharAt;
        }
        return strValueOf;
    }
}
