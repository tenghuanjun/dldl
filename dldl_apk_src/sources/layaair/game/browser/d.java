package layaair.game.browser;

import android.opengl.GLSurfaceView;
import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class d implements GLSurfaceView.Renderer {
    private GL10 a;
    private int b;
    private int c;
    private boolean d;

    private d() {
        this.a = null;
        this.b = 0;
        this.c = 0;
    }

    /* synthetic */ d(byte b) {
        this();
    }

    public final void a() {
        this.d = true;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        ConchJNI.onDrawFrame();
        if (this.d) {
            LayaConch5.GetInstance().dispatchGL(gl10, this.b, this.c);
            this.d = false;
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (LayaConch5.ms_layaConche == null) {
            Log.e("", ">>>>>>>>>>>>>surface not ready");
            return;
        }
        if (LayaConch5.ms_layaConche.getHorizontalScreen() && i < i2) {
            this.b = i2;
            this.c = i;
            this.a = gl10;
            Log.e("", ">>>>>>>>>>>>surfaceChangedhw w=" + this.b + ",h=" + this.c + " gl=" + gl10);
            ConchJNI.OnGLReady(this.b, this.c);
            return;
        }
        if (i == this.b && i2 == this.c && gl10 == this.a) {
            return;
        }
        this.b = i;
        this.c = i2;
        this.a = gl10;
        Log.e("", ">>>>>>>>>>>>surfaceChanged w=" + i + ",h=" + i2 + " gl=" + gl10);
        ConchJNI.OnGLReady(i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
    }
}
