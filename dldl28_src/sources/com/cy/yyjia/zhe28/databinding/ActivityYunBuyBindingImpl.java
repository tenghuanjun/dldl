package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.cy.yyjia.zhe28.domain.YunPrice;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityYunBuyBindingImpl extends ActivityYunBuyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final LinearLayout mboundView1;
    private final TextView mboundView3;
    private final LinearLayout mboundView4;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final ImageView mboundView8;
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 11);
        sparseIntArray.put(R.id.rv, 12);
        sparseIntArray.put(R.id.iv_add, 13);
        sparseIntArray.put(R.id.btn_zfb, 14);
        sparseIntArray.put(R.id.btn_wx, 15);
    }

    public ActivityYunBuyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivityYunBuyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (Button) bindings[10], (FrameLayout) bindings[15], (FrameLayout) bindings[14], (ImageView) bindings[13], (ImageView) bindings[2], (Navigation) bindings[11], (RecyclerView) bindings[12], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.btnBuy.setTag(null);
        this.ivSubtract.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[1];
        this.mboundView1 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout3;
        linearLayout3.setTag(null);
        TextView textView2 = (TextView) bindings[6];
        this.mboundView6 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[7];
        this.mboundView7 = textView3;
        textView3.setTag(null);
        ImageView imageView = (ImageView) bindings[8];
        this.mboundView8 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[9];
        this.mboundView9 = imageView2;
        imageView2.setTag(null);
        this.tvYunDevice.setTag(null);
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
        if (4 == variableId) {
            setAlipay(((Boolean) variable).booleanValue());
        } else if (77 == variableId) {
            setPrice((YunPrice) variable);
        } else if (63 == variableId) {
            setNum(((Integer) variable).intValue());
        } else if (85 == variableId) {
            setRenew(((Boolean) variable).booleanValue());
        } else {
            if (127 != variableId) {
                return false;
            }
            setYun((YunIndexBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunBuyBinding
    public void setAlipay(boolean Alipay) {
        this.mAlipay = Alipay;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunBuyBinding
    public void setPrice(YunPrice Price) {
        updateRegistration(0, Price);
        this.mPrice = Price;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(77);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunBuyBinding
    public void setNum(int Num) {
        this.mNum = Num;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(63);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunBuyBinding
    public void setRenew(boolean Renew) {
        this.mRenew = Renew;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(85);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityYunBuyBinding
    public void setYun(YunIndexBean Yun) {
        this.mYun = Yun;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(127);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangePrice((YunPrice) object, fieldId);
    }

    private boolean onChangePrice(YunPrice Price, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010e  */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r9v5 */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityYunBuyBindingImpl.executeBindings():void");
    }
}
