package com.sqinject.core.util;

import android.view.View;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ViewUtils {
    public static View findRequiredView(View view, String str, String str2) {
        View viewFindViewById = view.findViewById(view.getContext().getResources().getIdentifier(str, SqTrackCommonKey.id, view.getContext().getPackageName()));
        if (viewFindViewById != null) {
            return viewFindViewById;
        }
        throw new IllegalStateException("Required view '" + str + " for " + str2 + " was not found.");
    }

    public static <T> T findRequiredViewAsType(View view, String str, String str2, Class<T> cls) {
        return (T) castView(findRequiredView(view, str, str2), str2, cls);
    }

    public static <T> T castView(View view, String str, Class<T> cls) {
        try {
            return cls.cast(view);
        } catch (ClassCastException e) {
            throw new IllegalStateException("View for " + str + " was of the wrong type. See cause for more info.", e);
        }
    }

    public static <T> T castParam(Object obj, String str, int i, String str2, int i2, Class<T> cls) {
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Parameter #" + (i + 1) + " of method '" + str + "' was of the wrong type for parameter #" + (i2 + 1) + " of method '" + str2 + "'. See cause for more info.", e);
        }
    }

    private ViewUtils() {
        throw new AssertionError("No instances.");
    }
}
