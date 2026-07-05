package com.sq.websocket_engine.parse;

import com.sq.websocket_engine.BodyData;
import com.sq.websocket_engine.MsgBaseReq;
import com.sq.websocket_engine.RequestQueueHandler;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.concurrent.atomic.AtomicInteger;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestDataParse {
    private static final String TAG = "RequestDataParse";
    private static AtomicInteger globalSeq = new AtomicInteger(0);
    public static final short version = 100;
    public BodyData body;
    public int op;
    public RequestQueueHandler.IRequestCallback<ResponseDataParse> requestCallback;
    public RequestQueueHandler.RequestPriority requestPriority;
    public long sendTime;
    public int seq = globalSeq.incrementAndGet();
    public int timeout;

    public RequestDataParse(MsgBaseReq msgBaseReq) {
        this.timeout = 10000;
        this.requestPriority = RequestQueueHandler.RequestPriority.DEFAULT;
        this.requestPriority = msgBaseReq.requestPriority;
        this.timeout = msgBaseReq.timeout;
        this.op = msgBaseReq.getOp();
        this.body = new BodyData().getReqBodyData(msgBaseReq);
    }

    public byte[] pack() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        String json = this.body.toJson();
        try {
            dataOutputStream.writeInt(json.length() + 16);
            dataOutputStream.writeShort(16);
            dataOutputStream.writeShort(100);
            dataOutputStream.writeInt(this.op);
            dataOutputStream.writeInt(this.seq);
            dataOutputStream.writeBytes(json);
            dataOutputStream.close();
            StringBuilder sb = new StringBuilder();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (byte b : byteArray) {
                sb.append(Integer.toBinaryString(b & 255));
            }
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "RequestDataParse{op=" + this.op + ", seq=" + this.seq + ", body=" + this.body + ", sendTime=" + this.sendTime + ", timeout=" + this.timeout + ", requestPriority=" + this.requestPriority + ", requestCallback=" + this.requestCallback + AbstractJsonLexerKt.END_OBJ;
    }
}
