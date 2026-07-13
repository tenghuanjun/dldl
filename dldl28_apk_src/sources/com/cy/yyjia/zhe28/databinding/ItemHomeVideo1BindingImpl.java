package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CollectionBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeVideo1BindingImpl extends ItemHomeVideo1Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataOnPicClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemHomeVideo1BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemHomeVideo1BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (WancmsStandardPlayer) bindings[2]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        this.player.setTag(null);
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
        setData((CollectionBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeVideo1Binding
    public void setData(CollectionBean Data) {
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
        boolean zIsEmpty;
        String str;
        String video_url;
        String img_url;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CollectionBean collectionBean = this.mData;
        long j2 = j & 3;
        OnClickListenerImpl onClickListenerImpl = null;
        String str2 = null;
        if (j2 != 0) {
            if (collectionBean != null) {
                String name = collectionBean.getName();
                OnClickListenerImpl onClickListenerImpl2 = this.mDataOnPicClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl();
                    this.mDataOnPicClickAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                value = onClickListenerImpl2.setValue(collectionBean);
                video_url = collectionBean.getVideo_url();
                img_url = collectionBean.getImg_url();
                str2 = name;
            } else {
                value = null;
                video_url = null;
                img_url = null;
            }
            zIsEmpty = TextUtils.isEmpty(str2);
            String str3 = str2;
            onClickListenerImpl = value;
            str = str3;
        } else {
            zIsEmpty = false;
            str = null;
            video_url = null;
            img_url = null;
        }
        if (j2 != 0) {
            this.mboundView0.setOnClickListener(onClickListenerImpl);
            DataBindingHelper.setViewGone(this.mboundView1, zIsEmpty);
            TextViewBindingAdapter.setText(this.mboundView1, str);
            DataBindingHelper.setVideo(this.player, video_url, img_url);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private CollectionBean value;

        public OnClickListenerImpl setValue(CollectionBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onPicClick(arg0);
        }
    }
}
