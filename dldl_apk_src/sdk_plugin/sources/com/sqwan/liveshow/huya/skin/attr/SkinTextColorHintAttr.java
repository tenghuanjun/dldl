package com.sqwan.liveshow.huya.skin.attr;

import android.R;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.util.LogUtil;
import com.sqwan.liveshow.huya.skin.SkinHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinTextColorHintAttr extends SkinAttrEx {
    public SkinTextColorHintAttr(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
        this.resourceId = getAttributeValue(attributeSet, R.attr.textColorHint);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinAttrEx, com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    protected Class<? extends View> getSkinViewClass() {
        return TextView.class;
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    public void applySkinWithValid() {
        int colorValue = SkinHelper.getColorValue(this.view.getContext(), getEntryNameByResId(), Color.parseColor("#090a0e"));
        LogUtil.d("xxxxxxxxx", getEntryNameByResId() + "_" + colorValue);
        ((TextView) this.view).setHintTextColor(colorValue);
    }
}
