package com.sq.diagnostic.assistant.ui.action;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ClickAction extends View.OnClickListener {
    <V extends View> V findViewById(int i);

    @Override // android.view.View.OnClickListener
    void onClick(View view);

    void setOnClickListener(View.OnClickListener onClickListener, int... iArr);

    void setOnClickListener(View.OnClickListener onClickListener, View... viewArr);

    void setOnClickListener(int... iArr);

    void setOnClickListener(View... viewArr);

    /* JADX INFO: renamed from: com.sq.diagnostic.assistant.ui.action.ClickAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onClick(ClickAction clickAction, View view) {
        }

        public static void $default$setOnClickListener(ClickAction _this, View.OnClickListener onClickListener, int... iArr) {
            for (int i : iArr) {
                _this.findViewById(i).setOnClickListener(onClickListener);
            }
        }

        public static void $default$setOnClickListener(ClickAction _this, View.OnClickListener onClickListener, View... viewArr) {
            for (View view : viewArr) {
                if (view != null) {
                    view.setOnClickListener(onClickListener);
                }
            }
        }
    }
}
