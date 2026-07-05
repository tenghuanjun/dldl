package com.duowan.kiwi.barrage;

import android.graphics.Bitmap;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageEvent {

    public static class CleanVideoBarrage {
    }

    public static class PauseVideoBarrage {
    }

    public static class ResumeVideoBarrage {
    }

    public static class ShowAntiBlockTip {
    }

    public static class PubText {
        public int mDefColor;
        public int mSpeedMode;
        public ArrayList<String> mTestList;

        public PubText(ArrayList arrayList, int i, int i2) {
            this.mTestList = arrayList;
            this.mDefColor = i;
            this.mSpeedMode = i2;
        }
    }

    public static class TextAboutToSendV2 {
        public String arg3;
        public Integer mBulletColor;
        public String mContent;

        public TextAboutToSendV2(String str, Integer num, String str2) {
            this.mContent = str;
            this.mBulletColor = num;
            this.arg3 = str2;
        }
    }

    public static class BarrageAlphaChanged {
        public Float arg0;

        public BarrageAlphaChanged(Float f) {
            this.arg0 = f;
        }
    }

    public static class BarrageSizeChanged {
        public Integer arg0;

        public BarrageSizeChanged(Integer num) {
            this.arg0 = num;
        }
    }

    public static class BarrageModelChanged {
        public int mode;

        public BarrageModelChanged(int i) {
            this.mode = i;
        }
    }

    public static class BarrageWithFace {
        public GunPowder mGunPowder;

        public BarrageWithFace(GunPowder gunPowder) {
            this.mGunPowder = gunPowder;
        }
    }

    public static class BarrageWithAttach {
        public Object mAttach;

        public BarrageWithAttach(Object obj) {
            this.mAttach = obj;
        }
    }

    public static class RequireMarqueeInSurface {
        public Bitmap bitmap;
        public long duration;

        public RequireMarqueeInSurface(Bitmap bitmap, long j) {
            this.bitmap = bitmap;
            this.duration = j;
        }
    }
}
