package com.ss.android.socialbase.downloader.utils;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.nirvana.tools.crash.BuildConfig;
import com.social.sdk.common.Code;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadHttpUtils {
    public static String httpCodeToMessage(int i) {
        if (i == 449) {
            return "Retry With";
        }
        if (i == 451) {
            return "Unavailable For Legal Reasons";
        }
        if (i == 600) {
            return "Unparseable Response Headers";
        }
        if (i == 509) {
            return "Bandwidth Limit Exceeded";
        }
        if (i == 510) {
            return "Not Extended";
        }
        switch (i) {
            case 100:
                return "Continue";
            case 101:
                return "Switching Protocols";
            case 102:
                return "Processing";
            default:
                switch (i) {
                    case 200:
                        return "OK";
                    case Code.AUTHORIZE_CANCEL_CODE /* 201 */:
                        return "Created";
                    case Code.AUTHORIZE_FAILURE_CODE /* 202 */:
                        return "Accepted";
                    case BuildConfig.VERSION_CODE /* 203 */:
                        return "Non-Authoritative Information";
                    case 204:
                        return "No Content";
                    case 205:
                        return "Reset Content";
                    case 206:
                        return "Partial Content";
                    case 207:
                        return "Multi-Status";
                    default:
                        switch (i) {
                            case 300:
                                return "Multiple Choices";
                            case 301:
                                return "Moved Permanently";
                            case 302:
                                return "Move Temporarily";
                            case 303:
                                return "See Other";
                            case 304:
                                return "Not Modified";
                            case 305:
                                return "Use Proxy";
                            case 306:
                                return "Switch Proxy";
                            case StatusLine.HTTP_TEMP_REDIRECT /* 307 */:
                                return "Temporary Redirect";
                            default:
                                switch (i) {
                                    case 400:
                                        return "Bad Request";
                                    case TTAdConstant.AD_ID_IS_NULL_CODE /* 401 */:
                                        return "Unauthorized";
                                    case TTAdConstant.DEEPLINK_UNAVAILABLE_CODE /* 402 */:
                                        return "Payment Required";
                                    case TTAdConstant.DEEPLINK_FALLBACK_TYPE_ERROR_CODE /* 403 */:
                                        return "Forbidden";
                                    case TTAdConstant.SDK_NOT_SUPPORT_LIVE_MATE_CODE /* 404 */:
                                        return "Not Found";
                                    case TTAdConstant.LANDING_PAGE_TYPE_CODE /* 405 */:
                                        return "Method Not Allowed";
                                    case TTAdConstant.DOWNLOAD_APP_INFO_CODE /* 406 */:
                                        return "Not Acceptable";
                                    case TTAdConstant.DOWNLOAD_URL_CODE /* 407 */:
                                        return "Proxy Authentication Required";
                                    case TTAdConstant.INTERACTION_TYPE_CODE /* 408 */:
                                        return "Request Timeout";
                                    case TTAdConstant.IMAGE_LIST_CODE /* 409 */:
                                        return "Conflict";
                                    case TTAdConstant.IMAGE_LIST_SIZE_CODE /* 410 */:
                                        return "Gone";
                                    case TTAdConstant.IMAGE_CODE /* 411 */:
                                        return "Length Required";
                                    case TTAdConstant.IMAGE_URL_CODE /* 412 */:
                                        return "Precondition Failed";
                                    case TTAdConstant.VIDEO_INFO_CODE /* 413 */:
                                        return "Request Entity Too Large";
                                    case TTAdConstant.VIDEO_URL_CODE /* 414 */:
                                        return "Request-URI Too Long";
                                    case TTAdConstant.VIDEO_COVER_URL_CODE /* 415 */:
                                        return "Unsupported Media Type";
                                    case 416:
                                        return "Requested Range Not Satisfiable";
                                    case TTAdConstant.LIVE_FEED_URL_CODE /* 417 */:
                                        return "Expectation Failed";
                                    case TTAdConstant.DEEPLINK_FALL_BACK_CODE /* 418 */:
                                        return "I'm a teapot";
                                    default:
                                        switch (i) {
                                            case 421:
                                                return "Too Many Connections";
                                            case 422:
                                                return "Unprocessable Entity";
                                            case 423:
                                                return "Locked";
                                            case 424:
                                                return "Failed Dependency";
                                            case 425:
                                                return "Unordered Collection";
                                            case 426:
                                                return "Upgrade Required";
                                            default:
                                                switch (i) {
                                                    case TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT /* 500 */:
                                                        return "Internal Server Error";
                                                    case 501:
                                                        return "Not Implemented";
                                                    case 502:
                                                        return "Bad Gateway";
                                                    case 503:
                                                        return "Service Unavailable";
                                                    case 504:
                                                        return "Gateway Timeout";
                                                    case 505:
                                                        return "HTTP Version Not Supported";
                                                    case 506:
                                                        return "Variant Also Negotiates";
                                                    case 507:
                                                        return "Insufficient Storage";
                                                    default:
                                                        return "";
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
