package com.ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.sdk.openadsdk.R;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class AppDetailInfoActivity extends Activity {
    private ImageView a;
    private TextView b;
    private LinearLayout c;
    private RecyclerView d;
    private long e;
    private long f;
    private List<Pair<String, String>> g;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ttdownloader_activity_app_detail_info);
        if (a()) {
            b();
        } else {
            com.ss.android.socialbase.appdownloader.c.a((Activity) this);
        }
    }

    public static void a(Activity activity, long j) {
        Intent intent = new Intent(activity, (Class<?>) AppDetailInfoActivity.class);
        intent.putExtra("app_info_id", j);
        activity.startActivity(intent);
    }

    private boolean a() {
        this.e = getIntent().getLongExtra("app_info_id", 0L);
        com.ss.android.downloadlib.addownload.b.b bVarA = c.a().a(this.e);
        if (bVarA == null) {
            return false;
        }
        this.f = bVarA.b;
        this.g = bVarA.h;
        return true;
    }

    private void b() {
        this.a = (ImageView) findViewById(R.id.iv_detail_back);
        this.b = (TextView) findViewById(R.id.tv_empty);
        this.d = findViewById(R.id.permission_list);
        this.c = (LinearLayout) findViewById(R.id.ll_download);
        if (this.g.isEmpty()) {
            this.d.setVisibility(8);
            this.b.setVisibility(0);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(1);
            this.d.setLayoutManager(linearLayoutManager);
            this.d.setAdapter(new a());
        }
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                g.a("lp_app_detail_click_close", AppDetailInfoActivity.this.f);
                AppDetailInfoActivity.this.finish();
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                g.a("lp_app_detail_click_download", AppDetailInfoActivity.this.f);
                b.a().b(AppDetailInfoActivity.this.f);
                com.ss.android.socialbase.appdownloader.c.a((Activity) AppDetailInfoActivity.this);
                com.ss.android.socialbase.appdownloader.c.a(b.a().b());
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        g.a("lp_app_detail_click_close", this.f);
        super.onBackPressed();
    }

    private class a extends RecyclerView.Adapter<Object> {
        private a() {
        }
    }
}
