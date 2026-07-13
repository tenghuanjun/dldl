package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.TaskResult;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityQiandaoBindingImpl extends ActivityQiandaoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView2;
    private final ImageView mboundView5;
    private final TextView mboundView6;
    private final ImageView mboundView7;
    private final ImageView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_rule, 16);
        sparseIntArray.put(R.id.tv_record, 17);
        sparseIntArray.put(R.id.vf, 18);
        sparseIntArray.put(R.id.ll_task, 19);
        sparseIntArray.put(R.id.fl_daily, 20);
        sparseIntArray.put(R.id.fl_growup, 21);
        sparseIntArray.put(R.id.rv_nav, 22);
    }

    public ActivityQiandaoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityQiandaoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[20], (LinearLayout) bindings[21], (RecyclerView) bindings[3], (TextView) bindings[19], (RecyclerView) bindings[10], (RecyclerView) bindings[22], (ImageView) bindings[4], (TextView) bindings[17], (TextView) bindings[16], (ImageView) bindings[8], (AdapterViewFlipper) bindings[18]);
        this.mDirtyFlags = -1L;
        this.list.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[12];
        this.mboundView12 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[13];
        this.mboundView13 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[14];
        this.mboundView14 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[15];
        this.mboundView15 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[2];
        this.mboundView2 = textView7;
        textView7.setTag(null);
        ImageView imageView = (ImageView) bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag(null);
        TextView textView8 = (TextView) bindings[6];
        this.mboundView6 = textView8;
        textView8.setTag(null);
        ImageView imageView2 = (ImageView) bindings[7];
        this.mboundView7 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag(null);
        this.rv.setTag(null);
        this.sign.setTag(null);
        this.tvYhq.setTag(null);
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
            setData((QiandaoBean) variable);
        } else {
            if (107 != variableId) {
                return false;
            }
            setTask((TaskResult) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityQiandaoBinding
    public void setData(QiandaoBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityQiandaoBinding
    public void setTask(TaskResult Task) {
        this.mTask = Task;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(107);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityQiandaoBindingImpl.executeBindings():void");
    }
}
