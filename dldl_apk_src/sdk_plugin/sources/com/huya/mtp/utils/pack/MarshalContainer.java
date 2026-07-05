package com.huya.mtp.utils.pack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MarshalContainer {
    public static void marshalColUint8(Pack pack, Collection<Uint8> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<Uint8> it = collection.iterator();
        while (it.hasNext()) {
            pack.push(it.next());
        }
    }

    public static void marshalColUint16(Pack pack, Collection<Uint16> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<Uint16> it = collection.iterator();
        while (it.hasNext()) {
            pack.push(it.next());
        }
    }

    public static void marshalColUint32(Pack pack, Collection<Uint32> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<Uint32> it = collection.iterator();
        while (it.hasNext()) {
            pack.push(it.next());
        }
    }

    public static void marshalColUint64(Pack pack, Collection<Uint64> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<Uint64> it = collection.iterator();
        while (it.hasNext()) {
            pack.push(it.next());
        }
    }

    public static void marshalColString(Pack pack, Collection<String> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            pack.push(it.next());
        }
    }

    public static void marshalColBytes(Pack pack, Collection<byte[]> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<byte[]> it = collection.iterator();
        while (it.hasNext()) {
            pack.push(it.next());
        }
    }

    public static void marshalColMarshallable(Pack pack, Collection<? extends Marshallable> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<? extends Marshallable> it = collection.iterator();
        while (it.hasNext()) {
            it.next().marshall(pack);
        }
    }

    public static void marshalColMapStringString(Pack pack, Collection<Map<String, String>> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<Map<String, String>> it = collection.iterator();
        while (it.hasNext()) {
            marshalMapStringString(pack, it.next());
        }
    }

    public static void marshalColMapUint32String(Pack pack, Collection<Map<Uint32, String>> collection) {
        pack.push(new Uint32(collection.size()));
        Iterator<Map<Uint32, String>> it = collection.iterator();
        while (it.hasNext()) {
            marshalMapUint32String(pack, it.next());
        }
    }

    public static void marshalMapUint8Uint32(Pack pack, Map<Uint8, Uint32> map) {
        pack.push(new Uint32(map.size()));
        for (Uint8 uint8 : map.keySet()) {
            pack.push(uint8);
            pack.push(map.get(uint8));
        }
    }

    public static void marshalMapUint16Uint32(Pack pack, Map<Uint16, Uint32> map) {
        pack.push(new Uint32(map.size()));
        for (Uint16 uint16 : map.keySet()) {
            pack.push(uint16);
            pack.push(map.get(uint16));
        }
    }

    public static void marshalMapUint32Uint32(Pack pack, Map<Uint32, Uint32> map) {
        pack.push(new Uint32(map.size()));
        for (Uint32 uint32 : map.keySet()) {
            pack.push(uint32);
            pack.push(map.get(uint32));
        }
    }

    public static void marshalMapUint32String(Pack pack, Map<Uint32, String> map) {
        pack.push(new Uint32(map.size()));
        for (Uint32 uint32 : map.keySet()) {
            pack.push(uint32);
            pack.push(map.get(uint32));
        }
    }

    public static void marshalMapUint16String(Pack pack, Map<Uint16, String> map) {
        pack.push(new Uint32(map.size()));
        for (Uint16 uint16 : map.keySet()) {
            pack.push(uint16);
            pack.push(map.get(uint16));
        }
    }

    public static void marshalMapUint32Bytes(Pack pack, Map<Uint32, byte[]> map) {
        pack.push(new Uint32(map.size()));
        for (Uint32 uint32 : map.keySet()) {
            pack.push(uint32);
            pack.push(map.get(uint32));
        }
    }

    public static void marshalMapStringString(Pack pack, Map<String, String> map) {
        pack.push(new Uint32(map.size()));
        for (String str : map.keySet()) {
            pack.push(str);
            pack.push(map.get(str));
        }
    }

    public static void marshalMapBytesBytes(Pack pack, Map<byte[], byte[]> map) {
        pack.push(new Uint32(map.size()));
        for (byte[] bArr : map.keySet()) {
            pack.push(bArr);
            pack.push(map.get(bArr));
        }
    }

    public static void marshalMapStringUint32(Pack pack, Map<String, Uint32> map) {
        pack.push(new Uint32(map.size()));
        for (String str : map.keySet()) {
            pack.push(str);
            pack.push(map.get(str));
        }
    }

    public static void marshalMapBytesUint32(Pack pack, Map<byte[], Uint32> map) {
        pack.push(new Uint32(map.size()));
        for (byte[] bArr : map.keySet()) {
            pack.push(bArr);
            pack.push(map.get(bArr));
        }
    }
}
