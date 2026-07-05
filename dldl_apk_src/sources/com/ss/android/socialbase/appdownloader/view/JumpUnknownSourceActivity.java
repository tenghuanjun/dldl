package com.ss.android.socialbase.appdownloader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.igexin.push.core.b;
import com.ss.android.socialbase.appdownloader.c;
import com.ss.android.socialbase.appdownloader.c.k;
import com.ss.android.socialbase.appdownloader.c.l;
import com.ss.android.socialbase.appdownloader.d;
import com.ss.android.socialbase.appdownloader.h;
import com.ss.android.socialbase.appdownloader.i;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class JumpUnknownSourceActivity extends Activity {
    private k a;
    private Intent b;
    private Intent c;
    private int d;
    private JSONObject e;

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        h.a().a(this);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        h.a().a(this);
    }

    private void a() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        this.b = intent;
        if (intent != null) {
            this.c = (Intent) intent.getParcelableExtra("intent");
            this.d = intent.getIntExtra("id", -1);
            try {
                this.e = new JSONObject(intent.getStringExtra(b.X));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.e == null) {
            c.a((Activity) this);
            return;
        }
        b();
        k kVar = this.a;
        if (kVar != null && !kVar.b()) {
            this.a.a();
        } else if (this.a == null) {
            finish();
        }
    }

    private void b() {
        if (this.a != null || this.b == null) {
            return;
        }
        try {
            com.ss.android.socialbase.appdownloader.c.c cVarA = d.j().a();
            l lVarA = cVarA != null ? cVarA.a(this) : null;
            if (lVarA == null) {
                lVarA = new com.ss.android.socialbase.appdownloader.d.a(this);
            }
            int iA = i.a(this, "tt_appdownloader_tip");
            int iA2 = i.a(this, "tt_appdownloader_label_ok");
            int iA3 = i.a(this, "tt_appdownloader_label_cancel");
            String strOptString = this.e.optString(DownloadSettingKeys.AhPlans.KEY_JUMP_UNKNOWN_SOURCE_TIPS);
            if (TextUtils.isEmpty(strOptString)) {
                strOptString = getString(i.a(this, "tt_appdownloader_jump_unknown_source_tips"));
            }
            lVarA.a(iA).a(strOptString).a(iA2, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    JumpUnknownSourceActivity jumpUnknownSourceActivity = JumpUnknownSourceActivity.this;
                    if (com.ss.android.socialbase.appdownloader.b.a(jumpUnknownSourceActivity, jumpUnknownSourceActivity.c, JumpUnknownSourceActivity.this.d, JumpUnknownSourceActivity.this.e)) {
                        com.ss.android.socialbase.appdownloader.b.c(JumpUnknownSourceActivity.this.d, JumpUnknownSourceActivity.this.e);
                    } else {
                        JumpUnknownSourceActivity jumpUnknownSourceActivity2 = JumpUnknownSourceActivity.this;
                        com.ss.android.socialbase.appdownloader.b.a((Context) jumpUnknownSourceActivity2, jumpUnknownSourceActivity2.c, true);
                    }
                    com.ss.android.socialbase.appdownloader.b.a(JumpUnknownSourceActivity.this.d, JumpUnknownSourceActivity.this.e);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).b(iA3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (JumpUnknownSourceActivity.this.c != null) {
                        JumpUnknownSourceActivity jumpUnknownSourceActivity = JumpUnknownSourceActivity.this;
                        com.ss.android.socialbase.appdownloader.b.a((Context) jumpUnknownSourceActivity, jumpUnknownSourceActivity.c, true);
                    }
                    com.ss.android.socialbase.appdownloader.b.b(JumpUnknownSourceActivity.this.d, JumpUnknownSourceActivity.this.e);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).a(new DialogInterface.OnCancelListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    if (JumpUnknownSourceActivity.this.c != null) {
                        JumpUnknownSourceActivity jumpUnknownSourceActivity = JumpUnknownSourceActivity.this;
                        com.ss.android.socialbase.appdownloader.b.a((Context) jumpUnknownSourceActivity, jumpUnknownSourceActivity.c, true);
                    }
                    com.ss.android.socialbase.appdownloader.b.b(JumpUnknownSourceActivity.this.d, JumpUnknownSourceActivity.this.e);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).a(false);
            this.a = lVarA.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
