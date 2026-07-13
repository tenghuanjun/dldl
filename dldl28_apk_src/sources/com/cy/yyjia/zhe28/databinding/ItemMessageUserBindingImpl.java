package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CommentMessageBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemMessageUserBindingImpl extends ItemMessageUserBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final View mboundView1;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemMessageUserBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemMessageUserBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[5], (ImageView) bindings[2], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.imageView2.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        View view = (View) bindings[1];
        this.mboundView1 = view;
        view.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        this.userIcon.setTag(null);
        this.userName.setTag(null);
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
        setData((CommentMessageBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemMessageUserBinding
    public void setData(CommentMessageBean Data) {
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
        String avatar;
        boolean zIsRead;
        String userName;
        SpannableString contentText;
        String gameIcon;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CommentMessageBean commentMessageBean = this.mData;
        long j2 = j & 3;
        if (j2 == 0 || commentMessageBean == null) {
            avatar = null;
            zIsRead = false;
            userName = null;
            contentText = null;
            gameIcon = null;
        } else {
            avatar = commentMessageBean.getAvatar();
            userName = commentMessageBean.getUserName();
            contentText = commentMessageBean.getContentText();
            gameIcon = commentMessageBean.getGameIcon();
            zIsRead = commentMessageBean.isRead();
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.imageView2, gameIcon);
            DataBindingHelper.setViewGone(this.mboundView1, zIsRead);
            TextViewBindingAdapter.setText(this.mboundView4, contentText);
            DataBindingHelper.setUserIcon(this.userIcon, avatar);
            TextViewBindingAdapter.setText(this.userName, userName);
        }
    }
}
