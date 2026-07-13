package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.domain.TaskResult;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialogQiandaoTaskBindingImpl extends DialogQiandaoTaskBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public DialogQiandaoTaskBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private DialogQiandaoTaskBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[2]);
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        this.rv1.setTag(null);
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
        if (118 == variableId) {
            setType(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((TaskResult) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogQiandaoTaskBinding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(118);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogQiandaoTaskBinding
    public void setData(TaskResult Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        List<TaskBean> list;
        String totalNum;
        String finishNum;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mType;
        TaskResult taskResult = this.mData;
        long j2 = 5 & j;
        boolean z = false;
        if (j2 != 0 && i == 2) {
            z = true;
        }
        long j3 = j & 6;
        String name = null;
        if (j3 != 0) {
            TaskResult.Daily daily = taskResult != null ? taskResult.getDaily() : null;
            if (daily != null) {
                name = daily.getName();
                finishNum = daily.getFinishNum();
                list = daily.getList();
                totalNum = daily.getTotalNum();
            } else {
                totalNum = null;
                finishNum = null;
                list = null;
            }
            name = ((((name + "（") + finishNum) + "/") + totalNum) + "）";
        } else {
            list = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
            DataBindingHelper.setViewGone(this.rv1, z);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, name);
            DataBindingHelper.setRvData(this.rv1, list);
        }
    }
}
