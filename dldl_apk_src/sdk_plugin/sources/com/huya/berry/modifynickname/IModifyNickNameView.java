package com.huya.berry.modifynickname;

import com.duowan.HUYA.ModifyUserNickRsp;
import com.duowan.HUYA.UserNickStatusRsp;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IModifyNickNameView {
    void handleModifyNickNameRsp(ModifyUserNickRsp modifyUserNickRsp);

    void handleNickNameStatus(UserNickStatusRsp userNickStatusRsp);

    void handleNickNameStatusFail();

    void navToVerify(String str);

    void popupMoneyNotEnough();

    void saveWithVerifyCode(String str);
}
