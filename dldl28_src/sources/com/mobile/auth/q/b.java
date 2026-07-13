package com.mobile.auth.q;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.VendorSdkInfoManager;
import com.mobile.auth.gatewayauth.model.psc_info_upload.AllRBInfo;
import com.mobile.auth.gatewayauth.model.psc_info_upload.PnsVendorQueryResponse;
import com.mobile.auth.gatewayauth.model.psc_info_upload.Result;
import com.mobile.auth.gatewayauth.network.RequestState;
import com.mobile.auth.gatewayauth.network.RequestUtil;
import com.mobile.auth.gatewayauth.utils.EncryptUtils;

/* JADX INFO: loaded from: classes3.dex */
public class b extends a<com.mobile.auth.v.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.mobile.auth.p.a f774a;
    private VendorSdkInfoManager b;

    public b(Context context, VendorSdkInfoManager vendorSdkInfoManager, com.mobile.auth.gatewayauth.manager.b bVar, com.mobile.auth.p.a aVar) {
        super(context, bVar);
        this.f774a = aVar;
        this.b = vendorSdkInfoManager;
    }

    @Override // com.mobile.auth.q.a
    public /* synthetic */ com.mobile.auth.v.a a() {
        try {
            return d();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    @Override // com.mobile.auth.q.a
    public /* synthetic */ com.mobile.auth.v.a a(String str) {
        try {
            return b(str);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public com.mobile.auth.v.b b(String str) {
        String vendorListByPop;
        vendorListByPop = "";
        try {
            try {
                vendorListByPop = RequestState.getInstance().checkTokenValied(1) ? RequestUtil.getVendorListByPop(RequestState.getInstance().getKeyRespone().getSk(), EncryptUtils.encryptToken(c(), null, null, null, this.b.c(), str, null, null, null, false, null)) : "";
                this.f774a.a("PopRequest-GetVendorList:" + vendorListByPop);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        } catch (Exception unused) {
        }
        return new com.mobile.auth.v.b(false, vendorListByPop, str);
    }

    public com.mobile.auth.v.b d() {
        try {
            AllRBInfo allRBInfo = new AllRBInfo();
            PnsVendorQueryResponse pnsVendorQueryResponse = new PnsVendorQueryResponse();
            Result result = new Result();
            result.setCode(ResultCode.CODE_ERROR_FUNCTION_LIMIT);
            result.setMessage("GetVendorList Limited");
            pnsVendorQueryResponse.setResult(result);
            allRBInfo.setResponse(pnsVendorQueryResponse);
            return new com.mobile.auth.v.b(false, allRBInfo.toJson().toString(), "");
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public com.mobile.auth.v.a e() {
        try {
            return new com.mobile.auth.v.b(true, "{}", "");
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    @Override // com.nirvana.tools.requestqueue.TimeoutCallable
    public /* synthetic */ com.mobile.auth.v.a onTimeout() {
        try {
            return e();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
