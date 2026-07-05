package com.huya.component.user.api;

import android.graphics.Bitmap;
import android.net.Uri;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserInterface {

    public static class ModifyMyInfo {
        public String nickname;
        public String sex;
        public String signature;

        public ModifyMyInfo(String str, String str2, String str3) {
            this.nickname = str;
            this.signature = str2;
            this.sex = str3;
        }
    }

    public static class ModifyMyPortrait {
        public Bitmap portrait;

        public ModifyMyPortrait(Bitmap bitmap) {
            this.portrait = bitmap;
        }
    }

    public static class ModifyHuyaPortrait {
        public final int height;
        public final String md5;
        public Uri portrait;
        public final int width;

        public ModifyHuyaPortrait(Uri uri, int i, int i2, String str) {
            this.portrait = uri;
            this.width = i;
            this.height = i2;
            this.md5 = str;
        }
    }

    public static class ImageCollectUpload {
        public String destFileName;
        public String filePath;

        public ImageCollectUpload(String str, String str2) {
            this.filePath = str;
            this.destFileName = str2;
        }
    }
}
