package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemTopicTaskBindingImpl extends ItemTopicTaskBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemTopicTaskBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemTopicTaskBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.bg.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag(null);
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
        if (23 == variableId) {
            setData((TopicDetailBean.TaskList) variable);
        } else {
            if (17 != variableId) {
                return false;
            }
            setConfig((TopicDetailBean.Task) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemTopicTaskBinding
    public void setData(TopicDetailBean.TaskList Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemTopicTaskBinding
    public void setConfig(TopicDetailBean.Task Config) {
        this.mConfig = Config;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String desc;
        boolean z;
        String finish_btn;
        String desc_bg;
        String go_btn;
        int status;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TopicDetailBean.TaskList taskList = this.mData;
        TopicDetailBean.Task task = this.mConfig;
        long j2 = 5 & j;
        if (j2 != 0) {
            if (taskList != null) {
                status = taskList.getStatus();
                desc = taskList.getDesc();
            } else {
                desc = null;
                status = 0;
            }
            boolean z2 = status != 0;
            z = status == 0;
            z = z2;
        } else {
            desc = null;
            z = false;
        }
        long j3 = j & 6;
        if (j3 == 0 || task == null) {
            finish_btn = null;
            desc_bg = null;
            go_btn = null;
        } else {
            finish_btn = task.getFinish_btn();
            desc_bg = task.getDesc_bg();
            go_btn = task.getGo_btn();
        }
        if (j3 != 0) {
            DataBindingHelper.setImg(this.bg, desc_bg, null);
            DataBindingHelper.setImg(this.mboundView3, go_btn, null);
            DataBindingHelper.setImg(this.mboundView4, finish_btn, null);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, desc);
            DataBindingHelper.setViewGone(this.mboundView3, z);
            DataBindingHelper.setViewGone(this.mboundView4, z);
        }
    }
}
