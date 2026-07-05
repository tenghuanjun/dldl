package com.sqwan.liveshow.huya.skin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.sqwan.liveshow.huya.skin.attr.SkinTextColorAttr;
import com.sqwan.liveshow.huya.skin.attr.SkinViewInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinTextView extends TextView implements SkinViewInterface {
    private SkinTextColorAttr skinTextColorAttr;

    public SkinTextView(Context context) {
        this(context, null);
    }

    public SkinTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.skinTextColorAttr = new SkinTextColorAttr(this, attributeSet);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinViewInterface
    public void applySkin() {
        SkinTextColorAttr skinTextColorAttr = this.skinTextColorAttr;
        if (skinTextColorAttr != null) {
            skinTextColorAttr.applySkin();
        }
    }

    public void setTextColor(Context context, String str) {
        SkinTextColorAttr skinTextColorAttr = this.skinTextColorAttr;
        if (skinTextColorAttr != null) {
            skinTextColorAttr.setTextColor(context, str);
        }
    }
}
