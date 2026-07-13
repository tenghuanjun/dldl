package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.FudaiIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemFudaiIndexBindingImpl extends ItemFudaiIndexBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemFudaiIndexBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemFudaiIndexBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.iv.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
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
        if (126 == variableId) {
            setWo(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((FudaiIndexBean.List) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemFudaiIndexBinding
    public void setWo(boolean Wo) {
        this.mWo = Wo;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(126);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemFudaiIndexBinding
    public void setData(FudaiIndexBean.List Data) {
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
        boolean zEquals;
        String str;
        boolean z;
        String avatar;
        String str2;
        String str3;
        long j2;
        String str4;
        FudaiIndexBean.User user;
        String money;
        String userName;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z2 = this.mWo;
        FudaiIndexBean.List list = this.mData;
        long j3 = j & 7;
        if (j3 != 0 && j3 != 0) {
            j = z2 ? j | 16 : j | 8;
        }
        long j4 = 6 & j;
        if (j4 != 0) {
            if (list != null) {
                user = list.getUser();
                money = list.getMoney();
            } else {
                user = null;
                money = null;
            }
            if (user != null) {
                userName = user.getUserName();
                avatar = user.getAvatar();
            } else {
                avatar = null;
                userName = null;
            }
            zEquals = "0".equals(money);
            z = !zEquals;
            str = ("+" + money) + "福利币";
            str2 = userName;
        } else {
            zEquals = false;
            str = null;
            z = false;
            avatar = null;
            str2 = null;
        }
        if ((j & 24) != 0) {
            String createtime = list != null ? list.getCreatetime() : null;
            if ((8 & j) != 0) {
                str4 = "中奖时间 " + createtime;
            } else {
                str4 = null;
            }
            if ((16 & j) != 0) {
                str3 = "参与时间 " + createtime;
            } else {
                str3 = null;
            }
            j2 = 7;
        } else {
            str3 = null;
            j2 = 7;
            str4 = null;
        }
        long j5 = j & j2;
        if (j5 == 0) {
            str3 = null;
        } else if (!z2) {
            str3 = str4;
        }
        if (j4 != 0) {
            DataBindingHelper.setUserIcon(this.iv, avatar);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            DataBindingHelper.setViewGone(this.mboundView4, zEquals);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            DataBindingHelper.setViewGone(this.mboundView5, z);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str3);
        }
    }
}
