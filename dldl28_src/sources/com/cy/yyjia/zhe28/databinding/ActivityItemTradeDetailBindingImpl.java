package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityItemTradeDetailBindingImpl extends ActivityItemTradeDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final ShapeTextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 11);
        sparseIntArray.put(R.id.tv_role, 12);
        sparseIntArray.put(R.id.iv_down, 13);
        sparseIntArray.put(R.id.et, 14);
        sparseIntArray.put(R.id.iv_up, 15);
        sparseIntArray.put(R.id.tv_price, 16);
    }

    public ActivityItemTradeDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private ActivityItemTradeDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[10], (EditText) bindings[14], (ImageView) bindings[13], (ImageView) bindings[15], (Navigation) bindings[11], (TextView) bindings[16], (TextView) bindings[12]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[5];
        this.mboundView5 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[8];
        this.mboundView8 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[9];
        this.mboundView9 = textView7;
        textView7.setTag(null);
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
        if (88 == variableId) {
            setRoleId((String) variable);
        } else if (23 == variableId) {
            setData((ItemTradeBean) variable);
        } else {
            if (87 != variableId) {
                return false;
            }
            setRole((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityItemTradeDetailBinding
    public void setRoleId(String RoleId) {
        this.mRoleId = RoleId;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(88);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityItemTradeDetailBinding
    public void setData(ItemTradeBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityItemTradeDetailBinding
    public void setRole(String Role) {
        this.mRole = Role;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(87);
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

    /* JADX WARN: Removed duplicated region for block: B:34:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityItemTradeDetailBindingImpl.executeBindings():void");
    }
}
