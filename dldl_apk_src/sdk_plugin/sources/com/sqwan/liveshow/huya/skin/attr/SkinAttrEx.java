package com.sqwan.liveshow.huya.skin.attr;

import android.R;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.sqwan.common.util.SelectorUtil;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import com.sqwan.liveshow.huya.skin.SkinSelectorState;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class SkinAttrEx extends AbstraceSkinAttr {
    public SkinAttrEx(View view, AttributeSet attributeSet) {
        super(view, attributeSet);
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.AbstraceSkinAttr
    protected Class<? extends View> getSkinViewClass() {
        return View.class;
    }

    public String getEntryNameByResId() {
        return this.resourceId != 0 ? this.view.getContext().getResources().getResourceEntryName(this.resourceId) : "";
    }

    public Drawable getDrawable() {
        return SkinHelper.getDrawable(this.view.getContext(), getEntryNameByResId());
    }

    public void setBackground(SkinSelectorState... skinSelectorStateArr) {
        Drawable[] drawableArr = new Drawable[skinSelectorStateArr.length];
        int[] iArr = new int[skinSelectorStateArr.length];
        for (int i = 0; i < skinSelectorStateArr.length; i++) {
            SkinSelectorState skinSelectorState = skinSelectorStateArr[i];
            drawableArr[i] = SkinHelper.getDrawable(this.view.getContext(), skinSelectorState.resName);
            iArr[i] = skinSelectorState.stateId;
        }
        SelectorUtil.addSelectorFromDrawable(this.view.getContext(), iArr, drawableArr, this.view);
    }

    public void setBackground(String str, String str2) {
        setBackground(new SkinSelectorState(R.attr.state_selected, str), new SkinSelectorState(-16842913, str2));
    }
}
