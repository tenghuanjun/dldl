package java2jni_do_not_delete_this;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class java2jni_do_not_delete_this_library_zkfv_1ts_1tj {
    private static boolean library_loaded;

    public static void main(String[] strArr) {
    }

    public static void loadLibrary() {
        if (library_loaded) {
            return;
        }
        try {
            System.out.println("java2jni java.library.path is: " + System.getProperty("java.library.path"));
            System.loadLibrary("zkfv_ts_tj");
            library_loaded = true;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }
}
