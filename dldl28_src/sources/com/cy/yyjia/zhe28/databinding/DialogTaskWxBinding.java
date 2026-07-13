package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public final class DialogTaskWxBinding implements ViewBinding {
    public final EditText et;
    private final ShapeLinearLayout rootView;
    public final ShapeTextView sure;
    public final TextView taskContent;
    public final TextView taskName;
    public final TextView title;

    private DialogTaskWxBinding(ShapeLinearLayout rootView, EditText et, ShapeTextView sure, TextView taskContent, TextView taskName, TextView title) {
        this.rootView = rootView;
        this.et = et;
        this.sure = sure;
        this.taskContent = taskContent;
        this.taskName = taskName;
        this.title = title;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ShapeLinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogTaskWxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogTaskWxBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_task_wx, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogTaskWxBinding bind(View rootView) {
        int i = R.id.et;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et);
        if (editText != null) {
            i = R.id.sure;
            ShapeTextView shapeTextView = (ShapeTextView) ViewBindings.findChildViewById(rootView, R.id.sure);
            if (shapeTextView != null) {
                i = R.id.task_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.task_content);
                if (textView != null) {
                    i = R.id.task_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.task_name);
                    if (textView2 != null) {
                        i = R.id.title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.title);
                        if (textView3 != null) {
                            return new DialogTaskWxBinding((ShapeLinearLayout) rootView, editText, shapeTextView, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
