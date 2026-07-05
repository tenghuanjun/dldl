package com.duowan.jce.wup;

import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UniAttribute extends OldUniAttribute {
    protected HashMap<String, byte[]> _newData = null;
    private HashMap<String, Object> cachedData = new HashMap<>();
    JceInputStream _is = new JceInputStream();

    @Override // com.duowan.jce.wup.OldUniAttribute
    public /* bridge */ /* synthetic */ String getEncodeName() {
        return super.getEncodeName();
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public /* bridge */ /* synthetic */ void setEncodeName(String str) {
        super.setEncodeName(str);
    }

    public void useVersion3() {
        this._newData = new HashMap<>();
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public void clearCacheData() {
        this.cachedData.clear();
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public Set<String> getKeySet() {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            return Collections.unmodifiableSet(map.keySet());
        }
        return Collections.unmodifiableSet(this._data.keySet());
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public boolean isEmpty() {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            return map.isEmpty();
        }
        return this._data.isEmpty();
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public int size() {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            return map.size();
        }
        return this._data.size();
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public boolean containsKey(String str) {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            return map.containsKey(str);
        }
        return this._data.containsKey(str);
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public <T> void put(String str, T t) {
        if (this._newData == null) {
            super.put(str, t);
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        JceOutputStream jceOutputStream = new JceOutputStream();
        jceOutputStream.setServerEncoding(this.encodeName);
        jceOutputStream.write(t, 0);
        this._newData.put(str, JceUtil.getJceBufArray(jceOutputStream.getByteBuffer()));
    }

    public <T> T getJceStruct(String str, T t) throws ObjectCreateException {
        if (!this._newData.containsKey(str)) {
            return null;
        }
        if (this.cachedData.containsKey(str)) {
            return (T) this.cachedData.get(str);
        }
        try {
            T t2 = (T) decodeData(this._newData.get(str), t);
            if (t2 != null) {
                saveDataCache(str, t2);
            }
            return t2;
        } catch (Exception e) {
            throw new ObjectCreateException(e);
        }
    }

    public <T> T getByClass(String str, T t) throws ObjectCreateException {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            if (!map.containsKey(str)) {
                return null;
            }
            if (this.cachedData.containsKey(str)) {
                return (T) this.cachedData.get(str);
            }
            try {
                T t2 = (T) decodeData(this._newData.get(str), t);
                if (t2 != null) {
                    saveDataCache(str, t2);
                }
                return t2;
            } catch (Exception e) {
                throw new ObjectCreateException(e);
            }
        }
        if (!this._data.containsKey(str)) {
            return null;
        }
        if (this.cachedData.containsKey(str)) {
            return (T) this.cachedData.get(str);
        }
        byte[] value = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this._data.get(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            next.getKey();
            value = next.getValue();
        }
        try {
            this._is.warp(value);
            this._is.setServerEncoding(this.encodeName);
            T t3 = (T) this._is.read((Object) t, 0, true);
            saveDataCache(str, t3);
            return t3;
        } catch (Exception e2) {
            throw new ObjectCreateException(e2);
        }
    }

    public <T> T getByClass(String str, T t, T t2) throws ObjectCreateException {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            if (!map.containsKey(str)) {
                return t2;
            }
            if (this.cachedData.containsKey(str)) {
                return (T) this.cachedData.get(str);
            }
            try {
                T t3 = (T) decodeData(this._newData.get(str), t);
                if (t3 != null) {
                    saveDataCache(str, t3);
                }
                return t3;
            } catch (Exception e) {
                throw new ObjectCreateException(e);
            }
        }
        if (!this._data.containsKey(str)) {
            return t2;
        }
        if (this.cachedData.containsKey(str)) {
            return (T) this.cachedData.get(str);
        }
        byte[] value = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this._data.get(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            next.getKey();
            value = next.getValue();
        }
        try {
            this._is.warp(value);
            this._is.setServerEncoding(this.encodeName);
            T t4 = (T) this._is.read((Object) t, 0, true);
            saveDataCache(str, t4);
            return t4;
        } catch (Exception e2) {
            throw new ObjectCreateException(e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T get(String str, T t, Object obj) {
        return !this._newData.containsKey(str) ? obj : (T) getByClass(str, t);
    }

    private Object decodeData(byte[] bArr, Object obj) {
        this._is.warp(bArr);
        this._is.setServerEncoding(this.encodeName);
        return this._is.read(obj, 0, true);
    }

    private void saveDataCache(String str, Object obj) {
        this.cachedData.put(str, obj);
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public <T> T getJceStruct(String str) throws ObjectCreateException {
        if (this._newData != null) {
            throw new RuntimeException("data is encoded by new version, please use getJceStruct(String name,T proxy)");
        }
        return (T) super.getJceStruct(str);
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public <T> T get(String str) throws ObjectCreateException {
        if (this._newData != null) {
            throw new RuntimeException("data is encoded by new version, please use getByClass(String name, T proxy)");
        }
        return (T) super.get(str);
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public <T> T get(String str, Object obj) {
        if (this._newData != null) {
            throw new RuntimeException("data is encoded by new version, please use get(String name, T proxy, Object defaultValue)");
        }
        return (T) super.get(str, obj);
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public <T> T remove(String str) throws ObjectCreateException {
        HashMap<String, byte[]> map = this._newData;
        if (map != null) {
            if (!map.containsKey(str)) {
                return null;
            }
            this._newData.remove(str);
            return null;
        }
        return (T) super.remove(str);
    }

    public <T> T remove(String str, T t) throws ObjectCreateException {
        if (!this._newData.containsKey(str)) {
            return null;
        }
        if (t != null) {
            return (T) decodeData(this._newData.remove(str), t);
        }
        this._newData.remove(str);
        return null;
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public byte[] encode() {
        if (this._newData != null) {
            JceOutputStream jceOutputStream = new JceOutputStream(0);
            jceOutputStream.setServerEncoding(this.encodeName);
            jceOutputStream.write((Map) this._newData, 0);
            return JceUtil.getJceBufArray(jceOutputStream.getByteBuffer());
        }
        return super.encode();
    }

    public void decodeVersion3(byte[] bArr) {
        this._is.warp(bArr);
        this._is.setServerEncoding(this.encodeName);
        HashMap map = new HashMap(1);
        map.put("", new byte[0]);
        this._newData = this._is.readMap(map, 0, false);
    }

    public void decodeVersion2(byte[] bArr) {
        super.decode(bArr);
    }

    @Override // com.duowan.jce.wup.OldUniAttribute
    public void decode(byte[] bArr) {
        try {
            super.decode(bArr);
        } catch (Exception unused) {
            this._is.warp(bArr);
            this._is.setServerEncoding(this.encodeName);
            HashMap map = new HashMap(1);
            map.put("", new byte[0]);
            this._newData = this._is.readMap(map, 0, false);
        }
    }
}
