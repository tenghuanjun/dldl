package com.duowan.jce.wup;

import com.duowan.taf.RequestPacket;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceUtil;
import com.sqwan.bugless.util.FileUtil;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UniPacket extends UniAttribute {
    public static final int UniPacketHeadSize = 4;
    static HashMap<String, HashMap<String, byte[]>> cache__tempdata;
    static HashMap<String, byte[]> newCache__tempdata;
    protected RequestPacket _package;
    private int oldRespIret;

    public UniPacket() {
        RequestPacket requestPacket = new RequestPacket();
        this._package = requestPacket;
        this.oldRespIret = 0;
        requestPacket.iVersion = (short) 2;
    }

    public UniPacket(boolean z) {
        RequestPacket requestPacket = new RequestPacket();
        this._package = requestPacket;
        this.oldRespIret = 0;
        if (z) {
            useVersion3();
        } else {
            requestPacket.iVersion = (short) 2;
        }
    }

    public int getPackageVersion() {
        return this._package.iVersion;
    }

    @Override // com.duowan.jce.wup.UniAttribute, com.duowan.jce.wup.OldUniAttribute
    public <T> void put(String str, T t) {
        if (str.startsWith(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.put(str, t);
    }

    @Override // com.duowan.jce.wup.UniAttribute
    public void useVersion3() {
        super.useVersion3();
        this._package.iVersion = (short) 3;
    }

    @Override // com.duowan.jce.wup.UniAttribute, com.duowan.jce.wup.OldUniAttribute
    public byte[] encode() {
        if (this._package.iVersion == 2) {
            if (this._package.sServantName == null || this._package.sServantName.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this._package.sFuncName == null || this._package.sFuncName.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this._package.sServantName == null) {
                this._package.sServantName = "";
            }
            if (this._package.sFuncName == null) {
                this._package.sFuncName = "";
            }
        }
        JceOutputStream jceOutputStream = new JceOutputStream(0);
        jceOutputStream.setServerEncoding(this.encodeName);
        if (this._package.iVersion == 2) {
            jceOutputStream.write((Map) this._data, 0);
        } else {
            jceOutputStream.write((Map) this._newData, 0);
        }
        this._package.sBuffer = JceUtil.getJceBufArray(jceOutputStream.getByteBuffer());
        JceOutputStream jceOutputStream2 = new JceOutputStream(0);
        jceOutputStream2.setServerEncoding(this.encodeName);
        writeTo(jceOutputStream2);
        byte[] jceBufArray = JceUtil.getJceBufArray(jceOutputStream2.getByteBuffer());
        int length = jceBufArray.length + 4;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length).put(jceBufArray).flip();
        return byteBufferAllocate.array();
    }

    @Override // com.duowan.jce.wup.UniAttribute
    public void decodeVersion3(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            JceInputStream jceInputStream = new JceInputStream(bArr, 4);
            jceInputStream.setServerEncoding(this.encodeName);
            readFrom(jceInputStream);
            JceInputStream jceInputStream2 = new JceInputStream(this._package.sBuffer);
            jceInputStream2.setServerEncoding(this.encodeName);
            if (newCache__tempdata == null) {
                HashMap<String, byte[]> map = new HashMap<>();
                newCache__tempdata = map;
                map.put("", new byte[0]);
            }
            this._newData = jceInputStream2.readMap(newCache__tempdata, 0, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.duowan.jce.wup.UniAttribute
    public void decodeVersion2(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            JceInputStream jceInputStream = new JceInputStream(bArr, 4);
            jceInputStream.setServerEncoding(this.encodeName);
            readFrom(jceInputStream);
            JceInputStream jceInputStream2 = new JceInputStream(this._package.sBuffer);
            jceInputStream2.setServerEncoding(this.encodeName);
            if (cache__tempdata == null) {
                cache__tempdata = new HashMap<>();
                HashMap<String, byte[]> map = new HashMap<>();
                map.put("", new byte[0]);
                cache__tempdata.put("", map);
            }
            this._data = jceInputStream2.readMap(cache__tempdata, 0, false);
            this.cachedClassName = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.duowan.jce.wup.UniAttribute, com.duowan.jce.wup.OldUniAttribute
    public void decode(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            JceInputStream jceInputStream = new JceInputStream(bArr, 4);
            jceInputStream.setServerEncoding(this.encodeName);
            readFrom(jceInputStream);
            if (this._package.iVersion == 3) {
                JceInputStream jceInputStream2 = new JceInputStream(this._package.sBuffer);
                jceInputStream2.setServerEncoding(this.encodeName);
                if (newCache__tempdata == null) {
                    HashMap<String, byte[]> map = new HashMap<>();
                    newCache__tempdata = map;
                    map.put("", new byte[0]);
                }
                this._newData = jceInputStream2.readMap(newCache__tempdata, 0, false);
                return;
            }
            this._newData = null;
            JceInputStream jceInputStream3 = new JceInputStream(this._package.sBuffer);
            jceInputStream3.setServerEncoding(this.encodeName);
            if (cache__tempdata == null) {
                cache__tempdata = new HashMap<>();
                HashMap<String, byte[]> map2 = new HashMap<>();
                map2.put("", new byte[0]);
                cache__tempdata.put("", map2);
            }
            this._data = jceInputStream3.readMap(cache__tempdata, 0, false);
            this.cachedClassName = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getServantName() {
        return this._package.sServantName;
    }

    public void setServantName(String str) {
        this._package.sServantName = str;
    }

    public String getFuncName() {
        return this._package.sFuncName;
    }

    public void setFuncName(String str) {
        this._package.sFuncName = str;
    }

    public int getRequestId() {
        return this._package.iRequestId;
    }

    public void setRequestId(int i) {
        this._package.iRequestId = i;
    }

    public void writeTo(JceOutputStream jceOutputStream) {
        this._package.writeTo(jceOutputStream);
    }

    public void readFrom(JceInputStream jceInputStream) {
        this._package.readFrom(jceInputStream);
    }

    public void display(StringBuilder sb, int i) {
        this._package.display(sb, i);
    }

    public UniPacket createResponse() {
        UniPacket uniPacket = new UniPacket();
        uniPacket.setRequestId(getRequestId());
        uniPacket.setServantName(getServantName());
        uniPacket.setFuncName(getFuncName());
        uniPacket.setEncodeName(this.encodeName);
        uniPacket._package.iVersion = this._package.iVersion;
        return uniPacket;
    }

    public byte[] createOldRespEncode() {
        JceOutputStream jceOutputStream = new JceOutputStream(0);
        jceOutputStream.setServerEncoding(this.encodeName);
        jceOutputStream.write((Map) this._data, 0);
        byte[] jceBufArray = JceUtil.getJceBufArray(jceOutputStream.getByteBuffer());
        JceOutputStream jceOutputStream2 = new JceOutputStream(0);
        jceOutputStream2.setServerEncoding(this.encodeName);
        jceOutputStream2.write(this._package.iVersion, 1);
        jceOutputStream2.write(this._package.cPacketType, 2);
        jceOutputStream2.write(this._package.iRequestId, 3);
        jceOutputStream2.write(this._package.iMessageType, 4);
        jceOutputStream2.write(this.oldRespIret, 5);
        jceOutputStream2.write(jceBufArray, 6);
        jceOutputStream2.write((Map) this._package.status, 7);
        return JceUtil.getJceBufArray(jceOutputStream2.getByteBuffer());
    }

    public int getOldRespIret() {
        return this.oldRespIret;
    }

    public void setOldRespIret(int i) {
        this.oldRespIret = i;
    }
}
