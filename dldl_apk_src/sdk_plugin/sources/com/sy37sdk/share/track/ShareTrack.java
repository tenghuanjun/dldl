package com.sy37sdk.share.track;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface ShareTrack {

    public interface ShareTrackKey {
        public static final String classify = "classify";
        public static final String message = "message";
        public static final String platform = "platform";
        public static final String skipPreview = "skipPreview";
    }

    public interface ShareTrackMsg {
        public static final String share_cancel = "分享取消";
        public static final String share_close = "关闭分享";
        public static final String share_info_null = "分享内容空";
        public static final String share_message_null = "cp传入分享内容空";
        public static final String share_not_classify = "不支持的分享种类";
        public static final String share_not_install = "未安装客户端";
        public static final String share_param_error = "没有配置分享参数";
    }
}
