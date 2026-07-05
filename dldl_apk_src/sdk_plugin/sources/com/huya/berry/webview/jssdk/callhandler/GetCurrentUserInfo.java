package com.huya.berry.webview.jssdk.callhandler;

import android.content.Context;
import com.duowan.auk.NoProguard;
import com.duowan.live.common.webview.jssdk.callhandler.base.HandlerBase;
import com.duowan.live.common.webview.jssdk.callhandler.base.WrapUtils;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.huya.component.user.UserProperties;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GetCurrentUserInfo extends HandlerBase {

    public static class UserInfo extends WrapUtils.Wrap implements NoProguard {
        public String avatarUrl;
        public long imid;
        public String name;
        public long pid;
        public String presenterNick;
        public int ret;
        public String sex;
        public long sid;
        public String sign;
        public String ticketType;
        public long tid;
        public String udbToken;
        public long uid;
    }

    public String getFuncName() {
        return "getCurrentUserInfo";
    }

    public Object call(Object obj, Context context) {
        UserInfo userInfo = new UserInfo();
        userInfo.err_code = 0;
        userInfo.status = SqR.string.ok;
        userInfo.ret = 0;
        if (LoginProperties.uid.get().longValue() != 0) {
            userInfo.uid = LoginProperties.uid.get().longValue();
            userInfo.imid = UserProperties.roomId.get().intValue();
            userInfo.name = UserProperties.nickName.get();
            userInfo.sex = "";
            userInfo.sign = "";
            userInfo.avatarUrl = UserProperties.avatarUrl.get();
            TokenInfo defaultToken = LoginApi.getDefaultToken();
            userInfo.udbToken = defaultToken.getToken();
            userInfo.ticketType = String.valueOf(defaultToken.getTokenType());
            userInfo.tid = LoginProperties.uid.get().longValue();
            userInfo.sid = LoginProperties.uid.get().longValue();
            userInfo.pid = LoginProperties.uid.get().longValue();
        }
        return userInfo;
    }
}
