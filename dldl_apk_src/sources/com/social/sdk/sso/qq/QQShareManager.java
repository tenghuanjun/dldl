package com.social.sdk.sso.qq;

import android.content.Context;
import android.os.Bundle;
import com.social.sdk.common.util.BitmapUtils;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.PlatformType;
import com.social.sdk.share.media.IShareMedia;
import com.social.sdk.share.media.ShareImageMedia;
import java.io.File;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class QQShareManager {
    public static Bundle buildShareReq(Context context, IShareMedia iShareMedia, PlatformType platformType) {
        if (platformType == PlatformType.QQ) {
            return parse(context, iShareMedia);
        }
        LogUtils.i("不支持的分享类型");
        return null;
    }

    private static Bundle parse(Context context, IShareMedia iShareMedia) {
        Bundle bundle = new Bundle();
        if (iShareMedia.category() == 0) {
            File fileQqSaveToAlbum = BitmapUtils.qqSaveToAlbum(context, ((ShareImageMedia) iShareMedia).getImage(), String.format("share_image_%s", UUID.randomUUID().toString() + ".jpg"));
            if (fileQqSaveToAlbum == null) {
                LogUtils.i("图片保存出错");
                return null;
            }
            bundle.putString("imageLocalUrl", fileQqSaveToAlbum.getPath());
            bundle.putInt("req_type", 5);
            return bundle;
        }
        LogUtils.i("不支持的分享类型");
        return null;
    }
}
