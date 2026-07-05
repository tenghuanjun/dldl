package com.aliyun.aliyunface.network;

import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.zoloz.toyger.blob.BlobManager;
import com.alipay.zoloz.toyger.face.FaceBlobManager;
import com.alipay.zoloz.toyger.face.ToygerFaceAttr;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.aliyun.aliyunface.api.ZIMResponseCode;
import com.aliyun.aliyunface.config.OSSConfig;
import com.aliyun.aliyunface.config.ProtocolContent;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.model.BisBehavCommon;
import com.aliyun.aliyunface.network.model.BisBehavLog;
import com.aliyun.aliyunface.network.model.BisBehavToken;
import com.aliyun.aliyunface.network.model.BisClientInfo;
import com.aliyun.aliyunface.network.model.Blob;
import com.aliyun.aliyunface.network.model.BlobElem;
import com.aliyun.aliyunface.network.model.FaceInfo;
import com.aliyun.aliyunface.network.model.Meta;
import com.aliyun.aliyunface.network.model.OCRInfo;
import com.aliyun.aliyunface.network.model.RuntimeInfo;
import com.aliyun.aliyunface.network.model.Score;
import com.aliyun.aliyunface.network.model.ValidateContent;
import com.aliyun.aliyunface.network.model.ValidateData;
import com.aliyun.aliyunface.network.model.ZimInitRes;
import com.aliyun.aliyunface.network.model.ZimOcrIdentifyRes;
import com.aliyun.aliyunface.network.model.ZimValidateRes;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.ss.android.socialbase.downloader.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class NetworkPresenter {
    public static void zimInit(NetworkEnv networkEnv, String str, String str2, final ZimInitCallback zimInitCallback) {
        String str3;
        HashMap map = new HashMap();
        map.put("DeviceToken", ToygerPresenter.getInstance().getAliyunDeviceToken());
        map.put("CertifyId", str);
        map.put("MetaInfo", str2);
        PopNetHelper.HOST = networkEnv.safHost;
        PopNetHelper.appKey = networkEnv.appKey;
        PopNetHelper.appSecret = networkEnv.appSecret;
        if (PopNetHelper.isNormalCertifyId(str)) {
            str3 = "InitDevice";
        } else {
            map.put("AppVersion", "2021-07-01");
            str3 = "InitDeviceSecurity";
        }
        String str4 = str3;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        PopNetHelper.sendAsynRequest(networkEnv, str4, "2019-03-07", map, null, new PopNetCallback() { // from class: com.aliyun.aliyunface.network.NetworkPresenter.1
            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onSuccess(int i, String str5, Object obj) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "initDeviceCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                RecordService.getInstance().recordEvent(RecordLevel.LOG_DEBUG, "InitDeviceResOK", "status", "success", "content", str5);
                NetworkStore.getInstance().save("2. 初始化接口", str5);
                ZimInitRes zimInitRes = (ZimInitRes) MiscUtil.json2Object(str5, ZimInitRes.class);
                if (zimInitRes != null && 2003 == zimInitRes.Code) {
                    ZimInitCallback zimInitCallback2 = zimInitCallback;
                    if (zimInitCallback2 != null) {
                        zimInitCallback2.onServerError(String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID), str5);
                        return;
                    }
                    return;
                }
                if (zimInitRes != null && zimInitRes.isValid() && ToygerConst.ZcodeConstants.ZCODE_NET_INIT_SUCCESS.equalsIgnoreCase(zimInitRes.getRetCodeSub())) {
                    if (zimInitCallback != null) {
                        OSSConfig oSSConfig = new OSSConfig();
                        oSSConfig.AccessKeyId = zimInitRes.getAccessKeyId();
                        oSSConfig.AccessKeySecret = zimInitRes.getAccessKeySecret();
                        oSSConfig.SecurityToken = zimInitRes.getSecurityToken();
                        oSSConfig.BucketName = zimInitRes.getBucketName();
                        oSSConfig.FileNamePrefix = zimInitRes.getFileName();
                        oSSConfig.OssEndPoint = zimInitRes.getOssEndPoint();
                        zimInitCallback.onSuccess(zimInitRes.getProtocol(), oSSConfig);
                        return;
                    }
                    return;
                }
                String retCodeSub = (zimInitRes == null || !zimInitRes.isValid()) ? "NET_RESPONSE_INVALID" : zimInitRes.getRetCodeSub();
                ZimInitCallback zimInitCallback3 = zimInitCallback;
                if (zimInitCallback3 != null) {
                    zimInitCallback3.onError(retCodeSub, str5);
                }
            }

            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onError(int i, String str5, Object obj) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "InitDeviceResFail", "status", "error", "errMsg", str5);
                ZimInitCallback zimInitCallback2 = zimInitCallback;
                if (zimInitCallback2 != null) {
                    zimInitCallback2.onError(ToygerConst.ZcodeConstants.ZCODE_NETWORK_ERROR, str5);
                }
            }
        });
    }

    private static String buildValidateParams(String str, byte[] bArr, ToygerFaceAttr toygerFaceAttr, String str2, String str3) {
        FaceInfo faceInfo = new FaceInfo();
        if (toygerFaceAttr != null) {
            Bitmap bitmapBytes2Bitmap = MiscUtil.bytes2Bitmap(bArr);
            if (bitmapBytes2Bitmap != null) {
                faceInfo.rect = FaceBlobManager.convertFaceRegion(toygerFaceAttr.faceRegion, bitmapBytes2Bitmap.getWidth(), bitmapBytes2Bitmap.getHeight(), 0, false);
            }
            faceInfo.confidence = String.valueOf(toygerFaceAttr.confidence);
        }
        faceInfo.feaVersion = "1.0";
        BlobElem blobElem = new BlobElem();
        blobElem.subType = BlobManager.SUB_TYPE_PANO;
        blobElem.idx = 0;
        blobElem.version = "1.0";
        blobElem.type = BlobManager.BLOB_ELEM_TYPE_FACE;
        blobElem.faceInfos = new FaceInfo[1];
        blobElem.faceInfos[0] = faceInfo;
        blobElem.content = str;
        Blob blob = new Blob();
        blob.blobVersion = "";
        blob.blobElem = new BlobElem[1];
        blob.blobElem[0] = blobElem;
        Meta meta = new Meta();
        meta.serialize = 1;
        meta.type = BlobManager.META_TYPE_FACE;
        meta.score = new Score();
        if (toygerFaceAttr != null) {
            meta.score.quality = toygerFaceAttr.quality();
        }
        meta.collectInfo = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            meta.collectInfo.put("photinusMetadataFileId", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            meta.collectInfo.put("photinusVideoFileId", str3);
        }
        try {
            String highQualityFaceImageMD5 = ToygerPresenter.getInstance().getHighQualityFaceImageMD5();
            RuntimeInfo runtimeInfo = new RuntimeInfo();
            runtimeInfo.hash = highQualityFaceImageMD5;
            runtimeInfo.algVerison = "";
            meta.collectInfo.put(BlobManager.BLOB_ELEM_IMAGE_RUNTIMEINFO, runtimeInfo);
        } catch (Exception unused) {
        }
        ValidateData validateData = new ValidateData();
        validateData.blob = blob;
        validateData.meta = meta;
        ValidateContent validateContent = new ValidateContent();
        validateContent.content = JSON.toJSONString(validateData);
        BisBehavLog bisBehavLog = new BisBehavLog();
        BisClientInfo bisClientInfo = new BisClientInfo();
        bisClientInfo.setClientVer(ToygerConst.TOYGER_SDK_VERSION);
        bisClientInfo.setModel(Build.MODEL);
        bisClientInfo.setOs("android");
        bisClientInfo.setOsVer(Build.VERSION.RELEASE);
        ProtocolContent clientProtocolContent = ToygerPresenter.getInstance().getClientProtocolContent();
        BisBehavToken bisBehavToken = new BisBehavToken();
        if (clientProtocolContent != null) {
            bisBehavToken.setToken(clientProtocolContent.token);
            bisBehavToken.setSampleMode(clientProtocolContent.sampleMode);
            bisBehavToken.setType(clientProtocolContent.type);
        }
        BisBehavCommon bisBehavCommon = new BisBehavCommon();
        bisBehavCommon.setInvtp(BuildConfig.FLAVOR);
        bisBehavCommon.setTm("");
        bisBehavCommon.setRetry("0");
        ArrayList arrayList = new ArrayList();
        bisBehavLog.setBehavCommon(bisBehavCommon);
        bisBehavLog.setBehavTask(arrayList);
        bisBehavLog.setBehavToken(bisBehavToken);
        bisBehavLog.setClientInfo(bisClientInfo);
        validateContent.behavLog = MiscUtil.base64Encode(JSON.toJSONString(bisBehavLog));
        validateContent.contentSig = "";
        validateContent.behavLogSig = "";
        validateContent.bisToken = "";
        if (clientProtocolContent != null) {
            validateContent.bisToken = clientProtocolContent.token;
        }
        return JSON.toJSONString(validateContent);
    }

    public static void zimValidate(NetworkEnv networkEnv, String str, String str2, String str3, String str4, String str5, String str6, byte[] bArr, ToygerFaceAttr toygerFaceAttr, String str7, OCRInfo oCRInfo, final ZimValidateCallback zimValidateCallback) {
        String str8;
        HashMap map = new HashMap();
        map.put("CertifyId", str);
        String aliyunDeviceToken = ToygerPresenter.getInstance().getAliyunDeviceToken();
        if (!TextUtils.isEmpty(aliyunDeviceToken)) {
            map.put("DeviceToken", aliyunDeviceToken);
        }
        try {
            map.put("CertifyData", buildValidateParams(str4, bArr, toygerFaceAttr, str5, str6));
        } catch (Exception unused) {
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("videoFileMd5", (Object) str7);
        if (oCRInfo != null) {
            jSONObject.put("confirmCertName", (Object) oCRInfo.certName);
            jSONObject.put("confirmCertNo", (Object) oCRInfo.certNo);
        }
        if (str3 != null && !TextUtils.isEmpty(str3)) {
            jSONObject.put("videoFileName", (Object) str3);
        }
        if (str2 != null && !TextUtils.isEmpty(str2)) {
            jSONObject.put("metaInfo", (Object) str2);
        }
        map.put("ExtInfo", jSONObject.toJSONString());
        PopNetHelper.HOST = networkEnv.safHost;
        PopNetHelper.appKey = networkEnv.appKey;
        PopNetHelper.appSecret = networkEnv.appSecret;
        if (PopNetHelper.isNormalCertifyId(str)) {
            str8 = "VerifyDevice";
        } else {
            map.put("AppVersion", "2021-07-01");
            str8 = "VerifyDeviceSecurity";
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        PopNetHelper.sendAsynRequest(networkEnv, str8, "2019-03-07", map, null, new PopNetCallback() { // from class: com.aliyun.aliyunface.network.NetworkPresenter.2
            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onSuccess(int i, String str9, Object obj) {
                String retMessageSub;
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "verifyDeviceCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                RecordService.getInstance().recordEvent(RecordLevel.LOG_DEBUG, "VerifyDeviceResOK", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, "content", str9);
                NetworkStore.getInstance().save("3. 认证接口", str9);
                ZimValidateRes zimValidateRes = (ZimValidateRes) MiscUtil.json2Object(str9, ZimValidateRes.class);
                if (zimValidateRes != null && 2003 == zimValidateRes.Code) {
                    ZimValidateCallback zimValidateCallback2 = zimValidateCallback;
                    if (zimValidateCallback2 != null) {
                        zimValidateCallback2.onServerError(String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID), str9);
                        return;
                    }
                    return;
                }
                if (zimValidateRes != null && zimValidateRes.isValid() && ToygerConst.ZcodeConstants.ZCODE_NET_VERIFY_SUCCESS.equalsIgnoreCase(zimValidateRes.getRetCodeSub())) {
                    ZimValidateCallback zimValidateCallback3 = zimValidateCallback;
                    if (zimValidateCallback3 != null) {
                        zimValidateCallback3.onSuccess();
                        return;
                    }
                    return;
                }
                String retCodeSub = "NET_RESPONSE_INVALID";
                if (zimValidateRes == null || !zimValidateRes.isValid()) {
                    retMessageSub = "NET_RESPONSE_INVALID";
                } else {
                    retCodeSub = zimValidateRes.getRetCodeSub();
                    retMessageSub = zimValidateRes.getRetMessageSub();
                }
                ZimValidateCallback zimValidateCallback4 = zimValidateCallback;
                if (zimValidateCallback4 != null) {
                    zimValidateCallback4.onValidateFail(retCodeSub, retMessageSub, str9);
                }
            }

            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onError(int i, String str9, Object obj) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "VerifyDeviceResError", "status", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE, "errMsg", str9);
                ZimValidateCallback zimValidateCallback2 = zimValidateCallback;
                if (zimValidateCallback2 != null) {
                    zimValidateCallback2.onError(i + "", str9);
                }
            }
        });
    }

    public static void zimUploadLog(NetworkEnv networkEnv, final List<String> list, final ZimUploadLogCallback zimUploadLogCallback) {
        HashMap map = new HashMap();
        map.put("BizType", "ALIYUN_CLOUD_AUTH");
        map.put("AppVersion", "2020-11-12");
        map.put("UploadInfos", MiscUtil.object2Json(list));
        PopNetHelper.HOST = networkEnv.safHost;
        PopNetHelper.appKey = networkEnv.appKey;
        PopNetHelper.appSecret = networkEnv.appSecret;
        PopNetHelper.sendAsynRequest(networkEnv, "UploadDeviceInfos", "2020-11-12", map, null, new PopNetCallback() { // from class: com.aliyun.aliyunface.network.NetworkPresenter.3
            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onSuccess(int i, String str, Object obj) {
                ZimUploadLogCallback zimUploadLogCallback2 = zimUploadLogCallback;
                if (zimUploadLogCallback2 != null) {
                    zimUploadLogCallback2.onSuccess();
                }
            }

            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onError(int i, String str, Object obj) {
                ZimUploadLogCallback zimUploadLogCallback2 = zimUploadLogCallback;
                if (zimUploadLogCallback2 != null) {
                    zimUploadLogCallback2.onFail(list);
                }
            }
        });
    }

    public static void zimOCRIdentify(NetworkEnv networkEnv, String str, String str2, boolean z, final ZimOcrIdentifyCallback zimOcrIdentifyCallback) {
        String str3;
        HashMap map = new HashMap();
        map.put("AppVersion", "2020-02-07");
        map.put("CertifyId", str);
        map.put("FileName", str2);
        if (z) {
            map.put("Side", "OCR_ID_FACE");
        } else {
            map.put("Side", "OCR_ID_NATIONAL_EMBLEM");
        }
        PopNetHelper.HOST = networkEnv.safHost;
        PopNetHelper.appKey = networkEnv.appKey;
        PopNetHelper.appSecret = networkEnv.appSecret;
        if (PopNetHelper.isNormalCertifyId(str)) {
            str3 = "OcrDevice";
        } else {
            map.put("AppVersion", "2021-07-01");
            str3 = "OcrDeviceSecurity";
        }
        String str4 = str3;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        PopNetHelper.sendAsynRequest(networkEnv, str4, "2019-03-07", map, null, new PopNetCallback() { // from class: com.aliyun.aliyunface.network.NetworkPresenter.4
            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onSuccess(int i, String str5, Object obj) {
                RecordService.getInstance().recordEvent(RecordLevel.LOG_INFO, "ocrDeviceCost", "cost", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                ZimOcrIdentifyRes zimOcrIdentifyRes = (ZimOcrIdentifyRes) MiscUtil.json2Object(str5, ZimOcrIdentifyRes.class);
                if (zimOcrIdentifyRes != null && 2003 == zimOcrIdentifyRes.Code) {
                    ZimOcrIdentifyCallback zimOcrIdentifyCallback2 = zimOcrIdentifyCallback;
                    if (zimOcrIdentifyCallback2 != null) {
                        zimOcrIdentifyCallback2.onServerError(String.valueOf(ZIMResponseCode.ZIM_RESPONSE_CLIENT_TIME_INVALID), str5);
                        return;
                    }
                    return;
                }
                if (zimOcrIdentifyRes != null && zimOcrIdentifyRes.isValid()) {
                    ZimOcrIdentifyCallback zimOcrIdentifyCallback3 = zimOcrIdentifyCallback;
                    if (zimOcrIdentifyCallback3 != null) {
                        zimOcrIdentifyCallback3.onSuccess(zimOcrIdentifyRes.ResultObject.ocrInfo);
                        return;
                    }
                    return;
                }
                String str6 = (zimOcrIdentifyRes == null || zimOcrIdentifyRes.ResultObject == null) ? "NET_RESPONSE_INVALID" : zimOcrIdentifyRes.ResultObject.retCodeSub;
                ZimOcrIdentifyCallback zimOcrIdentifyCallback4 = zimOcrIdentifyCallback;
                if (zimOcrIdentifyCallback4 != null) {
                    zimOcrIdentifyCallback4.onServerError(str6, str5);
                }
            }

            @Override // com.aliyun.aliyunface.network.PopNetCallback
            public void onError(int i, String str5, Object obj) {
                ZimOcrIdentifyCallback zimOcrIdentifyCallback2 = zimOcrIdentifyCallback;
                if (zimOcrIdentifyCallback2 != null) {
                    zimOcrIdentifyCallback2.onError(String.valueOf(i), str5);
                }
            }
        });
    }
}
