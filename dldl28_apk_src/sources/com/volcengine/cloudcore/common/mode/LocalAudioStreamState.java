package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: classes3.dex */
public enum LocalAudioStreamState {
    LOCAL_AUDIO_STREAM_STATE_STOPPED(0),
    LOCAL_AUDIO_STREAM_STATE_RECORDING(1),
    LOCAL_AUDIO_STREAM_STATE_ENCODING(2),
    LOCAL_AUDIO_STREAM_STATE_FAILED(3);

    private int value;

    /* JADX INFO: renamed from: com.volcengine.cloudcore.common.mode.LocalAudioStreamState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$volcengine$cloudcore$common$mode$LocalAudioStreamState;

        static {
            int[] iArr = new int[LocalAudioStreamState.values().length];
            $SwitchMap$com$volcengine$cloudcore$common$mode$LocalAudioStreamState = iArr;
            try {
                iArr[LocalAudioStreamState.LOCAL_AUDIO_STREAM_STATE_STOPPED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$volcengine$cloudcore$common$mode$LocalAudioStreamState[LocalAudioStreamState.LOCAL_AUDIO_STREAM_STATE_RECORDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$volcengine$cloudcore$common$mode$LocalAudioStreamState[LocalAudioStreamState.LOCAL_AUDIO_STREAM_STATE_ENCODING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$volcengine$cloudcore$common$mode$LocalAudioStreamState[LocalAudioStreamState.LOCAL_AUDIO_STREAM_STATE_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    LocalAudioStreamState(int i) {
        this.value = i;
    }

    public static LocalAudioStreamState fromId(int i) {
        for (LocalAudioStreamState localAudioStreamState : values()) {
            if (localAudioStreamState.value() == i) {
                return localAudioStreamState;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = AnonymousClass1.$SwitchMap$com$volcengine$cloudcore$common$mode$LocalAudioStreamState[ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "kLocalAudioStreamStateFailed" : "kLocalAudioStreamStateEncoding" : "kLocalAudioStreamStateRecording" : "kLocalAudioStreamStateStopped";
    }

    public int value() {
        return this.value;
    }
}
