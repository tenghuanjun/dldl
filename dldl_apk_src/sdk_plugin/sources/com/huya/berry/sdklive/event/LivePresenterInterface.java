package com.huya.berry.sdklive.event;

import com.duowan.auk.NoProguard;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LivePresenterInterface implements NoProguard {

    public static class CloseBtnClicked implements NoProguard {
    }

    public static class ExpandMsgContainer implements NoProguard {
    }

    public static class LiveToolState implements NoProguard {
        public boolean mIsShow;

        public LiveToolState(boolean z) {
            this.mIsShow = z;
        }
    }

    public static class ToolHalfHide implements NoProguard {
        public boolean mHalfHide;

        public ToolHalfHide(boolean z) {
            this.mHalfHide = z;
        }
    }

    public static class LiveToolAction implements NoProguard {
        public static final int ACTION_TOOL_HALF_HIDE = 9;
        public int mAction;
        public String mText;

        public LiveToolAction(int i, String str) {
            this.mAction = i;
            this.mText = str;
        }

        public LiveToolAction(int i) {
            this(i, null);
        }
    }
}
