package com.cmic.sso.sdk;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class AuthRegisterViewConfig {
    private com.mobile.auth.m.a customInterface;
    private int rootViewId;
    private View view;

    public static class Builder {
        private com.mobile.auth.m.a customInterface;
        private int rootViewId;
        private View view;

        public AuthRegisterViewConfig build() {
            return new AuthRegisterViewConfig(this);
        }

        public Builder setCustomInterface(com.mobile.auth.m.a aVar) {
            this.customInterface = aVar;
            return this;
        }

        public Builder setRootViewId(int i) {
            this.rootViewId = i;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }
    }

    public static class RootViewId {
        public static final int ROOT_VIEW_ID_BODY = 0;
        public static final int ROOT_VIEW_ID_TITLE_BAR = 1;
    }

    private AuthRegisterViewConfig(Builder builder) {
        this.customInterface = builder.customInterface;
        this.view = builder.view;
        this.rootViewId = builder.rootViewId;
    }

    public com.mobile.auth.m.a getCustomInterface() {
        return this.customInterface;
    }

    public int getRootViewId() {
        return this.rootViewId;
    }

    public View getView() {
        return this.view;
    }
}
