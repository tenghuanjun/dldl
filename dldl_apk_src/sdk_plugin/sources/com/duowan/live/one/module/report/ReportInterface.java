package com.duowan.live.one.module.report;

import android.app.Activity;
import com.huya.statistics.core.StatisticsContent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ReportInterface {

    public static class ChangeHuyaSessionId {
    }

    public static class StopLive {
    }

    public static class ReportActivity {
        public Activity activity;
        public boolean resume;

        public ReportActivity(Activity activity, boolean z) {
            this.activity = activity;
            this.resume = z;
        }
    }

    public static class ReportEvent {
        public StatisticsContent content;
        public String desc;
        public String id;
        public String label;
        public String prop;

        public ReportEvent(String str) {
            this(str, null);
        }

        public ReportEvent(String str, String str2) {
            this.prop = null;
            this.id = str;
            this.desc = str2;
        }

        public ReportEvent(String str, String str2, String str3) {
            this.prop = null;
            this.id = str;
            this.label = str3;
            this.desc = str2;
        }

        public ReportEvent(String str, String str2, String str3, StatisticsContent statisticsContent) {
            this.prop = null;
            this.id = str;
            this.label = str3;
            this.desc = str2;
            this.content = statisticsContent;
        }

        public ReportEvent(String str, String str2, String str3, String str4, StatisticsContent statisticsContent) {
            this.prop = null;
            this.id = str;
            this.label = str3;
            this.desc = str2;
            this.prop = str4;
            this.content = statisticsContent;
        }
    }

    public static class ReportValue {
        public String id;
        public String label;
        public int value;

        public ReportValue(String str, int i) {
            this(str, null, i);
        }

        public ReportValue(String str, String str2, int i) {
            this.id = str;
            this.label = str2;
            this.value = i;
        }
    }

    public static class ReportError {
        public String id;
        public String label;
        public String message;

        public ReportError(String str, String str2, String str3) {
            this.id = str;
            this.label = str2;
            this.message = str3;
        }
    }

    public static class ReportHuyaEvent {
        public String eDes;
        public String eid;

        public ReportHuyaEvent(String str, String str2) {
            this.eid = str;
            this.eDes = str2;
        }
    }

    public static class StartLive {
        final String mAppVersion;
        final long mChannelId;
        final long mGameId;
        final long mLiveId;

        public StartLive(long j, long j2, long j3, String str) {
            this.mGameId = j;
            this.mLiveId = j2;
            this.mChannelId = j3;
            this.mAppVersion = str;
        }
    }

    public static class LoginSuccess {
        public long uid;

        public LoginSuccess(long j) {
            this.uid = j;
        }
    }

    public static class ReportGuid {
        public String guid;

        public ReportGuid(String str) {
            this.guid = str;
        }
    }
}
