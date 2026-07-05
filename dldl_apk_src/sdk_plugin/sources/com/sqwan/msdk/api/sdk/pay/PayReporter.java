package com.sqwan.msdk.api.sdk.pay;

import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.order.base.PayContext;
import com.sqwan.order.base.PayInfoModel;
import com.sqwan.order.base.ReportDataBuilder;
import com.sqwan.order.base.SqPayError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayReporter {
    private PayReporter() {
    }

    public static void reportPayInvoke(PayContext payContext, PayInfoModel payInfoModel) {
        report(SqTrackAction2.pay_invoke, dataBuilder(payContext).payInfo(payInfoModel));
    }

    public static void reportPaySuccess(PayContext payContext) {
        report(SqTrackAction2.pay_succ, dataBuilder(payContext));
    }

    public static void reportPayFail(PayContext payContext, SqPayError sqPayError) {
        report(SqTrackAction2.pay_fail, dataBuilder(payContext).error(sqPayError));
    }

    public static void reportPayCancel(PayContext payContext) {
        report(SqTrackAction2.pay_cancel, dataBuilder(payContext).put("pay_cancel_way", payContext.getCancelWay()));
    }

    private static ReportDataBuilder dataBuilder(PayContext payContext) {
        return new ReportDataBuilder().payCtx(payContext);
    }

    private static void report(SqTrackAction2 sqTrackAction2, ReportDataBuilder reportDataBuilder) {
        SqTrackActionManager2.getInstance().trackAction(sqTrackAction2, reportDataBuilder.map);
    }
}
