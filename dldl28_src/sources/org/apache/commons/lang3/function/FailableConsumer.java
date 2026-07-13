package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;
import org.apache.commons.lang3.function.FailableConsumer;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableConsumer<T, E extends Throwable> {
    public static final FailableConsumer NOP = new FailableConsumer() { // from class: org.apache.commons.lang3.function.FailableConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableConsumer
        public final void accept(Object obj) throws Throwable {
            FailableConsumer.CC.lambda$static$0(obj);
        }

        @Override // org.apache.commons.lang3.function.FailableConsumer
        public /* synthetic */ FailableConsumer andThen(FailableConsumer failableConsumer) {
            return FailableConsumer.CC.$default$andThen(this, failableConsumer);
        }
    };

    void accept(T t) throws Throwable;

    FailableConsumer<T, E> andThen(FailableConsumer<? super T, E> failableConsumer);

    /* JADX INFO: renamed from: org.apache.commons.lang3.function.FailableConsumer$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            FailableConsumer failableConsumer = FailableConsumer.NOP;
        }

        public static /* synthetic */ void lambda$static$0(Object obj) throws Throwable {
        }

        public static <T, E extends Throwable> FailableConsumer<T, E> nop() {
            return FailableConsumer.NOP;
        }

        public static FailableConsumer $default$andThen(final FailableConsumer _this, final FailableConsumer failableConsumer) {
            Objects.requireNonNull(failableConsumer);
            return new FailableConsumer() { // from class: org.apache.commons.lang3.function.FailableConsumer$$ExternalSyntheticLambda1
                @Override // org.apache.commons.lang3.function.FailableConsumer
                public final void accept(Object obj) throws Throwable {
                    FailableConsumer.CC.$private$lambda$andThen$1(_this, failableConsumer, obj);
                }

                @Override // org.apache.commons.lang3.function.FailableConsumer
                public /* synthetic */ FailableConsumer andThen(FailableConsumer failableConsumer2) {
                    return FailableConsumer.CC.$default$andThen(this, failableConsumer2);
                }
            };
        }

        public static /* synthetic */ void $private$lambda$andThen$1(FailableConsumer _this, FailableConsumer failableConsumer, Object obj) throws Throwable {
            _this.accept(obj);
            failableConsumer.accept(obj);
        }
    }
}
