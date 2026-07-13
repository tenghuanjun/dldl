package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogDraftBinding implements ViewBinding {
    public final TextView btnCancel;
    public final TextView btnSave;
    private final LinearLayout rootView;

    private DialogDraftBinding(LinearLayout rootView, TextView btnCancel, TextView btnSave) {
        this.rootView = rootView;
        this.btnCancel = btnCancel;
        this.btnSave = btnSave;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogDraftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogDraftBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_draft, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogDraftBinding bind(View rootView) {
        int i = R.id.btn_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_cancel);
        if (textView != null) {
            i = R.id.btn_save;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_save);
            if (textView2 != null) {
                return new DialogDraftBinding((LinearLayout) rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
