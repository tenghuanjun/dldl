package com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory;

import com.sqwan.base.L;
import com.sqwan.common.util.DensityUtil;
import com.sqwan.common.util.SpanUtil;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import com.sqwan.liveshow.huya.request.bean.danmu.http.SendImReqBean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class AImMsg extends SendImReqBean.ImMsg {
    private CharSequence content;
    private FetchImRspBean.ImMsg imMsg;
    private CharSequence user;

    protected CharSequence converContent(String str) {
        return str;
    }

    protected CharSequence convertUser(String str) {
        return str;
    }

    protected abstract int getContentColor();

    protected abstract int getContentSize();

    protected abstract int getUserColor();

    protected abstract int getUserSize();

    public void init(FetchImRspBean.ImMsg imMsg) {
        if (imMsg != null) {
            this.imMsg = imMsg;
            this.user = SpanUtil.getFontString(imMsg.msg.nickname + "： ", dip2px(getUserSize()), getUserColor());
            this.content = SpanUtil.getFontString(imMsg.msg.content, dip2px(getContentSize()), getContentColor());
        }
    }

    public CharSequence getUser() {
        return this.user;
    }

    public CharSequence getContent() {
        return this.content;
    }

    private int dip2px(int i) {
        return DensityUtil.dip2px(L.getApplicationContext(), i);
    }
}
