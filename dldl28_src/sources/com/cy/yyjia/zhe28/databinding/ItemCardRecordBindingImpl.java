package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.RecordBean;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ItemCardRecordBindingImpl extends ItemCardRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemCardRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemCardRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
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
        setData((RecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemCardRecordBinding
    public void setData(RecordBean Data) {
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
        String str3;
        String str4;
        String money;
        String payName;
        String moduleName;
        String create_time;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        RecordBean recordBean = this.mData;
        long j2 = j & 3;
        String end_time = null;
        if (j2 != 0) {
            if (recordBean != null) {
                end_time = recordBean.getEnd_time();
                money = recordBean.getMoney();
                payName = recordBean.getPayName();
                moduleName = recordBean.getModuleName();
                create_time = recordBean.getCreate_time();
            } else {
                money = null;
                payName = null;
                moduleName = null;
                create_time = null;
            }
            String str5 = "有效期至：" + end_time;
            str = "￥" + money;
            str2 = "充值类型：" + payName;
            str4 = "购买时间：" + create_time;
            end_time = moduleName;
            str3 = str5;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, end_time);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
            TextViewBindingAdapter.setText(this.mboundView4, str4);
            TextViewBindingAdapter.setText(this.mboundView5, str3);
        }
    }
}
