package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GameToolBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameToolBindingImpl extends ItemGameToolBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final WancmsStandardPlayer mboundView5;
    private final TextView mboundView6;

    public ItemGameToolBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemGameToolBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (CardView) bindings[4], (ImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.cv.setTag(null);
        this.iv.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        WancmsStandardPlayer wancmsStandardPlayer = (WancmsStandardPlayer) bindings[5];
        this.mboundView5 = wancmsStandardPlayer;
        wancmsStandardPlayer.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        setData((GameToolBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameToolBinding
    public void setData(GameToolBean Data) {
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
        return onChangeData((GameToolBean) object, fieldId);
    }

    private boolean onChangeData(GameToolBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String title;
        String video;
        String str;
        String desc;
        String str2;
        String str3;
        boolean z;
        String bg;
        String icon;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameToolBean gameToolBean = this.mData;
        long j2 = j & 7;
        if (j2 != 0) {
            if ((j & 5) == 0 || gameToolBean == null) {
                title = null;
                video = null;
                desc = null;
                bg = null;
                icon = null;
            } else {
                title = gameToolBean.getTitle();
                video = gameToolBean.getVideo();
                desc = gameToolBean.getDesc();
                bg = gameToolBean.getBg();
                icon = gameToolBean.getIcon();
            }
            selected = gameToolBean != null ? gameToolBean.getSelected() : false;
            if (j2 != 0) {
                j |= selected ? 16L : 8L;
            }
            boolean z2 = !selected;
            str = selected ? "收起" : "查看介绍";
            str2 = bg;
            str3 = icon;
            boolean z3 = selected;
            selected = z2;
            z = z3;
        } else {
            title = null;
            video = null;
            str = null;
            desc = null;
            str2 = null;
            str3 = null;
            z = false;
        }
        if ((7 & j) != 0) {
            DataBindingHelper.setViewGone(this.cv, selected);
            TextViewBindingAdapter.setText(this.mboundView6, str);
            DataBindingHelper.setSelected(this.mboundView6, z);
        }
        if ((j & 5) != 0) {
            DataBindingHelper.setImg(this.iv, str3, null);
            TextViewBindingAdapter.setText(this.mboundView2, title);
            TextViewBindingAdapter.setText(this.mboundView3, desc);
            DataBindingHelper.setVideo(this.mboundView5, video, str2);
        }
    }
}
