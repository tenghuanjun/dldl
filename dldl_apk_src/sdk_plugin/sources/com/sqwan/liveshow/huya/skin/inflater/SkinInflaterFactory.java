package com.sqwan.liveshow.huya.skin.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.sqwan.common.util.LogUtil;
import com.sqwan.liveshow.huya.skin.attr.SkinViewInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinInflaterFactory extends AbsLayoutInflater implements LayoutInflater.Factory2 {
    private static final String TAG = "SkinInflaterFactory";
    private SkinAppLayoutInflater appLayoutInflater;

    @Override // com.sqwan.liveshow.huya.skin.inflater.InflaterInterface
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        LogUtil.i(TAG, "onCreateView 2 " + str);
        return createViewWithAttr(view, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        LogUtil.i(TAG, "onCreateView 1 " + str);
        return createViewWithAttr(null, str, context, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View createViewWithAttr(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.appLayoutInflater == null) {
            this.appLayoutInflater = new SkinAppLayoutInflater();
        }
        View viewOnCreateView = this.appLayoutInflater.onCreateView(view, str, context, attributeSet);
        if (viewOnCreateView instanceof SkinViewInterface) {
            ((SkinViewInterface) viewOnCreateView).applySkin();
        }
        return viewOnCreateView;
    }
}
