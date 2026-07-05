package com.sqwan.common.user;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sq.sdk.tool.util.SpUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.user.UserInfo;
import com.sqwan.common.util.ZipString;
import com.sqwan.msdk.BaseSQwanCore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserInfoManager {
    private static final String SP_KEY_CURRENT_USER = "current_user";
    private static final String SP_KEY_LAST_USER = "last_user";
    private static final String SQ_PREFS = "sq_prefs";
    private static final String TAG = "【User】";
    private static volatile UserInfoManager sInstance;
    private volatile RoleInfo mCurrentRoleInfo;
    private UserInfo mCurrentUser;
    private boolean mRamEnable;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final List<UserListener> mUserListeners = new ArrayList();
    private final List<RoleListener> mRoleListeners = new ArrayList();

    public static class RoleListener {
        public void onRoleIn(RoleInfo roleInfo, RoleInfo roleInfo2) {
        }

        public void onRoleInfoChanged(RoleInfo roleInfo, RoleInfo roleInfo2) {
        }

        public void onRoleOut(RoleInfo roleInfo) {
        }
    }

    public static class UserListener {
        public void onLogin(UserInfo userInfo, UserInfo userInfo2) {
        }

        public void onLogout(UserInfo userInfo) {
        }

        public void onUserInfoChanged(UserInfo userInfo, UserInfo userInfo2) {
        }
    }

    public static UserInfoManager getInstance() {
        if (sInstance == null) {
            synchronized (UserInfoManager.class) {
                if (sInstance == null) {
                    sInstance = new UserInfoManager();
                }
            }
        }
        return sInstance;
    }

    private UserInfoManager() {
    }

    public boolean isLogin() {
        return getCurrentUser() != null;
    }

    public UserInfo getLastLoginUser() {
        UserInfo userFromLastSp = getUserFromLastSp();
        if (userFromLastSp == null) {
            userFromLastSp = getUserFromOld();
            SQLog.w("【User】旧记录: " + userFromLastSp);
            if (userFromLastSp != null) {
                saveUserToLastSp(userFromLastSp);
            }
        }
        return userFromLastSp;
    }

    public void setRamEnable(boolean z) {
        this.mRamEnable = z;
    }

    public UserInfo getCurrentUser() {
        if (this.mCurrentUser == null || !this.mRamEnable) {
            this.mCurrentUser = getCurrentUserFromSp();
        }
        return this.mCurrentUser;
    }

    public UserInfo logout() {
        final UserInfo currentUser = getCurrentUser();
        SQLog.w("【User】退出登录: " + currentUser);
        setCurrentRoleInfo(null);
        clearCurrentUser();
        if (currentUser != null) {
            this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$WR_UiX7rjFP5_tsxHniX1LmDFpo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$logout$0$UserInfoManager(currentUser);
                }
            });
        }
        return currentUser;
    }

    public /* synthetic */ void lambda$logout$0$UserInfoManager(UserInfo userInfo) {
        for (UserListener userListener : new ArrayList(this.mUserListeners)) {
            if (userListener != null) {
                userListener.onLogout(userInfo);
            }
        }
    }

    public void setLoginUser(final UserInfo userInfo) {
        SQLog.i("【User】设置登录用户" + userInfo);
        final UserInfo userInfo2 = this.mCurrentUser;
        this.mCurrentUser = userInfo;
        saveCurrentUserToSp(userInfo);
        saveUserToLastSp(userInfo);
        this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$raGZ2n_TnAoWDUoS7vBNVC7L-L4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setLoginUser$1$UserInfoManager(userInfo2, userInfo);
            }
        });
    }

    public /* synthetic */ void lambda$setLoginUser$1$UserInfoManager(UserInfo userInfo, UserInfo userInfo2) {
        for (UserListener userListener : new ArrayList(this.mUserListeners)) {
            if (userListener != null) {
                userListener.onLogin(userInfo, userInfo2);
            }
        }
    }

    public void updateUserInfo(final UserInfo userInfo) {
        SQLog.i("【User】更新用户信息" + userInfo);
        final UserInfo userInfo2 = this.mCurrentUser;
        if (userInfo2 == null) {
            SQLog.w("【User】当前无用户, 请调用setLoginUser");
            return;
        }
        if (userInfo2.uid.equals(userInfo.uid) && userInfo2.type.equals(userInfo.type)) {
            this.mCurrentUser = userInfo;
            saveCurrentUserToSp(userInfo);
            saveUserToLastSp(userInfo);
            this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$TZ_YEyQiUPQidnoJ5mqB6vgOP3Y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateUserInfo$2$UserInfoManager(userInfo2, userInfo);
                }
            });
            return;
        }
        SQLog.w(TAG + userInfo + "和" + userInfo2 + "的uid和类型不一致, 请调用setLoginUser");
    }

    public /* synthetic */ void lambda$updateUserInfo$2$UserInfoManager(UserInfo userInfo, UserInfo userInfo2) {
        for (UserListener userListener : new ArrayList(this.mUserListeners)) {
            if (userListener != null) {
                userListener.onUserInfoChanged(userInfo, userInfo2);
            }
        }
    }

    public void clearCurrentUser() {
        this.mCurrentUser = null;
        clearCurrentUserFromSp();
    }

    private UserInfo getCurrentUserFromSp() {
        return UserInfo.fromJson(SpUtils.getInstance().getString(SQ_PREFS, SP_KEY_CURRENT_USER));
    }

    private void saveCurrentUserToSp(UserInfo userInfo) {
        SpUtils.getInstance().putString(SQ_PREFS, SP_KEY_CURRENT_USER, userInfo.toJsonString());
    }

    private void clearCurrentUserFromSp() {
        SpUtils.getInstance().putString(SQ_PREFS, SP_KEY_CURRENT_USER, "");
    }

    private void saveUserToLastSp(UserInfo userInfo) {
        SpUtils.getInstance().putString(SQ_PREFS, SP_KEY_LAST_USER, userInfo.toJsonString());
    }

    private UserInfo getUserFromLastSp() {
        return UserInfo.fromJson(SpUtils.getInstance().getString(SQ_PREFS, SP_KEY_LAST_USER));
    }

    private UserInfo getUserFromOld() {
        UserInfo accountUserInfo;
        String string = SpUtils.getInstance().getString(SQ_PREFS, "user_info", "");
        if (TextUtils.isEmpty(string)) {
            String string2 = SpUtils.getInstance().getString(SQ_PREFS, BaseSQwanCore.LOGIN_KEY_USERID, "");
            String string3 = SpUtils.getInstance().getString(SQ_PREFS, BaseSQwanCore.LOGIN_KEY_USERNAME, "");
            String string4 = SpUtils.getInstance().getString(SQ_PREFS, "token", "");
            String strZipString2Json = ZipString.zipString2Json(SpUtils.getInstance().getString(SQ_PREFS, "pd", ""));
            if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4) || TextUtils.isEmpty(string3) || TextUtils.isEmpty(strZipString2Json)) {
                return null;
            }
            return new UserInfo.AccountUserInfo(string2, string4, string3, strZipString2Json);
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            String strOptString = jSONObject.optString("uname");
            String strZipString2Json2 = ZipString.zipString2Json(jSONObject.optString(SqConstants.UPWD));
            String strOptString2 = jSONObject.optString("token");
            String strOptString3 = jSONObject.optString(SqConstants.REFRESH_TOKEN);
            String strOptString4 = jSONObject.optString("uid");
            LoginType loginType = LoginType.get(Integer.parseInt(jSONObject.optString("login_type")));
            if (loginType == LoginType.ACCOUNT) {
                accountUserInfo = new UserInfo.AccountUserInfo(strOptString4, strOptString2, strOptString, strZipString2Json2);
            } else if (loginType == LoginType.PHONE) {
                UserInfo.PhoneUserInfo phoneUserInfo = new UserInfo.PhoneUserInfo(strOptString4, strOptString2, jSONObject.optString("mobile"));
                phoneUserInfo.pwd = strZipString2Json2;
                accountUserInfo = phoneUserInfo;
            } else if (loginType == LoginType.WECHAT) {
                UserInfo.WechatUserInfo wechatUserInfo = new UserInfo.WechatUserInfo(strOptString4, strOptString2);
                wechatUserInfo.uname = strOptString;
                wechatUserInfo.pwd = strZipString2Json2;
                accountUserInfo = wechatUserInfo;
            } else {
                BuglessAction.reportCatchException(new IllegalArgumentException(), "异常的用户类型", string, 999);
                return null;
            }
            accountUserInfo.refreshToken = strOptString3;
            return accountUserInfo;
        } catch (Exception e) {
            BuglessAction.reportCatchException(e, "解析上次登录用户失败", string, 999);
            return null;
        }
    }

    public RoleInfo getCurrentRoleInfo() {
        return this.mCurrentRoleInfo;
    }

    public void setCurrentRoleInfo(final RoleInfo roleInfo) {
        final RoleInfo roleInfo2 = this.mCurrentRoleInfo;
        this.mCurrentRoleInfo = roleInfo;
        if (roleInfo != null) {
            SpUtils.getInstance().putString(SQ_PREFS, SqConstants.DSID, roleInfo.getServerId());
            SpUtils.getInstance().putString(SQ_PREFS, SqConstants.DRID, roleInfo.getRoleId());
            SpUtils.getInstance().putString(SQ_PREFS, SqConstants.DRNAME, roleInfo.getRoleName());
            SpUtils.getInstance().putString(SQ_PREFS, SqConstants.DRLEVEL, roleInfo.getRoleLevel());
            SpUtils.getInstance().putString(SQ_PREFS, "viplevel", roleInfo.getVipLevel());
            SpUtils.getInstance().putString(SQ_PREFS, BaseSQwanCore.INFO_SERVERNAME, roleInfo.getServerName());
        }
        if (roleInfo == null) {
            SQLog.w("【User】清空当前角色 " + roleInfo2);
            this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$mE13G9pSGw-3lNmsayS5n1UWuoY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setCurrentRoleInfo$6$UserInfoManager(roleInfo2);
                }
            });
            return;
        }
        if (roleInfo2 != null) {
            if (!Objects.equals(roleInfo2.getRoleId(), roleInfo.getRoleId())) {
                SQLog.w("【User】切换角色 " + roleInfo);
                this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$WmBYcIEy_hrtPhYCEbOL5Wwpuck
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setCurrentRoleInfo$3$UserInfoManager(roleInfo2, roleInfo);
                    }
                });
                return;
            }
            SQLog.d("【User】更新角色 " + roleInfo);
            this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$ZILm3c-XKB0OH-Vrr_I0b_xDU7g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setCurrentRoleInfo$4$UserInfoManager(roleInfo2, roleInfo);
                }
            });
            return;
        }
        SQLog.i("【User】设置角色 " + roleInfo);
        this.mHandler.post(new Runnable() { // from class: com.sqwan.common.user.-$$Lambda$UserInfoManager$BGPoHcEHf1nF2udIo9ccyzuND7o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setCurrentRoleInfo$5$UserInfoManager(roleInfo);
            }
        });
    }

    public /* synthetic */ void lambda$setCurrentRoleInfo$3$UserInfoManager(RoleInfo roleInfo, RoleInfo roleInfo2) {
        for (RoleListener roleListener : new ArrayList(this.mRoleListeners)) {
            if (roleListener != null) {
                roleListener.onRoleIn(roleInfo, roleInfo2);
            }
        }
    }

    public /* synthetic */ void lambda$setCurrentRoleInfo$4$UserInfoManager(RoleInfo roleInfo, RoleInfo roleInfo2) {
        for (RoleListener roleListener : new ArrayList(this.mRoleListeners)) {
            if (roleListener != null) {
                roleListener.onRoleInfoChanged(roleInfo, roleInfo2);
            }
        }
    }

    public /* synthetic */ void lambda$setCurrentRoleInfo$5$UserInfoManager(RoleInfo roleInfo) {
        for (RoleListener roleListener : new ArrayList(this.mRoleListeners)) {
            if (roleListener != null) {
                roleListener.onRoleIn(null, roleInfo);
            }
        }
    }

    public /* synthetic */ void lambda$setCurrentRoleInfo$6$UserInfoManager(RoleInfo roleInfo) {
        for (RoleListener roleListener : new ArrayList(this.mRoleListeners)) {
            if (roleListener != null) {
                roleListener.onRoleOut(roleInfo);
            }
        }
    }

    public void addUserListener(UserListener userListener) {
        this.mUserListeners.add(userListener);
    }

    public void removeUserListener(UserListener userListener) {
        this.mUserListeners.remove(userListener);
    }

    public void addRoleListener(RoleListener roleListener) {
        this.mRoleListeners.add(roleListener);
    }

    public void removeRoleListener(RoleListener roleListener) {
        this.mRoleListeners.remove(roleListener);
    }
}
