package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeConstraintLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGiftBindingImpl extends ItemGiftBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ShapeConstraintLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.gift_name, 6);
        sparseIntArray.put(R.id.btn, 7);
    }

    public ItemGiftBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ItemGiftBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[7], (ImageView) bindings[1], (LinearLayout) bindings[6], (TextView) bindings[4], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        ShapeConstraintLayout shapeConstraintLayout = (ShapeConstraintLayout) bindings[0];
        this.mboundView0 = shapeConstraintLayout;
        shapeConstraintLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        this.textView.setTag(null);
        this.textView2.setTag(null);
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
        if (123 == variableId) {
            setVip(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((GiftBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGiftBinding
    public void setVip(boolean Vip) {
        this.mVip = Vip;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(123);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGiftBinding
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
        String content_text;
        String gift_name;
        String percentStr;
        String game_icon;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mVip;
        GiftBean giftBean = this.mData;
        long j2 = 6 & j;
        long j3 = j & 5;
        String availableTime = null;
        if (j3 != 0) {
            if (giftBean != null) {
                availableTime = giftBean.getAvailableTime();
                game_icon = giftBean.getGame_icon();
                content_text = giftBean.getContent_text();
                gift_name = giftBean.getGift_name();
                percentStr = giftBean.getPercentStr();
            } else {
                game_icon = null;
                content_text = null;
                gift_name = null;
                percentStr = null;
            }
            availableTime = game_icon;
            str = "有效日期：" + availableTime;
        } else {
            str = null;
            content_text = null;
            gift_name = null;
            percentStr = null;
        }
        if (j3 != 0) {
            DataBindingHelper.setGameIcon(this.gameIcon, availableTime);
            TextViewBindingAdapter.setText(this.mboundView2, gift_name);
            TextViewBindingAdapter.setText(this.mboundView5, percentStr);
            TextViewBindingAdapter.setText(this.textView, str);
            TextViewBindingAdapter.setText(this.textView2, content_text);
        }
        if (j2 != 0) {
            this.mboundView2.setEnabled(z);
        }
    }
}
