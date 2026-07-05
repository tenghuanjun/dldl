package com.huyaudbunify.account;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum SdkH5Ret {
    H5Ret_Unknow(0),
    H5Ret_Reg(12),
    H5Ret_FindPassword(13),
    H5Ret_ChangePassword(14),
    H5Ret_BindMobile(15),
    H5Ret_LgnThird_BindMobile(16),
    H5Ret_Upgrade(17),
    H5Ret_LgnThird_Auth(18);

    private int mType;

    SdkH5Ret(int i) {
        this.mType = i;
    }

    public static SdkH5Ret valueOf(int i) {
        switch (i) {
            case 12:
                return H5Ret_Reg;
            case 13:
                return H5Ret_FindPassword;
            case 14:
                return H5Ret_ChangePassword;
            case 15:
                return H5Ret_BindMobile;
            case 16:
                return H5Ret_LgnThird_BindMobile;
            case 17:
                return H5Ret_Upgrade;
            case 18:
                return H5Ret_LgnThird_Auth;
            default:
                return H5Ret_Unknow;
        }
    }
}
