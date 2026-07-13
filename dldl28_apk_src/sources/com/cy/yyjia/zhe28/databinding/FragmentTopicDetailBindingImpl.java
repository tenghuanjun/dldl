package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentTopicDetailBindingImpl extends FragmentTopicDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mOnClickOnClickAndroidViewViewOnClickListener;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final WancmsStandardPlayer mboundView16;
    private final CardView mboundView19;
    private final ImageView mboundView2;
    private final FrameLayout mboundView4;
    private final ImageView mboundView5;
    private final RelativeLayout mboundView7;
    private final ImageView mboundView8;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.vf, 22);
    }

    public FragmentTopicDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private FragmentTopicDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[9], (ImageView) bindings[17], (ImageView) bindings[14], (ImageView) bindings[15], (ImageView) bindings[12], (ImageView) bindings[11], (ImageView) bindings[20], (ImageView) bindings[6], (ImageView) bindings[3], (ImageView) bindings[21], (RecyclerView) bindings[10], (RecyclerView) bindings[18], (RecyclerView) bindings[13], (AdapterViewFlipper) bindings[22]);
        this.mDirtyFlags = -1L;
        this.bgLottery.setTag(null);
        this.bgPic.setTag(null);
        this.bgVideo.setTag(null);
        this.bgVideo2.setTag(null);
        this.btnRecord.setTag(null);
        this.btnRule.setTag(null);
        this.iv.setTag(null);
        this.ivCoupon.setTag(null);
        this.ivDownload.setTag(null);
        this.ivDownload2.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        WancmsStandardPlayer wancmsStandardPlayer = (WancmsStandardPlayer) bindings[16];
        this.mboundView16 = wancmsStandardPlayer;
        wancmsStandardPlayer.setTag(null);
        CardView cardView = (CardView) bindings[19];
        this.mboundView19 = cardView;
        cardView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[4];
        this.mboundView4 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView3 = (ImageView) bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[7];
        this.mboundView7 = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView4 = (ImageView) bindings[8];
        this.mboundView8 = imageView4;
        imageView4.setTag(null);
        this.rvLottery.setTag(null);
        this.rvPic.setTag(null);
        this.rvTask.setTag(null);
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
            setData((TopicDetailBean) variable);
        } else {
            if (68 != variableId) {
                return false;
            }
            setOnClick((View.OnClickListener) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentTopicDetailBinding
    public void setData(TopicDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.FragmentTopicDetailBinding
    public void setOnClick(View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(68);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01d3  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 724
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.FragmentTopicDetailBindingImpl.executeBindings():void");
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
