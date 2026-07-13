package com.bytedance.bdtracker;

import com.bytedance.applog.profile.UserProfileCallback;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public class a3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f212a = {"aid", "region", "os", "package", "app_version", Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, "os_version", "device_model", "resolution", IjkMediaMeta.IJKM_KEY_LANGUAGE, "timezone", "access", "display_name", "channel", "carrier", "app_language", "app_region", "tz_name", "tz_offset", "install_id", "openudid", "mcc_mnc", "rom", "manifest_version_code", "device_manufacturer", "clientudid", "sig_hash", "display_density", "os_api", "update_version_code", "density_dpi", "version_code", "sim_serial_number", "release_build", "udid", "cpu_abi", "google_aid"};
    public static final String[] b = {"setOnce", "synchronize"};
    public static final int[] c = {-1, -1};
    public static final long[] d = {-1, -1};

    public static class a implements UserProfileCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f213a;
        public final /* synthetic */ JSONObject b;
        public final /* synthetic */ UserProfileCallback c;

        public a(int i, JSONObject jSONObject, UserProfileCallback userProfileCallback) {
            this.f213a = i;
            this.b = jSONObject;
            this.c = userProfileCallback;
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onFail(int i) {
            this.c.onFail(i);
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onSuccess() {
            a3.c[this.f213a] = this.b.toString().hashCode();
            a3.d[this.f213a] = System.currentTimeMillis();
            this.c.onSuccess();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0118 A[Catch: JSONException -> 0x0131, TryCatch #0 {JSONException -> 0x0131, blocks: (B:40:0x0107, B:42:0x0111, B:44:0x0118, B:45:0x012d, B:37:0x00f3, B:39:0x0102), top: B:62:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.bytedance.bdtracker.c0 r25, int r26, org.json.JSONObject r27, com.bytedance.applog.profile.UserProfileCallback r28, android.os.Handler r29, boolean r30) {
        /*
            Method dump skipped, instruction units count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.a3.a(com.bytedance.bdtracker.c0, int, org.json.JSONObject, com.bytedance.applog.profile.UserProfileCallback, android.os.Handler, boolean):void");
    }
}
