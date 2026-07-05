package com.sqwan.liveshow.im;

import android.content.Context;
import com.google.sqgson.Gson;
import com.google.sqgson.annotations.Expose;
import com.sqwan.base.L;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.mod.liveshow.BaseBean;
import com.sqwan.common.request.SignUtils;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.AESUtil;
import com.sqwan.common.util.Base64;
import com.sy37sdk.account.AccountCache;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ImMsgChatExtBean {

    @Expose(deserialize = false, serialize = false)
    private VerifyData _verifyData;
    private String content;
    public String username;
    public String verifyData;

    public ImMsgChatExtBean(String str, String str2) {
        this.username = "";
        this.verifyData = "";
        this.content = "";
        this.username = str;
        this.content = str2;
        this._verifyData = new VerifyData(str2);
        try {
            this.verifyData = Base64.encode(AESUtil.encrypt(new Gson().toJson(this._verifyData)));
            this._verifyData = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class VerifyData {
        public int actor_level;
        public String content;
        public String gid;
        public String pid;
        public String uid;
        public String idfa = "";
        public String idfv = "";
        public String oudid = "";
        public String imei = "";
        public String mac = "";
        public String dsid = "";
        public String actor_id = "";
        public String actor_name = "";
        public String sign = "";

        public VerifyData(String str) {
            this.content = "";
            this.content = str;
            fillData(L.getApplicationContext());
        }

        public void fillData(Context context) {
            try {
                String value = "";
                String value2 = ImeiLogic.getInstance(context).getFromCache() == null ? "" : ImeiLogic.getInstance(context).getFromCache().getValue();
                if (MacLogic.getInstance(context).getFromCache() != null) {
                    value = MacLogic.getInstance(context).getFromCache().getValue();
                }
                this.uid = AccountCache.getUserid(context);
                this.gid = SqTrackUtil.getGameID(context);
                this.pid = SqTrackUtil.getPaternerID(context);
                this.imei = value2;
                this.mac = value;
                BaseBean baseUserInfo = CommonConfigs.getInstance().getBaseUserInfo();
                if (baseUserInfo != null) {
                    this.actor_id = baseUserInfo.roleId;
                    this.actor_name = baseUserInfo.roleName;
                    this.actor_level = Integer.parseInt(baseUserInfo.roleLevel);
                    this.dsid = baseUserInfo.serverId;
                }
                HashMap map = new HashMap();
                map.put("uid", this.uid);
                map.put("gid", this.gid);
                map.put("pid", this.pid);
                map.put("imei", value2);
                map.put("mac", value);
                map.put("actor_id", this.actor_id);
                map.put("actor_name", this.actor_name);
                map.put("actor_level", Integer.valueOf(this.actor_level));
                map.put(SqConstants.DSID, this.dsid);
                map.put("content", this.content);
                map.put(SqConstants.IDFA, this.idfa);
                map.put(SqConstants.IDFV, this.idfv);
                map.put(SqConstants.OUDID, this.oudid);
                this.sign = SignUtils.sign(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return "ImMsgChatExtBean{username='" + this.username + "', _verifyData=" + this._verifyData + ", verifyData='" + this.verifyData + "', content='" + this.content + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
