package cn.thinkingdata.android;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class h {
    public static boolean a(Object obj, String str) {
        for (Class<?> superclass = obj.getClass(); superclass.getCanonicalName() != null; superclass = superclass.getSuperclass()) {
            if (superclass.getCanonicalName().equals(str)) {
                return true;
            }
            if (superclass == Object.class) {
                return false;
            }
        }
        return false;
    }
}
