package org.apache.commons.lang3.function;

import java.lang.Throwable;
import org.apache.commons.lang3.function.FailableIntToDoubleFunction;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableIntToDoubleFunction<E extends Throwable> {
    public static final FailableIntToDoubleFunction NOP = new FailableIntToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableIntToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableIntToDoubleFunction
        public final double applyAsDouble(int i) {
            return FailableIntToDoubleFunction.CC.lambda$static$0(i);
        }
    };

    double applyAsDouble(int i) throws Throwable;

    /* JADX INFO: renamed from: org.apache.commons.lang3.function.FailableIntToDoubleFunction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            FailableIntToDoubleFunction failableIntToDoubleFunction = FailableIntToDoubleFunction.NOP;
        }

        public static /* synthetic */ double lambda$static$0(int i) throws Throwable {
            return 0.0d;
        }

        public static <E extends Throwable> FailableIntToDoubleFunction<E> nop() {
            return FailableIntToDoubleFunction.NOP;
        }
    }
}
