package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogVipCouponBuyBindingImpl extends DialogVipCouponBuyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_jian, 3);
        sparseIntArray.put(R.id.iv_jia, 4);
        sparseIntArray.put(R.id.btn, 5);
    }

    public DialogVipCouponBuyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private DialogVipCouponBuyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[5], (ImageView) bindings[4], (ImageView) bindings[3]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (55 == variableId) {
            setMax(((Integer) variable).intValue());
        } else {
            if (111 != variableId) {
                return false;
            }
            setText((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogVipCouponBuyBinding
    public void setMax(int Max) {
        this.mMax = Max;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(55);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogVipCouponBuyBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mMax;
        String str2 = this.mText;
        long j2 = 5 & j;
        if (j2 != 0) {
            str = ("每日限购" + i) + "张";
        } else {
            str = null;
        }
        if ((j & 6) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str2);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
    }
}
