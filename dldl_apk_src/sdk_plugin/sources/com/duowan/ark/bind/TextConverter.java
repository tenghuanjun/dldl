package com.duowan.ark.bind;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TextConverter<E> implements DataConverter<CharSequence, E> {
    private String mFormat;

    public TextConverter() {
        this.mFormat = "";
    }

    public TextConverter(String str) {
        this.mFormat = "";
        this.mFormat = str == null ? "" : str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.duowan.ark.bind.DataConverter
    public CharSequence convert(E e) {
        CharSequence string;
        if (e == null) {
            string = "";
        } else if (e instanceof CharSequence) {
            string = (CharSequence) e;
        } else {
            string = e.toString();
        }
        return !TextUtils.isEmpty(this.mFormat) ? String.format(this.mFormat, string) : string;
    }
}
