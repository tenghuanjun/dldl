package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class PopupRvBinding implements ViewBinding {
    public final CardView ll;
    private final CardView rootView;
    public final RecyclerView rv;

    private PopupRvBinding(CardView rootView, CardView ll, RecyclerView rv) {
        this.rootView = rootView;
        this.ll = ll;
        this.rv = rv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static PopupRvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PopupRvBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.popup_rv, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopupRvBinding bind(View rootView) {
        CardView cardView = (CardView) rootView;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rv);
        if (recyclerView != null) {
            return new PopupRvBinding(cardView, cardView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.rv)));
    }
}
