package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemMyGiftBindingImpl extends ItemMyGiftBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ShapeConstraintLayout mboundView0;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn, 6);
    }

    public ItemMyGiftBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemMyGiftBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[6], (ImageView) bindings[1], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        this.giftName.setTag(null);
        ShapeConstraintLayout shapeConstraintLayout = (ShapeConstraintLayout) bindings[0];
        this.mboundView0 = shapeConstraintLayout;
        shapeConstraintLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
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
        setData((GiftBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemMyGiftBinding
    public void setData(GiftBean Data) {
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
        return onChangeData((GiftBean) object, fieldId);
    }

    private boolean onChangeData(GiftBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String gift_name;
        String str3;
        String game_icon;
        String gift_code;
        String account_name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GiftBean giftBean = this.mData;
        long j2 = j & 3;
        String availableTime = null;
        if (j2 != 0) {
            if (giftBean != null) {
                availableTime = giftBean.getAvailableTime();
                game_icon = giftBean.getGame_icon();
                gift_code = giftBean.getGift_code();
                account_name = giftBean.getAccount_name();
                gift_name = giftBean.getGift_name();
            } else {
                game_icon = null;
                gift_code = null;
                account_name = null;
                gift_name = null;
            }
            String str4 = "有效日期：" + availableTime;
            str2 = "礼包码：" + gift_code;
            str3 = account_name + "可用";
            z = account_name == null;
            availableTime = game_icon;
            str = str4;
        } else {
            str = null;
            str2 = null;
            gift_name = null;
            str3 = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.gameIcon, availableTime);
            TextViewBindingAdapter.setText(this.giftName, gift_name);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            TextViewBindingAdapter.setText(this.mboundView5, str);
        }
    }
}
