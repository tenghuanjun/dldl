package com.plugin.core.loader;

import android.os.Build;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ApkClassLoader extends DexClassLoader {
    private Method mFindLibraryMethod;
    private ClassLoader mGrandParent;
    private final String[] mInterfacePackageNames;

    public ApkClassLoader(String str, String str2, String str3, ClassLoader classLoader, String[] strArr) {
        super(str, str2, str3, classLoader);
        this.mGrandParent = classLoader;
        this.mInterfacePackageNames = strArr;
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        int iLastIndexOf = str.lastIndexOf(46);
        boolean z2 = false;
        String strSubstring = iLastIndexOf != -1 ? str.substring(0, iLastIndexOf) : "";
        String[] strArr = this.mInterfacePackageNames;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (strSubstring.equals(strArr[i])) {
                z2 = true;
                break;
            }
            i++;
        }
        if (z2) {
            return super.loadClass(str, z);
        }
        Class<?> clsFindLoadedClass = findLoadedClass(str);
        if (clsFindLoadedClass != null) {
            return clsFindLoadedClass;
        }
        ClassNotFoundException e = null;
        try {
            clsFindLoadedClass = findClass(str);
        } catch (ClassNotFoundException e2) {
            e = e2;
        }
        if (clsFindLoadedClass != null) {
            return clsFindLoadedClass;
        }
        try {
            return this.mGrandParent.loadClass(str);
        } catch (ClassNotFoundException e3) {
            if (Build.VERSION.SDK_INT >= 19) {
                e3.addSuppressed(e);
            }
            throw e3;
        }
    }

    public <T> T getInterface(Class<T> cls, String str) throws Exception {
        try {
            return cls.cast(loadClass(str).newInstance());
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new Exception(e);
        }
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public String findLibrary(String str) {
        String strFindLibrary = super.findLibrary(str);
        if (strFindLibrary != null) {
            return strFindLibrary;
        }
        ClassLoader classLoader = this.mGrandParent;
        if (classLoader instanceof BaseDexClassLoader) {
            return ((BaseDexClassLoader) classLoader).findLibrary(str);
        }
        try {
            if (this.mFindLibraryMethod == null) {
                Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
                this.mFindLibraryMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            return (String) this.mFindLibraryMethod.invoke(this.mGrandParent, str);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
