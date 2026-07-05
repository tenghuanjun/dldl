package com.huya.mtp.hyns;

import android.util.LruCache;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.SingleInstanceCache;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NS {
    private static LazyTransporter mLazyTransporter;
    private static volatile NSProtocol sDefaultProtocol;
    private static final SingleInstanceCache<Class, NSProtocol> PROTOCOL_CACHE = new SingleInstanceCache<Class, NSProtocol>(new SingleInstanceCache.Cache<Class, NSProtocol>() { // from class: com.huya.mtp.hyns.NS.1
        private Map<Class, NSProtocol> mCacheImpl = new ConcurrentHashMap();

        @Override // com.huya.mtp.hyns.SingleInstanceCache.Cache
        public void put(Class cls, NSProtocol nSProtocol) {
            if (nSProtocol != null) {
                MTPApi.LOGGER.info(NSConstants.NSTAG, "NSProtocol: key:%s, value:%s", cls.toString(), nSProtocol.getClass().toString());
            }
            if (NSConstants.isApkInDebug() && this.mCacheImpl.containsKey(cls)) {
                throw new RuntimeException("Can not multi inject the same NSProtocol.");
            }
            this.mCacheImpl.put(cls, nSProtocol);
        }

        @Override // com.huya.mtp.hyns.SingleInstanceCache.Cache
        public NSProtocol get(Class cls) {
            return this.mCacheImpl.get(cls);
        }

        @Override // com.huya.mtp.hyns.SingleInstanceCache.Cache
        public Collection<NSProtocol> values() {
            return this.mCacheImpl.values();
        }
    }) { // from class: com.huya.mtp.hyns.NS.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huya.mtp.hyns.SingleInstanceCache
        public NSProtocol newInstance(Class cls) {
            try {
                return (NSProtocol) cls.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot initialize protocol class " + cls, e);
            }
        }
    };
    private static final SingleInstanceCache<Class, Object> API_IMPL_CACHE = new SingleInstanceCache<Class, Object>(new SingleInstanceCache.Cache<Class, Object>() { // from class: com.huya.mtp.hyns.NS.3
        private LruCache<Class, Object> mCacheImpl = new LruCache<>(100);

        @Override // com.huya.mtp.hyns.SingleInstanceCache.Cache
        public void put(Class cls, Object obj) {
            this.mCacheImpl.put(cls, obj);
        }

        @Override // com.huya.mtp.hyns.SingleInstanceCache.Cache
        public Object get(Class cls) {
            return this.mCacheImpl.get(cls);
        }

        @Override // com.huya.mtp.hyns.SingleInstanceCache.Cache
        public Collection<Object> values() {
            return this.mCacheImpl.snapshot().values();
        }
    }) { // from class: com.huya.mtp.hyns.NS.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huya.mtp.hyns.SingleInstanceCache
        public Object newInstance(Class cls) {
            NSProtocol protocolByApi = NS.getProtocolByApi(cls);
            if (protocolByApi == null || !protocolByApi.accept(cls)) {
                throw new RuntimeException("【NS未初始化，请排查初始化时序】Cannot get protocol instance for class :" + cls + ", protocol = " + protocolByApi);
            }
            return protocolByApi.get(cls);
        }
    };

    public interface LazyTransporter {
        NSTransporter getTransporter(NSHttpProtocol nSHttpProtocol);
    }

    public static LazyTransporter getLazyTransporter() {
        return mLazyTransporter;
    }

    public static void setLazyTransporter(LazyTransporter lazyTransporter) {
        mLazyTransporter = lazyTransporter;
    }

    public static <T extends NSProtocol> void initProtocol(Class<? super T> cls, T t) {
        PROTOCOL_CACHE.put(cls, t);
    }

    public static void setDefaultProtocol(NSProtocol nSProtocol) {
        sDefaultProtocol = nSProtocol;
    }

    public static <T> T get(Class<T> cls) {
        return (T) API_IMPL_CACHE.get(cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> NSProtocol getProtocolByApi(Class<T> cls) {
        NSApi nSApi = (NSApi) cls.getAnnotation(NSApi.class);
        if (nSApi != null) {
            return getProtocolImpl(nSApi.value());
        }
        NSProtocol nSProtocol = sDefaultProtocol;
        if (nSProtocol != null && nSProtocol.accept(cls)) {
            return nSProtocol;
        }
        for (NSProtocol nSProtocol2 : Collections.unmodifiableCollection(PROTOCOL_CACHE.values())) {
            if (nSProtocol2.accept(cls)) {
                return nSProtocol2;
            }
        }
        return nSProtocol;
    }

    public static <T extends NSProtocol> T getProtocolImpl(Class<T> cls) {
        return (T) PROTOCOL_CACHE.get(cls);
    }
}
