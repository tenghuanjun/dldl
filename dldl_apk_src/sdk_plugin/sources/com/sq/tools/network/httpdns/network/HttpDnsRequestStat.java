package com.sq.tools.network.httpdns.network;

import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsRequestStat {
    private long cost;
    private long costTotal;
    private int currentRequestCount;
    private String domain;
    private String extra;
    private String method;
    private String msg;
    private String protocol;
    private String request;
    private String requestUrl;
    private String responseData;
    private String server_ip;
    private int statusCode;
    private int type;
    private String uri;

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCurrentRequestCount() {
        return this.currentRequestCount;
    }

    public void setCurrentRequestCount(int currentRequestCount) {
        this.currentRequestCount = currentRequestCount;
    }

    public long getCost() {
        return this.cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCostTotal() {
        return this.costTotal;
    }

    public void setCostTotal(long costTotal) {
        this.costTotal = costTotal;
    }

    public String getServer_ip() {
        return this.server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        try {
            URL url = new URL(requestUrl);
            setProtocol(url.getProtocol());
            setServer_ip(url.getAuthority());
            setUri(url.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getResponseData() {
        return this.responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
