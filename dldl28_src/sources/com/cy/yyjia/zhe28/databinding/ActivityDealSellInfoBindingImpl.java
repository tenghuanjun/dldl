package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.layout.ShapeRelativeLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityDealSellInfoBindingImpl extends ActivityDealSellInfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etDescandroidTextAttrChanged;
    private InverseBindingListener etPriceandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView3;
    private final EditText mboundView6;
    private InverseBindingListener mboundView6androidTextAttrChanged;
    private final EditText mboundView7;
    private InverseBindingListener mboundView7androidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.re_tag, 11);
        sparseIntArray.put(R.id.re_tag2, 12);
        sparseIntArray.put(R.id.tag_view, 13);
        sparseIntArray.put(R.id.view_tag, 14);
        sparseIntArray.put(R.id.ll, 15);
        sparseIntArray.put(R.id.tv_tag2, 16);
        sparseIntArray.put(R.id.lin_tag1, 17);
        sparseIntArray.put(R.id.iv_more, 18);
        sparseIntArray.put(R.id.lin_tag2, 19);
        sparseIntArray.put(R.id.iv_more2, 20);
        sparseIntArray.put(R.id.lin_tag3, 21);
        sparseIntArray.put(R.id.tv_tag4, 22);
        sparseIntArray.put(R.id.tv_tag3, 23);
        sparseIntArray.put(R.id.tv_tag5, 24);
        sparseIntArray.put(R.id.view_tag1, 25);
        sparseIntArray.put(R.id.tv_tag6, 26);
        sparseIntArray.put(R.id.re_tag3, 27);
        sparseIntArray.put(R.id.tv_limit, 28);
        sparseIntArray.put(R.id.view_tag2, 29);
        sparseIntArray.put(R.id.tv_tag7, 30);
        sparseIntArray.put(R.id.tv_tag8, 31);
        sparseIntArray.put(R.id.tv_tag9, 32);
    }

    public ActivityDealSellInfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }

    private ActivityDealSellInfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (EditText) bindings[9], (EditText) bindings[8], (ImageView) bindings[2], (ImageView) bindings[18], (ImageView) bindings[20], (ShapeLinearLayout) bindings[17], (ShapeLinearLayout) bindings[19], (ShapeLinearLayout) bindings[21], (LinearLayout) bindings[15], (Navigation) bindings[11], (RelativeLayout) bindings[12], (ShapeRelativeLayout) bindings[27], (TextView) bindings[4], (RecyclerView) bindings[10], (View) bindings[13], (TextView) bindings[1], (TextView) bindings[28], (TextView) bindings[5], (TextView) bindings[16], (TextView) bindings[23], (TextView) bindings[22], (TextView) bindings[24], (TextView) bindings[26], (TextView) bindings[30], (TextView) bindings[31], (TextView) bindings[32], (View) bindings[14], (View) bindings[25], (View) bindings[29]);
        this.etDescandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealSellInfoBindingImpl.this.etDesc);
                DealBean dealBean = ActivityDealSellInfoBindingImpl.this.mData;
                if (dealBean != null) {
                    dealBean.setDescription(textString);
                }
            }
        };
        this.etPriceandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealSellInfoBindingImpl.this.etPrice);
                DealBean dealBean = ActivityDealSellInfoBindingImpl.this.mData;
                if (dealBean != null) {
                    dealBean.setSellMoney(textString);
                }
            }
        };
        this.mboundView6androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealSellInfoBindingImpl.this.mboundView6);
                DealBean dealBean = ActivityDealSellInfoBindingImpl.this.mData;
                if (dealBean != null) {
                    dealBean.setService(textString);
                }
            }
        };
        this.mboundView7androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityDealSellInfoBindingImpl.this.mboundView7);
                DealBean dealBean = ActivityDealSellInfoBindingImpl.this.mData;
                if (dealBean != null) {
                    dealBean.setRoleName(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.etDesc.setTag(null);
        this.etPrice.setTag(null);
        this.gameIcon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        EditText editText = (EditText) bindings[6];
        this.mboundView6 = editText;
        editText.setTag(null);
        EditText editText2 = (EditText) bindings[7];
        this.mboundView7 = editText2;
        editText2.setTag(null);
        this.roleName.setTag(null);
        this.rv.setTag(null);
        this.tvDicker.setTag(null);
        this.tvTag1.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        setData((DealBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBinding
    public void setData(DealBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeData((DealBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeData(DealBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 97) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        if (fieldId == 95) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 26) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00be  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBindingImpl.executeBindings():void");
    }
}
