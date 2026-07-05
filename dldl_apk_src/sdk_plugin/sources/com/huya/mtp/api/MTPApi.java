package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MTPApi {
    public static final DebugApi DEBUGGER = new DebugApiDelegate();
    public static final LogApi LOGGER = new LogApiDelegate();
    public static final ContextApi CONTEXT = new ContextApiDelegate();
    public static final MonitorApi MONITOR = new MonitorApiDelegate();
    public static final EnvVarApiDelegate ENVVAR = new EnvVarApiDelegate();
    public static final DidApiDelegate DID = new DidApiDelegate();

    public static void setContextApi(ContextApi contextApi) {
        ((ContextApiDelegate) CONTEXT).setContextApi(contextApi);
    }

    public static void setDebugger(DebugApi debugApi) {
        ((DebugApiDelegate) DEBUGGER).setDebugApi(debugApi);
    }

    public static void setLogger(LogApi logApi) {
        ((LogApiDelegate) LOGGER).setLogApi(logApi);
    }

    public static void setMonitorApi(MonitorApi monitorApi) {
        ((MonitorApiDelegate) MONITOR).setMonitorApi(monitorApi);
    }

    public static void setEnvVarApi(EnvVarApi envVarApi) {
        ENVVAR.setEnvVarApi(envVarApi);
    }

    public static void setDidApi(DidApi didApi) {
        DID.setDidVarApi(didApi);
    }
}
