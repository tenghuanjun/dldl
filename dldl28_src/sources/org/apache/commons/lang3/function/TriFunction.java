package org.apache.commons.lang3.function;

import java.util.Objects;
import java.util.function.Function;
import org.apache.commons.lang3.function.TriFunction;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    <W> TriFunction<T, U, V, W> andThen(Function<? super R, ? extends W> function);

    R apply(T t, U u, V v);

    /* JADX INFO: renamed from: org.apache.commons.lang3.function.TriFunction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static TriFunction $default$andThen(final TriFunction _this, final Function function) {
            Objects.requireNonNull(function);
            return new TriFunction() { // from class: org.apache.commons.lang3.function.TriFunction$$ExternalSyntheticLambda0
                @Override // org.apache.commons.lang3.function.TriFunction
                public /* synthetic */ TriFunction andThen(Function function2) {
                    return TriFunction.CC.$default$andThen(this, function2);
                }

                @Override // org.apache.commons.lang3.function.TriFunction
                public final Object apply(Object obj, Object obj2, Object obj3) {
                    return function.apply(_this.apply(obj, obj2, obj3));
                }
            };
        }
    }
}
