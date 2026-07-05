package com.nbvideo;

import android.content.Context;
import android.view.TextureView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NBTextureView extends TextureView {
    private static final String TAG = "NBTextureView";
    private int videoHeight;
    private int videoWidth;

    public NBTextureView(Context context) {
        super(context);
    }

    public void adaptVideoSize(int i, int i2) {
        if (this.videoWidth == i || this.videoHeight == i2) {
            return;
        }
        this.videoWidth = i;
        this.videoHeight = i2;
        requestLayout();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        if (f != getRotation()) {
            super.setRotation(f);
            requestLayout();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0083 A[PHI: r2 r8
  0x0083: PHI (r2v13 int) = (r2v10 int), (r2v10 int), (r2v15 int), (r2v15 int) binds: [B:38:0x0092, B:39:0x0094, B:29:0x007a, B:30:0x007c] A[DONT_GENERATE, DONT_INLINE]
  0x0083: PHI (r8v18 int) = (r8v14 int), (r8v14 int), (r8v12 int), (r8v12 int) binds: [B:38:0x0092, B:39:0x0094, B:29:0x007a, B:30:0x007c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r7, int r8) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nbvideo.NBTextureView.onMeasure(int, int):void");
    }
}
