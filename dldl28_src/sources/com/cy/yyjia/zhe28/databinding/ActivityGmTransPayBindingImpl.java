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
public class ActivityGmTransPayBindingImpl extends ActivityGmTransPayBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView13;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn, 17);
    }

    public ActivityGmTransPayBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ActivityGmTransPayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ShapeTextView) bindings[17], (TextView) bindings[16]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[10];
        this.mboundView10 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[11];
        this.mboundView11 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[12];
        this.mboundView12 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[13];
        this.mboundView13 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[14];
        this.mboundView14 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[15];
        this.mboundView15 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[2];
        this.mboundView2 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[3];
        this.mboundView3 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[4];
        this.mboundView4 = textView10;
        textView10.setTag(null);
        TextView textView11 = (TextView) bindings[5];
        this.mboundView5 = textView11;
        textView11.setTag(null);
        TextView textView12 = (TextView) bindings[6];
        this.mboundView6 = textView12;
        textView12.setTag(null);
        TextView textView13 = (TextView) bindings[7];
        this.mboundView7 = textView13;
        textView13.setTag(null);
        TextView textView14 = (TextView) bindings[8];
        this.mboundView8 = textView14;
        textView14.setTag(null);
        TextView textView15 = (TextView) bindings[9];
        this.mboundView9 = textView15;
        textView15.setTag(null);
        this.tvPrice.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
        } else if (121 == variableId) {
            setUsername((String) variable);
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

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setOldGame(GMGameBean OldGame) {
        this.mOldGame = OldGame;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(65);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setOldTitle(GMTitleBean OldTitle) {
        updateRegistration(0, OldTitle);
        this.mOldTitle = OldTitle;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(67);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setRole(GMRoleBean Role) {
        updateRegistration(1, Role);
        this.mRole = Role;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setUsername(String Username) {
        this.mUsername = Username;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(121);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setTitle(GMTitleBean Title) {
        updateRegistration(2, Title);
        this.mTitle = Title;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(114);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setOldRole(GMRoleBean OldRole) {
        updateRegistration(3, OldRole);
        this.mOldRole = OldRole;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(66);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBinding
    public void setGame(GMGameBean Game) {
        this.mGame = Game;
        synchronized (this) {
            this.mDirtyFlags |= 64;
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
        String tier_name;
        String tier_name2;
        String str;
        double d;
        double d2;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        int i;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String roleId;
        String roleName;
        String str17;
        String str18;
        String accountName;
        String roleName2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GMGameBean gMGameBean = this.mOldGame;
        GMTitleBean gMTitleBean = this.mOldTitle;
        GMRoleBean gMRoleBean = this.mRole;
        String str19 = this.mUsername;
        GMTitleBean gMTitleBean2 = this.mTitle;
        GMRoleBean gMRoleBean2 = this.mOldRole;
        GMGameBean gMGameBean2 = this.mGame;
        String name = ((j & 144) == 0 || gMGameBean == null) ? null : gMGameBean.getName();
        long j2 = j & 133;
        if (j2 != 0) {
            double price = gMTitleBean != null ? gMTitleBean.getPrice() : 0.0d;
            z = gMTitleBean == null;
            if (j2 != 0) {
                j = z ? j | 512 : j | 256;
            }
            price = gMTitleBean2 != null ? gMTitleBean2.getPrice() : 0.0d;
            d = price / 2.0d;
            d2 = price / 2.0d;
            String strValueOf = String.valueOf(Math.min(d, d2));
            tier_name = ((j & 129) == 0 || gMTitleBean == null) ? null : gMTitleBean.getTier_name();
            tier_name2 = ((j & 132) == 0 || gMTitleBean2 == null) ? null : gMTitleBean2.getTier_name();
            str = strValueOf;
        } else {
            tier_name = null;
            tier_name2 = null;
            str = null;
            d = 0.0d;
            d2 = 0.0d;
        }
        long j3 = j & 130;
        if (j3 != 0) {
            if (gMRoleBean != null) {
                String serviceCode = gMRoleBean.getServiceCode();
                String roleId2 = gMRoleBean.getRoleId();
                roleName2 = gMRoleBean.getRoleName();
                accountName = gMRoleBean.getAccountName();
                str3 = tier_name;
                str17 = serviceCode;
                str18 = roleId2;
            } else {
                str3 = tier_name;
                str17 = null;
                str18 = null;
                accountName = null;
                roleName2 = null;
            }
            str2 = name;
            str4 = "区服：" + str17;
            str5 = "角色id：" + str18;
            str6 = "小号：" + accountName;
            str7 = roleName2;
        } else {
            str2 = name;
            str3 = tier_name;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
        }
        int i2 = ((j & 160) > 0L ? 1 : ((j & 160) == 0L ? 0 : -1));
        long j4 = j & 136;
        if (j4 != 0) {
            if (gMRoleBean2 != null) {
                roleName = gMRoleBean2.getRoleName();
                String serviceCode2 = gMRoleBean2.getServiceCode();
                String accountName2 = gMRoleBean2.getAccountName();
                roleId = gMRoleBean2.getRoleId();
                i = i2;
                str9 = str19;
                str15 = accountName2;
                str10 = tier_name2;
                str16 = serviceCode2;
            } else {
                i = i2;
                str9 = str19;
                str10 = tier_name2;
                str15 = null;
                str16 = null;
                roleId = null;
                roleName = null;
            }
            str8 = str4;
            str11 = "区服：" + str16;
            str12 = "小号：" + str15;
            str13 = "角色id：" + roleId;
            str14 = roleName;
        } else {
            str8 = str4;
            i = i2;
            str9 = str19;
            str10 = tier_name2;
            str11 = null;
            str12 = null;
            str13 = null;
            str14 = null;
        }
        long j5 = j & 192;
        String name2 = (j5 == 0 || gMGameBean2 == null) ? null : gMGameBean2.getName();
        long j6 = 133 & j;
        String strValueOf2 = j6 != 0 ? z ? (j & 512) != 0 ? String.valueOf(price) : null : (j & 256) != 0 ? String.valueOf(Math.max(d2, price - d)) : null : null;
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str11);
            TextViewBindingAdapter.setText(this.mboundView3, str12);
            TextViewBindingAdapter.setText(this.mboundView5, str13);
            TextViewBindingAdapter.setText(this.mboundView6, str14);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, str6);
            TextViewBindingAdapter.setText(this.mboundView12, str5);
            TextViewBindingAdapter.setText(this.mboundView13, str7);
            TextViewBindingAdapter.setText(this.mboundView8, str8);
        }
        if (i != 0) {
            String str20 = str9;
            TextViewBindingAdapter.setText(this.mboundView11, str20);
            TextViewBindingAdapter.setText(this.mboundView4, str20);
        }
        if (j6 != 0) {
            TextViewBindingAdapter.setText(this.mboundView14, str);
            TextViewBindingAdapter.setText(this.tvPrice, strValueOf2);
        }
        if ((j & 132) != 0) {
            TextViewBindingAdapter.setText(this.mboundView15, str10);
        }
        if ((144 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
        if ((j & 129) != 0) {
            TextViewBindingAdapter.setText(this.mboundView7, str3);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView9, name2);
        }
    }
}
