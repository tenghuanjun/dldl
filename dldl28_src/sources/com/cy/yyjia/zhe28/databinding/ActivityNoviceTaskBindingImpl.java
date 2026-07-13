package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskBean;
import com.cy.yyjia.zhe28.domain.TaskResult;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityNoviceTaskBindingImpl extends ActivityNoviceTaskBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.imageView, 4);
        sparseIntArray.put(R.id.navigation, 5);
        sparseIntArray.put(R.id.textView, 6);
        sparseIntArray.put(R.id.linearLayout2, 7);
        sparseIntArray.put(R.id.linearLayout, 8);
    }

    public ActivityNoviceTaskBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityNoviceTaskBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[4], (LinearLayout) bindings[8], (LinearLayout) bindings[7], (RecyclerView) bindings[3], (Navigation) bindings[5], (LinearLayout) bindings[6]);
        this.mDirtyFlags = -1L;
        this.list.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
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
        setData((TaskResult) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityNoviceTaskBinding
    public void setData(TaskResult Data) {
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
        String zheCredit;
        TaskResult.Daily rookie;
        TaskResult.User user;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TaskResult taskResult = this.mData;
        long j2 = j & 3;
        List<TaskBean> list = null;
        if (j2 != 0) {
            if (taskResult != null) {
                rookie = taskResult.getRookie();
                user = taskResult.getUser();
            } else {
                rookie = null;
                user = null;
            }
            List<TaskBean> list2 = rookie != null ? rookie.getList() : null;
            if (user != null) {
                String allCredit = user.getAllCredit();
                zheCredit = user.getZheCredit();
                list = list2;
                str = allCredit;
            } else {
                zheCredit = null;
                list = list2;
                str = null;
            }
        } else {
            str = null;
            zheCredit = null;
        }
        if (j2 != 0) {
            DataBindingHelper.setRvData(this.list, list);
            TextViewBindingAdapter.setText(this.mboundView1, zheCredit);
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
    }
}
