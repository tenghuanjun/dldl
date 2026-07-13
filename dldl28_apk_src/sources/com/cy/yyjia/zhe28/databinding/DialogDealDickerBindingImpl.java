package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogDealDickerBindingImpl extends DialogDealDickerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cancel, 2);
        sparseIntArray.put(R.id.sure, 3);
    }

    public DialogDealDickerBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private DialogDealDickerBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[2], (ShapeTextView) bindings[3]);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.DialogDealDickerBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogDealDickerBindingImpl.this.mboundView1);
                String str = DialogDealDickerBindingImpl.this.mPrice;
                DialogDealDickerBindingImpl dialogDealDickerBindingImpl = DialogDealDickerBindingImpl.this;
                if (dialogDealDickerBindingImpl != null) {
                    dialogDealDickerBindingImpl.setPrice(textString);
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
        if (77 != variableId) {
            return false;
        }
        setPrice((String) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogDealDickerBinding
    public void setPrice(String Price) {
        this.mPrice = Price;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(77);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mPrice;
        if ((3 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
        }
    }
}
