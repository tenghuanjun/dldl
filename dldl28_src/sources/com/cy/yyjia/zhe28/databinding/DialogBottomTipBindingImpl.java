package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public class DialogBottomTipBindingImpl extends DialogBottomTipBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView10;
    private final LinearLayout mboundView11;
    private final TextView mboundView12;
    private final LinearLayout mboundView13;
    private final TextView mboundView14;
    private final LinearLayout mboundView15;
    private final LinearLayout mboundView16;
    private final LinearLayout mboundView17;
    private final TextView mboundView18;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final LinearLayout mboundView4;
    private final LinearLayout mboundView6;
    private final LinearLayout mboundView7;
    private final TextView mboundView8;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_permission, 19);
        sparseIntArray.put(R.id.tv_privacy, 20);
    }

    public DialogBottomTipBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }

    private DialogBottomTipBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (RecyclerView) bindings[5], (TextView) bindings[19], (TextView) bindings[20]);
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[10];
        this.mboundView10 = textView2;
        textView2.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[11];
        this.mboundView11 = linearLayout;
        linearLayout.setTag(null);
        TextView textView3 = (TextView) bindings[12];
        this.mboundView12 = textView3;
        textView3.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[13];
        this.mboundView13 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView4 = (TextView) bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[15];
        this.mboundView15 = linearLayout3;
        linearLayout3.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[16];
        this.mboundView16 = linearLayout4;
        linearLayout4.setTag(null);
        LinearLayout linearLayout5 = (LinearLayout) bindings[17];
        this.mboundView17 = linearLayout5;
        linearLayout5.setTag(null);
        TextView textView5 = (TextView) bindings[18];
        this.mboundView18 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[2];
        this.mboundView2 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[3];
        this.mboundView3 = textView7;
        textView7.setTag(null);
        LinearLayout linearLayout6 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout6;
        linearLayout6.setTag(null);
        LinearLayout linearLayout7 = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout7;
        linearLayout7.setTag(null);
        LinearLayout linearLayout8 = (LinearLayout) bindings[7];
        this.mboundView7 = linearLayout8;
        linearLayout8.setTag(null);
        TextView textView8 = (TextView) bindings[8];
        this.mboundView8 = textView8;
        textView8.setTag(null);
        LinearLayout linearLayout9 = (LinearLayout) bindings[9];
        this.mboundView9 = linearLayout9;
        linearLayout9.setTag(null);
        this.rvVip.setTag(null);
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
        setData((GameDetailBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogBottomTipBinding
    public void setData(GameDetailBean Data) {
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
        return onChangeData((GameDetailBean) object, fieldId);
    }

    private boolean onChangeData(GameDetailBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00f7  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.DialogBottomTipBindingImpl.executeBindings():void");
    }
}
