package com.sq.tools.network.httpdns.network;

import android.content.Context;
import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.callback.IDnsRequestListener;
import com.sq.tools.network.httpdns.data.DnsServerData;
import com.sq.tools.network.httpdns.dns.HttpDnsCache;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import com.sq.tools.network.httpdns.network.IGetJsonRequest;
import com.sq.tools.network.httpdns.third.IThirdDns;
import com.sq.tools.network.httpdns.util.ExceptionReporter;
import com.sq.tools.network.httpdns.util.HttpDnsUtil;
import com.sqwan.common.route.FunctionRouter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsNetRequest {
    private static final int MAX_FAIL_COUNT = 3;
    private static final int MAX_RETRY_COUNT = 3;
    private static final int REQUEST_TYPE_DNS_MULTI = 3;
    private static final int REQUEST_TYPE_DNS_SERVER = 1;
    private static final int REQUEST_TYPE_DNS_SINGLE = 2;
    private final IGetJsonRequest mGetJsonRequest;
    private final String mOriginHost;
    private ExceptionReporter mReporter;
    private final IThirdDns mThirdDns;
    private int currentRequestCount = 0;
    private long costTotal = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasRetryChance(int code) {
        return code >= 400;
    }

    static /* synthetic */ long access$114(HttpDnsNetRequest httpDnsNetRequest, long j) {
        long j2 = httpDnsNetRequest.costTotal + j;
        httpDnsNetRequest.costTotal = j2;
        return j2;
    }

    public HttpDnsNetRequest(IGetJsonRequest request, String host, IThirdDns thirdDns) {
        if (request == null) {
            throw new IllegalArgumentException("IGetJsonRequest can not be null");
        }
        if (TextUtils.isEmpty(host)) {
            throw new IllegalArgumentException("Dns server host can not be null");
        }
        this.mGetJsonRequest = request;
        this.mOriginHost = host;
        this.mThirdDns = thirdDns;
    }

    public void requestDnsServer(final Context context, final Map<String, String> requestMap, final IDnsRequestListener dnsRequestListener) {
        String httpDnsServerUrl = HttpDnsUrl.getHttpDnsServerUrl(context);
        if (TextUtils.isEmpty(httpDnsServerUrl)) {
            HttpDnsLog.e("dnsServerUrl must not be null");
            return;
        }
        int i = this.currentRequestCount + 1;
        this.currentRequestCount = i;
        Map<String, String> requestHeaderMap = null;
        if (i < 3) {
            requestHeaderMap = getRequestHeaderMap();
        } else {
            httpDnsServerUrl = httpDnsServerUrl.replaceFirst(HttpDnsUtil.getHost(httpDnsServerUrl), this.mOriginHost);
            ExceptionReporter exceptionReporter = this.mReporter;
            if (exceptionReporter != null) {
                exceptionReporter.report(ExceptionReporter.ExceptionType.USE_ORIGIN_URL_ERROR, httpDnsServerUrl);
            }
        }
        final String str = httpDnsServerUrl;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final Map<String, String> mapConstructServerDnsParam = constructServerDnsParam(requestMap);
        this.mGetJsonRequest.request(str, requestHeaderMap, mapConstructServerDnsParam, SqHttpDns.getInstance().getTimeout(), new IGetJsonRequest.IGetJsonRequestCallback() { // from class: com.sq.tools.network.httpdns.network.HttpDnsNetRequest.1
            @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest.IGetJsonRequestCallback
            public void onSuccess(int code, String content) {
                HttpDnsLog.i("requestDnsServer result: " + content);
                HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = HttpDnsNetRequest.this.createDnsRequestStat(str, (Map<String, String>) mapConstructServerDnsParam);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                HttpDnsNetRequest.access$114(HttpDnsNetRequest.this, jCurrentTimeMillis2);
                httpDnsRequestStatCreateDnsRequestStat.setType(1);
                httpDnsRequestStatCreateDnsRequestStat.setCost(jCurrentTimeMillis2);
                httpDnsRequestStatCreateDnsRequestStat.setCostTotal(HttpDnsNetRequest.this.costTotal);
                httpDnsRequestStatCreateDnsRequestStat.setStatusCode(code);
                httpDnsRequestStatCreateDnsRequestStat.setResponseData(content);
                try {
                    JSONObject jSONObject = new JSONObject(content);
                    if (jSONObject.getInt("code") != 0) {
                        HttpDnsNetRequest.this.recordDnsServerFail(context, dnsRequestListener);
                        if (dnsRequestListener != null) {
                            httpDnsRequestStatCreateDnsRequestStat.setMsg("返回code不为0");
                            dnsRequestListener.onFail(httpDnsRequestStatCreateDnsRequestStat);
                        }
                    } else {
                        if (dnsRequestListener != null) {
                            httpDnsRequestStatCreateDnsRequestStat.setMsg("请求成功");
                            dnsRequestListener.onSuccess(httpDnsRequestStatCreateDnsRequestStat);
                        }
                        DnsServerData dnsServerData = DnsServerData.parse(jSONObject.optString(FunctionRouter.KEY_DATA));
                        DnsServerData httpDnsServerFromCache = HttpDnsCache.getHttpDnsServerFromCache(context);
                        ArrayList<String> serviceIps = dnsServerData.getServiceIps();
                        if (serviceIps == null || serviceIps.isEmpty()) {
                            dnsServerData.setServiceIps(httpDnsServerFromCache.getServiceIps());
                        }
                        ArrayList<String> txServiceIps = dnsServerData.getTxServiceIps();
                        if (txServiceIps == null || txServiceIps.isEmpty()) {
                            dnsServerData.setTxServiceIps(httpDnsServerFromCache.getTxServiceIps());
                        }
                        HttpDnsLog.i("更新并且写入缓存httpDns服务数据：" + dnsServerData.toString());
                        HttpDnsCache.saveHttpDnsServerByData(context, dnsServerData);
                        SqHttpDns.getInstance().refreshDnsSever(dnsServerData);
                        HttpDnsCache.clearHttpDnsServerFailCount(context);
                    }
                } catch (Exception e) {
                    HttpDnsLog.e("dns服务配置解析失败", e);
                    HttpDnsNetRequest.this.recordDnsServerFail(context, dnsRequestListener);
                    if (dnsRequestListener != null) {
                        httpDnsRequestStatCreateDnsRequestStat.setMsg("dns服务配置解析失败 " + e);
                        dnsRequestListener.onInvalid(httpDnsRequestStatCreateDnsRequestStat);
                    }
                }
                if (SqHttpDns.getInstance().isDnsEnable()) {
                    if (HttpDnsNetRequest.this.mThirdDns != null) {
                        HttpDnsNetRequest.this.mThirdDns.init(context);
                    }
                    HttpDnsRequestManager.getInstance().requestDnsMulti(context, SqHttpDns.getInstance().getPreResolveHosts(), dnsRequestListener);
                }
            }

            @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest.IGetJsonRequestCallback
            public void onFailure(int code, String message) {
                HttpDnsLog.e("requestDnsServer onFailure,第 " + HttpDnsNetRequest.this.currentRequestCount + "  次, code=" + code + ", msg=" + message);
                if (dnsRequestListener != null) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    HttpDnsNetRequest.access$114(HttpDnsNetRequest.this, jCurrentTimeMillis2);
                    HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = HttpDnsNetRequest.this.createDnsRequestStat(str, (Map<String, String>) mapConstructServerDnsParam);
                    httpDnsRequestStatCreateDnsRequestStat.setType(1);
                    httpDnsRequestStatCreateDnsRequestStat.setCost(jCurrentTimeMillis2);
                    httpDnsRequestStatCreateDnsRequestStat.setCostTotal(HttpDnsNetRequest.this.costTotal);
                    httpDnsRequestStatCreateDnsRequestStat.setStatusCode(code);
                    httpDnsRequestStatCreateDnsRequestStat.setMsg(message);
                    httpDnsRequestStatCreateDnsRequestStat.setResponseData("");
                    dnsRequestListener.onFail(httpDnsRequestStatCreateDnsRequestStat);
                }
                if (HttpDnsNetRequest.this.hasRetryChance(code)) {
                    if (HttpDnsNetRequest.this.currentRequestCount >= 3) {
                        HttpDnsNetRequest.this.recordDnsServerFail(context, dnsRequestListener);
                        HttpDnsLog.w("失败码为" + code + "不再重试");
                        if (SqHttpDns.getInstance().isDnsEnable()) {
                            if (HttpDnsNetRequest.this.mThirdDns != null) {
                                HttpDnsNetRequest.this.mThirdDns.init(context);
                            }
                            HttpDnsRequestManager.getInstance().requestDnsMulti(context, SqHttpDns.getInstance().getPreResolveHosts(), dnsRequestListener);
                            return;
                        }
                        return;
                    }
                    HttpDnsLog.i("失败码为" + code + ", 重试");
                    HttpDnsNetRequest.this.requestDnsServer(context, requestMap, dnsRequestListener);
                    return;
                }
                HttpDnsNetRequest.this.recordDnsServerFail(context, dnsRequestListener);
                if (SqHttpDns.getInstance().isDnsEnable()) {
                    if (HttpDnsNetRequest.this.mThirdDns != null) {
                        HttpDnsNetRequest.this.mThirdDns.init(context);
                    }
                    HttpDnsRequestManager.getInstance().requestDnsMulti(context, SqHttpDns.getInstance().getPreResolveHosts(), dnsRequestListener);
                }
            }
        });
        if (dnsRequestListener != null) {
            HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = createDnsRequestStat(str, mapConstructServerDnsParam);
            httpDnsRequestStatCreateDnsRequestStat.setType(1);
            dnsRequestListener.onDnsRequest(httpDnsRequestStatCreateDnsRequestStat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordDnsServerFail(Context context, IDnsRequestListener dnsRequestListener) {
        int httpDnsServerFailCount = HttpDnsCache.getHttpDnsServerFailCount(context);
        if (httpDnsServerFailCount < 3) {
            HttpDnsCache.accumulateHttpDnsServerFailCount(context);
            if (httpDnsServerFailCount != 2 || dnsRequestListener == null) {
                return;
            }
            dnsRequestListener.onForceClose();
        }
    }

    public void requestDnsSingle(final Context context, final String host, final IDnsRequestListener dnsRequestListener) {
        if (!SqHttpDns.getInstance().isDnsEnable()) {
            HttpDnsLog.e("HttpDnsRequestManager requestDnsSingle fail, dns is not enable");
            return;
        }
        this.currentRequestCount++;
        HttpDnsLog.d("HttpDnsRequestManager requestDnsSingle " + host + "  第 " + this.currentRequestCount + " 次");
        String singleDnsUrl = HttpDnsUrl.getSingleDnsUrl(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(host);
        Map<String, String> requestHeaderMap = null;
        if (this.currentRequestCount < 3) {
            requestHeaderMap = getRequestHeaderMap();
        } else {
            singleDnsUrl = singleDnsUrl.replaceFirst(HttpDnsUtil.getHost(singleDnsUrl), this.mOriginHost);
            ExceptionReporter exceptionReporter = this.mReporter;
            if (exceptionReporter != null) {
                exceptionReporter.report(ExceptionReporter.ExceptionType.USE_ORIGIN_URL_ERROR, singleDnsUrl);
            }
        }
        final String str = singleDnsUrl;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final Map<String, String> mapConstructHostDnsParam = constructHostDnsParam(arrayList);
        this.mGetJsonRequest.request(str, requestHeaderMap, mapConstructHostDnsParam, SqHttpDns.getInstance().getTimeout(), new IGetJsonRequest.IGetJsonRequestCallback() { // from class: com.sq.tools.network.httpdns.network.HttpDnsNetRequest.2
            @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest.IGetJsonRequestCallback
            public void onSuccess(int code, String content) {
                HttpDnsLog.i("获取" + host + "的dns成功, " + content);
                HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = HttpDnsNetRequest.this.createDnsRequestStat(str, (Map<String, String>) mapConstructHostDnsParam);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                HttpDnsNetRequest.access$114(HttpDnsNetRequest.this, jCurrentTimeMillis2);
                httpDnsRequestStatCreateDnsRequestStat.setType(2);
                httpDnsRequestStatCreateDnsRequestStat.setCost(jCurrentTimeMillis2);
                httpDnsRequestStatCreateDnsRequestStat.setCostTotal(HttpDnsNetRequest.this.costTotal);
                httpDnsRequestStatCreateDnsRequestStat.setStatusCode(code);
                httpDnsRequestStatCreateDnsRequestStat.setResponseData(content);
                httpDnsRequestStatCreateDnsRequestStat.setExtra(host);
                try {
                    JSONObject jSONObject = new JSONObject(content);
                    if (jSONObject.getInt("code") == 0) {
                        String strOptString = jSONObject.optString(FunctionRouter.KEY_DATA);
                        if (HttpDnsNetRequest.this.checkIpv4Valid(strOptString)) {
                            if (dnsRequestListener != null) {
                                httpDnsRequestStatCreateDnsRequestStat.setMsg("请求成功");
                                dnsRequestListener.onSuccess(httpDnsRequestStatCreateDnsRequestStat);
                            }
                            HttpDnsCache.saveHttpDnsDataByHost(context, host, strOptString);
                            return;
                        }
                        HttpDnsLog.e(host + " checkIpv4Valid fail");
                        if (dnsRequestListener != null) {
                            httpDnsRequestStatCreateDnsRequestStat.setMsg("单域名返会的dns ip不合法");
                            dnsRequestListener.onInvalid(httpDnsRequestStatCreateDnsRequestStat);
                        }
                        HttpDnsNetRequest.this.requestThirdDns(context, host, dnsRequestListener);
                        if (dnsRequestListener != null) {
                            dnsRequestListener.onRecordThirdDns(host, str, -1, "ip校验不通过 " + content);
                            return;
                        }
                        return;
                    }
                    if (dnsRequestListener != null) {
                        httpDnsRequestStatCreateDnsRequestStat.setMsg("返回code不为0");
                        dnsRequestListener.onFail(httpDnsRequestStatCreateDnsRequestStat);
                    }
                    HttpDnsNetRequest.this.requestThirdDns(context, host, dnsRequestListener);
                    if (dnsRequestListener != null) {
                        dnsRequestListener.onRecordThirdDns(host, str, -1, "返回码不为0 " + content);
                    }
                } catch (Exception e) {
                    HttpDnsLog.e("单域名解析失败", e);
                    if (dnsRequestListener != null) {
                        httpDnsRequestStatCreateDnsRequestStat.setMsg("单域名解析失败 " + e);
                        dnsRequestListener.onInvalid(httpDnsRequestStatCreateDnsRequestStat);
                    }
                    HttpDnsNetRequest.this.requestThirdDns(context, host, dnsRequestListener);
                    IDnsRequestListener iDnsRequestListener = dnsRequestListener;
                    if (iDnsRequestListener != null) {
                        iDnsRequestListener.onRecordThirdDns(host, str, -1, "json校验不通过 " + content + " error:" + e.toString());
                    }
                }
            }

            @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest.IGetJsonRequestCallback
            public void onFailure(int code, String message) {
                HttpDnsLog.e("获取" + host + "的dns失败, 第 " + HttpDnsNetRequest.this.currentRequestCount + "  次, code=" + code + ", msg=" + message);
                if (dnsRequestListener != null) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    HttpDnsNetRequest.access$114(HttpDnsNetRequest.this, jCurrentTimeMillis2);
                    HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = HttpDnsNetRequest.this.createDnsRequestStat(str, (Map<String, String>) mapConstructHostDnsParam);
                    httpDnsRequestStatCreateDnsRequestStat.setType(2);
                    httpDnsRequestStatCreateDnsRequestStat.setCost(jCurrentTimeMillis2);
                    httpDnsRequestStatCreateDnsRequestStat.setCostTotal(HttpDnsNetRequest.this.costTotal);
                    httpDnsRequestStatCreateDnsRequestStat.setStatusCode(code);
                    httpDnsRequestStatCreateDnsRequestStat.setMsg(message);
                    httpDnsRequestStatCreateDnsRequestStat.setResponseData("");
                    httpDnsRequestStatCreateDnsRequestStat.setExtra(host);
                    dnsRequestListener.onFail(httpDnsRequestStatCreateDnsRequestStat);
                }
                if (HttpDnsNetRequest.this.hasRetryChance(code)) {
                    if (HttpDnsNetRequest.this.currentRequestCount < 3) {
                        HttpDnsLog.i("获取" + host + "的dns失败, code=" + code + ", 重试");
                        HttpDnsNetRequest.this.requestDnsSingle(context, host, dnsRequestListener);
                        return;
                    }
                    HttpDnsNetRequest.this.requestThirdDns(context, host, dnsRequestListener);
                    IDnsRequestListener iDnsRequestListener = dnsRequestListener;
                    if (iDnsRequestListener != null) {
                        iDnsRequestListener.onRecordThirdDns(host, str, code, message);
                    }
                    HttpDnsLog.w("获取" + host + "的dns失败, code=" + code + ", 不再重试");
                    return;
                }
                HttpDnsNetRequest.this.requestThirdDns(context, host, dnsRequestListener);
                IDnsRequestListener iDnsRequestListener2 = dnsRequestListener;
                if (iDnsRequestListener2 != null) {
                    iDnsRequestListener2.onRecordThirdDns(host, str, code, message);
                }
            }
        });
        if (dnsRequestListener != null) {
            HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = createDnsRequestStat(str, mapConstructHostDnsParam);
            httpDnsRequestStatCreateDnsRequestStat.setType(2);
            httpDnsRequestStatCreateDnsRequestStat.setExtra(host);
            dnsRequestListener.onDnsRequest(httpDnsRequestStatCreateDnsRequestStat);
        }
    }

    public void requestDnsMulti(final Context context, final ArrayList<String> hosts, final IDnsRequestListener dnsRequestListener) {
        if (!SqHttpDns.getInstance().isDnsEnable()) {
            HttpDnsLog.e("HttpDnsRequestManager requestDnsMulti fail, dns is not enable");
            return;
        }
        if (hosts == null || hosts.isEmpty()) {
            HttpDnsLog.e("HttpDnsRequestManager requestDnsMulti hosts is empty");
            return;
        }
        this.currentRequestCount++;
        HttpDnsLog.d("HttpDnsRequestManager requestDnsMulti start 第 " + this.currentRequestCount + " 次");
        String multiDnsUrl = HttpDnsUrl.getMultiDnsUrl(context);
        Map<String, String> requestHeaderMap = null;
        if (this.currentRequestCount < 3) {
            requestHeaderMap = getRequestHeaderMap();
        } else {
            multiDnsUrl = multiDnsUrl.replaceFirst(HttpDnsUtil.getHost(multiDnsUrl), this.mOriginHost);
            ExceptionReporter exceptionReporter = this.mReporter;
            if (exceptionReporter != null) {
                exceptionReporter.report(ExceptionReporter.ExceptionType.USE_ORIGIN_URL_ERROR, multiDnsUrl);
            }
        }
        final String str = multiDnsUrl;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final Map<String, String> mapConstructHostDnsParam = constructHostDnsParam(hosts);
        this.mGetJsonRequest.request(str, requestHeaderMap, mapConstructHostDnsParam, SqHttpDns.getInstance().getTimeout(), new IGetJsonRequest.IGetJsonRequestCallback() { // from class: com.sq.tools.network.httpdns.network.HttpDnsNetRequest.3
            @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest.IGetJsonRequestCallback
            public void onSuccess(int code, String content) {
                HttpDnsLog.i("获取多域名dns成功" + content);
                HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = HttpDnsNetRequest.this.createDnsRequestStat(str, (Map<String, String>) mapConstructHostDnsParam);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                HttpDnsNetRequest.access$114(HttpDnsNetRequest.this, jCurrentTimeMillis2);
                httpDnsRequestStatCreateDnsRequestStat.setType(3);
                httpDnsRequestStatCreateDnsRequestStat.setCost(jCurrentTimeMillis2);
                httpDnsRequestStatCreateDnsRequestStat.setCostTotal(HttpDnsNetRequest.this.costTotal);
                httpDnsRequestStatCreateDnsRequestStat.setStatusCode(code);
                httpDnsRequestStatCreateDnsRequestStat.setResponseData(content);
                try {
                    JSONObject jSONObject = new JSONObject(content);
                    if (jSONObject.getInt("code") == 0) {
                        if (dnsRequestListener != null) {
                            httpDnsRequestStatCreateDnsRequestStat.setMsg("请求成功");
                            dnsRequestListener.onSuccess(httpDnsRequestStatCreateDnsRequestStat);
                        }
                        HttpDnsNetRequest.this.parseMultiDns(context, jSONObject.optString(FunctionRouter.KEY_DATA), str, dnsRequestListener, httpDnsRequestStatCreateDnsRequestStat);
                        return;
                    }
                    if (dnsRequestListener != null) {
                        httpDnsRequestStatCreateDnsRequestStat.setMsg("返回code不为0");
                        dnsRequestListener.onFail(httpDnsRequestStatCreateDnsRequestStat);
                    }
                } catch (Exception e) {
                    HttpDnsLog.e("多域名dns解析失败", e);
                    if (dnsRequestListener != null) {
                        httpDnsRequestStatCreateDnsRequestStat.setMsg("多域名解析失败 " + e);
                        dnsRequestListener.onInvalid(httpDnsRequestStatCreateDnsRequestStat);
                    }
                }
            }

            @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest.IGetJsonRequestCallback
            public void onFailure(int code, String message) {
                HttpDnsLog.e("获取多域名dns失败, 第 " + HttpDnsNetRequest.this.currentRequestCount + "  次, code=" + code + ", msg=" + message);
                if (dnsRequestListener != null) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    HttpDnsNetRequest.access$114(HttpDnsNetRequest.this, jCurrentTimeMillis2);
                    HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = HttpDnsNetRequest.this.createDnsRequestStat(str, (Map<String, String>) mapConstructHostDnsParam);
                    httpDnsRequestStatCreateDnsRequestStat.setType(3);
                    httpDnsRequestStatCreateDnsRequestStat.setCost(jCurrentTimeMillis2);
                    httpDnsRequestStatCreateDnsRequestStat.setCostTotal(HttpDnsNetRequest.this.costTotal);
                    httpDnsRequestStatCreateDnsRequestStat.setStatusCode(code);
                    httpDnsRequestStatCreateDnsRequestStat.setMsg(message);
                    httpDnsRequestStatCreateDnsRequestStat.setResponseData("");
                    dnsRequestListener.onFail(httpDnsRequestStatCreateDnsRequestStat);
                }
                if (HttpDnsNetRequest.this.hasRetryChance(code)) {
                    if (HttpDnsNetRequest.this.currentRequestCount < 3) {
                        HttpDnsLog.i("获取多域名dns失败code=" + code + ", 重试");
                        HttpDnsNetRequest.this.requestDnsMulti(context, hosts, dnsRequestListener);
                        return;
                    }
                    HttpDnsLog.w("获取多域名dns失败code=" + code + ", 不再重试");
                }
            }
        });
        if (dnsRequestListener != null) {
            HttpDnsRequestStat httpDnsRequestStatCreateDnsRequestStat = createDnsRequestStat(str, mapConstructHostDnsParam);
            httpDnsRequestStatCreateDnsRequestStat.setType(3);
            dnsRequestListener.onDnsRequest(httpDnsRequestStatCreateDnsRequestStat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseMultiDns(Context context, String multiDnsJsonStr, String finalDnsUrl, IDnsRequestListener dnsRequestListener, HttpDnsRequestStat dnsRequestStat) throws JSONException {
        JSONObject jSONObject = new JSONObject(multiDnsJsonStr);
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (checkIpv4Valid(jSONObject.optString(next))) {
                HttpDnsCache.saveHttpDnsDataByHost(context, next, jSONObject.optString(next));
            } else {
                HttpDnsLog.e(next + " checkIpv4Valid fail ,多域名解析失败");
                if (dnsRequestListener != null) {
                    dnsRequestStat.setMsg("多域名返会的dns ip不合法");
                    dnsRequestStat.setExtra(next);
                    dnsRequestListener.onInvalid(dnsRequestStat);
                }
            }
        }
    }

    private Map<String, String> getRequestHeaderMap() {
        HashMap map = new HashMap();
        map.put("Host", this.mOriginHost);
        return map;
    }

    private Map<String, String> constructServerDnsParam(Map<String, String> requestMap) {
        if (requestMap == null) {
            return null;
        }
        HashMap map = new HashMap(requestMap);
        String str = (String) map.get("dev");
        map.put("appid", SqHttpDns.getInstance().getHttpDnsConfig().appId);
        String strRandomData = HttpDnsUtil.randomData(5);
        map.put("r", strRandomData);
        map.put("s", HttpDnsUtil.Md5(str + "-" + SqHttpDns.getInstance().getHttpDnsConfig().appKey + "-" + strRandomData));
        return map;
    }

    private Map<String, String> constructHostDnsParam(List<String> hosts) {
        String string;
        if (hosts == null || hosts.size() <= 0) {
            string = "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = hosts.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            string = sb.toString();
        }
        HashMap map = new HashMap();
        map.put("d", string);
        if (SqHttpDns.getInstance().isGlobalIpV6Enable()) {
            map.put("type", "ADDRS");
        } else {
            map.put("type", "A");
        }
        map.put("appid", SqHttpDns.getInstance().getHttpDnsConfig().appId);
        String strRandomData = HttpDnsUtil.randomData(5);
        map.put("r", strRandomData);
        map.put("s", HttpDnsUtil.Md5(string + "-" + SqHttpDns.getInstance().getHttpDnsConfig().appKey + "-" + strRandomData));
        return map;
    }

    public void requestThirdDns(final Context context, String host, final IDnsRequestListener dnsRequestListener) {
        if (this.mThirdDns == null) {
            return;
        }
        HttpDnsLog.d("requestThirdDns " + host);
        ExceptionReporter exceptionReporter = this.mReporter;
        if (exceptionReporter != null) {
            exceptionReporter.report(ExceptionReporter.ExceptionType.FALL_TO_TX_ERROR, host);
        }
        this.mThirdDns.getIp(host, new IThirdDns.IThirdDnsCallback() { // from class: com.sq.tools.network.httpdns.network.-$$Lambda$HttpDnsNetRequest$EMPIFuTvrYk9J1qLjsZa4hQRqxk
            @Override // com.sq.tools.network.httpdns.third.IThirdDns.IThirdDnsCallback
            public final void onDns(String str, String[] strArr, long j) {
                this.f$0.lambda$requestThirdDns$0$HttpDnsNetRequest(dnsRequestListener, context, str, strArr, j);
            }
        });
    }

    public /* synthetic */ void lambda$requestThirdDns$0$HttpDnsNetRequest(IDnsRequestListener iDnsRequestListener, Context context, String str, String[] strArr, long j) {
        if (iDnsRequestListener != null) {
            iDnsRequestListener.onThirdDns(this.mThirdDns.getDnsServerIp(), str, strArr, j);
        }
        if (strArr != null && strArr.length > 0) {
            HttpDnsCache.saveHttpDnsIPV4DataByHost(context, str, strArr);
            return;
        }
        ExceptionReporter exceptionReporter = this.mReporter;
        if (exceptionReporter != null) {
            exceptionReporter.report(ExceptionReporter.ExceptionType.TX_DNS_ERROR, str);
        }
    }

    private HttpDnsRequestStat createDnsRequestStat(String requestUrl, String requestParam) {
        HttpDnsRequestStat httpDnsRequestStat = new HttpDnsRequestStat();
        httpDnsRequestStat.setRequestUrl(requestUrl);
        httpDnsRequestStat.setRequest(requestParam);
        httpDnsRequestStat.setDomain(this.mOriginHost);
        httpDnsRequestStat.setCurrentRequestCount(this.currentRequestCount);
        httpDnsRequestStat.setMethod("get");
        return httpDnsRequestStat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HttpDnsRequestStat createDnsRequestStat(String requestUrl, Map<String, String> requestMap) {
        String string;
        if (requestMap != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : requestMap.entrySet()) {
                sb.append(entry.getKey());
                sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                sb.append(entry.getValue());
                sb.append("&");
            }
            sb.setLength(sb.length() - 1);
            string = sb.toString();
        } else {
            string = null;
        }
        return createDnsRequestStat(requestUrl, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkIpv4Valid(String dnsJson) {
        if (TextUtils.isEmpty(dnsJson)) {
            return false;
        }
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(dnsJson).optJSONArray("ips");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() >= 1) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject == null) {
                        return false;
                    }
                    String strOptString = jSONObjectOptJSONObject.optString("ip");
                    if (TextUtils.isEmpty(strOptString) || !HttpDnsUtil.isIpAddress(strOptString)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public HttpDnsNetRequest setExceptionReporter(ExceptionReporter reporter) {
        this.mReporter = reporter;
        return this;
    }
}
