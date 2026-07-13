package com.volcengine.androidcloud.common.log;

import java.util.Set;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AcLog {
    private static final AcLogCore CORE = new AcLogCore();

    public interface ILogger {
        void onDebug(String str, String str2);

        void onError(String str, String str2);

        void onError(String str, String str2, Throwable th);

        void onInfo(String str, String str2);

        void onVerbose(String str, String str2);

        void onWarn(String str, String str2);
    }

    public enum Level {
        FREQUENCY("F"),
        VERBOSE("V"),
        DEBUG("D"),
        INFO("I"),
        WARN("W"),
        ERROR("E");

        private final String identify;

        Level(String str) {
            this.identify = str;
        }

        public static Level getLevel(String str) {
            str.hashCode();
            str.hashCode();
            switch (str) {
                case "D":
                    return DEBUG;
                case "E":
                    return ERROR;
                case "F":
                    return FREQUENCY;
                case "I":
                    return INFO;
                case "V":
                    return VERBOSE;
                case "W":
                    return WARN;
                default:
                    throw new IllegalArgumentException("identify is illegal.");
            }
        }

        public String identify() {
            return this.identify;
        }
    }

    public static void d(String str, String str2) {
        CORE.getOutput().d(str, str2);
    }

    public static void e(String str, String str2) {
        CORE.getOutput().e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        CORE.getOutput().e(str, str2, th);
    }

    public static void f(String str, String str2) {
        CORE.getOutput().f(str, str2);
    }

    public static void i(String str, String str2) {
        CORE.getOutput().i(str, str2);
    }

    public static void setDebug(AcLogConfigSource acLogConfigSource, boolean z) {
        CORE.setDebug(acLogConfigSource, z);
    }

    public static void setFormat(boolean z) {
        CORE.setFormat(z);
    }

    public static void setLevel(Level level) {
        CORE.setLevel(level);
    }

    public static void setLogger(AcTargetType acTargetType, ILogger iLogger) {
        CORE.setLogger(acTargetType, iLogger);
    }

    public static void setRange(long j, long j2) {
        CORE.setRange(j, j2);
    }

    public static void setReplace(String str) {
        CORE.setReplace(str);
    }

    public static void setTags(Set<String> set) {
        CORE.setTags(set);
    }

    public static void setTargets(int i) {
        CORE.setTargets(i);
    }

    public static void v(String str, String str2) {
        CORE.getOutput().v(str, str2);
    }

    public static void w(String str, String str2) {
        CORE.getOutput().w(str, str2);
    }
}
