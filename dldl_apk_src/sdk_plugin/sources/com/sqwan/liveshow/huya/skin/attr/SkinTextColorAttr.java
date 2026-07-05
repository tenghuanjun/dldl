package com.sqwan.liveshow.huya.skin.attr;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.sqwan.liveshow.huya.skin.SkinHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinTextColorAttr extends SkinAttrEx {
    public SkinTextColorAttr(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
        this.resourceId = getAttributeValue(attributeSet, R.attr.textColor);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinAttrEx, com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    protected Class<? extends View> getSkinViewClass() {
        return TextView.class;
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    public void applySkinWithValid() {
        ((TextView) this.view).setTextColor(SkinHelper.getColorValue(this.view.getContext(), getEntryNameByResId(), Color.parseColor("#090a0e")));
    }

    public void setTextColor(Context context, String str) {
        ((TextView) this.view).setTextColor(SkinHelper.getColorValue(context, str, Color.parseColor("#090a0e")));
    }
}
