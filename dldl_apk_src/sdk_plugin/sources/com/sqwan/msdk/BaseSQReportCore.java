package com.sqwan.msdk;

import android.content.Context;
import com.huya.mtp.hyns.report.NSPushReporter;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.PurchaseReportBean;
import com.sqwan.msdk.api.RegisterReportBean;
import com.sqwan.msdk.api.SQDefaultMediaReport;
import com.sqwan.msdk.api.SQDefaultReporter;
import com.sqwan.msdk.api.SQMediaReportInterface;
import com.sqwan.msdk.api.SQReportInterface;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BaseSQReportCore implements SQReportInterface {
    private MDevObservable mDevObservable;
    private String mMDev;
    private SQMediaReportInterface mMediaReport;
    private SQReportInterface mReporter;

    public void init(Context context) {
        LogUtil.i("BaseSQReportCore init");
        if (this.mReporter == null) {
            LogUtil.i("mReporter is null 设置默认的");
            this.mReporter = new SQDefaultReporter();
        }
        if (this.mMediaReport == null) {
            LogUtil.i("mMediaReport is null  设置默认的");
            this.mMediaReport = new SQDefaultMediaReport();
        } else {
            LogUtil.i("mMediaReport is not null " + this.mMediaReport.toString());
        }
        this.mReporter.init(context);
    }

    public void afterPermission(Context context) {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_after_permission);
        this.mReporter.afterPermission(context);
    }

    public void eventRegister(RegisterReportBean registerReportBean) {
        HashMap map = new HashMap();
        if (registerReportBean != null) {
            map.put("type", registerReportBean.getType());
            map.put("success", registerReportBean.isSuccess() + "");
        }
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_register, map);
        this.mReporter.eventRegister(registerReportBean);
    }

    public void eventPurchase(PurchaseReportBean purchaseReportBean) {
        HashMap map = new HashMap();
        if (purchaseReportBean != null) {
            map.put("productType", purchaseReportBean.getProductType());
            map.put("channel", purchaseReportBean.getChannel());
            map.put(SqConstants.CURRENCY, purchaseReportBean.getCurrency());
            map.put(SqTrackKey.order_id, purchaseReportBean.getOrderId());
            map.put(SqConstants.PRODUCT_ID, purchaseReportBean.getProductId());
            map.put("product_name", purchaseReportBean.getProductName());
            map.put(NSPushReporter.NS_PUSH_COUNT, purchaseReportBean.getCount() + "");
            map.put("price", purchaseReportBean.getPrice() + "");
            map.put("success", purchaseReportBean.isSuccess() + "");
            map.put("product_type", purchaseReportBean.getProductType());
        }
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_purchase, map);
        this.mReporter.eventPurchase(purchaseReportBean);
    }

    public void eventCpPay(String str) {
        HashMap map = new HashMap();
        map.put("cp_data", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_cp_pay_event, map);
        this.mReporter.eventCpPay(str);
    }

    public void report(String str, Map<String, String> map) {
        HashMap map2 = new HashMap();
        map2.put("report_event", str);
        map2.putAll(map);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_event, map2);
        this.mReporter.report(str, map);
    }

    public void setReporter(SQReportInterface sQReportInterface) {
        this.mReporter = sQReportInterface;
    }

    public void setMediaReporter(SQMediaReportInterface sQMediaReportInterface) {
        LogUtil.i(toString() + " BaseSQReportCore setMediaReporter " + sQMediaReportInterface.toString());
        this.mMediaReport = sQMediaReportInterface;
    }

    public void setDevObservable(MDevObservable mDevObservable) {
        this.mDevObservable = mDevObservable;
    }

    public MDevObservable getDevObservable() {
        if (this.mDevObservable == null) {
            this.mDevObservable = new MDevObservable();
        }
        return this.mDevObservable;
    }

    public void setMDev(String str) {
        this.mMDev = str;
    }

    public String getMDev() {
        return this.mMDev;
    }

    public String getRefer() {
        LogUtil.i(toString() + " BaseSQReportCore getRefer " + this.mMediaReport.toString());
        return this.mMediaReport.mediaRefer();
    }
}
