package com.sqwan.common.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.plugin.standard.BaseActivity;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PlatformAnnouncementActivity extends BaseActivity {
    private Activity _activity;
    private Context _context;
    private ImageView ivClose;
    private LinearLayout llEnsure;
    private TextView tvContent;
    private TextView tvTitle;

    public void finish() {
        super.finish();
        this._activity.overridePendingTransition(0, 0);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this._context = context;
        this._activity = (Activity) context;
        getWindow().setSoftInputMode(19);
        getContext().getTheme().applyStyle(SqResUtils.getStyleId(this._context, "Transparent"), true);
        setContentView(SqResUtils.getLayoutId(this._context, "sy37_dialog_announcement"));
        this.tvTitle = (TextView) findViewById(SqResUtils.getId(this._context, "tv_sy37_announcement_title"));
        this.tvContent = (TextView) findViewById(SqResUtils.getId(this._context, "tv_sy37_announcement_content"));
        this.ivClose = (ImageView) findViewById(SqResUtils.getId(this._context, "iv_sy37_announcement_close"));
        this.llEnsure = (LinearLayout) findViewById(SqResUtils.getId(this._context, "ll_sy37_announcement_ensure"));
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.dialog.PlatformAnnouncementActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlatformAnnouncementActivity.this.finish();
            }
        });
        this.llEnsure.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.dialog.PlatformAnnouncementActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlatformAnnouncementActivity.this.finish();
            }
        });
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("title");
        String stringExtra2 = intent.getStringExtra("content");
        if (stringExtra != null) {
            this.tvTitle.setText(stringExtra);
        }
        if (stringExtra2 != null) {
            this.tvContent.setText(stringExtra2);
        }
    }
}
