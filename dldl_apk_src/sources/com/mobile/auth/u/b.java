package com.mobile.auth.u;

import android.util.SparseArray;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.model.VendorConfig;
import com.mobile.auth.gatewayauth.model.psc_info_upload.AllRBInfo;
import com.mobile.auth.gatewayauth.model.psc_info_upload.Module;
import com.mobile.auth.gatewayauth.model.psc_info_upload.ModuleList;
import com.mobile.auth.gatewayauth.model.psc_info_upload.PnsVendorQueryResponse;
import com.mobile.auth.gatewayauth.model.psc_info_upload.Result;
import com.mobile.auth.gatewayauth.utils.AESUtils;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b extends a {
    private SparseArray<VendorConfig> a;

    public b(boolean z, String str, String str2) {
        super(z, str, str2);
        this.a = new SparseArray<>(3);
        f();
    }

    private synchronized void a(AllRBInfo allRBInfo, String str) {
        try {
            PnsVendorQueryResponse response = allRBInfo.getResponse();
            Result result = response != null ? response.getResult() : null;
            ModuleList module_list = result != null ? result.getModule_list() : null;
            for (Module module : module_list != null ? module_list.getModule() : null) {
                VendorConfig vendorConfig = new VendorConfig();
                vendorConfig.setRequestId(response.getRequest_id());
                vendorConfig.setVendorAccessId(AESUtils.decrypt(module.getVendor_access_id(), str));
                vendorConfig.setVendorAccessSecret(AESUtils.decrypt(module.getVendor_access_secret(), str));
                vendorConfig.setVendorKey(AESUtils.decrypt(module.getVendor_key(), str));
                int iA = com.mobile.auth.gatewayauth.utils.c.a(vendorConfig.getVendorKey());
                if (iA != 4) {
                    this.a.put(iA, vendorConfig);
                }
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    private void f() {
        PnsVendorQueryResponse response;
        ModuleList module_list;
        List<Module> module;
        try {
            AllRBInfo allRBInfoFromJson = AllRBInfo.fromJson(c());
            if (allRBInfoFromJson != null && (response = allRBInfoFromJson.getResponse()) != null) {
                a(response.getRequest_id());
                Result result = response.getResult();
                if (result != null && (module_list = result.getModule_list()) != null && (module = module_list.getModule()) != null && module.size() > 0 && module.get(0) != null) {
                    a(true);
                    a(allRBInfoFromJson, d());
                    return;
                }
            }
            a(false);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public synchronized SparseArray<VendorConfig> e() {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
        return this.a;
    }

    public boolean isResultTimeout() {
        PnsVendorQueryResponse response;
        Result result;
        try {
            AllRBInfo allRBInfoFromJson = AllRBInfo.fromJson(c());
            if (allRBInfoFromJson == null || (response = allRBInfoFromJson.getResponse()) == null || (result = response.getResult()) == null) {
                return false;
            }
            return ResultCode.CODE_ERROR_FUNCTION_TIME_OUT.equals(result.getCode());
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return false;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return false;
            }
        }
    }
}
