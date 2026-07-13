package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemItemTradeBindingImpl extends ItemItemTradeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final ShapeTextView mboundView5;
    private final TextView mboundView6;

    public ItemItemTradeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemItemTradeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[5];
        this.mboundView5 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((ItemTradeBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemItemTradeBinding
    public void setData(ItemTradeBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGame((GameBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
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
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> La5
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> La5
            monitor-exit(r17)     // Catch: java.lang.Throwable -> La5
            com.cy.yyjia.zhe28.domain.ItemTradeBean r0 = r1.mData
            r6 = 7
            long r6 = r6 & r2
            r8 = 6
            r10 = 0
            int r11 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r11 == 0) goto L7a
            if (r0 == 0) goto L1d
            com.cy.yyjia.zhe28.domain.GameBean r6 = r0.getGame()
            goto L1e
        L1d:
            r6 = r10
        L1e:
            r7 = 0
            r1.updateRegistration(r7, r6)
            if (r6 == 0) goto L29
            java.lang.String r6 = r6.getName()
            goto L2a
        L29:
            r6 = r10
        L2a:
            long r12 = r2 & r8
            int r7 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r7 == 0) goto L75
            if (r0 == 0) goto L4d
            java.lang.String r7 = r0.getPic()
            java.lang.String r10 = r0.getServiceCode()
            java.lang.String r12 = r0.getName()
            java.lang.String r13 = r0.getPrice()
            java.lang.String r0 = r0.getCreateTime()
            r16 = r12
            r12 = r10
            r10 = r13
            r13 = r16
            goto L51
        L4d:
            r0 = r10
            r7 = r0
            r12 = r7
            r13 = r12
        L51:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "￥"
            r14.<init>(r15)
            r14.append(r10)
            java.lang.String r10 = r14.toString()
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "上架时间："
            r14.<init>(r15)
            r14.append(r0)
            java.lang.String r0 = r14.toString()
            r16 = r6
            r6 = r0
            r0 = r10
            r10 = r7
            r7 = r16
            goto L7f
        L75:
            r7 = r6
            r0 = r10
            r6 = r0
            r12 = r6
            goto L7e
        L7a:
            r0 = r10
            r6 = r0
            r7 = r6
            r12 = r7
        L7e:
            r13 = r12
        L7f:
            long r2 = r2 & r8
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 == 0) goto L9d
            android.widget.ImageView r2 = r1.mboundView1
            com.cy.yyjia.zhe28.util.DataBindingHelper.setGameIcon(r2, r10)
            android.widget.TextView r2 = r1.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r13)
            android.widget.TextView r2 = r1.mboundView3
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r0)
            com.hjq.shape.view.ShapeTextView r0 = r1.mboundView5
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
            android.widget.TextView r0 = r1.mboundView6
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L9d:
            if (r11 == 0) goto La4
            android.widget.TextView r0 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        La4:
            return
        La5:
            r0 = move-exception
            monitor-exit(r17)     // Catch: java.lang.Throwable -> La5
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemItemTradeBindingImpl.executeBindings():void");
    }
}
