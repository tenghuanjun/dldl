package com.sq.websocket_engine;

import com.google.sqgson.ExclusionStrategy;
import com.google.sqgson.FieldAttributes;
import com.google.sqgson.Gson;
import com.google.sqgson.GsonBuilder;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.MD5Util;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BodyData {
    private static final String TAG = "BodyData";
    public String appid;
    public String ev;
    public String gid;
    public int hb_time;
    public String msg;
    public MsgBaseReq msgBaseReq;
    public int opres;
    public String pid;
    public String session_id;
    public String sign;
    public long ts;
    public String userid;

    public BodyData getReqBodyData(MsgBaseReq msgBaseReq) {
        this.msgBaseReq = msgBaseReq;
        this.ev = msgBaseReq.getEv();
        this.msg = getGson().toJson(msgBaseReq);
        this.appid = msgBaseReq.getAppid();
        sign();
        return this;
    }

    private void sign() {
        this.pid = CommonConfigs.getInstance().getSqAppConfig().getPartner();
        this.gid = CommonConfigs.getInstance().getSqAppConfig().getGameid();
        this.ts = ModHelper.getConfig().getCommonConfig().getCurrentTime();
        this.userid = CommonConfigs.getInstance().getUserId();
        this.session_id = CommonConfigs.getInstance().getSession_id();
        String str = this.appid + this.ev + this.pid + this.gid + this.ts + this.userid + this.session_id + this.msg + CommonConfigs.getInstance().getAppKey();
        String lowerCase = MD5Util.Md5(str).toLowerCase();
        LogUtil.i(TAG, "key:" + str);
        LogUtil.i(TAG, "sign:" + lowerCase);
        this.sign = lowerCase;
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() { // from class: com.sq.websocket_engine.BodyData.1
            @Override // com.google.sqgson.ExclusionStrategy
            public boolean shouldSkipClass(Class<?> cls) {
                return false;
            }

            @Override // com.google.sqgson.ExclusionStrategy
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.getName().contains("opres") | fieldAttributes.getName().contains("msgBase");
            }
        });
        gsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy() { // from class: com.sq.websocket_engine.BodyData.2
            @Override // com.google.sqgson.ExclusionStrategy
            public boolean shouldSkipClass(Class<?> cls) {
                return false;
            }

            @Override // com.google.sqgson.ExclusionStrategy
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.getName().contains("msgBase");
            }
        });
        return gsonBuilder.create();
    }

    public String toJson() {
        return getGson().toJson(this);
    }

    public BodyData fromJson(String str) {
        return (BodyData) getGson().fromJson(str, (Class) getClass());
    }

    public boolean isSuccess() {
        return this.opres == 1;
    }

    public String toString() {
        return "BodyData{msg='" + this.msg + "', appid='" + this.appid + "', ev='" + this.ev + "', ts=" + this.ts + ", sign='" + this.sign + "', userid='" + this.userid + "', session_id='" + this.session_id + "', pid='" + this.pid + "', gid='" + this.gid + "', opres=" + this.opres + ", hb_time=" + this.hb_time + ", msgBaseReq=" + this.msgBaseReq + AbstractJsonLexerKt.END_OBJ;
    }
}
