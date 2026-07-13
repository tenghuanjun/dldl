package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.cloudcore.common.mode.Role;

/* JADX INFO: loaded from: classes3.dex */
public interface MultiUserService {

    public interface ChangeRoleCallBack {
        void onResult(String str, Role role, int i);
    }

    public interface RoomListener {
        void onJoinRoomRoleResult(Role role, int i, String str);

        void onPlayerChanged(String str);
    }

    void changeRole(String str, Role role, ChangeRoleCallBack changeRoleCallBack);

    Role getCurrentRole();

    void setRoomListener(RoomListener roomListener);
}
