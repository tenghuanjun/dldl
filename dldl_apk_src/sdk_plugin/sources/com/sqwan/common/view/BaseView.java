package com.sqwan.common.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BaseView extends FrameLayout {
    private long clickTime;

    public BaseView(Context context) {
        super(context);
        this.clickTime = 0L;
    }

    protected int getIdByName(String str, String str2) {
        return SqResUtils.getIdByName(str, str2, getContext());
    }

    protected <T extends View> T getViewByName(View view, String str) {
        return (T) view.findViewById(getIdByName(str, SqTrackCommonKey.id));
    }

    protected synchronized boolean isQuickClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.clickTime < 400) {
            this.clickTime = jCurrentTimeMillis;
            return true;
        }
        this.clickTime = jCurrentTimeMillis;
        return false;
    }
}
