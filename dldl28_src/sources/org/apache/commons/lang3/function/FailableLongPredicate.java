package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;
import org.apache.commons.lang3.function.FailableLongPredicate;

/* JADX INFO: loaded from: classes4.dex */
@FunctionalInterface
public interface FailableLongPredicate<E extends Throwable> {
    public static final FailableLongPredicate FALSE = new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda3
        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public /* synthetic */ FailableLongPredicate and(FailableLongPredicate failableLongPredicate) {
            return FailableLongPredicate.CC.$default$and(this, failableLongPredicate);
        }

        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public /* synthetic */ FailableLongPredicate negate() {
            return FailableLongPredicate.CC.$default$negate(this);
        }

        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public /* synthetic */ FailableLongPredicate or(FailableLongPredicate failableLongPredicate) {
            return FailableLongPredicate.CC.$default$or(this, failableLongPredicate);
        }

        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public final boolean test(long j) {
            return FailableLongPredicate.CC.lambda$static$0(j);
        }
    };
    public static final FailableLongPredicate TRUE = new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda4
        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public /* synthetic */ FailableLongPredicate and(FailableLongPredicate failableLongPredicate) {
            return FailableLongPredicate.CC.$default$and(this, failableLongPredicate);
        }

        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public /* synthetic */ FailableLongPredicate negate() {
            return FailableLongPredicate.CC.$default$negate(this);
        }

        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public /* synthetic */ FailableLongPredicate or(FailableLongPredicate failableLongPredicate) {
            return FailableLongPredicate.CC.$default$or(this, failableLongPredicate);
        }

        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public final boolean test(long j) {
            return FailableLongPredicate.CC.lambda$static$1(j);
        }
    };

    FailableLongPredicate<E> and(FailableLongPredicate<E> failableLongPredicate);

    FailableLongPredicate<E> negate();

    FailableLongPredicate<E> or(FailableLongPredicate<E> failableLongPredicate);

    boolean test(long j) throws Throwable;

    /* JADX INFO: renamed from: org.apache.commons.lang3.function.FailableLongPredicate$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            FailableLongPredicate failableLongPredicate = FailableLongPredicate.FALSE;
        }

        public static /* synthetic */ boolean lambda$static$0(long j) throws Throwable {
            return false;
        }

        public static /* synthetic */ boolean lambda$static$1(long j) throws Throwable {
            return true;
        }

        public static <E extends Throwable> FailableLongPredicate<E> falsePredicate() {
            return FailableLongPredicate.FALSE;
        }

        public static <E extends Throwable> FailableLongPredicate<E> truePredicate() {
            return FailableLongPredicate.TRUE;
        }

        public static FailableLongPredicate $default$and(final FailableLongPredicate _this, final FailableLongPredicate failableLongPredicate) {
            Objects.requireNonNull(failableLongPredicate);
            return new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda0
                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate and(FailableLongPredicate failableLongPredicate2) {
                    return FailableLongPredicate.CC.$default$and(this, failableLongPredicate2);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate negate() {
                    return FailableLongPredicate.CC.$default$negate(this);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate or(FailableLongPredicate failableLongPredicate2) {
                    return FailableLongPredicate.CC.$default$or(this, failableLongPredicate2);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public final boolean test(long j) {
                    return FailableLongPredicate.CC.$private$lambda$and$2(_this, failableLongPredicate, j);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$and$2(FailableLongPredicate _this, FailableLongPredicate failableLongPredicate, long j) throws Throwable {
            return _this.test(j) && failableLongPredicate.test(j);
        }

        public static FailableLongPredicate $default$negate(final FailableLongPredicate _this) {
            return new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda1
                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate and(FailableLongPredicate failableLongPredicate) {
                    return FailableLongPredicate.CC.$default$and(this, failableLongPredicate);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate negate() {
                    return FailableLongPredicate.CC.$default$negate(this);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate or(FailableLongPredicate failableLongPredicate) {
                    return FailableLongPredicate.CC.$default$or(this, failableLongPredicate);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public final boolean test(long j) {
                    return FailableLongPredicate.CC.$private$lambda$negate$3(_this, j);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$negate$3(FailableLongPredicate _this, long j) throws Throwable {
            return !_this.test(j);
        }

        public static FailableLongPredicate $default$or(final FailableLongPredicate _this, final FailableLongPredicate failableLongPredicate) {
            Objects.requireNonNull(failableLongPredicate);
            return new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda2
                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate and(FailableLongPredicate failableLongPredicate2) {
                    return FailableLongPredicate.CC.$default$and(this, failableLongPredicate2);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate negate() {
                    return FailableLongPredicate.CC.$default$negate(this);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public /* synthetic */ FailableLongPredicate or(FailableLongPredicate failableLongPredicate2) {
                    return FailableLongPredicate.CC.$default$or(this, failableLongPredicate2);
                }

                @Override // org.apache.commons.lang3.function.FailableLongPredicate
                public final boolean test(long j) {
                    return FailableLongPredicate.CC.$private$lambda$or$4(_this, failableLongPredicate, j);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$or$4(FailableLongPredicate _this, FailableLongPredicate failableLongPredicate, long j) throws Throwable {
            return _this.test(j) || failableLongPredicate.test(j);
        }
    }
}
