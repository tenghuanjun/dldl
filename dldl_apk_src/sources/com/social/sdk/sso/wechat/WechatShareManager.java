package com.social.sdk.sso.wechat;

import android.graphics.Bitmap;
import com.social.sdk.common.util.BitmapUtils;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.PlatformType;
import com.social.sdk.share.media.IShareMedia;
import com.social.sdk.share.media.ShareImageMedia;
import com.social.sdk.share.media.ShareWebMedia;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class WechatShareManager {
    WechatShareManager() {
    }

    public static SendMessageToWX.Req buildReq(IShareMedia iShareMedia, PlatformType platformType) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = parse(iShareMedia);
        if (platformType == PlatformType.WECHAT) {
            req.scene = 0;
        } else if (platformType == PlatformType.WECHAT_CIRCLE) {
            req.scene = 1;
        }
        return req;
    }

    private static WXMediaMessage parse(IShareMedia iShareMedia) {
        Bitmap image;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        int iCategory = iShareMedia.category();
        if (iCategory == 0) {
            ShareImageMedia shareImageMedia = (ShareImageMedia) iShareMedia;
            WXImageObject wXImageObject = new WXImageObject(shareImageMedia.getImage());
            image = shareImageMedia.getImage();
            wXMediaMessage.mediaObject = wXImageObject;
        } else if (iCategory == 1) {
            ShareWebMedia shareWebMedia = (ShareWebMedia) iShareMedia;
            WXWebpageObject wXWebpageObject = new WXWebpageObject();
            wXWebpageObject.webpageUrl = shareWebMedia.getWebPageUrl();
            wXMediaMessage.mediaObject = wXWebpageObject;
            wXMediaMessage.title = shareWebMedia.getTitle();
            wXMediaMessage.description = shareWebMedia.getDescription();
            image = shareWebMedia.getThumb();
        } else {
            LogUtils.i("不支持的分享类型");
            return null;
        }
        if (image != null) {
            int height = image.getHeight();
            int width = image.getWidth();
            LogUtils.i("压缩之前原图大小：width=" + width + " height=" + height);
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(image, 150, (height * 150) / width, true);
            image.recycle();
            wXMediaMessage.thumbData = BitmapUtils.compressBitmap(bitmapCreateScaledBitmap, 32768);
            if (wXMediaMessage.thumbData != null) {
                LogUtils.i("压缩后缩略图大小为：" + (wXMediaMessage.thumbData.length / 1024) + "kb");
            } else {
                LogUtils.i("压缩失败");
            }
        }
        return wXMediaMessage;
    }
}
