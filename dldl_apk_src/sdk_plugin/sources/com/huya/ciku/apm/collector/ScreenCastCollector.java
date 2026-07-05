package com.huya.ciku.apm.collector;

import android.text.TextUtils;
import com.duowan.monitor.core.OnStatusChangeListener;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.EUnit;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.constant.BeginLiveConstant;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ScreenCastCollector implements OnStatusChangeListener {
    private static final String METRIC_NAME = "anchor.screencast";
    protected boolean mEnabled;
    protected boolean mStarted;
    private boolean mStopInBackground = false;

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
        this.mStarted = true;
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
        if (this.mStopInBackground || !MonitorCenter.getInstance().isBackground()) {
            this.mStarted = false;
        }
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        this.mEnabled = jSONObject != null ? jSONObject.optBoolean("enabled") : false;
    }

    public void report(int i, int i2, String str, long j) {
        BeginLiveConstant beginLiveConstant;
        if (this.mEnabled && (beginLiveConstant = BeginLiveConstant.get(i)) != null) {
            Map<Integer, String> mapData = beginLiveConstant.data();
            String strMsg = mapData != null ? mapData.get(Integer.valueOf(i2)) : null;
            if (TextUtils.isEmpty(strMsg)) {
                strMsg = beginLiveConstant.msg();
            }
            if (TextUtils.isEmpty(strMsg)) {
                strMsg = "";
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Dimension("uid", j + ""));
            arrayList.add(new Dimension("rect_code", i + ""));
            arrayList.add(new Dimension("second_code", i2 + ""));
            arrayList.add(new Dimension("success", String.valueOf(beginLiveConstant.success() ? 1 : 0)));
            if (TextUtils.isEmpty(str)) {
                str = strMsg;
            }
            arrayList.add(new Dimension("msg", str));
            MonitorCenter.getInstance().requestWithoutUserId(METRIC_NAME, 0.0d, EUnit.EUnit_Count, arrayList);
        }
    }
}
