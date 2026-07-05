package com.sy37sdk.share;

import android.content.Context;
import android.graphics.Bitmap;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareImageHandler {
    public static int[] handleSize(Context context, Bitmap bitmap, int[] iArr, int[] iArr2) {
        int i;
        int i2;
        int i3;
        int i4;
        if (bitmap == null) {
            return new int[]{0, 0};
        }
        int i5 = context.getResources().getConfiguration().orientation;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        LogUtil.i("原始图片宽：" + width + " 高" + height);
        LogUtil.i("横屏时最大宽高：" + iArr[0] + "  " + iArr[1] + " 竖屏时最大宽高：" + iArr2[0] + " " + iArr2[1]);
        StringBuilder sb = new StringBuilder();
        sb.append("宽高比：");
        sb.append(1.7777778f);
        LogUtil.i(sb.toString());
        float f = (float) width;
        float f2 = (float) height;
        float f3 = f / f2;
        float f4 = f2 / f;
        if (i5 == 2) {
            if (f3 >= 1.7777778f) {
                i = iArr[0];
                i2 = (height * i) / width;
            } else {
                i3 = iArr[1];
                i4 = (width * i3) / height;
                int i6 = i4;
                i2 = i3;
                i = i6;
            }
        } else if (f4 >= 1.7777778f) {
            i3 = iArr2[1];
            i4 = (width * i3) / height;
            int i62 = i4;
            i2 = i3;
            i = i62;
        } else {
            i = iArr2[0];
            i2 = (height * i) / width;
        }
        LogUtil.i("计算出的实际宽高 width = " + i + " height = " + i2);
        return new int[]{i, i2};
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0047 A[Catch: Exception -> 0x0032, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x0032, blocks: (B:16:0x002b, B:27:0x0047), top: B:32:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String bitmapToBase64(android.graphics.Bitmap r4) throws java.lang.Throwable {
        /*
            java.lang.String r0 = ""
            r1 = 0
            if (r4 == 0) goto L45
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            r2.<init>()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L25
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r3 = 100
            r4.compress(r1, r3, r2)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            byte[] r4 = r2.toByteArray()     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r1 = 0
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r1)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L20
            r0 = r4
            r1 = r2
            goto L45
        L1d:
            r4 = move-exception
            r1 = r2
            goto L37
        L20:
            r4 = move-exception
            r1 = r2
            goto L26
        L23:
            r4 = move-exception
            goto L37
        L25:
            r4 = move-exception
        L26:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L23
            if (r1 == 0) goto L4d
            r1.flush()     // Catch: java.lang.Exception -> L32
            r1.close()     // Catch: java.lang.Exception -> L32
            goto L4d
        L32:
            r4 = move-exception
            r4.printStackTrace()
            goto L4d
        L37:
            if (r1 == 0) goto L44
            r1.flush()     // Catch: java.lang.Exception -> L40
            r1.close()     // Catch: java.lang.Exception -> L40
            goto L44
        L40:
            r0 = move-exception
            r0.printStackTrace()
        L44:
            throw r4
        L45:
            if (r1 == 0) goto L4d
            r1.flush()     // Catch: java.lang.Exception -> L32
            r1.close()     // Catch: java.lang.Exception -> L32
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.share.ShareImageHandler.bitmapToBase64(android.graphics.Bitmap):java.lang.String");
    }
}
