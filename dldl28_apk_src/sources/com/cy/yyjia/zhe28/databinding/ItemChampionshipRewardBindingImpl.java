package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemChampionshipRewardBindingImpl extends ItemChampionshipRewardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemChampionshipRewardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemChampionshipRewardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
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
        setData((ChampionshipBean.Prize) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemChampionshipRewardBinding
    public void setData(ChampionshipBean.Prize Data) {
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
        String reward_content;
        String reward_icon;
        String str;
        int winner_count;
        String prize_name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ChampionshipBean.Prize prize = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (prize != null) {
                reward_content = prize.getReward_content();
                reward_icon = prize.getReward_icon();
                prize_name = prize.getPrize_name();
                winner_count = prize.getWinner_count();
            } else {
                winner_count = 0;
                reward_content = null;
                reward_icon = null;
                prize_name = null;
            }
            str = (prize_name + "X") + winner_count;
        } else {
            reward_content = null;
            reward_icon = null;
            str = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setImg(this.mboundView1, reward_icon, null);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, reward_content);
        }
    }
}
