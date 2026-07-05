package com.duowan.networkmars.wup;

import com.duowan.auk.http.HttpClient;
import com.duowan.jce.wup.UniPacket;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.utils.ZipUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class WupEasyHandler<T extends JceStruct> implements HttpClient.HttpHandler {
    private T mProxy;

    public abstract void onFailure(FailReason failReason);

    public abstract void onSuccess(T t);

    public enum FailReason {
        Http(IDataSource.SCHEME_HTTP_TAG),
        WupParse("wup_parse");

        public final String name;

        FailReason(String str) {
            this.name = str;
        }
    }

    public WupEasyHandler(T t) {
        this.mProxy = t;
    }

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
        if (map.containsKey(NSFunction.CONTENT_ENCODING) && NSFunction.GZIP.equals(map.get(NSFunction.CONTENT_ENCODING).get(0))) {
            try {
                bArr = ZipUtils.ungzip(bArr);
            } catch (IOException unused) {
                onFailure(FailReason.WupParse);
            }
        }
        try {
            UniPacket uniPacket = new UniPacket();
            uniPacket.decode(bArr);
            JceStruct jceStruct = (JceStruct) uniPacket.getByClass("tRsp", this.mProxy);
            JsonNullTerminator.terminate(jceStruct);
            onSuccess(jceStruct);
        } catch (Exception unused2) {
            onFailure(FailReason.WupParse);
        }
    }

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
        onFailure(FailReason.Http);
    }
}
