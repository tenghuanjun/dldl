package com.tencent.open.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a extends RelativeLayout {
    private static final String a = "com.tencent.open.c.a";
    private Rect b;
    private boolean c;
    private InterfaceC0197a d;

    /* JADX INFO: renamed from: com.tencent.open.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    public interface InterfaceC0197a {
        void a();

        void a(int i);
    }

    public a(Context context) {
        super(context);
        this.b = null;
        this.c = false;
        this.d = null;
        this.b = new Rect();
    }

    public void a(InterfaceC0197a interfaceC0197a) {
        this.d = interfaceC0197a;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top) - size;
        InterfaceC0197a interfaceC0197a = this.d;
        if (interfaceC0197a != null && size != 0) {
            if (height > 100) {
                interfaceC0197a.a((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                interfaceC0197a.a();
            }
        }
        super.onMeasure(i, i2);
    }
}
