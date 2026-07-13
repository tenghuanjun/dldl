package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameScoreBean;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentGameCommentBindingImpl extends FragmentGameCommentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView2;
    private final ProgressBar mboundView3;
    private final ProgressBar mboundView4;
    private final ProgressBar mboundView5;
    private final ProgressBar mboundView6;
    private final ProgressBar mboundView7;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_comment_num, 8);
        sparseIntArray.put(R.id.tv_sort, 9);
        sparseIntArray.put(R.id.rv_category, 10);
        sparseIntArray.put(R.id.rv, 11);
        sparseIntArray.put(R.id.btn_top, 12);
    }

    public FragmentGameCommentBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private FragmentGameCommentBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[12], (RecyclerView) bindings[11], (RecyclerView) bindings[10], (TextView) bindings[8], (TextView) bindings[1], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[3];
        this.mboundView3 = progressBar;
        progressBar.setTag(null);
        ProgressBar progressBar2 = (ProgressBar) bindings[4];
        this.mboundView4 = progressBar2;
        progressBar2.setTag(null);
        ProgressBar progressBar3 = (ProgressBar) bindings[5];
        this.mboundView5 = progressBar3;
        progressBar3.setTag(null);
        ProgressBar progressBar4 = (ProgressBar) bindings[6];
        this.mboundView6 = progressBar4;
        progressBar4.setTag(null);
        ProgressBar progressBar5 = (ProgressBar) bindings[7];
        this.mboundView7 = progressBar5;
        progressBar5.setTag(null);
        this.tvScore.setTag(null);
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
        setData((GameScoreBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentGameCommentBinding
    public void setData(GameScoreBean Data) {
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
        int i;
        int i2;
        int s5;
        int i3;
        int i4;
        int s3;
        int s2;
        int s1;
        int max;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameScoreBean gameScoreBean = this.mData;
        long j2 = j & 3;
        String average = null;
        int total_num = 0;
        if (j2 != 0) {
            if (gameScoreBean != null) {
                s3 = gameScoreBean.getS3();
                s2 = gameScoreBean.getS2();
                s1 = gameScoreBean.getS1();
                total_num = gameScoreBean.getTotal_num();
                s5 = gameScoreBean.getS5();
                int s4 = gameScoreBean.getS4();
                max = gameScoreBean.getMax();
                average = gameScoreBean.getAverage();
                i3 = s4;
            } else {
                s3 = 0;
                s2 = 0;
                s1 = 0;
                s5 = 0;
                i3 = 0;
                max = 0;
            }
            int i5 = s2;
            i = s3;
            str = average;
            average = total_num + "个评价";
            total_num = max;
            i4 = s1;
            i2 = i5;
        } else {
            str = null;
            i = 0;
            i2 = 0;
            s5 = 0;
            i3 = 0;
            i4 = 0;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, average);
            this.mboundView3.setMax(total_num);
            this.mboundView3.setProgress(s5);
            this.mboundView4.setMax(total_num);
            this.mboundView4.setProgress(i3);
            this.mboundView5.setMax(total_num);
            this.mboundView5.setProgress(i);
            this.mboundView6.setMax(total_num);
            this.mboundView6.setProgress(i2);
            this.mboundView7.setMax(total_num);
            this.mboundView7.setProgress(i4);
            TextViewBindingAdapter.setText(this.tvScore, str);
        }
    }
}
