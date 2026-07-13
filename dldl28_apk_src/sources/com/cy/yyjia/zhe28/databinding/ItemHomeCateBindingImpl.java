package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.HomeBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeCateBindingImpl extends ItemHomeCateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;

    public ItemHomeCateBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemHomeCateBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[4];
        this.mboundView4 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[5];
        this.mboundView5 = imageView4;
        imageView4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        setData((HomeBean.CateBlock) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeCateBinding
    public void setData(HomeBean.CateBlock Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGamelistGetInt2((GameBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeDataGamelistGetInt0((GameBean) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeDataGamelistGetInt1((GameBean) object, fieldId);
    }

    private boolean onChangeDataGamelistGetInt2(GameBean DataGamelistGetInt2, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDataGamelistGetInt0(GameBean DataGamelistGetInt0, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDataGamelistGetInt1(GameBean DataGamelistGetInt1, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008b  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemHomeCateBindingImpl.executeBindings():void");
    }
}
