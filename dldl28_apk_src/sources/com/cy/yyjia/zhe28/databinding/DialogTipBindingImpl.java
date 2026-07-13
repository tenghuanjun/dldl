package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogTipBindingImpl extends DialogTipBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeConstraintLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public DialogTipBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private DialogTipBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[3]);
        this.mDirtyFlags = -1L;
        ShapeConstraintLayout shapeConstraintLayout = (ShapeConstraintLayout) bindings[0];
        this.mboundView0 = shapeConstraintLayout;
        shapeConstraintLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        this.tvGo.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (9 == variableId) {
            setBtn((String) variable);
        } else if (113 == variableId) {
            setTip((String) variable);
        } else {
            if (114 != variableId) {
                return false;
            }
            setTitle((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogTipBinding
    public void setBtn(String Btn) {
        this.mBtn = Btn;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogTipBinding
    public void setTip(String Tip) {
        this.mTip = Tip;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(113);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogTipBinding
    public void setTitle(String Title) {
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mBtn;
        String str2 = this.mTip;
        String str3 = this.mTitle;
        long j2 = 9 & j;
        long j3 = 10 & j;
        if ((j & 12) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str3);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.tvGo, str);
        }
    }
}
