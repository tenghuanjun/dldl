package com.volcengine.j;

import android.os.Build;
import com.volcengine.androidcloud.common.log.AcLog;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class k {

    private static final class a {
        static void a(ClassLoader classLoader, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException {
            if (classLoader == null || list == null || list.size() == 0) {
                return;
            }
            k.b(k.a((Object) classLoader, "pathList").get(classLoader), "nativeLibraryDirectories", list.toArray());
        }

        static void a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException {
            IOException[] iOExceptionArr;
            Object obj = k.a((Object) classLoader, "pathList").get(classLoader);
            ArrayList<IOException> arrayList = new ArrayList();
            k.b(obj, "dexElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                for (IOException iOException : arrayList) {
                }
                Field fieldA = k.a(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldA.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldA.set(obj, iOExceptionArr);
                IOException iOException2 = new IOException("I/O exception during makeDexElement");
                iOException2.initCause((Throwable) arrayList.get(0));
                throw iOException2;
            }
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
            return (Object[]) k.b(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, arrayList, file, arrayList2);
        }
    }

    private static final class b {
        static void a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException {
            IOException[] iOExceptionArr;
            Object obj = k.a((Object) classLoader, "pathList").get(classLoader);
            ArrayList<IOException> arrayList = new ArrayList();
            k.b(obj, "dexElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                for (IOException iOException : arrayList) {
                }
                Field fieldA = k.a(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldA.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldA.set(obj, iOExceptionArr);
                IOException iOException2 = new IOException("I/O exception during makeDexElement");
                iOException2.initCause((Throwable) arrayList.get(0));
                throw iOException2;
            }
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
            return (Object[]) k.b(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList, file, arrayList2);
        }

        static void b(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException {
            IOException[] iOExceptionArr;
            Object obj = k.a((Object) classLoader, "pathList").get(classLoader);
            ArrayList<IOException> arrayList = new ArrayList();
            k.b(obj, "nativeLibraryPathElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                for (IOException iOException : arrayList) {
                }
                Field fieldA = k.a(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldA.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldA.set(obj, iOExceptionArr);
                IOException iOException2 = new IOException("I/O exception during makeDexElement");
                iOException2.initCause((Throwable) arrayList.get(0));
                throw iOException2;
            }
        }
    }

    private static final class c {
        static void a(ClassLoader classLoader, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException {
            Object obj = k.a((Object) classLoader, "pathList").get(classLoader);
            k.b(obj, "nativeLibraryPathElements", a(obj, new ArrayList(list)));
        }

        static void a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException {
            IOException[] iOExceptionArr;
            Object obj = k.a((Object) classLoader, "pathList").get(classLoader);
            ArrayList<IOException> arrayList = new ArrayList();
            k.b(obj, "dexElements", a(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                for (IOException iOException : arrayList) {
                }
                Field fieldA = k.a(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldA.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldA.set(obj, iOExceptionArr);
                IOException iOException2 = new IOException("I/O exception during makeDexElement");
                iOException2.initCause((Throwable) arrayList.get(0));
                throw iOException2;
            }
        }

        private static Object[] a(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
            return (Object[]) k.b(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList, file, arrayList2);
        }

        private static Object[] a(Object obj, List<File> list) {
            return (Object[]) k.b(obj, "makePathElements", List.class).invoke(obj, list);
        }
    }

    public static Field a(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    public static void a(ClassLoader classLoader, File file, List<File> list, List<File> list2) throws IllegalAccessException, NoSuchFieldException, IOException {
        if (!list.isEmpty()) {
            a(classLoader, list, file);
        }
        if (list2.isEmpty()) {
            return;
        }
        b(classLoader, list2, file);
    }

    public static void a(ClassLoader classLoader, String str) throws ClassNotFoundException {
        Class.forName(str, true, classLoader);
    }

    static void a(ClassLoader classLoader, List<File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException {
        for (File file2 : list) {
            if (file2 != null) {
                AcLog.d("LoaderUtils#", "set dexFile readOnly result" + file2.setReadOnly());
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            c.a(classLoader, list, file);
        } else if (i >= 23) {
            b.a(classLoader, list, file);
        } else {
            a.a(classLoader, list, file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method b(Object obj, String str, Class... clsArr) throws NoSuchMethodException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }

    static void b(ClassLoader classLoader, List<File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            c.a(classLoader, (List<? extends File>) list);
        } else if (i >= 23) {
            b.b(classLoader, list, file);
        } else {
            a.a(classLoader, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Object obj, String str, Object[] objArr) throws IllegalAccessException, NoSuchFieldException {
        Field fieldA = a(obj, str);
        Object[] objArr2 = (Object[]) fieldA.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        fieldA.set(obj, objArr3);
    }
}
