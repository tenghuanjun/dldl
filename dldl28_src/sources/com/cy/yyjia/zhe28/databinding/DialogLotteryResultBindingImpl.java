package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.LotteryGiftBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogLotteryResultBindingImpl extends DialogLotteryResultBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final LinearLayout mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll, 5);
        sparseIntArray.put(R.id.btn, 6);
        sparseIntArray.put(R.id.anim, 7);
        sparseIntArray.put(R.id.tv_skip, 8);
    }

    public DialogLotteryResultBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private DialogLotteryResultBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[7], (Button) bindings[6], (LinearLayout) bindings[5], (RecyclerView) bindings[1], (ShapeTextView) bindings[8]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        this.rv.setTag(null);
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
        setData((LotteryGiftBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogLotteryResultBinding
    public void setData(LotteryGiftBean Data) {
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
        String pic;
        String name;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LotteryGiftBean lotteryGiftBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            z = lotteryGiftBean != null;
            z = lotteryGiftBean == null;
            if (lotteryGiftBean != null) {
                pic = lotteryGiftBean.getPic();
                name = lotteryGiftBean.getName();
            } else {
                pic = null;
                name = null;
            }
        } else {
            pic = null;
            name = null;
            z = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z);
            DataBindingHelper.setImg(this.mboundView3, pic, null);
            TextViewBindingAdapter.setText(this.mboundView4, name);
            DataBindingHelper.setViewGone(this.rv, z);
        }
    }
}
