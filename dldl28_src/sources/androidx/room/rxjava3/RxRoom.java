package androidx.room.rxjava3;

import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public final class RxRoom {
    public static final Object NOTHING = new Object();

    static /* synthetic */ MaybeSource lambda$createFlowable$2(Maybe maybe, Object obj) throws Throwable {
        return maybe;
    }

    static /* synthetic */ MaybeSource lambda$createObservable$5(Maybe maybe, Object obj) throws Throwable {
        return maybe;
    }

    public static Flowable<Object> createFlowable(final RoomDatabase roomDatabase, final String... strArr) {
        return Flowable.create(new FlowableOnSubscribe() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) throws Throwable {
                RxRoom.lambda$createFlowable$1(strArr, roomDatabase, flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    static /* synthetic */ void lambda$createFlowable$1(String[] strArr, final RoomDatabase roomDatabase, final FlowableEmitter flowableEmitter) throws Throwable {
        final InvalidationTracker.Observer observer = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.rxjava3.RxRoom.1
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(Set<String> set) {
                if (flowableEmitter.isCancelled()) {
                    return;
                }
                flowableEmitter.onNext(RxRoom.NOTHING);
            }
        };
        if (!flowableEmitter.isCancelled()) {
            roomDatabase.getInvalidationTracker().addObserver(observer);
            flowableEmitter.setDisposable(Disposable.CC.fromAction(new Action() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda6
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    roomDatabase.getInvalidationTracker().removeObserver(observer);
                }
            }));
        }
        if (flowableEmitter.isCancelled()) {
            return;
        }
        flowableEmitter.onNext(NOTHING);
    }

    public static <T> Flowable<T> createFlowable(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<T> callable) {
        Scheduler schedulerFrom = Schedulers.from(getExecutor(roomDatabase, z));
        final Maybe maybeFromCallable = Maybe.fromCallable(callable);
        return (Flowable<T>) createFlowable(roomDatabase, strArr).subscribeOn(schedulerFrom).unsubscribeOn(schedulerFrom).observeOn(schedulerFrom).flatMapMaybe(new Function() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return RxRoom.lambda$createFlowable$2(maybeFromCallable, obj);
            }
        });
    }

    public static Observable<Object> createObservable(final RoomDatabase roomDatabase, final String... strArr) {
        return Observable.create(new ObservableOnSubscribe() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                RxRoom.lambda$createObservable$4(strArr, roomDatabase, observableEmitter);
            }
        });
    }

    static /* synthetic */ void lambda$createObservable$4(String[] strArr, final RoomDatabase roomDatabase, final ObservableEmitter observableEmitter) throws Throwable {
        final InvalidationTracker.Observer observer = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.rxjava3.RxRoom.2
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(Set<String> set) {
                observableEmitter.onNext(RxRoom.NOTHING);
            }
        };
        roomDatabase.getInvalidationTracker().addObserver(observer);
        observableEmitter.setDisposable(Disposable.CC.fromAction(new Action() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                roomDatabase.getInvalidationTracker().removeObserver(observer);
            }
        }));
        observableEmitter.onNext(NOTHING);
    }

    public static <T> Observable<T> createObservable(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<T> callable) {
        Scheduler schedulerFrom = Schedulers.from(getExecutor(roomDatabase, z));
        final Maybe maybeFromCallable = Maybe.fromCallable(callable);
        return (Observable<T>) createObservable(roomDatabase, strArr).subscribeOn(schedulerFrom).unsubscribeOn(schedulerFrom).observeOn(schedulerFrom).flatMapMaybe(new Function() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return RxRoom.lambda$createObservable$5(maybeFromCallable, obj);
            }
        });
    }

    public static <T> Single<T> createSingle(final Callable<T> callable) {
        return Single.create(new SingleOnSubscribe() { // from class: androidx.room.rxjava3.RxRoom$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                RxRoom.lambda$createSingle$6(callable, singleEmitter);
            }
        });
    }

    static /* synthetic */ void lambda$createSingle$6(Callable callable, SingleEmitter singleEmitter) throws Throwable {
        try {
            singleEmitter.onSuccess(callable.call());
        } catch (EmptyResultSetException e) {
            singleEmitter.tryOnError(e);
        }
    }

    private static Executor getExecutor(RoomDatabase roomDatabase, boolean z) {
        if (z) {
            return roomDatabase.getTransactionExecutor();
        }
        return roomDatabase.getQueryExecutor();
    }

    private RxRoom() {
    }
}
