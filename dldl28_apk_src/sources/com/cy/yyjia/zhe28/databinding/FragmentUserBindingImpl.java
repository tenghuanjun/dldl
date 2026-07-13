package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentUserBindingImpl extends FragmentUserBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final TextView mboundView10;
    private final TextView mboundView13;
    private final ConstraintLayout mboundView14;
    private final ImageView mboundView17;
    private final TextView mboundView18;
    private final TextView mboundView19;
    private final TextView mboundView24;
    private final TextView mboundView26;
    private final TextView mboundView28;
    private final TextView mboundView30;
    private final TextView mboundView32;
    private final TextView mboundView33;
    private final LinearLayout mboundView34;
    private final LinearLayout mboundView42;
    private final TextView mboundView43;
    private final LinearLayout mboundView48;
    private final ShapeTextView mboundView5;
    private final TextView mboundView53;
    private final FrameLayout mboundView56;
    private final ConstraintLayout mboundView7;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cl, 59);
        sparseIntArray.put(R.id.iv_tag2, 60);
        sparseIntArray.put(R.id.iv_tag3, 61);
        sparseIntArray.put(R.id.user_icon_de, 62);
        sparseIntArray.put(R.id.tv_login, 63);
        sparseIntArray.put(R.id.tv1, 64);
        sparseIntArray.put(R.id.tv2, 65);
    }

    public FragmentUserBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 66, sIncludes, sViewsWithIds));
    }

    private FragmentUserBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[4], (TextView) bindings[1], (ImageView) bindings[2], (ConstraintLayout) bindings[59], (RelativeLayout) bindings[22], (RelativeLayout) bindings[21], (ImageView) bindings[3], (ImageView) bindings[20], (ShapeView) bindings[60], (ShapeView) bindings[61], (ImageView) bindings[50], (ImageView) bindings[52], (ShapeLinearLayout) bindings[31], (LinearLayout) bindings[29], (LinearLayout) bindings[23], (LinearLayout) bindings[25], (LinearLayout) bindings[16], (LinearLayout) bindings[27], (NestedScrollView) bindings[0], (RelativeLayout) bindings[6], (RecyclerView) bindings[40], (RecyclerView) bindings[45], (TextView) bindings[64], (TextView) bindings[65], (TextView) bindings[51], (ShapeTextView) bindings[35], (ShapeTextView) bindings[36], (ShapeTextView) bindings[37], (ShapeTextView) bindings[38], (TextView) bindings[63], (TextView) bindings[15], (TextView) bindings[41], (TextView) bindings[9], (ImageView) bindings[11], (ImageView) bindings[12], (ShapeTextView) bindings[58], (TextView) bindings[46], (TextView) bindings[39], (TextView) bindings[57], (TextView) bindings[49], (TextView) bindings[44], (TextView) bindings[47], (TextView) bindings[54], (TextView) bindings[55], (ImageView) bindings[8], (ImageView) bindings[62]);
        this.mDirtyFlags = -1L;
        this.btnDownload.setTag(null);
        this.btnService.setTag(null);
        this.btnSetting.setTag(null);
        this.clMonth.setTag(null);
        this.clSqk.setTag(null);
        this.ivMessage.setTag(null);
        this.ivSubscribe.setTag(null);
        this.ivYunGo.setTag(null);
        this.ivYunQuestion.setTag(null);
        this.llCompany.setTag(null);
        this.llFlb.setTag(null);
        this.llPoint.setTag(null);
        this.llPtb.setTag(null);
        this.llVip.setTag(null);
        this.llVoucher.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[14];
        this.mboundView14 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[17];
        this.mboundView17 = imageView;
        imageView.setTag(null);
        TextView textView3 = (TextView) bindings[18];
        this.mboundView18 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[19];
        this.mboundView19 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[24];
        this.mboundView24 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[26];
        this.mboundView26 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[28];
        this.mboundView28 = textView7;
        textView7.setTag(null);
        TextView textView8 = (TextView) bindings[30];
        this.mboundView30 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[32];
        this.mboundView32 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[33];
        this.mboundView33 = textView10;
        textView10.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[34];
        this.mboundView34 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[42];
        this.mboundView42 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView11 = (TextView) bindings[43];
        this.mboundView43 = textView11;
        textView11.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[48];
        this.mboundView48 = linearLayout3;
        linearLayout3.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[5];
        this.mboundView5 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView12 = (TextView) bindings[53];
        this.mboundView53 = textView12;
        textView12.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[56];
        this.mboundView56 = frameLayout;
        frameLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[7];
        this.mboundView7 = constraintLayout2;
        constraintLayout2.setTag(null);
        this.nsv.setTag(null);
        this.reUser.setTag(null);
        this.rvGame.setTag(null);
        this.rvYunBlock.setTag(null);
        this.tvDeviceName.setTag(null);
        this.tvGame1.setTag(null);
        this.tvGame2.setTag(null);
        this.tvGame3.setTag(null);
        this.tvGame4.setTag(null);
        this.tvLogin2.setTag(null);
        this.tvMore.setTag(null);
        this.tvNickname.setTag(null);
        this.tvSqk.setTag(null);
        this.tvYk.setTag(null);
        this.tvYunAdd.setTag(null);
        this.tvYunBuy.setTag(null);
        this.tvYunDevice.setTag(null);
        this.tvYunExit.setTag(null);
        this.tvYunGame.setTag(null);
        this.tvYunGames.setTag(null);
        this.tvYunLesson.setTag(null);
        this.tvYunRefresh.setTag(null);
        this.tvYunRenew.setTag(null);
        this.userIcon.setTag(null);
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
        if (127 == variableId) {
            setYun((YunIndexBean) variable);
        } else if (56 == variableId) {
            setMessageNum((String) variable);
        } else if (23 == variableId) {
            setData((UserBean) variable);
        } else if (68 == variableId) {
            setOnClick((View.OnClickListener) variable);
        } else if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (16 != variableId) {
                return false;
            }
            setCompany(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentUserBinding
    public void setYun(YunIndexBean Yun) {
        this.mYun = Yun;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(127);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentUserBinding
    public void setMessageNum(String MessageNum) {
        this.mMessageNum = MessageNum;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(56);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentUserBinding
    public void setData(UserBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentUserBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentUserBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentUserBinding
    public void setCompany(boolean Company) {
        this.mCompany = Company;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:218:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentUserBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private View.OnClickListener value;

        public OnClickListenerImpl setValue(View.OnClickListener value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
