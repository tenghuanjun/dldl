package com.duowan.live.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.duowan.auk.util.L;
import com.huya.component.base.framework.R;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveAlert extends Dialog {
    private View mButtonDividerA;
    private View mButtonDividerB;
    private View mButtonDividerC;
    private TextView mButtonNegative;
    private TextView mButtonNeutral;
    private TextView mButtonPositive;
    private FrameLayout mCustomView;
    private DialogInterface.OnClickListener mListener;
    private TextView mMessage;
    private View mMessageDivider;
    private TextView mTitle;
    private View mTitleDivider;

    private LiveAlert(Context context) {
        this(context, 0, false);
    }

    private LiveAlert(Context context, int i, boolean z) {
        super(context, i);
        setContentView(z ? R.layout.live_alert_portrait : R.layout.live_alert);
        initView();
    }

    private void initView() {
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mTitleDivider = findViewById(R.id.title_divider);
        this.mMessage = (TextView) findViewById(R.id.message);
        this.mMessageDivider = findViewById(R.id.message_divider);
        this.mButtonNegative = (TextView) findViewById(R.id.button_negative);
        this.mButtonDividerA = findViewById(R.id.button_divider_a);
        this.mButtonNeutral = (TextView) findViewById(R.id.button_neutral);
        this.mButtonDividerB = findViewById(R.id.button_divider_b);
        this.mButtonPositive = (TextView) findViewById(R.id.button_positive);
        this.mButtonDividerC = findViewById(R.id.button_divider_c);
        this.mCustomView = (FrameLayout) findViewById(R.id.custom_view);
        this.mMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.mMessage.setLongClickable(false);
        this.mButtonNegative.setOnClickListener(new View.OnClickListener() { // from class: com.duowan.live.common.widget.LiveAlert.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveAlert.this.dismiss();
                if (LiveAlert.this.mListener != null) {
                    LiveAlert.this.mListener.onClick(LiveAlert.this, -2);
                }
            }
        });
        this.mButtonNeutral.setOnClickListener(new View.OnClickListener() { // from class: com.duowan.live.common.widget.LiveAlert.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveAlert.this.dismiss();
                if (LiveAlert.this.mListener != null) {
                    LiveAlert.this.mListener.onClick(LiveAlert.this, -3);
                }
            }
        });
        this.mButtonPositive.setOnClickListener(new View.OnClickListener() { // from class: com.duowan.live.common.widget.LiveAlert.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveAlert.this.dismiss();
                if (LiveAlert.this.mListener != null) {
                    LiveAlert.this.mListener.onClick(LiveAlert.this, -1);
                }
            }
        });
    }

    public void setPositiveBtnColor(int i) {
        this.mButtonPositive.setTextColor(i);
    }

    public void setNegativeBtnColor(int i) {
        this.mButtonNegative.setTextColor(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.mTitle.setText(charSequence);
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage.setText(charSequence);
    }

    public void setNegative(CharSequence charSequence) {
        this.mButtonNegative.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mButtonNegative.setText(charSequence);
    }

    public void setNeutral(CharSequence charSequence) {
        this.mButtonNeutral.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mButtonNeutral.setText(charSequence);
    }

    public void setPositive(CharSequence charSequence) {
        this.mButtonPositive.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mButtonPositive.setText(charSequence);
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    public void setCustomView(View view) {
        this.mCustomView.removeAllViews();
        this.mCustomView.addView(view, -1, -1);
        this.mCustomView.setVisibility(0);
        this.mMessageDivider.setVisibility(0);
    }

    public static class Builder {
        private DialogInterface.OnCancelListener mCancelListener;
        private Context mContext;
        private View mCustomView;
        private DialogInterface.OnClickListener mListener;
        private CharSequence mMessage;
        private CharSequence mNegative;
        private CharSequence mNeutral;
        private CharSequence mPositive;
        private CharSequence mTitle;
        private boolean mCancelable = true;
        private boolean mPortrait = false;
        private boolean mAutoLink = true;
        private int textColor = -1;
        private int textNegativeColor = -1;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder title(int i) {
            title(this.mContext.getString(i));
            return this;
        }

        public Builder title(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder message(int i) {
            message(this.mContext.getString(i));
            return this;
        }

        public Builder message(CharSequence charSequence) {
            this.mMessage = charSequence;
            return this;
        }

        public Builder negative(int i) {
            negative(this.mContext.getString(i));
            return this;
        }

        public Builder negative(CharSequence charSequence) {
            this.mNegative = charSequence;
            return this;
        }

        public Builder neutral(int i) {
            neutral(this.mContext.getString(i));
            return this;
        }

        public Builder neutral(CharSequence charSequence) {
            this.mNeutral = charSequence;
            return this;
        }

        public Builder positive(int i) {
            positive(this.mContext.getString(i));
            return this;
        }

        public Builder positive(CharSequence charSequence) {
            this.mPositive = charSequence;
            return this;
        }

        public Builder onClickListener(DialogInterface.OnClickListener onClickListener) {
            this.mListener = onClickListener;
            return this;
        }

        public Builder onCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.mCancelListener = onCancelListener;
            return this;
        }

        public Builder customView(View view) {
            this.mCustomView = view;
            return this;
        }

        public Builder cancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public Builder positiveBtnColor(int i) {
            this.textColor = i;
            return this;
        }

        public Builder negativeBtnColor(int i) {
            this.textNegativeColor = i;
            return this;
        }

        public Builder portrait(boolean z) {
            this.mPortrait = z;
            return this;
        }

        public LiveAlert create() {
            LiveAlert liveAlert = new LiveAlert(this.mContext, R.style.Theme_Dialog_Kiwi, this.mPortrait);
            CharSequence charSequence = this.mTitle;
            if (charSequence == null) {
                liveAlert.mTitle.setVisibility(8);
                liveAlert.mTitleDivider.setVisibility(8);
                if (this.mMessage != null) {
                    liveAlert.mMessage.setTextColor(this.mContext.getResources().getColor(R.color.gray22));
                }
            } else {
                liveAlert.setTitle(charSequence);
            }
            if (this.mMessage == null) {
                liveAlert.mMessage.setVisibility(8);
                liveAlert.mMessageDivider.setVisibility(8);
            } else {
                if (this.mAutoLink) {
                    liveAlert.mMessage.setMovementMethod(LinkMovementMethod.getInstance());
                }
                liveAlert.setMessage(this.mMessage);
            }
            CharSequence charSequence2 = this.mPositive;
            if (charSequence2 == null) {
                liveAlert.mButtonPositive.setVisibility(8);
                liveAlert.mButtonDividerC.setVisibility(8);
            } else {
                liveAlert.setPositive(charSequence2);
            }
            CharSequence charSequence3 = this.mNegative;
            if (charSequence3 == null) {
                liveAlert.mButtonNegative.setVisibility(8);
                liveAlert.mButtonDividerB.setVisibility(8);
            } else {
                liveAlert.setNegative(charSequence3);
            }
            CharSequence charSequence4 = this.mNeutral;
            if (charSequence4 == null) {
                liveAlert.mButtonNeutral.setVisibility(8);
                liveAlert.mButtonDividerA.setVisibility(8);
            } else {
                liveAlert.setNeutral(charSequence4);
            }
            int i = this.textColor;
            if (i != -1) {
                liveAlert.setPositiveBtnColor(i);
            }
            int i2 = this.textNegativeColor;
            if (i2 != -1) {
                liveAlert.setNegativeBtnColor(i2);
            }
            liveAlert.setOnClickListener(this.mListener);
            liveAlert.setOnCancelListener(this.mCancelListener);
            liveAlert.setCancelable(this.mCancelable);
            View view = this.mCustomView;
            if (view != null) {
                liveAlert.setCustomView(view);
            }
            if (!(this.mContext instanceof Activity)) {
                liveAlert.getWindow().setType(2003);
            }
            return liveAlert;
        }

        public LiveAlert show() {
            LiveAlert liveAlertCreate = create();
            try {
                liveAlertCreate.show();
            } catch (Exception e) {
                L.error("LiveAlert", (Throwable) e);
            }
            return liveAlertCreate;
        }

        public Builder setAutoLink(boolean z) {
            this.mAutoLink = z;
            return this;
        }
    }
}
