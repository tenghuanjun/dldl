package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.ProblemBean;
import com.cy.yyjia.zhe28.domain.ServiceTypeBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemServiceBindingImpl extends ItemServiceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;

    public ItemServiceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemServiceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (RecyclerView) bindings[2]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        this.rv.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((ServiceTypeBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemServiceBinding
    public void setData(ServiceTypeBean Data) {
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
        return onChangeData((ServiceTypeBean) object, fieldId);
    }

    private boolean onChangeData(ServiceTypeBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 94) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId != 78) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        List<ProblemBean> list;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ServiceTypeBean serviceTypeBean = this.mData;
        String str = null;
        problem = null;
        List<ProblemBean> problem = null;
        if ((15 & j) != 0) {
            if ((j & 11) != 0) {
                selected = serviceTypeBean != null ? serviceTypeBean.getSelected() : false;
                z = !selected;
            } else {
                z = false;
            }
            String name = ((j & 9) == 0 || serviceTypeBean == null) ? null : serviceTypeBean.getName();
            if ((j & 13) != 0 && serviceTypeBean != null) {
                problem = serviceTypeBean.getProblem();
            }
            list = problem;
            str = name;
        } else {
            list = null;
            z = false;
        }
        if ((11 & j) != 0) {
            DataBindingHelper.setSelected(this.mboundView1, selected);
            DataBindingHelper.setViewGone(this.rv, z);
        }
        if ((j & 9) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if ((j & 13) != 0) {
            DataBindingHelper.setRvData(this.rv, list);
        }
    }
}
