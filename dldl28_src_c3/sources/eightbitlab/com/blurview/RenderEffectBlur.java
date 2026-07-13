package eightbitlab.com.blurview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RenderNode;
import android.graphics.Shader;
import androidx.compose.ui.platform.RenderNodeApi29$;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class RenderEffectBlur implements BlurAlgorithm {
    private Context context;
    public BlurAlgorithm fallbackAlgorithm;
    private int height;
    private int width;
    private final RenderNode node = RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m("BlurViewNode");
    private float lastBlurRadius = 1.0f;

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public boolean canModifyBitmap() {
        return true;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public float scaleFactor() {
        return 6.0f;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public Bitmap blur(Bitmap bitmap, float f) {
        this.lastBlurRadius = f;
        if (bitmap.getHeight() != this.height || bitmap.getWidth() != this.width) {
            this.height = bitmap.getHeight();
            int width = bitmap.getWidth();
            this.width = width;
            RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m(this.node, 0, 0, width, this.height);
        }
        RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m(this.node).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m(this.node);
        RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m(this.node, RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m(f, f, Shader.TileMode.MIRROR));
        return bitmap;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public void destroy() {
        RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m$1(this.node);
        BlurAlgorithm blurAlgorithm = this.fallbackAlgorithm;
        if (blurAlgorithm != null) {
            blurAlgorithm.destroy();
        }
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public Bitmap.Config getSupportedBitmapConfig() {
        return Bitmap.Config.ARGB_8888;
    }

    @Override // eightbitlab.com.blurview.BlurAlgorithm
    public void render(Canvas canvas, Bitmap bitmap) {
        if (canvas.isHardwareAccelerated()) {
            RenderNodeApi29$.ExternalSyntheticApiModelOutline0.m(canvas, this.node);
            return;
        }
        if (this.fallbackAlgorithm == null) {
            this.fallbackAlgorithm = new RenderScriptBlur(this.context);
        }
        this.fallbackAlgorithm.blur(bitmap, this.lastBlurRadius);
        this.fallbackAlgorithm.render(canvas, bitmap);
    }

    void setContext(Context context) {
        this.context = context;
    }
}
