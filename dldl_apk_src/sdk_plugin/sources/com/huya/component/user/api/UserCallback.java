package com.huya.component.user.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserCallback {

    public static class ModifyHuyaPortraitResult {
        public String msg;
        public boolean success;

        public ModifyHuyaPortraitResult(boolean z, String str) {
            this.success = z;
            this.msg = str;
        }
    }

    public static class OnSaveUserNickName {
        public final String verifyCode;

        public OnSaveUserNickName(String str) {
            this.verifyCode = str;
        }
    }
}
