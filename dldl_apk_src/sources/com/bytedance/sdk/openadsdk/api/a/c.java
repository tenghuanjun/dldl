package com.bytedance.sdk.openadsdk.api.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.downloadnew.core.ValueSetConstants;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c implements Bridge {
    private DownloadModel a;

    public c(DownloadModel downloadModel) {
        this.a = downloadModel;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a().a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_DOWNLOAD_URL, a()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_BACKUP_URLS, b()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_NOTIFICATION_JUMP_URL, c()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_ID, d()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_MD5, e()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_EXPECT_FILE_LENGTH, f()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_EXTRA_VALUE, g()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_NAME, h()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_MIME_TYPE, i()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_HEADERS, j()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_IS_SHOW_TOAST, k()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_IS_SHOW_NOTIFICATION, l()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_IS_NEED_WIFI, m()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_IS_IN_EXTERNAL_PUBLIC_DIR, n()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_IS_VISIBLE_IN_DOWNLOADS_UI, o()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_FILE_PATH, p()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_FILE_NAME, q()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_DOWNLOAD_SETTINGS, s()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_NEED_INDEPENDENT_PROCESS, v()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_VERSION_CODE, w()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_VERSION_NAME, x()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_IS_AD, y()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_LOG_EXTRA, z()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_PACKAGE_NAME, A()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_APP_ICON, B()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_DEEP_LINK, C()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_CLICK_TRACK_URL, D()).a(223430, E()).a(223431, F()).a(223432, G()).a(223433, H()).a(223434, I()).a(223435, J()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_EXECUTOR_GROUP, K()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_FUNNEL_TYPE, L()).a(ValueSetConstants.VALUE_DOWNLOAD_MODE_GET_START_TOAST, M()).a(223432, N()).a(223433, O()).a(223434, P()).a(223435, Q()).b();
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        switch (i) {
            case ValueSetConstants.VALUE_DOWNLOAD_MODE_FORCE_WIFI /* 223417 */:
                r();
                return null;
            case ValueSetConstants.VALUE_DOWNLOAD_MODE_FORCE_HIDE_TOAST /* 223419 */:
                t();
                return null;
            case ValueSetConstants.VALUE_DOWNLOAD_MODE_FORCE_HIDE_NOTIFICATION /* 223420 */:
                u();
                return null;
            case 223430:
                if (valueSet != null) {
                    return (T) a((String) valueSet.objectValue(223431, String.class));
                }
                return null;
            default:
                return null;
        }
    }

    public String a() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getDownloadUrl();
        }
        return null;
    }

    public List<String> b() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getBackupUrls();
        }
        return null;
    }

    public String c() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getNotificationJumpUrl();
        }
        return null;
    }

    public long d() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getId();
        }
        return 0L;
    }

    public String e() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getMd5();
        }
        return null;
    }

    public long f() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getExpectFileLength();
        }
        return 0L;
    }

    public long g() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getExtraValue();
        }
        return 0L;
    }

    public String h() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getName();
        }
        return null;
    }

    public String i() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getMimeType();
        }
        return null;
    }

    public Map<String, String> j() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getHeaders();
        }
        return null;
    }

    public boolean k() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isShowToast();
        }
        return false;
    }

    public boolean l() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isShowNotification();
        }
        return false;
    }

    public boolean m() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isNeedWifi();
        }
        return false;
    }

    public boolean n() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isInExternalPublicDir();
        }
        return false;
    }

    public boolean o() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isVisibleInDownloadsUi();
        }
        return false;
    }

    public String p() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getFilePath();
        }
        return null;
    }

    public String q() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getFileName();
        }
        return null;
    }

    public void r() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            downloadModel.forceWifi();
        }
    }

    public JSONObject s() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getDownloadSettings();
        }
        return null;
    }

    public void t() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            downloadModel.forceHideToast();
        }
    }

    public void u() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            downloadModel.forceHideNotification();
        }
    }

    public boolean v() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.needIndependentProcess();
        }
        return false;
    }

    public int w() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getVersionCode();
        }
        return 0;
    }

    public String x() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getVersionName();
        }
        return null;
    }

    public boolean y() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isAd();
        }
        return false;
    }

    public String z() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getLogExtra();
        }
        return null;
    }

    public String A() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getPackageName();
        }
        return null;
    }

    public String B() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getAppIcon();
        }
        return null;
    }

    public DeepLink C() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getDeepLink();
        }
        return null;
    }

    public List<String> D() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getClickTrackUrl();
        }
        return null;
    }

    public JSONObject E() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getExtra();
        }
        return null;
    }

    public int F() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getModelType();
        }
        return 0;
    }

    public com.ss.android.download.api.model.d G() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getQuickAppModel();
        }
        return null;
    }

    public boolean H() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.autoInstallWithoutNotification();
        }
        return false;
    }

    public IDownloadFileUriProvider I() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getDownloadFileUriProvider();
        }
        return null;
    }

    public boolean J() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.shouldDownloadWithPatchApply();
        }
        return false;
    }

    public int K() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getExecutorGroup();
        }
        return 0;
    }

    public int L() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getFunnelType();
        }
        return 0;
    }

    public String M() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getStartToast();
        }
        return null;
    }

    public DownloadModel a(String str) {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.setFilePath(str);
        }
        return null;
    }

    public String N() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.getSdkMonitorScene();
        }
        return null;
    }

    public boolean O() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.isAutoInstall();
        }
        return false;
    }

    public boolean P() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.distinctDir();
        }
        return false;
    }

    public boolean Q() {
        DownloadModel downloadModel = this.a;
        if (downloadModel != null) {
            return downloadModel.enablePause();
        }
        return false;
    }
}
