package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BossServerBean;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;
import com.cy.yyjia.zhe28.domain.MonthlyTaskBean;
import com.cy.yyjia.zhe28.domain.MonthlyTaskNavBean;
import com.cy.yyjia.zhe28.domain.QiandaoBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WelfareBean3;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentWelfare3BindingImpl extends FragmentWelfare3Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final NestedScrollView mboundView0;
    private final TextView mboundView15;
    private final TextView mboundView21;
    private final TextView mboundView25;
    private final TextView mboundView3;
    private final ImageView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.banner, 33);
        sparseIntArray.put(R.id.vf, 34);
    }

    public FragmentWelfare3BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 35, sIncludes, sViewsWithIds));
    }

    private FragmentWelfare3BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ConvenientBanner) bindings[33], (ShapeTextView) bindings[16], (ShapeTextView) bindings[22], (ShapeTextView) bindings[26], (TextView) bindings[11], (TextView) bindings[12], (TextView) bindings[13], (TextView) bindings[18], (TextView) bindings[19], (ImageView) bindings[29], (ImageView) bindings[30], (ConstraintLayout) bindings[1], (ImageView) bindings[8], (ImageView) bindings[9], (ImageView) bindings[2], (ProgressBar) bindings[7], (RecyclerView) bindings[14], (RecyclerView) bindings[20], (RecyclerView) bindings[24], (RecyclerView) bindings[32], (RecyclerView) bindings[28], (ShapeTextView) bindings[31], (ShapeTextView) bindings[27], (ShapeTextView) bindings[23], (ShapeTextView) bindings[10], (TextView) bindings[6], (ShapeTextView) bindings[17], (TextView) bindings[4], (AdapterViewFlipper) bindings[34]);
        this.mDirtyFlags = -1L;
        this.btnReceive1.setTag(null);
        this.btnReceive2.setTag(null);
        this.btnReceive3.setTag(null);
        this.btnTask1.setTag("0");
        this.btnTask12.setTag("1");
        this.btnTask13.setTag("2");
        this.btnTask2.setTag("0");
        this.btnTask22.setTag("1");
        this.clMonth.setTag(null);
        this.clSqk.setTag(null);
        this.clUser.setTag(null);
        this.ivBbs.setTag(null);
        this.ivInvite.setTag(null);
        this.ivUser.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        TextView textView = (TextView) bindings[15];
        this.mboundView15 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[21];
        this.mboundView21 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[25];
        this.mboundView25 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[3];
        this.mboundView3 = textView4;
        textView4.setTag(null);
        ImageView imageView = (ImageView) bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag(null);
        this.pb.setTag(null);
        this.rv1.setTag(null);
        this.rv2.setTag(null);
        this.rv3.setTag(null);
        this.rv4.setTag(null);
        this.rv5.setTag(null);
        this.tvBaofu.setTag(null);
        this.tvBoss.setTag(null);
        this.tvDailyCoupon.setTag(null);
        this.tvDailyTask.setTag(null);
        this.tvExp.setTag(null);
        this.tvMonthlyTask.setTag(null);
        this.tvName.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4096L;
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
        if (62 == variableId) {
            setNav((MonthlyTaskNavBean) variable);
        } else if (59 == variableId) {
            setMonth((MonthlyTaskBean) variable);
        } else if (82 == variableId) {
            setQiandao((QiandaoBean) variable);
        } else if (24 == variableId) {
            setData3((DailyCouponBean) variable);
        } else if (108 == variableId) {
            setTask1(((Integer) variable).intValue());
        } else if (23 == variableId) {
            setData((WelfareBean3) variable);
        } else if (119 == variableId) {
            setUser((UserBean) variable);
        } else if (68 == variableId) {
            setOnClick((View.OnClickListener) variable);
        } else if (109 == variableId) {
            setTask2(((Integer) variable).intValue());
        } else {
            if (8 != variableId) {
                return false;
            }
            setBoss((BossServerBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setNav(MonthlyTaskNavBean Nav) {
        this.mNav = Nav;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setMonth(MonthlyTaskBean Month) {
        this.mMonth = Month;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(59);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setQiandao(QiandaoBean Qiandao) {
        this.mQiandao = Qiandao;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(82);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setData3(DailyCouponBean Data3) {
        this.mData3 = Data3;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(24);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setTask1(int Task1) {
        this.mTask1 = Task1;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(108);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setData(WelfareBean3 Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setUser(UserBean User) {
        this.mUser = User;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(119);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setTask2(int Task2) {
        this.mTask2 = Task2;
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        notifyPropertyChanged(109);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentWelfare3Binding
    public void setBoss(BossServerBean Boss) {
        this.mBoss = Boss;
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        notifyPropertyChanged(8);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeNavListGetInt0((MonthlyTaskNavBean.ListBean) object, fieldId);
        }
        if (localFieldId != 1) {
            return false;
        }
        return onChangeNavListGetInt1((MonthlyTaskNavBean.ListBean) object, fieldId);
    }

    private boolean onChangeNavListGetInt0(MonthlyTaskNavBean.ListBean NavListGetInt0, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeNavListGetInt1(MonthlyTaskNavBean.ListBean NavListGetInt1, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006c  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentWelfare3BindingImpl.executeBindings():void");
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
