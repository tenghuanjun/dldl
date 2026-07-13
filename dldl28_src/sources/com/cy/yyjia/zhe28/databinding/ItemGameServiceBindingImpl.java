package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameServiceBindingImpl extends ItemGameServiceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemGameServiceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemGameServiceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
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
        setData((GameDetailBean.ServiceBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameServiceBinding
    public void setData(GameDetailBean.ServiceBean Data) {
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
        String server;
        String start_time_text;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameDetailBean.ServiceBean serviceBean = this.mData;
        long j2 = j & 3;
        String start_day_text = null;
        if (j2 != 0) {
            if (serviceBean != null) {
                start_day_text = serviceBean.getStart_day_text();
                server = serviceBean.getServer();
                start_time_text = serviceBean.getStart_time_text();
            } else {
                server = null;
                start_time_text = null;
            }
            start_day_text = (start_day_text + StringUtils.SPACE) + start_time_text;
        } else {
            server = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, start_day_text);
            TextViewBindingAdapter.setText(this.mboundView2, server);
        }
    }
}
