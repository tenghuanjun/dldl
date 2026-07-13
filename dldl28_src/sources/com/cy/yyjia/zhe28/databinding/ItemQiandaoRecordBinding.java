package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.QiandaoRecordBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemQiandaoRecordBinding extends ViewDataBinding {

    @Bindable
    protected QiandaoRecordBean.Data mData;

    public abstract void setData(QiandaoRecordBean.Data data);

    protected ItemQiandaoRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public QiandaoRecordBean.Data getData() {
        return this.mData;
    }

    public static ItemQiandaoRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemQiandaoRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao_record, root, attachToRoot, component);
    }

    public static ItemQiandaoRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemQiandaoRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao_record, null, false, component);
    }

    public static ItemQiandaoRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoRecordBinding bind(View view, Object component) {
        return (ItemQiandaoRecordBinding) bind(component, view, R.layout.item_qiandao_record);
    }
}
