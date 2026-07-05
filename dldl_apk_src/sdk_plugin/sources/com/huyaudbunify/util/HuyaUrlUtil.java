package com.huyaudbunify.util;

import com.huya.berry.webview.WebViewHelper;
import com.huya.security.DeviceFingerprintSDK;
import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ResGetTicket;
import com.huyaudbunify.msg.MsgAnonyLoginCred;
import com.huyaudbunify.msg.MsgAuthScanQr;
import com.huyaudbunify.msg.MsgBindAuth;
import com.huyaudbunify.msg.MsgBindChangePhone;
import com.huyaudbunify.msg.MsgBindLoginEmail;
import com.huyaudbunify.msg.MsgBindNewSendSms;
import com.huyaudbunify.msg.MsgBindNewVerifySms;
import com.huyaudbunify.msg.MsgBindScanQr;
import com.huyaudbunify.msg.MsgBindSendSms;
import com.huyaudbunify.msg.MsgBindVerifySms;
import com.huyaudbunify.msg.MsgCPCode;
import com.huyaudbunify.msg.MsgCPSendSms;
import com.huyaudbunify.msg.MsgCPToken;
import com.huyaudbunify.msg.MsgCPVerifySms;
import com.huyaudbunify.msg.MsgCancleAuthLogin;
import com.huyaudbunify.msg.MsgCancleQrLogin;
import com.huyaudbunify.msg.MsgCheckRegMobile;
import com.huyaudbunify.msg.MsgCheckUserCP;
import com.huyaudbunify.msg.MsgCheckUserFP;
import com.huyaudbunify.msg.MsgEmailLogin;
import com.huyaudbunify.msg.MsgFPCode;
import com.huyaudbunify.msg.MsgFPSendSms;
import com.huyaudbunify.msg.MsgFPToken;
import com.huyaudbunify.msg.MsgFPVerifySms;
import com.huyaudbunify.msg.MsgGetBindList;
import com.huyaudbunify.msg.MsgGetByPass;
import com.huyaudbunify.msg.MsgGetUserStatus;
import com.huyaudbunify.msg.MsgLogin;
import com.huyaudbunify.msg.MsgLoginAnonymous;
import com.huyaudbunify.msg.MsgLoginAntiViolent;
import com.huyaudbunify.msg.MsgLoginCred;
import com.huyaudbunify.msg.MsgLoginGuest;
import com.huyaudbunify.msg.MsgLoginMobileQuick;
import com.huyaudbunify.msg.MsgLoginPhoneSms;
import com.huyaudbunify.msg.MsgLoginRefreshPic;
import com.huyaudbunify.msg.MsgLoginSecondAuth;
import com.huyaudbunify.msg.MsgLoginSendMobileSms;
import com.huyaudbunify.msg.MsgLoginSessionSendSms;
import com.huyaudbunify.msg.MsgLoginThird;
import com.huyaudbunify.msg.MsgNotifyScanCode;
import com.huyaudbunify.msg.MsgRegisterPhoneCode;
import com.huyaudbunify.msg.MsgRegisterPhoneToken;
import com.huyaudbunify.msg.MsgRegisterSendSms;
import com.huyaudbunify.msg.MsgRegisterVerifySms;
import com.huyaudbunify.msg.MsgReport;
import com.huyaudbunify.msg.MsgReportCache;
import com.huyaudbunify.msg.MsgSendBindEmailCode;
import com.huyaudbunify.msg.MsgUnBindAuth;
import com.huyaudbunify.msg.MsgUnBindSendSms;
import com.huyaudbunify.msg.MsgUnBindVerifySms;
import com.huyaudbunify.msg.MsgUpdateCred;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaUrlUtil {
    public static String constServName;
    public static String constUrlGetPass;
    public static String constUrlGetPassDev;
    public static String constUrlLgn;
    public static String constUrlLgnDev;
    public static String constUrlLgnThird;
    public static String constUrlLgnThirdDev;
    public static String constUrlLog;
    public static String constUrlLogDev;
    public static String constUrlReg;
    public static String constUrlRegDev;

    static {
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            constServName = "huyaudbwebui";
            constUrlGetPass = "https://udbapi.huya.com";
            constUrlGetPassDev = "http://udbapi-test.huya.com";
            constUrlReg = "https://udbreg.huya.com";
            constUrlRegDev = "http://udbreg-test.huya.com";
            constUrlLgn = "https://udblgn.huya.com";
            constUrlLgnDev = "http://udblgn-test.huya.com";
            constUrlLog = "http://udblog.huya.com";
            constUrlLogDev = "http://udblog.huya.com";
            constUrlLgnThird = "https://udb3lgn.huya.com";
            constUrlLgnThirdDev = "http://udb3lgn-test.huya.com";
            return;
        }
        constServName = "huyaudbindependentwebui";
        constUrlGetPass = "https://udbapi.nimo.com";
        constUrlGetPassDev = "http://udbapi-test.nimo.com";
        constUrlReg = "https://udbreg.nimo.tv";
        constUrlRegDev = "http://udbreg-test.nimo.tv";
        constUrlLgn = "https://udblgn.nimo.tv";
        constUrlLgnDev = "http://udblgn-test.nimo.tv";
        constUrlLog = "http://udblog.nimo.tv";
        constUrlLogDev = "http://udblog-test.nimo.tv";
        constUrlLgnThird = "https://udb3lgn.nimo.tv";
        constUrlLgnThirdDev = "http://udb3lgn-test.nimo.tv";
    }

    public static String getUrl(long j) {
        if (MsgLogin.mMsgId == j) {
            return MsgLogin.getUrl();
        }
        if (MsgLoginAntiViolent.mMsgId == j) {
            return MsgLoginAntiViolent.getUrl();
        }
        if (MsgLoginRefreshPic.mMsgId == j) {
            return MsgLoginRefreshPic.getUrl();
        }
        if (MsgLoginSendMobileSms.mMsgId == j) {
            return MsgLoginSendMobileSms.getUrl();
        }
        if (MsgLoginPhoneSms.mMsgId == j) {
            return MsgLoginPhoneSms.getUrl();
        }
        if (MsgLoginCred.mMsgId == j) {
            return MsgLoginCred.getUrl();
        }
        if (MsgLoginSessionSendSms.mMsgId == j) {
            return MsgLoginSessionSendSms.getUrl();
        }
        if (MsgLoginSecondAuth.mMsgId == j) {
            return MsgLoginSecondAuth.getUrl();
        }
        if (MsgRegisterSendSms.mMsgId == j) {
            return MsgRegisterSendSms.getUrl();
        }
        if (MsgRegisterVerifySms.mMsgId == j) {
            return MsgRegisterVerifySms.getUrl();
        }
        if (MsgRegisterPhoneCode.mMsgId == j) {
            return MsgRegisterPhoneCode.getUrl();
        }
        if (MsgRegisterPhoneToken.mMsgId == j) {
            return MsgRegisterPhoneToken.getUrl();
        }
        if (MsgFPSendSms.mMsgId == j) {
            return MsgFPSendSms.getUrl();
        }
        if (MsgFPVerifySms.mMsgId == j) {
            return MsgFPVerifySms.getUrl();
        }
        if (MsgFPToken.mMsgId == j) {
            return MsgFPToken.getUrl();
        }
        if (MsgFPCode.mMsgId == j) {
            return MsgFPCode.getUrl();
        }
        if (MsgCPSendSms.mMsgId == j) {
            return MsgCPSendSms.getUrl();
        }
        if (MsgCPVerifySms.mMsgId == j) {
            return MsgCPVerifySms.getUrl();
        }
        if (MsgCPToken.mMsgId == j) {
            return MsgCPToken.getUrl();
        }
        if (MsgCPCode.mMsgId == j) {
            return MsgCPCode.getUrl();
        }
        if (MsgCheckUserCP.mMsgId == j) {
            return MsgCheckUserCP.getUrl();
        }
        if (MsgCheckUserFP.mMsgId == j) {
            return MsgCheckUserFP.getUrl();
        }
        if (MsgCheckRegMobile.mMsgId == j) {
            return MsgCheckRegMobile.getUrl();
        }
        if (MsgGetByPass.mMsgId == j) {
            return MsgGetByPass.getUrl();
        }
        if (MsgUpdateCred.mMsgId == j) {
            return MsgUpdateCred.getUrl();
        }
        if (MsgReport.mMsgId == j) {
            return MsgReport.getUrl();
        }
        if (MsgReportCache.mMsgId == j) {
            return MsgReportCache.getUrl();
        }
        if (MsgLoginThird.mMsgId == j) {
            return MsgLoginThird.getUrl();
        }
        if (MsgNotifyScanCode.mMsgId == j) {
            return MsgNotifyScanCode.getUrl();
        }
        if (MsgBindScanQr.mMsgId == j) {
            return MsgBindScanQr.getUrl();
        }
        if (MsgCancleQrLogin.mMsgId == j) {
            return MsgCancleQrLogin.getUrl();
        }
        if (MsgAuthScanQr.mMsgId == j) {
            return MsgAuthScanQr.getUrl();
        }
        if (MsgCancleAuthLogin.mMsgId == j) {
            return MsgCancleAuthLogin.getUrl();
        }
        if (MsgLoginAnonymous.mMsgId == j) {
            return MsgLoginAnonymous.getUrl();
        }
        if (MsgLoginMobileQuick.mMsgId == j) {
            return MsgLoginMobileQuick.getUrl();
        }
        if (MsgBindSendSms.mMsgId == j) {
            return MsgBindSendSms.getUrl();
        }
        if (MsgBindVerifySms.mMsgId == j) {
            return MsgBindVerifySms.getUrl();
        }
        if (MsgBindChangePhone.mMsgId == j) {
            return MsgBindChangePhone.getUrl();
        }
        if (MsgUnBindSendSms.mMsgId == j) {
            return MsgUnBindSendSms.getUrl();
        }
        if (MsgUnBindVerifySms.mMsgId == j) {
            return MsgUnBindVerifySms.getUrl();
        }
        if (MsgBindNewSendSms.mMsgId == j) {
            return MsgBindNewSendSms.getUrl();
        }
        if (MsgBindNewVerifySms.mMsgId == j) {
            return MsgBindNewVerifySms.getUrl();
        }
        if (MsgBindVerifySms.mMsgId == j) {
            return MsgBindVerifySms.getUrl();
        }
        if (MsgUnBindSendSms.mMsgId == j) {
            return MsgUnBindSendSms.getUrl();
        }
        if (MsgUnBindVerifySms.mMsgId == j) {
            return MsgUnBindVerifySms.getUrl();
        }
        if (MsgBindNewSendSms.mMsgId == j) {
            return MsgBindNewSendSms.getUrl();
        }
        if (MsgBindNewVerifySms.mMsgId == j) {
            return MsgBindNewVerifySms.getUrl();
        }
        if (MsgLoginGuest.mMsgId == j) {
            return MsgLoginGuest.getUrl();
        }
        if (MsgBindAuth.mMsgId == j) {
            return MsgBindAuth.getUrl();
        }
        if (MsgGetBindList.mMsgId == j) {
            return MsgGetBindList.getUrl();
        }
        if (MsgUnBindAuth.mMsgId == j) {
            return MsgUnBindAuth.getUrl();
        }
        if (MsgGetUserStatus.mMsgId == j) {
            return MsgGetUserStatus.getUrl();
        }
        if (MsgAnonyLoginCred.mMsgId == j) {
            return MsgAnonyLoginCred.getUrl();
        }
        if (MsgSendBindEmailCode.mMsgId == j) {
            return MsgSendBindEmailCode.getUrl();
        }
        if (MsgBindLoginEmail.mMsgId == j) {
            return MsgBindLoginEmail.getUrl();
        }
        return MsgEmailLogin.mMsgId == j ? MsgEmailLogin.getUrl() : "";
    }

    public static String getAuthUrl() {
        return HuyaAuth.getInstance().isDeveloper() ? "http://aq-test.huya.com/m/safe_redirect/safe_redirect.html?hideShareButton=1&allowRefresh=0" : "https://aq.huya.com/m/safe_redirect/safe_redirect.html?hideShareButton=1&allowRefresh=0";
    }

    public static String getUpgradeUrl(long j) {
        String str = "https://aq.huya.com/p/upgrade/upgrade_index.html?uid=" + j;
        if (!HuyaAuth.getInstance().isDeveloper()) {
            return str;
        }
        return "https://aq-test.huya.com/p/upgrade/upgrade_index.html?uid=" + j;
    }

    public static String getQUrlLink() {
        String str = "https://udblgn.huya.com/qrClient.html?appid=" + HuyaDeveloperUtils.getInstance().getAppId();
        if (!HuyaAuth.getInstance().isDeveloper()) {
            return str;
        }
        return "http://udblgn-test.huya.com/qrClient.html?appid=" + HuyaDeveloperUtils.getInstance().getAppId();
    }

    public static String getH5UrlRegister() {
        if (HuyaAuth.getInstance().isDeveloper()) {
            return "http://aq-test.huya.com/m/register.html?time=" + System.currentTimeMillis() + "&appid=" + HuyaDeveloperUtils.getInstance().getAppId();
        }
        return "https://aq.huya.com/m/register.html?time=" + System.currentTimeMillis() + "&appid=" + HuyaDeveloperUtils.getInstance().getAppId();
    }

    public static String getH5UrlFindPassword() {
        if (HuyaAuth.getInstance().isDeveloper()) {
            return "http://aq-test.huya.com/m/find.html?time=" + System.currentTimeMillis() + "&appid=" + HuyaDeveloperUtils.getInstance().getAppId();
        }
        return "https://aq.huya.com/m/find.html?time=" + System.currentTimeMillis() + "&appid=" + HuyaDeveloperUtils.getInstance().getAppId();
    }

    public static String getH5UrlModifyPassword() {
        if (HuyaAuth.getInstance().isDeveloper()) {
            return "http://aq-test.huya.com/m/modify.html?time=" + System.currentTimeMillis() + "&appid=" + HuyaDeveloperUtils.getInstance().getAppId();
        }
        return "https://aq.huya.com/m/modify.html?time=" + System.currentTimeMillis() + "&appid=" + HuyaDeveloperUtils.getInstance().getAppId();
    }

    public static String getHyBindMobileUrl() {
        return HuyaAuth.getInstance().isDeveloper() ? "http://aq-test.huya.com/m/bind/bind_tel.html" : "https://aq.huya.com/m/bind/bind_tel.html";
    }

    public static String getHyChangeBindMobileUrl() {
        return HuyaAuth.getInstance().isDeveloper() ? "http://aq-test.huya.com/m/bind/yes_bind_tel.html" : "https://aq.huya.com/m/bind/yes_bind_tel.html";
    }

    public static String getMigrateUrl(String str) {
        return str.contains("hymigrate") ? getUrlFromDes(str) : "";
    }

    public static String getUrlFromDes(String str) {
        Matcher matcher = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))", 2).matcher(str);
        return matcher.find() ? matcher.group().replaceAll("href\\s*=\\s*(['|\"]*)", "").replaceAll("['|\"]", "").replaceAll(" ", "") : "";
    }

    public static String getHyWeiguiUrl() {
        String str = HuyaAuth.getInstance().isDeveloper() ? "http://test.hd.huya.com/h5/violation_appeals/index.html" : "https://hd.huya.com/h5/violation_appeals/index.html";
        return HuyaAccountSaveUtils.getInstance().isLogin() ? getHyLgnJumpUrl(WebViewHelper.BASE_JUMP_SERVICE, "", str, "huya.com") : str;
    }

    public static String getHYBusinessUrlParam(String str, String str2, String str3) {
        String strEncode;
        int i;
        int tokenType;
        ResGetTicket defaultToken = HuyaAuth.getInstance().getDefaultToken();
        String strEncode2 = "";
        if (defaultToken != null) {
            try {
                strEncode = URLEncoder.encode(defaultToken.getToken(), "UTF-8");
            } catch (Exception e) {
                e = e;
                strEncode = "";
            }
            try {
                tokenType = defaultToken.getTokenType();
            } catch (Exception e2) {
                e = e2;
                i = 2;
                e.printStackTrace();
                tokenType = i;
                return String.format("uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&huyalgn=1&version=%s&client_ua=%s&passport=%s&bypass=%d", String.valueOf(HuyaAccountSaveUtils.getInstance().getUid()), HuyaDeveloperUtils.getInstance().getAppId(), strEncode, Integer.valueOf(tokenType), str, str2, str3, strEncode2, Integer.valueOf(HuyaAccountSaveUtils.getInstance().getType().getType()));
            }
        } else {
            strEncode = "";
            tokenType = 2;
        }
        try {
            strEncode2 = URLEncoder.encode(HuyaAccountSaveUtils.getInstance().getPassport(), "UTF-8");
        } catch (Exception e3) {
            i = tokenType;
            e = e3;
            e.printStackTrace();
            tokenType = i;
        }
        return String.format("uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&huyalgn=1&version=%s&client_ua=%s&passport=%s&bypass=%d", String.valueOf(HuyaAccountSaveUtils.getInstance().getUid()), HuyaDeveloperUtils.getInstance().getAppId(), strEncode, Integer.valueOf(tokenType), str, str2, str3, strEncode2, Integer.valueOf(HuyaAccountSaveUtils.getInstance().getType().getType()));
    }

    public static String getHyLgnJumpUrl(String str, String str2, String str3, String str4) {
        String strEncode;
        String strEncode2;
        String strEncode3;
        int tokenType;
        String strEncode4 = "";
        String str5 = HuyaAuth.getInstance().isDeveloper() ? "http://udblgn-test.huya.com/login/ticket?uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&cks=true&busiUrl=%s&reqDomainList=%s&passport=%s&bypass=%d&sdid=%s" : "https://udblgn.huya.com/login/ticket?uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&cks=true&busiUrl=%s&reqDomainList=%s&passport=%s&bypass=%d&sdid=%s";
        ResGetTicket defaultToken = HuyaAuth.getInstance().getDefaultToken();
        try {
            strEncode = URLEncoder.encode(str3, "UTF-8");
            if (defaultToken != null) {
                try {
                    strEncode2 = URLEncoder.encode(defaultToken.getToken(), "UTF-8");
                    try {
                        tokenType = defaultToken.getTokenType();
                    } catch (Exception e) {
                        e = e;
                        strEncode3 = "";
                        tokenType = 2;
                        e.printStackTrace();
                        return String.format(str5, String.valueOf(HuyaAccountSaveUtils.getInstance().getUid()), HuyaDeveloperUtils.getInstance().getAppId(), strEncode2, Integer.valueOf(tokenType), str2, strEncode, str4, strEncode3, Integer.valueOf(HuyaAccountSaveUtils.getInstance().getType().getType()), strEncode4);
                    }
                } catch (Exception e2) {
                    e = e2;
                    strEncode2 = "";
                    strEncode3 = strEncode2;
                    tokenType = 2;
                    e.printStackTrace();
                    return String.format(str5, String.valueOf(HuyaAccountSaveUtils.getInstance().getUid()), HuyaDeveloperUtils.getInstance().getAppId(), strEncode2, Integer.valueOf(tokenType), str2, strEncode, str4, strEncode3, Integer.valueOf(HuyaAccountSaveUtils.getInstance().getType().getType()), strEncode4);
                }
            } else {
                strEncode2 = "";
                tokenType = 2;
            }
            try {
                strEncode3 = URLEncoder.encode(HuyaAccountSaveUtils.getInstance().getPassport(), "UTF-8");
                try {
                    strEncode4 = URLEncoder.encode(DeviceFingerprintSDK.getInstance().getSDID(), "UTF-8");
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                }
            } catch (Exception e4) {
                e = e4;
                strEncode3 = "";
            }
        } catch (Exception e5) {
            e = e5;
            strEncode = "";
            strEncode2 = strEncode;
        }
        return String.format(str5, String.valueOf(HuyaAccountSaveUtils.getInstance().getUid()), HuyaDeveloperUtils.getInstance().getAppId(), strEncode2, Integer.valueOf(tokenType), str2, strEncode, str4, strEncode3, Integer.valueOf(HuyaAccountSaveUtils.getInstance().getType().getType()), strEncode4);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getHYBusinessUrl(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            com.huyaudbunify.HuyaAuth r0 = com.huyaudbunify.HuyaAuth.getInstance()
            com.huyaudbunify.bean.ResGetTicket r0 = r0.getDefaultToken()
            java.lang.String r1 = "UTF-8"
            r2 = 2
            java.lang.String r3 = ""
            if (r0 == 0) goto L22
            java.lang.String r4 = r0.getToken()     // Catch: java.lang.Exception -> L1e
            java.lang.String r4 = java.net.URLEncoder.encode(r4, r1)     // Catch: java.lang.Exception -> L1e
            int r0 = r0.getTokenType()     // Catch: java.lang.Exception -> L1c
            goto L24
        L1c:
            r0 = move-exception
            goto L20
        L1e:
            r0 = move-exception
            r4 = r3
        L20:
            r1 = 2
            goto L35
        L22:
            r4 = r3
            r0 = 2
        L24:
            com.huyaudbunify.util.HuyaAccountSaveUtils r5 = com.huyaudbunify.util.HuyaAccountSaveUtils.getInstance()     // Catch: java.lang.Exception -> L31
            java.lang.String r5 = r5.getPassport()     // Catch: java.lang.Exception -> L31
            java.lang.String r3 = java.net.URLEncoder.encode(r5, r1)     // Catch: java.lang.Exception -> L31
            goto L39
        L31:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
        L35:
            r0.printStackTrace()
            r0 = r1
        L39:
            com.huyaudbunify.util.HuyaAccountSaveUtils r1 = com.huyaudbunify.util.HuyaAccountSaveUtils.getInstance()
            long r5 = r1.getUid()
            java.lang.String r1 = java.lang.String.valueOf(r5)
            r5 = 9
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r1
            r1 = 1
            com.huyaudbunify.util.HuyaDeveloperUtils r6 = com.huyaudbunify.util.HuyaDeveloperUtils.getInstance()
            java.lang.String r6 = r6.getAppId()
            r5[r1] = r6
            r5[r2] = r4
            r1 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r1] = r0
            r0 = 4
            r5[r0] = r9
            r9 = 5
            r5[r9] = r10
            r9 = 6
            r5[r9] = r11
            r9 = 7
            r5[r9] = r3
            r9 = 8
            com.huyaudbunify.util.HuyaAccountSaveUtils r10 = com.huyaudbunify.util.HuyaAccountSaveUtils.getInstance()
            com.huyaudbunify.account.SdkComType r10 = r10.getType()
            int r10 = r10.getType()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r5[r9] = r10
            java.lang.String r9 = "uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&huyalgn=1&version=%s&client_ua=%s&passport=%s&bypass=%d"
            java.lang.String r9 = java.lang.String.format(r9, r5)
            java.lang.String r10 = "?"
            boolean r11 = r8.contains(r10)
            if (r11 == 0) goto La3
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r8)
            java.lang.String r8 = "&"
            r10.append(r8)
            r10.append(r9)
            java.lang.String r8 = r10.toString()
            goto Lb5
        La3:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r8)
            r11.append(r10)
            r11.append(r9)
            java.lang.String r8 = r11.toString()
        Lb5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huyaudbunify.util.HuyaUrlUtil.getHYBusinessUrl(java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String getHyLgnJumpUrl(String str, long j, String str2, String str3, String str4, String str5) {
        String strEncode;
        String strEncode2;
        int tokenType;
        String strEncode3 = "";
        String str6 = HuyaDeveloperUtils.getInstance().isDeveloper() ? "http://udblgn-test.huya.com/login/ticket?uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&cks=true&busiUrl=%s&reqDomainList=%s&passport=%s&bypass=%d&sdid=%s" : "https://udblgn.huya.com/login/ticket?uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&cks=true&busiUrl=%s&reqDomainList=%s&passport=%s&bypass=%d&sdid=%s";
        ResGetTicket defaultToken = HuyaAuth.getInstance().getDefaultToken();
        try {
            strEncode = URLEncoder.encode(str4, "UTF-8");
            if (defaultToken != null) {
                try {
                    strEncode2 = URLEncoder.encode(defaultToken.getToken(), "UTF-8");
                    try {
                        tokenType = defaultToken.getTokenType();
                    } catch (Exception e) {
                        e = e;
                        tokenType = 2;
                        e.printStackTrace();
                        return String.format(str6, String.valueOf(j), HuyaDeveloperUtils.getInstance().getAppId(), strEncode2, Integer.valueOf(tokenType), str3, strEncode, str5, str2, 3, strEncode3);
                    }
                } catch (Exception e2) {
                    e = e2;
                    strEncode2 = "";
                }
            } else {
                strEncode2 = "";
                tokenType = 2;
            }
        } catch (Exception e3) {
            e = e3;
            strEncode = "";
            strEncode2 = strEncode;
        }
        try {
            strEncode3 = URLEncoder.encode(DeviceFingerprintSDK.getInstance().getSDID(), "UTF-8");
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
        }
        return String.format(str6, String.valueOf(j), HuyaDeveloperUtils.getInstance().getAppId(), strEncode2, Integer.valueOf(tokenType), str3, strEncode, str5, str2, 3, strEncode3);
    }

    public static String getHYBusinessUrlParam(long j, String str, String str2, String str3, String str4) {
        int tokenType;
        ResGetTicket defaultToken = HuyaAuth.getInstance().getDefaultToken();
        String strEncode = "";
        if (defaultToken != null) {
            try {
                strEncode = URLEncoder.encode(defaultToken.getToken(), "UTF-8");
                tokenType = defaultToken.getTokenType();
            } catch (Exception e) {
                e.printStackTrace();
                tokenType = 2;
            }
        } else {
            tokenType = 2;
        }
        return String.format("uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&huyalgn=1&version=%s&client_ua=%s&passport=%s&bypass=%d", String.valueOf(j), HuyaDeveloperUtils.getInstance().getAppId(), strEncode, Integer.valueOf(tokenType), str2, str3, str4, str, 3);
    }

    public static String getHYBusinessUrl(String str, long j, String str2, String str3, String str4, String str5) {
        int tokenType;
        ResGetTicket defaultToken = HuyaAuth.getInstance().getDefaultToken();
        String strEncode = "";
        if (defaultToken != null) {
            try {
                strEncode = URLEncoder.encode(defaultToken.getToken(), "UTF-8");
                tokenType = defaultToken.getTokenType();
            } catch (Exception e) {
                e.printStackTrace();
                tokenType = 2;
            }
        } else {
            tokenType = 2;
        }
        String str6 = String.format("uid=%s&appid=%s&ticket=%s&ticketType=%d&busiId=%s&huyalgn=1&version=%s&client_ua=%s&passport=%s&bypass=%d", String.valueOf(j), HuyaDeveloperUtils.getInstance().getAppId(), strEncode, Integer.valueOf(tokenType), str3, str4, str5, str2, 3);
        if (str.contains("?")) {
            return str + "&" + str6;
        }
        return str + "?" + str6;
    }
}
