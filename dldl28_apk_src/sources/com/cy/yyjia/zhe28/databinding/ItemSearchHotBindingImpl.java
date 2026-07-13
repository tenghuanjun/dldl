package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemSearchHotBindingImpl extends ItemSearchHotBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ShapeTextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.name, 8);
        sparseIntArray.put(R.id.desc, 9);
    }

    public ItemSearchHotBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemSearchHotBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (LinearLayout) bindings[9], (LinearLayout) bindings[8]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[3];
        this.mboundView3 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[4];
        this.mboundView4 = imageView4;
        imageView4.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[5];
        this.mboundView5 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView = (TextView) bindings[6];
        this.mboundView6 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[7];
        this.mboundView7 = textView2;
        textView2.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((GameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSearchHotBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSearchHotBinding
    public void setData(GameBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((GameBean) object, fieldId);
    }

    private boolean onChangeData(GameBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        SpannableString spannableString;
        String str2;
        String showName;
        OnClickListenerImpl value;
        SpannableString descNew;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        GameBean gameBean = this.mData;
        long j2 = 6 & j;
        boolean z4 = false;
        if (j2 != 0) {
            z2 = i != 2;
            z3 = i != 1;
            z = i != 0;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        long j3 = 5 & j;
        OnClickListenerImpl onClickListenerImpl = null;
        String str3 = null;
        if (j3 != 0) {
            if (gameBean != null) {
                OnClickListenerImpl onClickListenerImpl2 = this.mDataGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl2 == null) {
                    onClickListenerImpl2 = new OnClickListenerImpl();
                    this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl2;
                }
                value = onClickListenerImpl2.setValue(gameBean);
                String name_suffix = gameBean.getName_suffix();
                showName = gameBean.getShowName();
                String icon = gameBean.getIcon();
                descNew = gameBean.getDescNew();
                str = icon;
                str3 = name_suffix;
            } else {
                str = null;
                value = null;
                descNew = null;
                showName = null;
            }
            boolean zIsEmpty = TextUtils.isEmpty(str3);
            spannableString = descNew;
            str2 = str3;
            onClickListenerImpl = value;
            z4 = zIsEmpty;
        } else {
            str = null;
            spannableString = null;
            str2 = null;
            showName = null;
        }
        if (j3 != 0) {
            this.mboundView0.setOnClickListener(onClickListenerImpl);
            DataBindingHelper.setGameIcon(this.mboundView1, str);
            DataBindingHelper.setViewGone(this.mboundView5, z4);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
            TextViewBindingAdapter.setText(this.mboundView6, showName);
            TextViewBindingAdapter.setText(this.mboundView7, spannableString);
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z);
            DataBindingHelper.setViewGone(this.mboundView3, z3);
            DataBindingHelper.setViewGone(this.mboundView4, z2);
        }
        if ((j & 4) != 0) {
            DataBindingHelper.setSelected(this.mboundView6, true);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private GameBean value;

        public OnClickListenerImpl setValue(GameBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.gotoGame(arg0);
        }
    }
}
