package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import com.cy.yyjia.zhe28.domain.GMTitleBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityGmPayBindingImpl extends ActivityGmPayBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn, 10);
    }

    public ActivityGmPayBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ActivityGmPayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (ShapeTextView) bindings[10], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[6];
        this.mboundView6 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[7];
        this.mboundView7 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[8];
        this.mboundView8 = textView8;
        textView8.setTag(null);
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
        if (67 == variableId) {
            setOldTitle((GMTitleBean) variable);
        } else if (87 == variableId) {
            setRole((GMRoleBean) variable);
        } else if (121 == variableId) {
            setUsername((String) variable);
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

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmPayBinding
    public void setOldTitle(GMTitleBean OldTitle) {
        updateRegistration(0, OldTitle);
        this.mOldTitle = OldTitle;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(67);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmPayBinding
    public void setRole(GMRoleBean Role) {
        updateRegistration(1, Role);
        this.mRole = Role;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmPayBinding
    public void setUsername(String Username) {
        this.mUsername = Username;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(121);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmPayBinding
    public void setTitle(GMTitleBean Title) {
        updateRegistration(2, Title);
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmPayBinding
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
            return onChangeOldTitle((GMTitleBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeRole((GMRoleBean) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeTitle((GMTitleBean) object, fieldId);
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

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String strValueOf;
        String strValueOf2;
        String accountName;
        String serviceCode;
        String roleId;
        String roleName;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GMTitleBean gMTitleBean = this.mOldTitle;
        GMRoleBean gMRoleBean = this.mRole;
        String str6 = this.mUsername;
        GMTitleBean gMTitleBean2 = this.mTitle;
        GMGameBean gMGameBean = this.mGame;
        long j2 = j & 37;
        if (j2 != 0) {
            z = gMTitleBean == null;
            if (j2 != 0) {
                j = z ? j | 128 : j | 64;
            }
            if ((j & 33) != 0) {
                j = z ? j | 512 : j | 256;
            }
        }
        long j3 = 34 & j;
        String str7 = null;
        if (j3 != 0) {
            if (gMRoleBean != null) {
                serviceCode = gMRoleBean.getServiceCode();
                roleId = gMRoleBean.getRoleId();
                roleName = gMRoleBean.getRoleName();
                accountName = gMRoleBean.getAccountName();
            } else {
                accountName = null;
                serviceCode = null;
                roleId = null;
                roleName = null;
            }
            str2 = "区服：" + serviceCode;
            str3 = "角色id：" + roleId;
            str = "小号：" + accountName;
            str4 = roleName;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
        }
        long j4 = j & 40;
        long j5 = j & 36;
        String tier_name = (j5 == 0 || gMTitleBean2 == null) ? null : gMTitleBean2.getTier_name();
        long j6 = j & 48;
        String name = (j6 == 0 || gMGameBean == null) ? null : gMGameBean.getName();
        long j7 = j & 64;
        double price = (j7 == 0 || gMTitleBean == null) ? 0.0d : gMTitleBean.getPrice();
        if ((j & 256) != 0) {
            str5 = "已开通权限：" + (gMTitleBean != null ? gMTitleBean.getTier_name() : null);
        } else {
            str5 = null;
        }
        if ((192 & j) != 0) {
            double price2 = gMTitleBean2 != null ? gMTitleBean2.getPrice() : 0.0d;
            strValueOf2 = (j & 128) != 0 ? String.valueOf(price2) : null;
            strValueOf = j7 != 0 ? String.valueOf(Math.max(0.0d, price2 - price)) : null;
        } else {
            strValueOf = null;
            strValueOf2 = null;
        }
        long j8 = 37 & j;
        if (j8 == 0) {
            strValueOf = null;
        } else if (z) {
            strValueOf = strValueOf2;
        }
        long j9 = j & 33;
        if (j9 != 0) {
            if (z) {
                str5 = "暂未开通";
            }
            str7 = str5;
        }
        String str8 = str7;
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            TextViewBindingAdapter.setText(this.mboundView5, str3);
            TextViewBindingAdapter.setText(this.mboundView6, str4);
        }
        if (j6 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, name);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str6);
        }
        if (j9 != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, str8);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView8, tier_name);
        }
        if (j8 != 0) {
            TextViewBindingAdapter.setText(this.tvPrice, strValueOf);
        }
    }
}
