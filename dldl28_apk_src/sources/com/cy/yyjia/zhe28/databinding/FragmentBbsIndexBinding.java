package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsBlockBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentBbsIndexBinding extends ViewDataBinding {
    public final ImageView ivMessage;

    @Bindable
    protected List<BbsBlockBean> mData;

    @Bindable
    protected View.OnClickListener mOnClick;

    @Bindable
    protected int mPosition;
    public final View point;
    public final ViewPager2 vp;

    public abstract void setData(List<BbsBlockBean> data);

    public abstract void setOnClick(View.OnClickListener onClick);

    public abstract void setPosition(int position);

    protected FragmentBbsIndexBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMessage, View point, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMessage = ivMessage;
        this.point = point;
        this.vp = vp;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public List<BbsBlockBean> getData() {
        return this.mData;
    }

    public static FragmentBbsIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbsIndexBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentBbsIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_bbs_index, root, attachToRoot, component);
    }

    public static FragmentBbsIndexBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbsIndexBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentBbsIndexBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_bbs_index, null, false, component);
    }

    public static FragmentBbsIndexBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBbsIndexBinding bind(View view, Object component) {
        return (FragmentBbsIndexBinding) bind(component, view, R.layout.fragment_bbs_index);
    }
}
