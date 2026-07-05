package com.huya.force.cameracapture;

import com.huya.force.cameracapture.impl.SurfaceTextureImpl;
import com.huya.force.export.surface.ISurface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SurfaceFactory {

    /* JADX INFO: renamed from: com.huya.force.cameracapture.SurfaceFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$force$export$surface$ISurface$SurfaceType;

        static {
            int[] iArr = new int[ISurface.SurfaceType.values().length];
            $SwitchMap$com$huya$force$export$surface$ISurface$SurfaceType = iArr;
            try {
                iArr[ISurface.SurfaceType.SURFACE_TEXTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static ISurface createSurface(ISurface.SurfaceType surfaceType) {
        if (AnonymousClass1.$SwitchMap$com$huya$force$export$surface$ISurface$SurfaceType[surfaceType.ordinal()] == 1) {
            return new SurfaceTextureImpl();
        }
        return new SurfaceTextureImpl();
    }
}
