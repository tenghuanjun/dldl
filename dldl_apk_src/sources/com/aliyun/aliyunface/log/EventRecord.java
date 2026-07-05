package com.aliyun.aliyunface.log;

import com.aliyun.aliyunface.ToygerConst;
import com.igexin.push.core.d.c;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class EventRecord extends RecordBase {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public EventRecord() {
        setMagic("D-VM");
        setLogTime(this.simpleDateFormat.format(new Date()));
        setClientId(ToygerConst.TOYGER_LOG_CLIENT_ID);
        setClientVersion(ToygerConst.TOYGER_SDK_VERSION);
        setLogVersion("2");
        setActionId("event");
        setBizType("u");
        setLogType(c.a);
        setAppId(ToygerConst.TOYGER_LOG_APP_ID);
    }
}
