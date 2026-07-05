package com.sqwan.common.util;

import android.text.Editable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SpanUtil {
    public static CharSequence getFontString(String str, int i, int i2, boolean z) {
        if (!z) {
            return getFontString(str, i, i2);
        }
        StyleSpan styleSpan = new StyleSpan(1);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i), 0, spannableString.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(i2), 0, spannableString.length(), 33);
        spannableString.setSpan(styleSpan, 0, spannableString.length(), 17);
        return spannableString;
    }

    public static CharSequence getFontString(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i), 0, spannableString.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(i2), 0, spannableString.length(), 33);
        return spannableString;
    }

    public static CharSequence concat(CharSequence... charSequenceArr) {
        CharSequence charSequence = charSequenceArr[0];
        if (charSequence == null) {
            charSequence = "";
        }
        Editable editableNewEditable = Editable.Factory.getInstance().newEditable(charSequence);
        for (int i = 1; i < charSequenceArr.length; i++) {
            CharSequence charSequence2 = charSequenceArr[i];
            if (charSequenceArr != null) {
                editableNewEditable.append(charSequence2);
            }
        }
        return editableNewEditable;
    }
}
