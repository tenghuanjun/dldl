package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGmRoleBindingImpl extends ItemGmRoleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeFrameLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    public ItemGmRoleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemGmRoleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        ShapeFrameLayout shapeFrameLayout = (ShapeFrameLayout) bindings[0];
        this.mboundView0 = shapeFrameLayout;
        shapeFrameLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((GMRoleBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGmRoleBinding
    public void setData(GMRoleBean Data) {
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
        return onChangeData((GMRoleBean) object, fieldId);
    }

    private boolean onChangeData(GMRoleBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String accountName;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GMRoleBean gMRoleBean = this.mData;
        long j2 = 7 & j;
        String str4 = null;
        boolean selected = false;
        if (j2 != 0) {
            if ((j & 5) != 0) {
                if (gMRoleBean != null) {
                    String showText = gMRoleBean.getShowText();
                    String roleId = gMRoleBean.getRoleId();
                    accountName = gMRoleBean.getAccountName();
                    str4 = roleId;
                    str3 = showText;
                } else {
                    str3 = null;
                    accountName = null;
                }
                String str5 = "角色ID：" + str4;
                str2 = "小号：" + accountName;
                str4 = str3;
                str = str5;
            } else {
                str = null;
                str2 = null;
            }
            if (gMRoleBean != null) {
                selected = gMRoleBean.getSelected();
            }
        } else {
            str = null;
            str2 = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.mboundView0, selected);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str4);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
    }
}
