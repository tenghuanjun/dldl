package com.alipay.zoloz.image;

import android.graphics.Bitmap;
import com.alipay.zoloz.toyger.algorithm.TGFrame;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ToygerImage {
    public static final String TAG = "TOYGER_FLOW_ANDROID::ToygerImage";

    Bitmap tgFrameToBitmap(TGFrame tGFrame, int i, float f, String str, boolean z);

    byte[] tgFrameToBlob(TGFrame tGFrame, int i, float f, String str, boolean z);
}
