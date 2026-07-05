package com.alipay.zoloz.image;

import android.graphics.Bitmap;
import com.alipay.zoloz.image.impl.ToygerImageAndroid;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_zkfv_1ts_1tj;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerImageUtil {
    private static ToygerImageAndroid sToygerImageAndroid;

    static {
        java2jni_do_not_delete_this_library_zkfv_1ts_1tj.loadLibrary();
        sToygerImageAndroid = new ToygerImageAndroid();
    }

    public static native Bitmap tgFrameToBitmap(TGFrame tGFrame, int i, float f, boolean z);

    public static native byte[] tgFrameToBlob(TGFrame tGFrame, int i, float f, String str, boolean z);
}
