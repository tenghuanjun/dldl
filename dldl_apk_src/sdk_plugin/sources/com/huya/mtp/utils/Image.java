package com.huya.mtp.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Image {

    public enum ScaleType {
        Fit,
        Overspread,
        Original,
        ClipOverspread
    }

    /* JADX INFO: renamed from: com.huya.mtp.utils.Image$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$mtp$utils$Image$ScaleType;

        static {
            int[] iArr = new int[ScaleType.values().length];
            $SwitchMap$com$huya$mtp$utils$Image$ScaleType = iArr;
            try {
                iArr[ScaleType.Overspread.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$mtp$utils$Image$ScaleType[ScaleType.Original.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huya$mtp$utils$Image$ScaleType[ScaleType.ClipOverspread.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huya$mtp$utils$Image$ScaleType[ScaleType.Fit.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void scaleToW2H2(ScaleType scaleType, int i, int i2, int i3, int i4, float[] fArr) {
        float f = i;
        float f2 = f * 1.0f;
        float f3 = i3;
        float f4 = f2 / f3;
        float f5 = i2;
        float f6 = f5 * 1.0f;
        float f7 = i4;
        float f8 = f6 / f7;
        int i5 = AnonymousClass1.$SwitchMap$com$huya$mtp$utils$Image$ScaleType[scaleType.ordinal()];
        if (i5 == 1) {
            fArr[0] = f3;
            fArr[1] = f7;
            return;
        }
        if (i5 == 2) {
            fArr[0] = f;
            fArr[1] = f5;
            return;
        }
        if (i5 != 3) {
            if (f4 > f8) {
                fArr[0] = f3;
                fArr[1] = (f6 * f3) / f;
                return;
            } else {
                fArr[0] = (f2 * f7) / f5;
                fArr[1] = f7;
                return;
            }
        }
        if (f4 > f8) {
            fArr[0] = (f2 * f7) / f5;
            fArr[1] = f7;
        } else {
            fArr[0] = f3;
            fArr[1] = ((f3 * 1.0f) * f5) / f;
        }
    }
}
