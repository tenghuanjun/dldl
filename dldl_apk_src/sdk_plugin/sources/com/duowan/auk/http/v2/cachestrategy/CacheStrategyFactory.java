package com.duowan.auk.http.v2.cachestrategy;

import com.duowan.auk.http.v2.CacheType;
import com.duowan.auk.http.v2.HttpFunction;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CacheStrategyFactory {

    /* JADX INFO: renamed from: com.duowan.auk.http.v2.cachestrategy.CacheStrategyFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$duowan$auk$http$v2$CacheType;

        static {
            int[] iArr = new int[CacheType.values().length];
            $SwitchMap$com$duowan$auk$http$v2$CacheType = iArr;
            try {
                iArr[CacheType.NetFirst.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$v2$CacheType[CacheType.CacheOnly.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$v2$CacheType[CacheType.AsConfig.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$v2$CacheType[CacheType.CacheFirst.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$v2$CacheType[CacheType.CacheThenNet.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static <T> BaseCacheStrategy<T> createCacheStrategy(CacheType cacheType, HttpFunction<T> httpFunction) {
        int i = AnonymousClass1.$SwitchMap$com$duowan$auk$http$v2$CacheType[cacheType.ordinal()];
        if (i == 1) {
            return new NetFirst(httpFunction);
        }
        if (i == 2) {
            return new CacheOnly(httpFunction);
        }
        if (i == 3) {
            return new NetOnly(httpFunction);
        }
        if (i == 4) {
            return new CacheFirst(httpFunction);
        }
        if (i == 5) {
            return new CacheThenNet(httpFunction);
        }
        return new NetOnly(httpFunction);
    }
}
