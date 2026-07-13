package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.NewGameBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomeSchedule2BindingImpl extends ItemHomeSchedule2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private OnClickListenerImpl mDataGameGotoGameAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LayoutGameIconBinding mboundView0;
    private final LinearLayout mboundView01;
    private final RelativeLayout mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(9);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_game_icon", "layout_discount"}, new int[]{5, 7}, new int[]{R.layout.layout_game_icon, R.layout.layout_discount});
        includedLayouts.setIncludes(1, new String[]{"layout_game_name"}, new int[]{6}, new int[]{R.layout.layout_game_name});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.desc, 8);
    }

    public ItemHomeSchedule2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ItemHomeSchedule2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (LinearLayout) bindings[8], (LayoutDiscountBinding) bindings[7], (LayoutGameNameBinding) bindings[6], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.discount);
        LayoutGameIconBinding layoutGameIconBinding = (LayoutGameIconBinding) bindings[5];
        this.mboundView0 = layoutGameIconBinding;
        setContainedBinding(layoutGameIconBinding);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView01 = linearLayout;
        linearLayout.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[1];
        this.mboundView1 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        setContainedBinding(this.name);
        this.tag.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        this.mboundView0.invalidateAll();
        this.name.invalidateAll();
        this.discount.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mboundView0.hasPendingBindings() || this.name.hasPendingBindings() || this.discount.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((NewGameBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomeSchedule2Binding
    public void setData(NewGameBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView0.setLifecycleOwner(lifecycleOwner);
        this.name.setLifecycleOwner(lifecycleOwner);
        this.discount.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId == 0) {
            return onChangeDiscount((LayoutDiscountBinding) object, fieldId);
        }
        if (localFieldId == 1) {
            return onChangeDataGame((GameBean) object, fieldId);
        }
        if (localFieldId != 2) {
            return false;
        }
        return onChangeName((LayoutGameNameBinding) object, fieldId);
    }

    private boolean onChangeDiscount(LayoutDiscountBinding Discount, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeDataGame(GameBean DataGame, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
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

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        SpannableString descNew;
        OnClickListenerImpl value;
        String score;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NewGameBean newGameBean = this.mData;
        long j2 = 26 & j;
        GameBean gameBean = null;
        serverName = null;
        String serverName = null;
        if (j2 != 0) {
            GameBean game = newGameBean != null ? newGameBean.getGame() : null;
            updateRegistration(1, game);
            if (game != null) {
                descNew = game.getDescNew();
                OnClickListenerImpl onClickListenerImpl = this.mDataGameGotoGameAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataGameGotoGameAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(game);
                score = game.getScore();
            } else {
                descNew = null;
                value = null;
                score = null;
            }
            if ((j & 24) != 0 && newGameBean != null) {
                serverName = newGameBean.getServerName();
            }
            str = serverName;
            gameBean = game;
        } else {
            str = null;
            descNew = null;
            value = null;
            score = null;
        }
        if (j2 != 0) {
            this.discount.setData(gameBean);
            this.mboundView0.setData(gameBean);
            this.mboundView01.setOnClickListener(value);
            TextViewBindingAdapter.setText(this.mboundView2, score);
            TextViewBindingAdapter.setText(this.mboundView3, descNew);
            this.name.setData(gameBean);
        }
        if ((j & 24) != 0) {
            TextViewBindingAdapter.setText(this.tag, str);
        }
        executeBindingsOn(this.mboundView0);
        executeBindingsOn(this.name);
        executeBindingsOn(this.discount);
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
