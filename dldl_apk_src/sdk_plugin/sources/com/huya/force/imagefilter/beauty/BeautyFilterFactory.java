package com.huya.force.imagefilter.beauty;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.huya.force.export.imagefilter.BaseImageFilter;
import com.huya.force.gpuimage.GPUImageFilter;
import com.huya.force.log.ForceLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BeautyFilterFactory {
    private static final String TAG = "BeautyFilterFactory";

    private static Bitmap getLookupBitMap(Context context, BaseImageFilter.Type type) {
        if (context == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        switch (type) {
        }
        return null;
    }

    public static GPUImageFilter createBeautyFilter(Context context, BaseImageFilter.Type type) {
        if (context == null) {
            ForceLog.error(TAG, "createBeautyFilter context == null");
            return null;
        }
        switch (type) {
            case NATURAL:
                GPUImageBeautyBaseFilter gPUImageBeautyBaseFilter = new GPUImageBeautyBaseFilter();
                gPUImageBeautyBaseFilter.setBitmap(getLookupBitMap(context, type));
                break;
            case SUMMER:
                GPUImageBeautySummerFilter gPUImageBeautySummerFilter = new GPUImageBeautySummerFilter();
                gPUImageBeautySummerFilter.setBitmap(getLookupBitMap(context, type));
                break;
            case SWEETNESS:
                GPUImageBeautySweetFilter gPUImageBeautySweetFilter = new GPUImageBeautySweetFilter();
                gPUImageBeautySweetFilter.setBitmap(getLookupBitMap(context, type));
                break;
            case FRESH:
                GPUImageBeautyFace2 gPUImageBeautyFace2 = new GPUImageBeautyFace2();
                gPUImageBeautyFace2.setBitmap(getLookupBitMap(context, type));
                break;
            case DAWN:
                GPUImageBeautyFace6 gPUImageBeautyFace6 = new GPUImageBeautyFace6();
                gPUImageBeautyFace6.setBitmap(getLookupBitMap(context, type));
                break;
            case GLAMOROUS:
                GPUImageBeautyFace11 gPUImageBeautyFace11 = new GPUImageBeautyFace11();
                gPUImageBeautyFace11.setBitmap(getLookupBitMap(context, type));
                break;
            case WARMSUN:
                GPUImageBeautyFace16 gPUImageBeautyFace16 = new GPUImageBeautyFace16();
                gPUImageBeautyFace16.setBitmap(getLookupBitMap(context, type));
                break;
            case HOLIDAY:
                GPUImageBeautyHoliday gPUImageBeautyHoliday = new GPUImageBeautyHoliday();
                gPUImageBeautyHoliday.setBitmap(getLookupBitMap(context, type));
                break;
            case FLOWER_SEA:
                GPUImageBeautyFlower gPUImageBeautyFlower = new GPUImageBeautyFlower();
                gPUImageBeautyFlower.setBitmap(getLookupBitMap(context, type));
                break;
            case SOFT_LIGHT:
                GPUImageBeautySoftlight gPUImageBeautySoftlight = new GPUImageBeautySoftlight();
                gPUImageBeautySoftlight.setBitmap(getLookupBitMap(context, type));
                break;
            case WARM_AIR:
                GPUImageBeautyWarm gPUImageBeautyWarm = new GPUImageBeautyWarm();
                gPUImageBeautyWarm.setBitmap(getLookupBitMap(context, type));
                break;
        }
        return null;
    }

    public static void setNewBeautyWhite(GPUImageFilter gPUImageFilter, float f) {
        if (gPUImageFilter == null) {
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyBaseFilter) {
            ((GPUImageBeautyBaseFilter) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautySummerFilter) {
            ((GPUImageBeautySummerFilter) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautySweetFilter) {
            ((GPUImageBeautySweetFilter) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace2) {
            ((GPUImageBeautyFace2) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace6) {
            ((GPUImageBeautyFace6) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace11) {
            ((GPUImageBeautyFace11) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace16) {
            ((GPUImageBeautyFace16) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyHoliday) {
            ((GPUImageBeautyHoliday) gPUImageFilter).setIntensity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFlower) {
            ((GPUImageBeautyFlower) gPUImageFilter).setIntensity(f);
        } else if (gPUImageFilter instanceof GPUImageBeautySoftlight) {
            ((GPUImageBeautySoftlight) gPUImageFilter).setIntensity(f);
        } else if (gPUImageFilter instanceof GPUImageBeautyWarm) {
            ((GPUImageBeautyWarm) gPUImageFilter).setIntensity(f);
        }
    }

    public static void setNewBeautyDermabrasion(GPUImageFilter gPUImageFilter, float f) {
        if (gPUImageFilter == null) {
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyBaseFilter) {
            ((GPUImageBeautyBaseFilter) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautySummerFilter) {
            ((GPUImageBeautySummerFilter) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautySweetFilter) {
            ((GPUImageBeautySweetFilter) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace2) {
            ((GPUImageBeautyFace2) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace6) {
            ((GPUImageBeautyFace6) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace11) {
            ((GPUImageBeautyFace11) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFace16) {
            ((GPUImageBeautyFace16) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyHoliday) {
            ((GPUImageBeautyHoliday) gPUImageFilter).setOpacity(f);
            return;
        }
        if (gPUImageFilter instanceof GPUImageBeautyFlower) {
            ((GPUImageBeautyFlower) gPUImageFilter).setOpacity(f);
        } else if (gPUImageFilter instanceof GPUImageBeautySoftlight) {
            ((GPUImageBeautySoftlight) gPUImageFilter).setOpacity(f);
        } else if (gPUImageFilter instanceof GPUImageBeautyWarm) {
            ((GPUImageBeautyWarm) gPUImageFilter).setOpacity(f);
        }
    }
}
