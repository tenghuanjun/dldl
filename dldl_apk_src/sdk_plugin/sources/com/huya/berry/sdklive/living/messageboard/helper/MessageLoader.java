package com.huya.berry.sdklive.living.messageboard.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.duowan.auk.ArkValue;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.sdklive.living.messageboard.widget.VerticalImageSpan;
import com.sqwan.liveshow.huya.SqR;
import com.youme.voiceengine.YouMeConst;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessageLoader {
    private static final int COLOR_NAME = Color.argb(255, 255, YouMeConst.YouMeEvent.YOUME_EVENT_OTHER_PLAY_BACKGRAOUND_MUSIC, 93);
    private static final int COLOR_OWN_NAME = Color.argb(255, 255, 157, 31);
    private static final int MAX_NICK_LENGTH = 12;
    public static final int NORMAL_TEXT_SIZE = 14;

    public static SpannableString getClickableNickSpanText(String str) {
        return getClickableNickSpanText(str, COLOR_NAME, 0, str.length());
    }

    public static SpannableString getClickableNickSpanText(String str, int i) {
        return getClickableNickSpanText(str, i, 0, str.length());
    }

    public static SpannableString getClickableNickSpanText(String str, int i, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() { // from class: com.huya.berry.sdklive.living.messageboard.helper.MessageLoader.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
            }
        }, i2, i3, 33);
        spannableString.setSpan(new ForegroundColorSpan(i), i2, i3, 18);
        return spannableString;
    }

    public static String getValidNickName(String str) {
        return str.length() >= 12 ? ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_string_ellipsis_end), new Object[]{str.substring(0, 12)}) : str;
    }

    public static void setImageSpannele(SpannableStringBuilder spannableStringBuilder, String str, int i) {
        spannableStringBuilder.append((CharSequence) getImageSpannele(str, BitmapFactory.decodeResource(ArkValue.gContext.getResources(), i), 0, str.length()));
    }

    public static void setImageSpannele(SpannableString spannableString, Bitmap bitmap, int i, int i2) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(ArkValue.gContext.getResources(), bitmap);
        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        spannableString.setSpan(new VerticalImageSpan(bitmapDrawable, 0), i, i2, 17);
    }

    public static SpannableString getImageSpannele(String str, Bitmap bitmap) {
        return getImageSpannele(str, new BitmapDrawable(ArkValue.gContext.getResources(), bitmap), 0, str.length(), 0, 0);
    }

    public static SpannableString getImageSpannele(String str, Drawable drawable) {
        return getImageSpannele(str, drawable, 0, str.length(), 0, 0);
    }

    public static SpannableString getImageSpannele(String str, Bitmap bitmap, int i, int i2, int i3, int i4) {
        return getImageSpannele(str, new BitmapDrawable(ArkValue.gContext.getResources(), bitmap), i, i2, i3, i4);
    }

    public static SpannableString getImageSpannele(String str, Bitmap bitmap, int i, int i2) {
        return getImageSpannele(str, bitmap, i, i2, 0, 0);
    }

    public static SpannableString getImageSpannele(String str, Drawable drawable, int i, int i2) {
        return getImageSpannele(str, drawable, i, i2, 0, 0);
    }

    public static SpannableString getImageSpannele(String str, Drawable drawable, int i, int i2, int i3, int i4) {
        SpannableString spannableString = new SpannableString(str);
        if (i3 == 0 || i4 == 0) {
            i3 = drawable.getIntrinsicWidth();
            i4 = drawable.getIntrinsicHeight();
        }
        drawable.setBounds(0, 0, i3, i4);
        spannableString.setSpan(new VerticalImageSpan(drawable, 0), i, i2, 17);
        return spannableString;
    }

    public static SpannableString getDrawSpannele(String str, Drawable drawable, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new VerticalImageSpan(drawable, 0), i, i2, 17);
        return spannableString;
    }

    public static SpannableString getSpannaleText(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(i), 0, str.length(), 18);
        return spannableString;
    }
}
