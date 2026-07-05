package layaair.game.browser;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class c implements GLSurfaceView.EGLConfigChooser {
    private static int a = 4;
    private static int[] b = {12324, 4, 12323, 4, 12322, 4, 12352, a, 12344};
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int[] i = new int[1];

    public c(int i, int i2, int i3, int i4, int i5, int i6) {
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
    }

    private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.i)) {
            return this.i[0];
        }
        return 0;
    }

    private EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
        for (EGLConfig eGLConfig : eGLConfigArr) {
            int iA = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
            int iA2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
            if (iA >= this.g && iA2 >= this.h) {
                int iA3 = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                int iA4 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                int iA5 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                int iA6 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                if (iA3 == this.c && iA4 == this.d && iA5 == this.e && iA6 == this.f) {
                    return eGLConfig;
                }
            }
        }
        return null;
    }

    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public final EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eGLDisplay, b, null, 0, iArr);
        int i = iArr[0];
        if (i <= 0) {
            throw new IllegalArgumentException("No configs match configSpec");
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[i];
        egl10.eglChooseConfig(eGLDisplay, b, eGLConfigArr, i, iArr);
        return a(egl10, eGLDisplay, eGLConfigArr);
    }
}
