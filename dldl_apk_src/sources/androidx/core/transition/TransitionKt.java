package androidx.core.transition;

import android.transition.Transition;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u001aÆ\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\r\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u000f\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0011\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b¨\u0006\u0013"}, d2 = {"addListener", "Landroid/transition/Transition$TransitionListener;", "Landroid/transition/Transition;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "transition", "", "onStart", "onCancel", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnResume", "doOnStart", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class TransitionKt {
    public static /* synthetic */ Transition.TransitionListener addListener$default(Transition addListener, Function1 onEnd, Function1 function1, Function1 function12, Function1 onResume, Function1 onPause, int i, Object obj) {
        if ((i & 1) != 0) {
            onEnd = new Function1<Transition, Unit>() { // from class: androidx.core.transition.TransitionKt.addListener.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Transition transition) {
                    invoke2(transition);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull Transition it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        Function1 onStart = (i & 2) != 0 ? new Function1<Transition, Unit>() { // from class: androidx.core.transition.TransitionKt.addListener.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Transition transition) {
                invoke2(transition);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Transition it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }
        } : function1;
        Function1 onCancel = (i & 4) != 0 ? new Function1<Transition, Unit>() { // from class: androidx.core.transition.TransitionKt.addListener.3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Transition transition) {
                invoke2(transition);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Transition it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }
        } : function12;
        if ((i & 8) != 0) {
            onResume = new Function1<Transition, Unit>() { // from class: androidx.core.transition.TransitionKt.addListener.4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Transition transition) {
                    invoke2(transition);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull Transition it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        if ((i & 16) != 0) {
            onPause = new Function1<Transition, Unit>() { // from class: androidx.core.transition.TransitionKt.addListener.5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Transition transition) {
                    invoke2(transition);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull Transition it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(addListener, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(onEnd, "onEnd");
        Intrinsics.checkParameterIsNotNull(onStart, "onStart");
        Intrinsics.checkParameterIsNotNull(onCancel, "onCancel");
        Intrinsics.checkParameterIsNotNull(onResume, "onResume");
        Intrinsics.checkParameterIsNotNull(onPause, "onPause");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(onEnd, onResume, onPause, onCancel, onStart);
        addListener.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    @RequiresApi(19)
    @NotNull
    public static final Transition.TransitionListener addListener(@NotNull Transition addListener, @NotNull Function1<? super Transition, Unit> onEnd, @NotNull Function1<? super Transition, Unit> onStart, @NotNull Function1<? super Transition, Unit> onCancel, @NotNull Function1<? super Transition, Unit> onResume, @NotNull Function1<? super Transition, Unit> onPause) {
        Intrinsics.checkParameterIsNotNull(addListener, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(onEnd, "onEnd");
        Intrinsics.checkParameterIsNotNull(onStart, "onStart");
        Intrinsics.checkParameterIsNotNull(onCancel, "onCancel");
        Intrinsics.checkParameterIsNotNull(onResume, "onResume");
        Intrinsics.checkParameterIsNotNull(onPause, "onPause");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(onEnd, onResume, onPause, onCancel, onStart);
        addListener.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    @RequiresApi(19)
    @NotNull
    public static final Transition.TransitionListener doOnEnd(@NotNull Transition doOnEnd, @NotNull final Function1<? super Transition, Unit> action) {
        Intrinsics.checkParameterIsNotNull(doOnEnd, "$this$doOnEnd");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnEnd$$inlined$addListener$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
                action.invoke(transition);
            }
        };
        doOnEnd.addListener(transitionListener);
        return transitionListener;
    }

    @RequiresApi(19)
    @NotNull
    public static final Transition.TransitionListener doOnStart(@NotNull Transition doOnStart, @NotNull final Function1<? super Transition, Unit> action) {
        Intrinsics.checkParameterIsNotNull(doOnStart, "$this$doOnStart");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnStart$$inlined$addListener$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
                action.invoke(transition);
            }
        };
        doOnStart.addListener(transitionListener);
        return transitionListener;
    }

    @RequiresApi(19)
    @NotNull
    public static final Transition.TransitionListener doOnCancel(@NotNull Transition doOnCancel, @NotNull final Function1<? super Transition, Unit> action) {
        Intrinsics.checkParameterIsNotNull(doOnCancel, "$this$doOnCancel");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnCancel$$inlined$addListener$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
                action.invoke(transition);
            }
        };
        doOnCancel.addListener(transitionListener);
        return transitionListener;
    }

    @RequiresApi(19)
    @NotNull
    public static final Transition.TransitionListener doOnResume(@NotNull Transition doOnResume, @NotNull final Function1<? super Transition, Unit> action) {
        Intrinsics.checkParameterIsNotNull(doOnResume, "$this$doOnResume");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnResume$$inlined$addListener$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
                action.invoke(transition);
            }
        };
        doOnResume.addListener(transitionListener);
        return transitionListener;
    }

    @RequiresApi(19)
    @NotNull
    public static final Transition.TransitionListener doOnPause(@NotNull Transition doOnPause, @NotNull final Function1<? super Transition, Unit> action) {
        Intrinsics.checkParameterIsNotNull(doOnPause, "$this$doOnPause");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnPause$$inlined$addListener$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(@NotNull Transition transition) {
                Intrinsics.checkParameterIsNotNull(transition, "transition");
                action.invoke(transition);
            }
        };
        doOnPause.addListener(transitionListener);
        return transitionListener;
    }
}
