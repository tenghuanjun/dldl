package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameDealBindingImpl extends ItemGameDealBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataToDetailAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView3;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.lin_tag1, 9);
        sparseIntArray.put(R.id.lin_tag2, 10);
    }

    public ItemGameDealBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ItemGameDealBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[1], (LinearLayout) bindings[9], (LinearLayout) bindings[10], (TextView) bindings[2], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[5];
        this.mboundView5 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag(null);
        this.tvName.setTag(null);
        this.tvPrice.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        setData((DealBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameDealBinding
    public void setData(DealBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeData((DealBean) object, fieldId);
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

    private boolean onChangeData(DealBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 95) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        if (fieldId == 97) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 26) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b8  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemGameDealBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private DealBean value;

        public OnClickListenerImpl setValue(DealBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.toDetail(arg0);
        }
    }
}
