package com.huya.mtp.hyns.rx;

import com.huya.mtp.hyns.NSCallAdapter;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.utils.TypeUtils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSRxCallAdapterFactory extends NSCallAdapter.Factory {
    @Override // com.huya.mtp.hyns.NSCallAdapter.Factory
    public NSCallAdapter getAdapter(Method method) {
        Type parameterUpperBound;
        boolean z;
        boolean z2;
        Type genericReturnType = method.getGenericReturnType();
        Class<?> rawType = TypeUtils.getRawType(genericReturnType);
        if (rawType == Completable.class) {
            return new RxWupCallAdapter(Void.class, false, true, false, false, false, true);
        }
        boolean z3 = rawType == Flowable.class;
        boolean z4 = rawType == Single.class;
        boolean z5 = rawType == Maybe.class;
        if (rawType != Observable.class && rawType != NSObservable.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(genericReturnType instanceof ParameterizedType)) {
            String str = !z3 ? !z4 ? z5 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type parameterUpperBound2 = TypeUtils.getParameterUpperBound(0, (ParameterizedType) genericReturnType);
        Class<?> rawType2 = TypeUtils.getRawType(parameterUpperBound2);
        if (rawType2 == NSResponse.class) {
            if (!(parameterUpperBound2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            parameterUpperBound = TypeUtils.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound2);
            z = false;
        } else if (rawType2 == Result.class) {
            if (!(parameterUpperBound2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            parameterUpperBound = TypeUtils.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound2);
            z = true;
        } else {
            parameterUpperBound = parameterUpperBound2;
            z = false;
            z2 = true;
            return new RxWupCallAdapter(parameterUpperBound, z, z2, z3, z4, z5, false);
        }
        z2 = false;
        return new RxWupCallAdapter(parameterUpperBound, z, z2, z3, z4, z5, false);
    }
}
