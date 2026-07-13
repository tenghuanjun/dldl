package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.hjq.shape.view.ShapeImageView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGameReport2BindingImpl extends ActivityGameReport2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final ShapeImageView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ActivityGameReport2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ActivityGameReport2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityGameReport2BindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityGameReport2BindingImpl.this.mboundView1);
                String str = ActivityGameReport2BindingImpl.this.mGame;
                ActivityGameReport2BindingImpl activityGameReport2BindingImpl = ActivityGameReport2BindingImpl.this;
                if (activityGameReport2BindingImpl != null) {
                    activityGameReport2BindingImpl.setGame(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        EditText editText = (EditText) bindings[1];
        this.mboundView1 = editText;
        editText.setTag(null);
        ShapeImageView shapeImageView = (ShapeImageView) bindings[2];
        this.mboundView2 = shapeImageView;
        shapeImageView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (35 != variableId) {
            return false;
        }
        setGame((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGameReport2Binding
    public void setGame(String Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mGame;
        long j2 = 3 & j;
        boolean z = j2 != 0 ? !TextUtils.isEmpty(str) : false;
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
            this.mboundView2.setEnabled(z);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
        }
    }
}
