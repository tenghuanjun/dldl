package com.volcengine.common.plugin;

import dalvik.system.DexClassLoader;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class a extends DexClassLoader {
    public a(String str, String str2, String str3, ClassLoader classLoader) {
        super(str, str2, str3, classLoader);
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) {
        try {
            Class<?> clsFindLoadedClass = findLoadedClass(str);
            if (clsFindLoadedClass != null) {
                return clsFindLoadedClass;
            }
            Class<?> clsFindClass = findClass(str);
            if (clsFindClass != null) {
                return clsFindClass;
            }
        } catch (Exception unused) {
        }
        return super.loadClass(str, z);
    }
}
