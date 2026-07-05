package com.sy37sdk.account.floatview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.DisplayUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.floatview.SystemUiCheckUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BottomDeleteView extends CheckSystemUiViewBase implements SystemUiCheckUtils.onSystemUiChangedCallback {
    private ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback;
    FloatViewUtils.FloatViewConfig floatViewConfig;
    private ImageView ivStatus;
    private View llContainer;
    private MoniterHeightListener moniterHeightListener;
    private TextView tvStatus;

    public interface MoniterHeightListener {
        void onChange(int i);
    }

    public BottomDeleteView(Context context) {
        this(context, null);
    }

    public BottomDeleteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.appVisibilityCallback = new ActivityLifeCycleUtils.AppVisibilityCallbackAdapter() { // from class: com.sy37sdk.account.floatview.BottomDeleteView.1
            @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                super.onActivityPaused(activity);
                BottomDeleteView.this.hideBottomDeleteView();
            }

            @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                super.onActivityResumed(activity);
                BottomDeleteView.this.hideBottomDeleteView();
            }
        };
        ViewUtils.hidden(this);
        View.inflate(this.mContext, SqResUtils.getLayoutId(this.mContext, "sy37_view_bottom_delete"), this);
        this.ivStatus = (ImageView) findViewById(SqResUtils.getId(this.mContext, "iv_status"));
        this.tvStatus = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_status"));
        this.llContainer = findViewById(SqResUtils.getId(this.mContext, "ll_container"));
        ActivityLifeCycleUtils.getInstance().registerActivityListener(this.appVisibilityCallback);
        SystemUiCheckUtils.getInstance().init(this.mContext);
    }

    public void setStatus(boolean z) {
        int drawableId;
        boolean zIsLandscape = DisplayUtil.isLandscape(getContext());
        if (z) {
            if (zIsLandscape) {
                drawableId = SqResUtils.getDrawableId(getContext(), "sy37_bg_bottomdelete_landscape_highlight");
            } else {
                drawableId = SqResUtils.getDrawableId(getContext(), "sy37_bg_bottomdelete_portrait_highlight");
            }
        } else if (zIsLandscape) {
            drawableId = SqResUtils.getDrawableId(getContext(), "sy37_bg_bottomdelete_landscape_normal");
        } else {
            drawableId = SqResUtils.getDrawableId(getContext(), "sy37_bg_bottomdelete_portrait_normal");
        }
        this.llContainer.setBackgroundResource(drawableId);
    }

    public void attach(final MoniterHeightListener moniterHeightListener) {
        initView();
        int bottomDeleteHeight = FloatViewUtils.getBottomDeleteHeight(getContext());
        int bottomDeleteWidth = FloatViewUtils.getBottomDeleteWidth(getContext());
        ViewGroup.LayoutParams layoutParams = this.llContainer.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = bottomDeleteWidth;
        }
        this.moniterHeightListener = moniterHeightListener;
        this.floatLayoutParams.height = bottomDeleteHeight;
        this.floatLayoutParams.width = this.floatViewConfig.regionWidth;
        updateY(this.floatViewConfig.regionHeight - bottomDeleteHeight);
        updateX(0);
        addView();
        post(new Runnable() { // from class: com.sy37sdk.account.floatview.BottomDeleteView.2
            @Override // java.lang.Runnable
            public void run() {
                MoniterHeightListener moniterHeightListener2 = moniterHeightListener;
                if (moniterHeightListener2 != null) {
                    moniterHeightListener2.onChange(BottomDeleteView.this.floatLayoutParams.y);
                }
            }
        });
    }

    public void showBottomDeleteView() {
        if (isAttachedToWindow()) {
            ViewUtils.show(this);
            update();
        }
    }

    public void hideBottomDeleteView() {
        if (isAttachedToWindow()) {
            ViewUtils.hidden(this);
            update();
        }
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void release() {
        super.release();
        ActivityLifeCycleUtils.getInstance().unRegisterActivityListener(this.appVisibilityCallback);
    }

    public void removeBottomDeleteView() {
        release();
    }

    public void updateView() {
        initView();
        int bottomDeleteHeight = FloatViewUtils.getBottomDeleteHeight(getContext());
        this.floatLayoutParams.width = this.floatViewConfig.regionWidth;
        this.floatLayoutParams.height = bottomDeleteHeight;
        int i = this.floatViewConfig.regionHeight - bottomDeleteHeight;
        updateY(i);
        updateX(0);
        update();
        MoniterHeightListener moniterHeightListener = this.moniterHeightListener;
        if (moniterHeightListener != null) {
            moniterHeightListener.onChange(i);
        }
    }

    private void initView() {
        FloatViewUtils.FloatViewConfig floatViewConfig = new FloatViewUtils.FloatViewConfig(this.mContext);
        this.floatViewConfig = floatViewConfig;
        LogUtil.i(this.TAG, floatViewConfig.toString());
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase, com.sy37sdk.account.floatview.SystemUiCheckUtils.onSystemUiChangedCallback
    public void onSystemUiChanged(FloatViewUtils.FloatViewConfig floatViewConfig) {
        updateY(floatViewConfig.regionHeight);
        update();
    }
}
