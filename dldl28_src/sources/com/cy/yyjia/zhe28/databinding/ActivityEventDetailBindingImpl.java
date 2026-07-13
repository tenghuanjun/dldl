package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameEventDetailBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityEventDetailBindingImpl extends ActivityEventDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final Space mboundView3;
    private final CardView mboundView4;
    private final ImageView mboundView5;
    private final Space mboundView6;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.wv2, 7);
        sparseIntArray.put(R.id.navigation, 8);
        sparseIntArray.put(R.id.csl, 9);
        sparseIntArray.put(R.id.wv, 10);
    }

    public ActivityEventDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ActivityEventDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConsecutiveScrollerLayout) bindings[9], (Navigation) bindings[8], (WebView) bindings[10], (WebView) bindings[7]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        Space space = (Space) bindings[3];
        this.mboundView3 = space;
        space.setTag(null);
        CardView cardView = (CardView) bindings[4];
        this.mboundView4 = cardView;
        cardView.setTag(null);
        ImageView imageView = (ImageView) bindings[5];
        this.mboundView5 = imageView;
        imageView.setTag(null);
        Space space2 = (Space) bindings[6];
        this.mboundView6 = space2;
        space2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (23 != variableId) {
            return false;
        }
        setData((GameEventDetailBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityEventDetailBinding
    public void setData(GameEventDetailBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean zIsEmpty;
        String title;
        String pic;
        String dateline;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameEventDetailBean gameEventDetailBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (gameEventDetailBean != null) {
                title = gameEventDetailBean.getTitle();
                pic = gameEventDetailBean.getPic();
                dateline = gameEventDetailBean.getDateline();
            } else {
                title = null;
                pic = null;
                dateline = null;
            }
            zIsEmpty = TextUtils.isEmpty(pic);
        } else {
            zIsEmpty = false;
            title = null;
            pic = null;
            dateline = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, title);
            TextViewBindingAdapter.setText(this.mboundView2, dateline);
            DataBindingHelper.setViewGone(this.mboundView3, zIsEmpty);
            DataBindingHelper.setViewGone(this.mboundView4, zIsEmpty);
            DataBindingHelper.setImg(this.mboundView5, pic, null);
            DataBindingHelper.setViewGone(this.mboundView6, zIsEmpty);
        }
    }
}
