package com.duowan.taf.jce.dynamic;

import com.duowan.taf.jce.JceDecodeException;
import com.duowan.taf.jce.JceInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class DynamicInputStream {
    private ByteBuffer bs;
    private String sServerEncoding = "GBK";

    public DynamicInputStream(ByteBuffer byteBuffer) {
        this.bs = byteBuffer;
    }

    public DynamicInputStream(byte[] bArr) {
        this.bs = ByteBuffer.wrap(bArr);
    }

    public int setServerEncoding(String str) {
        this.sServerEncoding = str;
        return 0;
    }

    public JceField read() {
        HeadData headDataObtain = HeadData.obtain();
        JceField jceFieldCreate = null;
        try {
            JceInputStream.readHead(headDataObtain, this.bs);
            int i = 0;
            switch (headDataObtain.type) {
                case 0:
                    jceFieldCreate = JceField.create(this.bs.get(), headDataObtain.tag);
                    return jceFieldCreate;
                case 1:
                    return JceField.create(this.bs.getShort(), headDataObtain.tag);
                case 2:
                    return JceField.create(this.bs.getInt(), headDataObtain.tag);
                case 3:
                    return JceField.create(this.bs.getLong(), headDataObtain.tag);
                case 4:
                    return JceField.create(this.bs.getFloat(), headDataObtain.tag);
                case 5:
                    return JceField.create(this.bs.getDouble(), headDataObtain.tag);
                case 6:
                    int i2 = this.bs.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    return readString(headDataObtain, i2);
                case 7:
                    return readString(headDataObtain, this.bs.getInt());
                case 8:
                    int iIntValue = ((NumberField) read()).intValue();
                    JceField[] jceFieldArr = new JceField[iIntValue];
                    JceField[] jceFieldArr2 = new JceField[iIntValue];
                    while (i < iIntValue) {
                        jceFieldArr[i] = read();
                        jceFieldArr2[i] = read();
                        i++;
                    }
                    return JceField.createMap(jceFieldArr, jceFieldArr2, headDataObtain.tag);
                case 9:
                    int iIntValue2 = ((NumberField) read()).intValue();
                    JceField[] jceFieldArr3 = new JceField[iIntValue2];
                    while (i < iIntValue2) {
                        jceFieldArr3[i] = read();
                        i++;
                    }
                    return JceField.createList(jceFieldArr3, headDataObtain.tag);
                case 10:
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        JceField jceField = read();
                        if (jceField == null) {
                            return JceField.createStruct((JceField[]) arrayList.toArray(new JceField[0]), headDataObtain.tag);
                        }
                        arrayList.add(jceField);
                    }
                    break;
                case 11:
                    return null;
                case 12:
                    return JceField.createZero(headDataObtain.tag);
                case 13:
                    int i3 = headDataObtain.tag;
                    JceInputStream.readHead(headDataObtain, this.bs);
                    if (headDataObtain.type == 0) {
                        byte[] bArr = new byte[((NumberField) read()).intValue()];
                        this.bs.get(bArr);
                        return JceField.create(bArr, i3);
                    }
                    throw new JceDecodeException("type mismatch, simple_list only support byte, tag: " + i3 + ", type: " + ((int) headDataObtain.type));
                default:
                    return jceFieldCreate;
            }
        } catch (BufferUnderflowException unused) {
            return null;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    private JceField readString(HeadData headData, int i) {
        String str;
        byte[] bArr = new byte[i];
        this.bs.get(bArr);
        try {
            str = new String(bArr, this.sServerEncoding);
        } catch (UnsupportedEncodingException unused) {
            str = new String(bArr);
        }
        return JceField.create(str, headData.tag);
    }
}
