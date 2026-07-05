package com.cmic.sso.sdk.activity;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cmic.sso.sdk.AuthThemeConfig;
import com.mobile.auth.k.g;
import com.mobile.auth.k.n;
import com.mobile.auth.k.u;
import com.mobile.auth.k.w;
import com.mobile.auth.k.y;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.unionpay.tsmservice.mini.data.Constant;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class LoginAuthActivity extends Activity implements View.OnClickListener {
    protected static final String a = "LoginAuthActivity";
    private Handler b;
    private Context c;
    private RelativeLayout d;
    private com.cmic.sso.sdk.widget.a e;
    private com.cmic.sso.sdk.widget.a f;
    private com.cmic.sso.sdk.widget.a g;
    private com.cmic.sso.sdk.a h;
    private com.mobile.auth.e.f i;
    private CheckBox k;
    private LinearLayout l;
    private RelativeLayout m;
    private com.mobile.auth.e.e q;
    private LinearLayout s;
    private String t;
    private AuthThemeConfig u;
    private int v;
    private int w;
    private boolean x;
    private Dialog y;
    private String j = "";
    private long n = 0;
    private int o = 0;
    private g p = null;
    private boolean r = true;

    class a implements DialogInterface.OnKeyListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                LoginAuthActivity.this.e.dismiss();
            }
            return true;
        }
    }

    class b implements DialogInterface.OnKeyListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                LoginAuthActivity.this.f.dismiss();
            }
            return true;
        }
    }

    class c implements DialogInterface.OnKeyListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                LoginAuthActivity.this.g.dismiss();
            }
            return true;
        }
    }

    class d implements g.a {
        d() {
        }
    }

    class e implements CompoundButton.OnCheckedChangeListener {
        e() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CheckBox checkBox;
            LoginAuthActivity loginAuthActivity;
            String str;
            if (z) {
                LoginAuthActivity.this.d.setEnabled(true);
                try {
                    LoginAuthActivity.this.k.setBackgroundResource(n.b(LoginAuthActivity.this, LoginAuthActivity.this.u.getCheckedImgPath()));
                    return;
                } catch (Exception unused) {
                    checkBox = LoginAuthActivity.this.k;
                    loginAuthActivity = LoginAuthActivity.this;
                    str = "umcsdk_check_image";
                }
            } else {
                LoginAuthActivity.this.d.setEnabled(true ^ TextUtils.isEmpty(LoginAuthActivity.this.u.getCheckTipText()));
                try {
                    LoginAuthActivity.this.k.setBackgroundResource(n.b(LoginAuthActivity.this, LoginAuthActivity.this.u.getUncheckedImgPath()));
                    return;
                } catch (Exception unused2) {
                    checkBox = LoginAuthActivity.this.k;
                    loginAuthActivity = LoginAuthActivity.this;
                    str = "umcsdk_uncheck_image";
                }
            }
            checkBox.setBackgroundResource(n.b(loginAuthActivity, str));
        }
    }

    class f implements DialogInterface.OnKeyListener {
        f(LoginAuthActivity loginAuthActivity) {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4;
        }
    }

    private static class g extends Handler {
        WeakReference<LoginAuthActivity> a;

        g(LoginAuthActivity loginAuthActivity) {
            this.a = new WeakReference<>(loginAuthActivity);
        }

        private void a(Message message) {
            LoginAuthActivity loginAuthActivity = this.a.get();
            if (loginAuthActivity == null || message.what != 13) {
                return;
            }
            loginAuthActivity.c();
            loginAuthActivity.k();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                a(message);
            } catch (Exception e) {
                com.mobile.auth.j.a.a.add(e);
                e.printStackTrace();
            }
        }
    }

    private static class h extends u.a {
        WeakReference<LoginAuthActivity> a;
        WeakReference<i> b;

        class a implements com.mobile.auth.e.g {
            final /* synthetic */ LoginAuthActivity a;

            /* JADX INFO: renamed from: com.cmic.sso.sdk.activity.LoginAuthActivity$h$a$a, reason: collision with other inner class name */
            class C0031a implements com.mobile.auth.e.g {
                C0031a() {
                }

                @Override // com.mobile.auth.e.g
                public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
                    if (h.this.b()) {
                        long jB = aVar.b("loginTime");
                        if (jB != 0) {
                            aVar.b("loginTime", System.currentTimeMillis() - jB);
                        }
                        String strC = aVar.c("phonescrip");
                        if (!"103000".equals(str) || TextUtils.isEmpty(strC)) {
                            a.this.a.r = false;
                            com.mobile.auth.k.c.a("authClickFailed");
                        } else {
                            com.mobile.auth.k.c.a("authClickSuccess");
                            a.this.a.r = true;
                        }
                        a.this.a.a(str, str2, aVar, jSONObject);
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        a.this.a.p.sendEmptyMessage(13);
                    }
                }
            }

            a(LoginAuthActivity loginAuthActivity) {
                this.a = loginAuthActivity;
            }

            @Override // com.mobile.auth.e.g
            public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
                if (h.this.b()) {
                    if ("103000".equals(str)) {
                        this.a.i.a(this.a.h, new C0031a());
                        return;
                    }
                    this.a.r = false;
                    this.a.a(str, str2, aVar, jSONObject);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.a.p.sendEmptyMessage(13);
                }
            }
        }

        class b implements com.mobile.auth.e.g {
            final /* synthetic */ LoginAuthActivity a;

            b(LoginAuthActivity loginAuthActivity) {
                this.a = loginAuthActivity;
            }

            @Override // com.mobile.auth.e.g
            public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
                if (h.this.b()) {
                    long jB = aVar.b("loginTime");
                    String strC = aVar.c("phonescrip");
                    if (jB != 0) {
                        aVar.b("loginTime", System.currentTimeMillis() - jB);
                    }
                    if (!"103000".equals(str) || TextUtils.isEmpty(strC)) {
                        this.a.r = false;
                        com.mobile.auth.k.c.a("authClickFailed");
                    } else {
                        com.mobile.auth.k.c.a("authClickSuccess");
                        this.a.r = true;
                    }
                    this.a.a(str, str2, aVar, jSONObject);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.a.p.sendEmptyMessage(13);
                }
            }
        }

        protected h(LoginAuthActivity loginAuthActivity, i iVar) {
            this.a = new WeakReference<>(loginAuthActivity);
            this.b = new WeakReference<>(iVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            i iVar = this.b.get();
            if (this.a.get() == null || iVar == null) {
                return false;
            }
            return iVar.a(false);
        }

        @Override // com.mobile.auth.k.u.a
        protected void a() {
            LoginAuthActivity loginAuthActivity = this.a.get();
            if (loginAuthActivity.r) {
                loginAuthActivity.i.a(loginAuthActivity.h, new b(loginAuthActivity));
            } else {
                loginAuthActivity.i.a(loginAuthActivity.h, String.valueOf(3), new a(loginAuthActivity));
            }
        }
    }

    private class i implements Runnable {
        private com.cmic.sso.sdk.a b;
        private boolean c;

        i(com.cmic.sso.sdk.a aVar) {
            this.b = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean a(boolean z) {
            boolean z2;
            z2 = this.c;
            this.c = z;
            return !z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a(true)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(Constant.KEY_RESULT_CODE, "102507");
                    jSONObject.put("resultString", "请求超时");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LoginAuthActivity.this.r = false;
                com.mobile.auth.k.c.a("authClickFailed");
                LoginAuthActivity.this.p.sendEmptyMessage(13);
                long jB = this.b.b("loginTime");
                if (jB != 0) {
                    this.b.b("loginTime", System.currentTimeMillis() - jB);
                }
                LoginAuthActivity.this.a("102507", "请求超时", this.b, jSONObject);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        try {
            this.b.removeCallbacksAndMessages(null);
            if ("103000".equals(str)) {
                if (com.mobile.auth.e.a.a(this) == null || com.mobile.auth.k.i.c(aVar.c("traceId")) == null) {
                    return;
                }
                com.mobile.auth.e.a.a(this).a(str, str2, aVar, jSONObject, null, true);
                return;
            }
            if (!"200020".equals(str)) {
                com.mobile.auth.e.a.a(this).a(str, str2, aVar, jSONObject, null, true);
            } else if (com.mobile.auth.e.a.a(this) != null) {
                if (com.mobile.auth.k.i.c(aVar.c("traceId")) != null) {
                    com.mobile.auth.e.a.a(this).a(str, str2, aVar, jSONObject, null);
                }
                a();
            }
        } catch (Exception e2) {
            com.mobile.auth.k.f.a(a, "CallbackResult:未知错误");
            e2.printStackTrace();
        }
    }

    private void a(boolean z) {
        try {
            com.mobile.auth.k.c.a("authPageOut");
            a("200020", "登录页面关闭", this.h, null);
        } catch (Exception e2) {
            com.mobile.auth.j.a.a.add(e2);
            e2.printStackTrace();
        }
    }

    private void d() {
        String str;
        com.cmic.sso.sdk.a aVarD = com.mobile.auth.k.i.d(getIntent().getStringExtra("traceId"));
        this.h = aVarD;
        if (aVarD == null) {
            this.h = new com.cmic.sso.sdk.a(0);
        }
        this.q = com.mobile.auth.k.i.c(this.h.a("traceId", ""));
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.b = new Handler(getMainLooper());
        this.p = new g(this);
        this.j = this.h.c("securityphone");
        com.mobile.auth.k.f.b(a, "mSecurityPhone value is " + this.j);
        String strA = this.h.a("operatorType", "");
        com.mobile.auth.k.f.b(a, "operator value is " + strA);
        if (strA.equals("1")) {
            this.t = com.mobile.auth.gatewayauth.Constant.CMCC_PROTOCOL;
            str = "http://wap.cmpassport.com/resources/html/contract.html";
        } else if (strA.equals("3")) {
            this.t = "中国电信天翼账号服务条款";
            str = "https://e.189.cn/sdk/agreement/detail.do";
        } else {
            this.t = com.mobile.auth.gatewayauth.Constant.CUCC_WOPROTOCOL;
            str = com.mobile.auth.gatewayauth.Constant.CUCC_PROTOCOL_URL;
        }
        com.cmic.sso.sdk.widget.a aVar = new com.cmic.sso.sdk.widget.a(this.c, R.style.Theme.Translucent.NoTitleBar, null, str);
        this.e = aVar;
        aVar.setOnKeyListener(new a());
        if (!TextUtils.isEmpty(this.u.getClauseUrl())) {
            com.cmic.sso.sdk.widget.a aVar2 = new com.cmic.sso.sdk.widget.a(this.c, R.style.Theme.Translucent.NoTitleBar, this.u.getClauseName(), this.u.getClauseUrl());
            this.f = aVar2;
            aVar2.setOnKeyListener(new b());
        }
        if (!TextUtils.isEmpty(this.u.getClauseUrl2())) {
            com.cmic.sso.sdk.widget.a aVar3 = new com.cmic.sso.sdk.widget.a(this.c, R.style.Theme.Translucent.NoTitleBar, this.u.getClauseName2(), this.u.getClauseUrl2());
            this.g = aVar3;
            aVar3.setOnKeyListener(new c());
        }
        com.mobile.auth.k.g.a().a(new d());
    }

    private void e() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
        if (this.u.getNumFieldOffsetY() > 0 || this.u.getNumFieldOffsetY_B() < 0) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.m.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            com.mobile.auth.k.f.b(a, "mPhoneLayout.getMeasuredHeight()=" + this.m.getMeasuredHeight());
            if (this.u.getNumFieldOffsetY() <= 0 || (this.v - this.m.getMeasuredHeight()) - w.a(this.c, this.u.getNumFieldOffsetY()) <= 0) {
                layoutParams.addRule(12, -1);
            } else {
                com.mobile.auth.k.f.b(a, "numberField_top");
                layoutParams.addRule(10, -1);
                layoutParams.setMargins(0, w.a(this.c, this.u.getNumFieldOffsetY()), 0, 0);
            }
        } else if (this.u.getNumFieldOffsetY_B() <= 0 || (this.v - this.m.getMeasuredHeight()) - w.a(this.c, this.u.getNumFieldOffsetY_B()) <= 0) {
            layoutParams.addRule(10, -1);
        } else {
            com.mobile.auth.k.f.b(a, "numberField_bottom");
            layoutParams.addRule(12, -1);
            layoutParams.setMargins(0, 0, 0, w.a(this.c, this.u.getNumFieldOffsetY_B()));
        }
        this.m.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        int iMax = Math.max(this.u.getLogBtnMarginLeft(), 0);
        int iMax2 = Math.max(this.u.getLogBtnMarginRight(), 0);
        if (this.u.getLogBtnOffsetY() > 0 || this.u.getLogBtnOffsetY_B() < 0) {
            if (this.u.getLogBtnOffsetY() <= 0 || this.v - w.a(this.c, this.u.getLogBtnHeight() + this.u.getLogBtnOffsetY()) <= 0) {
                layoutParams2.addRule(12, -1);
                layoutParams2.setMargins(w.a(this.c, iMax), 0, w.a(this.c, iMax2), 0);
            } else {
                com.mobile.auth.k.f.b(a, "logBtn_top");
                layoutParams2.addRule(10, -1);
                layoutParams2.setMargins(w.a(this.c, iMax), w.a(this.c, this.u.getLogBtnOffsetY()), w.a(this.c, iMax2), 0);
            }
        } else if (this.u.getLogBtnOffsetY_B() <= 0 || this.v - w.a(this.c, this.u.getLogBtnHeight() + this.u.getLogBtnOffsetY_B()) <= 0) {
            layoutParams2.addRule(10, -1);
            layoutParams2.setMargins(w.a(this.c, iMax), 0, w.a(this.c, iMax2), 0);
        } else {
            com.mobile.auth.k.f.b(a, "logBtn_bottom");
            layoutParams2.addRule(12, -1);
            layoutParams2.setMargins(w.a(this.c, iMax), 0, w.a(this.c, iMax2), w.a(this.c, this.u.getLogBtnOffsetY_B()));
        }
        this.d.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.l.getLayoutParams();
        int privacyMarginLeft = this.u.getPrivacyMarginLeft() >= 0 ? this.u.getCheckedImgWidth() > 30 ? this.u.getPrivacyMarginLeft() : this.u.getPrivacyMarginLeft() - (30 - this.u.getCheckedImgWidth()) : this.u.getCheckedImgWidth() > 30 ? 0 : -(30 - this.u.getCheckedImgWidth());
        int iMax3 = Math.max(this.u.getPrivacyMarginRight(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.l.measure(iMakeMeasureSpec2, iMakeMeasureSpec2);
        if (this.u.getPrivacyOffsetY() > 0 || this.u.getPrivacyOffsetY_B() < 0) {
            if (this.u.getPrivacyOffsetY() <= 0 || (this.v - this.l.getMeasuredHeight()) - w.a(this.c, this.u.getPrivacyOffsetY()) <= 0) {
                com.mobile.auth.k.f.b(a, "privacy_bottom=" + privacyMarginLeft);
                layoutParams3.addRule(12, -1);
                layoutParams3.setMargins(w.a(this.c, (float) privacyMarginLeft), 0, w.a(this.c, (float) iMax3), 0);
            } else {
                com.mobile.auth.k.f.b(a, "privacy_top = " + this.l.getMeasuredHeight());
                layoutParams3.addRule(10, -1);
                layoutParams3.setMargins(w.a(this.c, (float) privacyMarginLeft), w.a(this.c, (float) this.u.getPrivacyOffsetY()), w.a(this.c, (float) iMax3), 0);
            }
        } else if (this.u.getPrivacyOffsetY_B() <= 0 || (this.v - this.l.getMeasuredHeight()) - w.a(this.c, this.u.getPrivacyOffsetY_B()) <= 0) {
            layoutParams3.addRule(10, -1);
            layoutParams3.setMargins(w.a(this.c, privacyMarginLeft), 0, w.a(this.c, iMax3), 0);
            com.mobile.auth.k.f.b(a, "privacy_top");
        } else {
            com.mobile.auth.k.f.b(a, "privacy_bottom=" + this.l.getMeasuredHeight());
            layoutParams3.addRule(12, -1);
            layoutParams3.setMargins(w.a(this.c, (float) privacyMarginLeft), 0, w.a(this.c, (float) iMax3), w.a(this.c, (float) this.u.getPrivacyOffsetY_B()));
        }
        this.l.setLayoutParams(layoutParams3);
    }

    private void f() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
            if (this.u.getStatusBarColor() != 0) {
                getWindow().addFlags(Integer.MIN_VALUE);
                getWindow().clearFlags(67108864);
                getWindow().setStatusBarColor(this.u.getStatusBarColor());
                getWindow().setNavigationBarColor(this.u.getStatusBarColor());
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.u.isLightColor()) {
                getWindow().getDecorView().setSystemUiVisibility(8192);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        View contentView = this.u.getContentView();
        if (contentView != null) {
            ViewParent parent = contentView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(contentView);
            }
            relativeLayout.addView(contentView);
        } else if (this.u.getLayoutResID() != -1) {
            getLayoutInflater().inflate(this.u.getLayoutResID(), relativeLayout);
        }
        setContentView(relativeLayout);
        int requestedOrientation = getRequestedOrientation();
        this.v = w.b(this.c);
        int iA = w.a(this.c);
        this.w = iA;
        if ((requestedOrientation == 1 && iA > this.v) || (requestedOrientation == 0 && this.w < this.v)) {
            int i2 = this.w;
            this.w = this.v;
            this.v = i2;
        }
        com.mobile.auth.k.f.d(a, "orientation = " + requestedOrientation + "--screenWidth = " + this.w + "--screenHeight = " + this.v);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (this.u.getWindowWidth() != 0) {
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            getWindowManager().getDefaultDisplay().getSize(new Point());
            attributes.width = w.a(this.c, this.u.getWindowWidth());
            int iA2 = w.a(this.c, this.u.getWindowHeight());
            attributes.height = iA2;
            this.w = attributes.width;
            this.v = iA2;
            attributes.x = w.a(this.c, this.u.getWindowX());
            if (this.u.getWindowBottom() == 1) {
                getWindow().setGravity(80);
            } else {
                attributes.y = w.a(this.c, this.u.getWindowY());
            }
            getWindow().setAttributes(attributes);
        }
        relativeLayout.setFitsSystemWindows(true);
        relativeLayout.setClipToPadding(true);
        try {
            g();
            relativeLayout.addView(this.m);
            relativeLayout.addView(h());
            relativeLayout.addView(i());
            e();
            this.d.setOnClickListener(this);
            this.s.setOnClickListener(this);
            this.k.setOnCheckedChangeListener(new e());
            k();
            try {
                if (this.u.isPrivacyState()) {
                    this.k.setChecked(true);
                    this.k.setBackgroundResource(n.b(this, this.u.getCheckedImgPath()));
                    this.d.setEnabled(true);
                } else {
                    this.k.setChecked(false);
                    this.d.setEnabled(!TextUtils.isEmpty(this.u.getCheckTipText()));
                    this.k.setBackgroundResource(n.b(this, this.u.getUncheckedImgPath()));
                }
            } catch (Exception unused) {
                this.k.setChecked(false);
            }
        } catch (Exception e2) {
            com.mobile.auth.j.a.a.add(e2);
            e2.printStackTrace();
            com.mobile.auth.k.f.a(a, e2.toString());
            a("200040", "UI资源加载异常", this.h, null);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(1:4)(9:(2:7|(1:9)(1:10))|11|21|12|15|23|16|19|20)|5|11|21|12|15|23|16|19|20) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0069, code lost:
    
        r0.setTextSize(2, 18.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0087, code lost:
    
        r0.setTextColor(-13421773);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g() {
        /*
            r6 = this;
            android.widget.RelativeLayout r0 = new android.widget.RelativeLayout
            r0.<init>(r6)
            r6.m = r0
            r1 = 13107(0x3333, float:1.8367E-41)
            r0.setId(r1)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r1 = -2
            r2 = -1
            r0.<init>(r2, r1)
            android.widget.RelativeLayout r2 = r6.m
            r2.setLayoutParams(r0)
            android.widget.TextView r0 = new android.widget.TextView
            r0.<init>(r6)
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r2.<init>(r1, r1)
            r1 = 15
            r0.setGravity(r1)
            com.cmic.sso.sdk.AuthThemeConfig r1 = r6.u
            int r1 = r1.getNumberOffsetX()
            r3 = 0
            if (r1 != 0) goto L36
            r1 = 13
        L32:
            r2.addRule(r1)
            goto L5d
        L36:
            if (r1 <= 0) goto L5d
            int r4 = r6.w
            int r5 = r0.getWidth()
            int r4 = r4 - r5
            android.content.Context r5 = r6.c
            float r1 = (float) r1
            int r5 = com.mobile.auth.k.w.a(r5, r1)
            int r4 = r4 - r5
            if (r4 <= 0) goto L53
            android.content.Context r4 = r6.c
            int r1 = com.mobile.auth.k.w.a(r4, r1)
            r2.setMargins(r1, r3, r3, r3)
            goto L5d
        L53:
            java.lang.String r1 = com.cmic.sso.sdk.activity.LoginAuthActivity.a
            java.lang.String r4 = "RelativeLayout.ALIGN_PARENT_RIGHT"
            com.mobile.auth.k.f.b(r1, r4)
            r1 = 11
            goto L32
        L5d:
            r1 = 2
            com.cmic.sso.sdk.AuthThemeConfig r4 = r6.u     // Catch: java.lang.Exception -> L69
            int r4 = r4.getNumberSize()     // Catch: java.lang.Exception -> L69
            float r4 = (float) r4     // Catch: java.lang.Exception -> L69
            r0.setTextSize(r1, r4)     // Catch: java.lang.Exception -> L69
            goto L6e
        L69:
            r4 = 1099956224(0x41900000, float:18.0)
            r0.setTextSize(r1, r4)
        L6e:
            java.lang.String r1 = r6.j
            r0.setText(r1)
            r1 = 30583(0x7777, float:4.2856E-41)
            r0.setId(r1)
            android.widget.RelativeLayout r1 = r6.m
            r1.addView(r0, r2)
            com.cmic.sso.sdk.AuthThemeConfig r1 = r6.u     // Catch: java.lang.Exception -> L87
            int r1 = r1.getNumberColor()     // Catch: java.lang.Exception -> L87
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> L87
            goto L8d
        L87:
            r1 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r0.setTextColor(r1)
        L8d:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r3)
            android.widget.RelativeLayout r1 = r6.m
            r1.measure(r0, r0)
            java.lang.String r0 = com.cmic.sso.sdk.activity.LoginAuthActivity.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "mPhoneLayout.getMeasuredHeight()="
            r1.append(r2)
            android.widget.RelativeLayout r2 = r6.m
            int r2 = r2.getMeasuredHeight()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.mobile.auth.k.f.b(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.activity.LoginAuthActivity.g():void");
    }

    private RelativeLayout h() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.d = relativeLayout;
        relativeLayout.setId(17476);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(w.a(this.c, this.u.getLogBtnWidth()), w.a(this.c, this.u.getLogBtnHeight())));
        TextView textView = new TextView(this);
        textView.setTextSize(2, this.u.getLogBtnTextSize());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setLayoutParams(layoutParams);
        this.d.addView(textView);
        textView.setText(this.u.getLogBtnText());
        try {
            textView.setTextColor(this.u.getLogBtnTextColor());
        } catch (Exception unused) {
            textView.setTextColor(-1);
        }
        try {
            this.d.setBackgroundResource(n.b(this.c, this.u.getLogBtnBackgroundPath()));
        } catch (Exception e2) {
            e2.printStackTrace();
            this.d.setBackgroundResource(n.b(this.c, "umcsdk_login_btn_bg"));
        }
        return this.d;
    }

    private LinearLayout i() {
        LinearLayout linearLayout = new LinearLayout(this);
        this.l = linearLayout;
        linearLayout.setOrientation(0);
        this.l.setHorizontalGravity(1);
        this.l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        int checkedImgWidth = this.u.getCheckedImgWidth();
        int checkedImgHeight = this.u.getCheckedImgHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(w.a(this.c, checkedImgWidth > 30 ? checkedImgWidth : 30.0f), w.a(this.c, checkedImgHeight > 30 ? checkedImgHeight : 30.0f));
        LinearLayout linearLayout2 = new LinearLayout(this);
        this.s = linearLayout2;
        linearLayout2.setOrientation(0);
        this.s.setId(34952);
        this.s.setLayoutParams(layoutParams);
        CheckBox checkBox = new CheckBox(this);
        this.k = checkBox;
        checkBox.setChecked(false);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(w.a(this.c, this.u.getCheckedImgWidth()), w.a(this.c, this.u.getCheckedImgHeight()));
        layoutParams2.setMargins(w.a(this.c, checkedImgWidth > 30 ? 0.0f : 30 - checkedImgWidth), 0, 0, 0);
        this.k.setLayoutParams(layoutParams2);
        this.s.addView(this.k);
        this.l.addView(this.s);
        TextView textView = new TextView(this);
        textView.setTextSize(2, this.u.getPrivacyTextSize());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(w.a(this.c, 5.0f), 0, 0, w.a(this.c, 5.0f));
        textView.setLayoutParams(layoutParams3);
        this.l.addView(textView);
        textView.setTextColor(this.u.getClauseBaseColor());
        textView.setText(w.a(this, j(), this.t, this.e, this.f, this.g));
        textView.setLineSpacing(8.0f, 1.0f);
        textView.setIncludeFontPadding(false);
        if (this.u.isPrivacyTextGravityCenter()) {
            textView.setGravity(17);
        }
        textView.setHighlightColor(getResources().getColor(R.color.transparent));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.k.setButtonDrawable(new ColorDrawable());
        try {
            this.k.setBackgroundResource(n.b(this, this.u.getUncheckedImgPath()));
        } catch (Exception unused) {
            this.k.setBackgroundResource(n.b(this, "umcsdk_uncheck_image"));
        }
        return this.l;
    }

    private String j() {
        String privacy;
        String str;
        String str2;
        if (this.u.getPrivacy().contains(AuthThemeConfig.PLACEHOLDER2)) {
            this.t = "《" + this.t + "》";
            privacy = this.u.getPrivacy();
            str = this.t;
            str2 = AuthThemeConfig.PLACEHOLDER2;
        } else {
            privacy = this.u.getPrivacy();
            str = this.t;
            str2 = AuthThemeConfig.PLACEHOLDER;
        }
        return privacy.replace(str2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.d.setClickable(true);
        this.k.setClickable(true);
    }

    private void l() {
        this.d.setClickable(false);
        this.k.setClickable(false);
    }

    private void m() {
        try {
            if (this.o >= 5) {
                Toast.makeText(this.c, "网络不稳定,请返回重试其他登录方式", 1).show();
                this.d.setClickable(true);
                return;
            }
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                com.mobile.auth.k.f.a("stack", stackTraceElement.getClassName());
                String className = stackTraceElement.getClassName();
                if (!TextUtils.isEmpty(className) && className.contains("com.cmic.sso.sdk.activity") && !sb.toString().contains(className)) {
                    sb.append(className);
                    sb.append(com.alipay.sdk.util.i.b);
                }
            }
            this.h.b("caller", sb.toString());
            this.h.b("loginTime", System.currentTimeMillis());
            String strA = this.h.a("traceId", "");
            if (!TextUtils.isEmpty(strA) && com.mobile.auth.k.i.a(strA)) {
                String strB = y.b();
                this.h.b("traceId", strB);
                com.mobile.auth.k.i.a(strB, this.q);
            }
            b();
            l();
            i iVar = new i(this.h);
            this.b.postDelayed(iVar, com.mobile.auth.e.a.a(this).b());
            u.a(new h(this, iVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a() {
        this.b.removeCallbacksAndMessages(null);
        com.cmic.sso.sdk.widget.a aVar = this.e;
        if (aVar != null && aVar.isShowing()) {
            this.e.dismiss();
        }
        com.cmic.sso.sdk.widget.a aVar2 = this.f;
        if (aVar2 != null && aVar2.isShowing()) {
            this.f.dismiss();
        }
        c();
        this.y = null;
        finish();
        if (this.u.getAuthPageActOut() == null || this.u.getActivityIn() == null) {
            return;
        }
        overridePendingTransition(n.c(this, this.u.getActivityIn()), n.c(this, this.u.getAuthPageActOut()));
    }

    public void b() {
        com.mobile.auth.k.f.a(a, "loginClickStart");
        try {
            this.x = true;
            if (this.u.getLoginClickListener() != null) {
                this.u.getLoginClickListener().a(this.c, null);
            } else {
                if (this.y != null) {
                    this.y.show();
                    return;
                }
                AlertDialog alertDialogCreate = new AlertDialog.Builder(this).create();
                this.y = alertDialogCreate;
                alertDialogCreate.setCancelable(false);
                this.y.setCanceledOnTouchOutside(false);
                this.y.setOnKeyListener(new f(this));
                RelativeLayout relativeLayout = new RelativeLayout(this.y.getContext());
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                ImageView imageView = new ImageView(this.y.getContext());
                imageView.setImageResource(n.b(this.c, "dialog_loading"));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(80, 80);
                layoutParams.addRule(13, -1);
                relativeLayout.addView(imageView, layoutParams);
                if (this.y.getWindow() != null) {
                    this.y.getWindow().setDimAmount(0.0f);
                }
                this.y.show();
                this.y.setContentView(relativeLayout);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.mobile.auth.k.f.a(a, "loginClickStart");
    }

    public void c() {
        try {
            com.mobile.auth.k.f.a(a, "loginClickComplete");
            if (this.u.getLoginClickListener() != null && this.x) {
                this.x = false;
                this.u.getLoginClickListener().b(this.c, null);
            } else if (this.y != null && this.y.isShowing()) {
                this.y.dismiss();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id == 17476) {
                if (!this.k.isChecked() && !TextUtils.isEmpty(this.u.getCheckTipText())) {
                    Toast.makeText(this.c, this.u.getCheckTipText(), 1).show();
                    return;
                } else {
                    this.o++;
                    m();
                    return;
                }
            }
            if (id == 26214) {
                a(false);
            } else {
                if (id != 34952) {
                    return;
                }
                if (this.k.isChecked()) {
                    this.k.setChecked(false);
                } else {
                    this.k.setChecked(true);
                }
            }
        } catch (Exception e2) {
            com.mobile.auth.j.a.a.add(e2);
            e2.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            if (bundle != null) {
                finish();
            }
            this.c = this;
            AuthThemeConfig authThemeConfigA = com.mobile.auth.e.a.a(this).a();
            this.u = authThemeConfigA;
            if (authThemeConfigA != null) {
                if (authThemeConfigA.getThemeId() != -1) {
                    setTheme(this.u.getThemeId());
                }
                if (this.u.getAuthPageActIn() != null && this.u.getActivityOut() != null) {
                    overridePendingTransition(n.c(this, this.u.getAuthPageActIn()), n.c(this, this.u.getActivityOut()));
                }
            }
            com.mobile.auth.k.c.a("authPageIn");
            this.n = System.currentTimeMillis();
            this.i = com.mobile.auth.e.f.a(this);
            d();
            f();
        } catch (Exception e2) {
            com.mobile.auth.j.a.a.add(e2);
            com.mobile.auth.k.f.a(a, e2.toString());
            e2.printStackTrace();
            a("200025", "发生未知错误", this.h, null);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        String str;
        String str2;
        try {
            this.b.removeCallbacksAndMessages(null);
            com.mobile.auth.k.c.a("timeOnAuthPage", (System.currentTimeMillis() - this.n) + "");
            if (this.k.isChecked()) {
                str = "authPrivacyState";
                str2 = "1";
            } else {
                str = "authPrivacyState";
                str2 = "0";
            }
            com.mobile.auth.k.c.a(str, str2);
            if (!this.h.a("isLoginSwitch", false)) {
                com.mobile.auth.k.c.a("timeOnAuthPage", (System.currentTimeMillis() - this.n) + "");
                com.mobile.auth.k.c.a(this.c.getApplicationContext(), this.h);
                com.mobile.auth.k.c.a();
            }
            this.y = null;
            com.mobile.auth.k.g.a().b();
            this.p.removeCallbacksAndMessages(null);
        } catch (Exception e2) {
            com.mobile.auth.k.f.a(a, "LoginAuthActivity clear failed");
            com.mobile.auth.j.a.a.add(e2);
            e2.printStackTrace();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.isCanceled() || keyEvent.getRepeatCount() != 0) {
            return true;
        }
        if (this.u.getBackPressedListener() != null) {
            this.u.getBackPressedListener().a();
        }
        a(false);
        return true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (this.h != null) {
                this.h.b("loginMethod", "loginAuth");
            }
            com.mobile.auth.e.a.a(this).a("200087", null);
        } catch (Exception e2) {
            com.mobile.auth.j.a.a.add(e2);
            a("200025", "发生未知错误", this.h, null);
        }
    }
}
