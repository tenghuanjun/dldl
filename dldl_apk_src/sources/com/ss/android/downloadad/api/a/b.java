package com.ss.android.downloadad.api.a;

import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.socialbase.downloader.constants.DbJsonConstants;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b implements a {
    private int A;
    private long B;
    private long C;
    private long D;
    private int E;
    private int F;
    private String G;
    private boolean H;
    private String I;
    private boolean J;
    private boolean K;
    private boolean L;
    private String M;
    private String N;
    private boolean O;
    private boolean P;
    private int Q;
    private int R;
    private long S;
    private long T;
    private boolean U;
    private boolean V;
    private String W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    protected boolean a;
    private long aa;
    private transient boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private String ai;
    private long aj;
    protected boolean b;
    public final AtomicBoolean c;
    public final AtomicBoolean d;
    public final AtomicBoolean e;
    private long f;
    private long g;
    private String h;
    private int i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private int o;
    private int p;
    private int q;
    private String r;
    private String s;
    private String t;
    private boolean u;
    private long v;
    private JSONObject w;
    private int x;
    private boolean y;
    private int z;

    @Override // com.ss.android.downloadad.api.a.a
    public JSONObject k() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public List<String> n() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public Object o() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public JSONObject p() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public JSONObject r() {
        return null;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public int t() {
        return -1;
    }

    private b() {
        this.i = 1;
        this.u = true;
        this.y = false;
        this.z = 0;
        this.A = 0;
        this.H = false;
        this.J = false;
        this.K = true;
        this.L = true;
        this.a = true;
        this.b = true;
        this.c = new AtomicBoolean(false);
        this.d = new AtomicBoolean(false);
        this.e = new AtomicBoolean(false);
        this.R = 1;
        this.U = true;
        this.aa = -1L;
    }

    public b(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        this(downloadModel, downloadEventConfig, downloadController, 0);
    }

    public b(DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, int i) {
        this.i = 1;
        this.u = true;
        this.y = false;
        this.z = 0;
        this.A = 0;
        this.H = false;
        this.J = false;
        this.K = true;
        this.L = true;
        this.a = true;
        this.b = true;
        this.c = new AtomicBoolean(false);
        this.d = new AtomicBoolean(false);
        this.e = new AtomicBoolean(false);
        this.R = 1;
        this.U = true;
        this.aa = -1L;
        this.f = downloadModel.getId();
        this.g = downloadModel.getExtraValue();
        this.h = downloadModel.getLogExtra();
        this.j = downloadModel.getPackageName();
        this.w = downloadModel.getExtra();
        this.u = downloadModel.isAd();
        this.q = downloadModel.getVersionCode();
        this.r = downloadModel.getVersionName();
        this.k = downloadModel.getDownloadUrl();
        if (downloadModel.getDeepLink() != null) {
            this.l = downloadModel.getDeepLink().getOpenUrl();
            this.m = downloadModel.getDeepLink().getWebUrl();
        }
        this.n = downloadModel.getModelType();
        this.s = downloadModel.getName();
        this.t = downloadModel.getAppIcon();
        this.I = downloadModel.getMimeType();
        this.M = downloadEventConfig.getClickButtonTag();
        this.N = downloadEventConfig.getRefer();
        this.O = downloadEventConfig.isEnableV3Event();
        this.y = downloadController.isEnableBackDialog();
        this.o = downloadController.getLinkMode();
        this.p = downloadController.getDownloadMode();
        this.U = downloadController.enableShowComplianceDialog();
        this.V = downloadController.isAutoDownloadOnCardShow();
        this.K = downloadController.enableNewActivity();
        this.a = downloadController.enableAH();
        this.b = downloadController.enableAM();
        this.x = i;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.v = jCurrentTimeMillis;
        this.B = jCurrentTimeMillis;
        this.J = downloadModel.shouldDownloadWithPatchApply();
        if (downloadModel instanceof AdDownloadModel) {
            this.ai = ((AdDownloadModel) downloadModel).getTaskKey();
        }
    }

    public int x() {
        return this.z;
    }

    public void a(int i) {
        this.z = i;
    }

    public synchronized void y() {
        this.z++;
    }

    public int z() {
        return this.A;
    }

    public void b(int i) {
        this.A = i;
    }

    public synchronized void A() {
        this.A++;
    }

    public long B() {
        long j = this.B;
        return j == 0 ? this.v : j;
    }

    public void a(long j) {
        this.B = j;
    }

    public long C() {
        return this.C;
    }

    public void b(long j) {
        this.C = j;
    }

    public void c(long j) {
        this.D = j;
    }

    public int D() {
        return this.E;
    }

    public void c(int i) {
        this.E = i;
    }

    public int E() {
        return this.F;
    }

    public void d(int i) {
        this.F = i;
    }

    public String F() {
        return this.G;
    }

    public void a(String str) {
        this.G = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public long b() {
        return this.f;
    }

    public void d(long j) {
        this.f = j;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public long l() {
        return this.g;
    }

    public void e(long j) {
        this.g = j;
    }

    public int G() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public String e() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public long H() {
        return this.v;
    }

    public void f(long j) {
        if (j > 0) {
            this.v = j;
        }
    }

    @Override // com.ss.android.downloadad.api.a.a
    public String d() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public boolean c() {
        return this.u;
    }

    public void a(boolean z) {
        this.u = z;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public JSONObject g() {
        return this.w;
    }

    public void a(JSONObject jSONObject) {
        this.w = jSONObject;
    }

    public void f(int i) {
        this.q = i;
    }

    public void d(String str) {
        this.r = str;
    }

    public int I() {
        return this.q;
    }

    public String J() {
        return this.r;
    }

    public void g(int i) {
        this.x = i;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public int s() {
        return this.x;
    }

    public void b(boolean z) {
        this.O = z;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public boolean m() {
        return this.O;
    }

    public void h(int i) {
        this.Q = i;
    }

    public int K() {
        return this.Q;
    }

    public void i(int i) {
        this.R = i;
    }

    public void e(String str) {
        this.m = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public int h() {
        return this.R;
    }

    public void c(boolean z) {
        this.y = z;
    }

    public boolean L() {
        return this.y;
    }

    public void f(String str) {
        this.M = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public String j() {
        return this.M;
    }

    public void g(String str) {
        this.N = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public String i() {
        return this.N;
    }

    public void h(String str) {
        this.k = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public String a() {
        return this.k;
    }

    public void i(String str) {
        this.l = str;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public String f() {
        return this.l;
    }

    public void g(long j) {
        this.aa = j;
    }

    public long M() {
        return this.aa;
    }

    public void j(String str) {
        this.s = str;
    }

    public String N() {
        return this.s;
    }

    public void k(String str) {
        this.t = str;
    }

    public void j(int i) {
        this.o = i;
    }

    public int O() {
        return this.o;
    }

    public void k(int i) {
        this.p = i;
    }

    public void l(int i) {
        this.n = i;
    }

    public void d(boolean z) {
        this.X = z;
    }

    public String P() {
        return this.W;
    }

    public void l(String str) {
        this.W = str;
    }

    public boolean Q() {
        return this.Y;
    }

    public void e(boolean z) {
        this.Y = z;
    }

    public boolean R() {
        return this.P;
    }

    public void f(boolean z) {
        this.P = z;
    }

    public boolean S() {
        return this.Z;
    }

    public void g(boolean z) {
        this.Z = z;
    }

    public long T() {
        return this.S;
    }

    public void h(long j) {
        this.S = j;
    }

    public long U() {
        return this.T;
    }

    public void i(long j) {
        this.T = j;
    }

    public boolean V() {
        return this.H;
    }

    public void h(boolean z) {
        this.H = z;
    }

    public String W() {
        return this.I;
    }

    public void m(String str) {
        this.I = str;
    }

    public boolean X() {
        return this.J;
    }

    public void i(boolean z) {
        this.J = z;
    }

    public boolean Y() {
        return this.ab;
    }

    public void j(boolean z) {
        this.ab = z;
    }

    public boolean Z() {
        return this.ac;
    }

    public void k(boolean z) {
        this.ac = z;
    }

    public boolean aa() {
        return this.ae;
    }

    public void l(boolean z) {
        this.ae = z;
    }

    public boolean ab() {
        return this.ad;
    }

    public void m(boolean z) {
        this.ad = z;
    }

    public boolean ac() {
        return this.af;
    }

    public void n(boolean z) {
        this.af = z;
    }

    public boolean ad() {
        return this.ag;
    }

    public void o(boolean z) {
        this.ag = z;
    }

    public void p(boolean z) {
        this.U = z;
    }

    public void q(boolean z) {
        this.V = z;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public boolean q() {
        return this.K;
    }

    public void r(boolean z) {
        this.K = z;
    }

    public void s(boolean z) {
        this.L = z;
    }

    public void t(boolean z) {
        this.a = z;
    }

    public void u(boolean z) {
        this.b = z;
    }

    public boolean ae() {
        return this.ah;
    }

    public void v(boolean z) {
        this.ah = z;
    }

    public String af() {
        return this.ai;
    }

    public void n(String str) {
        this.ai = str;
    }

    public long ag() {
        return this.aj;
    }

    public void j(long j) {
        this.aj = j;
    }

    public JSONObject ah() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mId", this.f);
            jSONObject.put("mExtValue", this.g);
            jSONObject.put("mLogExtra", this.h);
            jSONObject.put("mDownloadStatus", this.i);
            jSONObject.put("mPackageName", this.j);
            jSONObject.put("mIsAd", this.u);
            jSONObject.put("mTimeStamp", this.v);
            jSONObject.put("mExtras", this.w);
            jSONObject.put("mVersionCode", this.q);
            jSONObject.put("mVersionName", this.r);
            jSONObject.put("mDownloadId", this.x);
            jSONObject.put("mIsV3Event", this.O);
            jSONObject.put("mScene", this.Q);
            jSONObject.put("mEventTag", this.M);
            jSONObject.put("mEventRefer", this.N);
            jSONObject.put("mDownloadUrl", this.k);
            jSONObject.put("mEnableBackDialog", this.y);
            jSONObject.put("hasSendInstallFinish", this.c.get());
            jSONObject.put("hasSendDownloadFailedFinally", this.d.get());
            jSONObject.put("hasDoRebootMarketInstallFinishCheck", this.e.get());
            jSONObject.put("mLastFailedErrCode", this.F);
            jSONObject.put("mLastFailedErrMsg", this.G);
            jSONObject.put("mOpenUrl", this.l);
            jSONObject.put("mLinkMode", this.o);
            jSONObject.put("mDownloadMode", this.p);
            jSONObject.put("mModelType", this.n);
            jSONObject.put("mAppName", this.s);
            jSONObject.put("mAppIcon", this.t);
            jSONObject.put("mDownloadFailedTimes", this.z);
            jSONObject.put("mRecentDownloadResumeTime", this.B == 0 ? this.v : this.B);
            jSONObject.put("mClickPauseTimes", this.A);
            jSONObject.put("mJumpInstallTime", this.C);
            jSONObject.put("mCancelInstallTime", this.D);
            jSONObject.put("mLastFailedResumeCount", this.E);
            jSONObject.put("mIsUpdateDownload", this.H);
            jSONObject.put("mOriginMimeType", this.I);
            jSONObject.put("mIsPatchApplyHandled", this.J);
            jSONObject.put("downloadFinishReason", this.W);
            jSONObject.put("clickDownloadTime", this.S);
            jSONObject.put("clickDownloadSize", this.T);
            jSONObject.put("installAfterCleanSpace", this.P);
            jSONObject.put(TTDownloadField.TT_FUNNEL_TYPE, this.R);
            jSONObject.put(TTDownloadField.TT_WEB_URL, this.m);
            jSONObject.put(TTDownloadField.TT_ENABLE_SHOW_COMPLIANCE_DIALOG, this.U);
            jSONObject.put(TTDownloadField.TT_IS_AUTO_DOWNLOAD_ON_CARD_SHOW, this.V);
            int i = 1;
            jSONObject.put("enable_new_activity", this.K ? 1 : 0);
            jSONObject.put("enable_pause", this.L ? 1 : 0);
            jSONObject.put("enable_ah", this.a ? 1 : 0);
            if (!this.b) {
                i = 0;
            }
            jSONObject.put("enable_am", i);
            jSONObject.putOpt("intent_jump_browser_success", Boolean.valueOf(this.ah));
            jSONObject.put(DbJsonConstants.DBJSON_KEY_TASK_KEY, this.ai);
            jSONObject.putOpt("market_install_finish_check_start_timestamp", Long.valueOf(this.aj));
        } catch (Exception e) {
            k.u().a(e, "NativeDownloadModel toJson");
        }
        return jSONObject;
    }

    public static b b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        b bVar = new b();
        try {
            bVar.d(com.ss.android.download.api.c.b.a(jSONObject, "mId"));
            bVar.e(com.ss.android.download.api.c.b.a(jSONObject, "mExtValue"));
            bVar.c(jSONObject.optString("mLogExtra"));
            bVar.e(jSONObject.optInt("mDownloadStatus"));
            bVar.b(jSONObject.optString("mPackageName"));
            boolean z = true;
            bVar.a(jSONObject.optBoolean("mIsAd", true));
            bVar.f(com.ss.android.download.api.c.b.a(jSONObject, "mTimeStamp"));
            bVar.f(jSONObject.optInt("mVersionCode"));
            bVar.d(jSONObject.optString("mVersionName"));
            bVar.g(jSONObject.optInt("mDownloadId"));
            bVar.b(jSONObject.optBoolean("mIsV3Event"));
            bVar.h(jSONObject.optInt("mScene"));
            bVar.f(jSONObject.optString("mEventTag"));
            bVar.g(jSONObject.optString("mEventRefer"));
            bVar.h(jSONObject.optString("mDownloadUrl"));
            bVar.c(jSONObject.optBoolean("mEnableBackDialog"));
            bVar.c.set(jSONObject.optBoolean("hasSendInstallFinish"));
            bVar.d.set(jSONObject.optBoolean("hasSendDownloadFailedFinally"));
            bVar.e.set(jSONObject.optBoolean("hasDoRebootMarketInstallFinishCheck"));
            bVar.d(jSONObject.optInt("mLastFailedErrCode"));
            bVar.a(jSONObject.optString("mLastFailedErrMsg"));
            bVar.i(jSONObject.optString("mOpenUrl"));
            bVar.j(jSONObject.optInt("mLinkMode"));
            bVar.k(jSONObject.optInt("mDownloadMode"));
            bVar.l(jSONObject.optInt("mModelType"));
            bVar.j(jSONObject.optString("mAppName"));
            bVar.k(jSONObject.optString("mAppIcon"));
            bVar.a(jSONObject.optInt("mDownloadFailedTimes", 0));
            bVar.a(com.ss.android.download.api.c.b.a(jSONObject, "mRecentDownloadResumeTime"));
            bVar.b(jSONObject.optInt("mClickPauseTimes"));
            bVar.b(com.ss.android.download.api.c.b.a(jSONObject, "mJumpInstallTime"));
            bVar.c(com.ss.android.download.api.c.b.a(jSONObject, "mCancelInstallTime"));
            bVar.c(jSONObject.optInt("mLastFailedResumeCount"));
            bVar.l(jSONObject.optString("downloadFinishReason"));
            bVar.i(jSONObject.optLong("clickDownloadSize"));
            bVar.h(jSONObject.optLong("clickDownloadTime"));
            bVar.h(jSONObject.optBoolean("mIsUpdateDownload"));
            bVar.m(jSONObject.optString("mOriginMimeType"));
            bVar.i(jSONObject.optBoolean("mIsPatchApplyHandled"));
            bVar.f(jSONObject.optBoolean("installAfterCleanSpace"));
            bVar.i(jSONObject.optInt(TTDownloadField.TT_FUNNEL_TYPE, 1));
            bVar.e(jSONObject.optString(TTDownloadField.TT_WEB_URL));
            bVar.p(jSONObject.optBoolean(TTDownloadField.TT_ENABLE_SHOW_COMPLIANCE_DIALOG, true));
            bVar.q(jSONObject.optBoolean(TTDownloadField.TT_IS_AUTO_DOWNLOAD_ON_CARD_SHOW));
            bVar.r(jSONObject.optInt("enable_new_activity", 1) == 1);
            bVar.s(jSONObject.optInt("enable_pause", 1) == 1);
            bVar.t(jSONObject.optInt("enable_ah", 1) == 1);
            if (jSONObject.optInt("enable_am", 1) != 1) {
                z = false;
            }
            bVar.u(z);
            bVar.a(jSONObject.optJSONObject("mExtras"));
            bVar.v(jSONObject.optBoolean("intent_jump_browser_success"));
            bVar.n(jSONObject.optString(DbJsonConstants.DBJSON_KEY_TASK_KEY));
            bVar.j(jSONObject.optLong("market_install_finish_check_start_timestamp"));
        } catch (Exception e) {
            k.u().a(e, "NativeDownloadModel fromJson");
        }
        return bVar;
    }

    @Override // com.ss.android.downloadad.api.a.a
    public DownloadModel u() {
        return ai();
    }

    @Override // com.ss.android.downloadad.api.a.a
    public DownloadEventConfig v() {
        return aj();
    }

    @Override // com.ss.android.downloadad.api.a.a
    public DownloadController w() {
        return ak();
    }

    public AdDownloadModel ai() {
        return new AdDownloadModel.Builder().setAdId(this.f).setExtraValue(this.g).setLogExtra(this.h).setPackageName(this.j).setExtra(this.w).setIsAd(this.u).setVersionCode(this.q).setVersionName(this.r).setDownloadUrl(this.k).setModelType(this.n).setMimeType(this.I).setAppName(this.s).setAppIcon(this.t).setTaskKey(this.ai).setDeepLink(new DeepLink(this.l, this.m, null)).build();
    }

    public AdDownloadEventConfig aj() {
        return new AdDownloadEventConfig.Builder().setClickButtonTag(this.M).setRefer(this.N).setIsEnableV3Event(this.O).build();
    }

    public AdDownloadController ak() {
        return new AdDownloadController.Builder().setIsEnableBackDialog(this.y).setLinkMode(this.o).setDownloadMode(this.p).setEnableShowComplianceDialog(this.U).setEnableAH(this.a).setEnableAM(this.b).build();
    }
}
