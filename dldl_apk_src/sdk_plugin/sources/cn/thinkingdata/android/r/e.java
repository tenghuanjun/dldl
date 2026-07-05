package cn.thinkingdata.android.r;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public enum e {
    PROVIDER,
    PLUGIN,
    UNKNOWN;

    public static e a(int i) {
        return i != 0 ? i != 1 ? UNKNOWN : PLUGIN : PROVIDER;
    }
}
