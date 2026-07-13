package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityInfoBindingImpl extends ActivityInfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_avatar, 7);
        sparseIntArray.put(R.id.rg, 8);
        sparseIntArray.put(R.id.ll_nickname, 9);
    }

    public ActivityInfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityInfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[7], (LinearLayout) bindings[9], (RadioButton) bindings[3], (RadioButton) bindings[4], (RadioButton) bindings[5], (RadioGroup) bindings[8]);
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
        TextView textView2 = (TextView) bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag(null);
        this.rb1.setTag(null);
        this.rb2.setTag(null);
        this.rb3.setTag(null);
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

    @Override // com.cy.yyjia.zhe28.databinding.ActivityInfoBinding
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
        String userName;
        String str;
        boolean z;
        boolean zEquals;
        String str2;
        String user_avatar;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        UserBean userBean = this.mData;
        long j2 = j & 3;
        boolean z2 = false;
        String str3 = null;
        if (j2 != 0) {
            if (userBean != null) {
                String nickName = userBean.getNickName();
                String userSex = userBean.getUserSex();
                userName = userBean.getUserName();
                user_avatar = userBean.getUser_avatar();
                str3 = userSex;
                str2 = nickName;
            } else {
                str2 = null;
                userName = null;
                user_avatar = null;
            }
            if (str3 != null) {
                boolean zEquals2 = str3.equals("female");
                boolean zEquals3 = str3.equals("male");
                zEquals = str3.equals(BooleanUtils.NO);
                str3 = user_avatar;
                str = str2;
                z = zEquals2;
                z2 = zEquals3;
            } else {
                str3 = user_avatar;
                zEquals = false;
                str = str2;
                z = false;
            }
        } else {
            userName = null;
            str = null;
            z = false;
            zEquals = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setUserIcon(this.mboundView1, str3);
            TextViewBindingAdapter.setText(this.mboundView2, userName);
            TextViewBindingAdapter.setText(this.mboundView6, str);
            CompoundButtonBindingAdapter.setChecked(this.rb1, z2);
            CompoundButtonBindingAdapter.setChecked(this.rb2, z);
            CompoundButtonBindingAdapter.setChecked(this.rb3, zEquals);
        }
    }
}
