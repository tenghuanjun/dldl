package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.CollectionBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeC3BindingImpl extends ItemHomeC3Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemHomeC3BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemHomeC3BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[2]);
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
        setData((CollectionBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeC3Binding
    public void setData(CollectionBean Data) {
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
        List<GameBean> list;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CollectionBean collectionBean = this.mData;
        long j2 = j & 3;
        String name = null;
        boolean z2 = false;
        if (j2 != 0) {
            if (collectionBean != null) {
                List<GameBean> game_list = collectionBean.getGame_list();
                name = collectionBean.getName();
                list = game_list;
            } else {
                list = null;
            }
            z = name == null;
            if (j2 != 0) {
                j = z ? j | 8 : j | 4;
            }
        } else {
            list = null;
            z = false;
        }
        boolean zIsEmpty = ((4 & j) == 0 || name == null) ? false : name.isEmpty();
        long j3 = j & 3;
        if (j3 != 0) {
            z2 = z ? true : zIsEmpty;
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, name);
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            DataBindingHelper.setRvData(this.rv, list);
        }
    }
}
