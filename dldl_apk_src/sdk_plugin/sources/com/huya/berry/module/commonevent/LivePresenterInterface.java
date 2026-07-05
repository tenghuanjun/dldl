package com.huya.berry.module.commonevent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LivePresenterInterface {

    public static class CloseBtnClicked {
    }

    public static class ExpandMsgContainer {
    }

    public static class LiveToolState {
        public boolean mIsShow;

        public LiveToolState(boolean z) {
            this.mIsShow = z;
        }
    }

    public static class ToolHalfHide {
        public boolean mHalfHide;

        public ToolHalfHide(boolean z) {
            this.mHalfHide = z;
        }
    }

    public static class LiveToolAction {
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
