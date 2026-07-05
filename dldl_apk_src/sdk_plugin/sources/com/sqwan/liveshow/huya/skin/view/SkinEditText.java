package com.sqwan.liveshow.huya.skin.view;

import android.content.Context;
import android.util.AttributeSet;
import com.sqwan.liveshow.huya.skin.attr.SkinBackgroundAttr;
import com.sqwan.liveshow.huya.skin.attr.SkinTextColorAttr;
import com.sqwan.liveshow.huya.skin.attr.SkinTextColorHintAttr;
import com.sqwan.liveshow.huya.skin.attr.SkinViewInterface;
import com.sqwan.supportview.LimitedEditText;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinEditText extends LimitedEditText implements SkinViewInterface {
    private SkinBackgroundAttr skinBackgroundAttr;
    private SkinTextColorAttr skinTextColorAttr;
    private SkinTextColorHintAttr skinTextColorHintAttr;

    public SkinEditText(Context context) {
        super(context, null);
    }

    public SkinEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.skinBackgroundAttr = new SkinBackgroundAttr(this, attributeSet);
        this.skinTextColorAttr = new SkinTextColorAttr(this, attributeSet);
        this.skinTextColorHintAttr = new SkinTextColorHintAttr(this, attributeSet);
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
        SkinTextColorHintAttr skinTextColorHintAttr = this.skinTextColorHintAttr;
        if (skinTextColorHintAttr != null) {
            skinTextColorHintAttr.applySkin();
        }
        SkinTextColorAttr skinTextColorAttr = this.skinTextColorAttr;
        if (skinTextColorAttr != null) {
            skinTextColorAttr.applySkin();
        }
    }
}
