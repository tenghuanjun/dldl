package com.sqwan.common.user;

import android.text.TextUtils;
import com.sqwan.msdk.BaseSQwanCore;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoleInfo {
    private String mLevelUpTime;
    private String mRoleCreateTime;
    private String mVipLevel;
    private String mServerId = "";
    private String mServerName = "";
    private String mRoleId = "";
    private String mRoleName = "";
    private String mRoleLevel = "";
    private String mPartyName = "";
    private String mRoleBalance = "";

    public RoleInfo setServerId(String str) {
        this.mServerId = str;
        return this;
    }

    public String getServerId() {
        return this.mServerId;
    }

    public RoleInfo setServerName(String str) {
        this.mServerName = str;
        return this;
    }

    public String getServerName() {
        return this.mServerName;
    }

    public RoleInfo setVipLevel(String str) {
        this.mVipLevel = str;
        return this;
    }

    public String getVipLevel() {
        return this.mVipLevel;
    }

    public RoleInfo setRoleId(String str) {
        this.mRoleId = str;
        return this;
    }

    public String getRoleId() {
        return this.mRoleId;
    }

    public RoleInfo setRoleCreateTime(String str) {
        this.mRoleCreateTime = str;
        return this;
    }

    public String getRoleCreateTime() {
        return this.mRoleCreateTime;
    }

    public RoleInfo setRoleLevelUpTime(String str) {
        this.mLevelUpTime = str;
        return this;
    }

    public String getRoleLevelUpTime() {
        return this.mLevelUpTime;
    }

    public RoleInfo setRoleName(String str) {
        this.mRoleName = str;
        return this;
    }

    public String getRoleName() {
        return this.mRoleName;
    }

    public RoleInfo setRoleLevel(String str) {
        this.mRoleLevel = str;
        return this;
    }

    public String getRoleLevel() {
        return this.mRoleLevel;
    }

    public RoleInfo setPartyName(String str) {
        this.mPartyName = str;
        return this;
    }

    public String getPartyName() {
        return this.mPartyName;
    }

    public RoleInfo setRoleBalance(String str) {
        this.mRoleBalance = str;
        return this;
    }

    public String getRoleBalance() {
        return this.mRoleBalance;
    }

    public boolean isInfoValid() {
        return (TextUtils.isEmpty(this.mServerId) || TextUtils.isEmpty(this.mRoleId)) ? false : true;
    }

    public String toString() {
        return "RoleInfo: 服务器=" + this.mServerName + '(' + this.mServerId + "), 角色=" + this.mRoleName + '(' + this.mRoleId + "), createTime=" + this.mRoleCreateTime + ", level=" + this.mRoleLevel + ", levelUpTime=" + this.mLevelUpTime + ", 工会=" + this.mPartyName + ", 余额=" + this.mRoleBalance + ", vipLevel=" + this.mVipLevel;
    }

    public String toMapString() {
        return "{serverName=" + this.mServerName + ", serverId=" + this.mServerId + ", roleName=" + this.mRoleName + ", roleId=" + this.mRoleId + ", createTime=" + this.mRoleCreateTime + ", level=" + this.mRoleLevel + ", levelUpTime=" + this.mLevelUpTime + ", vipLevel=" + this.mVipLevel + ", roleBalance=" + this.mRoleBalance + ", partyName=" + this.mPartyName + "}";
    }

    public static RoleInfo fromRoleMap(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleId(map.get(BaseSQwanCore.INFO_ROLEID));
        roleInfo.setRoleName(map.get(BaseSQwanCore.INFO_ROLENAME));
        roleInfo.setRoleLevel(map.get(BaseSQwanCore.INFO_ROLELEVEL));
        roleInfo.setRoleCreateTime(map.get(BaseSQwanCore.INFO_ROLE_TIME_CREATE));
        roleInfo.setRoleLevelUpTime(map.get(BaseSQwanCore.INFO_ROLE_TIME_LEVEL));
        roleInfo.setRoleBalance(map.get(BaseSQwanCore.INFO_BALANCE));
        roleInfo.setVipLevel(map.get(BaseSQwanCore.INFO_VIPLEVEL));
        roleInfo.setServerId(map.get(BaseSQwanCore.INFO_SERVERID));
        roleInfo.setServerName(map.get(BaseSQwanCore.INFO_SERVERNAME));
        roleInfo.setPartyName(map.get(BaseSQwanCore.INFO_PARTYNAME));
        return roleInfo;
    }
}
