package com.huya.component.user.module;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.duowan.HUYA.CheckNameValidReq;
import com.duowan.HUYA.CheckNameValidRsp;
import com.duowan.HUYA.GetUserCardReq;
import com.duowan.HUYA.GetUserCardRsp;
import com.duowan.HUYA.GetUserHDAvatarReq;
import com.duowan.HUYA.GetUserHDAvatarRsp;
import com.duowan.HUYA.GetUserProfileReq;
import com.duowan.HUYA.GetUserProfileRsp;
import com.duowan.HUYA.ModifyUserNickReq;
import com.duowan.HUYA.ModifyUserNickRsp;
import com.duowan.HUYA.PresenterBase;
import com.duowan.HUYA.PresenterLevelProgressReq;
import com.duowan.HUYA.PresenterLevelProgressRsp;
import com.duowan.HUYA.SettingFetchReq;
import com.duowan.HUYA.SettingFetchRsp;
import com.duowan.HUYA.SettingSetupReq;
import com.duowan.HUYA.UserBase;
import com.duowan.HUYA.UserId;
import com.duowan.HUYA.UserNickStatusReq;
import com.duowan.HUYA.UserNickStatusRsp;
import com.duowan.HUYA.UserProfile;
import com.duowan.HUYA.UserSettingItem;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.notify.MapPropertyUpdate;
import com.duowan.auk.http.BitmapEasyHandler;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.http.StringEasyHandler;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.base.utils.BitmapUtil;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.LoginEvent;
import com.huya.component.login.api.TokenInfo;
import com.huya.component.user.UserProperties;
import com.huya.component.user.api.IUserService;
import com.huya.component.user.api.UserApi;
import com.huya.component.user.api.UserCallback;
import com.huya.component.user.api.UserInterface;
import com.huya.component.user.api.data.PresenterChannelInfo;
import com.huya.component.user.api.data.PresenterInfo;
import com.huya.component.user.api.data.PresenterLevel;
import com.huya.component.user.api.data.UserInfo;
import com.huya.component.user.event.UserAvatarEvent;
import com.huya.live.common.api.DataConst;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.AbsService;
import com.huya.live.utils.cache.BitmapCache;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.utils.BitmapUtils;
import com.huya.mtp.utils.Utils;
import com.sqwan.common.route.FunctionRouter;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserService extends AbsService implements IUserService {
    private static final String TAG = "UserInfoModule";

    @Override // com.huya.live.service.AbsService
    public void onCreate() {
        ArkUtils.register(this);
    }

    @Override // com.huya.live.service.AbsService
    public void onStop() {
        ArkUtils.unregister(this);
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<CheckNameValidRsp> CheckNameIsValid(String str, String str2) {
        return ((IUserWupApi) NS.get(IUserWupApi.class)).checkNameValid(new CheckNameValidReq(UserApi.getUserId(), str, str2));
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<GetUserHDAvatarRsp> getUserHDAvatar(UserId userId, long j) {
        return ((IUserWupApi) NS.get(IUserWupApi.class)).getUserHDAvatar(new GetUserHDAvatarReq(userId, j));
    }

    @Override // com.huya.component.user.api.IUserService
    public void queryUserInfo(long j) {
        L.debug(TAG, "queryUserInfo");
        getUserProfile(j);
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<PresenterLevelProgressRsp> getPresenterLevelProgress(final long j) {
        return ((IUserWupApi) NS.get(IUserWupApi.class)).getPresenterLevelProgress(new PresenterLevelProgressReq(UserApi.getUserId(), j)).doOnNext(new Consumer<PresenterLevelProgressRsp>() { // from class: com.huya.component.user.module.UserService.1
            @Override // io.reactivex.functions.Consumer
            public void accept(PresenterLevelProgressRsp presenterLevelProgressRsp) throws Exception {
                L.info(UserService.TAG, "getPresenterLevelProgress success");
                if (presenterLevelProgressRsp == null || presenterLevelProgressRsp.tLevelBase == null || j != LoginApi.getUid()) {
                    return;
                }
                UserProperties.presenterLevel.set(new PresenterLevel(presenterLevelProgressRsp));
            }
        });
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<GetUserProfileRsp> getUserProfile(final long j) {
        return ((IUserWupApi) NS.get(IUserWupApi.class)).getUserProfile(new GetUserProfileReq(UserApi.getUserId(), j)).doOnNext(new Consumer<GetUserProfileRsp>() { // from class: com.huya.component.user.module.UserService.3
            @Override // io.reactivex.functions.Consumer
            public void accept(GetUserProfileRsp getUserProfileRsp) throws Exception {
                L.info(UserService.TAG, "getUserProfile success");
                if (j != LoginProperties.uid.get().longValue()) {
                    return;
                }
                UserService.this.onUserProfile(j, getUserProfileRsp.getTUserProfile());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.huya.component.user.module.UserService.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                L.info(UserService.TAG, "getUserProfile fail");
            }
        });
    }

    @Override // com.huya.component.user.api.IUserService
    public void onUserProfile(long j, UserProfile userProfile) {
        if (userProfile != null) {
            L.info(TAG, "用户资料：" + userProfile);
            if (!UserProperties.avatarUrl.get().equals(userProfile.tUserBase.sAvatarUrl)) {
                getUserAvatar(UserProperties.avatarUrl.get());
            }
            UserBase tUserBase = userProfile.getTUserBase();
            UserInfo userInfo = new UserInfo();
            userInfo.yy = tUserBase.lYYId;
            userInfo.huyaId = tUserBase.sHuyaId;
            userInfo.signature = tUserBase.sSign;
            userInfo.nickname = tUserBase.sNickName;
            userInfo.gender = tUserBase.iGender == 1 ? UserInfo.Gender.Male : UserInfo.Gender.Female;
            userInfo.portrait = tUserBase.sAvatarUrl;
            userInfo.userLevel = tUserBase.iUserLevel;
            PresenterBase tPresenterBase = userProfile.getTPresenterBase();
            if (tPresenterBase.iCertified == 1) {
                userInfo.nickname = tPresenterBase.sPresenterName;
            }
            if (TextUtils.isEmpty(userInfo.nickname) && !TextUtils.isEmpty(LoginProperties.passport.get())) {
                userInfo.nickname = LoginProperties.passport.get();
            }
            L.info(TAG, "yy = %d, nickName = %s", Long.valueOf(userInfo.yy), userInfo.nickname);
            UserProperties.userInfoMap.put(Long.valueOf(j), userInfo);
            UserProperties.roomId.set(Integer.valueOf(userProfile.tPresenterBase.iRoomId));
            UserProperties.liveUrl.set(DataConst.URL_DEFAULT_SHARE + UserProperties.roomId.get());
            UserProperties.subscribesCount.set(Integer.valueOf(tUserBase.iSubscribedCount));
            PresenterBase tPresenterBase2 = userProfile.getTPresenterBase();
            if (tPresenterBase2 != null) {
                PresenterInfo presenterInfo = new PresenterInfo();
                presenterInfo.iCertified = tPresenterBase2.iCertified == 1;
                presenterInfo.lSignedChannel = tPresenterBase2.lSignedChannel;
                presenterInfo.iPresenterLevel = tPresenterBase2.iPresenterLevel;
                presenterInfo.lPresenterExp = tPresenterBase2.lPresenterExp;
                presenterInfo.iRoomId = tPresenterBase2.iRoomId;
                UserProperties.presenterInfo.set(presenterInfo);
            }
        }
    }

    private void getUserAvatar(final String str) {
        getUserHDAvatar(UserApi.getUserId(), LoginApi.getUid()).compose(SchedulerUtils.net()).subscribe(new WupObserver<GetUserHDAvatarRsp>() { // from class: com.huya.component.user.module.UserService.4
            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onNext(GetUserHDAvatarRsp getUserHDAvatarRsp) {
                String str2 = getUserHDAvatarRsp.sHDAvatar;
                if (TextUtils.isEmpty(str2)) {
                    UserService.this.downloadUserAvatar(str);
                } else {
                    UserService.this.downloadUserAvatar(str2);
                }
            }

            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                UserService.this.downloadUserAvatar(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadUserAvatar(final String str) {
        Bitmap bitmapToCache = BitmapCache.getInstance().readBitmapToCache(str);
        if (bitmapToCache != null) {
            setPortrait(bitmapToCache, str);
        } else {
            HttpClient.get(str, new BitmapEasyHandler() { // from class: com.huya.component.user.module.UserService.5
                @Override // com.duowan.auk.http.BitmapEasyHandler
                public void onFailure() {
                }

                @Override // com.duowan.auk.http.BitmapEasyHandler
                public void onSuccess(Bitmap bitmap) {
                    if (bitmap == null) {
                        return;
                    }
                    BitmapCache.getInstance().saveBitmapToCache(str, bitmap);
                    UserService.this.setPortrait(bitmap, str);
                    ArkUtils.send(new UserAvatarEvent(bitmap));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPortrait(Bitmap bitmap, String str) {
        if (bitmap != null) {
            if (bitmap.getWidth() > 140) {
                bitmap = Bitmap.createScaledBitmap(bitmap, 140, 140, true);
            }
            UserProperties.avatar.set(bitmap);
            UserProperties.avatarUrl.set(str);
            UserProperties.portrait.set(BitmapUtils.getCircle(Bitmap.createBitmap(bitmap)));
        }
    }

    @IASlot
    public void onLogout(LoginEvent.LogOutFinished logOutFinished) {
        doLogout(logOutFinished);
    }

    @Override // com.huya.component.user.api.IUserService
    public void doLogout(LoginEvent.LogOutFinished logOutFinished) {
        if (logOutFinished == null) {
            return;
        }
        LoginEvent.LogOutFinished.Reason reason = logOutFinished.reason;
        if (reason == LoginEvent.LogOutFinished.Reason.NoNetwork || reason == LoginEvent.LogOutFinished.Reason.KickOff) {
            UserProperties.tempPortraitUrl.set(UserProperties.avatarUrl.get());
            UserProperties.tempNickName.set(UserProperties.nickName.get());
        } else {
            UserProperties.tempPortraitUrl.reset();
            UserProperties.tempNickName.reset();
        }
    }

    @IASlot
    public void onLoginSuccess(LoginEvent.LoginSuccess loginSuccess) {
        doLoginSuccess(loginSuccess);
    }

    @Override // com.huya.component.user.api.IUserService
    public void doLoginSuccess(LoginEvent.LoginSuccess loginSuccess) {
        L.debug(TAG, "onLoginSuccess");
        getUserProfile(LoginProperties.uid.get().longValue());
        getPresenterLevelProgress(LoginProperties.uid.get().longValue()).compose(SchedulerUtils.net()).subscribe(new WupObserver());
    }

    @IASlot
    public void onLogout(LoginEvent.LogoutRest logoutRest) {
        doLogoutRest();
    }

    @Override // com.huya.component.user.api.IUserService
    public void doLogoutRest() {
        UserProperties.nickName.reset();
        UserProperties.signature.reset();
        UserProperties.gender.reset();
        UserProperties.avatarUrl.reset();
        UserProperties.nobleLevel.reset();
        UserProperties.avatar.reset();
        UserProperties.portrait.reset();
        UserProperties.nobleLevel.reset();
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<Object> setPresenterPCAuthInfo(PresenterChannelInfo presenterChannelInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UserSettingItem("Presenter_PCAuthInfo", presenterChannelInfo.toBase64String()));
        arrayList.add(new UserSettingItem("Live_Mode", "0"));
        arrayList.add(new UserSettingItem("Mobile_Flv_Url", ""));
        return ((IUserWupApi) NS.get(IUserWupApi.class)).setUserSetting(new SettingSetupReq(UserApi.getUserId(), arrayList));
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<SettingFetchRsp> getPresenterPCAuthInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Presenter_PCAuthInfo");
        arrayList.add("Live_Mode");
        arrayList.add("Mobile_Flv_Url");
        return ((IUserWupApi) NS.get(IUserWupApi.class)).getUserSetting(new SettingFetchReq(UserApi.getUserId(), arrayList, false));
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<UserNickStatusRsp> getUserNickNameStatus() {
        return ((IUserWupApi) NS.get(IUserWupApi.class)).getUserNickStatus(new UserNickStatusReq(UserApi.getUserId()));
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<ModifyUserNickRsp> modifyUserNickname(String str, String str2, int i) {
        ModifyUserNickReq modifyUserNickReq = new ModifyUserNickReq();
        modifyUserNickReq.setTId(UserApi.getUserId());
        modifyUserNickReq.setSNick(str2);
        if (!TextUtils.isEmpty(str)) {
            modifyUserNickReq.setSVerifyCode(str);
        }
        if (i != 0) {
            modifyUserNickReq.setIPayType(i);
        }
        return ((IUserWupApi) NS.get(IUserWupApi.class)).modifyUserNick(modifyUserNickReq);
    }

    @IASlot
    public void onModifyHuyaPortrait(UserInterface.ModifyHuyaPortrait modifyHuyaPortrait) throws Throwable {
        Bitmap smallBitmap;
        byte[] bArrFile2byte;
        TokenInfo defaultToken = LoginApi.getDefaultToken();
        if (defaultToken == null || TextUtils.isEmpty(defaultToken.getToken())) {
            L.warn(TAG, "get token empty");
            return;
        }
        if (modifyHuyaPortrait.portrait == null || TextUtils.isEmpty(modifyHuyaPortrait.portrait.getPath())) {
            return;
        }
        File file = new File(modifyHuyaPortrait.portrait.getPath());
        String str = modifyHuyaPortrait.md5;
        if (!file.exists()) {
            Bitmap bitmapDecodeUriAsBitmap = BitmapUtil.decodeUriAsBitmap(modifyHuyaPortrait.portrait);
            if (bitmapDecodeUriAsBitmap != null) {
                smallBitmap = BitmapUtil.getSmallBitmap(bitmapDecodeUriAsBitmap, 200, 200);
                bArrFile2byte = BitmapUtil.bitmap2Byte(bitmapDecodeUriAsBitmap);
            } else {
                L.warn(TAG, "bitmap is null");
                return;
            }
        } else {
            smallBitmap = BitmapUtil.getSmallBitmap(file.getAbsolutePath(), 200, 200);
            bArrFile2byte = BitmapUtil.File2byte(file.getAbsolutePath());
        }
        if (bArrFile2byte == null) {
            return;
        }
        final Bitmap bitmap = UserProperties.portrait.get();
        setPortrait(smallBitmap, "");
        String str2 = "===" + System.currentTimeMillis() + "===";
        byte[] bytes = (((((((((((((((("--" + str2 + "\r\n") + "Content-Disposition: form-data; name=\"token\"\r\n") + "Content-Type: text/plain; charset=UTF-8\r\n\r\n") + defaultToken.getToken() + "\r\n") + "--" + str2 + "\r\n") + "Content-Disposition: form-data; name=\"ticketType\"\r\n") + "Content-Type: text/plain; charset=UTF-8\r\n\r\n") + String.valueOf(defaultToken.getTokenType()) + "\r\n") + "--" + str2 + "\r\n") + "Content-Disposition: form-data; name=\"hdAvatar\"\r\n") + "Content-Type: text/plain; charset=UTF-8\r\n\r\n") + "true\r\n") + "--" + str2 + "\r\n") + "Content-Disposition: form-data; name=\"screenshot\"; filename=\"") + Utils.md5(new String(bArrFile2byte)) + ".png\"\r\n") + "Content-Type: image/png\r\n\r\n").getBytes();
        byte[] bytes2 = ("\r\n\r\n--" + str2 + "--\r\n").getBytes();
        HttpClient.RequestParams requestParams = new HttpClient.RequestParams();
        requestParams.setBodyContentType("multipart/form-data; boundary=" + str2);
        byte[] bArr = new byte[bytes.length + bArrFile2byte.length + bytes2.length];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        System.arraycopy(bArrFile2byte, 0, bArr, bytes.length, bArrFile2byte.length);
        System.arraycopy(bytes2, 0, bArr, bytes.length + bArrFile2byte.length, bytes2.length);
        requestParams.putBody(bArr);
        requestParams.putUrlParam(FeedBackConstants.KEY_LOG_MD5, str);
        HttpClient.post(ArkValue.debuggable() ? "https://test.q.huya.com/zs/useravatar.php" : "https://q.huya.com/zs/useravatar.php", requestParams, new StringEasyHandler() { // from class: com.huya.component.user.module.UserService.6
            @Override // com.duowan.auk.http.StringEasyHandler
            public void onSuccess(String str3) {
                String string;
                String string2;
                int i;
                L.info(UserService.TAG, "onSuccess:" + str3);
                try {
                    JSONObject jSONObject = new JSONObject(new JSONTokener(new String(str3)));
                    i = jSONObject.has("status") ? jSONObject.getInt("status") : 0;
                    string2 = jSONObject.has(FunctionRouter.KEY_DATA) ? jSONObject.getString(FunctionRouter.KEY_DATA) : "";
                    if (jSONObject.has("msg")) {
                        string = jSONObject.getString("msg");
                        try {
                            L.info(UserService.TAG, "onModifyHuyaPortrait msg: %s", string);
                        } catch (Exception unused) {
                            string2 = "";
                            i = 0;
                        }
                    } else {
                        string = "";
                    }
                } catch (Exception unused2) {
                    string = "";
                }
                if (i == 200) {
                    UserProperties.avatarUrl.set(string2);
                    ArkUtils.send(new UserCallback.ModifyHuyaPortraitResult(true, ""));
                    UserService.this.queryUserInfo(LoginApi.getUid());
                } else {
                    UserProperties.portrait.set(bitmap);
                    ArkUtils.send(new UserCallback.ModifyHuyaPortraitResult(false, string));
                }
            }

            @Override // com.duowan.auk.http.StringEasyHandler
            public void onFailure() {
                UserProperties.portrait.set(bitmap);
                ArkUtils.send(new UserCallback.ModifyHuyaPortraitResult(false, ""));
            }
        });
    }

    @IASlot(mark = {UserProperties.MarkUserInfoMap})
    public void onUserInfoMap(MapPropertyUpdate<Long, UserInfo> mapPropertyUpdate) {
        if (mapPropertyUpdate.key.longValue() == LoginProperties.uid.get().longValue() && LoginApi.isLogined()) {
            UserInfo userInfo = mapPropertyUpdate.newValue;
            LoginProperties.yy.set(Long.valueOf(userInfo.yy));
            UserProperties.signature.set(userInfo.signature);
            UserProperties.gender.set(userInfo.gender);
            UserProperties.huyaId.set(userInfo.huyaId);
            UserProperties.nickName.set(userInfo.nickname);
            if (userInfo.portrait != null && !userInfo.portrait.equals(UserProperties.avatarUrl)) {
                getUserAvatar(userInfo.portrait);
            }
            if (UserApi.getUserCallback() != null) {
                UserApi.getUserCallback().onUserInfoSet(userInfo.nickname);
            }
        }
    }

    @Override // com.huya.component.user.api.IUserService
    public Observable<GetUserCardRsp> getUserCard(UserId userId, long j, long j2) {
        return ((IUserWupApi) NS.get(IUserWupApi.class)).getUserCard(new GetUserCardReq(userId, j, j2));
    }
}
