package com.sqwan.liveshow.huya.skin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.sqwan.liveshow.huya.skin.attr.SkinBackgroundAttr;
import com.sqwan.liveshow.huya.skin.attr.SkinViewInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinRelativeLayout extends RelativeLayout implements SkinViewInterface {
    private SkinBackgroundAttr skinBackgroundAttr;

    public SkinRelativeLayout(Context context) {
        this(context, null);
    }

    public SkinRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SkinRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public SkinRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.skinBackgroundAttr = new SkinBackgroundAttr(this, attributeSet);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        SkinBackgroundAttr skinBackgroundAttr = this.skinBackgroundAttr;
        if (skinBackgroundAttr != null) {
            skinBackgroundAttr.setBackgroundResource(i);
        }
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinViewInterface
    public void applySkin() {
        SkinBackgroundAttr skinBackgroundAttr = this.skinBackgroundAttr;
        if (skinBackgroundAttr != null) {
            skinBackgroundAttr.applySkin();
        }
    }
}
