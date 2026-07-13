package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeEditText;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityTrumpetBinding extends ViewDataBinding {
    public final ShapeEditText et;

    @Bindable
    protected String mGame;

    @Bindable
    protected boolean mManager;
    public final Navigation navigation;
    public final RecyclerView rv;

    public abstract void setGame(String game);

    public abstract void setManager(boolean manager);

    protected ActivityTrumpetBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeEditText et, Navigation navigation, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.navigation = navigation;
        this.rv = rv;
    }

    public boolean getManager() {
        return this.mManager;
    }

    public String getGame() {
        return this.mGame;
    }

    public static ActivityTrumpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTrumpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTrumpetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_trumpet, root, attachToRoot, component);
    }

    public static ActivityTrumpetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTrumpetBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTrumpetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_trumpet, null, false, component);
    }

    public static ActivityTrumpetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTrumpetBinding bind(View view, Object component) {
        return (ActivityTrumpetBinding) bind(component, view, R.layout.activity_trumpet);
    }
}
