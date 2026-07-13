package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ActivitySettingBindingImpl extends ActivitySettingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rl_user, 5);
        sparseIntArray.put(R.id.tv_safe, 6);
        sparseIntArray.put(R.id.ll_update, 7);
        sparseIntArray.put(R.id.tv_feedback, 8);
        sparseIntArray.put(R.id.tv_cache, 9);
        sparseIntArray.put(R.id.ll_cancellation, 10);
        sparseIntArray.put(R.id.ll_parents, 11);
        sparseIntArray.put(R.id.ll_sdk, 12);
        sparseIntArray.put(R.id.iv_switch_apk, 13);
        sparseIntArray.put(R.id.tv_download_manager, 14);
        sparseIntArray.put(R.id.tv_download, 15);
        sparseIntArray.put(R.id.ll_about, 16);
        sparseIntArray.put(R.id.tv_logout, 17);
    }

    public ActivitySettingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivitySettingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[13], (TextView) bindings[16], (LinearLayout) bindings[10], (LinearLayout) bindings[11], (LinearLayout) bindings[12], (LinearLayout) bindings[7], (RelativeLayout) bindings[5], (TextView) bindings[9], (TextView) bindings[15], (TextView) bindings[14], (TextView) bindings[8], (TextView) bindings[17], (TextView) bindings[6]);
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
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
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
        setData((UserBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivitySettingBinding
    public void setData(UserBean Data) {
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
        int uid;
        String user_avatar;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        UserBean userBean = this.mData;
        long j2 = 3 & j;
        String str3 = null;
        if (j2 != 0) {
            if (userBean != null) {
                uid = userBean.getUid();
                String nickName = userBean.getNickName();
                user_avatar = userBean.getUser_avatar();
                str3 = nickName;
            } else {
                uid = 0;
                user_avatar = null;
            }
            str2 = "UID:" + uid;
            String str4 = str3;
            str3 = user_avatar;
            str = str4;
        } else {
            str = null;
            str2 = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setUserIcon(this.mboundView1, str3);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, str2);
        }
        if ((j & 2) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, "V4.2.9");
        }
    }
}
