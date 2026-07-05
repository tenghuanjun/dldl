package com.bytedance.sdk.openadsdk.downloadnew;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.EventListener;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.api.a.e;
import com.bytedance.sdk.openadsdk.api.a.f;
import com.bytedance.sdk.openadsdk.api.a.g;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadAdapter;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.download.api.model.d;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d implements Bridge {
    private static volatile d a;
    private final Context b;
    private AdDownloadModel.Builder c;
    private AdDownloadModel d;
    private AdDownloadController.Builder e;
    private AdDownloadController f;
    private AdDownloadEventConfig.Builder g;
    private AdDownloadEventConfig h;

    private static boolean a(IDownloadButtonClickListener iDownloadButtonClickListener) {
        return iDownloadButtonClickListener != null;
    }

    private d(Context context) {
        this.b = context;
    }

    public static d a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d(context);
                }
            }
        }
        return a;
    }

    public <T> T a(Class<T> cls, int i, Map<String, Object> map) {
        DownloadModel downloadModelD;
        DownloadModel downloadModelD2;
        DownloadEventConfig downloadEventConfigE;
        DownloadController downloadControllerF;
        DownloadModel downloadModelD3;
        DownloadEventConfig downloadEventConfigE2;
        DownloadController downloadControllerF2;
        DownloadEventConfig downloadEventConfigE3;
        DownloadController downloadControllerF3;
        DownloadEventConfig downloadEventConfigE4;
        DownloadController downloadControllerF4;
        switch (i) {
            case 3:
                c.a(((Integer) map.get(TTDownloadField.TT_HID)).intValue());
                break;
            case 4:
                AdDownloadModel adDownloadModel = this.d;
                c.a().a(adDownloadModel == null ? (String) map.get(TTDownloadField.TT_DOWNLOAD_URL) : adDownloadModel.getDownloadUrl(), ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue());
                break;
            case 5:
                int iIntValue = ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue();
                AdDownloadModel adDownloadModel2 = this.d;
                if (adDownloadModel2 == null) {
                    downloadModelD = d(map.get(TTDownloadField.TT_DOWNLOAD_MODEL));
                } else {
                    downloadModelD = d(adDownloadModel2);
                }
                c.a().a(this.b, iIntValue, c(map.get(TTDownloadField.TT_DOWNLOAD_STATUSCHANGE_LISTENER)), downloadModelD);
                break;
            case 6:
                AdDownloadModel adDownloadModel3 = this.d;
                break;
            case 7:
                c.b();
                break;
            case 8:
                AdDownloadModel adDownloadModel4 = this.d;
                c.a().a(adDownloadModel4 == null ? (String) map.get(TTDownloadField.TT_DOWNLOAD_URL) : adDownloadModel4.getDownloadUrl(), ((Boolean) map.get("force")).booleanValue());
                break;
            case 9:
                c.a(((Integer) map.get("id")).intValue(), (ITTDownloadAdapter.OnEventLogHandler) map.get(TTDownloadField.TT_ONEVENT_LOG_HANDLER));
                break;
            case 10:
                c.a((String) map.get(TTDownloadField.TT_DOWNLOAD_PATH));
                break;
            case 12:
                Uri uri = (Uri) map.get("uri");
                AdDownloadModel adDownloadModel5 = this.d;
                if (adDownloadModel5 == null) {
                    downloadModelD2 = d(map.get(TTDownloadField.TT_DOWNLOAD_MODEL));
                } else {
                    downloadModelD2 = d(adDownloadModel5);
                }
                AdDownloadEventConfig adDownloadEventConfig = this.h;
                if (adDownloadEventConfig == null) {
                    downloadEventConfigE = e(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG));
                } else {
                    downloadEventConfigE = e(adDownloadEventConfig);
                }
                AdDownloadController adDownloadController = this.f;
                if (adDownloadController == null) {
                    downloadControllerF = f(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER));
                } else {
                    downloadControllerF = f(adDownloadController);
                }
                IDownloadButtonClickListener iDownloadButtonClickListenerJ = j(map.get(TTDownloadField.TT_DOWNLOAD_BUTTON_CLICK_LISTENER));
                if (!a(iDownloadButtonClickListenerJ)) {
                }
                break;
            case 13:
                int iIntValue2 = ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue();
                boolean zBooleanValue = ((Boolean) map.get(TTDownloadField.TT_IS_DISABLE_DIALOG)).booleanValue();
                String str = (String) map.get(TTDownloadField.TT_USERAGENT);
                AdDownloadModel adDownloadModel6 = this.d;
                if (adDownloadModel6 == null) {
                    downloadModelD3 = d(map.get(TTDownloadField.TT_DOWNLOAD_MODEL));
                } else {
                    downloadModelD3 = d(adDownloadModel6);
                }
                DownloadModel downloadModel = downloadModelD3;
                AdDownloadEventConfig adDownloadEventConfig2 = this.h;
                if (adDownloadEventConfig2 == null) {
                    downloadEventConfigE2 = e(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG));
                } else {
                    downloadEventConfigE2 = e(adDownloadEventConfig2);
                }
                DownloadEventConfig downloadEventConfig = downloadEventConfigE2;
                AdDownloadController adDownloadController2 = this.f;
                if (adDownloadController2 == null) {
                    downloadControllerF2 = f(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER));
                } else {
                    downloadControllerF2 = f(adDownloadController2);
                }
                DownloadController downloadController = downloadControllerF2;
                DownloadStatusChangeListener downloadStatusChangeListenerC = c(map.get(TTDownloadField.TT_DOWNLOAD_STATUSCHANGE_LISTENER));
                IDownloadButtonClickListener iDownloadButtonClickListenerJ2 = j(map.get(TTDownloadField.TT_DOWNLOAD_BUTTON_CLICK_LISTENER));
                if (a(iDownloadButtonClickListenerJ2)) {
                    c.a().e().a(this.b, str, zBooleanValue, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListenerC, iIntValue2, iDownloadButtonClickListenerJ2);
                } else {
                    c.a().e().a(this.b, str, zBooleanValue, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListenerC, iIntValue2);
                }
                break;
            case 14:
                AdDownloadModel adDownloadModel7 = this.d;
                long jLongValue = adDownloadModel7 == null ? ((Long) map.get("id")).longValue() : adDownloadModel7.getId();
                AdDownloadModel adDownloadModel8 = this.d;
                break;
            case 16:
                AdDownloadModel adDownloadModel9 = this.d;
                String downloadUrl = adDownloadModel9 == null ? (String) map.get(TTDownloadField.TT_DOWNLOAD_URL) : adDownloadModel9.getDownloadUrl();
                AdDownloadModel adDownloadModel10 = this.d;
                long jLongValue2 = adDownloadModel10 == null ? ((Long) map.get("id")).longValue() : adDownloadModel10.getId();
                int iIntValue3 = ((Integer) map.get(TTDownloadField.TT_ACTION_TYPE_BUTTON)).intValue();
                AdDownloadEventConfig adDownloadEventConfig3 = this.h;
                if (adDownloadEventConfig3 == null) {
                    downloadEventConfigE3 = e(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG));
                } else {
                    downloadEventConfigE3 = e(adDownloadEventConfig3);
                }
                DownloadEventConfig downloadEventConfig2 = downloadEventConfigE3;
                AdDownloadController adDownloadController3 = this.f;
                if (adDownloadController3 == null) {
                    downloadControllerF3 = f(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER));
                } else {
                    downloadControllerF3 = f(adDownloadController3);
                }
                c.a().a(downloadUrl, jLongValue2, iIntValue3, downloadEventConfig2, downloadControllerF3);
                break;
            case 17:
                AdDownloadModel adDownloadModel11 = this.d;
                String downloadUrl2 = adDownloadModel11 == null ? (String) map.get(TTDownloadField.TT_DOWNLOAD_URL) : adDownloadModel11.getDownloadUrl();
                long jLongValue3 = ((Long) map.get("id")).longValue();
                int iIntValue4 = ((Integer) map.get(TTDownloadField.TT_ACTION_TYPE_BUTTON)).intValue();
                AdDownloadEventConfig adDownloadEventConfig4 = this.h;
                if (adDownloadEventConfig4 == null) {
                    downloadEventConfigE4 = e(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG));
                } else {
                    downloadEventConfigE4 = e(adDownloadEventConfig4);
                }
                DownloadEventConfig downloadEventConfig3 = downloadEventConfigE4;
                AdDownloadController adDownloadController4 = this.f;
                if (adDownloadController4 == null) {
                    downloadControllerF4 = f(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER));
                } else {
                    downloadControllerF4 = f(adDownloadController4);
                }
                c.a().a(downloadUrl2, jLongValue3, iIntValue4, downloadEventConfig3, downloadControllerF4, i(map.get(TTDownloadField.TT_ITEM_CLICK_LISTENER)), j(map.get(TTDownloadField.TT_DOWNLOAD_BUTTON_CLICK_LISTENER)));
                break;
            case 18:
                AdDownloadModel adDownloadModel12 = this.d;
                break;
            case 19:
                AdDownloadModel adDownloadModel13 = this.d;
                break;
            case 23:
                if (((Boolean) map.get(TTDownloadField.TT_MATE_IS_EMPTY)).booleanValue()) {
                    AdDownloadModel.Builder builder = new AdDownloadModel.Builder();
                    this.c = builder;
                    this.d = builder.build();
                } else {
                    b(map);
                }
                break;
            case 24:
                a((String) map.get(TTDownloadField.TT_APP_ICON), (String) map.get("appName"), (String) map.get("packageName"));
                break;
            case 25:
                a(((Integer) map.get(TTDownloadField.TT_AUTO_OPEN)).intValue(), ((Integer) map.get(TTDownloadField.TT_DOWNLOAD_MODE)).intValue(), ((Boolean) map.get(TTDownloadField.TT_IS_HAVE_DOWNLOAD_SDK_CONFIG)).booleanValue(), ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_AH)).booleanValue(), ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_AM)).booleanValue());
                break;
            case 26:
                f(((Integer) map.get(TTDownloadField.TT_DOWNLOAD_MODE)).intValue());
                break;
            case 28:
                j(((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_OPPO_AUTO_DOWNLOAD)).booleanValue());
                break;
            case 29:
                d(map);
                break;
            case 30:
                b(((Integer) map.get(TTDownloadField.TT_DOWNLOAD_SCENE)).intValue());
                break;
            case 31:
                e(((Boolean) map.get(TTDownloadField.TT_IS_SHOW_TOAST)).booleanValue());
                break;
            case 32:
                c(map);
                break;
            case 44:
                a(((Integer) map.get(TTDownloadField.TT_LINK_MODE)).intValue());
                break;
            case 46:
                a(((Boolean) map.get(TTDownloadField.TT_ENABLE_SHOW_COMPLIANCE_DIALOG)).booleanValue());
                break;
            case 49:
                b(((Boolean) map.get(TTDownloadField.TT_IS_AUTO_DOWNLOAD_ON_CARD_SHOW)).booleanValue());
                break;
            case 50:
                c(((Boolean) map.get(TTDownloadField.TT_ENABLE_NEW_ACTIVITY)).booleanValue());
                break;
            case 53:
                a(map.get(TTDownloadField.TT_EXTRA_OBJECT));
                break;
            case 54:
                a((JSONObject) map.get(TTDownloadField.TT_EXTRA_JSON));
                break;
            case 56:
                e(map);
                break;
            case 72:
                b(map.get(TTDownloadField.TT_EXTRA_EVENT_OBJECT));
                break;
            case 73:
                a((String) map.get(TTDownloadField.TT_CLICK_BUTTON_TAG));
                break;
            case 74:
                b((JSONObject) map.get(TTDownloadField.TT_EVENT_CONFIG_EXTRA_JSON));
                break;
            case 75:
                c((JSONObject) map.get(TTDownloadField.TT_PARAMS_JSON));
                break;
            case 76:
                b((String) map.get(TTDownloadField.TT_CLICK_ITEM_TAG));
                break;
            case 78:
                c((String) map.get(TTDownloadField.TT_REFER));
                break;
            case 79:
                d((String) map.get(TTDownloadField.TT_QUICK_APP_EVENT_TAG));
                break;
            case 80:
                f(map);
                break;
            case 98:
                W();
                break;
            case 100:
                Y();
                break;
            case 101:
                Z();
                break;
            case 123:
                e((String) map.get("md5"));
                break;
            case 124:
                a(((Long) map.get(TTDownloadField.TT_EXPECT_FILE_LENGTH)).longValue());
                break;
            case 125:
                d(((Boolean) map.get(TTDownloadField.TT_NEED_WIFI)).booleanValue());
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA_VALUE /* 127 */:
                b(((Long) map.get(TTDownloadField.TT_EXTRA_VALUE)).longValue());
                break;
            case 128:
                f((String) map.get("appName"));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA /* 129 */:
                d((JSONObject) map.get(TTDownloadField.TT_EXTRA_JSON));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_START_TOAST /* 130 */:
                g((String) map.get(TTDownloadField.TT_START_TOAST));
                break;
            case 131:
                h((String) map.get(TTDownloadField.TT_SDK_MONITOR_SCENE));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_ID /* 132 */:
                c(((Long) map.get("id")).longValue());
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_IS_AD /* 133 */:
                f(((Boolean) map.get(TTDownloadField.TT_IS_AD)).booleanValue());
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_MODEL_TYPE /* 134 */:
                c(((Integer) map.get(TTDownloadField.TT_MODEL_TYPE)).intValue());
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_LOG_EXTRA /* 135 */:
                i((String) map.get(TTDownloadField.TT_LOG_EXTRA));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_PACKAGE_NAME /* 136 */:
                j((String) map.get("packageName"));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_APP_ICON /* 137 */:
                k((String) map.get(TTDownloadField.TT_APP_ICON));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_CLICK_TRACK_URL /* 139 */:
                a((List<String>) map.get(TTDownloadField.TT_CLICK_TRACK_URL));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_DOWNLOAD_URL /* 140 */:
                l((String) map.get(TTDownloadField.TT_DOWNLOAD_URL));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_BACKUP_URLS /* 141 */:
                b((List<String>) map.get(TTDownloadField.TT_BACK_UP_URLS));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_NOTIFICATION_JUMP_URL /* 142 */:
                m((String) map.get(TTDownloadField.TT_NOTIFICATION_JUMP_URL));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_MIME_TYPE /* 143 */:
                n((String) map.get("mimeType"));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_HEADERS /* 144 */:
                a((Map<String, String>) map.get(TTDownloadField.TT_HEADERS));
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_IS_SHOW_NOTIFICATION /* 145 */:
                g(((Boolean) map.get(TTDownloadField.TT_IS_SHOW_NOTIFICATION)).booleanValue());
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_FILE_PATH /* 146 */:
                o((String) map.get(TTDownloadField.TT_FILE_PATH));
                break;
            case 147:
                p((String) map.get(TTDownloadField.TT_FILE_NAME));
                break;
            case 148:
                h(((Boolean) map.get(TTDownloadField.TT_IS_NEED_INDEPENDENT_PROCESS)).booleanValue());
                break;
            case 149:
                d(((Integer) map.get(TTDownloadField.TT_VERSION_CODE)).intValue());
                break;
            case 150:
                q((String) map.get(TTDownloadField.TT_VERSION_NAME));
                break;
            case 151:
                a(new d.a().a((String) map.get(TTDownloadField.TT_QUICK_APP_MODEL_OPEN_URL)).b((String) map.get(TTDownloadField.TT_QUICK_APP_MODEL_EXTRA_DATA)).a());
                break;
            case TTDownloadField.CALL_DOWNLOAD_MODEL_SET_AUTO_INSTALL_WITHOUT_NOTIFICATION /* 152 */:
                i(((Boolean) map.get(TTDownloadField.TT_IS_AUTO_INSTALL_WITHOUT_NOTIFICATION)).booleanValue());
                break;
            case 153:
                e(((Integer) map.get(TTDownloadField.TT_FUNNEL_TYPE)).intValue());
                break;
        }
        return null;
    }

    public void a(Bundle bundle) {
        c.a(this.b);
    }

    private DownloadStatusChangeListener c(Object obj) {
        if (obj instanceof DownloadStatusChangeListener) {
            return (DownloadStatusChangeListener) obj;
        }
        if (obj instanceof EventListener) {
            return new e((EventListener) obj);
        }
        return null;
    }

    private DownloadModel d(Object obj) {
        if (obj instanceof DownloadModel) {
            return (DownloadModel) obj;
        }
        return null;
    }

    private DownloadEventConfig e(Object obj) {
        if (obj instanceof DownloadEventConfig) {
            return (DownloadEventConfig) obj;
        }
        return null;
    }

    private DownloadController f(Object obj) {
        if (obj instanceof DownloadController) {
            return (DownloadController) obj;
        }
        return null;
    }

    private Activity g(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        return null;
    }

    private ExitInstallListener h(Object obj) {
        if (obj instanceof ExitInstallListener) {
            return (ExitInstallListener) obj;
        }
        return null;
    }

    private OnItemClickListener i(Object obj) {
        if (obj instanceof OnItemClickListener) {
            return (OnItemClickListener) obj;
        }
        if (obj instanceof EventListener) {
            return new g((EventListener) obj);
        }
        return null;
    }

    private IDownloadButtonClickListener j(Object obj) {
        if (obj instanceof IDownloadButtonClickListener) {
            return (IDownloadButtonClickListener) obj;
        }
        if (obj instanceof EventListener) {
            return new f((EventListener) obj);
        }
        return null;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a().a(0, c.a).a(1, Boolean.valueOf(c.b)).a(10000, 3).b();
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        Map<String, Object> map;
        if (i == 20) {
            a((Bundle) valueSet.objectValue(0, Bundle.class));
            return null;
        }
        if (valueSet != null && valueSet.objectValue(0, Map.class) != null) {
            map = (Map) valueSet.objectValue(0, Map.class);
        } else {
            map = new HashMap<>();
        }
        return (T) a(cls, i, map);
    }

    private AdDownloadModel.Builder a(long j, String str, boolean z, boolean z2, String str2, JSONObject jSONObject, JSONObject jSONObject2, String str3, String str4, String str5, String str6, boolean z3, String str7, String str8, String str9) {
        AdDownloadModel.Builder fileUriProvider = new AdDownloadModel.Builder().setAdId(j).setAppIcon(str).setIsShowNotification(z).setAutoInstallWithoutNotification(z2).setLogExtra(str2).setExtra(jSONObject).setDistinctDir(true).setIsAd(true).setFileUriProvider(new IDownloadFileUriProvider() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.1
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider
            public Uri getUriForFile(String str10, String str11) {
                return null;
            }
        });
        if (jSONObject2 != null) {
            fileUriProvider.setDownloadSettings(jSONObject2);
        }
        if (!TextUtils.isEmpty(str3)) {
            fileUriProvider.setFilePath(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            fileUriProvider.setDownloadUrl(str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            fileUriProvider.setAppName(str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            fileUriProvider.setPackageName(str6);
        }
        fileUriProvider.setNeedIndependentProcess(z3);
        fileUriProvider.setDeepLink(a(j, str7, str8, str9));
        return fileUriProvider;
    }

    private DeepLink a(long j, String str, String str2, String str3) {
        DeepLink deepLink = new DeepLink();
        deepLink.setId(j);
        deepLink.setOpenUrl(str);
        deepLink.setWebTitle(str2);
        deepLink.setWebUrl(str3);
        return deepLink;
    }

    private void a(String str, String str2, String str3) {
        AdDownloadModel.Builder builder = this.c;
        if (builder == null) {
            return;
        }
        this.d = builder.setAppIcon(str).setAppName(str2).setPackageName(str3).build();
    }

    private void b(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        AdDownloadModel.Builder builderA = a(((Long) map.get("id")).longValue(), (String) map.get(TTDownloadField.TT_APP_ICON), ((Boolean) map.get(TTDownloadField.TT_IS_SHOW_NOTIFICATION)).booleanValue(), ((Boolean) map.get(TTDownloadField.TT_IS_AUTO_INSTALL_WITHOUT_NOTIFICATION)).booleanValue(), (String) map.get(TTDownloadField.TT_LOG_EXTRA), (JSONObject) map.get(TTDownloadField.TT_EXTRA_JSON), (JSONObject) map.get(TTDownloadField.TT_DOWNLOAD_SETTINGS), (String) map.get(TTDownloadField.TT_FILE_PATH), (String) map.get(TTDownloadField.TT_DOWNLOAD_URL), (String) map.get("appName"), (String) map.get("packageName"), ((Boolean) map.get(TTDownloadField.TT_IS_NEED_INDEPENDENT_PROCESS)).booleanValue(), (String) map.get(TTDownloadField.TT_OPEN_URL), (String) map.get(TTDownloadField.TT_WEB_TITLE), (String) map.get(TTDownloadField.TT_WEB_URL));
        this.c = builderA;
        this.d = builderA.build();
    }

    private void a(int i, int i2, boolean z, boolean z2, boolean z3) {
        AdDownloadController.Builder isAddToDownloadManage = new AdDownloadController.Builder().setLinkMode(i).setDownloadMode(i2).setIsEnableBackDialog(true).setIsAddToDownloadManage(false);
        this.e = isAddToDownloadManage;
        if (z) {
            isAddToDownloadManage.setEnableAH(z2);
            this.e.setEnableAM(z3);
        }
        this.f = this.e.build();
    }

    private void c(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        int iIntValue = ((Integer) map.get(TTDownloadField.TT_LINK_MODE)).intValue();
        int iIntValue2 = ((Integer) map.get(TTDownloadField.TT_DOWNLOAD_MODE)).intValue();
        boolean zBooleanValue = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_BACK_DIALOG)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) map.get(TTDownloadField.TT_IS_ADD_TO_DOWNLOAD_MANAGE)).booleanValue();
        Object obj = map.get(TTDownloadField.TT_EXTRA_OPERATION);
        boolean zBooleanValue3 = ((Boolean) map.get(TTDownloadField.TT_SHOULD_USE_NEW_WEB_VIEW)).booleanValue();
        int iIntValue3 = ((Integer) map.get(TTDownloadField.TT_INTERCEPT_FLAG)).intValue();
        JSONObject jSONObject = (JSONObject) map.get(TTDownloadField.TT_EXTRA_JSON);
        Object obj2 = map.get(TTDownloadField.TT_EXTRA_OBJECT);
        boolean zBooleanValue4 = ((Boolean) map.get(TTDownloadField.TT_ENABLE_SHOW_COMPLIANCE_DIALOG)).booleanValue();
        boolean zBooleanValue5 = ((Boolean) map.get(TTDownloadField.TT_IS_AUTO_DOWNLOAD_ON_CARD_SHOW)).booleanValue();
        boolean zBooleanValue6 = ((Boolean) map.get(TTDownloadField.TT_ENABLE_NEW_ACTIVITY)).booleanValue();
        boolean zBooleanValue7 = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_AH)).booleanValue();
        boolean zBooleanValue8 = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_AM)).booleanValue();
        AdDownloadController.Builder enableOppoAutoDownload = new AdDownloadController.Builder().setLinkMode(iIntValue).setDownloadMode(iIntValue2).setIsEnableBackDialog(zBooleanValue).setIsAddToDownloadManage(zBooleanValue2).setExtraOperation(obj).setShouldUseNewWebView(zBooleanValue3).setInterceptFlag(iIntValue3).setExtraJson(jSONObject).setExtraObject(obj2).setEnableShowComplianceDialog(zBooleanValue4).setIsAutoDownloadOnCardShow(zBooleanValue5).setEnableNewActivity(zBooleanValue6).setEnableAH(zBooleanValue7).setEnableAM(zBooleanValue8).setEnableOppoAutoDownload(((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_OPPO_AUTO_DOWNLOAD)).booleanValue());
        this.e = enableOppoAutoDownload;
        this.f = enableOppoAutoDownload.build();
    }

    private void f(int i) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setDownloadMode(i);
    }

    private int av() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return 0;
        }
        return adDownloadController.getDownloadMode();
    }

    private void d(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        String str = (String) map.get(TTDownloadField.TT_CLICK_BUTTON_TAG);
        String str2 = (String) map.get(TTDownloadField.TT_CLICK_ITEM_TAG);
        String str3 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_START);
        String str4 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_CONTINUE);
        String str5 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_PAUSE);
        String str6 = (String) map.get(TTDownloadField.TT_LABEL_STORAGE_DENY);
        String str7 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_INSTALL);
        boolean zBooleanValue = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_CLICK_EVENT)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_V3_EVENT)).booleanValue();
        JSONObject jSONObject = (JSONObject) map.get(TTDownloadField.TT_EXTRA_EVENT_OBJECT);
        AdDownloadEventConfig.Builder isEnableV3Event = new AdDownloadEventConfig.Builder().setClickButtonTag(str).setClickItemTag(str2).setClickStartLabel(str3).setClickContinueLabel(str4).setClickPauseLabel(str5).setStorageDenyLabel(str6).setClickInstallLabel(str7).setIsEnableClickEvent(zBooleanValue).setIsEnableV3Event(zBooleanValue2);
        this.g = isEnableV3Event;
        if (jSONObject != null) {
            isEnableV3Event.setExtraEventObject(jSONObject);
        }
        this.h = this.g.build();
    }

    private void e(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        String str = (String) map.get(TTDownloadField.TT_CLICK_BUTTON_TAG);
        String str2 = (String) map.get(TTDownloadField.TT_CLICK_ITEM_TAG);
        String str3 = (String) map.get(TTDownloadField.TT_CLICK_LABEL);
        int iIntValue = ((Integer) map.get(TTDownloadField.TT_DOWNLOAD_SCENE)).intValue();
        String str4 = (String) map.get(TTDownloadField.TT_REFER);
        JSONObject jSONObject = (JSONObject) map.get(TTDownloadField.TT_EXTRA_JSON);
        JSONObject jSONObject2 = (JSONObject) map.get(TTDownloadField.TT_PARAMS_JSON);
        String str5 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_START);
        String str6 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_CONTINUE);
        String str7 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_PAUSE);
        String str8 = (String) map.get(TTDownloadField.TT_LABEL_STORAGE_DENY);
        String str9 = (String) map.get(TTDownloadField.TT_LABEL_CLICK_INSTALL);
        boolean zBooleanValue = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_CLICK_EVENT)).booleanValue();
        boolean zBooleanValue2 = ((Boolean) map.get(TTDownloadField.TT_IS_ENABLE_V3_EVENT)).booleanValue();
        JSONObject jSONObject3 = (JSONObject) map.get(TTDownloadField.TT_EXTRA_EVENT_OBJECT);
        AdDownloadEventConfig.Builder paramsJson = new AdDownloadEventConfig.Builder().setClickButtonTag(str).setClickItemTag(str2).setClickLabel(str3).setClickStartLabel(str5).setClickContinueLabel(str6).setClickPauseLabel(str7).setStorageDenyLabel(str8).setClickInstallLabel(str9).setIsEnableClickEvent(zBooleanValue).setDownloadScene(iIntValue).setIsEnableV3Event(zBooleanValue2).setRefer(str4).setExtraJson(jSONObject).setParamsJson(jSONObject2);
        this.g = paramsJson;
        if (jSONObject3 != null) {
            paramsJson.setExtraEventObject(jSONObject3);
        }
        this.h = this.g.build();
    }

    public int a() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return 0;
        }
        return adDownloadController.getLinkMode();
    }

    public boolean b() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.isEnableBackDialog();
    }

    public boolean c() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.isAddToDownloadManage();
    }

    public Object d() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return null;
        }
        return adDownloadController.getExtraClickOperation();
    }

    public boolean e() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.isEnableMultipleDownload();
    }

    public int f() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return 1;
        }
        return adDownloadController.getDowloadChunkCount();
    }

    public boolean g() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.shouldUseNewWebView();
    }

    public int h() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return 0;
        }
        return adDownloadController.getInterceptFlag();
    }

    public JSONObject i() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return null;
        }
        return adDownloadController.getExtraJson();
    }

    public Object j() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return null;
        }
        return adDownloadController.getExtraObject();
    }

    public void a(int i) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setLinkMode(i);
    }

    public boolean k() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.enableShowComplianceDialog();
    }

    public void a(boolean z) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setEnableShowComplianceDialog(z);
    }

    public boolean l() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.isAutoDownloadOnCardShow();
    }

    public boolean m() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.enableNewActivity();
    }

    public void b(boolean z) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setIsAutoDownloadOnCardShow(z);
    }

    public void c(boolean z) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setEnableNewActivity(z);
    }

    public boolean n() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.enableAH();
    }

    public boolean o() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.enableAM();
    }

    public void a(Object obj) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setExtraObject(obj);
    }

    public void a(JSONObject jSONObject) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        adDownloadController.setExtraJson(jSONObject);
    }

    public boolean p() {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return false;
        }
        return adDownloadController.enableOppoAutoDownload();
    }

    private void j(boolean z) {
        AdDownloadController adDownloadController = this.f;
        if (adDownloadController == null) {
            return;
        }
        try {
            adDownloadController.setEnableOppoAutoDownload(z);
        } catch (Throwable unused) {
        }
    }

    public String q() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getRefer();
    }

    public String r() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickButtonTag();
    }

    public String s() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickItemTag();
    }

    public String t() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickLabel();
    }

    public String u() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickStartLabel();
    }

    public String v() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickPauseLabel();
    }

    public String w() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickPauseLabel();
    }

    public String x() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getClickInstallLabel();
    }

    public String y() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        return adDownloadEventConfig == null ? "" : adDownloadEventConfig.getStorageDenyLabel();
    }

    public Object z() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return null;
        }
        return adDownloadEventConfig.getExtraEventObject();
    }

    public int A() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return 0;
        }
        return adDownloadEventConfig.getDownloadScene();
    }

    public boolean B() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return true;
        }
        return adDownloadEventConfig.isEnableClickEvent();
    }

    public boolean C() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return false;
        }
        return adDownloadEventConfig.isEnableV3Event();
    }

    public JSONObject D() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return null;
        }
        return adDownloadEventConfig.getExtraJson();
    }

    public JSONObject E() {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return null;
        }
        return adDownloadEventConfig.getParamsJson();
    }

    public void b(Object obj) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setExtraEventObject(obj);
    }

    public void a(String str) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setClickButtonTag(str);
    }

    public void b(JSONObject jSONObject) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setExtraJson(jSONObject);
    }

    public void c(JSONObject jSONObject) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setParamsJson(jSONObject);
    }

    public void b(String str) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setClickItemTag(str);
    }

    public void b(int i) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setDownloadScene(i);
    }

    public void c(String str) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setRefer(str);
    }

    public void d(String str) {
        AdDownloadEventConfig adDownloadEventConfig = this.h;
        if (adDownloadEventConfig == null) {
            return;
        }
        adDownloadEventConfig.setQuickAppEventTag(str);
    }

    private void f(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        long jLongValue = ((Long) map.get(TTDownloadField.TT_EXPECT_FILE_LENGTH)).longValue();
        String str = (String) map.get("md5");
        long jLongValue2 = ((Long) map.get(TTDownloadField.TT_EXTRA_VALUE)).longValue();
        boolean zBooleanValue = ((Boolean) map.get(TTDownloadField.TT_IS_AD)).booleanValue();
        int iIntValue = ((Integer) map.get(TTDownloadField.TT_MODEL_TYPE)).intValue();
        List<String> list = (List) map.get(TTDownloadField.TT_CLICK_TRACK_URL);
        List<String> list2 = (List) map.get(TTDownloadField.TT_BACK_UP_URLS);
        String str2 = (String) map.get(TTDownloadField.TT_NOTIFICATION_JUMP_URL);
        String str3 = (String) map.get("mimeType");
        Map<String, String> map2 = (Map) map.get(TTDownloadField.TT_HEADERS);
        boolean zBooleanValue2 = ((Boolean) map.get(TTDownloadField.TT_IS_SHOW_TOAST)).booleanValue();
        boolean zBooleanValue3 = ((Boolean) map.get(TTDownloadField.TT_NEED_WIFI)).booleanValue();
        String str4 = (String) map.get(TTDownloadField.TT_FILE_NAME);
        int iIntValue2 = ((Integer) map.get(TTDownloadField.TT_VERSION_CODE)).intValue();
        String str5 = (String) map.get(TTDownloadField.TT_VERSION_NAME);
        String str6 = (String) map.get(TTDownloadField.TT_QUICK_APP_MODEL_OPEN_URL);
        com.ss.android.download.api.model.d dVarA = new d.a().a(str6).b((String) map.get(TTDownloadField.TT_QUICK_APP_MODEL_EXTRA_DATA)).a();
        int iIntValue3 = ((Integer) map.get(TTDownloadField.TT_EXECUTOR_GROUP)).intValue();
        String str7 = (String) map.get(TTDownloadField.TT_START_TOAST);
        String str8 = (String) map.get(TTDownloadField.TT_SDK_MONITOR_SCENE);
        boolean zBooleanValue4 = ((Boolean) map.get(TTDownloadField.TT_AUTO_INSTALL)).booleanValue();
        boolean zBooleanValue5 = ((Boolean) map.get(TTDownloadField.TT_DISTINCT_DIR)).booleanValue();
        boolean zBooleanValue6 = ((Boolean) map.get(TTDownloadField.TT_ENABLE_PAUSE)).booleanValue();
        long jLongValue3 = ((Long) map.get("id")).longValue();
        String str9 = (String) map.get(TTDownloadField.TT_APP_ICON);
        boolean zBooleanValue7 = ((Boolean) map.get(TTDownloadField.TT_IS_SHOW_NOTIFICATION)).booleanValue();
        boolean zBooleanValue8 = ((Boolean) map.get(TTDownloadField.TT_IS_AUTO_INSTALL_WITHOUT_NOTIFICATION)).booleanValue();
        String str10 = (String) map.get(TTDownloadField.TT_LOG_EXTRA);
        JSONObject jSONObject = (JSONObject) map.get(TTDownloadField.TT_EXTRA_JSON);
        JSONObject jSONObject2 = (JSONObject) map.get(TTDownloadField.TT_DOWNLOAD_SETTINGS);
        String str11 = (String) map.get(TTDownloadField.TT_FILE_PATH);
        String str12 = (String) map.get(TTDownloadField.TT_DOWNLOAD_URL);
        String str13 = (String) map.get("appName");
        String str14 = (String) map.get("packageName");
        boolean zBooleanValue9 = ((Boolean) map.get(TTDownloadField.TT_IS_NEED_INDEPENDENT_PROCESS)).booleanValue();
        String str15 = (String) map.get(TTDownloadField.TT_OPEN_URL);
        String str16 = (String) map.get(TTDownloadField.TT_WEB_TITLE);
        String str17 = (String) map.get(TTDownloadField.TT_WEB_URL);
        AdDownloadModel.Builder fileUriProvider = new AdDownloadModel.Builder().setExpectFileLength(jLongValue).setMd5(str).setId(jLongValue3).setExtraValue(jLongValue2).setIsAd(zBooleanValue).setModelType(iIntValue).setLogExtra(str10).setAppIcon(str9).setBackupUrls(list2).setNotificationJumpUrl(str2).setClickTrackUrl(list).setMimeType(str3).setHeaders(map2).setIsShowToast(zBooleanValue2).setIsShowNotification(zBooleanValue7).setNeedWifi(zBooleanValue3).setFileName(str4).setVersionCode(iIntValue2).setVersionName(str5).setQuickAppModel(dVarA).setAutoInstallWithoutNotification(zBooleanValue8).setExecutorGroup(iIntValue3).setStartToast(str7).setSdkMonitorScene(str8).setAutoInstall(zBooleanValue4).setDistinctDir(zBooleanValue5).setEnablePause(zBooleanValue6).setExtra(jSONObject).setFileUriProvider(new IDownloadFileUriProvider() { // from class: com.bytedance.sdk.openadsdk.downloadnew.d.2
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider
            public Uri getUriForFile(String str18, String str19) {
                return null;
            }
        });
        if (jSONObject2 != null) {
            fileUriProvider.setDownloadSettings(jSONObject2);
        }
        if (!TextUtils.isEmpty(str11)) {
            fileUriProvider.setFilePath(str11);
        }
        if (!TextUtils.isEmpty(str12)) {
            fileUriProvider.setDownloadUrl(str12);
        }
        if (!TextUtils.isEmpty(str13)) {
            fileUriProvider.setAppName(str13);
        }
        if (!TextUtils.isEmpty(str14)) {
            fileUriProvider.setPackageName(str14);
        }
        fileUriProvider.setNeedIndependentProcess(zBooleanValue9);
        fileUriProvider.setDeepLink(a(jLongValue3, str15, str16, str17));
        this.d = this.c.build();
    }

    public long F() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 0L;
        }
        return adDownloadModel.getId();
    }

    public String G() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getMd5();
    }

    public long H() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 0L;
        }
        return adDownloadModel.getExpectFileLength();
    }

    public long I() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 0L;
        }
        return adDownloadModel.getExtraValue();
    }

    public String J() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getDownloadUrl();
    }

    public List<String> K() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getBackupUrls();
    }

    public String L() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getNotificationJumpUrl();
    }

    public String M() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getName();
    }

    public String N() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getMimeType();
    }

    public Map<String, String> O() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getHeaders();
    }

    public boolean P() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return true;
        }
        return adDownloadModel.isShowToast();
    }

    public boolean Q() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return true;
        }
        return adDownloadModel.isShowNotification();
    }

    public boolean R() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return false;
        }
        return adDownloadModel.isNeedWifi();
    }

    public boolean S() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return false;
        }
        return adDownloadModel.isInExternalPublicDir();
    }

    public boolean T() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return false;
        }
        return adDownloadModel.isInExternalPublicDir();
    }

    public String U() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getFilePath();
    }

    public String V() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getFileName();
    }

    public void W() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.forceWifi();
    }

    public JSONObject X() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getDownloadSettings();
    }

    public void Y() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.forceHideToast();
    }

    public void Z() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.forceHideNotification();
    }

    public boolean aa() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return false;
        }
        return adDownloadModel.needIndependentProcess();
    }

    public int ab() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 0;
        }
        return adDownloadModel.getVersionCode();
    }

    public String ac() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getVersionName();
    }

    public boolean ad() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return true;
        }
        return adDownloadModel.isAd();
    }

    public String ae() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getLogExtra();
    }

    public String af() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getPackageName();
    }

    public String ag() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getAppIcon();
    }

    public DeepLink ah() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getDeepLink();
    }

    public List<String> ai() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getClickTrackUrl();
    }

    public JSONObject aj() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getExtra();
    }

    public int ak() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 0;
        }
        return adDownloadModel.getModelType();
    }

    public com.ss.android.download.api.model.d al() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.getQuickAppModel();
    }

    public boolean am() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return false;
        }
        return adDownloadModel.autoInstallWithoutNotification();
    }

    public boolean an() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return com.ss.android.download.api.c.b.a(DownloadSetting.obtain(X()), N());
        }
        return adDownloadModel.shouldDownloadWithPatchApply();
    }

    public int ao() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 2;
        }
        return adDownloadModel.getExecutorGroup();
    }

    public int ap() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return 1;
        }
        return adDownloadModel.getFunnelType();
    }

    public String aq() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getStartToast();
    }

    public String ar() {
        AdDownloadModel adDownloadModel = this.d;
        return adDownloadModel == null ? "" : adDownloadModel.getStartToast();
    }

    public boolean as() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return true;
        }
        return adDownloadModel.isAutoInstall();
    }

    public boolean at() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return false;
        }
        return adDownloadModel.distinctDir();
    }

    public boolean au() {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return true;
        }
        return adDownloadModel.enablePause();
    }

    public void e(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setMd5(str);
    }

    public void a(long j) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setExpectFileLength(j);
    }

    public void d(boolean z) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setNeedWifi(z);
    }

    public void e(boolean z) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setIsShowToast(z);
    }

    public void b(long j) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setExtraValue(j);
    }

    public void f(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setAppName(str);
    }

    public void d(JSONObject jSONObject) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setExtra(jSONObject);
    }

    public void g(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setStartToast(str);
    }

    public void h(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return;
        }
        adDownloadModel.setSdkMonitorScene(str);
    }

    public AdDownloadModel c(long j) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setId(j);
    }

    public AdDownloadModel f(boolean z) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setIsAd(z);
    }

    public AdDownloadModel c(int i) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setModelType(i);
    }

    public AdDownloadModel i(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setLogExtra(str);
    }

    public AdDownloadModel j(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setPackageName(str);
    }

    public AdDownloadModel k(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setAppIcon(str);
    }

    public AdDownloadModel a(List<String> list) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setClickTrackUrl(list);
    }

    public AdDownloadModel l(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setDownloadUrl(str);
    }

    public AdDownloadModel b(List<String> list) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setBackupUrls(list);
    }

    public AdDownloadModel m(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setNotificationJumpUrl(str);
    }

    public AdDownloadModel n(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setMimeType(str);
    }

    public AdDownloadModel a(Map<String, String> map) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setHeaders(map);
    }

    public AdDownloadModel g(boolean z) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setIsShowNotification(z);
    }

    public AdDownloadModel o(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setFilePath(str);
    }

    public AdDownloadModel p(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setFileName(str);
    }

    public AdDownloadModel h(boolean z) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setNeedIndependentProcess(z);
    }

    public AdDownloadModel d(int i) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setVersionCode(i);
    }

    public AdDownloadModel q(String str) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setVersionName(str);
    }

    public AdDownloadModel a(com.ss.android.download.api.model.d dVar) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setQuickAppModel(dVar);
    }

    public AdDownloadModel i(boolean z) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setAutoInstallWithoutNotification(z);
    }

    public AdDownloadModel e(int i) {
        AdDownloadModel adDownloadModel = this.d;
        if (adDownloadModel == null) {
            return null;
        }
        return adDownloadModel.setFunnelType(i);
    }
}
