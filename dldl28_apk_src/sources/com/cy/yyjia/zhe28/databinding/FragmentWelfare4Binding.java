package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WelfareBean3;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentWelfare4Binding extends ViewDataBinding {
    public final ConstraintLayout clUser;
    public final ImageView ivUser;

    @Bindable
    protected WelfareBean3 mData;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected UserBean mUser;
    public final ProgressBar pb;
    public final RecyclerView rv1;
    public final TextView tvExp;
    public final TextView tvName;

    public abstract void setData(WelfareBean3 data);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setUser(UserBean user);

    protected FragmentWelfare4Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clUser, ImageView ivUser, ProgressBar pb, RecyclerView rv1, TextView tvExp, TextView tvName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clUser = clUser;
        this.ivUser = ivUser;
        this.pb = pb;
        this.rv1 = rv1;
        this.tvExp = tvExp;
        this.tvName = tvName;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public UserBean getUser() {
        return this.mUser;
    }

    public WelfareBean3 getData() {
        return this.mData;
    }

    public static FragmentWelfare4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWelfare4Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWelfare4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_welfare4, root, attachToRoot, component);
    }

    public static FragmentWelfare4Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWelfare4Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWelfare4Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_welfare4, null, false, component);
    }

    public static FragmentWelfare4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWelfare4Binding bind(View view, Object component) {
        return (FragmentWelfare4Binding) bind(component, view, R.layout.fragment_welfare4);
    }
}
