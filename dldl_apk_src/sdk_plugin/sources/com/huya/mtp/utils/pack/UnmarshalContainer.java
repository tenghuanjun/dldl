package com.huya.mtp.utils.pack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UnmarshalContainer {
    public static void unmarshalColUint8(Unpack unpack, Collection<Uint8> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            collection.add(unpack.popUint8());
        }
    }

    public static void unmarshalColUint16(Unpack unpack, Collection<Uint16> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            collection.add(unpack.popUint16());
        }
    }

    public static void unmarshalColUint32(Unpack unpack, Collection<Uint32> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            collection.add(unpack.popUint32());
        }
    }

    public static void unmarshalColUint64(Unpack unpack, Collection<Uint64> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            collection.add(unpack.popUint64());
        }
    }

    public static void unmarshalColString(Unpack unpack, Collection<String> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            collection.add(unpack.popString());
        }
    }

    public static void unmarshalColBytes(Unpack unpack, Collection<byte[]> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            collection.add(unpack.popBytes());
        }
    }

    public static void unmarshalColMarshallable(Unpack unpack, Collection collection, Class<? extends Marshallable> cls) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            try {
                Marshallable marshallableNewInstance = cls.newInstance();
                marshallableNewInstance.unmarshall(unpack);
                collection.add(marshallableNewInstance);
            } catch (IllegalAccessException e) {
                throw new UnpackException(e);
            } catch (InstantiationException e2) {
                throw new UnpackException(e2);
            }
        }
    }

    public static void unmarshalColMapStringString(Unpack unpack, Collection<Map<String, String>> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            HashMap map = new HashMap();
            unmarshalMapStringString(unpack, map);
            collection.add(map);
        }
    }

    public static void unmarshalColMapUint32String(Unpack unpack, Collection<Map<Uint32, String>> collection) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            HashMap map = new HashMap();
            unmarshalMapUint32String(unpack, map);
            collection.add(map);
        }
    }

    public static void unmarshalMapUint8Uint32(Unpack unpack, Map<Uint8, Uint32> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popUint8(), unpack.popUint32());
        }
    }

    public static void unmarshalMapUint16Uint32(Unpack unpack, Map<Uint16, Uint32> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popUint16(), unpack.popUint32());
        }
    }

    public static void unmarshalMapUint32Uint32(Unpack unpack, Map<Uint32, Uint32> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popUint32(), unpack.popUint32());
        }
    }

    public static void unmarshalMapUint32String(Unpack unpack, Map<Uint32, String> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popUint32(), unpack.popString());
        }
    }

    public static void unmarshalMapUint16String(Unpack unpack, Map<Uint16, String> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popUint16(), unpack.popString());
        }
    }

    public static void unmarshalMapUint32Bytes(Unpack unpack, Map<Uint32, byte[]> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popUint32(), unpack.popBytes());
        }
    }

    public static void unmarshalMapStringString(Unpack unpack, Map<String, String> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popString(), unpack.popString());
        }
    }

    public static void unmarshalMapBytesBytes(Unpack unpack, Map<byte[], byte[]> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popBytes(), unpack.popBytes());
        }
    }

    public static void unmarshalMapStringUint32(Unpack unpack, Map<String, Uint32> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popString(), unpack.popUint32());
        }
    }

    public static void unmarshalMapBytesUint32(Unpack unpack, Map<byte[], Uint32> map) {
        Uint32 uint32PopUint32 = unpack.popUint32();
        for (int i = 0; i < uint32PopUint32.toInt(); i++) {
            map.put(unpack.popBytes(), unpack.popUint32());
        }
    }
}
