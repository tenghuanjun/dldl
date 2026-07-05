package com.huyaudbunify.util;

import com.huyaudbunify.msg.MsgAnonyLoginCred;
import com.huyaudbunify.msg.MsgAuthScanQr;
import com.huyaudbunify.msg.MsgBindLoginEmail;
import com.huyaudbunify.msg.MsgBindScanQr;
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
import com.huyaudbunify.msg.MsgGetByPass;
import com.huyaudbunify.msg.MsgGetUserStatus;
import com.huyaudbunify.msg.MsgLogin;
import com.huyaudbunify.msg.MsgLoginAnonymous;
import com.huyaudbunify.msg.MsgLoginAntiViolent;
import com.huyaudbunify.msg.MsgLoginCred;
import com.huyaudbunify.msg.MsgLoginMobileQuick;
import com.huyaudbunify.msg.MsgLoginPhoneSms;
import com.huyaudbunify.msg.MsgLoginRefreshPic;
import com.huyaudbunify.msg.MsgLoginSecondAuth;
import com.huyaudbunify.msg.MsgLoginSendMobileSms;
import com.huyaudbunify.msg.MsgLoginSessionSendSms;
import com.huyaudbunify.msg.MsgLoginThird;
import com.huyaudbunify.msg.MsgNotifyScanCode;
import com.huyaudbunify.msg.MsgOtherAppCredLogin;
import com.huyaudbunify.msg.MsgRegisterPhoneCode;
import com.huyaudbunify.msg.MsgRegisterPhoneToken;
import com.huyaudbunify.msg.MsgRegisterSendSms;
import com.huyaudbunify.msg.MsgRegisterVerifySms;
import com.huyaudbunify.msg.MsgReport;
import com.huyaudbunify.msg.MsgReportCache;
import com.huyaudbunify.msg.MsgSendBindEmailCode;
import com.huyaudbunify.msg.MsgUnBindAuth;
import com.huyaudbunify.msg.MsgUpdateCred;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaMarsCgiUtil {
    public static String getCgi(long j) {
        if (MsgLogin.mMsgId == j) {
            return MsgLogin.getCgi();
        }
        if (MsgLoginAntiViolent.mMsgId == j) {
            return MsgLoginAntiViolent.getCgi();
        }
        if (MsgLoginRefreshPic.mMsgId == j) {
            return MsgLoginRefreshPic.getCgi();
        }
        if (MsgLoginSendMobileSms.mMsgId == j) {
            return MsgLoginSendMobileSms.getCgi();
        }
        if (MsgLoginPhoneSms.mMsgId == j) {
            return MsgLoginPhoneSms.getCgi();
        }
        if (MsgLoginCred.mMsgId == j) {
            return MsgLoginCred.getCgi();
        }
        if (MsgLoginSessionSendSms.mMsgId == j) {
            return MsgLoginSessionSendSms.getCgi();
        }
        if (MsgLoginSecondAuth.mMsgId == j) {
            return MsgLoginSecondAuth.getCgi();
        }
        if (MsgRegisterSendSms.mMsgId == j) {
            return MsgRegisterSendSms.getCgi();
        }
        if (MsgRegisterVerifySms.mMsgId == j) {
            return MsgRegisterVerifySms.getCgi();
        }
        if (MsgRegisterPhoneCode.mMsgId == j) {
            return MsgRegisterPhoneCode.getCgi();
        }
        if (MsgRegisterPhoneToken.mMsgId == j) {
            return MsgRegisterPhoneToken.getCgi();
        }
        if (MsgFPSendSms.mMsgId == j) {
            return MsgFPSendSms.getCgi();
        }
        if (MsgFPVerifySms.mMsgId == j) {
            return MsgFPVerifySms.getCgi();
        }
        if (MsgFPToken.mMsgId == j) {
            return MsgFPToken.getCgi();
        }
        if (MsgFPCode.mMsgId == j) {
            return MsgFPCode.getCgi();
        }
        if (MsgCPSendSms.mMsgId == j) {
            return MsgCPSendSms.getCgi();
        }
        if (MsgCPVerifySms.mMsgId == j) {
            return MsgCPVerifySms.getCgi();
        }
        if (MsgCPToken.mMsgId == j) {
            return MsgCPToken.getCgi();
        }
        if (MsgCPCode.mMsgId == j) {
            return MsgCPCode.getCgi();
        }
        if (MsgCheckUserCP.mMsgId == j) {
            return MsgCheckUserCP.getCgi();
        }
        if (MsgCheckUserFP.mMsgId == j) {
            return MsgCheckUserFP.getCgi();
        }
        if (MsgCheckRegMobile.mMsgId == j) {
            return MsgCheckRegMobile.getCgi();
        }
        if (MsgGetByPass.mMsgId == j) {
            return MsgGetByPass.getCgi();
        }
        if (MsgUpdateCred.mMsgId == j) {
            return MsgUpdateCred.getCgi();
        }
        if (MsgReport.mMsgId == j) {
            return MsgReport.getCgi();
        }
        if (MsgReportCache.mMsgId == j) {
            return MsgReportCache.getCgi();
        }
        if (MsgLoginThird.mMsgId == j) {
            return MsgLoginThird.getCgi();
        }
        if (MsgNotifyScanCode.mMsgId == j) {
            return MsgNotifyScanCode.getCgi();
        }
        if (MsgBindScanQr.mMsgId == j) {
            return MsgBindScanQr.getCgi();
        }
        if (MsgCancleQrLogin.mMsgId == j) {
            return MsgCancleQrLogin.getCgi();
        }
        if (MsgAuthScanQr.mMsgId == j) {
            return MsgAuthScanQr.getCgi();
        }
        if (MsgCancleAuthLogin.mMsgId == j) {
            return MsgCancleAuthLogin.getCgi();
        }
        if (MsgLoginAnonymous.mMsgId == j) {
            return MsgLoginAnonymous.getCgi();
        }
        if (MsgLoginMobileQuick.mMsgId == j) {
            return MsgLoginMobileQuick.getCgi();
        }
        if (MsgGetUserStatus.mMsgId == j) {
            return MsgGetUserStatus.getCgi();
        }
        if (MsgUnBindAuth.mMsgId == j) {
            return MsgUnBindAuth.getCgi();
        }
        if (MsgOtherAppCredLogin.mMsgId == j) {
            return MsgOtherAppCredLogin.getCgi();
        }
        if (MsgAnonyLoginCred.mMsgId == j) {
            return MsgAnonyLoginCred.getCgi();
        }
        if (MsgSendBindEmailCode.mMsgId == j) {
            return MsgSendBindEmailCode.getCgi();
        }
        if (MsgBindLoginEmail.mMsgId == j) {
            return MsgBindLoginEmail.getCgi();
        }
        return MsgEmailLogin.mMsgId == j ? MsgEmailLogin.getCgi() : "/";
    }
}
