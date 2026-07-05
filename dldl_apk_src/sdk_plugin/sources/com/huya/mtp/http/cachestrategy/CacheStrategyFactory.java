package com.huya.mtp.http.cachestrategy;

import com.huya.mtp.http.CacheType;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CacheStrategyFactory {

    /* JADX INFO: renamed from: com.huya.mtp.http.cachestrategy.CacheStrategyFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$mtp$http$CacheType;

        static {
            int[] iArr = new int[CacheType.values().length];
            $SwitchMap$com$huya$mtp$http$CacheType = iArr;
            try {
                iArr[CacheType.NetFirst.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$mtp$http$CacheType[CacheType.CacheOnly.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huya$mtp$http$CacheType[CacheType.NetOnly.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huya$mtp$http$CacheType[CacheType.CacheFirst.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$huya$mtp$http$CacheType[CacheType.CacheThenNet.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static <T> BaseNetworkStrategy<T> createCacheStrategy(CacheType cacheType) {
        int i = AnonymousClass1.$SwitchMap$com$huya$mtp$http$CacheType[cacheType.ordinal()];
        if (i == 1) {
            return new NetFirst();
        }
        if (i == 2) {
            return new CacheOnly();
        }
        if (i == 3) {
            return new NetOnly();
        }
        if (i == 4) {
            return new CacheFirst();
        }
        if (i == 5) {
            return new CacheThenNet();
        }
        return new NetOnly();
    }
}
