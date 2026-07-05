package layaair.game.browser;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class n implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ String c;
    private /* synthetic */ ExportJavaFunction d;

    n(ExportJavaFunction exportJavaFunction, String str, String str2, String str3) {
        this.d = exportJavaFunction;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "runJS");
            jSONObject.put("fName", this.a);
            jSONObject.put("value", this.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.d.m_pEngine.m_pPlatform != null) {
            this.d.m_pEngine.m_pPlatform.LP_sendMessageToPlatform(jSONObject.toString());
        }
        this.d.m_pEngine.getWebView().a(this.a, this.b, this.c);
    }
}
