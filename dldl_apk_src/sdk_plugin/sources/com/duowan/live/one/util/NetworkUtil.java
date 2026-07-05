package com.duowan.live.one.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.mtp.utils.Utils;
import com.sqwan.bugless.core.Constant;
import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkUtil {
    private static final int DEFAULT_PROXY_PORT = 80;
    private static final int MAX_PORT = 65535;
    private static final int MIN_PORT = 0;
    public static final String NET_TYPE_2G = "2G";
    public static final String NET_TYPE_3G = "3G";
    public static final String NET_TYPE_4G = "4G";
    public static final String NET_TYPE_NONE = "none";
    public static final String NET_TYPE_UNKNOWN = "unknown";
    public static final String NET_TYPE_WIFI = "wifi";
    private static final String TAG = "NetworkUtil";

    public static String getWifiSSID(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            return (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "" : connectionInfo.getSSID();
        } catch (Throwable th) {
            L.error(TAG, th);
            return "";
        }
    }

    public static boolean isWifiActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static boolean is2GOr3GActive(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static String getNetWorkType(Context context) {
        return !isNetworkAvailable(context) ? "none" : isWifiActive(context) ? "wifi" : getNetWorkSubType(context);
    }

    public static String getNetWorkSubType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "unknown";
        }
        switch (activeNetworkInfo.getSubtype()) {
        }
        return "unknown";
    }

    public static boolean isNetworkStrictlyAvailable(Context context) {
        String string;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            return true;
        }
        if (activeNetworkInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network type = ");
            sb.append(activeNetworkInfo.getType());
            sb.append(", ");
            sb.append(activeNetworkInfo.isAvailable() ? "available" : "inavailable");
            sb.append(", ");
            sb.append(activeNetworkInfo.isConnected() ? "" : "not");
            sb.append(" connected");
            string = sb.toString();
        } else {
            string = "no active network";
        }
        L.info(Constant.DEV_NETWORK, string);
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnected() || (activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting());
    }

    public static void openNetworkConfig(Context context) {
        Intent intent;
        if (Build.VERSION.SDK_INT > 10) {
            intent = new Intent("android.settings.WIRELESS_SETTINGS");
        } else {
            intent = new Intent();
            intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setAction("android.intent.action.MAIN");
        }
        try {
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static InetSocketAddress getTunnelProxy(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.WRITE_APN_SETTINGS") == -1) {
            return null;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("proxy"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("port"));
            L.info("getTunnelProxy", Utils.getOperator(context) + ", proxy = " + string + ", port = " + string2);
            if (string != null && string.length() > 0) {
                cursorQuery.close();
                int i = 80;
                try {
                    int i2 = Integer.parseInt(string2);
                    if (i2 >= 0 && i2 <= 65535) {
                        i = i2;
                    }
                } catch (Exception e) {
                    L.info("getTunnelProxy", "port is invalid, e = " + e);
                }
                try {
                    return new InetSocketAddress(string, i);
                } catch (Exception e2) {
                    L.info("getTunnelProxy", "create address failed, e = " + e2);
                    return null;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }

    public static String getIp(Context context) {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) ArkValue.gContext.getSystemService("wifi");
        return (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "0.0.0.0" : intToIp(connectionInfo.getIpAddress());
    }

    public static String intToIp(int i) {
        return (i & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 8) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 16) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 24) & 255);
    }

    public static int ping() {
        try {
            int iWaitFor = Runtime.getRuntime().exec("ping -c 3 www.baidu.com").waitFor();
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(iWaitFor == 0);
            objArr[1] = Integer.valueOf(iWaitFor);
            L.info(TAG, "connet is %s,ping Process:%d", objArr);
            return iWaitFor;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean pingIp(String str) throws Throwable {
        BufferedReader bufferedReader;
        InputStream inputStream;
        BufferedReader bufferedReader2;
        try {
            try {
                try {
                    Process processExec = Runtime.getRuntime().exec("ping -c 1 -w 1 " + str);
                    inputStream = processExec.getInputStream();
                    try {
                        bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
                        try {
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line);
                            }
                            L.info(TAG, "ping result content : " + sb.toString());
                            if (processExec.waitFor() == 0) {
                                L.info(TAG, "ping result = successful~");
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e) {
                                    L.error(TAG, (Throwable) e);
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e2) {
                                        L.error(TAG, (Throwable) e2);
                                    }
                                }
                                return true;
                            }
                            L.info(TAG, "ping result = failed~ cannot reach the IP address");
                            try {
                                bufferedReader2.close();
                            } catch (IOException e3) {
                                L.error(TAG, (Throwable) e3);
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e4) {
                                    L.error(TAG, (Throwable) e4);
                                }
                            }
                            return false;
                        } catch (IOException e5) {
                            e = e5;
                            L.error(TAG, (Throwable) e);
                            L.info(TAG, "ping result = " + ((String) null));
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e6) {
                                    L.error(TAG, (Throwable) e6);
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return false;
                        } catch (InterruptedException e7) {
                            e = e7;
                            L.error(TAG, (Throwable) e);
                            L.info(TAG, "ping result = " + ((String) null));
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e8) {
                                    L.error(TAG, (Throwable) e8);
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return false;
                        }
                    } catch (IOException e9) {
                        e = e9;
                        bufferedReader2 = null;
                    } catch (InterruptedException e10) {
                        e = e10;
                        bufferedReader2 = null;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = null;
                        L.info(TAG, "ping result = " + ((String) null));
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e11) {
                                L.error(TAG, (Throwable) e11);
                            }
                        }
                        if (inputStream == null) {
                            throw th;
                        }
                        try {
                            inputStream.close();
                            throw th;
                        } catch (IOException e12) {
                            L.error(TAG, (Throwable) e12);
                            throw th;
                        }
                    }
                } catch (IOException e13) {
                    L.error(TAG, (Throwable) e13);
                    return false;
                }
            } catch (IOException e14) {
                e = e14;
                inputStream = null;
                bufferedReader2 = null;
            } catch (InterruptedException e15) {
                e = e15;
                inputStream = null;
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static boolean pingSocket(String str, int i) throws Throwable {
        Socket socket;
        boolean zIsConnected = false;
        Socket socket2 = null;
        try {
            try {
                try {
                    L.info(TAG, "pingSocket %s,port %d", str, Integer.valueOf(i));
                    socket = new Socket();
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            socket.connect(new InetSocketAddress(str, i), 5000);
            zIsConnected = socket.isConnected();
            socket.close();
        } catch (Exception e3) {
            e = e3;
            socket2 = socket;
            L.error("pingSocket", (Throwable) e);
            if (socket2 != null) {
                socket2.close();
            }
            return zIsConnected;
        } catch (Throwable th2) {
            th = th2;
            socket2 = socket;
            if (socket2 != null) {
                try {
                    socket2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return zIsConnected;
    }
}
