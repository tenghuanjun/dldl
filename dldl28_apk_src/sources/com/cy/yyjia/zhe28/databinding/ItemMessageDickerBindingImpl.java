package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.DickerMessageBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemMessageDickerBindingImpl extends ItemMessageDickerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final View mboundView1;
    private final TextView mboundView4;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemMessageDickerBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemMessageDickerBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[2], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        View view = (View) bindings[1];
        this.mboundView1 = view;
        view.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        this.userIcon.setTag(null);
        this.userName.setTag(null);
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
        setData((DickerMessageBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemMessageDickerBinding
    public void setData(DickerMessageBean Data) {
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
        String productName;
        String game_icon;
        String createTime;
        int isread;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DickerMessageBean dickerMessageBean = this.mData;
        long j2 = j & 3;
        boolean z = false;
        String message = null;
        if (j2 != 0) {
            if (dickerMessageBean != null) {
                message = dickerMessageBean.getMessage();
                isread = dickerMessageBean.getIsread();
                productName = dickerMessageBean.getProductName();
                game_icon = dickerMessageBean.getGame_icon();
                createTime = dickerMessageBean.getCreateTime();
            } else {
                productName = null;
                game_icon = null;
                createTime = null;
                isread = 0;
            }
            if (isread == 1) {
                z = true;
            }
        } else {
            productName = null;
            game_icon = null;
            createTime = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
            TextViewBindingAdapter.setText(this.mboundView4, message);
            TextViewBindingAdapter.setText(this.mboundView5, createTime);
            DataBindingHelper.setGameIcon(this.userIcon, game_icon);
            TextViewBindingAdapter.setText(this.userName, productName);
        }
    }
}
