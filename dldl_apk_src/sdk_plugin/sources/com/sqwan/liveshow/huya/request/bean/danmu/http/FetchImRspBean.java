package com.sqwan.liveshow.huya.request.bean.danmu.http;

import android.text.TextUtils;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImReqBean;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FetchImRspBean {
    public List<ImMsg> items;

    public static class ImMsg {
        public SendImReqBean.ImMsg msg;
        public String rid;
        public long time;
        public long uid;
        public String uname;

        public boolean isSelf() {
            return TextUtils.equals(this.uid + "", LiveshowManager.getInstance().getUserId());
        }
    }
}
