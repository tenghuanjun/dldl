package com.sqwan.common.data;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SpannableEntity {
    private String colorValue = "#3087DE";
    private String link;
    private OnClickListener onClickListener;
    private String tag;

    public interface OnClickListener {
        void onClick();
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(String str) {
        this.colorValue = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
