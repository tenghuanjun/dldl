package com.taptap.sdk.base.utils.prefs;

import android.content.SharedPreferences;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: SharedPreferenceDelegate.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ#\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0001H\u0002¢\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\u00028\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0096\u0002¢\u0006\u0002\u0010\u0013J#\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u0002H\u0001H\u0002¢\u0006\u0002\u0010\u0017J,\u0010\u0018\u001a\u00020\u00152\b\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0016\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0019R\u0010\u0010\b\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/taptap/sdk/base/utils/prefs/SharedPreferenceDelegate;", "T", "Lkotlin/properties/ReadWriteProperty;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "name", "", "default", "(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/Object;)V", "Ljava/lang/Object;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "getPreference", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "putPreference", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "tap-base_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SharedPreferenceDelegate<T> implements ReadWriteProperty<Object, T> {
    private final T default;
    private final ReentrantReadWriteLock lock;
    private final String name;
    private final SharedPreferences sharedPreferences;

    public SharedPreferenceDelegate(SharedPreferences sharedPreferences, String name, T t) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        Intrinsics.checkNotNullParameter(name, "name");
        this.sharedPreferences = sharedPreferences;
        this.name = name;
        this.default = t;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public T getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
        lock.lock();
        try {
            return getPreference(this.name, this.default);
        } finally {
            lock.unlock();
        }
    }

    @Override // kotlin.properties.ReadWriteProperty
    public void setValue(Object thisRef, KProperty<?> property, T value) {
        Intrinsics.checkNotNullParameter(property, "property");
        ReentrantReadWriteLock reentrantReadWriteLock = this.lock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            putPreference(this.name, value);
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> T getPreference(String name, T t) {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (t instanceof String) {
            return (T) sharedPreferences.getString(name, (String) t);
        }
        if (t instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(name, ((Boolean) t).booleanValue()));
        }
        if (t instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(name, ((Number) t).intValue()));
        }
        if (t instanceof Float) {
            return (T) Float.valueOf(sharedPreferences.getFloat(name, ((Number) t).floatValue()));
        }
        if (t instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(name, ((Number) t).longValue()));
        }
        throw new IllegalArgumentException("type can't be saved into SharedPreferences");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> void putPreference(String name, T value) {
        SharedPreferences.Editor editorPutLong;
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        if (value instanceof String) {
            editorPutLong = editorEdit.putString(name, (String) value);
        } else if (value instanceof Boolean) {
            editorPutLong = editorEdit.putBoolean(name, ((Boolean) value).booleanValue());
        } else if (value instanceof Integer) {
            editorPutLong = editorEdit.putInt(name, ((Number) value).intValue());
        } else if (value instanceof Float) {
            editorPutLong = editorEdit.putFloat(name, ((Number) value).floatValue());
        } else {
            if (!(value instanceof Long)) {
                throw new IllegalArgumentException("type can't be saved into SharedPreferences");
            }
            editorPutLong = editorEdit.putLong(name, ((Number) value).longValue());
        }
        editorPutLong.commit();
    }
}
