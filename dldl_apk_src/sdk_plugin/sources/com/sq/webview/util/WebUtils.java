package com.sq.webview.util;

import android.graphics.Bitmap;
import android.net.Uri;
import com.sq.tools.network.httpdns.SqHttpDns;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebUtils {
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static float getBitmapWhiteScreenRate(android.graphics.Bitmap r8) {
        /*
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r8 == 0) goto L4d
            boolean r1 = r8.isRecycled()
            if (r1 == 0) goto Lb
            goto L4d
        Lb:
            int[] r8 = getPicturePixel(r8)
            int r1 = r8.length
            float r1 = (float) r1
            r2 = 0
            int r3 = r8.length
            r4 = 0
        L14:
            if (r4 >= r3) goto L4b
            r5 = r8[r4]
            if (r5 != 0) goto L1c
        L1a:
            float r2 = r2 + r0
            goto L48
        L1c:
            r6 = 16711680(0xff0000, float:2.3418052E-38)
            r6 = r6 & r5
            int r6 = r6 >> 16
            r7 = 65280(0xff00, float:9.1477E-41)
            r7 = r7 & r5
            int r7 = r7 >> 8
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.Boolean r6 = getWhiteDiff(r6)
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L48
            java.lang.Boolean r6 = getWhiteDiff(r7)
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L48
            java.lang.Boolean r5 = getWhiteDiff(r5)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L48
            goto L1a
        L48:
            int r4 = r4 + 1
            goto L14
        L4b:
            float r2 = r2 / r1
            return r2
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.webview.util.WebUtils.getBitmapWhiteScreenRate(android.graphics.Bitmap):float");
    }

    private static Boolean getWhiteDiff(int color) {
        return Boolean.valueOf(((double) ((((float) color) * 1.0f) / 255.0f)) >= 0.99d);
    }

    private static int[] getPicturePixel(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return iArr;
    }

    public static boolean isFavicon(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                return isFavicon(Uri.parse(url));
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean isFavicon(Uri uri) {
        String path;
        return (uri == null || uri.getPath() == null || (path = uri.getPath()) == null || !path.endsWith("favicon.ico")) ? false : true;
    }

    public static boolean isInHttpDnsBlacklist(String urlStr) {
        try {
            return isInBlacklist(urlStr, SqHttpDns.getInstance().getReportBlackList());
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isInBlacklist(String urlStr, List<String> blacklist) {
        String strEncodedPath;
        if (urlStr != null && !urlStr.isEmpty() && blacklist != null && !blacklist.isEmpty()) {
            HttpUrl httpUrl = HttpUrl.parse(urlStr);
            if (httpUrl == null) {
                if (urlStr.contains("/")) {
                    strEncodedPath = urlStr;
                    urlStr = null;
                } else {
                    strEncodedPath = null;
                }
            } else {
                urlStr = httpUrl.host();
                strEncodedPath = httpUrl.encodedPath();
            }
            for (String str : blacklist) {
                if (str != null && !str.isEmpty()) {
                    if (str.contains("/")) {
                        if (strEncodedPath != null && strEncodedPath.contains(str)) {
                            return true;
                        }
                    } else if (str.equals(urlStr)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
