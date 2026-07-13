package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DickerMessageBean;
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDealDickerBindingImpl extends ItemDealDickerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataToDetailAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final ShapeConstraintLayout mboundView0;
    private final TextView mboundView13;
    private final ShapeTextView mboundView14;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView7;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tag_view, 15);
        sparseIntArray.put(R.id.ll_btn, 16);
    }

    public ItemDealDickerBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private ItemDealDickerBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (TextView) bindings[2], (LinearLayout) bindings[16], (RecyclerView) bindings[5], (View) bindings[15], (TextView) bindings[6], (TextView) bindings[11], (TextView) bindings[8], (TextView) bindings[9], (TextView) bindings[10], (TextView) bindings[12]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        this.gameName.setTag(null);
        ShapeConstraintLayout shapeConstraintLayout = (ShapeConstraintLayout) bindings[0];
        this.mboundView0 = shapeConstraintLayout;
        shapeConstraintLayout.setTag(null);
        TextView textView = (TextView) bindings[13];
        this.mboundView13 = textView;
        textView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[14];
        this.mboundView14 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        this.rv.setTag(null);
        this.tvDicker.setTag(null);
        this.tvDickerAgree.setTag(null);
        this.tvDickerCancel.setTag(null);
        this.tvDickerModify.setTag(null);
        this.tvDickerRefuse.setTag(null);
        this.tvReceive.setTag(null);
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
        setData((DickerMessageBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDealDickerBinding
    public void setData(DickerMessageBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:170:0x0275  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 809
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemDealDickerBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private DickerMessageBean value;

        public OnClickListenerImpl setValue(DickerMessageBean value) {
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
