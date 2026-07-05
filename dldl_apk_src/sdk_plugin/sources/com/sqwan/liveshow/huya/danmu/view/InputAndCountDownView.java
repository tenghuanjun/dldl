package com.sqwan.liveshow.huya.danmu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.view.SkinLinearLayout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class InputAndCountDownView extends SkinLinearLayout {
    private LinearLayout linearLayout;
    public View.OnClickListener onClickListener;
    private TextView tvCountDown;
    private TextView tvInputMessage;

    public InputAndCountDownView(Context context) {
        this(context, null);
    }

    public InputAndCountDownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InputAndCountDownView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public InputAndCountDownView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_item_roast_view), this);
        this.linearLayout = (LinearLayout) findViewById(SqResUtils.getId(context, SqR.id.ll_roast_view));
        this.tvCountDown = (TextView) findViewById(SqResUtils.getId(context, SqR.id.tv_countdown));
        this.tvInputMessage = (TextView) findViewById(SqResUtils.getId(context, SqR.id.tv_input_message));
    }

    public TextView getTvInputMessage() {
        return this.tvInputMessage;
    }

    public TextView getTvCountDown() {
        return this.tvCountDown;
    }

    public LinearLayout getLinearLayout() {
        return this.linearLayout;
    }
}
