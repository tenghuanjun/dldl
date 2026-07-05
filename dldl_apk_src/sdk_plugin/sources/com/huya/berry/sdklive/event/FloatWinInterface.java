package com.huya.berry.sdklive.event;

import com.duowan.auk.NoProguard;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FloatWinInterface implements NoProguard {

    public static class GetSendTime implements NoProguard {
    }

    public static class OnEndLive implements NoProguard {
    }

    public static class OnStartLive implements NoProguard {
    }

    public static class onKaLongTime implements NoProguard {
    }

    public static class onPauseAudio implements NoProguard {
    }

    public static class onPauseLive implements NoProguard {
    }

    public static class onResumeAudio implements NoProguard {
    }

    public static class onResumeLive implements NoProguard {
    }

    public static class GetSendTimeCallback implements NoProguard {
        public int timeDelta;

        public GetSendTimeCallback(int i) {
            this.timeDelta = i;
        }
    }

    public static class OnAutoPauseLive implements NoProguard {
        public boolean isPause;

        public OnAutoPauseLive(boolean z) {
            this.isPause = z;
        }
    }
}
