package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogYunDeviceNameBinding implements ViewBinding {
    public final EditText et;
    public final ImageView ivClear;
    private final LinearLayout rootView;
    public final TextView tvCancel;
    public final TextView tvConfirm;

    private DialogYunDeviceNameBinding(LinearLayout rootView, EditText et, ImageView ivClear, TextView tvCancel, TextView tvConfirm) {
        this.rootView = rootView;
        this.et = et;
        this.ivClear = ivClear;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogYunDeviceNameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogYunDeviceNameBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_yun_device_name, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogYunDeviceNameBinding bind(View rootView) {
        int i = R.id.et;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et);
        if (editText != null) {
            i = R.id.iv_clear;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_clear);
            if (imageView != null) {
                i = R.id.tv_cancel;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
                if (textView != null) {
                    i = R.id.tv_confirm;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_confirm);
                    if (textView2 != null) {
                        return new DialogYunDeviceNameBinding((LinearLayout) rootView, editText, imageView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
