package com.huya.berry.gamesdk.module.commonevent;

import android.view.View;
import com.duowan.HUYA.UserId;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CommonEvent {

    public static class BackFloating {
    }

    public static class CloseFloating {
    }

    public static class FullScreen {
    }

    public static class GoUserCenter {
    }

    public static class HideBottomUIMenu {
    }

    public static class OpenFloating {
    }

    public static class OpenLine {
    }

    public static class OpenRealFloat {
    }

    public static class RePushLive {
    }

    public static class ShowFloating {
    }

    public static class ShowFloatingRefresh {
    }

    public static class SmallWindowPlay {
    }

    public static class WatchLive {
    }

    public static class onBackPress {
    }

    public static class FullScreenCloseFloating {
        public boolean fullScreen;

        public FullScreenCloseFloating(boolean z) {
            this.fullScreen = z;
        }
    }

    public static class NormalPlay {
        public boolean fullScreen;

        public NormalPlay(boolean z) {
            this.fullScreen = z;
        }
    }

    public static class PauseOrPlay {
        public boolean hasPause;

        public PauseOrPlay(boolean z) {
            this.hasPause = z;
        }
    }

    public static class SwitchDanmu {
        public boolean open;

        public SwitchDanmu(boolean z) {
            this.open = z;
        }
    }

    public static class SwitchVoice {
        public boolean open;

        public SwitchVoice(boolean z) {
            this.open = z;
        }
    }

    public static class OnLiveUserCount {
        public int count;

        public OnLiveUserCount(int i) {
            this.count = i;
        }
    }

    public static class OpenFloatEdit {
        public View view;

        public OpenFloatEdit(View view) {
            this.view = view;
        }
    }

    public static class GetComponentDistribute {
        public int gameId;

        public GetComponentDistribute(int i) {
            this.gameId = i;
        }
    }

    public static class SendPubText {
        public long channalId;
        public String mText;
        public long subId;
        public long uid;
        public UserId userId;

        public SendPubText(String str, long j, long j2, long j3, UserId userId) {
            this.mText = str;
            this.uid = j;
            this.channalId = j2;
            this.subId = j3;
            this.userId = userId;
        }
    }

    public static class CertificateFinish {
        public String message;
        public int resultCode;

        public CertificateFinish(int i, String str) {
            this.resultCode = i;
            this.message = str;
        }
    }

    public static class WupMetricReport {
        public int init;
        public int login;
        public int startLive;

        public WupMetricReport(int i, int i2, int i3) {
            this.init = i;
            this.login = i2;
            this.startLive = i3;
        }
    }
}
