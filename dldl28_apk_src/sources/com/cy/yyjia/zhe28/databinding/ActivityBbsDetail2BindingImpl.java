package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityBbsDetail2BindingImpl extends ActivityBbsDetail2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataFoldAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mDataGameGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final LinearLayout mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final FrameLayout mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final TextView mboundView17;
    private final TextView mboundView19;
    private final ImageView mboundView2;
    private final TextView mboundView20;
    private final Space mboundView22;
    private final LinearLayout mboundView23;
    private final LayoutGameIconBinding mboundView231;
    private final RelativeLayout mboundView24;
    private final TextView mboundView25;
    private final TextView mboundView26;
    private final TextView mboundView27;
    private final EditText mboundView28;
    private InverseBindingListener mboundView28androidTextAttrChanged;
    private final TextView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;
    private final ImageView mboundView6;
    private final LinearLayout mboundView7;
    private final ShapeTextView mboundView8;
    private final TextView mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(35);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(23, new String[]{"layout_game_icon"}, new int[]{29}, new int[]{R.layout.layout_game_icon});
        includedLayouts.setIncludes(24, new String[]{"layout_game_name"}, new int[]{30}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_activity, 31);
        sparseIntArray.put(R.id.tv_sort, 32);
        sparseIntArray.put(R.id.rv, 33);
        sparseIntArray.put(R.id.btn, 34);
    }

    public ActivityBbsDetail2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 35, sIncludes, sViewsWithIds));
    }

    private ActivityBbsDetail2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (TextView) bindings[34], (ImageView) bindings[31], (LayoutGameNameBinding) bindings[30], (Navigation) bindings[1], (RecyclerView) bindings[33], (RecyclerView) bindings[13], (TextView) bindings[21], (TextView) bindings[32], (WebView) bindings[18]);
        this.mboundView28androidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2BindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityBbsDetail2BindingImpl.this.mboundView28);
                String str = ActivityBbsDetail2BindingImpl.this.mText;
                ActivityBbsDetail2BindingImpl activityBbsDetail2BindingImpl = ActivityBbsDetail2BindingImpl.this;
                if (activityBbsDetail2BindingImpl != null) {
                    activityBbsDetail2BindingImpl.setText(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[10];
        this.mboundView10 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[14];
        this.mboundView14 = frameLayout;
        frameLayout.setTag(null);
        TextView textView3 = (TextView) bindings[15];
        this.mboundView15 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[16];
        this.mboundView16 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[17];
        this.mboundView17 = textView5;
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
        Space space = (Space) bindings[22];
        this.mboundView22 = space;
        space.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[23];
        this.mboundView23 = linearLayout3;
        linearLayout3.setTag(null);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[29];
        this.mboundView231 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[24];
        this.mboundView24 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView8 = (TextView) bindings[25];
        this.mboundView25 = textView8;
        textView8.setTag(null);
        TextView textView9 = (TextView) bindings[26];
        this.mboundView26 = textView9;
        textView9.setTag(null);
        TextView textView10 = (TextView) bindings[27];
        this.mboundView27 = textView10;
        textView10.setTag(null);
        EditText editText = (EditText) bindings[28];
        this.mboundView28 = editText;
        editText.setTag(null);
        TextView textView11 = (TextView) bindings[3];
        this.mboundView3 = textView11;
        textView11.setTag(null);
        ImageView imageView2 = (ImageView) bindings[4];
        this.mboundView4 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag(null);
        ImageView imageView4 = (ImageView) bindings[6];
        this.mboundView6 = imageView4;
        imageView4.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[7];
        this.mboundView7 = linearLayout4;
        linearLayout4.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[8];
        this.mboundView8 = shapeTextView;
        shapeTextView.setTag(null);
        TextView textView12 = (TextView) bindings[9];
        this.mboundView9 = textView12;
        textView12.setTag(null);
        setContainedBinding(this.name);
        this.navigation.setTag(null);
        this.rvPic.setTag(null);
        this.tvPraise.setTag(null);
        this.wv.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1024L;
        }
        this.mboundView231.invalidateAll();
        this.name.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView231.hasPendingBindings() || this.name.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 == variableId) {
            setData((BbsBean) variable);
        } else if (111 == variableId) {
            setText((String) variable);
        } else {
            if (2 != variableId) {
                return false;
            }
            setActivity((ChampionshipBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2Binding
    public void setData(BbsBean Data) {
        updateRegistration(1, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2Binding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2Binding
    public void setActivity(ChampionshipBean Activity) {
        this.mActivity = Activity;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView231.setLifecycleOwner(lifecycleOwner);
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
        if (localFieldId == 2) {
            return onChangeDataActivity((TypeBean) object, fieldId);
        }
        if (localFieldId != 3) {
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
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        if (fieldId == 94) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        if (fieldId == 76) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        if (fieldId != 75) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeDataActivity(TypeBean DataActivity, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeName(LayoutGameNameBinding Name, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0372  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2BindingImpl.executeBindings():void");
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
