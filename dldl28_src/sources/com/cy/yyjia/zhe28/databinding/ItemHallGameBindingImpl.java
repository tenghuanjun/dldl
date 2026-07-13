package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHallGameBindingImpl extends ItemHallGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ShapeView mboundView1;
    private final ImageView mboundView10;
    private final ShapeView mboundView2;
    private final ShapeView mboundView3;
    private final LinearLayout mboundView4;
    private final LayoutGameIconBinding mboundView41;
    private final RelativeLayout mboundView5;
    private final TextView mboundView6;
    private final ImageView mboundView8;
    private final ImageView mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(15);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(4, new String[]{"layout_game_icon", "layout_discount"}, new int[]{11, 13}, new int[]{R.layout.layout_game_icon, R.layout.layout_discount});
        includedLayouts.setIncludes(5, new String[]{"layout_game_name"}, new int[]{12}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 14);
    }

    public ItemHallGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private ItemHallGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (LinearLayout) bindings[14], (LayoutDiscountBinding) bindings[13], (LayoutGameNameBinding) bindings[12], (LinearLayout) bindings[7]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.discount);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ShapeView shapeView = (ShapeView) bindings[1];
        this.mboundView1 = shapeView;
        shapeView.setTag(null);
        ImageView imageView = (ImageView) bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag(null);
        ShapeView shapeView2 = (ShapeView) bindings[2];
        this.mboundView2 = shapeView2;
        shapeView2.setTag(null);
        ShapeView shapeView3 = (ShapeView) bindings[3];
        this.mboundView3 = shapeView3;
        shapeView3.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout;
        linearLayout.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[11];
        this.mboundView41 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[5];
        this.mboundView5 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[6];
        this.mboundView6 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[8];
        this.mboundView8 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        this.mboundView41.invalidateAll();
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
            return this.mboundView41.hasPendingBindings() || this.name.hasPendingBindings() || this.discount.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((GameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHallGameBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHallGameBinding
    public void setData(GameBean Data) {
        updateRegistration(2, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView41.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
        this.discount.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDiscount((LayoutDiscountBinding) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeName((LayoutGameNameBinding) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeDiscount(LayoutDiscountBinding Discount, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        OnClickListenerImpl value;
        List<GameBean.Tag> tags;
        SpannableString descNew;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        GameBean gameBean = this.mData;
        long j2 = 24 & j;
        if (j2 != 0) {
            boolean z3 = i != 2;
            z2 = i != 1;
            z = i != 0;
            z = z3;
        } else {
            z = false;
            z2 = false;
        }
        long j3 = j & 20;
        if (j3 == 0 || gameBean == null) {
            value = null;
            tags = null;
            descNew = null;
        } else {
            OnClickListenerImpl onClickListenerImpl = this.mDataGotoGameAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(gameBean);
            tags = gameBean.getTags();
            descNew = gameBean.getDescNew();
        }
        if (j3 != 0) {
            this.discount.setData(gameBean);
            this.mboundView4.setOnClickListener(value);
            this.mboundView41.setData(gameBean);
            TextViewBindingAdapter.setText(this.mboundView6, descNew);
            this.name.setData(gameBean);
            DataBindingHelper.setTags(this.tag, tags);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
            DataBindingHelper.setViewGone(this.mboundView10, z);
            DataBindingHelper.setViewGone(this.mboundView2, z2);
            DataBindingHelper.setViewGone(this.mboundView3, z);
            DataBindingHelper.setViewGone(this.mboundView8, z);
            DataBindingHelper.setViewGone(this.mboundView9, z2);
        }
        executeBindingsOn(this.mboundView41);
        executeBindingsOn(this.name);
        executeBindingsOn(this.discount);
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
