package com.sy37sdk.account.floatview;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatWindow {

    public interface ClickListener {
        void onClick(View view, int i, int i2);

        void onShowComplete();
    }

    public static class ClickListenerAdapter implements ClickListener {
        @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListener
        public void onClick(View view, int i, int i2) {
        }

        @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListener
        public void onShowComplete() {
        }
    }

    public interface DragBottom2DeleteCallback {
        void onDragBottom2Delete(boolean z, boolean z2, boolean z3);
    }

    public interface OnDragCallBack {
        void onStartDrag();

        void onStayEdge();
    }
}
