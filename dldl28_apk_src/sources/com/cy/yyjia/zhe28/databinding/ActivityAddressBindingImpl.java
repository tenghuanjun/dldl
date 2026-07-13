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
import com.cy.yyjia.zhe28.domain.AddressResult;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityAddressBindingImpl extends ActivityAddressBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged;
    private final EditText mboundView2;
    private InverseBindingListener mboundView2androidTextAttrChanged;
    private final TextView mboundView3;
    private final EditText mboundView4;
    private InverseBindingListener mboundView4androidTextAttrChanged;

    public ActivityAddressBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityAddressBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mboundView1androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAddressBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAddressBindingImpl.this.mboundView1);
                AddressResult.DataBean dataBean = ActivityAddressBindingImpl.this.mData;
                if (dataBean != null) {
                    dataBean.setName(textString);
                }
            }
        };
        this.mboundView2androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAddressBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAddressBindingImpl.this.mboundView2);
                AddressResult.DataBean dataBean = ActivityAddressBindingImpl.this.mData;
                if (dataBean != null) {
                    dataBean.setTelephone(textString);
                }
            }
        };
        this.mboundView4androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityAddressBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityAddressBindingImpl.this.mboundView4);
                AddressResult.DataBean dataBean = ActivityAddressBindingImpl.this.mData;
                if (dataBean != null) {
                    dataBean.setAddress(textString);
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
        EditText editText2 = (EditText) bindings[2];
        this.mboundView2 = editText2;
        editText2.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        EditText editText3 = (EditText) bindings[4];
        this.mboundView4 = editText3;
        editText3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (23 != variableId) {
            return false;
        }
        setData((AddressResult.DataBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityAddressBinding
    public void setData(AddressResult.DataBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((AddressResult.DataBean) object, fieldId);
    }

    private boolean onChangeData(AddressResult.DataBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 61) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 110) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        if (fieldId == 5) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 3) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String address;
        String telephone;
        String area;
        String name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AddressResult.DataBean dataBean = this.mData;
        if ((63 & j) != 0) {
            telephone = ((j & 37) == 0 || dataBean == null) ? null : dataBean.getTelephone();
            area = ((j & 41) == 0 || dataBean == null) ? null : dataBean.getArea();
            name = ((j & 35) == 0 || dataBean == null) ? null : dataBean.getName();
            address = ((j & 49) == 0 || dataBean == null) ? null : dataBean.getAddress();
        } else {
            address = null;
            telephone = null;
            area = null;
            name = null;
        }
        if ((35 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, name);
        }
        if ((32 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, null, null, null, this.mboundView1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView2, null, null, null, this.mboundView2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.mboundView4, null, null, null, this.mboundView4androidTextAttrChanged);
        }
        if ((j & 37) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, telephone);
        }
        if ((j & 41) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, area);
        }
        if ((j & 49) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, address);
        }
    }
}
