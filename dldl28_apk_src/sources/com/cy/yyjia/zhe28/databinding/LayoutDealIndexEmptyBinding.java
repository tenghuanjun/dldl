package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class LayoutDealIndexEmptyBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final RecyclerView rv;

    private LayoutDealIndexEmptyBinding(LinearLayout rootView, RecyclerView rv) {
        this.rootView = rootView;
        this.rv = rv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDealIndexEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDealIndexEmptyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_deal_index_empty, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDealIndexEmptyBinding bind(View rootView) {
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rv);
        if (recyclerView != null) {
            return new LayoutDealIndexEmptyBinding((LinearLayout) rootView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.rv)));
    }
}
