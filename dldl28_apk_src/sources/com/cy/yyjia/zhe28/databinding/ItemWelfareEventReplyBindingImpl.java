package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemWelfareEventReplyBindingImpl extends ItemWelfareEventReplyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    public ItemWelfareEventReplyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemWelfareEventReplyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[6]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        this.tvPraise.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((CommentBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemWelfareEventReplyBinding
    public void setData(CommentBean Data) {
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
        return onChangeData((CommentBean) object, fieldId);
    }

    private boolean onChangeData(CommentBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 47) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId != 53) {
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
        String strValueOf;
        String vieLevelImg;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        boolean z2;
        String dateline;
        String message;
        String user_avatar;
        String nickName;
        boolean zIsEmpty;
        UserBean user;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CommentBean commentBean = this.mData;
        if ((15 & j) != 0) {
            if ((j & 13) != 0) {
                strValueOf = String.valueOf(commentBean != null ? commentBean.getLike_num() : 0);
            } else {
                strValueOf = null;
            }
            if ((j & 9) != 0) {
                if (commentBean != null) {
                    user = commentBean.getUser();
                    dateline = commentBean.getDateline();
                    message = commentBean.getMessage();
                } else {
                    user = null;
                    dateline = null;
                    message = null;
                }
                if (user != null) {
                    user_avatar = user.getUser_avatar();
                    nickName = user.getNickName();
                    vieLevelImg = user.getVieLevelImg();
                } else {
                    vieLevelImg = null;
                    user_avatar = null;
                    nickName = null;
                }
                zIsEmpty = TextUtils.isEmpty(vieLevelImg);
            } else {
                vieLevelImg = null;
                dateline = null;
                message = null;
                user_avatar = null;
                nickName = null;
                zIsEmpty = false;
            }
            if ((j & 11) != 0) {
                z = (commentBean != null ? commentBean.getIslikeNum() : 0) == 1;
                str3 = dateline;
                str4 = message;
                str = user_avatar;
                str2 = nickName;
                z2 = zIsEmpty;
            } else {
                str3 = dateline;
                str4 = message;
                str = user_avatar;
                str2 = nickName;
                z2 = zIsEmpty;
                z = false;
            }
        } else {
            strValueOf = null;
            vieLevelImg = null;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            z = false;
            z2 = false;
        }
        if ((9 & j) != 0) {
            DataBindingHelper.setUserIcon(this.mboundView1, str);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            DataBindingHelper.setViewGone(this.mboundView3, z2);
            DataBindingHelper.setImg(this.mboundView3, vieLevelImg, null);
            TextViewBindingAdapter.setText(this.mboundView4, str4);
            TextViewBindingAdapter.setText(this.mboundView5, str3);
        }
        if ((11 & j) != 0) {
            DataBindingHelper.setSelected(this.tvPraise, z);
        }
        if ((j & 13) != 0) {
            TextViewBindingAdapter.setText(this.tvPraise, strValueOf);
        }
    }
}
