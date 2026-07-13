package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMItemBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeImageView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGmBindingImpl extends ActivityGmBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final EditText mboundView5;
    private InverseBindingListener mboundView5androidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 7);
        sparseIntArray.put(R.id.tv_trans, 8);
        sparseIntArray.put(R.id.ll_roles, 9);
        sparseIntArray.put(R.id.rv, 10);
        sparseIntArray.put(R.id.ll_item, 11);
        sparseIntArray.put(R.id.ll, 12);
        sparseIntArray.put(R.id.btn1, 13);
        sparseIntArray.put(R.id.btn2, 14);
        sparseIntArray.put(R.id.btn3, 15);
        sparseIntArray.put(R.id.btn4, 16);
        sparseIntArray.put(R.id.btn5, 17);
        sparseIntArray.put(R.id.btn6, 18);
        sparseIntArray.put(R.id.btn7, 19);
        sparseIntArray.put(R.id.iv, 20);
        sparseIntArray.put(R.id.fl, 21);
    }

    public ActivityGmBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private ActivityGmBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (TextView) bindings[6], (TextView) bindings[13], (TextView) bindings[14], (TextView) bindings[15], (TextView) bindings[16], (TextView) bindings[17], (TextView) bindings[18], (TextView) bindings[19], (FrameLayout) bindings[21], (ShapeImageView) bindings[20], (ShapeLinearLayout) bindings[12], (LinearLayout) bindings[11], (LinearLayout) bindings[9], (Navigation) bindings[7], (RecyclerView) bindings[10], (ShapeTextView) bindings[8]);
        this.mboundView5androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityGmBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityGmBindingImpl.this.mboundView5);
                String str = ActivityGmBindingImpl.this.mCount;
                ActivityGmBindingImpl activityGmBindingImpl = ActivityGmBindingImpl.this;
                if (activityGmBindingImpl != null) {
                    activityGmBindingImpl.setCount(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
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
        EditText editText = (EditText) bindings[5];
        this.mboundView5 = editText;
        editText.setTag(null);
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
        if (49 == variableId) {
            setItem((GMItemBean) variable);
        } else if (87 == variableId) {
            setRole((GMRoleBean) variable);
        } else if (19 == variableId) {
            setCount((String) variable);
        } else if (114 == variableId) {
            setTitle((GMTitleBean) variable);
        } else {
            if (35 != variableId) {
                return false;
            }
            setGame((GMGameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmBinding
    public void setItem(GMItemBean Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(49);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmBinding
    public void setRole(GMRoleBean Role) {
        updateRegistration(1, Role);
        this.mRole = Role;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmBinding
    public void setCount(String Count) {
        this.mCount = Count;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmBinding
    public void setTitle(GMTitleBean Title) {
        updateRegistration(2, Title);
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmBinding
    public void setGame(GMGameBean Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeItem((GMItemBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeRole((GMRoleBean) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeTitle((GMTitleBean) object, fieldId);
    }

    private boolean onChangeItem(GMItemBean Item, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeRole(GMRoleBean Role, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeTitle(GMTitleBean Title, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityGmBindingImpl.executeBindings():void");
    }
}
