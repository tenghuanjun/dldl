package com.bytedance.sdk.openadsdk.api.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.downloadnew.core.ValueSetConstants;
import com.ss.android.download.api.download.DownloadController;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements Bridge {
    private DownloadController a;

    public a(DownloadController downloadController) {
        this.a = downloadController;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a().a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_LINK_MODE, a()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_DOWNLOAD_MODE, b()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_IS_ENABLE_BACK_DIALOG, c()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_IS_ADD_TO_DOWNLOAD_MANAGE, d()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_IS_ENABLE_MULTIPLE_DOWNLOAD, e()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_DOWNLOAD_CHUNK_COUNT, f()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_EXTRA_CLICK_OPERATION, g()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_SHOULD_USE_NEW_WEB_VIEW, h()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_INTERCEPT_FLAG, i()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_EXTRA_JSON, j()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_EXTRA_OBJECT, k()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_GET_EXTRA_JSON, j()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_ENABLE_SHOW_COMPLIANCE_DIALOG, l()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_IS_AUTO_DOWNLOAD_ON_CARD_SHOW, m()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_ENABLE_NEW_ACTIVITY, n()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_ENABLE_AH, o()).a(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_ENABLE_AM, p()).b();
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        if (i == 223317) {
            if (valueSet == null) {
                return null;
            }
            b(((Boolean) valueSet.objectValue(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_PARAMETER_ENABLE_NEW_ACTIVITY, Boolean.class)).booleanValue());
            return null;
        }
        switch (i) {
            case ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_SET_DOWNLOAD_MODE /* 223311 */:
                if (valueSet != null) {
                    a(((Integer) valueSet.objectValue(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_PARAMETER_MODE, Integer.class)).intValue());
                }
                break;
            case ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_SET_LINK_MODE /* 223312 */:
                if (valueSet != null) {
                    b(((Integer) valueSet.objectValue(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_PARAMETER_LINK_MODE, Integer.class)).intValue());
                }
                break;
            case ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_SET_ENABLE_SHOW_COMPLIANCE_DIALOG /* 223313 */:
                if (valueSet != null) {
                    a(((Boolean) valueSet.objectValue(ValueSetConstants.VALUE_DOWNLOAD_CONTROLLER_PARAMETER_ENABLE_SHOW_COMPLIANCE_DIALOG, Boolean.class)).booleanValue());
                }
                break;
        }
        return null;
    }

    public int a() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getLinkMode();
        }
        return 0;
    }

    public int b() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getDownloadMode();
        }
        return 0;
    }

    public boolean c() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.isEnableBackDialog();
        }
        return false;
    }

    public boolean d() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.isAddToDownloadManage();
        }
        return false;
    }

    public boolean e() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.isEnableMultipleDownload();
        }
        return false;
    }

    public int f() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getDowloadChunkCount();
        }
        return 0;
    }

    public Object g() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getExtraClickOperation();
        }
        return null;
    }

    public boolean h() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.shouldUseNewWebView();
        }
        return false;
    }

    public int i() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getInterceptFlag();
        }
        return 0;
    }

    public JSONObject j() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getExtraJson();
        }
        return null;
    }

    public Object k() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.getExtraObject();
        }
        return null;
    }

    public void a(int i) {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            downloadController.setDownloadMode(i);
        }
    }

    public void b(int i) {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            downloadController.setLinkMode(i);
        }
    }

    public void a(boolean z) {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            downloadController.setEnableShowComplianceDialog(z);
        }
    }

    public boolean l() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.enableShowComplianceDialog();
        }
        return false;
    }

    public boolean m() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.isAutoDownloadOnCardShow();
        }
        return false;
    }

    public boolean n() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.enableNewActivity();
        }
        return false;
    }

    public void b(boolean z) {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            downloadController.setEnableNewActivity(z);
        }
    }

    public boolean o() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.enableAH();
        }
        return false;
    }

    public boolean p() {
        DownloadController downloadController = this.a;
        if (downloadController != null) {
            return downloadController.enableAM();
        }
        return false;
    }
}
