package com.bytedance.applog;

import com.bytedance.applog.util.UriConstants;

/* JADX INFO: loaded from: classes2.dex */
public class UriConfig {
    public static final String DOMAIN_BUSINESS = "https://log-api.oceanengine.com";
    public static final String PATH_AB = "/service/2/abtest_config/";
    public static final String PATH_ACTIVE = "/service/2/app_alert_check/";
    public static final String PATH_ALINK_ATTRIBUTION = "/service/2/attribution_data";
    public static final String PATH_ALINK_QUERY = "/service/2/alink_data";
    public static final String PATH_CONFIG = "/service/2/log_settings/";
    public static final String PATH_DEVICE_UPDATE = "/service/2/device_update";
    public static final String PATH_ID_BIND = "/service/2/id_bind";
    public static final String PATH_PROFILE = "/service/2/profile/";
    public static final String PATH_REGISTER = "/service/2/device_register/";
    public static final String PATH_SEND = "/service/2/app_log/";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f182a;
    public String b;
    public String c;
    public String[] d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f183a;
        public String b;
        public String c;
        public String[] d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;

        public UriConfig build() {
            return new UriConfig(this, null);
        }

        public Builder setALinkAttributionUri(String str) {
            this.j = str;
            return this;
        }

        public Builder setALinkQueryUri(String str) {
            this.i = str;
            return this;
        }

        public Builder setAbUri(String str) {
            this.f = str;
            return this;
        }

        public Builder setActiveUri(String str) {
            this.c = str;
            return this;
        }

        public Builder setBusinessUri(String str) {
            this.h = str;
            return this;
        }

        public Builder setIDBindUri(String str) {
            this.k = str;
            return this;
        }

        public Builder setProfileUri(String str) {
            this.g = str;
            return this;
        }

        public Builder setRegisterUri(String str) {
            this.f183a = str;
            return this;
        }

        public Builder setReportOaidUri(String str) {
            this.b = str;
            return this;
        }

        public Builder setSendUris(String[] strArr) {
            this.d = strArr;
            return this;
        }

        public Builder setSettingUri(String str) {
            this.e = str;
            return this;
        }
    }

    public /* synthetic */ UriConfig(Builder builder, a aVar) {
        this.f182a = builder.f183a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
    }

    public static UriConfig createByDomain(String str, String[] strArr) {
        Builder builder = new Builder();
        builder.setRegisterUri(str + PATH_REGISTER).setReportOaidUri(str + PATH_DEVICE_UPDATE).setActiveUri(str + PATH_ACTIVE).setALinkAttributionUri(str + PATH_ALINK_ATTRIBUTION).setALinkQueryUri(str + PATH_ALINK_QUERY);
        if (strArr == null || strArr.length == 0) {
            builder.setSendUris(new String[]{str + PATH_SEND});
        } else {
            int length = strArr.length + 1;
            String[] strArr2 = new String[length];
            strArr2[0] = str + PATH_SEND;
            for (int i = 1; i < length; i++) {
                strArr2[i] = strArr[i - 1] + PATH_SEND;
            }
            builder.setSendUris(strArr2);
        }
        builder.setSettingUri(str + PATH_CONFIG).setAbUri(str + PATH_AB).setProfileUri(str + PATH_PROFILE).setIDBindUri(str + PATH_ID_BIND);
        return builder.build();
    }

    public static UriConfig createUriConfig(int i) {
        return UriConstants.createUriConfig(i);
    }

    public String getAbUri() {
        return this.f;
    }

    public String getActiveUri() {
        return this.c;
    }

    public String getAlinkAttributionUri() {
        return this.j;
    }

    public String getAlinkQueryUri() {
        return this.i;
    }

    public String getBusinessUri() {
        return this.h;
    }

    public String getIDBindUri() {
        return this.k;
    }

    public String getProfileUri() {
        return this.g;
    }

    public String getRegisterUri() {
        return this.f182a;
    }

    public String getReportOaidUri() {
        return this.b;
    }

    public String[] getSendUris() {
        return this.d;
    }

    public String getSettingUri() {
        return this.e;
    }

    public void setALinkAttributionUri(String str) {
        this.j = str;
    }

    public void setALinkQueryUri(String str) {
        this.i = str;
    }

    public void setAbUri(String str) {
        this.f = str;
    }

    public void setActiveUri(String str) {
        this.c = str;
    }

    public void setBusinessUri(String str) {
        this.h = str;
    }

    public void setProfileUri(String str) {
        this.g = str;
    }

    public void setRegisterUri(String str) {
        this.f182a = str;
    }

    public void setReportOaidUri(String str) {
        this.b = str;
    }

    public void setSendUris(String[] strArr) {
        this.d = strArr;
    }

    public void setSettingUri(String str) {
        this.e = str;
    }
}
