package com.huya.component.user.module;

import com.duowan.HUYA.BadgeInfo;
import com.duowan.HUYA.BadgeReq;
import com.duowan.HUYA.CheckNameValidReq;
import com.duowan.HUYA.CheckNameValidRsp;
import com.duowan.HUYA.GetUserCardReq;
import com.duowan.HUYA.GetUserCardRsp;
import com.duowan.HUYA.GetUserHDAvatarReq;
import com.duowan.HUYA.GetUserHDAvatarRsp;
import com.duowan.HUYA.GetUserProfileReq;
import com.duowan.HUYA.GetUserProfileRsp;
import com.duowan.HUYA.GetUserTypeReq;
import com.duowan.HUYA.GetUserTypeRsp;
import com.duowan.HUYA.ModifyUserNickReq;
import com.duowan.HUYA.ModifyUserNickRsp;
import com.duowan.HUYA.PresenterLevelProgressReq;
import com.duowan.HUYA.PresenterLevelProgressRsp;
import com.duowan.HUYA.SettingFetchReq;
import com.duowan.HUYA.SettingFetchRsp;
import com.duowan.HUYA.SettingSetupReq;
import com.duowan.HUYA.UserNickStatusReq;
import com.duowan.HUYA.UserNickStatusRsp;
import com.huya.component.user.module.UserWupConstant;
import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.wup.WupFunc;
import com.huya.mtp.hyns.wup.WupProtocol;
import com.huya.mtp.hyns.wup.WupServant;
import io.reactivex.Observable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(WupProtocol.class)
@WupServant("huyauserui")
public interface IUserWupApi {
    @WupFunc(servant = "huyauserui", value = UserWupConstant.FuncName.CHECK_NAME_VALID)
    Observable<CheckNameValidRsp> checkNameValid(CheckNameValidReq checkNameValidReq);

    @WupFunc(servant = "presenterui", value = UserWupConstant.FuncName.GET_PRESENTER_LEVEL_PROGRESS)
    Observable<PresenterLevelProgressRsp> getPresenterLevelProgress(PresenterLevelProgressReq presenterLevelProgressReq);

    @WupFunc(servant = "huyauserui", value = UserWupConstant.FuncName.GET_USER_CARD)
    Observable<GetUserCardRsp> getUserCard(GetUserCardReq getUserCardReq);

    @WupFunc(servant = "huyauserui", value = UserWupConstant.FuncName.GET_USER_HD_AVATAR)
    Observable<GetUserHDAvatarRsp> getUserHDAvatar(GetUserHDAvatarReq getUserHDAvatarReq);

    @WupFunc(UserWupConstant.FuncName.GET_USER_NICK_NAME_STATUS)
    Observable<UserNickStatusRsp> getUserNickStatus(UserNickStatusReq userNickStatusReq);

    @WupFunc(servant = "huyauserui", value = UserWupConstant.FuncName.GET_USER_PROFILE)
    Observable<GetUserProfileRsp> getUserProfile(GetUserProfileReq getUserProfileReq);

    @WupFunc(servant = "liveui", value = UserWupConstant.FuncName.GET_USER_SETTING)
    Observable<SettingFetchRsp> getUserSetting(SettingFetchReq settingFetchReq);

    @WupFunc(servant = "liveui", value = UserWupConstant.FuncName.GET_USER_TYPE)
    Observable<GetUserTypeRsp> getUserType(GetUserTypeReq getUserTypeReq);

    @WupFunc(UserWupConstant.FuncName.MODIFY_USER_NICK_NAME)
    Observable<ModifyUserNickRsp> modifyUserNick(ModifyUserNickReq modifyUserNickReq);

    @WupFunc(servant = "liveui", value = UserWupConstant.FuncName.QUERY_BADGE_INFO)
    Observable<BadgeInfo> queryBadgeInfo(BadgeReq badgeReq);

    @WupFunc(servant = "liveui", value = UserWupConstant.FuncName.SET_USER_SETTING)
    Observable<Object> setUserSetting(SettingSetupReq settingSetupReq);
}
