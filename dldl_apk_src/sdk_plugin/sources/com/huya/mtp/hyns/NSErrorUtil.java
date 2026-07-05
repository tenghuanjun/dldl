package com.huya.mtp.hyns;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.duowan.HUYA.ErrorCode;
import com.huya.berry.sdkplayer.common.pubtext.XXBarrageParser;
import com.huya.hysignal.core.HySignalException;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.exception.CacheNotFoundError;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.DataNetworkException;
import com.huya.mtp.data.exception.JsonParseException;
import com.huya.mtp.data.exception.NoAvailableNetworkException;
import com.huya.mtp.data.exception.NoParserException;
import com.huya.mtp.data.exception.NoStrategyException;
import com.huya.mtp.data.exception.NoValidatorException;
import com.huya.mtp.data.exception.NullResponseException;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.data.exception.PermissionException;
import com.huya.mtp.data.exception.TransportException;
import com.huya.mtp.data.exception.ValidationException;
import com.huya.mtp.hyns.utils.Reflect;
import com.huya.mtp.hyns.wup.WupError;
import com.tencent.mars.stn.StnLogic;
import com.youme.voiceengine.YouMeConst;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSErrorUtil {
    private static final int WUP_ERROR_REASON_BIZ = 0;
    private static final int WUP_ERROR_REASON_SIGNAL = 1;

    public static int parseErrorType(Throwable th) {
        if (th == null) {
            return -10;
        }
        Throwable throwable = parseThrowable(th);
        if (throwable instanceof HySignalException) {
            HySignalException hySignalException = (HySignalException) throwable;
            return (hySignalException.getErrorType() == 9 && hySignalException.getErrorCode() == -4) ? 3 : 1;
        }
        if (throwable instanceof WupError) {
            return ((WupError) throwable).mCode < 0 ? 2 : 0;
        }
        return 4;
    }

    public static int parseErrorCode(Throwable th) {
        if (th == null) {
            return 998;
        }
        Throwable throwable = parseThrowable(th);
        if (throwable instanceof HySignalException) {
            HySignalException hySignalException = (HySignalException) throwable;
            printHySignalException(hySignalException);
            return hySignalException.getErrorCode();
        }
        if (throwable instanceof WupError) {
            WupError wupError = (WupError) throwable;
            parseWupErrorType(wupError);
            return wupError.mCode;
        }
        if (throwable instanceof VolleyError) {
            return 606;
        }
        if (throwable instanceof TimeoutError) {
            return 600;
        }
        if (throwable instanceof ParseError) {
            return 601;
        }
        if (throwable instanceof AuthFailureError) {
            return 602;
        }
        if (throwable instanceof NoConnectionError) {
            return 603;
        }
        if (throwable instanceof NetworkError) {
            return 604;
        }
        if (throwable instanceof DataException) {
            return 703;
        }
        if (throwable instanceof DataNetworkException) {
            return 700;
        }
        if (throwable instanceof TransportException) {
            return 701;
        }
        if (throwable instanceof NoAvailableNetworkException) {
            return 702;
        }
        if (throwable instanceof NSException) {
            return 704;
        }
        if (throwable instanceof Reflect.ReflectException) {
            return 705;
        }
        if (throwable instanceof NoParserException) {
            return 801;
        }
        if (throwable instanceof ParseException) {
            return 802;
        }
        if (throwable instanceof JsonParseException) {
            return 803;
        }
        if (throwable instanceof PermissionException) {
            return XXBarrageParser.SUPER_FAST_DURATION;
        }
        if (throwable instanceof NullResponseException) {
            return 901;
        }
        if (throwable instanceof NoValidatorException) {
            return 902;
        }
        if (throwable instanceof ValidationException) {
            return ErrorCode._EC_UNAVAILABLE;
        }
        if (throwable instanceof CacheNotFoundError) {
            return ErrorCode._EC_INVALID_ARGUMENT;
        }
        if (throwable instanceof NoStrategyException) {
            return ErrorCode._EC_NO_PRIVILEGE;
        }
        return 999;
    }

    public static String parseErrorDesc(Throwable th) {
        if (th == null) {
            return "'throwable' is null";
        }
        Throwable throwable = parseThrowable(th);
        if (throwable instanceof HySignalException) {
            return printHySignalException((HySignalException) throwable);
        }
        if (throwable instanceof WupError) {
            return parseWupErrorType((WupError) throwable);
        }
        return throwable.getClass().getName();
    }

    public static Throwable parseThrowable(Throwable th) {
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            if ((cause instanceof VolleyError) || (cause instanceof DataException) || (cause instanceof HySignalException)) {
                th = cause;
            }
        }
        return th;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0008. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String parseWupErrorType(com.huya.mtp.hyns.wup.WupError r11) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.hyns.NSErrorUtil.parseWupErrorType(com.huya.mtp.hyns.wup.WupError):java.lang.String");
    }

    private static String printHySignalException(HySignalException hySignalException) {
        String str;
        if (hySignalException == null) {
            return "hySignalException is null";
        }
        int errorCode = hySignalException.getErrorCode();
        int errorType = hySignalException.getErrorType();
        if (errorCode == -10606) {
            str = "DNS失败";
        } else if (errorCode == -10504) {
            str = "解析流消息失败";
        } else if (errorCode == -10195) {
            str = "读取HTTP响应状态码失败";
        } else if (errorCode == -10194) {
            str = "HTTP头和消息体结束不完整";
        } else if (errorCode == -10) {
            str = "信令未初始化时进行请求";
        } else if (errorCode != -9) {
            switch (errorCode) {
                case -10093:
                    str = "noop服务器超时";
                    break;
                case StnLogic.SOCKETSENDERR /* -10092 */:
                    str = "socket发送异常";
                    break;
                case StnLogic.SOCKETRECVERR /* -10091 */:
                    str = "socket接收异常";
                    break;
                case StnLogic.SOCKETSHUTDOWN /* -10090 */:
                    str = "socket关闭";
                    break;
                case StnLogic.SOCKETREADONCE /* -10089 */:
                    str = "socket读取异常";
                    break;
                case StnLogic.SOCKETWRITENWITHNONBLOCK /* -10088 */:
                    str = "socket非阻塞写网络异常";
                    break;
                case StnLogic.SOCKETMAKESOCKETPREPARED /* -10087 */:
                    str = "socket就绪异常";
                    break;
                case StnLogic.SOCKETNETWORKCHANGE /* -10086 */:
                    str = "socket网络状态变化";
                    break;
                default:
                    switch (errorCode) {
                        case StnLogic.TASKTIMEOUT /* -503 */:
                            str = "长/短连接任务超时";
                            break;
                        case StnLogic.READWRITETIMEOUT /* -502 */:
                            str = "长/短连接读写网络超时";
                            break;
                        case -501:
                            str = "长/短连接接收包超时";
                            break;
                        case StnLogic.FIRSTPKGTIMEOUT /* -500 */:
                            str = "长/短连接首包异常";
                            break;
                        default:
                            switch (errorCode) {
                                case -14:
                                    str = "任务channel_id异常";
                                    break;
                                case YouMeConst.YouMeErrorCode.YOUME_ERROR_BE_KICK /* -13 */:
                                    str = "任务在一段时间内达到cgi频率限制阈值";
                                    break;
                                case YouMeConst.YouMeErrorCode.YOUME_ERROR_NOT_IN_CHANNEL /* -12 */:
                                    str = "任务参数配置错误";
                                    break;
                                default:
                                    switch (errorCode) {
                                        case -7:
                                            str = "本地任务被取消";
                                            break;
                                        case -6:
                                            str = "网络不可用";
                                            break;
                                        case -5:
                                            str = "任务通道选择错误";
                                            break;
                                        case -4:
                                            str = "本地防雪崩被触发";
                                            break;
                                        case -3:
                                            str = "本地开始任务失败";
                                            break;
                                        case -2:
                                            str = "本地任务重试";
                                            break;
                                        case -1:
                                            str = "本地任务超时";
                                            break;
                                        default:
                                            str = "unknown";
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            str = "任务管理器被重置";
        }
        MTPApi.LOGGER.error(NSConstants.NSTAG, "【%s-mars通道错误】:错误类型:%d, 错误码:%d, 信息：%s", NSInnerConfig.getInstance().getEnvStr(), Integer.valueOf(errorType), Integer.valueOf(errorCode), str);
        return "mars错误, 类型:" + errorType + ", 错误码:" + errorCode + ", 描述: " + str;
    }
}
