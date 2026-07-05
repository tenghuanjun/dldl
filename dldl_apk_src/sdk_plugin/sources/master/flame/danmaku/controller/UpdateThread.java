package master.flame.danmaku.controller;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class UpdateThread extends Thread {
    volatile boolean mIsQuited;

    public UpdateThread(String str) {
        super(str);
    }

    public void quit() {
        this.mIsQuited = true;
    }

    public boolean isQuited() {
        return this.mIsQuited;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.mIsQuited) {
        }
    }
}
