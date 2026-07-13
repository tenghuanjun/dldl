package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ProblemBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityServiceDetailBindingImpl extends ActivityServiceDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 4);
    }

    public ActivityServiceDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityServiceDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Navigation) bindings[4], (RecyclerView) bindings[3], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        this.rv.setTag(null);
        this.tvContent.setTag(null);
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
        setData((ProblemBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityServiceDetailBinding
    public void setData(ProblemBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0075  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r22 = this;
            r1 = r22
            monitor-enter(r22)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L9f
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r22)     // Catch: java.lang.Throwable -> L9f
            com.cy.yyjia.zhe28.domain.ProblemBean r0 = r1.mData
            r6 = 3
            long r8 = r2 & r6
            r10 = 16
            r12 = 4
            r14 = 1
            r15 = 0
            r16 = 0
            int r17 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r17 == 0) goto L52
            if (r0 == 0) goto L2b
            java.util.List r8 = r0.getMore()
            java.lang.String r9 = r0.getTitle()
            java.lang.String r0 = r0.getContent()
            goto L2e
        L2b:
            r0 = r15
            r8 = r0
            r9 = r8
        L2e:
            if (r8 != 0) goto L33
            r18 = 1
            goto L35
        L33:
            r18 = 0
        L35:
            boolean r19 = android.text.TextUtils.isEmpty(r0)
            if (r17 == 0) goto L43
            if (r18 == 0) goto L42
            r20 = 32
            long r2 = r2 | r20
            goto L43
        L42:
            long r2 = r2 | r10
        L43:
            long r20 = r2 & r6
            int r17 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r17 == 0) goto L59
            if (r19 == 0) goto L50
            r20 = 8
            long r2 = r2 | r20
            goto L59
        L50:
            long r2 = r2 | r12
            goto L59
        L52:
            r0 = r15
            r8 = r0
            r9 = r8
            r18 = 0
            r19 = 0
        L59:
            long r12 = r12 & r2
            int r17 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r17 == 0) goto L63
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            goto L64
        L63:
            r0 = r15
        L64:
            long r10 = r10 & r2
            int r12 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r12 == 0) goto L75
            if (r8 == 0) goto L70
            int r10 = r8.size()
            goto L71
        L70:
            r10 = 0
        L71:
            if (r10 != 0) goto L75
            r10 = 1
            goto L76
        L75:
            r10 = 0
        L76:
            long r2 = r2 & r6
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L85
            if (r19 == 0) goto L7f
            java.lang.String r0 = ""
        L7f:
            r15 = r0
            if (r18 == 0) goto L83
            goto L86
        L83:
            r14 = r10
            goto L86
        L85:
            r14 = 0
        L86:
            if (r6 == 0) goto L9e
            android.widget.TextView r0 = r1.mboundView1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
            androidx.recyclerview.widget.RecyclerView r0 = r1.rv
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r0, r14)
            androidx.recyclerview.widget.RecyclerView r0 = r1.rv
            com.cy.yyjia.zhe28.util.DataBindingHelper.setRvData(r0, r8)
            android.widget.TextView r0 = r1.tvContent
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
        L9e:
            return
        L9f:
            r0 = move-exception
            monitor-exit(r22)     // Catch: java.lang.Throwable -> L9f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityServiceDetailBindingImpl.executeBindings():void");
    }
}
