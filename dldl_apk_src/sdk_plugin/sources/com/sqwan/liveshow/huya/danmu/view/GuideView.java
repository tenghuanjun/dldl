package com.sqwan.liveshow.huya.danmu.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.view.SkinImageView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class GuideView extends LinearLayout implements View.OnClickListener {
    private SkinImageView iv_guide_content;
    private SkinImageView iv_switch;
    private View.OnClickListener onClickListener;

    public GuideView(Context context) {
        this(context, null);
    }

    public GuideView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GuideView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public GuideView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_layout_guide_view), this);
        this.iv_guide_content = (SkinImageView) findViewById(SqResUtils.getId(context, SqR.id.iv_guide_content));
        this.iv_switch = (SkinImageView) findViewById(SqResUtils.getId(context, SqR.id.iv_live_switch_width));
        this.iv_guide_content.setOnClickListener(this);
        this.iv_switch.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener = this.onClickListener;
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick(view);
    }

    public void resetGuideViewPosition(View view, Drawable drawable, Drawable drawable2, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        if (this.iv_guide_content != null) {
            view.getLocationInWindow(new int[2]);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.iv_guide_content.getLayoutParams();
            marginLayoutParams.leftMargin = (r13[0] - ((int) (((double) this.iv_guide_content.getWidth()) * 0.7d))) - 10;
            marginLayoutParams.topMargin = (r13[1] - this.iv_guide_content.getHeight()) - 5;
            this.iv_guide_content.setLayoutParams(marginLayoutParams);
            this.iv_guide_content.setImageDrawable(drawable2);
        }
        if (this.iv_switch != null) {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.iv_switch.getLayoutParams();
            marginLayoutParams2.leftMargin = iArr[0];
            marginLayoutParams2.topMargin = iArr[1];
            marginLayoutParams2.width = view.getWidth();
            marginLayoutParams2.height = view.getHeight();
            this.iv_switch.setLayoutParams(marginLayoutParams2);
            this.iv_switch.setImageDrawable(drawable);
        }
        if (CommonConfigs.getInstance().getIsNewRole(getContext())) {
            setVisibility(0);
            CommonConfigs.getInstance().setNewRoleId(getContext());
        }
    }
}
