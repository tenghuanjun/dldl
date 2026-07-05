package com.duowan.auk.ui;

import android.app.Activity;
import android.view.View;
import com.duowan.auk.ui.utils.ResourceGet;
import com.duowan.auk.util.L;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkView<T extends View> {
    private boolean mHasFound;
    private T mHolderView;
    private int mId;
    private String mIdName;
    private WeakReference<Object> mRoot;

    public ArkView(Object obj, int i) {
        this.mRoot = null;
        this.mHasFound = false;
        this.mRoot = new WeakReference<>(obj);
        this.mId = i;
    }

    public ArkView(Object obj, String str) {
        this(obj, -1);
        this.mIdName = str;
    }

    public T get() {
        if (!this.mHasFound) {
            this.mHasFound = true;
            try {
                this.mHolderView = (T) findView();
            } catch (Exception e) {
                L.error(this, e);
            }
        }
        return this.mHolderView;
    }

    public boolean exist() {
        return get() != null;
    }

    public int getId() {
        return get().getId();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        get().setOnClickListener(onClickListener);
    }

    public void setVisibility(int i) {
        get().setVisibility(i);
    }

    public int getVisibility() {
        return get().getVisibility();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        get().setPadding(i, i2, i3, i4);
    }

    public void setSelected(boolean z) {
        get().setSelected(z);
    }

    public boolean isSelected() {
        return get().isSelected();
    }

    public void setBackgroundColor(int i) {
        get().setBackgroundColor(i);
    }

    public void setLongClickable(boolean z) {
        get().setLongClickable(z);
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        get().setOnTouchListener(onTouchListener);
    }

    public void setEnabled(boolean z) {
        get().setEnabled(z);
    }

    public boolean isEnabled() {
        return get().isEnabled();
    }

    public void setClickable(boolean z) {
        get().setClickable(z);
    }

    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        get().setOnFocusChangeListener(onFocusChangeListener);
    }

    public void requestFocus() {
        get().requestFocus();
    }

    private View findView() {
        Object obj = this.mRoot.get();
        if (obj == null) {
            return null;
        }
        if (obj instanceof View) {
            View view = (View) obj;
            if (this.mId == -1) {
                this.mId = ResourceGet.idIdByName(view.getContext(), this.mIdName);
            }
            return view.findViewById(this.mId);
        }
        if (!(obj instanceof Activity)) {
            return null;
        }
        Activity activity = (Activity) obj;
        if (this.mId == -1) {
            this.mId = ResourceGet.idIdByName(activity, this.mIdName);
        }
        return activity.findViewById(this.mId);
    }
}
