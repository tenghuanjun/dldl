package com.volcengine.androidcloud.common.log;

/* JADX INFO: loaded from: classes3.dex */
public final class AcTargetFactory {

    /* JADX INFO: renamed from: com.volcengine.androidcloud.common.log.AcTargetFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$volcengine$androidcloud$common$log$AcTargetType;

        static {
            int[] iArr = new int[AcTargetType.values().length];
            $SwitchMap$com$volcengine$androidcloud$common$log$AcTargetType = iArr;
            try {
                iArr[AcTargetType.LocalStorageTarget.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$volcengine$androidcloud$common$log$AcTargetType[AcTargetType.InjectTarget.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$volcengine$androidcloud$common$log$AcTargetType[AcTargetType.LoggingTarget.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static AcLogTarget getTarget(AcTargetType acTargetType, int i) {
        return AnonymousClass1.$SwitchMap$com$volcengine$androidcloud$common$log$AcTargetType[acTargetType.ordinal()] != 1 ? new AcStubTarget(acTargetType.isActive(i)) : new AcStubCacheTarget(acTargetType.isActive(i));
    }
}
