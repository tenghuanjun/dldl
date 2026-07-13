package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;
import org.apache.commons.lang3.function.FailableIntConsumer;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableIntConsumer<E extends Throwable> {
    public static final FailableIntConsumer NOP = new FailableIntConsumer() { // from class: org.apache.commons.lang3.function.FailableIntConsumer$$ExternalSyntheticLambda1
        @Override // org.apache.commons.lang3.function.FailableIntConsumer
        public final void accept(int i) throws Throwable {
            FailableIntConsumer.CC.lambda$static$0(i);
        }

        @Override // org.apache.commons.lang3.function.FailableIntConsumer
        public /* synthetic */ FailableIntConsumer andThen(FailableIntConsumer failableIntConsumer) {
            return FailableIntConsumer.CC.$default$andThen(this, failableIntConsumer);
        }
    };

    void accept(int i) throws Throwable;

    FailableIntConsumer<E> andThen(FailableIntConsumer<E> failableIntConsumer);

    /* JADX INFO: renamed from: org.apache.commons.lang3.function.FailableIntConsumer$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            FailableIntConsumer failableIntConsumer = FailableIntConsumer.NOP;
        }

        public static /* synthetic */ void lambda$static$0(int i) throws Throwable {
        }

        public static <E extends Throwable> FailableIntConsumer<E> nop() {
            return FailableIntConsumer.NOP;
        }

        public static FailableIntConsumer $default$andThen(final FailableIntConsumer _this, final FailableIntConsumer failableIntConsumer) {
            Objects.requireNonNull(failableIntConsumer);
            return new FailableIntConsumer() { // from class: org.apache.commons.lang3.function.FailableIntConsumer$$ExternalSyntheticLambda0
                @Override // org.apache.commons.lang3.function.FailableIntConsumer
                public final void accept(int i) throws Throwable {
                    FailableIntConsumer.CC.$private$lambda$andThen$1(_this, failableIntConsumer, i);
                }

                @Override // org.apache.commons.lang3.function.FailableIntConsumer
                public /* synthetic */ FailableIntConsumer andThen(FailableIntConsumer failableIntConsumer2) {
                    return FailableIntConsumer.CC.$default$andThen(this, failableIntConsumer2);
                }
            };
        }

        public static /* synthetic */ void $private$lambda$andThen$1(FailableIntConsumer _this, FailableIntConsumer failableIntConsumer, int i) throws Throwable {
            _this.accept(i);
            failableIntConsumer.accept(i);
        }
    }
}
