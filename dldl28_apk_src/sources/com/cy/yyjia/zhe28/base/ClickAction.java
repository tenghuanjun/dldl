package com.cy.yyjia.zhe28.base;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public interface ClickAction extends View.OnClickListener {
    <V extends View> V findViewById(int id);

    @Override // android.view.View.OnClickListener
    void onClick(View v);

    void setOnClickListener(int... ids);

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.base.ClickAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onClick(ClickAction _this, View v) {
        }

        public static void $default$setOnClickListener(ClickAction _this, int... ids) {
            for (int i : ids) {
                _this.findViewById(i).setOnClickListener(_this);
            }
        }
    }
}
