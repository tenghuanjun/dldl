package com.alipay.deviceid.module.rpc.report.open;

import com.alipay.deviceid.module.rpc.report.open.model.ReportRequest;
import com.alipay.deviceid.module.rpc.report.open.model.ReportResult;
import com.alipay.deviceid.module.x.am;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface OpenReportService {
    @am(a = "alipay.security.device.data.report.open")
    ReportResult reportData(ReportRequest reportRequest);
}
