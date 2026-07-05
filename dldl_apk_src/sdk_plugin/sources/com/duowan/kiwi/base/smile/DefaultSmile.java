package com.duowan.kiwi.base.smile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.LruCache;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.mtp.utils.DensityUtil;
import com.huya.mtp.utils.FP;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DefaultSmile implements SmileConst {
    private static final int CACHE_SIZE = 1048576;
    private static final int Default_Emoji_face_len = 10;
    private static LruCache<String, Bitmap> msCache = new LruCache<>(1048576);
    private static final int Default_Image_Size = DensityUtil.dip2px(ArkValue.gContext, 20.0f);
    private static final Map<String, String> msSmiles = SmileLoader.manualLoad();
    private static final Map<String, String> msSmilesString = SmileLoader.manualLoadString();
    private static final Map<String, String> mOldSmileMap = SmileLoader.manualLoad();
    private static final Map<String, String> mNewAddedSmileMap = SmileLoader.manualLoad();

    public static String preProcessText(String str) {
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(SmileConst.PREFIX, i);
            if (iIndexOf > 10) {
                return str.substring(0, iIndexOf) + "...";
            }
            if (iIndexOf == -1) {
                return str;
            }
            i = iIndexOf + 1;
        }
    }

    public static void matchText(Context context, SpannableString spannableString) {
        matchText(context, spannableString, Default_Image_Size);
    }

    public static void matchText(Context context, SpannableString spannableString, int i) {
        String string = spannableString.toString();
        int spanLen = 0;
        while (true) {
            int iIndexOf = string.indexOf(SmileConst.PREFIX, spanLen);
            if (iIndexOf == -1) {
                return;
            } else {
                spanLen = iIndexOf + getSpanLen(context, spannableString, iIndexOf, i);
            }
        }
    }

    public static SpannableString matchText(Context context, String str, int i) {
        if (FP.empty(str)) {
            return null;
        }
        String strReplaceNewAddedSmile = replaceNewAddedSmile(replaceOldSmile(str));
        SpannableString spannableString = new SpannableString(strReplaceNewAddedSmile);
        int spanLen = 0;
        while (true) {
            int iIndexOf = strReplaceNewAddedSmile.indexOf(SmileConst.PREFIX, spanLen);
            if (iIndexOf == -1) {
                return spannableString;
            }
            spanLen = iIndexOf + getSpanLen(context, spannableString, iIndexOf, i);
        }
    }

    private static String replaceOldSmile(String str) {
        for (Map.Entry<String, String> entry : mOldSmileMap.entrySet()) {
            if (str.contains(entry.getKey())) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }
        return str;
    }

    private static String getOldSmileString(String str) {
        String str2 = mOldSmileMap.get(str);
        if (str2 == null) {
            return null;
        }
        return str2;
    }

    private static String replaceNewAddedSmile(String str) {
        for (Map.Entry<String, String> entry : mNewAddedSmileMap.entrySet()) {
            if (str.contains(entry.getKey())) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }
        return str;
    }

    public static boolean hasSmile(String str) {
        int i;
        int i2 = 0;
        while (true) {
            int iIndexOf = str.indexOf(SmileConst.PREFIX, i2);
            if (iIndexOf == -1) {
                return false;
            }
            for (int i3 = 4; i3 <= 5 && str.length() >= (i = iIndexOf + i3); i3++) {
                String strSubstring = str.substring(iIndexOf, i);
                if (msCache.get(strSubstring + SmileConst.ADJUST) != null || getSmile(ArkValue.gContext, strSubstring) != null) {
                    return true;
                }
            }
            i2 = iIndexOf + 2;
        }
    }

    public static Set<String> getAllKeys() {
        return msSmiles.keySet();
    }

    private static ImageSpan getImageSpan(Context context, String str, int i) {
        Bitmap adjustSmile = getAdjustSmile(context, str, i);
        if (adjustSmile == null) {
            return null;
        }
        return new VerticalImageSpan(context, adjustSmile);
    }

    private static Bitmap getAdjustSmile(Context context, String str, int i) {
        String str2 = str + SmileConst.ADJUST + i;
        Bitmap bitmap = msCache.get(str2);
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap smile = getSmile(context, str);
        if (smile == null) {
            return null;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(smile, i, i, false);
        msCache.put(str2, bitmapCreateScaledBitmap);
        return bitmapCreateScaledBitmap;
    }

    public static Bitmap getSmile(Context context, String str) {
        Bitmap bitmapDecodeStream = msCache.get(str + SmileConst.ADJUST);
        if (bitmapDecodeStream != null) {
            return bitmapDecodeStream;
        }
        String str2 = msSmiles.get(str);
        if (str2 == null) {
            return null;
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open(SmileConst.DIR + str2);
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
            inputStreamOpen.close();
        } catch (IOException e) {
            L.error(context, "getSmile | %s | %s", str, e);
        }
        if (bitmapDecodeStream == null) {
            return null;
        }
        msCache.put(str, bitmapDecodeStream);
        return bitmapDecodeStream;
    }

    @Deprecated
    public static SpannableString getSpannableString(Context context, String str) {
        SpannableString spannableString = new SpannableString(str);
        ImageSpan imageSpan = getImageSpan(context, str, Default_Image_Size);
        if (imageSpan != null) {
            spannableString.setSpan(imageSpan, 0, spannableString.length(), 17);
        }
        return spannableString;
    }

    public static SpannableString getExclusiveSpannableString(Context context, String str) {
        SpannableString spannableString = new SpannableString(str);
        ImageSpan imageSpan = getImageSpan(context, str, Default_Image_Size);
        if (imageSpan != null) {
            spannableString.setSpan(imageSpan, 0, spannableString.length(), 33);
        }
        return spannableString;
    }

    private static int getSpanLen(Context context, SpannableString spannableString, int i, int i2) {
        int i3;
        String string = spannableString.toString();
        for (int i4 = 4; i4 <= 5 && string.length() >= (i3 = i + i4); i4++) {
            ImageSpan imageSpan = getImageSpan(context, string.substring(i, i3), i2);
            if (imageSpan != null) {
                spannableString.setSpan(imageSpan, i, i3, 17);
                return i4;
            }
        }
        return 2;
    }

    private static int getSpanLen(StringBuilder sb, int i) {
        int i2;
        int i3 = 4;
        while (i3 <= 5 && sb.length() >= (i2 = i + i3)) {
            String smileString = getSmileString(sb.substring(i, i2));
            if (smileString != null) {
                int length = smileString.length();
                sb.replace(i, i2, smileString);
                return length == i3 ? i3 : (i3 - length) + 1;
            }
            i3++;
        }
        return 2;
    }

    private static String getSmileString(String str) {
        String str2 = msSmilesString.get(str);
        if (str2 == null) {
            return null;
        }
        return str2;
    }

    public static String getMessageWithSmileString(String str) {
        StringBuilder sb = new StringBuilder(str);
        int spanLen = 0;
        while (true) {
            int iIndexOf = sb.indexOf(SmileConst.PREFIX, spanLen);
            if (iIndexOf != -1) {
                spanLen = iIndexOf + getSpanLen(sb, iIndexOf);
            } else {
                return sb.toString();
            }
        }
    }

    private static class VerticalImageSpan extends ImageSpan {
        public VerticalImageSpan(Context context, Bitmap bitmap) {
            super(context, bitmap);
        }

        public VerticalImageSpan(Drawable drawable) {
            super(drawable);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            Rect bounds = getDrawable().getBounds();
            if (fontMetricsInt != null) {
                Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
                int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
                int i4 = (bounds.bottom - bounds.top) / 2;
                int i5 = i3 / 4;
                int i6 = i4 - i5;
                int i7 = -(i4 + i5);
                fontMetricsInt.ascent = i7;
                fontMetricsInt.top = i7;
                fontMetricsInt.bottom = i6;
                fontMetricsInt.descent = i6;
            }
            return bounds.right;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            canvas.translate(f, (((i5 - i3) - drawable.getBounds().bottom) / 2) + i3);
            drawable.draw(canvas);
            canvas.restore();
        }
    }
}
