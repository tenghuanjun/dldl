package com.huya.component.user.api;

import com.duowan.HUYA.CheckNameValidRsp;
import com.duowan.HUYA.GetUserCardRsp;
import com.duowan.HUYA.GetUserHDAvatarRsp;
import com.duowan.HUYA.GetUserProfileRsp;
import com.duowan.HUYA.ModifyUserNickRsp;
import com.duowan.HUYA.PresenterLevelProgressRsp;
import com.duowan.HUYA.SettingFetchRsp;
import com.duowan.HUYA.UserId;
import com.duowan.HUYA.UserNickStatusRsp;
import com.duowan.HUYA.UserProfile;
import com.huya.component.login.api.LoginEvent;
import com.huya.component.user.api.data.PresenterChannelInfo;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IUserService {
    Observable<CheckNameValidRsp> CheckNameIsValid(String str, String str2);

    void doLoginSuccess(LoginEvent.LoginSuccess loginSuccess);

    void doLogout(LoginEvent.LogOutFinished logOutFinished);

    void doLogoutRest();

    Observable<PresenterLevelProgressRsp> getPresenterLevelProgress(long j);

    Observable<SettingFetchRsp> getPresenterPCAuthInfo();

    Observable<GetUserCardRsp> getUserCard(UserId userId, long j, long j2);

    Observable<GetUserHDAvatarRsp> getUserHDAvatar(UserId userId, long j);

    Observable<UserNickStatusRsp> getUserNickNameStatus();

    Observable<GetUserProfileRsp> getUserProfile(long j);

    Observable<ModifyUserNickRsp> modifyUserNickname(String str, String str2, int i);

    void onUserProfile(long j, UserProfile userProfile);

    void queryUserInfo(long j);

    Observable<Object> setPresenterPCAuthInfo(PresenterChannelInfo presenterChannelInfo);
}
