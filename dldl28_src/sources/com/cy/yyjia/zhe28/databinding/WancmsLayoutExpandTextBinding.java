package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class WancmsLayoutExpandTextBinding implements ViewBinding {
    public final TextView expandCollapse;
    public final TextView expandableText;
    private final View rootView;

    private WancmsLayoutExpandTextBinding(View rootView, TextView expandCollapse, TextView expandableText) {
        this.rootView = rootView;
        this.expandCollapse = expandCollapse;
        this.expandableText = expandableText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static WancmsLayoutExpandTextBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        if (parent == null) {
            throw new NullPointerException("parent");
        }
        inflater.inflate(R.layout.wancms_layout_expand_text, parent);
        return bind(parent);
    }

    public static WancmsLayoutExpandTextBinding bind(View rootView) {
        int i = R.id.expand_collapse;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.expand_collapse);
        if (textView != null) {
            i = R.id.expandable_text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.expandable_text);
            if (textView2 != null) {
                return new WancmsLayoutExpandTextBinding(rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
