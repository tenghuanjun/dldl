package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class CountdownView extends AppCompatTextView implements Runnable {
    private static final String TIME_UNIT = "S";
    private boolean big;
    private int mCurrentSecond;
    private boolean mFlag;
    private CharSequence mRecordText;
    private int mTotalSecond;

    public CountdownView(Context context) {
        super(context);
        this.mTotalSecond = 60;
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTotalSecond = 60;
    }

    public CountdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTotalSecond = 60;
    }

    public void setTotalTime(int totalTime) {
        this.mTotalSecond = totalTime;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    public void resetState() {
        this.mFlag = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setClickable(true);
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    public boolean performClick() {
        boolean zPerformClick = super.performClick();
        this.mRecordText = getText();
        setEnabled(false);
        this.mCurrentSecond = this.mTotalSecond;
        post(this);
        return zPerformClick;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = this.mCurrentSecond;
        if (i == 0 || this.mFlag) {
            setText(this.mRecordText);
            setEnabled(true);
            this.mFlag = false;
            return;
        }
        this.mCurrentSecond = i - 1;
        if (this.big) {
            setText("重新发送（" + this.mCurrentSecond + "S）");
        } else {
            setText(this.mCurrentSecond + " S");
        }
        postDelayed(this, 1000L);
    }
}
