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
public class ItemCardRewardRecordBindingImpl extends ItemCardRewardRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemCardRewardRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemCardRewardRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        if (10 == variableId) {
            setCard(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((RecordBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemCardRewardRecordBinding
    public void setCard(boolean Card) {
        this.mCard = Card;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemCardRewardRecordBinding
    public void setData(RecordBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String moduleName;
        String str2;
        String str3;
        String create_time;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mCard;
        RecordBean recordBean = this.mData;
        long j2 = j & 7;
        if (j2 != 0 && j2 != 0) {
            j = z ? j | 16 : j | 8;
        }
        long j3 = 6 & j;
        if (j3 != 0) {
            if (recordBean != null) {
                moduleName = recordBean.getModuleName();
                create_time = recordBean.getCreate_time();
            } else {
                moduleName = null;
                create_time = null;
            }
            str = "领取时间：" + create_time;
        } else {
            str = null;
            moduleName = null;
        }
        if ((j & 24) != 0) {
            String str4 = "+" + (recordBean != null ? recordBean.getNum() : null);
            if ((j & 16) != 0) {
                str3 = str4 + "元优惠券";
            } else {
                str3 = null;
            }
            if ((j & 8) != 0) {
                str2 = str4 + (recordBean != null ? recordBean.getUnit() : null);
            } else {
                str2 = null;
            }
        } else {
            str2 = null;
            str3 = null;
        }
        long j4 = j & 7;
        String str5 = j4 != 0 ? z ? str3 : str2 : null;
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, moduleName);
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str5);
        }
    }
}
