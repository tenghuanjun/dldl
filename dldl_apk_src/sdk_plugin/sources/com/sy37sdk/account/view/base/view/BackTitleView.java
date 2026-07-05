package com.sy37sdk.account.view.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.msdk.config.MultiConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BackTitleView extends RelativeLayout {
    private View backView;
    private int clickCount;
    private View closeView;
    private ImageView ivLogo;
    private long lastClickTime;
    private Context mContext;
    private OnClickTitleViewListener mOnClickTitleViewListener;
    private View rootView;
    private TextView tvTitle;

    public interface OnClickTitleViewListener {
        void clickBack();

        void clickClose();
    }

    static /* synthetic */ int access$208(BackTitleView backTitleView) {
        int i = backTitleView.clickCount;
        backTitleView.clickCount = i + 1;
        return i;
    }

    public BackTitleView(Context context) {
        this(context, null);
    }

    public BackTitleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BackTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.clickCount = 0;
        this.lastClickTime = 0L;
        this.mContext = context;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Context context = this.mContext;
        this.rootView = View.inflate(context, SqResUtils.getIdByName("sysq_base_title_layout", "layout", context), this);
        initView();
        initEvent();
    }

    private void initView() {
        this.backView = this.rootView.findViewById(SqResUtils.getIdByName("view_back", SqTrackCommonKey.id, this.mContext));
        this.closeView = this.rootView.findViewById(SqResUtils.getIdByName("view_close", SqTrackCommonKey.id, this.mContext));
        this.ivLogo = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_logo", SqTrackCommonKey.id, this.mContext));
        this.ivLogo.setImageResource(SqResUtils.getIdByName(MultiConfigManager.getInstance().isSqUnion() ? "sysq_ic_logo_union" : "sysq_ic_logo", "drawable", this.mContext));
        this.tvTitle = (TextView) this.rootView.findViewById(SqResUtils.getIdByName("tv_title", SqTrackCommonKey.id, this.mContext));
    }

    private void initEvent() {
        this.backView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.base.view.BackTitleView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BackTitleView.this.mOnClickTitleViewListener != null) {
                    BackTitleView.this.mOnClickTitleViewListener.clickBack();
                }
            }
        });
        this.closeView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.base.view.BackTitleView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BackTitleView.this.mOnClickTitleViewListener != null) {
                    BackTitleView.this.mOnClickTitleViewListener.clickClose();
                }
            }
        });
        this.ivLogo.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.base.view.BackTitleView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (jUptimeMillis - BackTitleView.this.lastClickTime < 500) {
                    return;
                }
                BackTitleView.this.lastClickTime = jUptimeMillis;
                BackTitleView.access$208(BackTitleView.this);
                if (BackTitleView.this.clickCount < 5) {
                    return;
                }
                if (BackTitleView.this.clickCount == 5) {
                    BackTitleView.this.clickCount = 0;
                }
                Context context = BackTitleView.this.getContext();
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    if (activity.isFinishing() || activity.isDestroyed()) {
                        return;
                    }
                    DiagnosticAssistant.show(activity);
                }
            }
        });
    }

    public void showTitle() {
        this.tvTitle.setVisibility(0);
        this.ivLogo.setVisibility(8);
    }

    public void showLogo() {
        this.tvTitle.setVisibility(8);
        this.ivLogo.setVisibility(0);
    }

    public void showBackView(boolean z) {
        LogUtil.i("showBackView " + z);
        this.backView.setVisibility(z ? 0 : 8);
    }

    public void setTitle(String str) {
        this.tvTitle.setText(str);
    }

    public void setOnClickTitleViewListener(OnClickTitleViewListener onClickTitleViewListener) {
        this.mOnClickTitleViewListener = onClickTitleViewListener;
    }

    public ImageView getCloseImageView() {
        return (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("btn_close", SqTrackCommonKey.id, this.mContext));
    }

    public ImageView getBackImageView() {
        return (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_back_icon", SqTrackCommonKey.id, this.mContext));
    }
}
