package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityChampionshipBindingImpl extends ActivityChampionshipBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final Navigation mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv1, 6);
        sparseIntArray.put(R.id.tv2, 7);
        sparseIntArray.put(R.id.tv3, 8);
        sparseIntArray.put(R.id.btn, 9);
    }

    public ActivityChampionshipBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityChampionshipBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[9], (RecyclerView) bindings[4], (RecyclerView) bindings[3], (TextView) bindings[6], (TextView) bindings[7], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        Navigation navigation = (Navigation) bindings[1];
        this.mboundView1 = navigation;
        navigation.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        this.rv.setTag(null);
        this.rvTask.setTag(null);
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
        setData((ChampionshipBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityChampionshipBinding
    public void setData(ChampionshipBean Data) {
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
        List<ChampionshipBean.Prize> prizes;
        List<ChampionshipBean.Task> list;
        ChampionshipBean.UserStatus user_status;
        List<ChampionshipBean.Task> tasks;
        ChampionshipBean.ActivityInfo activity_info;
        int completed_count;
        int all_tasks_completed;
        String name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ChampionshipBean championshipBean = this.mData;
        long j2 = j & 3;
        String rule = null;
        if (j2 != 0) {
            if (championshipBean != null) {
                user_status = championshipBean.getUser_status();
                tasks = championshipBean.getTasks();
                prizes = championshipBean.getPrizes();
                activity_info = championshipBean.getActivity_info();
            } else {
                user_status = null;
                tasks = null;
                prizes = null;
                activity_info = null;
            }
            if (user_status != null) {
                completed_count = user_status.getCompleted_count();
                all_tasks_completed = user_status.getAll_tasks_completed();
            } else {
                completed_count = 0;
                all_tasks_completed = 0;
            }
            if (activity_info != null) {
                rule = activity_info.getRule();
                name = activity_info.getName();
            } else {
                name = null;
            }
            str = ((("(" + all_tasks_completed) + "/") + completed_count) + ")";
            String str3 = name;
            list = tasks;
            str2 = rule;
            rule = str3;
        } else {
            str = null;
            str2 = null;
            prizes = null;
            list = null;
        }
        if (j2 != 0) {
            this.mboundView1.setTitle(rule);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
            DataBindingHelper.setRvData(this.rv, prizes);
            DataBindingHelper.setRvData(this.rvTask, list);
        }
    }
}
