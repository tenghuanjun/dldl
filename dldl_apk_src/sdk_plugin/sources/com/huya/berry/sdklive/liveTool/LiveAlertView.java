package com.huya.berry.sdklive.liveTool;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveAlertView extends FrameLayout {
    public static String TAG = LiveAlertView.class.getSimpleName();
    private TextView mButtonNegative;
    private TextView mButtonPositive;
    private boolean mCancelable;
    private TextView mMessage;
    private OnButtonClickListener mOnButtonClickListener;
    private TextView mTitle;

    public interface OnButtonClickListener {
        void onNegativeButtonClick(View view);

        void onPositiveButtonClick(View view);
    }

    public LiveAlertView(Context context) {
        super(context);
        this.mCancelable = false;
        initLiveAlertView(context);
    }

    public LiveAlertView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCancelable = false;
        initLiveAlertView(context);
    }

    public LiveAlertView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCancelable = false;
        initLiveAlertView(context);
    }

    private void initLiveAlertView(Context context) {
        setBackgroundResource(ResourceUtil.getColorResIDByName(SqR.color.hyberry_black_transparent_60));
        View viewInflate = LayoutInflater.from(context).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_live_alert_view), (ViewGroup) this, true);
        this.mTitle = (TextView) viewInflate.findViewById(ResourceUtil.getIdResIDByName("title"));
        this.mMessage = (TextView) viewInflate.findViewById(ResourceUtil.getIdResIDByName("message"));
        TextView textView = (TextView) viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.button_neutral));
        this.mButtonNegative = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveAlertView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LiveAlertView.this.mOnButtonClickListener != null) {
                    LiveAlertView.this.mOnButtonClickListener.onNegativeButtonClick(LiveAlertView.this.mButtonNegative);
                }
            }
        });
        TextView textView2 = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.button_positive));
        this.mButtonPositive = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.liveTool.LiveAlertView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LiveAlertView.this.mOnButtonClickListener != null) {
                    LiveAlertView.this.mOnButtonClickListener.onPositiveButtonClick(LiveAlertView.this.mButtonPositive);
                }
            }
        });
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTitle.setVisibility(8);
        } else {
            this.mTitle.setVisibility(0);
            this.mTitle.setText(str);
        }
    }

    public void setMessage(String str) {
        this.mMessage.setText(str);
    }

    public void setPositiveTitle(String str) {
        if (str == null || str.isEmpty()) {
            this.mButtonPositive.setVisibility(8);
        } else {
            this.mButtonPositive.setVisibility(0);
            this.mButtonPositive.setText(str);
        }
    }

    public void setNegativeTitle(String str) {
        if (str == null || str.isEmpty()) {
            this.mButtonNegative.setVisibility(8);
        } else {
            this.mButtonNegative.setVisibility(0);
            this.mButtonNegative.setText(str);
        }
    }

    public void setCancelable(boolean z) {
        this.mCancelable = z;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        OnButtonClickListener onButtonClickListener;
        if (motionEvent.getAction() == 0) {
            if (!this.mCancelable || (onButtonClickListener = this.mOnButtonClickListener) == null) {
                return true;
            }
            onButtonClickListener.onNegativeButtonClick(this.mButtonNegative);
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }
}
