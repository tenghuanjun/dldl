package layaair.game.conch;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class b extends AbsoluteLayout {
    private /* synthetic */ LayaConch5 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b(LayaConch5 layaConch5, Context context) {
        super(context);
        this.a = layaConch5;
    }

    @Override // android.widget.AbsoluteLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Log.i("fix", "onLayout (" + i + com.igexin.push.core.b.aj + i2 + com.igexin.push.core.b.aj + i3 + com.igexin.push.core.b.aj + i4 + ")");
        if (!LayaConch5.isHUAWEI() || this.a.m_pCavans == null || this.a.m_pCavans.d <= 0 || this.a.m_pCavans.e <= 0) {
            return;
        }
        WindowManager windowManager = (WindowManager) this.a.mCtx.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if ((this.a.m_bHorizontalScreen && displayMetrics.heightPixels > displayMetrics.widthPixels) || (!this.a.m_bHorizontalScreen && displayMetrics.widthPixels > displayMetrics.heightPixels)) {
            int i5 = displayMetrics.heightPixels;
            displayMetrics.heightPixels = displayMetrics.widthPixels;
            displayMetrics.widthPixels = i5;
        }
        this.a.m_pCavans.b = displayMetrics.widthPixels;
        this.a.m_pCavans.c = displayMetrics.heightPixels;
        this.a.m_pCavans.a(this.a.m_pCavans.d, this.a.m_pCavans.e);
    }
}
