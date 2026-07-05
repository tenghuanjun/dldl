package com.sqwan.liveshow.huya.skin.inflater;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.sqwan.liveshow.huya.skin.manager.SkinManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SkinAppLayoutInflater extends AbsLayoutInflater implements InflaterInterface {
    @Override // com.sqwan.liveshow.huya.skin.inflater.InflaterInterface
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (-1 == str.indexOf(46)) {
            for (int i = 0; i < sClassPrefixList.length; i++) {
                if (SkinManager.getInstance().canSkinViewConvert(sClassPrefixList[i] + str)) {
                    return createViewFromTag(context, SkinManager.getInstance().findSkinInflaterViewClassName(sClassPrefixList[i] + str), attributeSet);
                }
            }
        }
        if (SkinManager.getInstance().canSkinViewConvert(str)) {
            return createViewFromTag(context, SkinManager.getInstance().findSkinInflaterViewClassName(str), attributeSet);
        }
        return null;
    }
}
