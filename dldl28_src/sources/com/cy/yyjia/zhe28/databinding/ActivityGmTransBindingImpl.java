package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGmTransBindingImpl extends ActivityGmTransBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 8);
        sparseIntArray.put(R.id.ll_game, 9);
        sparseIntArray.put(R.id.ll_roles, 10);
        sparseIntArray.put(R.id.btn, 11);
    }

    public ActivityGmTransBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ActivityGmTransBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ShapeTextView) bindings[11], (FrameLayout) bindings[7], (LinearLayout) bindings[9], (LinearLayout) bindings[10], (Navigation) bindings[8], (RecyclerView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.fl.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
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
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        this.rv.setTag(null);
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
        if (65 == variableId) {
            setOldGame((GMGameBean) variable);
        } else if (67 == variableId) {
            setOldTitle((GMTitleBean) variable);
        } else if (87 == variableId) {
            setRole((GMRoleBean) variable);
        } else if (114 == variableId) {
            setTitle((GMTitleBean) variable);
        } else if (66 == variableId) {
            setOldRole((GMRoleBean) variable);
        } else {
            if (35 != variableId) {
                return false;
            }
            setGame((GMGameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding
    public void setOldGame(GMGameBean OldGame) {
        this.mOldGame = OldGame;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(65);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding
    public void setOldTitle(GMTitleBean OldTitle) {
        this.mOldTitle = OldTitle;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding
    public void setRole(GMRoleBean Role) {
        updateRegistration(1, Role);
        this.mRole = Role;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding
    public void setTitle(GMTitleBean Title) {
        updateRegistration(2, Title);
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding
    public void setOldRole(GMRoleBean OldRole) {
        updateRegistration(3, OldRole);
        this.mOldRole = OldRole;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(66);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransBinding
    public void setGame(GMGameBean Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(35);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeOldTitle((GMTitleBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeRole((GMRoleBean) object, fieldId);
        }
        if (localFieldId == 2) {
            return onChangeTitle((GMTitleBean) object, fieldId);
        }
        if (localFieldId != 3) {
            return false;
        }
        return onChangeOldRole((GMRoleBean) object, fieldId);
    }

    private boolean onChangeOldTitle(GMTitleBean OldTitle, int fieldId) {
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

    private boolean onChangeOldRole(GMRoleBean OldRole, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String icon;
        String name;
        String showText;
        boolean z;
        boolean z2;
        String name2;
        boolean z3;
        boolean z4;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GMGameBean gMGameBean = this.mOldGame;
        GMRoleBean gMRoleBean = this.mRole;
        GMTitleBean gMTitleBean = this.mTitle;
        GMRoleBean gMRoleBean2 = this.mOldRole;
        GMGameBean gMGameBean2 = this.mGame;
        String str2 = null;
        if ((j & 80) == 0 || gMGameBean == null) {
            icon = null;
            name = null;
        } else {
            name = gMGameBean.getName();
            icon = gMGameBean.getIcon();
        }
        long j2 = j & 66;
        boolean z5 = true;
        if (j2 != 0) {
            showText = gMRoleBean != null ? gMRoleBean.getShowText() : null;
            z = showText == null;
            if (j2 != 0) {
                j |= z ? 256L : 128L;
            }
        } else {
            showText = null;
            z = false;
        }
        long j3 = j & 70;
        if (j3 != 0) {
            z2 = gMTitleBean == null;
            if (j3 != 0) {
                j = z2 ? j | 1024 : j | 512;
            }
        } else {
            z2 = false;
        }
        String showText2 = ((j & 72) == 0 || gMRoleBean2 == null) ? null : gMRoleBean2.getShowText();
        long j4 = j & 96;
        if (j4 != 0) {
            name2 = gMGameBean2 != null ? gMGameBean2.getName() : null;
            z3 = gMGameBean2 == null;
            z4 = name2 == null;
            if (j4 != 0) {
                j |= z4 ? 4096L : 2048L;
            }
        } else {
            name2 = null;
            z3 = false;
            z4 = false;
        }
        boolean z6 = (j & 512) != 0 && gMRoleBean == null;
        long j5 = 66 & j;
        if (j5 != 0) {
            if (z) {
                showText = "选择角色";
            }
            str = showText;
        } else {
            str = null;
        }
        long j6 = j & 96;
        if (j6 != 0) {
            str2 = z4 ? "选择游戏" : name2;
        }
        long j7 = j & 70;
        if (j7 == 0) {
            z5 = false;
        } else if (!z2) {
            z5 = z6;
        }
        if (j7 != 0) {
            DataBindingHelper.setViewGone(this.fl, z5);
        }
        if ((j & 80) != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, icon);
            TextViewBindingAdapter.setText(this.mboundView2, name);
        }
        if ((j & 72) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, showText2);
        }
        if (j6 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str2);
            DataBindingHelper.setViewGone(this.rv, z3);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, str);
        }
    }
}
