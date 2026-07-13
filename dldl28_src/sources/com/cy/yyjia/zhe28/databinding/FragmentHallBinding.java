package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentHallBinding extends ViewDataBinding {
    public final LinearLayout btn1;
    public final LinearLayout btn2;
    public final LinearLayout btn3;
    public final LinearLayout btn4;
    public final LinearLayout ll;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;
    public final ImageView tvSearch;
    public final ViewPager2 vp;

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentHallBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btn1, LinearLayout btn2, LinearLayout btn3, LinearLayout btn4, LinearLayout ll, ImageView tvSearch, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.ll = ll;
        this.tvSearch = tvSearch;
        this.vp = vp;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static FragmentHallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentHallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_hall, root, attachToRoot, component);
    }

    public static FragmentHallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHallBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentHallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_hall, null, false, component);
    }

    public static FragmentHallBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHallBinding bind(View view, Object component) {
        return (FragmentHallBinding) bind(component, view, R.layout.fragment_hall);
    }
}
