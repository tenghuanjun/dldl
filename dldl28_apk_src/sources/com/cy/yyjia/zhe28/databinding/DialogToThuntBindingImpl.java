package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.SignResultBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class DialogToThuntBindingImpl extends DialogToThuntBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataLinkOnClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final LinearLayout mboundView1;
    private final TextView mboundView5;
    private final ImageView mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public DialogToThuntBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private DialogToThuntBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[4], (TextView) bindings[3], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[5];
        this.mboundView5 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[6];
        this.mboundView6 = imageView;
        imageView.setTag(null);
        this.f454tv.setTag(null);
        this.tv1.setTag(null);
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
        setData((SignResultBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogToThuntBinding
    public void setData(SignResultBean Data) {
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
        boolean z;
        String rewardsBg;
        String msg;
        String text;
        OnClickListenerImpl value;
        String linkBtn;
        String rewardTip;
        BtnBean link;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        SignResultBean signResultBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (signResultBean != null) {
                rewardsBg = signResultBean.getRewardsBg();
                msg = signResultBean.getMsg();
                text = signResultBean.getText();
                link = signResultBean.getLink();
                rewardTip = signResultBean.getRewardTip();
                linkBtn = signResultBean.getLinkBtn();
            } else {
                rewardsBg = null;
                msg = null;
                text = null;
                link = null;
                linkBtn = null;
                rewardTip = null;
            }
            boolean zIsEmpty = TextUtils.isEmpty(rewardsBg);
            if (link != null) {
                OnClickListenerImpl onClickListenerImpl = this.mDataLinkOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataLinkOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(link);
            } else {
                value = null;
            }
            z = !zIsEmpty;
        } else {
            z = false;
            rewardsBg = null;
            msg = null;
            text = null;
            value = null;
            linkBtn = null;
            rewardTip = null;
        }
        if (j2 != 0) {
            this.btn.setOnClickListener(value);
            TextViewBindingAdapter.setText(this.btn, linkBtn);
            DataBindingHelper.setViewGone(this.mboundView1, z);
            TextViewBindingAdapter.setText(this.mboundView5, rewardTip);
            DataBindingHelper.setImg(this.mboundView6, rewardsBg, null);
            TextViewBindingAdapter.setText(this.f454tv, text);
            TextViewBindingAdapter.setText(this.tv1, msg);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BtnBean value;

        public OnClickListenerImpl setValue(BtnBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
