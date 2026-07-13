package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.QiandaoRecordBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityQiandaoRecordBinding extends ViewDataBinding {

    @Bindable
    protected QiandaoRecordBean mData;
    public final Navigation navigation;
    public final RecyclerView rv;

    public abstract void setData(QiandaoRecordBean data);

    protected ActivityQiandaoRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.rv = rv;
    }

    public QiandaoRecordBean getData() {
        return this.mData;
    }

    public static ActivityQiandaoRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQiandaoRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityQiandaoRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_qiandao_record, root, attachToRoot, component);
    }

    public static ActivityQiandaoRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQiandaoRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityQiandaoRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_qiandao_record, null, false, component);
    }

    public static ActivityQiandaoRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityQiandaoRecordBinding bind(View view, Object component) {
        return (ActivityQiandaoRecordBinding) bind(component, view, R.layout.activity_qiandao_record);
    }
}
