package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BbsDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBbsEditBinding extends ViewDataBinding {
    public final TextView btn;
    public final EditText et;
    public final ImageView ivGame;
    public final LinearLayout llGame;

    @Bindable
    protected BbsDetailBean mData;

    @Bindable
    protected int mPosition;

    @Bindable
    protected String mText;

    @Bindable
    protected String mTitle;
    public final RecyclerView rvCate;
    public final RecyclerView rvPic;
    public final RecyclerView rvReply;

    public abstract void setData(BbsDetailBean data);

    public abstract void setPosition(int position);

    public abstract void setText(String text);

    public abstract void setTitle(String title);

    protected ActivityBbsEditBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, EditText et, ImageView ivGame, LinearLayout llGame, RecyclerView rvCate, RecyclerView rvPic, RecyclerView rvReply) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.ivGame = ivGame;
        this.llGame = llGame;
        this.rvCate = rvCate;
        this.rvPic = rvPic;
        this.rvReply = rvReply;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getText() {
        return this.mText;
    }

    public BbsDetailBean getData() {
        return this.mData;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public static ActivityBbsEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBbsEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_edit, root, attachToRoot, component);
    }

    public static ActivityBbsEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsEditBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBbsEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_edit, null, false, component);
    }

    public static ActivityBbsEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsEditBinding bind(View view, Object component) {
        return (ActivityBbsEditBinding) bind(component, view, R.layout.activity_bbs_edit);
    }
}
