package io.reactivex.rxjava3.internal.jdk8;

import com.volcengine.j.l$$ExternalSyntheticApiModelOutline0;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.observers.BasicFuseableObserver;
import java.util.Objects;
import java.util.Optional;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class ObservableMapOptional<T, R> extends Observable<R> {
    final Function<? super T, Optional<? extends R>> mapper;
    final Observable<T> source;

    public ObservableMapOptional(Observable<T> source, Function<? super T, Optional<? extends R>> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new MapOptionalObserver(observer, this.mapper));
    }

    static final class MapOptionalObserver<T, R> extends BasicFuseableObserver<T, R> {
        final Function<? super T, Optional<? extends R>> mapper;

        MapOptionalObserver(Observer<? super R> downstream, Function<? super T, Optional<? extends R>> mapper) {
            super(downstream);
            this.mapper = mapper;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                Optional optionalM112m = l$$ExternalSyntheticApiModelOutline0.m112m(Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null Optional"));
                if (optionalM112m.isPresent()) {
                    this.downstream.onNext((Object) optionalM112m.get());
                }
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public R poll() throws Throwable {
            Optional optionalM112m;
            do {
                T tPoll = this.qd.poll();
                if (tPoll == null) {
                    return null;
                }
                optionalM112m = l$$ExternalSyntheticApiModelOutline0.m112m(Objects.requireNonNull(this.mapper.apply(tPoll), "The mapper returned a null Optional"));
            } while (!optionalM112m.isPresent());
            return (R) optionalM112m.get();
        }
    }
}
