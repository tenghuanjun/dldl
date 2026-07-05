package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class UPPayWapActivity extends Activity {
    private static String a = "ex_mode";
    private WebView b;
    private WebViewJavascriptBridge c;
    private AlertDialog d;
    private boolean e = false;
    private String f = "";
    private String g;
    private View h;
    private af i;

    static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, boolean z) {
        View view = uPPayWapActivity.h;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("pay_result", str);
        intent.putExtra("result_data", str2);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject.put("code", str);
            }
            if (str2 != null) {
                jSONObject.put("msg", str2);
            }
            if (str3 != null) {
                jSONObject.put("value", str3);
            }
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject2.put("code", str);
            }
            if (str2 != null) {
                jSONObject2.put("msg", str2);
            }
            if (jSONObject != null) {
                jSONObject2.put("value", jSONObject);
            }
            return jSONObject2.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    static /* synthetic */ void d(UPPayWapActivity uPPayWapActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(uPPayWapActivity);
        uPPayWapActivity.d = builder.create();
        builder.setMessage(com.unionpay.utils.k.a().a);
        builder.setTitle(com.unionpay.utils.k.a().d);
        builder.setPositiveButton(com.unionpay.utils.k.a().b, new r(uPPayWapActivity));
        builder.setNegativeButton(com.unionpay.utils.k.a().c, new s(uPPayWapActivity));
        builder.create().show();
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String str = "";
                    String string = extras.containsKey("pay_result") ? extras.getString("pay_result") : extras.containsKey("code") ? extras.getString("code") : "";
                    if (TextUtils.isEmpty(string)) {
                        string = "";
                    }
                    String string2 = extras.containsKey(com.alipay.sdk.packet.e.k) ? extras.getString(com.alipay.sdk.packet.e.k) : "";
                    if (!TextUtils.isEmpty(string2)) {
                        str = string2;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("code", string);
                    jSONObject.put(com.alipay.sdk.packet.e.k, str);
                    if (this.i != null) {
                        this.i.a(b("0", (String) null, jSONObject));
                    }
                } else if (this.i != null) {
                    this.i.a(b("1", "No pay result", (String) null));
                }
            } catch (Exception unused) {
                af afVar = this.i;
                if (afVar != null) {
                    afVar.a(b("1", "No pay result", (String) null));
                }
            }
            this.i = null;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String stringExtra;
        View.OnClickListener qVar;
        super.onCreate(bundle);
        getWindow().addFlags(8192);
        try {
            try {
                if (!"949A1CC".equalsIgnoreCase(getIntent().getStringExtra("magic_data"))) {
                    finish();
                }
                this.e = "link".equals(getIntent().getStringExtra("actionType"));
                String stringExtra2 = getIntent().getStringExtra(a);
                this.f = stringExtra2;
                if (TextUtils.isEmpty(stringExtra2)) {
                    this.f = "00";
                }
                stringExtra = "";
                getWindow().requestFeature(1);
                RelativeLayout relativeLayout = new RelativeLayout(this);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(1);
                relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -1));
                setContentView(relativeLayout);
                this.b = new WebView(this);
                String stringExtra3 = getIntent().getStringExtra("actionType");
                this.g = stringExtra3;
                if ("link".equals(stringExtra3)) {
                    stringExtra = getIntent().getStringExtra("wapurl");
                } else {
                    String stringExtra4 = getIntent().getStringExtra("waptype");
                    String stringExtra5 = getIntent().getStringExtra("wapurl");
                    if ("new_page".equals(stringExtra4)) {
                        stringExtra = stringExtra5 != null ? stringExtra5 : "";
                        qVar = new j(this);
                    } else {
                        String stringExtra6 = getIntent().getStringExtra("paydata");
                        if (stringExtra6 != null) {
                            stringExtra = stringExtra5 + "?s=" + stringExtra6;
                        }
                        qVar = null;
                    }
                    ImageView imageView = new ImageView(this);
                    imageView.setBackgroundDrawable(com.unionpay.utils.g.a(com.unionpay.utils.h.b));
                    int iA = com.unionpay.utils.f.a(this, 24.0f);
                    int iA2 = com.unionpay.utils.f.a(this, 18.0f);
                    int iA3 = com.unionpay.utils.f.a(this, 14.0f);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iA, iA);
                    layoutParams.addRule(9, -1);
                    layoutParams.addRule(10, -1);
                    layoutParams.setMargins(iA2, iA3, 0, 0);
                    relativeLayout.addView(imageView, layoutParams);
                    if (qVar == null) {
                        qVar = new q(this);
                    }
                    imageView.setOnClickListener(qVar);
                    this.h = imageView;
                }
                this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                linearLayout.addView(this.b);
                WebViewJavascriptBridge webViewJavascriptBridge = new WebViewJavascriptBridge(this, this.b, null);
                this.c = webViewJavascriptBridge;
                webViewJavascriptBridge.setAllowScheme(true);
                if (this.b != null) {
                    this.b.loadUrl(stringExtra);
                }
                if (this.c != null) {
                    this.c.registerHandler("getDeviceInfo", new t(this));
                    this.c.registerHandler("saveData", new u(this));
                    this.c.registerHandler("getData", new v(this));
                    this.c.registerHandler("removeData", new w(this));
                    this.c.registerHandler("setPageBackEnable", new x(this));
                    this.c.registerHandler("payBySDK", new y(this));
                    this.c.registerHandler("payResult", new k(this));
                    this.c.registerHandler("closePage", new l(this));
                    this.c.registerHandler("openNewPage", new m(this));
                    this.c.registerHandler("checkBankSchemes", new n(this));
                    this.c.registerHandler("openBankApp", new o(this));
                    this.c.registerHandler("openScheme", new p(this));
                }
            } catch (Exception unused) {
                finish();
            }
        } catch (Exception unused2) {
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.e) {
            WebView webView = this.b;
            if (webView != null && webView.canGoBack()) {
                this.b.goBack();
                return true;
            }
            a("cancel", (String) null);
        } else {
            onPause();
        }
        return true;
    }
}
