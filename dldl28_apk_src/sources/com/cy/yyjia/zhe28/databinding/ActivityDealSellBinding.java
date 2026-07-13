package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDealSellBinding extends ViewDataBinding {
    public final EditText et;
    public final ImageView ivIntro;
    public final ImageView ivRecord;
    public final ShapeLinearLayout linTag1;
    public final ShapeLinearLayout linTag2;

    @Bindable
    protected String mGame;
    public final Navigation reTag;
    public final RecyclerView rv;

    public abstract void setGame(String game);

    protected ActivityDealSellBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText et, ImageView ivIntro, ImageView ivRecord, ShapeLinearLayout linTag1, ShapeLinearLayout linTag2, Navigation reTag, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.ivIntro = ivIntro;
        this.ivRecord = ivRecord;
        this.linTag1 = linTag1;
        this.linTag2 = linTag2;
        this.reTag = reTag;
        this.rv = rv;
    }

    public String getGame() {
        return this.mGame;
    }

    public static ActivityDealSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDealSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_sell, root, attachToRoot, component);
    }

    public static ActivityDealSellBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealSellBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDealSellBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_deal_sell, null, false, component);
    }

    public static ActivityDealSellBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDealSellBinding bind(View view, Object component) {
        return (ActivityDealSellBinding) bind(component, view, R.layout.activity_deal_sell);
    }
}
