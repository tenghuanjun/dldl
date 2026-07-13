package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ItemTrumpet2BindingImpl extends ItemTrumpet2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final LinearLayout mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final LinearLayout mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;

    public ItemTrumpet2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemTrumpet2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (54 == variableId) {
            setManager(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((DealBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemTrumpet2Binding
    public void setManager(boolean Manager) {
        this.mManager = Manager;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(54);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemTrumpet2Binding
    public void setData(DealBean Data) {
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
        return onChangeData((DealBean) object, fieldId);
    }

    private boolean onChangeData(DealBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 90) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r17 = this;
            r1 = r17
            monitor-enter(r17)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L8e
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r17)     // Catch: java.lang.Throwable -> L8e
            boolean r0 = r1.mManager
            com.cy.yyjia.zhe28.domain.DealBean r6 = r1.mData
            r7 = 10
            long r7 = r7 & r2
            int r10 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r10 == 0) goto L18
            r7 = r0 ^ 1
            goto L19
        L18:
            r7 = 0
        L19:
            r11 = 13
            long r11 = r11 & r2
            r13 = 9
            r8 = 0
            int r15 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r15 == 0) goto L5f
            long r11 = r2 & r13
            int r16 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r16 == 0) goto L57
            if (r6 == 0) goto L34
            java.lang.String r8 = r6.getTotalMoney()
            java.lang.String r11 = r6.getName()
            goto L35
        L34:
            r11 = r8
        L35:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r9 = "累计消费："
            r12.<init>(r9)
            r12.append(r8)
            java.lang.String r8 = r12.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            java.lang.String r8 = "元"
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r9 = r8
            r8 = r11
            goto L58
        L57:
            r9 = r8
        L58:
            if (r6 == 0) goto L60
            boolean r6 = r6.getSelect()
            goto L61
        L5f:
            r9 = r8
        L60:
            r6 = 0
        L61:
            if (r15 == 0) goto L68
            com.hjq.shape.layout.ShapeLinearLayout r11 = r1.mboundView0
            com.cy.yyjia.zhe28.util.DataBindingHelper.setSelected(r11, r6)
        L68:
            if (r10 == 0) goto L79
            android.widget.LinearLayout r6 = r1.mboundView1
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r6, r0)
            android.widget.ImageView r0 = r1.mboundView3
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r0, r7)
            android.widget.LinearLayout r0 = r1.mboundView4
            com.cy.yyjia.zhe28.util.DataBindingHelper.setViewGone(r0, r7)
        L79:
            long r2 = r2 & r13
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L8d
            android.widget.TextView r0 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
            android.widget.TextView r0 = r1.mboundView5
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
            android.widget.TextView r0 = r1.mboundView6
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
        L8d:
            return
        L8e:
            r0 = move-exception
            monitor-exit(r17)     // Catch: java.lang.Throwable -> L8e
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemTrumpet2BindingImpl.executeBindings():void");
    }
}
