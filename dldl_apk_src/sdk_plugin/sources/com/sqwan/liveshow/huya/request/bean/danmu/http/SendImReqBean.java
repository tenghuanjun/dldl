package com.sqwan.liveshow.huya.request.bean.danmu.http;

import com.google.sqgson.Gson;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.liveshow.huya.request.bean.danmu.LiveshowBaseRequest;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SendImReqBean extends LiveshowBaseRequest {
    public ImMsg msg;

    public SendImReqBean(String str, String str2) {
        ImMsg imMsg = new ImMsg();
        this.msg = imMsg;
        imMsg.nickname = str;
        this.msg.content = str2;
    }

    public static class ImMsg {
        public String content;
        public String nickname;
        public int type = 1;

        public String toString() {
            return "ImMsg{nickname='" + this.nickname + "', type=" + this.type + ", content='" + this.content + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    @Override // com.sqwan.liveshow.huya.request.bean.danmu.LiveshowBaseRequest
    public Map<String, String> toMap() {
        HashMap map = new HashMap();
        map.put("msg", new Gson().toJson(this.msg));
        map.put("actor_id", CommonConfigs.getInstance().getBaseUserInfo().roleId);
        return map;
    }
}
