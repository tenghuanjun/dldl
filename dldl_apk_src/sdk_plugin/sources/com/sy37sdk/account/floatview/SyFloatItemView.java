package com.sy37sdk.account.floatview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
@Deprecated
public class SyFloatItemView extends FrameLayout {
    private int mIconId;
    private String mName;
    private ImageView mRedDotView;
    private TextView mTextView;

    public void setRedDotVisible(boolean z) {
    }

    public SyFloatItemView(Context context) {
        this(context, null);
    }

    public SyFloatItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SyFloatItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int idByName = SqResUtils.getIdByName("sy_name", "attr", context);
        int idByName2 = SqResUtils.getIdByName("sy_icon", "attr", context);
        if (attributeSet != null) {
            for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
                int attributeNameResource = attributeSet.getAttributeNameResource(i2);
                if (attributeNameResource == idByName) {
                    this.mName = context.getString(attributeSet.getAttributeResourceValue(i2, -1));
                } else if (attributeNameResource == idByName2) {
                    this.mIconId = attributeSet.getAttributeResourceValue(i2, -1);
                }
            }
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(SqResUtils.getIdByName("sy37_float_item", "layout", getContext()), (ViewGroup) this, true);
        this.mTextView = (TextView) findViewById(SqResUtils.getIdByName("sy_text", SqTrackCommonKey.id, getContext()));
        this.mRedDotView = (ImageView) findViewById(SqResUtils.getIdByName("sy_red_dot", SqTrackCommonKey.id, getContext()));
        this.mTextView.setText(this.mName);
        this.mTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getContext().getResources().getDrawable(this.mIconId), (Drawable) null, (Drawable) null);
    }

    public void setVisible(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    public boolean isRedDotVisible() {
        return this.mRedDotView.getVisibility() == 0;
    }
}
