package com.sy37sdk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NetErrorView extends RelativeLayout {
    public NetErrorView(Context context) {
        super(context);
    }

    public NetErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NetErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(Util.getIdByName("sy37_net_error_view_port", "layout", getContext().getPackageName(), getContext()), this).findViewById(SqResUtils.getId(getContext(), "iv_error_view"));
        if (imageView != null) {
            imageView.setImageResource(SqResUtils.getDrawableId(getContext(), "sy37_net_wifi"));
        }
    }
}
