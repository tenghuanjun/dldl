package com.sy37sdk.account.view.base.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ClearEditText extends EditText implements View.OnFocusChangeListener {
    private Drawable drawable;
    private boolean focused;
    private Context mContext;

    public ClearEditText(Context context) {
        super(context);
        this.focused = false;
        init();
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.focused = false;
        init(context, attributeSet);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.focused = false;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        this.drawable = context.getResources().getDrawable(SqResUtils.getDrawableId(context, "sysq_pop_delete"));
        init();
    }

    private void init() {
        setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcher() { // from class: com.sy37sdk.account.view.base.view.ClearEditText.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ClearEditText.this.setDrawable();
            }
        });
    }

    public void setClearDrawableRes(String str) {
        this.drawable = this.mContext.getResources().getDrawable(SqResUtils.getDrawableId(this.mContext, str));
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        this.focused = z;
        if (z && length() > 0) {
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.drawable, (Drawable) null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        this.focused = z;
        if (z && length() > 0) {
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.drawable, (Drawable) null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDrawable() {
        if (length() <= 0 || !this.focused) {
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            this.drawable.setBounds(0, 0, DisplayUtil.dip2px(this.mContext, 15.0f), DisplayUtil.dip2px(this.mContext, 15.0f));
            setCompoundDrawables(null, null, this.drawable, null);
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.drawable != null) {
            if (motionEvent.getAction() == 1) {
                int x = (int) motionEvent.getX();
                boolean z = x > getWidth() - getTotalPaddingRight() && x < getWidth() - getPaddingRight();
                int iHeight = this.drawable.getBounds().height();
                int y = (int) motionEvent.getY();
                int height = (getHeight() - iHeight) / 2;
                boolean z2 = y > height && y < height + iHeight;
                if (z && z2) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
