package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidImageBitmap.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u000e\u001a\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0011\u0010\u0014\u001a\u00020\u0006*\u00020\u0011H\u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"ActualImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "width", "", "height", "config", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "hasAlpha", "", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "ActualImageBitmap-x__-hDU", "(IIIZLandroidx/compose/ui/graphics/colorspace/ColorSpace;)Landroidx/compose/ui/graphics/ImageBitmap;", "asAndroidBitmap", "Landroid/graphics/Bitmap;", "asImageBitmap", "toBitmapConfig", "Landroid/graphics/Bitmap$Config;", "toBitmapConfig-1JJdX4A", "(I)Landroid/graphics/Bitmap$Config;", "toImageConfig", "(Landroid/graphics/Bitmap$Config;)I", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidImageBitmap_androidKt {
    public static final ImageBitmap asImageBitmap(Bitmap bitmap) {
        return new AndroidImageBitmap(bitmap);
    }

    /* JADX INFO: renamed from: ActualImageBitmap-x__-hDU, reason: not valid java name */
    public static final ImageBitmap m3329ActualImageBitmapx__hDU(int i, int i2, int i3, boolean z, ColorSpace colorSpace) {
        Bitmap bitmapCreateBitmap;
        Bitmap.Config configM3330toBitmapConfig1JJdX4A = m3330toBitmapConfig1JJdX4A(i3);
        if (Build.VERSION.SDK_INT < 26) {
            bitmapCreateBitmap = Bitmap.createBitmap((DisplayMetrics) null, i, i2, configM3330toBitmapConfig1JJdX4A);
            bitmapCreateBitmap.setHasAlpha(z);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap((DisplayMetrics) null, i, i2, m3330toBitmapConfig1JJdX4A(i3), z, AndroidColorSpace_androidKt.toAndroidColorSpace(colorSpace));
        }
        return new AndroidImageBitmap(bitmapCreateBitmap);
    }

    public static final Bitmap asAndroidBitmap(ImageBitmap imageBitmap) {
        if (imageBitmap instanceof AndroidImageBitmap) {
            return ((AndroidImageBitmap) imageBitmap).getBitmap();
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    /* JADX INFO: renamed from: toBitmapConfig-1JJdX4A, reason: not valid java name */
    public static final Bitmap.Config m3330toBitmapConfig1JJdX4A(int i) {
        if (ImageBitmapConfig.m3718equalsimpl0(i, ImageBitmapConfig.INSTANCE.m3723getArgb8888_sVssgQ())) {
            return Bitmap.Config.ARGB_8888;
        }
        if (ImageBitmapConfig.m3718equalsimpl0(i, ImageBitmapConfig.INSTANCE.m3722getAlpha8_sVssgQ())) {
            return Bitmap.Config.ALPHA_8;
        }
        if (ImageBitmapConfig.m3718equalsimpl0(i, ImageBitmapConfig.INSTANCE.m3726getRgb565_sVssgQ())) {
            return Bitmap.Config.RGB_565;
        }
        if (Build.VERSION.SDK_INT >= 26 && ImageBitmapConfig.m3718equalsimpl0(i, ImageBitmapConfig.INSTANCE.m3724getF16_sVssgQ())) {
            return Bitmap.Config.RGBA_F16;
        }
        if (Build.VERSION.SDK_INT >= 26 && ImageBitmapConfig.m3718equalsimpl0(i, ImageBitmapConfig.INSTANCE.m3725getGpu_sVssgQ())) {
            return Bitmap.Config.HARDWARE;
        }
        return Bitmap.Config.ARGB_8888;
    }

    public static final int toImageConfig(Bitmap.Config config) {
        if (config == Bitmap.Config.ALPHA_8) {
            return ImageBitmapConfig.INSTANCE.m3722getAlpha8_sVssgQ();
        }
        if (config == Bitmap.Config.RGB_565) {
            return ImageBitmapConfig.INSTANCE.m3726getRgb565_sVssgQ();
        }
        if (config == Bitmap.Config.ARGB_4444) {
            return ImageBitmapConfig.INSTANCE.m3723getArgb8888_sVssgQ();
        }
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.RGBA_F16) {
            return ImageBitmapConfig.INSTANCE.m3724getF16_sVssgQ();
        }
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            return ImageBitmapConfig.INSTANCE.m3725getGpu_sVssgQ();
        }
        return ImageBitmapConfig.INSTANCE.m3723getArgb8888_sVssgQ();
    }
}
