package com.volcengine.cloudphone.apiservice;

import android.widget.EditText;

/* JADX INFO: loaded from: classes3.dex */
public interface LocalInputManager {

    public interface RemoteInputCallBack {

        /* JADX INFO: renamed from: com.volcengine.cloudphone.apiservice.LocalInputManager$RemoteInputCallBack$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: D8$$SyntheticClass */
        public final /* synthetic */ class CC {
            public static void $default$onGetKeyboardTypeResult(RemoteInputCallBack _this, int i) {
            }

            public static void $default$onKeyboardTypeChanged(RemoteInputCallBack _this, int i, int i2, String str) {
            }
        }

        void onCommandHide();

        void onCommandShow();

        void onGetKeyboardTypeResult(int i);

        void onKeyboardTypeChanged(int i, int i2, String str);

        void onPrepare(String str, int i);

        void onRemoteKeyBoardEnabled(boolean z);

        void onTextChange(String str);

        void onTextInputEnableStateChanged(boolean z);
    }

    void closeAutoKeyBoard(boolean z);

    void coverCurrentEditTextMessage(String str);

    void enableShowCurrentInputText(boolean z);

    EditText getEditText();

    boolean getKeyboardEnable();

    int getKeyboardType();

    boolean isTextInputEnable();

    int sendInputText(String str);

    int setKeyBoardEnable(boolean z);

    int setKeyboardType(int i);

    void setRemoteInputCallBack(RemoteInputCallBack remoteInputCallBack);
}
