package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityCardBindingImpl extends ActivityCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final ImageView mboundView2;
    private final ShapeTextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rl_title, 12);
        sparseIntArray.put(R.id.iv_back, 13);
        sparseIntArray.put(R.id.tv_record, 14);
        sparseIntArray.put(R.id.tv_buy, 15);
        sparseIntArray.put(R.id.tv_game, 16);
    }

    public ActivityCardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private ActivityCardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ImageView) bindings[13], (RelativeLayout) bindings[12], (RecyclerView) bindings[8], (RecyclerView) bindings[7], (LinearLayout) bindings[15], (ShapeTextView) bindings[6], (TextView) bindings[16], (TextView) bindings[3], (TextView) bindings[14]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[10];
        this.mboundView10 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[11];
        this.mboundView11 = textView3;
        textView3.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[4];
        this.mboundView4 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[9];
        this.mboundView9 = textView5;
        textView5.setTag(null);
        this.rv.setTag(null);
        this.rvTitle.setTag(null);
        this.tvGain.setTag(null);
        this.tvNickname.setTag(null);
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
        if (23 == variableId) {
            setData((CardModuleBean) variable);
        } else {
            if (119 != variableId) {
                return false;
            }
            setUser((UserBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCardBinding
    public void setData(CardModuleBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCardBinding
    public void setUser(UserBean User) {
        this.mUser = User;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(119);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataSelectedModule((CardInfoBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeDataSelectedModuleSelectPrice((CardInfoBean.Price) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeDataGetSelectedModule((CardInfoBean) object, fieldId);
    }

    private boolean onChangeDataSelectedModule(CardInfoBean DataSelectedModule, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 91) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDataSelectedModuleSelectPrice(CardInfoBean.Price DataSelectedModuleSelectPrice, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDataGetSelectedModule(CardInfoBean DataGetSelectedModule, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01a2  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityCardBindingImpl.executeBindings():void");
    }
}
