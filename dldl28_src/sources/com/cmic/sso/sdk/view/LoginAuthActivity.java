package com.cmic.sso.sdk.view;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
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
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.cmic.sso.sdk.view.f;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.m.n;
import com.mobile.auth.m.q;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class LoginAuthActivity extends Activity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final String f412a = "LoginAuthActivity";
    private com.cmic.sso.sdk.view.a A;
    private int B;
    private int C;
    private boolean D;
    private Dialog E;
    private Handler b;
    private Context c;
    private RelativeLayout d;
    private h e;
    private h f;
    private h g;
    private h h;
    private h i;
    private ArrayList<h> j;
    private ArrayList<String> k;
    private String[] l;
    private com.cmic.sso.sdk.a m;
    private com.mobile.auth.f.c n;
    private CheckBox p;
    private RelativeLayout q;
    private RelativeLayout r;
    private com.mobile.auth.f.b v;
    private RelativeLayout x;
    private String y;
    private String z;
    private String o = "";
    private long s = 0;
    private int t = 0;
    private a u = null;
    private boolean w = true;

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<LoginAuthActivity> f421a;

        a(LoginAuthActivity loginAuthActivity) {
            this.f421a = new WeakReference<>(loginAuthActivity);
        }

        private void a(Message message) {
            LoginAuthActivity loginAuthActivity = this.f421a.get();
            if (loginAuthActivity == null || message.what != 1) {
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
                com.cmic.sso.sdk.d.c.b.add(e);
                e.printStackTrace();
            }
        }
    }

    private static class b extends n.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<LoginAuthActivity> f422a;
        WeakReference<c> b;

        protected b(LoginAuthActivity loginAuthActivity, c cVar) {
            this.f422a = new WeakReference<>(loginAuthActivity);
            this.b = new WeakReference<>(cVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            c cVar = this.b.get();
            if (this.f422a.get() == null || cVar == null) {
                return false;
            }
            return cVar.a(false);
        }

        @Override // com.mobile.auth.m.n.a
        protected void a() {
            final LoginAuthActivity loginAuthActivity = this.f422a.get();
            loginAuthActivity.m.a("logintype", 1);
            com.mobile.auth.m.h.a(true, false);
            loginAuthActivity.n.b(loginAuthActivity.m, new com.mobile.auth.f.d() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.b.1
                @Override // com.mobile.auth.f.d
                public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
                    if (b.this.b()) {
                        long jB = aVar.b("loginTime", 0L);
                        String strB = aVar.b("phonescrip");
                        if (jB != 0) {
                            aVar.a("loginTime", System.currentTimeMillis() - jB);
                        }
                        if (!"103000".equals(str) || TextUtils.isEmpty(strB)) {
                            loginAuthActivity.w = false;
                            com.cmic.sso.sdk.d.a.a("authClickFailed");
                        } else {
                            com.cmic.sso.sdk.d.a.a("authClickSuccess");
                            loginAuthActivity.w = true;
                        }
                        loginAuthActivity.a(str, str2, aVar, jSONObject);
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        loginAuthActivity.u.sendEmptyMessage(1);
                    }
                }
            });
        }
    }

    private class c implements Runnable {
        private com.cmic.sso.sdk.a b;
        private boolean c;

        c(com.cmic.sso.sdk.a aVar) {
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
                    jSONObject.put("resultCode", "102507");
                    jSONObject.put("resultString", "请求超时");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LoginAuthActivity.this.w = false;
                com.cmic.sso.sdk.d.a.a("authClickFailed");
                LoginAuthActivity.this.u.sendEmptyMessage(1);
                long jB = this.b.b("loginTime", 0L);
                if (jB != 0) {
                    this.b.a("loginTime", System.currentTimeMillis() - jB);
                }
                LoginAuthActivity.this.a("102507", "请求超时", this.b, jSONObject);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        com.mobile.auth.f.a aVarA;
        try {
            this.b.removeCallbacksAndMessages(null);
            if ("103000".equals(str)) {
                if (com.mobile.auth.f.a.a(this) == null || com.mobile.auth.m.e.c(aVar.b("traceId")) == null) {
                    return;
                }
                aVar.a("keepListener", true);
                aVarA = com.mobile.auth.f.a.a(this);
            } else {
                if ("200020".equals(str)) {
                    if (com.mobile.auth.f.a.a(this) != null) {
                        if (com.mobile.auth.m.e.c(aVar.b("traceId")) != null) {
                            com.mobile.auth.f.a.a(this).a(str, str2, aVar, jSONObject);
                        }
                        a();
                        return;
                    }
                    return;
                }
                aVar.a("keepListener", true);
                aVarA = com.mobile.auth.f.a.a(this);
            }
            aVarA.a(str, str2, aVar, jSONObject);
        } catch (Exception e) {
            com.mobile.auth.m.c.a(f412a, "CallbackResult:未知错误");
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        try {
            com.cmic.sso.sdk.d.a.a("authPageOut");
            a("200020", "登录页面关闭", this.m, null);
        } catch (Exception e) {
            com.cmic.sso.sdk.d.c.b.add(e);
            e.printStackTrace();
        }
    }

    private void d() {
        String str;
        com.cmic.sso.sdk.a aVarD = com.mobile.auth.m.e.d(getIntent().getStringExtra("traceId"));
        this.m = aVarD;
        if (aVarD == null) {
            this.m = new com.cmic.sso.sdk.a(0);
        }
        this.v = com.mobile.auth.m.e.c(this.m.b("traceId", ""));
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.b = new Handler(getMainLooper());
        this.u = new a(this);
        this.o = this.m.b("securityphone");
        String str2 = f412a;
        com.mobile.auth.m.c.b(str2, "mSecurityPhone value is " + this.o);
        String strB = this.m.b("operatortype", "");
        com.mobile.auth.m.c.b(str2, "operator value is " + strB);
        if (this.A.ap() == 1) {
            this.l = com.cmic.sso.sdk.c.b;
        } else if (this.A.ap() == 2) {
            this.l = com.cmic.sso.sdk.c.c;
        } else {
            this.l = com.cmic.sso.sdk.c.f406a;
        }
        if (strB.equals("1")) {
            this.y = this.l[0];
            str = "http://wap.cmpassport.com/resources/html/contract.html";
        } else if (strB.equals("3")) {
            this.y = this.l[1];
            str = Constant.CTCC_PROTOCOL_URL;
        } else {
            this.y = this.l[2];
            str = Constant.CUCC_PROTOCOL_URL;
        }
        h hVar = new h(this.c, R.style.Theme.Translucent.NoTitleBar, this.y, str);
        this.e = hVar;
        hVar.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                    LoginAuthActivity.this.e.b();
                }
                return true;
            }
        });
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        if (!TextUtils.isEmpty(this.A.N())) {
            h hVar2 = new h(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.M(), this.A.N());
            this.f = hVar2;
            hVar2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.2
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        LoginAuthActivity.this.f.b();
                    }
                    return true;
                }
            });
            this.j.add(this.f);
            this.k.add(this.A.M());
        }
        if (!TextUtils.isEmpty(this.A.P())) {
            h hVar3 = new h(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.O(), this.A.P());
            this.g = hVar3;
            hVar3.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        LoginAuthActivity.this.g.b();
                    }
                    return true;
                }
            });
            this.j.add(this.g);
            this.k.add(this.A.O());
        }
        if (!TextUtils.isEmpty(this.A.R())) {
            h hVar4 = new h(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.Q(), this.A.R());
            this.h = hVar4;
            hVar4.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.4
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        LoginAuthActivity.this.h.b();
                    }
                    return true;
                }
            });
            this.j.add(this.h);
            this.k.add(this.A.Q());
        }
        if (!TextUtils.isEmpty(this.A.T())) {
            h hVar5 = new h(this.c, R.style.Theme.Translucent.NoTitleBar, this.A.S(), this.A.T());
            this.i = hVar5;
            hVar5.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.5
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
                        LoginAuthActivity.this.i.b();
                    }
                    return true;
                }
            });
            this.j.add(this.i);
            this.k.add(this.A.S());
        }
        j();
        if (this.A.ad()) {
            for (int i = 0; i < this.k.size(); i++) {
                String str3 = String.format("《%s》", this.k.get(i));
                this.z = this.z.replaceFirst(this.k.get(i), str3);
                this.k.set(i, str3);
            }
        }
        f.a().a(new f.a() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.6
            @Override // com.cmic.sso.sdk.view.f.a
            public void a() {
                LoginAuthActivity.this.b.removeCallbacksAndMessages(null);
                if (LoginAuthActivity.this.e != null && LoginAuthActivity.this.e.isShowing()) {
                    LoginAuthActivity.this.e.dismiss();
                }
                if (LoginAuthActivity.this.f != null && LoginAuthActivity.this.f.isShowing()) {
                    LoginAuthActivity.this.f.dismiss();
                }
                LoginAuthActivity.this.a(true);
            }
        });
    }

    private void e() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.r.getLayoutParams();
        if (this.A.p() > 0 || this.A.q() < 0) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.r.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            String str = f412a;
            com.mobile.auth.m.c.b(str, "mPhoneLayout.getMeasuredHeight()=" + this.r.getMeasuredHeight());
            if (this.A.p() <= 0 || (this.B - this.r.getMeasuredHeight()) - i.a(this.c, this.A.p()) <= 0) {
                layoutParams.addRule(12, -1);
            } else {
                com.mobile.auth.m.c.b(str, "numberField_top");
                layoutParams.addRule(10, -1);
                layoutParams.setMargins(0, i.a(this.c, this.A.p()), 0, 0);
            }
        } else if (this.A.q() <= 0 || (this.B - this.r.getMeasuredHeight()) - i.a(this.c, this.A.q()) <= 0) {
            layoutParams.addRule(10, -1);
        } else {
            com.mobile.auth.m.c.b(f412a, "numberField_bottom");
            layoutParams.addRule(12, -1);
            layoutParams.setMargins(0, 0, 0, i.a(this.c, this.A.q()));
        }
        this.r.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
        int iMax = Math.max(this.A.y(), 0);
        int iMax2 = Math.max(this.A.z(), 0);
        if (this.A.A() > 0 || this.A.B() < 0) {
            if (this.A.A() <= 0 || this.B - i.a(this.c, this.A.x() + this.A.A()) <= 0) {
                layoutParams2.addRule(12, -1);
                layoutParams2.setMargins(i.a(this.c, iMax), 0, i.a(this.c, iMax2), 0);
            } else {
                com.mobile.auth.m.c.b(f412a, "logBtn_top");
                layoutParams2.addRule(10, -1);
                layoutParams2.setMargins(i.a(this.c, iMax), i.a(this.c, this.A.A()), i.a(this.c, iMax2), 0);
            }
        } else if (this.A.B() <= 0 || this.B - i.a(this.c, this.A.x() + this.A.B()) <= 0) {
            layoutParams2.addRule(10, -1);
            layoutParams2.setMargins(i.a(this.c, iMax), 0, i.a(this.c, iMax2), 0);
        } else {
            com.mobile.auth.m.c.b(f412a, "logBtn_bottom");
            layoutParams2.addRule(12, -1);
            layoutParams2.setMargins(i.a(this.c, iMax), 0, i.a(this.c, iMax2), i.a(this.c, this.A.B()));
        }
        this.d.setLayoutParams(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.q.getLayoutParams();
        int iZ = this.A.Z() >= 0 ? this.A.I() > 30 ? this.A.Z() : this.A.Z() - (30 - this.A.I()) : this.A.I() > 30 ? 0 : -(30 - this.A.I());
        int iMax3 = Math.max(this.A.aa(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.q.measure(iMakeMeasureSpec2, iMakeMeasureSpec2);
        if (this.A.ab() > 0 || this.A.ac() < 0) {
            if (this.A.ab() <= 0 || (this.B - this.q.getMeasuredHeight()) - i.a(this.c, this.A.ab()) <= 0) {
                com.mobile.auth.m.c.b(f412a, "privacy_bottom=" + iZ);
                layoutParams3.addRule(12, -1);
                layoutParams3.setMargins(i.a(this.c, (float) iZ), 0, i.a(this.c, (float) iMax3), 0);
            } else {
                com.mobile.auth.m.c.b(f412a, "privacy_top = " + this.q.getMeasuredHeight());
                layoutParams3.addRule(10, -1);
                layoutParams3.setMargins(i.a(this.c, (float) iZ), i.a(this.c, (float) this.A.ab()), i.a(this.c, (float) iMax3), 0);
            }
        } else if (this.A.ac() <= 0 || (this.B - this.q.getMeasuredHeight()) - i.a(this.c, this.A.ac()) <= 0) {
            layoutParams3.addRule(10, -1);
            layoutParams3.setMargins(i.a(this.c, iZ), 0, i.a(this.c, iMax3), 0);
            com.mobile.auth.m.c.b(f412a, "privacy_top");
        } else {
            com.mobile.auth.m.c.b(f412a, "privacy_bottom=" + this.q.getMeasuredHeight());
            layoutParams3.addRule(12, -1);
            layoutParams3.setMargins(i.a(this.c, (float) iZ), 0, i.a(this.c, (float) iMax3), i.a(this.c, (float) this.A.ac()));
        }
        this.q.setLayoutParams(layoutParams3);
    }

    private void f() {
        getWindow().addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        getWindow().addFlags(134217728);
        if (this.A.a() != 0) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            getWindow().setStatusBarColor(this.A.a());
            getWindow().setNavigationBarColor(this.A.a());
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.A.b()) {
                getWindow().getDecorView().setSystemUiVisibility(8192);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        View viewC = this.A.c();
        if (viewC != null) {
            ViewParent parent = viewC.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(viewC);
            }
            relativeLayout.addView(viewC);
        } else if (this.A.d() != -1) {
            getLayoutInflater().inflate(this.A.d(), relativeLayout);
        }
        setContentView(relativeLayout);
        int requestedOrientation = getRequestedOrientation();
        this.B = i.b(this.c);
        int iA = i.a(this.c);
        this.C = iA;
        boolean z = true;
        if ((requestedOrientation == 1 && iA > this.B) || (requestedOrientation == 0 && iA < this.B)) {
            this.C = this.B;
            this.B = iA;
        }
        com.mobile.auth.m.c.b(f412a, "orientation = " + requestedOrientation + "--screenWidth = " + this.C + "--screenHeight = " + this.B);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (this.A.aj() != 0) {
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            getWindowManager().getDefaultDisplay().getSize(new Point());
            attributes.width = i.a(this.c, this.A.aj());
            attributes.height = i.a(this.c, this.A.ak());
            this.C = attributes.width;
            this.B = attributes.height;
            attributes.x = i.a(this.c, this.A.al());
            if (this.A.an() == 1) {
                getWindow().setGravity(80);
            } else {
                attributes.y = i.a(this.c, this.A.am());
            }
            getWindow().setAttributes(attributes);
        }
        relativeLayout.setFitsSystemWindows(this.A.aq());
        relativeLayout.setClipToPadding(true);
        try {
            g();
            relativeLayout.addView(this.r);
            relativeLayout.addView(h());
            relativeLayout.addView(i());
            e();
            this.d.setOnClickListener(this);
            this.x.setOnClickListener(this);
            this.p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.7
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    CheckBox checkBox;
                    LoginAuthActivity loginAuthActivity;
                    String str;
                    boolean z3 = true;
                    if (z2) {
                        LoginAuthActivity.this.d.setEnabled(true);
                        try {
                            CheckBox checkBox2 = LoginAuthActivity.this.p;
                            LoginAuthActivity loginAuthActivity2 = LoginAuthActivity.this;
                            checkBox2.setBackgroundResource(g.b(loginAuthActivity2, loginAuthActivity2.A.G()));
                            return;
                        } catch (Exception unused) {
                            checkBox = LoginAuthActivity.this.p;
                            loginAuthActivity = LoginAuthActivity.this;
                            str = "umcsdk_check_image";
                        }
                    } else {
                        RelativeLayout relativeLayout2 = LoginAuthActivity.this.d;
                        if (LoginAuthActivity.this.A.F() == null && TextUtils.isEmpty(LoginAuthActivity.this.A.C())) {
                            z3 = false;
                        }
                        relativeLayout2.setEnabled(z3);
                        try {
                            CheckBox checkBox3 = LoginAuthActivity.this.p;
                            LoginAuthActivity loginAuthActivity3 = LoginAuthActivity.this;
                            checkBox3.setBackgroundResource(g.b(loginAuthActivity3, loginAuthActivity3.A.H()));
                            return;
                        } catch (Exception unused2) {
                            checkBox = LoginAuthActivity.this.p;
                            loginAuthActivity = LoginAuthActivity.this;
                            str = "umcsdk_uncheck_image";
                        }
                    }
                    checkBox.setBackgroundResource(g.b(loginAuthActivity, str));
                }
            });
            k();
            try {
                if (this.A.K()) {
                    this.p.setChecked(true);
                    this.p.setBackgroundResource(g.b(this, this.A.G()));
                    this.d.setEnabled(true);
                    return;
                }
                this.p.setChecked(false);
                RelativeLayout relativeLayout2 = this.d;
                if (this.A.F() == null && TextUtils.isEmpty(this.A.C())) {
                    z = false;
                }
                relativeLayout2.setEnabled(z);
                this.p.setBackgroundResource(g.b(this, this.A.H()));
            } catch (Exception unused) {
                this.p.setChecked(false);
            }
        } catch (Exception e) {
            com.cmic.sso.sdk.d.c.b.add(e);
            e.printStackTrace();
            com.mobile.auth.m.c.a(f412a, e.toString());
            a("200040", "UI资源加载异常", this.m, null);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|(1:4)(11:(2:7|(1:9)(1:10))|11|24|12|15|(1:17)|18|26|19|22|23)|5|11|24|12|15|(0)|18|26|19|22|23) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0069, code lost:
    
        r0.setTextSize(2, 18.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0094, code lost:
    
        r0.setTextColor(-13421773);
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g() {
        /*
            r6 = this;
            android.widget.RelativeLayout r0 = new android.widget.RelativeLayout
            r0.<init>(r6)
            r6.r = r0
            r1 = 13107(0x3333, float:1.8367E-41)
            r0.setId(r1)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r1 = -1
            r2 = -2
            r0.<init>(r1, r2)
            android.widget.RelativeLayout r1 = r6.r
            r1.setLayoutParams(r0)
            android.widget.TextView r0 = new android.widget.TextView
            r0.<init>(r6)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r2, r2)
            r2 = 15
            r0.setGravity(r2)
            com.cmic.sso.sdk.view.a r2 = r6.A
            int r2 = r2.o()
            r3 = 0
            if (r2 != 0) goto L36
            r2 = 13
        L32:
            r1.addRule(r2)
            goto L5d
        L36:
            if (r2 <= 0) goto L5d
            int r4 = r6.C
            int r5 = r0.getWidth()
            int r4 = r4 - r5
            android.content.Context r5 = r6.c
            float r2 = (float) r2
            int r5 = com.cmic.sso.sdk.view.i.a(r5, r2)
            int r4 = r4 - r5
            if (r4 <= 0) goto L53
            android.content.Context r4 = r6.c
            int r2 = com.cmic.sso.sdk.view.i.a(r4, r2)
            r1.setMargins(r2, r3, r3, r3)
            goto L5d
        L53:
            java.lang.String r2 = com.cmic.sso.sdk.view.LoginAuthActivity.f412a
            java.lang.String r4 = "RelativeLayout.ALIGN_PARENT_RIGHT"
            com.mobile.auth.m.c.b(r2, r4)
            r2 = 11
            goto L32
        L5d:
            r2 = 2
            com.cmic.sso.sdk.view.a r4 = r6.A     // Catch: java.lang.Exception -> L69
            int r4 = r4.l()     // Catch: java.lang.Exception -> L69
            float r4 = (float) r4     // Catch: java.lang.Exception -> L69
            r0.setTextSize(r2, r4)     // Catch: java.lang.Exception -> L69
            goto L6e
        L69:
            r4 = 1099956224(0x41900000, float:18.0)
            r0.setTextSize(r2, r4)
        L6e:
            java.lang.String r2 = r6.o
            r0.setText(r2)
            com.cmic.sso.sdk.view.a r2 = r6.A
            boolean r2 = r2.m()
            if (r2 == 0) goto L80
            android.graphics.Typeface r2 = android.graphics.Typeface.DEFAULT_BOLD
            r0.setTypeface(r2)
        L80:
            r2 = 30583(0x7777, float:4.2856E-41)
            r0.setId(r2)
            android.widget.RelativeLayout r2 = r6.r
            r2.addView(r0, r1)
            com.cmic.sso.sdk.view.a r1 = r6.A     // Catch: java.lang.Exception -> L94
            int r1 = r1.n()     // Catch: java.lang.Exception -> L94
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> L94
            goto L9a
        L94:
            r1 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r0.setTextColor(r1)
        L9a:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r3)
            android.widget.RelativeLayout r1 = r6.r
            r1.measure(r0, r0)
            java.lang.String r0 = com.cmic.sso.sdk.view.LoginAuthActivity.f412a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "mPhoneLayout.getMeasuredHeight()="
            r1.<init>(r2)
            android.widget.RelativeLayout r2 = r6.r
            int r2 = r2.getMeasuredHeight()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.mobile.auth.m.c.b(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.view.LoginAuthActivity.g():void");
    }

    private RelativeLayout h() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.d = relativeLayout;
        relativeLayout.setId(17476);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(i.a(this.c, this.A.w()), i.a(this.c, this.A.x())));
        TextView textView = new TextView(this);
        textView.setTextSize(2, this.A.s());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setLayoutParams(layoutParams);
        if (this.A.t()) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        this.d.addView(textView);
        textView.setText(this.A.r());
        try {
            textView.setTextColor(this.A.u());
        } catch (Exception unused) {
            textView.setTextColor(-1);
        }
        try {
            this.d.setBackgroundResource(g.b(this.c, this.A.v()));
        } catch (Exception e) {
            e.printStackTrace();
            this.d.setBackgroundResource(g.b(this.c, "umcsdk_login_btn_bg"));
        }
        return this.d;
    }

    private RelativeLayout i() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.q = relativeLayout;
        relativeLayout.setHorizontalGravity(1);
        this.q.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        int I = this.A.I();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i.a(this.c, Math.max(I, 30)), i.a(this.c, Math.max(this.A.J(), 30)));
        if (this.A.ae() == 1) {
            layoutParams.addRule(15, -1);
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        this.x = relativeLayout2;
        relativeLayout2.setId(34952);
        this.x.setLayoutParams(layoutParams);
        CheckBox checkBox = new CheckBox(this);
        this.p = checkBox;
        checkBox.setChecked(false);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i.a(this.c, this.A.I()), i.a(this.c, this.A.J()));
        layoutParams2.setMargins(i.a(this.c, I > 30 ? 0.0f : 30 - I), 0, 0, 0);
        layoutParams2.addRule(11, -1);
        if (this.A.ae() == 1) {
            layoutParams2.addRule(15, -1);
        }
        this.p.setLayoutParams(layoutParams2);
        this.x.addView(this.p);
        this.q.addView(this.x);
        TextView textView = new TextView(this);
        textView.setTextSize(2, this.A.U());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(i.a(this.c, 5.0f), 0, 0, i.a(this.c, 5.0f));
        layoutParams3.addRule(1, 34952);
        textView.setLayoutParams(layoutParams3);
        this.q.addView(textView);
        textView.setTextColor(this.A.W());
        textView.setText(i.a(this, this.z, this.y, this.e, this.j, this.k));
        textView.setLineSpacing(8.0f, 1.0f);
        textView.setIncludeFontPadding(false);
        if (this.A.V()) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (this.A.Y()) {
            textView.setGravity(17);
        }
        textView.setHighlightColor(getResources().getColor(R.color.transparent));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.p.setButtonDrawable(new ColorDrawable());
        try {
            this.p.setBackgroundResource(g.b(this, this.A.H()));
        } catch (Exception unused) {
            this.p.setBackgroundResource(g.b(this, "umcsdk_uncheck_image"));
        }
        return this.q;
    }

    private String j() {
        this.z = this.A.L();
        if (this.A.ad()) {
            this.y = String.format("《%s》", this.y);
        }
        if (this.z.contains("$$运营商条款$$")) {
            this.z = this.z.replace("$$运营商条款$$", this.y);
        }
        return this.z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.d.setClickable(true);
        this.p.setClickable(true);
    }

    private void l() {
        this.d.setClickable(false);
        this.p.setClickable(false);
    }

    private void m() {
        try {
            if (this.t >= 5) {
                Toast.makeText(this.c, "网络不稳定,请返回重试其他登录方式", 1).show();
                this.d.setClickable(true);
                return;
            }
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                com.mobile.auth.m.c.a("stack", stackTraceElement.getClassName());
                String className = stackTraceElement.getClassName();
                if (!TextUtils.isEmpty(className) && className.contains("com.cmic.sso.sdk.activity") && !sb.toString().contains(className)) {
                    sb.append(className);
                    sb.append(";");
                }
            }
            this.m.a("loginTime", System.currentTimeMillis());
            String strB = this.m.b("traceId", "");
            if (!TextUtils.isEmpty(strB) && com.mobile.auth.m.e.a(strB)) {
                String strC = q.c();
                this.m.a("traceId", strC);
                com.mobile.auth.m.e.a(strC, this.v);
            }
            b();
            l();
            c cVar = new c(this.m);
            this.b.postDelayed(cVar, com.mobile.auth.f.a.a(this).c());
            n.a(new b(this, cVar));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        this.b.removeCallbacksAndMessages(null);
        h hVar = this.e;
        if (hVar != null && hVar.isShowing()) {
            this.e.dismiss();
        }
        h hVar2 = this.f;
        if (hVar2 != null && hVar2.isShowing()) {
            this.f.dismiss();
        }
        c();
        this.E = null;
        this.q.clearAnimation();
        finish();
        if (this.A.ah() == null || this.A.ai() == null) {
            return;
        }
        overridePendingTransition(g.c(this, this.A.ai()), g.c(this, this.A.ah()));
    }

    public void b() {
        com.mobile.auth.m.c.a(f412a, "loginClickStart");
        try {
            this.D = true;
            if (this.A.E() != null) {
                this.A.E().a(this.c, null);
            } else {
                Dialog dialog = this.E;
                if (dialog != null) {
                    dialog.show();
                    return;
                }
                AlertDialog alertDialogCreate = new AlertDialog.Builder(this).create();
                this.E = alertDialogCreate;
                alertDialogCreate.setCancelable(false);
                this.E.setCanceledOnTouchOutside(false);
                this.E.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.cmic.sso.sdk.view.LoginAuthActivity.8
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        return i == 4;
                    }
                });
                RelativeLayout relativeLayout = new RelativeLayout(this.E.getContext());
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
                ImageView imageView = new ImageView(this.E.getContext());
                imageView.setImageResource(g.b(this.c, "dialog_loading"));
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(80, 80);
                layoutParams.addRule(13, -1);
                relativeLayout.addView(imageView, layoutParams);
                if (this.E.getWindow() != null) {
                    this.E.getWindow().setDimAmount(0.0f);
                }
                this.E.show();
                this.E.setContentView(relativeLayout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.mobile.auth.m.c.a(f412a, "loginClickStart");
    }

    public void c() {
        try {
            com.mobile.auth.m.c.a(f412a, "loginClickComplete");
            if (this.A.E() == null || !this.D) {
                Dialog dialog = this.E;
                if (dialog != null && dialog.isShowing()) {
                    this.E.dismiss();
                }
            } else {
                this.D = false;
                this.A.E().b(this.c, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id != 17476) {
                if (id == 26214) {
                    a(false);
                    return;
                } else {
                    if (id != 34952) {
                        return;
                    }
                    if (this.p.isChecked()) {
                        this.p.setChecked(false);
                        return;
                    } else {
                        this.p.setChecked(true);
                        return;
                    }
                }
            }
            if (!this.p.isChecked()) {
                if (this.A.as() != null) {
                    Context context = this.c;
                    this.q.startAnimation(AnimationUtils.loadAnimation(context, g.c(context, this.A.as())));
                }
                if (this.A.F() != null) {
                    this.A.F().a(this.c, null);
                    return;
                } else if (!TextUtils.isEmpty(this.A.C())) {
                    Toast.makeText(this.c, this.A.C(), 1).show();
                    return;
                }
            }
            this.t++;
            m();
        } catch (Exception e) {
            com.cmic.sso.sdk.d.c.b.add(e);
            e.printStackTrace();
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
            com.cmic.sso.sdk.view.a aVarA = com.mobile.auth.f.a.a(this).a();
            this.A = aVarA;
            if (aVarA != null) {
                if (aVarA.ao() != -1) {
                    setTheme(this.A.ao());
                }
                if (this.A.af() != null && this.A.ag() != null) {
                    overridePendingTransition(g.c(this, this.A.af()), g.c(this, this.A.ag()));
                }
            }
            com.cmic.sso.sdk.d.a.a("authPageIn");
            this.s = System.currentTimeMillis();
            this.n = com.mobile.auth.f.c.a(this);
            d();
            f();
        } catch (Exception e) {
            this.m.a().f408a.add(e);
            com.mobile.auth.m.c.a(f412a, e.toString());
            e.printStackTrace();
            a("200025", "发生未知错误", this.m, null);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            this.b.removeCallbacksAndMessages(null);
            com.cmic.sso.sdk.d.a.a("timeOnAuthPage", (System.currentTimeMillis() - this.s) + "");
            com.cmic.sso.sdk.d.a.a("authPrivacyState", this.p.isChecked() ? "1" : "0");
            this.E = null;
            f.a().c();
            this.u.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            com.mobile.auth.m.c.a(f412a, "LoginAuthActivity clear failed");
            com.cmic.sso.sdk.d.c.b.add(e);
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.isCanceled() || keyEvent.getRepeatCount() != 0) {
            return true;
        }
        if (this.A.D() != null) {
            this.A.D().a();
        }
        if (this.A.aj() != 0 && !this.A.ar()) {
            return true;
        }
        a(false);
        return true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            com.cmic.sso.sdk.a aVar = this.m;
            if (aVar != null) {
                aVar.a("loginMethod", "loginAuth");
            }
            com.mobile.auth.f.a.a(this).a("200087", (JSONObject) null);
        } catch (Exception e) {
            this.m.a().f408a.add(e);
            a("200025", "发生未知错误", this.m, null);
        }
    }
}
