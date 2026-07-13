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
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDealBindingImpl extends ItemDealBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataToDetailAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final ShapeConstraintLayout mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView17;
    private final ShapeTextView mboundView18;
    private final TextView mboundView4;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll, 19);
        sparseIntArray.put(R.id.tag_view, 20);
        sparseIntArray.put(R.id.tv_modify, 21);
    }

    public ItemDealBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private ItemDealBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[1], (TextView) bindings[3], (ImageView) bindings[2], (LinearLayout) bindings[19], (RecyclerView) bindings[12], (View) bindings[20], (TextView) bindings[21], (TextView) bindings[16], (TextView) bindings[5], (TextView) bindings[15]);
        this.mDirtyFlags = -1L;
        this.gameIcon.setTag(null);
        this.gameName.setTag(null);
        this.ivSwitch.setTag(null);
        ShapeConstraintLayout shapeConstraintLayout = (ShapeConstraintLayout) bindings[0];
        this.mboundView0 = shapeConstraintLayout;
        shapeConstraintLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[13];
        this.mboundView13 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[17];
        this.mboundView17 = textView5;
        textView5.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[18];
        this.mboundView18 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView6 = (TextView) bindings[4];
        this.mboundView4 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[6];
        this.mboundView6 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[7];
        this.mboundView7 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[8];
        this.mboundView8 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[9];
        this.mboundView9 = textView10;
        textView10.setTag(null);
        this.rv.setTag(null);
        this.tvOffset.setTag(null);
        this.tvPrice.setTag(null);
        this.tvReceive.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((DealBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDealBinding
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
        if (fieldId != 95) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0257 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01f4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x020a  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 785
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ItemDealBindingImpl.executeBindings():void");
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
