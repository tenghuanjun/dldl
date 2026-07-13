package org.apache.commons.lang3.compare;

import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class ComparableUtils {

    public static class ComparableCheckBuilder<A extends Comparable<A>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final A f1152a;

        private ComparableCheckBuilder(A a2) {
            this.f1152a = a2;
        }

        public boolean between(A a2, A a3) {
            return betweenOrdered(a2, a3) || betweenOrdered(a3, a2);
        }

        public boolean betweenExclusive(A a2, A a3) {
            return betweenOrderedExclusive(a2, a3) || betweenOrderedExclusive(a3, a2);
        }

        private boolean betweenOrdered(A a2, A a3) {
            return greaterThanOrEqualTo(a2) && lessThanOrEqualTo(a3);
        }

        private boolean betweenOrderedExclusive(A a2, A a3) {
            return greaterThan(a2) && lessThan(a3);
        }

        public boolean equalTo(A a2) {
            return this.f1152a.compareTo(a2) == 0;
        }

        public boolean greaterThan(A a2) {
            return this.f1152a.compareTo(a2) > 0;
        }

        public boolean greaterThanOrEqualTo(A a2) {
            return this.f1152a.compareTo(a2) >= 0;
        }

        public boolean lessThan(A a2) {
            return this.f1152a.compareTo(a2) < 0;
        }

        public boolean lessThanOrEqualTo(A a2) {
            return this.f1152a.compareTo(a2) <= 0;
        }
    }

    public static <A extends Comparable<A>> Predicate<A> between(final A a2, final A a3) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).between(a2, a3);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> betweenExclusive(final A a2, final A a3) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).betweenExclusive(a2, a3);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> ge(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).greaterThanOrEqualTo(a2);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> gt(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).greaterThan(a2);
            }
        };
    }

    public static <A extends Comparable<A>> ComparableCheckBuilder<A> is(A a2) {
        return new ComparableCheckBuilder<>(a2);
    }

    public static <A extends Comparable<A>> Predicate<A> le(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).lessThanOrEqualTo(a2);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> lt(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).lessThan(a2);
            }
        };
    }

    private ComparableUtils() {
    }
}
