package com.sqwan.liveshow.huya.skin.attr;

import android.R;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinDrawableAttr extends SkinAttrEx {
    public SkinDrawableAttr(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
        this.resourceId = getAttributeValue(attributeSet, R.attr.src);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinAttrEx, com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    protected Class<? extends View> getSkinViewClass() {
        return ImageView.class;
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    public void applySkinWithValid() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            ((ImageView) this.view).setImageDrawable(drawable);
        }
    }

    public void setImageResource(int i) {
        this.resourceId = checkResourceId(i);
        applySkinWithValid();
    }
}
