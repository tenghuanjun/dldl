package layaair.game.browser;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.Log;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a extends GLSurfaceView {
    private static String f = "MainCanvas";
    public ap a;
    public int b;
    public int c;
    public int d;
    public int e;
    private boolean g;
    private d h;

    public a(Context context) {
        super(context);
        byte b = 0;
        this.g = false;
        this.a = new ap();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        int i = Build.VERSION.SDK_INT;
        setPreserveEGLContextOnPause(true);
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) getContext().getSystemService(TTDownloadField.TT_ACTIVITY)).getDeviceConfigurationInfo();
        if (((deviceConfigurationInfo.reqGlEsVersion != 0 ? deviceConfigurationInfo.reqGlEsVersion : 65536) >> 16) == 3) {
            Log.i(f, "Android OpenGL ES Major Version 3");
            setEGLContextClientVersion(3);
        } else {
            Log.i(f, "Android OpenGL ES Major Version 2");
            setEGLContextClientVersion(2);
        }
        setEGLConfigChooser(new c(5, 6, 5, 0, 24, 8));
        this.h = new d(b);
        setRenderer(this.h);
    }

    public final void a() {
        d dVar = this.h;
        if (dVar != null) {
            dVar.a();
        }
    }

    public final void a(int i, int i2) {
        ap apVar;
        float f2;
        int iMax;
        Log.i("fix", "setFixedSize " + i + com.igexin.push.core.b.aj + i2 + ",scr:" + this.b + com.igexin.push.core.b.aj + this.c);
        if (i <= 0 || i2 <= 0) {
            return;
        }
        getHolder().setFixedSize(i, i2);
        if (LayaConch5.ms_layaConche.getHorizontalScreen()) {
            if (i < i2) {
                Log.e("LayaBox", "setFixedSize error! screen orientation = landscape  w should bigger than h");
            }
            this.a.c = i / Math.max(this.b, this.c);
            apVar = this.a;
            f2 = i2;
            iMax = Math.min(this.b, this.c);
        } else {
            if (i > i2) {
                Log.e("LayaBox", "setFixedSize error! screen orientation = portrait  w should less than h");
            }
            this.a.c = i / Math.min(this.b, this.c);
            apVar = this.a;
            f2 = i2;
            iMax = Math.max(this.b, this.c);
        }
        apVar.d = f2 / iMax;
        LayaConch5.ms_layaConche.m_pEditBox$37880073.c = 1.0f / this.a.c;
        LayaConch5.ms_layaConche.m_pEditBox$37880073.d = 1.0f / this.a.d;
        this.d = i;
        this.e = i2;
    }

    @Override // android.opengl.GLSurfaceView
    public final void onPause() {
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public final void onResume() {
        super.onResume();
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011b A[SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: layaair.game.browser.a.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
