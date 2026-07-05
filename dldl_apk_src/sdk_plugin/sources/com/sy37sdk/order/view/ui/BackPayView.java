package com.sy37sdk.order.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BackPayView extends RelativeLayout {
    private ImageView ivLeft;
    private ImageView ivRight;
    private View leftLayout;
    private Context mContext;
    private OnClickBackViewListener mOnClickBackViewListener;
    private View rightLayout;
    private View rootView;
    private TextView tvTitle;

    public interface OnClickBackViewListener {
        void clickLeft();

        void clickRight();
    }

    public BackPayView(Context context) {
        this(context, null);
    }

    public BackPayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BackPayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Context context = this.mContext;
        this.rootView = View.inflate(context, SqResUtils.getIdByName("sysq_pay_dialog_action_bar", "layout", context), this);
        initView();
        initEvent();
    }

    private void initView() {
        this.leftLayout = this.rootView.findViewById(SqResUtils.getIdByName("left_layout", SqTrackCommonKey.id, this.mContext));
        this.rightLayout = this.rootView.findViewById(SqResUtils.getIdByName("right_layout", SqTrackCommonKey.id, this.mContext));
        this.tvTitle = (TextView) this.rootView.findViewById(SqResUtils.getIdByName("tv_title", SqTrackCommonKey.id, this.mContext));
        this.ivLeft = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_left", SqTrackCommonKey.id, this.mContext));
        this.ivRight = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_right", SqTrackCommonKey.id, this.mContext));
    }

    private void initEvent() {
        this.leftLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.view.ui.BackPayView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BackPayView.this.mOnClickBackViewListener != null) {
                    BackPayView.this.mOnClickBackViewListener.clickLeft();
                }
            }
        });
        this.rightLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.view.ui.BackPayView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BackPayView.this.mOnClickBackViewListener != null) {
                    BackPayView.this.mOnClickBackViewListener.clickRight();
                }
            }
        });
    }

    public void setLeftResDrawable(String str) {
        ImageView imageView = this.ivLeft;
        if (imageView != null) {
            imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, str));
        }
    }

    public void setRightResDrawable(String str) {
        ImageView imageView = this.ivRight;
        if (imageView != null) {
            imageView.setImageResource(SqResUtils.getDrawableId(this.mContext, str));
        }
    }

    public void setTitle(String str) {
        TextView textView = this.tvTitle;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setOnClickBackViewListener(OnClickBackViewListener onClickBackViewListener) {
        this.mOnClickBackViewListener = onClickBackViewListener;
    }

    public void setLeftVisible(boolean z) {
        View view = this.leftLayout;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }
}
