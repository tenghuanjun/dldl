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
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemBbsMessageReplyBindingImpl extends ItemBbsMessageReplyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;
    private final View mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_reply, 9);
    }

    public ItemBbsMessageReplyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemBbsMessageReplyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[9]);
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
        ImageView imageView3 = (ImageView) bindings[4];
        this.mboundView4 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[5];
        this.mboundView5 = imageView4;
        imageView4.setTag(null);
        View view = (View) bindings[6];
        this.mboundView6 = view;
        view.setTag(null);
        TextView textView2 = (TextView) bindings[7];
        this.mboundView7 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[8];
        this.mboundView8 = textView3;
        textView3.setTag(null);
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
        if (12 == variableId) {
            setChild(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((BbsBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemBbsMessageReplyBinding
    public void setChild(boolean Child) {
        this.mChild = Child;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemBbsMessageReplyBinding
    public void setData(BbsBean Data) {
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
        return onChangeData((BbsBean) object, fieldId);
    }

    private boolean onChangeData(BbsBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 83) {
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
        boolean z;
        String vieLevelImg;
        String message;
        boolean z2;
        String str;
        boolean z3;
        String str2;
        String str3;
        boolean z4;
        String str4;
        String str5;
        String createTime;
        String savingCardImg;
        String monthCardImg;
        String user_avatar;
        String nickName;
        boolean zIsEmpty;
        boolean zIsEmpty2;
        boolean zIsEmpty3;
        UserBean user;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BbsBean bbsBean = this.mData;
        long j2 = 13 & j;
        if (j2 != 0) {
            if ((j & 9) != 0) {
                if (bbsBean != null) {
                    user = bbsBean.getUser();
                    message = bbsBean.getMessage();
                    createTime = bbsBean.getCreateTime();
                } else {
                    user = null;
                    message = null;
                    createTime = null;
                }
                if (user != null) {
                    savingCardImg = user.getSavingCardImg();
                    monthCardImg = user.getMonthCardImg();
                    user_avatar = user.getUser_avatar();
                    nickName = user.getNickName();
                    vieLevelImg = user.getVieLevelImg();
                } else {
                    vieLevelImg = null;
                    savingCardImg = null;
                    monthCardImg = null;
                    user_avatar = null;
                    nickName = null;
                }
                zIsEmpty = TextUtils.isEmpty(savingCardImg);
                zIsEmpty2 = TextUtils.isEmpty(monthCardImg);
                zIsEmpty3 = TextUtils.isEmpty(vieLevelImg);
            } else {
                vieLevelImg = null;
                message = null;
                createTime = null;
                savingCardImg = null;
                monthCardImg = null;
                user_avatar = null;
                nickName = null;
                zIsEmpty = false;
                zIsEmpty2 = false;
                zIsEmpty3 = false;
            }
            boolean z5 = (bbsBean != null ? bbsBean.getRead() : 0) == 1;
            str4 = savingCardImg;
            str5 = monthCardImg;
            str = user_avatar;
            str2 = nickName;
            z = zIsEmpty;
            z4 = z5;
            str3 = createTime;
            z3 = zIsEmpty2;
            z2 = zIsEmpty3;
        } else {
            z = false;
            vieLevelImg = null;
            message = null;
            z2 = false;
            str = null;
            z3 = false;
            str2 = null;
            str3 = null;
            z4 = false;
            str4 = null;
            str5 = null;
        }
        if ((j & 9) != 0) {
            DataBindingHelper.setUserIcon(this.mboundView1, str);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            DataBindingHelper.setViewGone(this.mboundView3, z2);
            DataBindingHelper.setImg(this.mboundView3, vieLevelImg, null);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            DataBindingHelper.setImg(this.mboundView4, str4, null);
            DataBindingHelper.setViewGone(this.mboundView5, z3);
            DataBindingHelper.setImg(this.mboundView5, str5, null);
            TextViewBindingAdapter.setText(this.mboundView7, message);
            TextViewBindingAdapter.setText(this.mboundView8, str3);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView6, z4);
        }
    }
}
