package com.bytedance.pangle.transform;

import android.support.v4.app.FragmentActivity;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class HostPartUtils {
    private Class fragmentActivityClazz;

    static class a {
        private static final HostPartUtils a = new HostPartUtils();
    }

    public HostPartUtils() {
        try {
            this.fragmentActivityClazz = FragmentActivity.class;
        } catch (Throwable unused) {
        }
    }

    public static final Class getFragmentActivityClass() {
        return a.a.fragmentActivityClazz;
    }

    public static FragmentActivity getFragmentActivity(Object obj, String str) {
        return (FragmentActivity) ZeusTransformUtils._getActivity(obj, str);
    }
}
