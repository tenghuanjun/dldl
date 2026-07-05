package com.sqwan.liveshow.huya.skin.attr;

import android.R;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinBackgroundAttr extends SkinAttrEx {
    public SkinBackgroundAttr(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
        this.resourceId = getAttributeValue(attributeSet, R.attr.background);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinAttrEx, com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    protected Class<? extends View> getSkinViewClass() {
        return View.class;
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    public void applySkinWithValid() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            this.view.setBackground(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        this.resourceId = checkResourceId(i);
        applySkinWithValid();
    }
}
