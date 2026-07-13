package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.RecycleListBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemRecycleAccountBindingImpl extends ItemRecycleAccountBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;

    public ItemRecycleAccountBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemRecycleAccountBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
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
        if (58 == variableId) {
            setMode(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((RecycleListBean.Account) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemRecycleAccountBinding
    public void setMode(int Mode) {
        this.mMode = Mode;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(58);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemRecycleAccountBinding
    public void setData(RecycleListBean.Account Data) {
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
        return onChangeData((RecycleListBean.Account) object, fieldId);
    }

    private boolean onChangeData(RecycleListBean.Account Data, int fieldId) {
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
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        String str;
        String str2;
        boolean selected;
        String str3;
        String name;
        String userMoney;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mMode;
        RecycleListBean.Account account = this.mData;
        long j2 = 10 & j;
        if (j2 != 0) {
            z = true;
            z2 = i != 0;
            if (i != 0) {
                z = false;
            }
        } else {
            z = false;
            z2 = false;
        }
        long j3 = 13 & j;
        String recycleMoney = null;
        if (j3 != 0) {
            if ((j & 9) != 0) {
                if (account != null) {
                    recycleMoney = account.getRecycleMoney();
                    userMoney = account.getUserMoney();
                    name = account.getName();
                } else {
                    userMoney = null;
                    name = null;
                }
                str3 = "+" + recycleMoney;
                recycleMoney = ("累计消费：" + userMoney) + "元";
            } else {
                str3 = null;
                name = null;
            }
            if (account != null) {
                selected = account.getSelected();
                str2 = str3;
            } else {
                str2 = str3;
                selected = false;
            }
            str = recycleMoney;
            recycleMoney = name;
        } else {
            str = null;
            str2 = null;
            selected = false;
        }
        if ((j & 9) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, recycleMoney);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView4, str2);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView3, z2);
            DataBindingHelper.setViewGone(this.mboundView4, z);
        }
        if (j3 != 0) {
            DataBindingHelper.setSelected(this.mboundView3, selected);
        }
    }
}
