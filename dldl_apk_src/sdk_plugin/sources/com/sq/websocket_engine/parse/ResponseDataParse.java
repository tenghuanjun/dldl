package com.sq.websocket_engine.parse;

import com.sq.websocket_engine.BodyData;
import com.sqwan.common.util.LogUtil;
import java.nio.ByteBuffer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResponseDataParse {
    private static final String TAG = "ResponseDataParse";
    public BodyData body;
    public int op;
    public int seq;

    public ResponseDataParse parse(ByteBuffer byteBuffer) {
        try {
            LogUtil.i(TAG, "size:" + byteBuffer.capacity());
            int i = byteBuffer.getInt(0);
            short s = byteBuffer.getShort(4);
            if (byteBuffer.getShort(6) != 100) {
                return null;
            }
            int i2 = byteBuffer.getInt(8);
            int i3 = byteBuffer.getInt(12);
            byte[] bArrBytebuffer2ByteArray = bytebuffer2ByteArray(byteBuffer);
            int i4 = i - s;
            byte[] bArr = new byte[i4];
            LogUtil.i(TAG, "byteBody size:" + i4);
            for (int i5 = 0; i5 < i4; i5++) {
                bArr[i5] = bArrBytebuffer2ByteArray[i5 + s];
            }
            BodyData bodyDataFromJson = new BodyData().fromJson(new String(bArr));
            this.op = i2;
            this.seq = i3;
            this.body = bodyDataFromJson;
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] bytebuffer2ByteArray(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.get(bArr, 0, iRemaining);
        return bArr;
    }

    public boolean isSuccess() {
        BodyData bodyData = this.body;
        if (bodyData != null) {
            return bodyData.isSuccess();
        }
        return false;
    }

    public String toString() {
        return "ResponseDataParse{op=" + this.op + ", seq=" + this.seq + ", body=" + this.body + AbstractJsonLexerKt.END_OBJ;
    }
}
