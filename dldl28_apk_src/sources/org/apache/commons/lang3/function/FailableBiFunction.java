package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;
import org.apache.commons.lang3.function.FailableBiFunction;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableBiFunction<T, U, R, E extends Throwable> {
    public static final FailableBiFunction NOP = new FailableBiFunction() { // from class: org.apache.commons.lang3.function.FailableBiFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableBiFunction
        public /* synthetic */ FailableBiFunction andThen(FailableFunction failableFunction) {
            return FailableBiFunction.CC.$default$andThen(this, failableFunction);
        }

        @Override // org.apache.commons.lang3.function.FailableBiFunction
        public final Object apply(Object obj, Object obj2) {
            return FailableBiFunction.CC.lambda$static$0(obj, obj2);
        }
    };

    <V> FailableBiFunction<T, U, V, E> andThen(FailableFunction<? super R, ? extends V, E> failableFunction);

    R apply(T t, U u) throws Throwable;

    /* JADX INFO: renamed from: org.apache.commons.lang3.function.FailableBiFunction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            FailableBiFunction failableBiFunction = FailableBiFunction.NOP;
        }

        public static /* synthetic */ Object lambda$static$0(Object obj, Object obj2) throws Throwable {
            return null;
        }

        public static <T, U, R, E extends Throwable> FailableBiFunction<T, U, R, E> nop() {
            return FailableBiFunction.NOP;
        }

        public static FailableBiFunction $default$andThen(final FailableBiFunction _this, final FailableFunction failableFunction) {
            Objects.requireNonNull(failableFunction);
            return new FailableBiFunction() { // from class: org.apache.commons.lang3.function.FailableBiFunction$$ExternalSyntheticLambda1
                @Override // org.apache.commons.lang3.function.FailableBiFunction
                public /* synthetic */ FailableBiFunction andThen(FailableFunction failableFunction2) {
                    return FailableBiFunction.CC.$default$andThen(this, failableFunction2);
                }

                @Override // org.apache.commons.lang3.function.FailableBiFunction
                public final Object apply(Object obj, Object obj2) {
                    return failableFunction.apply(_this.apply(obj, obj2));
                }
            };
        }
    }
}
