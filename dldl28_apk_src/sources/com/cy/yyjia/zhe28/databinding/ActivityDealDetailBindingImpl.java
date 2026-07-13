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
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityDealDetailBindingImpl extends ActivityDealDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGameGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView13;
    private final ShapeLinearLayout mboundView14;
    private final TextView mboundView15;
    private final ShapeTextView mboundView2;
    private final TextView mboundView4;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.re_tag, 19);
        sparseIntArray.put(R.id.cl_tag, 20);
        sparseIntArray.put(R.id.tv_tag1, 21);
        sparseIntArray.put(R.id.tv_tag2, 22);
        sparseIntArray.put(R.id.tv_tag3, 23);
        sparseIntArray.put(R.id.tv_tag5, 24);
        sparseIntArray.put(R.id.tv_tag6, 25);
        sparseIntArray.put(R.id.tv_buy, 26);
        sparseIntArray.put(R.id.ll_dicker, 27);
        sparseIntArray.put(R.id.tv_dicker_price, 28);
        sparseIntArray.put(R.id.tv_dicker2, 29);
        sparseIntArray.put(R.id.tv_dicker1, 30);
    }

    public ActivityDealDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds));
    }

    private ActivityDealDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ShapeConstraintLayout) bindings[20], (ImageView) bindings[1], (TextView) bindings[3], (LinearLayout) bindings[27], (Navigation) bindings[19], (LinearLayout) bindings[16], (RecyclerView) bindings[12], (ShapeTextView) bindings[26], (TextView) bindings[17], (ShapeTextView) bindings[30], (ShapeTextView) bindings[29], (TextView) bindings[28], (TextView) bindings[18], (ShapeTextView) bindings[11], (TextView) bindings[21], (TextView) bindings[22], (TextView) bindings[23], (TextView) bindings[5], (TextView) bindings[24], (TextView) bindings[25]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        this.gameName.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[14];
        this.mboundView14 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView3 = (TextView) bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[6];
        this.mboundView6 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[7];
        this.mboundView7 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[8];
        this.mboundView8 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[9];
        this.mboundView9 = textView8;
        textView8.setTag(null);
        this.reTag1.setTag(null);
        this.rv.setTag(null);
        this.tvDicker.setTag(null);
        this.tvDickerPrice1.setTag(null);
        this.tvGame.setTag(null);
        this.tvTag4.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
        if (99 == variableId) {
            setShowDickerPrice(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((DealBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealDetailBinding
    public void setShowDickerPrice(boolean ShowDickerPrice) {
        this.mShowDickerPrice = ShowDickerPrice;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(99);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityDealDetailBinding
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
        if (fieldId == 97) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId == 95) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        if (fieldId != 26) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0132  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityDealDetailBindingImpl.executeBindings():void");
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
