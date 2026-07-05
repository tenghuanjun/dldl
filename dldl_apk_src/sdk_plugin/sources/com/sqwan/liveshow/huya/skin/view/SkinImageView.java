package com.sqwan.liveshow.huya.skin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sqwan.liveshow.huya.skin.attr.SkinDrawableAttr;
import com.sqwan.liveshow.huya.skin.attr.SkinViewInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinImageView extends ImageView implements SkinViewInterface {
    private SkinDrawableAttr skinDrawableAttr;

    public SkinImageView(Context context) {
        this(context, null);
    }

    public SkinImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.skinDrawableAttr = new SkinDrawableAttr(this, attributeSet);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinViewInterface
    public void applySkin() {
        SkinDrawableAttr skinDrawableAttr = this.skinDrawableAttr;
        if (skinDrawableAttr != null) {
            skinDrawableAttr.applySkin();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        SkinDrawableAttr skinDrawableAttr = this.skinDrawableAttr;
        if (skinDrawableAttr != null) {
            skinDrawableAttr.setImageResource(i);
        }
    }

    public void setBackground(String str, String str2) {
        SkinDrawableAttr skinDrawableAttr = this.skinDrawableAttr;
        if (skinDrawableAttr != null) {
            skinDrawableAttr.setBackground(str, str2);
        }
    }
}
