package com.sy37sdk.share.bean;

import com.social.sdk.share.media.IShareMedia;
import com.social.sdk.share.media.ShareImageMedia;
import com.social.sdk.share.media.ShareWebMedia;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareMediaParser {
    public static IShareMedia parse(ShareBean shareBean) {
        int type = shareBean.getType();
        if (type == 1) {
            ShareImageMedia shareImageMedia = new ShareImageMedia();
            shareImageMedia.setImage(shareBean.getBitmap());
            return shareImageMedia;
        }
        if (type == 2) {
            ShareWebMedia shareWebMedia = new ShareWebMedia();
            shareWebMedia.setTitle(shareBean.getTitle());
            shareWebMedia.setDescription(shareBean.getDesc());
            shareWebMedia.setWebPageUrl(shareBean.getLandingPageUrl());
            shareWebMedia.setThumb(shareBean.getBitmap());
            return shareWebMedia;
        }
        ShareImageMedia shareImageMedia2 = new ShareImageMedia();
        shareImageMedia2.setImage(shareBean.getBitmap());
        return shareImageMedia2;
    }
}
