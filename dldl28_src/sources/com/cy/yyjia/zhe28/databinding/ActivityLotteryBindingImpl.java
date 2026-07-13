package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameToolBean;
import com.cy.yyjia.zhe28.domain.LotteryInfoBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityLotteryBindingImpl extends ActivityLotteryBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final ProgressBar mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView13;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_shop, 17);
        sparseIntArray.put(R.id.vf, 18);
        sparseIntArray.put(R.id.rv, 19);
        sparseIntArray.put(R.id.tv_rule, 20);
        sparseIntArray.put(R.id.tv_record, 21);
    }

    public ActivityLotteryBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private ActivityLotteryBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[5], (ShapeTextView) bindings[6], (RecyclerView) bindings[19], (RecyclerView) bindings[14], (ShapeTextView) bindings[15], (ShapeTextView) bindings[16], (ShapeTextView) bindings[12], (TextView) bindings[21], (TextView) bindings[20], (ShapeTextView) bindings[17], (AdapterViewFlipper) bindings[18]);
        this.mDirtyFlags = -1L;
        this.btn1.setTag(null);
        this.btn10.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ProgressBar progressBar = (ProgressBar) bindings[10];
        this.mboundView10 = progressBar;
        progressBar.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[4];
        this.mboundView4 = imageView4;
        imageView4.setTag(null);
        TextView textView3 = (TextView) bindings[7];
        this.mboundView7 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[8];
        this.mboundView8 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[9];
        this.mboundView9 = textView5;
        textView5.setTag(null);
        this.rvBottom.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tvPrize.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
            setData((LotteryInfoBean) variable);
        } else {
            if (118 != variableId) {
                return false;
            }
            setType(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLotteryBinding
    public void setData(LotteryInfoBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLotteryBinding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(118);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        int total;
        List<GameToolBean> bottom_module;
        String str4;
        String str5;
        String str6;
        String btnStr;
        String str7;
        int i;
        boolean z;
        boolean z2;
        boolean z3;
        int colorFromResource;
        String user_credit;
        LotteryInfoBean.Prize prize_module;
        LotteryInfoBean.Config config;
        int target;
        String coninType;
        boolean enable;
        String title;
        String desc;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LotteryInfoBean lotteryInfoBean = this.mData;
        int i2 = this.mType;
        if ((j & 5) != 0) {
            if (lotteryInfoBean != null) {
                prize_module = lotteryInfoBean.getPrize_module();
                bottom_module = lotteryInfoBean.getBottom_module();
                config = lotteryInfoBean.getConfig();
                user_credit = lotteryInfoBean.getUser_credit();
            } else {
                user_credit = null;
                prize_module = null;
                bottom_module = null;
                config = null;
            }
            if (prize_module != null) {
                btnStr = prize_module.getBtnStr();
                target = prize_module.getTarget();
                coninType = prize_module.getConinType();
                enable = prize_module.getEnable();
                title = prize_module.getTitle();
                desc = prize_module.getDesc();
                total = prize_module.getTotal();
            } else {
                total = 0;
                btnStr = null;
                target = 0;
                coninType = null;
                enable = false;
                title = null;
                desc = null;
            }
            LotteryInfoBean.Use use = config != null ? config.getUse() : null;
            String str8 = total + "/";
            String one = use != null ? use.getOne() : null;
            str4 = str8 + target;
            str6 = one + "积分*1";
            i = target;
            z = enable;
            str3 = desc;
            str7 = user_credit;
            str5 = one + "积分*10";
            str = coninType;
            str2 = title;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            total = 0;
            bottom_module = null;
            str4 = null;
            str5 = null;
            str6 = null;
            btnStr = null;
            str7 = null;
            i = 0;
            z = false;
        }
        long j2 = j & 6;
        if (j2 != 0) {
            z2 = i2 == 2;
            boolean z4 = i2 == 1;
            if (j2 != 0) {
                j |= z4 ? 16L : 8L;
            }
            colorFromResource = z4 ? -12608520 : getColorFromResource(this.mboundView7, R.color.colorPrimary);
            z3 = z4;
        } else {
            z2 = false;
            z3 = false;
            colorFromResource = 0;
        }
        String str9 = btnStr;
        if ((j & 6) != 0) {
            DataBindingHelper.setSelected(this.btn1, z2);
            DataBindingHelper.setSelected(this.btn10, z2);
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            DataBindingHelper.setViewGone(this.mboundView2, z3);
            DataBindingHelper.setViewGone(this.mboundView3, z2);
            DataBindingHelper.setViewGone(this.mboundView4, z3);
            this.mboundView7.setTextColor(colorFromResource);
            DataBindingHelper.setSelected(this.tv1, z3);
            DataBindingHelper.setSelected(this.tv2, z2);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.btn1, str6);
            TextViewBindingAdapter.setText(this.btn10, str5);
            this.mboundView10.setMax(i);
            this.mboundView10.setProgress(total);
            TextViewBindingAdapter.setText(this.mboundView11, str4);
            TextViewBindingAdapter.setText(this.mboundView13, str3);
            TextViewBindingAdapter.setText(this.mboundView7, str7);
            TextViewBindingAdapter.setText(this.mboundView8, str2);
            TextViewBindingAdapter.setText(this.mboundView9, str);
            DataBindingHelper.setRvData(this.rvBottom, bottom_module);
            this.tvPrize.setEnabled(z);
            TextViewBindingAdapter.setText(this.tvPrize, str9);
        }
    }
}
