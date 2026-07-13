package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.NewGameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeScheduleBindingImpl extends ItemHomeScheduleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGameGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView10;
    private final ShapeLinearLayout mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView13;
    private final TextView mboundView4;
    private final LinearLayout mboundView5;
    private final LinearLayout mboundView6;
    private final TextView mboundView7;
    private final FrameLayout mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_xc, 14);
    }

    public ItemHomeScheduleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private ItemHomeScheduleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[1], (TextView) bindings[2], (ShapeTextView) bindings[3], (ShapeLinearLayout) bindings[14]);
        this.mDirtyFlags = -1L;
        this.iv.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[11];
        this.mboundView11 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[13];
        this.mboundView13 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[5];
        this.mboundView5 = linearLayout2;
        linearLayout2.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout3;
        linearLayout3.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[8];
        this.mboundView8 = frameLayout;
        frameLayout.setTag(null);
        TextView textView6 = (TextView) bindings[9];
        this.mboundView9 = textView6;
        textView6.setTag(null);
        this.f467tv.setTag(null);
        this.tv2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
            setData((NewGameBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeScheduleBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeScheduleBinding
    public void setData(NewGameBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataGame((GameBean) object, fieldId);
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
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
        long j2;
        long j3;
        String str;
        boolean zEquals;
        SpannableString spannableString;
        String str2;
        boolean z;
        boolean zEquals2;
        String tagStr;
        String str3;
        boolean z2;
        String str4;
        OnClickListenerImpl value;
        boolean z3;
        String str5;
        boolean z4;
        String serverName;
        boolean z5;
        boolean z6;
        boolean zCanShowDiscount;
        String str6;
        String str7;
        SpannableString descStr;
        String showDiscount;
        String name;
        String icon;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NewGameBean newGameBean = this.mData;
        long j4 = j & 13;
        if (j4 != 0) {
            GameBean game = newGameBean != null ? newGameBean.getGame() : null;
            updateRegistration(0, game);
            if (game != null) {
                tagStr = game.getTagStr();
                String continueRechargeDiscount = game.getContinueRechargeDiscount();
                descStr = game.getDescStr();
                OnClickListenerImpl onClickListenerImpl = this.mDataGameGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataGameGotoGameAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(game);
                String firstRechargeDiscount = game.getFirstRechargeDiscount();
                String recommendType = game.getRecommendType();
                showDiscount = game.getShowDiscount();
                name = game.getName();
                icon = game.getIcon();
                zCanShowDiscount = game.canShowDiscount();
                str7 = continueRechargeDiscount;
                str3 = firstRechargeDiscount;
                str6 = recommendType;
            } else {
                zCanShowDiscount = false;
                tagStr = null;
                str3 = null;
                str6 = null;
                value = null;
                str7 = null;
                descStr = null;
                showDiscount = null;
                name = null;
                icon = null;
            }
            zEquals = tagStr != null ? tagStr.equals("") : false;
            String str8 = str7 + "折";
            boolean zEquals3 = "recommend".equals(str6);
            z = !zCanShowDiscount;
            zEquals2 = str3 != null ? str3.equals(str7) : false;
            if (j4 != 0) {
                j |= zEquals2 ? 128L : 64L;
            }
            z2 = !zEquals3;
            z3 = !zEquals2;
            j2 = 0;
            if ((j & 13) != 0) {
                j |= !zEquals2 ? 32L : 16L;
            }
            if ((j & 12) == 0 || newGameBean == null) {
                str5 = str8;
                spannableString = descStr;
                str4 = showDiscount;
                str = icon;
                serverName = null;
                z4 = zEquals3;
                str2 = name;
            } else {
                str5 = str8;
                spannableString = descStr;
                str4 = showDiscount;
                serverName = newGameBean.getServerName();
                z4 = zEquals3;
                str2 = name;
                str = icon;
            }
            j3 = 13;
        } else {
            j2 = 0;
            j3 = 13;
            str = null;
            zEquals = false;
            spannableString = null;
            str2 = null;
            z = false;
            zEquals2 = false;
            tagStr = null;
            str3 = null;
            z2 = false;
            str4 = null;
            value = null;
            z3 = false;
            str5 = null;
            z4 = false;
            serverName = null;
        }
        long j5 = j & j3;
        if (j5 != j2) {
            z6 = z3 ? true : z4;
            if (zEquals2) {
                z4 = true;
            }
            z5 = z4;
        } else {
            z5 = false;
            z6 = false;
        }
        long j6 = j;
        if (j5 != j2) {
            DataBindingHelper.setGameIcon(this.iv, str);
            this.mboundView0.setOnClickListener(value);
            TextViewBindingAdapter.setText(this.mboundView10, str5);
            DataBindingHelper.setViewGone(this.mboundView11, z6);
            TextViewBindingAdapter.setText(this.mboundView12, str3);
            TextViewBindingAdapter.setText(this.mboundView4, spannableString);
            DataBindingHelper.setViewGone(this.mboundView5, z);
            DataBindingHelper.setViewGone(this.mboundView6, z2);
            TextViewBindingAdapter.setText(this.mboundView7, str4);
            DataBindingHelper.setViewGone(this.mboundView8, z5);
            TextViewBindingAdapter.setText(this.mboundView9, str3);
            TextViewBindingAdapter.setText(this.f467tv, str2);
            DataBindingHelper.setViewGone(this.tv2, zEquals);
            TextViewBindingAdapter.setText(this.tv2, tagStr);
        }
        if ((j6 & 12) != 0) {
            TextViewBindingAdapter.setText(this.mboundView13, serverName);
        }
        if ((j6 & 8) != 0) {
            DataBindingHelper.setSelected(this.f467tv, true);
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
