package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.SanbaoRuleBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivitySanbaoBindingImpl extends ActivitySanbaoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final View mboundView11;
    private final ImageView mboundView2;
    private final NestedScrollView mboundView3;
    private final ImageView mboundView4;
    private final RelativeLayout mboundView5;
    private final View mboundView7;
    private final View mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_rule, 14);
        sparseIntArray.put(R.id.rv, 15);
    }

    public ActivitySanbaoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ActivitySanbaoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (FrameLayout) bindings[6], (ImageView) bindings[13], (RecyclerView) bindings[15], (TextView) bindings[8], (TextView) bindings[10], (TextView) bindings[12], (ShapeTextView) bindings[14]);
        this.mDirtyFlags = -1L;
        this.body.setTag(null);
        this.ivSearch.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        View view = (View) bindings[11];
        this.mboundView11 = view;
        view.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[3];
        this.mboundView3 = nestedScrollView;
        nestedScrollView.setTag(null);
        ImageView imageView3 = (ImageView) bindings[4];
        this.mboundView4 = imageView3;
        imageView3.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[5];
        this.mboundView5 = relativeLayout;
        relativeLayout.setTag(null);
        View view2 = (View) bindings[7];
        this.mboundView7 = view2;
        view2.setTag(null);
        View view3 = (View) bindings[9];
        this.mboundView9 = view3;
        view3.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tv3.setTag(null);
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
        if (117 == variableId) {
            setTopic((SanbaoRuleBean.Topic2) variable);
        } else {
            if (74 != variableId) {
                return false;
            }
            setPosition(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivitySanbaoBinding
    public void setTopic(SanbaoRuleBean.Topic2 Topic) {
        this.mTopic = Topic;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(117);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivitySanbaoBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String title;
        String adpic;
        String str;
        String str2;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        boolean z5;
        boolean z6;
        int i2;
        int i3;
        int colorFromResource;
        int i4;
        int i5;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        SanbaoRuleBean.Topic2 topic2 = this.mTopic;
        int i6 = this.mPosition;
        if ((j & 5) == 0 || topic2 == null) {
            title = null;
            adpic = null;
        } else {
            adpic = topic2.getAdpic();
            title = topic2.getTitle();
        }
        long j2 = j & 6;
        if (j2 != 0) {
            boolean z7 = i6 != 2;
            boolean z8 = i6 == 2;
            boolean z9 = i6 != 3;
            boolean z10 = i6 != 1;
            boolean z11 = i6 == 1;
            boolean z12 = i6 == 3;
            if (j2 != 0) {
                j |= z8 ? 4096L : 2048L;
            }
            if ((j & 6) != 0) {
                j |= z11 ? 17728L : 8864L;
            }
            if ((j & 6) != 0) {
                j |= z12 ? 16L : 8L;
            }
            int i7 = z8 ? 20 : 16;
            TextView textView = this.tv3;
            int colorFromResource2 = z11 ? getColorFromResource(textView, R.color.colorWhite) : getColorFromResource(textView, R.color.colorBackground);
            int i8 = z11 ? 20 : 16;
            TextView textView2 = this.tv1;
            int colorFromResource3 = z11 ? getColorFromResource(textView2, R.color.colorWhite) : getColorFromResource(textView2, R.color.colorBackground);
            colorFromResource = z11 ? getColorFromResource(this.tv2, R.color.colorWhite) : getColorFromResource(this.tv2, R.color.colorBackground);
            i3 = colorFromResource3;
            z2 = z12;
            i5 = colorFromResource2;
            z4 = z7;
            i4 = z12 ? 20 : 16;
            z3 = z9;
            z5 = z10;
            z6 = z11;
            str2 = adpic;
            i = i7;
            str = title;
            z = z8;
            i2 = i8;
        } else {
            str = title;
            str2 = adpic;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            i = 0;
            z5 = false;
            z6 = false;
            i2 = 0;
            i3 = 0;
            colorFromResource = 0;
            i4 = 0;
            i5 = 0;
        }
        if ((6 & j) != 0) {
            DataBindingHelper.setViewGone(this.body, z5);
            DataBindingHelper.setViewGone(this.ivSearch, z2);
            DataBindingHelper.setViewGone(this.mboundView1, z5);
            DataBindingHelper.setViewGone(this.mboundView11, z3);
            DataBindingHelper.setViewGone(this.mboundView2, z4);
            DataBindingHelper.setViewGone(this.mboundView3, z3);
            DataBindingHelper.setViewGone(this.mboundView5, z2);
            DataBindingHelper.setViewGone(this.mboundView7, z5);
            DataBindingHelper.setViewGone(this.mboundView9, z4);
            DataBindingHelper.setBold(this.tv1, z6);
            DataBindingHelper.setSelected(this.tv1, z6);
            DataBindingHelper.setSelectedSize(this.tv1, i2);
            this.tv1.setTextColor(i3);
            DataBindingHelper.setBold(this.tv2, z);
            DataBindingHelper.setSelected(this.tv2, z);
            DataBindingHelper.setSelectedSize(this.tv2, i);
            this.tv2.setTextColor(colorFromResource);
            DataBindingHelper.setBold(this.tv3, z2);
            DataBindingHelper.setSelected(this.tv3, z2);
            DataBindingHelper.setSelectedSize(this.tv3, i4);
            this.tv3.setTextColor(i5);
        }
        if ((j & 5) != 0) {
            DataBindingHelper.setImg(this.mboundView4, str2, null);
            TextViewBindingAdapter.setText(this.tv3, str);
        }
    }
}
