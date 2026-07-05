package com.tencent.mars.stn;

import com.tencent.mars.Mars;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class StnLogic {
    public static final int CONNECTED = 4;
    public static final int CONNECTTING = 3;
    public static final int DNSMAKESOCKETPREPARED = -10606;
    public static int ECHECK_NEVER = 0;
    public static int ECHECK_NEXT = 0;
    public static int ECHECK_NOW = 0;
    public static final int FIRSTPKGTIMEOUT = -500;
    public static final int GATEWAY_FAILED = 1;
    public static final int HTTPPARSESTATUSLINE = -10195;
    public static final int HTTPSPLITHTTPHEADANDBODY = -10194;
    public static final int INVALID_TASK_ID = -1;
    public static final int NETMSGXPHANDLEBUFFERERR = -10504;
    public static final int NETWORK_UNAVAILABLE = 0;
    public static final int NETWORK_UNKNOWN = -1;
    public static final int PKGPKGTIMEOUT = -501;
    public static final int READWRITETIMEOUT = -502;
    public static int RESP_FAIL_HANDLE_DEFAULT = 0;
    public static int RESP_FAIL_HANDLE_NORMAL = 0;
    public static int RESP_FAIL_HANDLE_SESSION_TIMEOUT = 0;
    public static int RESP_FAIL_HANDLE_TASK_END = 0;
    public static final int SERVER_DOWN = 5;
    public static final int SERVER_FAILED = 2;
    public static final int SOCKETMAKESOCKETPREPARED = -10087;
    public static final int SOCKETNETWORKCHANGE = -10086;
    public static final int SOCKETREADONCE = -10089;
    public static final int SOCKETRECVERR = -10091;
    public static final int SOCKETSENDERR = -10092;
    public static final int SOCKETSHUTDOWN = -10090;
    public static final int SOCKETWRITENWITHNONBLOCK = -10088;
    public static final String TAG = "mars.StnLogic";
    public static final int TASKTIMEOUT = -503;
    public static int TASK_END_SUCCESS = 0;
    private static ICallBack callBack = null;
    public static final int ectDial = 2;
    public static final int ectDns = 3;
    public static final int ectEnDecode = 7;
    public static final int ectFalse = 1;
    public static final int ectHttp = 5;
    public static final int ectLocal = 9;
    public static final int ectNetMsgXP = 6;
    public static final int ectOK = 0;
    public static final int ectServer = 8;
    public static final int ectSocket = 4;

    public interface ICallBack {
        int buf2Resp(int i, Object obj, byte[] bArr, int[] iArr, int i2);

        int getLongLinkIdentifyCheckBuffer(ByteArrayOutputStream byteArrayOutputStream, ByteArrayOutputStream byteArrayOutputStream2, int[] iArr);

        boolean isLogoned();

        boolean makesureAuthed();

        void onLinkConnectError(String str, int i, long j, int i2, int i3, String str2, int i4);

        boolean onLongLinkIdentifyResp(byte[] bArr, byte[] bArr2);

        String[] onNewDns(String str);

        void onPush(String str, int i, String str2, int i2, byte[] bArr);

        int onTaskEnd(int i, Object obj, int i2, int i3);

        void reportConnectInfo(String str, int i, int i2);

        void reportQuicStatus(int i);

        void reportTaskProfile(String str);

        boolean req2Buf(int i, Object obj, ByteArrayOutputStream byteArrayOutputStream, int[] iArr, int i2);

        void requestDoSync();

        String[] requestNetCheckShortLinkHosts();

        void trafficData(int i, int i2);
    }

    public static native void clearTask();

    public static native void createLonglink(LongLinkConfig longLinkConfig);

    public static native void destroyLonglink(String str);

    private static native ArrayList<String> getLoadLibraries();

    public static native int getLocalIPStack();

    public static native boolean hasTask(int i);

    public static native void keepSignalling();

    public static native boolean longlinkIsConnected(String str);

    public static native void makesureLongLinkConnected();

    public static native void makesureLonglinkConnected(String str);

    public static native void redoTask();

    public static native void reset();

    public static native void setAutoConnectInterval(long j);

    public static native void setBackupIPs(String str, String[] strArr);

    public static native void setClientVersion(int i);

    public static native void setDebugIP(String str, String str2);

    public static native void setEnableIdleBreak(boolean z);

    public static native void setEnableKeepAlive(boolean z);

    public static native void setEnableMultiConn(boolean z);

    public static native void setEnableNewTls(boolean z);

    public static native void setEnableShortMonitor(boolean z);

    public static native void setEncryptKey(String str);

    public static native void setHuyaAppSrc(String str);

    public static native void setHuyaDeviceid(String str);

    public static native void setHuyaExperiment(String str);

    public static native void setHuyaGuid(String str);

    public static native void setHuyaMid(String str);

    public static native void setHuyaUA(String str);

    public static native void setHuyaUid(String str);

    public static native void setIPSortStrategy(int i);

    public static native void setIpSortType(int i);

    public static native void setKeepAliveMaxCount(int i);

    public static native void setLonglinkSvrAddr(String str, int[] iArr, String str2);

    public static native void setQuicEnable(boolean z);

    public static native void setQuicLogEnable(boolean z);

    public static native void setQuicPushEnable(boolean z);

    public static native void setQuiclinkSvrAddr(String str, int[] iArr, String str2);

    public static native void setRefineParaEnable(boolean z);

    public static native void setShortlinkSvrAddr(int i, String str);

    public static native void setSignallingStrategy(long j, long j2);

    public static native void startTask(Task task);

    public static native void stopSignalling();

    public static native void stopTask(int i);

    static {
        Mars.loadDefaultMarsLibrary();
        ECHECK_NOW = 0;
        ECHECK_NEXT = 1;
        ECHECK_NEVER = 2;
        RESP_FAIL_HANDLE_NORMAL = 0;
        RESP_FAIL_HANDLE_DEFAULT = -1;
        RESP_FAIL_HANDLE_SESSION_TIMEOUT = -13;
        RESP_FAIL_HANDLE_TASK_END = -14;
        TASK_END_SUCCESS = 0;
        callBack = null;
    }

    public static class Task {
        public static final int EBoth = 3;
        public static final int EBothWithoutQuic = 6;
        public static final int ECustomWs = 7;
        public static final int EFAST = 1;
        public static final int ELong = 2;
        public static final int ENORMAL = 0;
        public static final int EPushChannel = 5;
        public static final int EQuic = 4;
        public static final int EShort = 1;
        public static final int ETASK_PRIORITY_0 = 0;
        public static final int ETASK_PRIORITY_1 = 1;
        public static final int ETASK_PRIORITY_2 = 2;
        public static final int ETASK_PRIORITY_3 = 3;
        public static final int ETASK_PRIORITY_4 = 4;
        public static final int ETASK_PRIORITY_5 = 5;
        public static final int ETASK_PRIORITY_HIGHEST = 0;
        public static final int ETASK_PRIORITY_LOWEST = 5;
        public static final int ETASK_PRIORITY_NORMAL = 3;
        private static AtomicInteger ai = new AtomicInteger(0);
        public String cgi;
        public String channelName;
        public int channelSelect;
        public int channelStrategy;
        public int cmdID;
        public boolean encrypt;
        public boolean limitFlow;
        public boolean limitFrequency;
        public boolean needAuthed;
        public boolean networkStatusSensitive;
        public int priority;
        public String reportArg;
        public int retryCount;
        public boolean sendOnly;
        public int serverProcessCost;
        public ArrayList<String> shortLinkHostList;
        public int taskID;
        public int totalTimeout;
        public String traceId;
        public Object userContext;

        public Task() {
            this.retryCount = -1;
            this.taskID = ai.incrementAndGet();
        }

        public Task(int i, int i2, String str, String str2, ArrayList<String> arrayList) {
            this.retryCount = -1;
            this.taskID = ai.incrementAndGet();
            this.channelSelect = i;
            this.cmdID = i2;
            this.cgi = str;
            this.traceId = str2;
            this.shortLinkHostList = arrayList;
            this.sendOnly = false;
            this.needAuthed = true;
            this.limitFlow = true;
            this.limitFrequency = true;
            this.encrypt = false;
            this.channelStrategy = 0;
            this.networkStatusSensitive = false;
            this.priority = 3;
            this.retryCount = -1;
            this.serverProcessCost = 0;
            this.totalTimeout = 0;
            this.userContext = null;
        }
    }

    public static void setCallBack(ICallBack iCallBack) {
        callBack = iCallBack;
    }

    public static void setLonglinkSvrAddrWithTryCatch(String str, int[] iArr, String str2) {
        try {
            setLonglinkSvrAddr(str, iArr, str2);
        } catch (UnsatisfiedLinkError unused) {
            setLonglinkSvrAddr(str, iArr, str2);
        }
    }

    public static void setLonglinkSvrAddr(String str, int[] iArr) {
        setLonglinkSvrAddrWithTryCatch(str, iArr, null);
    }

    public static void setShortlinkSvrAddrWithTryCatch(int i, String str) {
        try {
            setShortlinkSvrAddr(i, str);
        } catch (UnsatisfiedLinkError unused) {
            setShortlinkSvrAddr(i, str);
        }
    }

    public static void setShortlinkSvrAddr(int i) {
        setShortlinkSvrAddrWithTryCatch(i, null);
    }

    public static void startTaskWirhTryCatch(Task task) {
        try {
            startTask(task);
        } catch (UnsatisfiedLinkError unused) {
            startTask(task);
        }
    }

    public static void makesureLongLinkConnectedWithTryCatch() {
        try {
            makesureLongLinkConnected();
        } catch (UnsatisfiedLinkError unused) {
            makesureLongLinkConnected();
        }
    }

    private static boolean makesureAuthed() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return false;
            }
            return callBack.makesureAuthed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static class LongLinkConfig {
        public static final String DEFAULT_LONGLINK_GROUP = "default-group";
        public static final String DEFAULT_LONGLINK_NAME = "default-longlink";
        public static final String DEFAULT_PUSH_LONGLINK_NAME = "push-longlink";
        public String group;
        public String host;
        public boolean isConnectImmediately;
        public boolean isMain;
        public String name;

        public LongLinkConfig(String str, String str2, String str3, boolean z, boolean z2) {
            this.name = str;
            this.group = str2;
            this.host = str3;
            this.isMain = z;
            this.isConnectImmediately = z2;
        }
    }

    private static String[] onNewDns(String str) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return null;
            }
            return callBack.onNewDns(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void onPush(int i, int i2, int i3, byte[] bArr) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.onPush("", i2, "", i3, bArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean req2Buf(int i, Object obj, ByteArrayOutputStream byteArrayOutputStream, int[] iArr, int i2) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return false;
            }
            return callBack.req2Buf(i, obj, byteArrayOutputStream, iArr, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int buf2Resp(int i, Object obj, byte[] bArr, int[] iArr, int i2) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return RESP_FAIL_HANDLE_TASK_END;
            }
            return callBack.buf2Resp(i, obj, bArr, iArr, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return RESP_FAIL_HANDLE_TASK_END;
        }
    }

    private static int onTaskEnd(int i, Object obj, int i2, int i3) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return 0;
            }
            return callBack.onTaskEnd(i, obj, i2, i3);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void trafficData(int i, int i2) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.trafficData(i, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reportConnectStatus(String str, int i, int i2) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.reportConnectInfo(str, i, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reportQuicStatus(int i) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.reportQuicStatus(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getLongLinkIdentifyCheckBuffer(ByteArrayOutputStream byteArrayOutputStream, ByteArrayOutputStream byteArrayOutputStream2, int[] iArr) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return ECHECK_NEVER;
            }
            return callBack.getLongLinkIdentifyCheckBuffer(byteArrayOutputStream, byteArrayOutputStream2, iArr);
        } catch (Exception e) {
            e.printStackTrace();
            return ECHECK_NEVER;
        }
    }

    private static boolean onLongLinkIdentifyResp(byte[] bArr, byte[] bArr2) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return false;
            }
            return callBack.onLongLinkIdentifyResp(bArr, bArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String[] requestNetCheckShortLinkHosts() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return null;
            }
            return callBack.requestNetCheckShortLinkHosts();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void requestDoSync() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.requestDoSync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isLogoned() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return false;
            }
            return callBack.isLogoned();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void reportTaskProfile(String str) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.reportTaskProfile(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void onLinkConnectError(String str, int i, long j, int i2, int i3, String str2, int i4) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.onLinkConnectError(str, i, j, i2, i3, str2, i4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
