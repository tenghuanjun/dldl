package com.cy.yyjia.zhe28.databinding;

import android.text.Html;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DialogGiftCodeBindingImpl extends DialogGiftCodeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_copy, 3);
    }

    public DialogGiftCodeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private DialogGiftCodeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
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
        if (45 == variableId) {
            setIntro((String) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogGiftCodeBinding
    public void setIntro(String Intro) {
        this.mIntro = Intro;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(45);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogGiftCodeBinding
    public void setData(String Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean zIsEmpty;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str2 = this.mIntro;
        String str3 = this.mData;
        long j2 = j & 5;
        if (j2 != 0) {
            zIsEmpty = TextUtils.isEmpty(str2);
            if (j2 != 0) {
                j = zIsEmpty ? j | 16 : j | 8;
            }
        } else {
            zIsEmpty = false;
        }
        long j3 = 6 & j;
        String str4 = null;
        if ((8 & j) != 0) {
            str = "使用说明：" + ((Object) Html.fromHtml(str2));
        } else {
            str = null;
        }
        long j4 = j & 5;
        if (j4 != 0) {
            str4 = zIsEmpty ? "" : str;
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str3);
        }
        if (j4 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, zIsEmpty);
            TextViewBindingAdapter.setText(this.mboundView2, str4);
        }
    }
}
