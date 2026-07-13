package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.NoviceGameBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemNoviceGameGiftBindingImpl extends ItemNoviceGameGiftBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final ProgressBar mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemNoviceGameGiftBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemNoviceGameGiftBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[2];
        this.mboundView2 = progressBar;
        progressBar.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
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
        setData((NoviceGameBean.GiftBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemNoviceGameGiftBinding
    public void setData(NoviceGameBean.GiftBean Data) {
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
        int leftPercent;
        int isGet;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NoviceGameBean.GiftBean giftBean = this.mData;
        long j2 = j & 3;
        String name = null;
        if (j2 != 0) {
            if (giftBean != null) {
                isGet = giftBean.getIsGet();
                name = giftBean.getName();
                leftPercent = giftBean.getLeftPercent();
            } else {
                leftPercent = 0;
                isGet = 0;
            }
            i = isGet == 1 ? 1 : 0;
            String str3 = "剩余" + leftPercent;
            if (j2 != 0) {
                j |= i != 0 ? 8L : 4L;
            }
            String str4 = i != 0 ? "已领取" : "领取";
            str2 = str3 + "%";
            i = leftPercent;
            str = name;
            name = str4;
        } else {
            str = null;
            str2 = null;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.btn, name);
            TextViewBindingAdapter.setText(this.mboundView1, str);
            this.mboundView2.setProgress(i);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
        }
    }
}
