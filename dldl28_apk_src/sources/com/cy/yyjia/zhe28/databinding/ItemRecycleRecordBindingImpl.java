package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.RecycleRecordBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemRecycleRecordBindingImpl extends ItemRecycleRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeTextView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final LinearLayout mboundView12;
    private final LinearLayout mboundView14;
    private final TextView mboundView15;
    private final ShapeTextView mboundView2;
    private final ShapeTextView mboundView3;
    private final ShapeTextView mboundView4;
    private final ImageView mboundView5;
    private final ShapeTextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    public ItemRecycleRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ItemRecycleRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[13]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[1];
        this.mboundView1 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[12];
        this.mboundView12 = linearLayout2;
        linearLayout2.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[14];
        this.mboundView14 = linearLayout3;
        linearLayout3.setTag(null);
        TextView textView3 = (TextView) bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView2;
        shapeTextView2.setTag(null);
        ShapeTextView shapeTextView3 = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView3;
        shapeTextView3.setTag(null);
        ShapeTextView shapeTextView4 = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView4;
        shapeTextView4.setTag(null);
        ImageView imageView = (ImageView) bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag(null);
        ShapeTextView shapeTextView5 = (ShapeTextView) bindings[6];
        this.mboundView6 = shapeTextView5;
        shapeTextView5.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[9];
        this.mboundView9 = textView6;
        textView6.setTag(null);
        this.tvOff.setTag(null);
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
        setData((RecycleRecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemRecycleRecordBinding
    public void setData(RecycleRecordBean Data) {
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

    /* JADX WARN: Removed duplicated region for block: B:46:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemRecycleRecordBindingImpl.executeBindings():void");
    }
}
