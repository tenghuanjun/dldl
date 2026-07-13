package androidx.compose.ui.platform;

import android.graphics.ColorSpace;
import android.graphics.RenderNode;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import android.view.translation.ViewTranslationCallback;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RenderNodeApi29$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ ColorSpace.Rgb.TransferParameters m(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return new ColorSpace.Rgb.TransferParameters(d, d2, d3, d4, d5, d6, d7);
    }

    public static /* bridge */ /* synthetic */ ColorSpace m(Object obj) {
        return (ColorSpace) obj;
    }

    public static /* synthetic */ RenderNode m(String str) {
        return new RenderNode(str);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AutofillId m5148m(Object obj) {
        return (AutofillId) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ContentCaptureSession m5149m(Object obj) {
        return (ContentCaptureSession) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ViewTranslationCallback m5150m(Object obj) {
        return (ViewTranslationCallback) obj;
    }

    public static /* synthetic */ ViewTranslationRequest.Builder m(AutofillId autofillId, long j) {
        return new ViewTranslationRequest.Builder(autofillId, j);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ViewTranslationResponse m5151m(Object obj) {
        return (ViewTranslationResponse) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m5152m() {
    }
}
