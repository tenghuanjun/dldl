package com.cy.yyjia.zhe28.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes3.dex */
public class Navigation extends ConstraintLayout {
    private int backIcon;
    private int backTint;
    private int backgroundColor;
    private int iconTint;
    private ImageView ivBack;
    private ImageView ivMore;
    private Context mContext;
    private int moreIcon;
    private int moreIconTint;
    private CharSequence moreText;
    private int moreTextColor;
    private ConstraintLayout rlBg;
    private int textColor;
    private String title;
    private TextView tvMore;
    private TextView tvTitle;

    public Navigation(Context context) {
        super(context);
        initView(context, null);
    }

    public Navigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public Navigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public Navigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.wancms_navigation, (ViewGroup) this, true);
        this.rlBg = (ConstraintLayout) findViewById(R.id.bg);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.ivBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.view.Navigation$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0(view);
            }
        });
        this.ivMore = (ImageView) findViewById(R.id.iv_more);
        this.tvTitle = (TextView) findViewById(R.id.navigation_title);
        this.tvMore = (TextView) findViewById(R.id.tv_more);
        TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attrs, R.styleable.Navigation);
        this.backgroundColor = typedArrayObtainStyledAttributes.getColor(2, -1);
        this.title = typedArrayObtainStyledAttributes.getString(10);
        int color = typedArrayObtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
        this.iconTint = color;
        this.backTint = typedArrayObtainStyledAttributes.getColor(1, color);
        this.textColor = typedArrayObtainStyledAttributes.getColor(9, ViewCompat.MEASURED_STATE_MASK);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(8, false);
        this.moreIcon = typedArrayObtainStyledAttributes.getResourceId(4, R.mipmap.ic_game_more);
        this.backIcon = typedArrayObtainStyledAttributes.getResourceId(0, R.mipmap.icon_back);
        this.moreIconTint = typedArrayObtainStyledAttributes.getColor(5, this.iconTint);
        this.moreText = typedArrayObtainStyledAttributes.getText(6);
        this.moreTextColor = typedArrayObtainStyledAttributes.getColor(7, this.textColor);
        typedArrayObtainStyledAttributes.recycle();
        initCommon();
        if (z) {
            initIvMore();
        }
        if (TextUtils.isEmpty(this.moreText)) {
            return;
        }
        initTvMore();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    private void initCommon() {
        this.rlBg.setBackgroundColor(this.backgroundColor);
        this.tvTitle.setText(this.title);
        this.tvTitle.setTextColor(this.textColor);
        this.ivBack.setColorFilter(this.backTint, PorterDuff.Mode.SRC_IN);
        this.ivBack.setImageResource(this.backIcon);
    }

    public void setFinish(final Activity activity) {
        this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.view.Navigation$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                activity.finish();
            }
        });
    }

    public void setIconTint(int iconTint) {
        this.iconTint = iconTint;
        this.moreIconTint = iconTint;
        this.backTint = iconTint;
        this.ivMore.setColorFilter(iconTint, PorterDuff.Mode.SRC_IN);
        this.ivBack.setColorFilter(this.backTint, PorterDuff.Mode.SRC_IN);
    }

    @Override // android.view.View
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.rlBg.setBackgroundColor(backgroundColor);
    }

    public void setMoreIconTint(int moreIconTint) {
        this.moreIconTint = moreIconTint;
        this.ivMore.setColorFilter(moreIconTint, PorterDuff.Mode.SRC_IN);
    }

    public void setBackTint(int backTint) {
        this.backTint = backTint;
        this.ivBack.setColorFilter(backTint, PorterDuff.Mode.SRC_IN);
    }

    public void setMoreTextColor(int moreTextColor) {
        this.moreTextColor = moreTextColor;
        this.tvMore.setTextColor(moreTextColor);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        this.moreTextColor = textColor;
        this.tvTitle.setTextColor(textColor);
        this.tvMore.setTextColor(this.moreTextColor);
    }

    public void setMoreIcon(int moreIcon) {
        this.moreIcon = moreIcon;
        initIvMore();
    }

    public String getMoreText() {
        return this.tvMore.getText().toString().trim();
    }

    public void setMoreText(CharSequence moreText) {
        this.moreText = moreText;
        initTvMore();
    }

    public void setTitle(String title) {
        this.title = title;
        this.tvTitle.setText(title);
    }

    public void showMoreIcon() {
        this.ivMore.setVisibility(0);
    }

    public void showMoreIcon(boolean show) {
        if (show) {
            showMoreIcon();
        } else {
            this.ivMore.setVisibility(8);
        }
    }

    public void setTitle(int title) {
        this.title = this.mContext.getString(title);
        this.tvTitle.setText(title);
    }

    public void setMoreClickListener(View.OnClickListener moreClickListener) {
        this.tvMore.setOnClickListener(moreClickListener);
        this.ivMore.setOnClickListener(moreClickListener);
    }

    public void setBackClickListener(View.OnClickListener backClickListener) {
        this.ivBack.setOnClickListener(backClickListener);
    }

    public ImageView getIvMore() {
        return this.ivMore;
    }

    public TextView getTvMore() {
        return this.tvMore;
    }

    private void initIvMore() {
        this.ivMore.setVisibility(0);
        this.ivMore.setImageResource(this.moreIcon);
        this.ivMore.setColorFilter(this.moreIconTint);
    }

    private void initTvMore() {
        this.tvMore.setVisibility(0);
        this.tvMore.setText(this.moreText);
        this.tvMore.setTextColor(this.moreTextColor);
    }
}
