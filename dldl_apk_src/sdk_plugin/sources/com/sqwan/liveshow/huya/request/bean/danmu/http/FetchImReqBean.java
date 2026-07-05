package com.sqwan.liveshow.huya.request.bean.danmu.http;

import com.sqwan.liveshow.huya.request.bean.danmu.LiveshowBaseRequest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FetchImReqBean extends LiveshowBaseRequest {
    public String etime;
    public String stime;

    public FetchImReqBean() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        this.stime = (jCurrentTimeMillis - 10) + "";
        this.etime = jCurrentTimeMillis + "";
    }
}
