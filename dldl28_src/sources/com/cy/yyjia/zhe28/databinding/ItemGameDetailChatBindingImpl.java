package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GameDetailChatBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeImageView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameDetailChatBindingImpl extends ItemGameDetailChatBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final LinearLayout mboundView1;
    private final ShapeImageView mboundView2;
    private final ShapeTextView mboundView3;
    private final LinearLayout mboundView4;
    private final ShapeImageView mboundView5;
    private final ShapeTextView mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemGameDetailChatBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemGameDetailChatBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        ShapeImageView shapeImageView = (ShapeImageView) bindings[2];
        this.mboundView2 = shapeImageView;
        shapeImageView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView;
        shapeTextView.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout2;
        linearLayout2.setTag(null);
        ShapeImageView shapeImageView2 = (ShapeImageView) bindings[5];
        this.mboundView5 = shapeImageView2;
        shapeImageView2.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[6];
        this.mboundView6 = shapeTextView2;
        shapeTextView2.setTag(null);
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
        setData((GameDetailChatBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameDetailChatBinding
    public void setData(GameDetailChatBean Data) {
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
        boolean z;
        String content;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameDetailChatBean gameDetailChatBean = this.mData;
        long j2 = j & 3;
        boolean left = false;
        String avatar = null;
        if (j2 != 0) {
            if (gameDetailChatBean != null) {
                avatar = gameDetailChatBean.getAvatar();
                content = gameDetailChatBean.getContent();
                left = gameDetailChatBean.getLeft();
            } else {
                content = null;
            }
            str = content;
            z = left;
            left = !left;
        } else {
            str = null;
            z = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, left);
            DataBindingHelper.setUserIcon(this.mboundView2, avatar);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            DataBindingHelper.setUserIcon(this.mboundView5, avatar);
            TextViewBindingAdapter.setText(this.mboundView6, str);
        }
    }
}
