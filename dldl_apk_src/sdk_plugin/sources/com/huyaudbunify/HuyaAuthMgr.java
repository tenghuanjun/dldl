package com.huyaudbunify;

import android.app.Application;
import android.util.Base64;
import android.util.Log;
import com.huyaudb.HuyaAuthCore;
import com.huyaudbunify.inter.IIHttpCallBack;
import com.huyaudbunify.inter.IIHuyaAuthCoreCallBack;
import com.huyaudbunify.inter.IIHuyaAuthMgrCallBack;
import com.huyaudbunify.msg.MsgReport;
import com.huyaudbunify.msg.MsgReportCache;
import com.huyaudbunify.request.HuyaAuthMarsNetRequestUtil;
import com.huyaudbunify.request.HuyaAuthNetRequestUtil;
import com.huyaudbunify.util.HuyaDeveloperUtils;
import com.huyaudbunify.util.HuyaMarsCgiUtil;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAuthMgr {
    public IIHuyaAuthMgrCallBack callback;

    public void setCallback(IIHuyaAuthMgrCallBack iIHuyaAuthMgrCallBack) {
        this.callback = iIHuyaAuthMgrCallBack;
    }

    public HuyaAuthMgr() {
        HuyaAuthCore.getInstance().setAuthCorecallBack(new IIHuyaAuthCoreCallBack() { // from class: com.huyaudbunify.HuyaAuthMgr.1
            @Override // com.huyaudbunify.inter.IIHuyaAuthCoreCallBack
            public void sendNet(long j, int i, byte[] bArr) {
                if (!HuyaDeveloperUtils.getInstance().isForbidLog()) {
                    Log.i("udbauth", "sendData: call");
                }
                if (j == MsgReport.mMsgId || j == MsgReportCache.mMsgId || !HuyaDeveloperUtils.getInstance().isUseMars()) {
                    HuyaAuthMgr.this.httpSendNet(j, i, bArr);
                } else {
                    HuyaAuthMgr.this.marsSendNet(j, i, bArr);
                }
            }

            @Override // com.huyaudbunify.inter.IIHuyaAuthCoreCallBack
            public void receiveMsg(long j, byte[] bArr) {
                String str = new String(bArr);
                if (!HuyaDeveloperUtils.getInstance().isForbidLog()) {
                    Log.i("udbauth", "receiveMsg: call" + str);
                }
                if (HuyaAuthMgr.this.callback != null) {
                    HuyaAuthMgr.this.callback.onReceiveCallBack(j, str);
                }
            }

            @Override // com.huyaudbunify.inter.IIHuyaAuthCoreCallBack
            public void log(String str) {
                if (HuyaAuthMgr.this.callback != null) {
                    HuyaAuthMgr.this.callback.log(str);
                }
            }
        });
    }

    public void init(Application application) {
        try {
            HuyaAuthCore.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unInit() {
        HuyaAuthCore.getInstance().unInit();
    }

    public String sendMessage(long j, String str) {
        try {
            byte[] bArrSendMsg = HuyaAuthCore.getInstance().sendMsg(j, str.getBytes());
            return bArrSendMsg != null ? new String(bArrSendMsg) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void httpSendNet(long j, int i, byte[] bArr) {
        byte[] bArrDecode = Base64.decode(bArr, 0);
        HuyaAuthNetRequestUtil huyaAuthNetRequestUtil = new HuyaAuthNetRequestUtil();
        String url = HuyaUrlUtil.getUrl(j);
        if (url.length() == 0) {
            return;
        }
        huyaAuthNetRequestUtil.sendRequestPost(url, bArrDecode, i, false, new IIHttpCallBack() { // from class: com.huyaudbunify.HuyaAuthMgr.2
            @Override // com.huyaudbunify.inter.IIHttpCallBack
            public void onResponse(byte[] bArr2, int i2, int i3, int i4) {
                if (!HuyaDeveloperUtils.getInstance().isForbidLog()) {
                    Log.i("udbauth", "IIHttpCallBack: call");
                }
                if (bArr2 == null || bArr2.length == 0) {
                    Log.i("udbauth", "IIHttpCallBack: call error: length = 0");
                    return;
                }
                try {
                    HuyaAuthCore.getInstance().receiveNet(Base64.encodeToString(bArr2, 2).getBytes(), i2, i4, i3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void marsSendNet(long j, final int i, byte[] bArr) {
        byte[] bArrDecode = Base64.decode(bArr, 0);
        HuyaAuthMarsNetRequestUtil.getInstance().sendNet(i, HuyaMarsCgiUtil.getCgi(j), bArrDecode, new IIHttpCallBack() { // from class: com.huyaudbunify.HuyaAuthMgr.3
            @Override // com.huyaudbunify.inter.IIHttpCallBack
            public void onResponse(byte[] bArr2, int i2, int i3, int i4) {
                if (!HuyaDeveloperUtils.getInstance().isForbidLog()) {
                    Log.i("udbauth", "MarsCallBackResponse: call");
                }
                if (bArr2 == null || bArr2.length == 0) {
                    Log.i("udbauth", "MarsCallBackResponse: call error: length = 0");
                    return;
                }
                try {
                    HuyaAuthCore.getInstance().receiveNet(Base64.encodeToString(bArr2, 2).getBytes(), i, i4, i3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
