package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeVideoGameBindingImpl extends ItemHomeVideoGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ItemHomeLargeBinding mboundView01;
    private final ConstraintLayout mboundView1;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(4);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"item_home_large"}, new int[]{3}, new int[]{R.layout.item_home_large});
        sViewsWithIds = null;
    }

    public ItemHomeVideoGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemHomeVideoGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (WancmsStandardPlayer) bindings[2]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ItemHomeLargeBinding itemHomeLargeBinding = (ItemHomeLargeBinding) bindings[3];
        this.mboundView01 = itemHomeLargeBinding;
        setContainedBinding(itemHomeLargeBinding);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag(null);
        this.player.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.mboundView01.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView01.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((GameBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeVideoGameBinding
    public void setData(GameBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView01.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean zIsEmpty;
        String str;
        String videoCoverUrl;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameBean gameBean = this.mData;
        long j2 = j & 3;
        OnClickListenerImpl onClickListenerImpl = null;
        String videoUrl = null;
        if (j2 != 0) {
            if (gameBean != null) {
                OnClickListenerImpl onClickListenerImpl2 = this.mDataGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl();
                    this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                value = onClickListenerImpl2.setValue(gameBean);
                videoCoverUrl = gameBean.getVideoCoverUrl();
                videoUrl = gameBean.getVideoUrl();
            } else {
                value = null;
                videoCoverUrl = null;
            }
            zIsEmpty = TextUtils.isEmpty(videoUrl);
            String str2 = videoUrl;
            onClickListenerImpl = value;
            str = str2;
        } else {
            zIsEmpty = false;
            str = null;
            videoCoverUrl = null;
        }
        if (j2 != 0) {
            this.mboundView0.setOnClickListener(onClickListenerImpl);
            this.mboundView01.setData(gameBean);
            DataBindingHelper.setViewGone(this.mboundView1, zIsEmpty);
            DataBindingHelper.setVideo(this.player, str, videoCoverUrl);
        }
        executeBindingsOn(this.mboundView01);
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private GameBean value;

        public OnClickListenerImpl setValue(GameBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.gotoGame(arg0);
        }
    }
}
