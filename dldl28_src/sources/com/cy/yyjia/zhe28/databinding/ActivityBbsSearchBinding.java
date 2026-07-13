package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBbsSearchBinding extends ViewDataBinding {
    public final TextView btn;
    public final EditText et;
    public final ImageView iv;

    @Bindable
    protected String mText;
    public final RecyclerView rv;

    public abstract void setText(String text);

    protected ActivityBbsSearchBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, EditText et, ImageView iv, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.et = et;
        this.iv = iv;
        this.rv = rv;
    }

    public String getText() {
        return this.mText;
    }

    public static ActivityBbsSearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsSearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBbsSearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_search, root, attachToRoot, component);
    }

    public static ActivityBbsSearchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsSearchBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBbsSearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_bbs_search, null, false, component);
    }

    public static ActivityBbsSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBbsSearchBinding bind(View view, Object component) {
        return (ActivityBbsSearchBinding) bind(component, view, R.layout.activity_bbs_search);
    }
}
