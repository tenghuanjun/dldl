package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogGameServiceBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final RecyclerView rv;
    public final TextView tvTitle;

    private DialogGameServiceBinding(LinearLayout rootView, RecyclerView rv, TextView tvTitle) {
        this.rootView = rootView;
        this.rv = rv;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogGameServiceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogGameServiceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_game_service, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogGameServiceBinding bind(View rootView) {
        int i = R.id.rv;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rv);
        if (recyclerView != null) {
            i = R.id.tv_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
            if (textView != null) {
                return new DialogGameServiceBinding((LinearLayout) rootView, recyclerView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
