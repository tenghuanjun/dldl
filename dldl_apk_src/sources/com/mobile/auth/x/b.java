package com.mobile.auth.x;

import android.content.Context;
import com.alipay.sdk.util.e;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.model.pns_vendor_query.UploadRB;
import com.mobile.auth.gatewayauth.network.RequestState;
import com.mobile.auth.gatewayauth.network.RequestUtil;
import com.mobile.auth.gatewayauth.sdktools.upload.pns.model.PnsUploadRequest;
import com.mobile.auth.gatewayauth.sdktools.upload.pns.model.UploadLogDTO;
import com.mobile.auth.gatewayauth.sdktools.upload.pns.model.UploadMonitorDTO;
import com.mobile.auth.gatewayauth.utils.i;
import com.nirvana.tools.core.ExecutorManager;
import com.nirvana.tools.jsoner.JSONUtils;
import com.nirvana.tools.jsoner.JsonType;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class b implements com.mobile.auth.gatewayauth.b {
    @Override // com.mobile.auth.gatewayauth.b
    public boolean a(String str) {
        try {
            if (!i.b() || !RequestState.getInstance().checkTokenValied(1)) {
                return true;
            }
            PnsUploadRequest pnsUploadRequest = new PnsUploadRequest();
            try {
                pnsUploadRequest.setUploadLogDTOList(JSONUtils.parseJsonArray2JsonerList(str, new JsonType<UploadLogDTO>() { // from class: com.mobile.auth.x.b.1
                }));
                JSONObject json = pnsUploadRequest.toJson();
                UploadRB uploadRBFromJson = UploadRB.fromJson(RequestUtil.uploadUserTrackInfoByPop(RequestState.getInstance().getKeyRespone().getSk(), json.toString()));
                com.mobile.auth.q.a aVarA = com.mobile.auth.q.a.a((Context) null);
                if (aVarA != null) {
                    aVarA.a(uploadRBFromJson);
                }
                if (uploadRBFromJson == null || uploadRBFromJson.getAlibaba_aliqin_psc_info_upload_response() == null || uploadRBFromJson.getAlibaba_aliqin_psc_info_upload_response().getResult() == null || !"OK".equals(uploadRBFromJson.getAlibaba_aliqin_psc_info_upload_response().getResult().getCode())) {
                    json.put("uploadResult2_0", e.a);
                    return false;
                }
                json.put("uploadResult2_0", "success");
                return true;
            } catch (Exception e) {
                i.a(e);
                return false;
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.b
    public boolean b(String str) {
        try {
            if (!i.b()) {
                ExecutorManager.getInstance().scheduleFutureDelay(new Runnable() { // from class: com.mobile.auth.x.b.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.mobile.auth.q.a.a((Context) null).d();
                        } catch (Throwable th) {
                            try {
                                ExceptionProcessor.processException(th);
                            } catch (Throwable th2) {
                                ExceptionProcessor.processException(th2);
                            }
                        }
                    }
                }, com.igexin.push.config.c.j);
                return true;
            }
            if (!RequestState.getInstance().checkTokenValied(1)) {
                ExecutorManager.getInstance().scheduleFutureDelay(new Runnable() { // from class: com.mobile.auth.x.b.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.mobile.auth.q.a.a((Context) null).d();
                        } catch (Throwable th) {
                            try {
                                ExceptionProcessor.processException(th);
                            } catch (Throwable th2) {
                                ExceptionProcessor.processException(th2);
                            }
                        }
                    }
                }, com.igexin.push.config.c.j);
                return true;
            }
            PnsUploadRequest pnsUploadRequest = new PnsUploadRequest();
            try {
                pnsUploadRequest.setUploadMonitorDTOList(JSONUtils.parseJsonArray2JsonerList(str, new JsonType<UploadMonitorDTO>() { // from class: com.mobile.auth.x.b.4
                }));
                JSONObject json = pnsUploadRequest.toJson();
                UploadRB uploadRBFromJson = UploadRB.fromJson(RequestUtil.uploadUserTrackInfoByPop(RequestState.getInstance().getKeyRespone().getSk(), json.toString()));
                com.mobile.auth.q.a aVarA = com.mobile.auth.q.a.a((Context) null);
                if (aVarA != null) {
                    aVarA.a(uploadRBFromJson);
                }
                ExecutorManager.getInstance().scheduleFutureDelay(new Runnable() { // from class: com.mobile.auth.x.b.5
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.mobile.auth.q.a.a((Context) null).d();
                        } catch (Throwable th) {
                            try {
                                ExceptionProcessor.processException(th);
                            } catch (Throwable th2) {
                                ExceptionProcessor.processException(th2);
                            }
                        }
                    }
                }, com.igexin.push.config.c.j);
                if (uploadRBFromJson == null || uploadRBFromJson.getAlibaba_aliqin_psc_info_upload_response() == null || uploadRBFromJson.getAlibaba_aliqin_psc_info_upload_response().getResult() == null || !"OK".equals(uploadRBFromJson.getAlibaba_aliqin_psc_info_upload_response().getResult().getCode())) {
                    json.put("uploadResult2_0", e.a);
                    return false;
                }
                json.put("uploadResult2_0", "success");
                return true;
            } catch (Exception e) {
                i.a(e);
                ExecutorManager.getInstance().scheduleFutureDelay(new Runnable() { // from class: com.mobile.auth.x.b.6
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            com.mobile.auth.q.a.a((Context) null).d();
                        } catch (Throwable th) {
                            try {
                                ExceptionProcessor.processException(th);
                            } catch (Throwable th2) {
                                ExceptionProcessor.processException(th2);
                            }
                        }
                    }
                }, com.igexin.push.config.c.j);
                return false;
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }
}
