package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutDiscountBindingImpl extends LayoutDiscountBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView1;
    private final TextView mboundView10;
    private final ImageView mboundView11;
    private final ImageView mboundView12;
    private final TextView mboundView2;
    private final RelativeLayout mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView6;
    private final TextView mboundView8;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.first, 13);
    }

    public LayoutDiscountBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private LayoutDiscountBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (FrameLayout) bindings[0], (LinearLayout) bindings[13], (LinearLayout) bindings[7], (LinearLayout) bindings[5]);
        this.mDirtyFlags = -1L;
        this.discount.setTag(null);
        this.llFree.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ImageView imageView = (ImageView) bindings[11];
        this.mboundView11 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[12];
        this.mboundView12 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[3];
        this.mboundView3 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[9];
        this.mboundView9 = linearLayout2;
        linearLayout2.setTag(null);
        this.other.setTag(null);
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
            setData((GameBean) variable);
        } else {
            if (35 != variableId) {
                return false;
            }
            setGame(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutDiscountBinding
    public void setData(GameBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutDiscountBinding
    public void setGame(boolean Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d9  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.LayoutDiscountBindingImpl.executeBindings():void");
    }
}
