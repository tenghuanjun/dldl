package com.duowan.monitor.core;

import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.jce.Metric;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
final class MetricPool {
    private static SynchronizedPool<Metric> sPools = new SynchronizedPool<>(100);

    public interface Pool<T> {
        T acquire();

        boolean release(T t);
    }

    private MetricPool() {
    }

    private static Metric obtain() {
        Metric metricAcquire = sPools.acquire();
        return metricAcquire != null ? metricAcquire : new Metric();
    }

    public static Metric obtain(String str, double d, EUnit eUnit) {
        Metric metricObtain = obtain();
        metricObtain.sMetricName = str;
        metricObtain.fValue = d;
        metricObtain.eUnit = eUnit.value();
        metricObtain.iTS = System.currentTimeMillis();
        return metricObtain;
    }

    static Metric shallowCopy(Metric metric) {
        if (metric == null) {
            return null;
        }
        Metric metricObtain = obtain();
        metricObtain.sMetricName = metric.sMetricName;
        metricObtain.fValue = metric.fValue;
        metricObtain.eUnit = metric.eUnit;
        metricObtain.sExtDesc = metric.sExtDesc;
        metricObtain.iRetCode = metric.iRetCode;
        metricObtain.iSuccess = metric.iSuccess;
        metricObtain.tStatsSet = metric.tStatsSet;
        metricObtain.iTS = metric.iTS;
        metricObtain.vDimension = metric.vDimension;
        return metricObtain;
    }

    static void clean(Metric metric) {
        metric.sMetricName = null;
        metric.fValue = 0.0d;
        metric.eUnit = 0;
        metric.iTS = 0L;
        metric.sExtDesc = null;
        metric.iRetCode = 0;
        metric.iSuccess = 0;
        metric.tStatsSet = null;
        metric.vDimension = null;
        metric.vExLog = null;
    }

    public static void release(Collection<Metric> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        for (Metric metric : collection) {
            clean(metric);
            sPools.release(metric);
        }
    }

    public static boolean release(Metric metric) {
        if (metric == null) {
            return false;
        }
        clean(metric);
        return sPools.release(metric);
    }

    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock;

        public SynchronizedPool(int i) {
            super(i);
            this.mLock = new Object();
        }

        @Override // com.duowan.monitor.core.MetricPool.SimplePool, com.duowan.monitor.core.MetricPool.Pool
        public T acquire() {
            T t;
            synchronized (this.mLock) {
                t = (T) super.acquire();
            }
            return t;
        }

        @Override // com.duowan.monitor.core.MetricPool.SimplePool, com.duowan.monitor.core.MetricPool.Pool
        public boolean release(T t) {
            boolean zRelease;
            synchronized (this.mLock) {
                zRelease = super.release(t);
            }
            return zRelease;
        }
    }

    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[i];
        }

        @Override // com.duowan.monitor.core.MetricPool.Pool
        public T acquire() {
            int i = this.mPoolSize;
            if (i <= 0) {
                return null;
            }
            int i2 = i - 1;
            Object[] objArr = this.mPool;
            T t = (T) objArr[i2];
            objArr[i2] = null;
            this.mPoolSize = i - 1;
            return t;
        }

        @Override // com.duowan.monitor.core.MetricPool.Pool
        public boolean release(T t) {
            if (isInPool(t)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int i = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i >= objArr.length) {
                return false;
            }
            objArr[i] = t;
            this.mPoolSize = i + 1;
            return true;
        }

        private boolean isInPool(T t) {
            for (int i = 0; i < this.mPoolSize; i++) {
                if (this.mPool[i] == t) {
                    return true;
                }
            }
            return false;
        }
    }
}
