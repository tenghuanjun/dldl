package com.huya.berry.modifynickname;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NickNameInputFilter implements InputFilter {
    private int mMaxLength;
    String regEx = "[^a-zA-Z0-9\\u4e00-\\u9fa5\\-丶【】]";

    public NickNameInputFilter(int i) {
        this.mMaxLength = 20;
        this.mMaxLength = i;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (spanned == null) {
            return null;
        }
        String strReplaceAll = spanned.toString().replaceAll(this.regEx, "");
        int i5 = 0;
        int i6 = 0;
        while (i5 <= this.mMaxLength && i6 < strReplaceAll.length()) {
            int i7 = i6 + 1;
            i5 = strReplaceAll.charAt(i6) < 128 ? i5 + 1 : i5 + 2;
            i6 = i7;
        }
        if (i5 >= this.mMaxLength || (charSequence instanceof Spanned)) {
            return "";
        }
        String strReplaceAll2 = charSequence.toString().replaceAll(this.regEx, "");
        int i8 = 0;
        while (i5 <= this.mMaxLength && i8 < strReplaceAll2.length()) {
            int i9 = i8 + 1;
            i5 = charSequence.charAt(i8) < 128 ? i5 + 1 : i5 + 2;
            i8 = i9;
        }
        if (i5 > this.mMaxLength) {
            i8--;
        }
        return strReplaceAll2.subSequence(0, i8);
    }
}
