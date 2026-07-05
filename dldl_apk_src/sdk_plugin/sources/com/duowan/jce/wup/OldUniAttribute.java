package com.duowan.jce.wup;

import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class OldUniAttribute {
    protected HashMap<String, HashMap<String, byte[]>> _data = new HashMap<>();
    protected HashMap<String, Object> cachedClassName = new HashMap<>();
    private HashMap<String, Object> cachedData = new HashMap<>();
    protected String encodeName = "UTF-8";
    JceInputStream _is = new JceInputStream();

    OldUniAttribute() {
    }

    public String getEncodeName() {
        return this.encodeName;
    }

    public void setEncodeName(String str) {
        this.encodeName = str;
    }

    public void clearCacheData() {
        this.cachedData.clear();
    }

    public Set<String> getKeySet() {
        return Collections.unmodifiableSet(this._data.keySet());
    }

    public boolean isEmpty() {
        return this._data.isEmpty();
    }

    public int size() {
        return this._data.size();
    }

    public boolean containsKey(String str) {
        return this._data.containsKey(str);
    }

    public <T> void put(String str, T t) {
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
        byte[] jceBufArray = JceUtil.getJceBufArray(jceOutputStream.getByteBuffer());
        HashMap<String, byte[]> map = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        checkObjectType(arrayList, t);
        map.put(BasicClassTypeUtil.transTypeList(arrayList), jceBufArray);
        this.cachedData.remove(str);
        this._data.put(str, map);
    }

    public <T> T getJceStruct(String str) throws ObjectCreateException {
        String key = null;
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
            key = next.getKey();
            value = next.getValue();
        }
        try {
            Object cacheProxy = getCacheProxy(key);
            this._is.warp(value);
            this._is.setServerEncoding(this.encodeName);
            T t = (T) this._is.directRead((JceStruct) cacheProxy, 0, true);
            saveDataCache(str, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectCreateException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T get(String str) throws ObjectCreateException {
        String key = null;
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
            key = next.getKey();
            value = next.getValue();
        }
        try {
            Object cacheProxy = getCacheProxy(key);
            this._is.warp(value);
            this._is.setServerEncoding(this.encodeName);
            T t = (T) this._is.read(cacheProxy, 0, true);
            saveDataCache(str, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectCreateException(e);
        }
    }

    private Object getCacheProxy(String str) {
        if (this.cachedClassName.containsKey(str)) {
            return this.cachedClassName.get(str);
        }
        Object objCreateClassByUni = BasicClassTypeUtil.createClassByUni(str);
        this.cachedClassName.put(str, objCreateClassByUni);
        return objCreateClassByUni;
    }

    private void saveDataCache(String str, Object obj) {
        this.cachedData.put(str, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T get(String str, Object obj) {
        String key;
        byte[] value;
        if (!this._data.containsKey(str)) {
            return obj;
        }
        if (this.cachedData.containsKey(str)) {
            return (T) this.cachedData.get(str);
        }
        byte[] bArr = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this._data.get(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            key = next.getKey();
            value = next.getValue();
        } else {
            key = "";
            value = bArr;
        }
        try {
            Object cacheProxy = getCacheProxy(key);
            this._is.warp(value);
            this._is.setServerEncoding(this.encodeName);
            T t = (T) this._is.read(cacheProxy, 0, true);
            saveDataCache(str, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            saveDataCache(str, obj);
            return obj;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T remove(String str) throws ObjectCreateException {
        String key;
        byte[] value;
        if (!this._data.containsKey(str)) {
            return null;
        }
        byte[] bArr = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this._data.remove(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            key = next.getKey();
            value = next.getValue();
        } else {
            key = "";
            value = bArr;
        }
        try {
            Object objCreateClassByUni = BasicClassTypeUtil.createClassByUni(key);
            this._is.warp(value);
            this._is.setServerEncoding(this.encodeName);
            return (T) this._is.read(objCreateClassByUni, 0, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectCreateException(e);
        }
    }

    private void checkObjectType(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                checkObjectType(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                checkObjectType(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                checkObjectType(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    public byte[] encode() {
        JceOutputStream jceOutputStream = new JceOutputStream(0);
        jceOutputStream.setServerEncoding(this.encodeName);
        jceOutputStream.write((Map) this._data, 0);
        return JceUtil.getJceBufArray(jceOutputStream.getByteBuffer());
    }

    public void decode(byte[] bArr) {
        this._is.warp(bArr);
        this._is.setServerEncoding(this.encodeName);
        HashMap map = new HashMap(1);
        HashMap map2 = new HashMap(1);
        map2.put("", new byte[0]);
        map.put("", map2);
        this._data = this._is.readMap(map, 0, false);
    }
}
