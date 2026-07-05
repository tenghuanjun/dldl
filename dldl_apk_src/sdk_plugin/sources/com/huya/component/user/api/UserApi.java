package com.huya.component.user.api;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.duowan.HUYA.UserId;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.duowan.networkmars.wup.WupHelper;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.huya.component.user.UserProperties;
import com.huya.component.user.api.data.PresenterLevel;
import com.huya.mtp.utils.StringUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserApi {
    private static String APM_HUYA_UA = null;
    private static final String CONFIG_WUP_GUID = "wup_guid";
    private static final String TAG = "UserApi";
    private static String mGuid;
    private static IUserInfoCallback sUserCallback;

    public static String getGUID() {
        if (TextUtils.isEmpty(mGuid)) {
            mGuid = Config.getInstance(ArkValue.gContext).getString(CONFIG_WUP_GUID, "");
        }
        return mGuid;
    }

    public static void setGUID(String str) {
        if (TextUtils.isEmpty(mGuid) || !mGuid.equals(str)) {
            mGuid = str;
            L.info(TAG, "update guid :%s", str);
        } else {
            mGuid = str;
        }
        Config.getInstance(ArkValue.gContext).setString(CONFIG_WUP_GUID, str);
    }

    public static void initUserInfoCallback(IUserInfoCallback iUserInfoCallback) {
        sUserCallback = iUserInfoCallback;
    }

    public static IUserInfoCallback getUserCallback() {
        return sUserCallback;
    }

    public static UserId getUserId() {
        UserId userId = new UserId();
        userId.setLUid(LoginProperties.uid.get().longValue());
        userId.setSGuid(getGUID());
        userId.setSHuYaUA(WupHelper.getSHuYaUA());
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        if (defaultToken == null) {
            L.error(TAG, "getUserId ResGetTicket == null...");
            userId.setSToken("");
            userId.setITokenType(0);
        } else {
            userId.setSToken(defaultToken.getToken());
            userId.setITokenType(defaultToken.getTokenType());
        }
        return userId;
    }

    private static String getApmHuYaUA() {
        if (StringUtils.isNullOrEmpty(APM_HUYA_UA)) {
            StringBuilder sb = new StringBuilder();
            sb.append(WupHelper.getClientType());
            sb.append(ArkValue.gIsSnapshot ? "_test" : "");
            APM_HUYA_UA = String.format("%s&%s&%s", sb.toString(), "1.0", ArkValue.channelName());
        }
        return APM_HUYA_UA;
    }

    public static UserId getApmUserId() {
        UserId userId = getUserId();
        userId.sHuYaUA = getApmHuYaUA();
        return userId;
    }

    public static long getRoomid() {
        if (UserProperties.roomId.get().intValue() != 0) {
            return UserProperties.roomId.get().intValue();
        }
        return LoginApi.getYY();
    }

    public static String getNickname() {
        return UserProperties.nickName.get();
    }

    public static String getHuyaIdOrYY() {
        if (TextUtils.isEmpty(UserProperties.huyaId.get())) {
            return String.valueOf(LoginApi.getYY());
        }
        return UserProperties.huyaId.get();
    }

    public static void updatePortrait(ImageView imageView, int i) {
        Bitmap bitmap = UserProperties.portrait.get();
        if (bitmap == null) {
            imageView.setImageResource(i);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static PresenterLevel getPresenterLevel() {
        return UserProperties.presenterLevel.get();
    }
}
