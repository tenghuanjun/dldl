package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeNewBindingImpl extends ItemHomeNewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final LinearLayout mboundView1;
    private final LayoutGameIconBinding mboundView11;
    private final RelativeLayout mboundView2;
    private final ShapeTextView mboundView3;
    private final TextView mboundView6;
    private final TextView mboundView7;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(14);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"layout_game_icon"}, new int[]{10}, new int[]{R.layout.layout_game_icon});
        includedLayouts.setIncludes(2, new String[]{"layout_game_name"}, new int[]{11}, new int[]{R.layout.layout_game_name});
        includedLayouts.setIncludes(8, new String[]{"layout_discount"}, new int[]{12}, new int[]{R.layout.layout_discount});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 13);
    }

    public ItemHomeNewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ItemHomeNewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ShapeTextView) bindings[9], (LinearLayout) bindings[13], (LayoutDiscountBinding) bindings[12], (FrameLayout) bindings[8], (LayoutGameNameBinding) bindings[11], (LinearLayout) bindings[4], (LinearLayout) bindings[5]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        setContainedBinding(this.discount);
        this.fl.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout;
        linearLayout.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[10];
        this.mboundView11 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[6];
        this.mboundView6 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[7];
        this.mboundView7 = textView2;
        textView2.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        this.tag2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
        }
        this.mboundView11.invalidateAll();
        this.name.invalidateAll();
        this.discount.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView11.hasPendingBindings() || this.name.hasPendingBindings() || this.discount.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 == variableId) {
            setData((GameBean) variable);
        } else {
            if (69 != variableId) {
                return false;
            }
            setOrder(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeNewBinding
    public void setData(GameBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeNewBinding
    public void setOrder(boolean Order) {
        this.mOrder = Order;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(69);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView11.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
        this.discount.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeName((LayoutGameNameBinding) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeData((GameBean) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeDiscount((LayoutDiscountBinding) object, fieldId);
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId != 69) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeDiscount(LayoutDiscountBinding Discount, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0170  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemHomeNewBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private GameBean value;

        public OnClickListenerImpl setValue(GameBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.gotoGame(arg0);
        }
    }
}
