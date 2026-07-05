package com.social.sdk.sso.wechat;

import android.content.Context;
import android.text.TextUtils;
import com.social.sdk.common.net.SocialHttp;
import com.social.sdk.common.net.listener.OnRequestCallBack;
import com.social.sdk.common.util.LogUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class WechatHttpUtil {
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    public static final String WeChatLoginFourth = "https://api.weixin.qq.com/sns/userinfo?access_token=";
    private static WechatHttpUtil instance;
    public static byte[] lock = new byte[0];
    private Context mContext;

    private WechatHttpUtil(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static WechatHttpUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new WechatHttpUtil(context);
                }
            }
        }
        return instance;
    }

    public void requestAccessToken(String str, String str2, final OnRequestCallBack onRequestCallBack) {
        LogUtils.i("请求access_token");
        HashMap map = new HashMap();
        map.put("grant_type", "client_credential");
        map.put("appid", str);
        map.put("secret", str2);
        SocialHttp.get(ACCESS_TOKEN_URL, map, new OnRequestCallBack() { // from class: com.social.sdk.sso.wechat.WechatHttpUtil.1
            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onSuccess(String str3) {
                LogUtils.i("请求access_token成功，返回数据：" + str3);
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    String strOptString = jSONObject.optString("access_token");
                    if (TextUtils.isEmpty(strOptString)) {
                        String str4 = "获取access_token失败  errcode: " + jSONObject.optInt("errcode") + "  errmsg: " + jSONObject.optString("errmsg");
                        LogUtils.e(str4);
                        if (onRequestCallBack != null) {
                            onRequestCallBack.onSuccess(str4);
                        }
                    } else {
                        LogUtils.e("access_token：" + strOptString);
                        WechatHttpUtil.this.requestTicket(strOptString, onRequestCallBack);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtils.e("获取access_token返回数据格式异常");
                    OnRequestCallBack onRequestCallBack2 = onRequestCallBack;
                    if (onRequestCallBack2 != null) {
                        onRequestCallBack2.onError(1, "获取access_token返回数据格式异常");
                    }
                }
            }

            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onError(int i, String str3) {
                String str4 = "RequestError 获取access_token失败  " + str3;
                LogUtils.e(str4);
                OnRequestCallBack onRequestCallBack2 = onRequestCallBack;
                if (onRequestCallBack2 != null) {
                    onRequestCallBack2.onError(i, str4);
                }
            }
        });
    }

    public void requestTicket(String str, final OnRequestCallBack onRequestCallBack) {
        HashMap map = new HashMap();
        map.put("access_token", str);
        map.put("type", "2");
        SocialHttp.get(TICKET_URL, map, new OnRequestCallBack() { // from class: com.social.sdk.sso.wechat.WechatHttpUtil.2
            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onSuccess(String str2) {
                LogUtils.i("请求ticket成功，返回数据：" + str2);
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    int iOptInt = jSONObject.optInt("errcode");
                    if (iOptInt == 0) {
                        String strOptString = jSONObject.optString("ticket");
                        LogUtils.i("tikcet: " + strOptString);
                        if (onRequestCallBack != null) {
                            onRequestCallBack.onSuccess(strOptString);
                        }
                    } else {
                        String str3 = "获取ticket失败  errcode: " + iOptInt + "  errmsg: " + jSONObject.optString("errmsg");
                        LogUtils.e(str3);
                        if (onRequestCallBack != null) {
                            onRequestCallBack.onError(iOptInt, str3);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtils.e("获取ticket返回数据格式异常");
                    OnRequestCallBack onRequestCallBack2 = onRequestCallBack;
                    if (onRequestCallBack2 != null) {
                        onRequestCallBack2.onError(1, "获取ticket返回数据格式异常");
                    }
                }
            }

            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onError(int i, String str2) {
                String str3 = "RequestError 获取ticket失败  " + str2;
                LogUtils.e(str3);
                OnRequestCallBack onRequestCallBack2 = onRequestCallBack;
                if (onRequestCallBack2 != null) {
                    onRequestCallBack2.onError(i, str3);
                }
            }
        });
    }

    public void getTicket(String str, String str2, OnRequestCallBack onRequestCallBack) {
        requestAccessToken(str, str2, onRequestCallBack);
    }

    public void requestOpenId(String str, String str2, String str3, final OnRequestCallBack onRequestCallBack) {
        HashMap map = new HashMap();
        map.put("appid", str);
        map.put("secret", str2);
        map.put("code", str3);
        map.put("grant_type", "authorization_code");
        SocialHttp.get(OPENID_URL, map, new OnRequestCallBack() { // from class: com.social.sdk.sso.wechat.WechatHttpUtil.3
            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onSuccess(String str4) {
                LogUtils.i("请求openid成功，返回数据：" + str4);
                try {
                    JSONObject jSONObject = new JSONObject(str4);
                    String strOptString = jSONObject.optString("openid");
                    if (TextUtils.isEmpty(strOptString)) {
                        int iOptInt = jSONObject.optInt("errcode");
                        String str5 = "获取openid失败  errcode: " + iOptInt + "  errmsg: " + jSONObject.optString("errmsg");
                        LogUtils.e(str5);
                        if (onRequestCallBack != null) {
                            onRequestCallBack.onError(iOptInt, str5);
                        }
                    } else if (onRequestCallBack != null) {
                        onRequestCallBack.onSuccess(strOptString);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtils.e("获取openid返回数据格式异常");
                    OnRequestCallBack onRequestCallBack2 = onRequestCallBack;
                    if (onRequestCallBack2 != null) {
                        onRequestCallBack2.onError(1, "获取openid返回数据格式异常");
                    }
                }
            }

            @Override // com.social.sdk.common.net.listener.OnRequestCallBack
            public void onError(int i, String str4) {
                String str5 = "获取openid失败:" + str4;
                LogUtils.e(str5);
                OnRequestCallBack onRequestCallBack2 = onRequestCallBack;
                if (onRequestCallBack2 != null) {
                    onRequestCallBack2.onError(i, str5);
                }
            }
        });
    }
}
