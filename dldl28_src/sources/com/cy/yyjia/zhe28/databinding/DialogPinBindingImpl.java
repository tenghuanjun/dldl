package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogPinBindingImpl extends DialogPinBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener editandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_tag3, 8);
        sparseIntArray.put(R.id.tv_go, 9);
    }

    public DialogPinBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private DialogPinBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (EditText) bindings[7], (ShapeTextView) bindings[9], (ShapeTextView) bindings[3], (ShapeTextView) bindings[4], (ShapeTextView) bindings[5], (ShapeTextView) bindings[6], (TextView) bindings[2], (TextView) bindings[8]);
        this.editandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.DialogPinBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogPinBindingImpl.this.edit);
                String str = DialogPinBindingImpl.this.mData;
                DialogPinBindingImpl dialogPinBindingImpl = DialogPinBindingImpl.this;
                if (dialogPinBindingImpl != null) {
                    dialogPinBindingImpl.setData(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.edit.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        this.tvPassword1.setTag(null);
        this.tvPassword2.setTag(null);
        this.tvPassword3.setTag(null);
        this.tvPassword4.setTag(null);
        this.tvTag1.setTag(null);
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
        if (113 == variableId) {
            setTip((String) variable);
        } else if (23 == variableId) {
            setData((String) variable);
        } else {
            if (114 != variableId) {
                return false;
            }
            setTitle((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogPinBinding
    public void setTip(String Tip) {
        this.mTip = Tip;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(113);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogPinBinding
    public void setData(String Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogPinBinding
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
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mTip;
        String str2 = this.mData;
        String str3 = this.mTitle;
        long j2 = j & 10;
        if (j2 != 0) {
            int length = str2 != null ? str2.length() : 0;
            z2 = length > 1;
            z3 = length > 3;
            z4 = length > 2;
            z = length > 0;
            if (j2 != 0) {
                j = z2 ? j | 32 : j | 16;
            }
            if ((j & 10) != 0) {
                j = z3 ? j | 128 : j | 64;
            }
            if ((j & 10) != 0) {
                j = z4 ? j | 512 : j | 256;
            }
            if ((j & 10) != 0) {
                j = z ? j | 2048 : j | 1024;
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        long j3 = j & 12;
        String strSubstring = ((j & 512) == 0 || str2 == null) ? null : str2.substring(2, 3);
        String strSubstring2 = ((j & 32) == 0 || str2 == null) ? null : str2.substring(1, 2);
        String strSubstring3 = ((j & 2048) == 0 || str2 == null) ? null : str2.substring(0, 1);
        String strSubstring4 = ((j & 128) == 0 || str2 == null) ? null : str2.substring(3, 4);
        long j4 = j & 10;
        if (j4 != 0) {
            if (!z2) {
                strSubstring2 = "";
            }
            if (!z3) {
                strSubstring4 = "";
            }
            if (!z4) {
                strSubstring = "";
            }
            if (!z) {
                strSubstring3 = "";
            }
        } else {
            strSubstring = null;
            strSubstring3 = null;
            strSubstring4 = null;
            strSubstring2 = null;
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.edit, str2);
            TextViewBindingAdapter.setText(this.tvPassword1, strSubstring3);
            TextViewBindingAdapter.setText(this.tvPassword2, strSubstring2);
            TextViewBindingAdapter.setText(this.tvPassword3, strSubstring);
            TextViewBindingAdapter.setText(this.tvPassword4, strSubstring4);
        }
        if ((8 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.edit, null, null, null, this.editandroidTextAttrChanged);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str3);
        }
        if ((j & 9) != 0) {
            TextViewBindingAdapter.setText(this.tvTag1, str);
        }
    }
}
