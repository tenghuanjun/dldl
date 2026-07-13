package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityBbsDetailBindingImpl extends ActivityBbsDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataFoldAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mDataGameGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView13;
    private final TextView mboundView14;
    private final LinearLayout mboundView16;
    private final LayoutGameIconBinding mboundView161;
    private final RelativeLayout mboundView17;
    private final TextView mboundView18;
    private final TextView mboundView19;
    private final ImageView mboundView2;
    private final TextView mboundView20;
    private final EditText mboundView21;
    private InverseBindingListener mboundView21androidTextAttrChanged;
    private final TextView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;
    private final ImageView mboundView6;
    private final LinearLayout mboundView7;
    private final ShapeTextView mboundView8;
    private final TextView mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(29);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(16, new String[]{"layout_game_icon"}, new int[]{22}, new int[]{R.layout.layout_game_icon});
        includedLayouts.setIncludes(17, new String[]{"layout_game_name"}, new int[]{23}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.abl, 24);
        sparseIntArray.put(R.id.ctl, 25);
        sparseIntArray.put(R.id.tv_sort, 26);
        sparseIntArray.put(R.id.rv, 27);
        sparseIntArray.put(R.id.btn, 28);
    }

    public ActivityBbsDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }

    private ActivityBbsDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (AppBarLayout) bindings[24], (TextView) bindings[28], (CollapsingToolbarLayout) bindings[25], (LayoutGameNameBinding) bindings[23], (Navigation) bindings[1], (RecyclerView) bindings[27], (RecyclerView) bindings[12], (TextView) bindings[15], (TextView) bindings[26]);
        this.mboundView21androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityBbsDetailBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityBbsDetailBindingImpl.this.mboundView21);
                String str = ActivityBbsDetailBindingImpl.this.mText;
                ActivityBbsDetailBindingImpl activityBbsDetailBindingImpl = ActivityBbsDetailBindingImpl.this;
                if (activityBbsDetailBindingImpl != null) {
                    activityBbsDetailBindingImpl.setText(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[13];
        this.mboundView13 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[14];
        this.mboundView14 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[16];
        this.mboundView16 = linearLayout2;
        linearLayout2.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[22];
        this.mboundView161 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[17];
        this.mboundView17 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView5 = (TextView) bindings[18];
        this.mboundView18 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[19];
        this.mboundView19 = textView6;
        textView6.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        TextView textView7 = (TextView) bindings[20];
        this.mboundView20 = textView7;
        textView7.setTag(null);
        EditText editText = (EditText) bindings[21];
        this.mboundView21 = editText;
        editText.setTag(null);
        TextView textView8 = (TextView) bindings[3];
        this.mboundView3 = textView8;
        textView8.setTag(null);
        ImageView imageView2 = (ImageView) bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[6];
        this.mboundView6 = imageView4;
        imageView4.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[7];
        this.mboundView7 = linearLayout3;
        linearLayout3.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[8];
        this.mboundView8 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView9 = (TextView) bindings[9];
        this.mboundView9 = textView9;
        textView9.setTag(null);
        setContainedBinding(this.name);
        this.navigation.setTag(null);
        this.rvPic.setTag(null);
        this.tvPraise.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
        }
        this.mboundView161.invalidateAll();
        this.name.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView161.hasPendingBindings() || this.name.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 == variableId) {
            setData((BbsBean) variable);
        } else {
            if (111 != variableId) {
                return false;
            }
            setText((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsDetailBinding
    public void setData(BbsBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsDetailBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView161.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeData((BbsBean) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeName((LayoutGameNameBinding) object, fieldId);
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

    private boolean onChangeData(BbsBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 98) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        if (fieldId == 94) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        if (fieldId == 76) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        if (fieldId != 75) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0234  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1002
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityBbsDetailBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BbsBean value;

        public OnClickListenerImpl setValue(BbsBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.fold(arg0);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private GameBean value;

        public OnClickListenerImpl1 setValue(GameBean value) {
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
