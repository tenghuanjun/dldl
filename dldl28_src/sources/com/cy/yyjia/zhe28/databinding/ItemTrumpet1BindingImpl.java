package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.AccountListBean;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemTrumpet1BindingImpl extends ItemTrumpet1Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemTrumpet1BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemTrumpet1BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (TextView) bindings[2], (RecyclerView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        this.gameName.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        this.rv.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((AccountListBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemTrumpet1Binding
    public void setData(AccountListBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        List<DealBean> list;
        int total;
        List<DealBean> account_list;
        String icon;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AccountListBean accountListBean = this.mData;
        long j2 = j & 3;
        String str3 = null;
        if (j2 != 0) {
            if (accountListBean != null) {
                String name = accountListBean.getName();
                account_list = accountListBean.getAccount_list();
                total = accountListBean.getTotal();
                icon = accountListBean.getIcon();
                str3 = name;
            } else {
                total = 0;
                account_list = null;
                icon = null;
            }
            list = account_list;
            str2 = total + "个小号";
            str = str3;
            str3 = icon;
        } else {
            str = null;
            str2 = null;
            list = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.gameIcon, str3);
            TextViewBindingAdapter.setText(this.gameName, str);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
            DataBindingHelper.setRvData(this.rv, list);
        }
    }
}
