package com.taptap.sdk.db.biz.gameplay.reporter;

import com.taptap.sdk.db.data.model.GameDuration;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GameDurationReporter.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/reporter/GameDurationReporter;", "", "submitDeviceDuration", "", "duration", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitUserDurations", "userDurations", "", "Lcom/taptap/sdk/db/data/model/GameDuration$User;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GameDurationReporter {
    Object submitDeviceDuration(long j, Continuation<? super Unit> continuation);

    Object submitUserDurations(List<GameDuration.User> list, Continuation<? super Unit> continuation);
}
