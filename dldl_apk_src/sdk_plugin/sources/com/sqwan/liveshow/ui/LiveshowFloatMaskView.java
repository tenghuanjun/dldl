package com.sqwan.liveshow.ui;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.liveshow.SqR;
import com.sy37sdk.account.floatview.CheckSystemUiViewBase;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowFloatMaskView extends CheckSystemUiViewBase {
    private LiveshowFloatView liveshowFloatView;

    public void setLiveshowFloatView(LiveshowFloatView liveshowFloatView) {
        if (isFixWindowManager()) {
            this.liveshowFloatView = liveshowFloatView;
        }
    }

    private boolean isFixWindowManager() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23;
    }

    public LiveshowFloatMaskView(Context context) {
        super(context);
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_base_liveshow_floatview_mask), this);
        if (isFixWindowManager()) {
            setOnTouchListener(new View.OnTouchListener() { // from class: com.sqwan.liveshow.ui.LiveshowFloatMaskView.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        if (LiveshowFloatMaskView.this.liveshowFloatView != null && LiveshowFloatMaskView.this.liveshowFloatView.fixWindowManager(motionEvent)) {
                            return false;
                        }
                        LiveshowFloatMaskView.this.dismiss();
                    }
                    return true;
                }
            });
        } else {
            setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowFloatMaskView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LiveshowFloatMaskView.this.dismiss();
                }
            });
        }
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void initLayoutParams() {
        super.initLayoutParams();
        this.floatLayoutParams.width = -1;
        this.floatLayoutParams.height = -1;
        ViewUtils.gone(this);
    }

    public void show() {
        if (!isAttachedToWindow()) {
            init();
            addView();
        } else {
            ViewUtils.show(this);
            update();
        }
    }

    public void dismiss() {
        ViewUtils.gone(this);
        update();
        if (this.closeCallback != null) {
            this.closeCallback.invoke();
        }
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void release() {
        super.release();
    }
}
