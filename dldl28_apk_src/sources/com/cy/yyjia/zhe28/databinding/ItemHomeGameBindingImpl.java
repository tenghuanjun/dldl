package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeGameBindingImpl extends ItemHomeGameBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final LayoutGameIconBinding mboundView01;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final ShapeTextView mboundView7;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon"}, new int[]{8}, new int[]{R.layout.layout_game_icon});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.name, 9);
    }

    public ItemHomeGameBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemHomeGameBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (LinearLayout) bindings[9]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[8];
        this.mboundView01 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[6];
        this.mboundView6 = textView6;
        textView6.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[7];
        this.mboundView7 = shapeTextView;
        shapeTextView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        this.mboundView01.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView01.hasPendingBindings();
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

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeGameBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeGameBinding
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
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView01.setLifecycleOwner(lifecycleOwner);
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
        String strValueOf;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String str;
        String showName;
        OnClickListenerImpl onClickListenerImpl;
        int playNum;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        GameBean gameBean = this.mData;
        long j2 = 6 & j;
        OnClickListenerImpl onClickListenerImpl2 = null;
        String str2 = null;
        boolean zEquals = false;
        if (j2 != 0) {
            z2 = i < 3;
            z3 = i != 2;
            z4 = i != 0;
            int i2 = i + 1;
            z = i != 1;
            strValueOf = String.valueOf(i2);
        } else {
            strValueOf = null;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        long j3 = 5 & j;
        if (j3 != 0) {
            if (gameBean != null) {
                OnClickListenerImpl onClickListenerImpl3 = this.mDataGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl();
                    this.mDataGotoGameAndroidViewViewOnClickListener = onClickListenerImpl3;
                }
                OnClickListenerImpl value = onClickListenerImpl3.setValue(gameBean);
                String recommendType = gameBean.getRecommendType();
                showName = gameBean.getShowName();
                playNum = gameBean.getPlayNum();
                onClickListenerImpl = value;
                str2 = recommendType;
            } else {
                onClickListenerImpl = null;
                showName = null;
                playNum = 0;
            }
            OnClickListenerImpl onClickListenerImpl4 = onClickListenerImpl;
            zEquals = "recommend".equals(str2);
            str = playNum + "";
            onClickListenerImpl2 = onClickListenerImpl4;
        } else {
            str = null;
            showName = null;
        }
        if (j3 != 0) {
            this.mboundView0.setOnClickListener(onClickListenerImpl2);
            this.mboundView01.setData(gameBean);
            TextViewBindingAdapter.setText(this.mboundView5, showName);
            TextViewBindingAdapter.setText(this.mboundView6, str);
            DataBindingHelper.setViewGone(this.mboundView7, zEquals);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, strValueOf);
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            DataBindingHelper.setViewGone(this.mboundView2, z4);
            DataBindingHelper.setViewGone(this.mboundView3, z);
            DataBindingHelper.setViewGone(this.mboundView4, z3);
        }
        if ((j & 4) != 0) {
            DataBindingHelper.setSelected(this.mboundView5, true);
        }
        executeBindingsOn(this.mboundView01);
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
