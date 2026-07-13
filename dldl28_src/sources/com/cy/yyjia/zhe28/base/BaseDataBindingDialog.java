package com.cy.yyjia.zhe28.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.BaseDialog.Builder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDataBindingDialog<DB extends ViewDataBinding, B extends BaseDialog.Builder> extends BaseDialog.Builder<B> {
    protected DB mBinding;

    public BaseDataBindingDialog(Context context, int i) {
        super(context);
        DB db = (DB) DataBindingUtil.inflate(LayoutInflater.from(getContext()), i, new FrameLayout(getContext()), false);
        this.mBinding = db;
        setContentView(db.getRoot());
    }
}
