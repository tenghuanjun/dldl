package com.alicom.tools.networking;

import android.text.TextUtils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class RequestManager {
    private static volatile RequestManager mInstance;
    private Request request;
    private int type = 0;
    private int connectTime = 3000;
    private int readTime = 3000;

    public static RequestManager getInstance() {
        if (mInstance == null) {
            synchronized (RequestManager.class) {
                if (mInstance == null) {
                    mInstance = new RequestManager();
                }
            }
        }
        return mInstance;
    }

    public ResultMsg excuse() {
        String localizedMessage = NetConstant.MSG_ALICOMNETWORK_HOST;
        ResultMsg resultMsg = new ResultMsg();
        Request request = this.request;
        if (request == null) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_PARAMAERROR);
            localizedMessage = NetConstant.MSG_ALICOMNETWORK_PARAMAERROR;
        } else if (TextUtils.isEmpty(request.getBaseUrl())) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_URL);
            localizedMessage = NetConstant.MSG_ALICOMNETWORK_URL;
        } else {
            try {
                if (TextUtils.isEmpty(new URL(this.request.getBaseUrl()).getHost())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
                    resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_HOST);
                    resultMsg.setSuccess(false);
                    return resultMsg;
                }
                if (TextUtils.isEmpty(this.request.getMethod()) && TextUtils.isEmpty(this.request.getAction())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_API);
                    localizedMessage = NetConstant.MSG_ALICOMNETWORK_API;
                } else if (this.request.isSign() && TextUtils.isEmpty(this.request.getAccessKeySecret())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SECREKEY);
                    localizedMessage = NetConstant.MSG_ALICOMNETWORK_SECREKEY;
                } else {
                    try {
                        String strCallHttpsApi = this.request.getBaseUrl().startsWith("https://") ? AlicomHttpUtils.callHttpsApi(this.request, this.connectTime, this.readTime, this.type) : AlicomHttpUtils.callApi(this.request, this.connectTime, this.readTime, this.type);
                        if (TextUtils.isEmpty(strCallHttpsApi) || "{}".equals(strCallHttpsApi)) {
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_DATAERROR);
                            resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_DATAERROR);
                            resultMsg.setSuccess(false);
                        } else {
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SUCCESS);
                            resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_SUCCESS);
                            resultMsg.setSuccess(true);
                            resultMsg.setResult(strCallHttpsApi);
                        }
                        return resultMsg;
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_NETWORK);
                        localizedMessage = e.getLocalizedMessage();
                    }
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
            }
        }
        resultMsg.setMsg(localizedMessage);
        resultMsg.setSuccess(false);
        return resultMsg;
    }

    public void excuse(RequestCallback requestCallback) {
        String str = NetConstant.MSG_ALICOMNETWORK_HOST;
        if (requestCallback == null) {
            return;
        }
        ResultMsg resultMsg = new ResultMsg();
        Request request = this.request;
        if (request == null) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_PARAMAERROR);
            str = NetConstant.MSG_ALICOMNETWORK_PARAMAERROR;
        } else if (TextUtils.isEmpty(request.getBaseUrl())) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_URL);
            str = NetConstant.MSG_ALICOMNETWORK_URL;
        } else {
            try {
                if (TextUtils.isEmpty(new URL(this.request.getBaseUrl()).getHost())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
                    resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_HOST);
                    resultMsg.setSuccess(false);
                    requestCallback.onResult(resultMsg);
                    return;
                }
                if (TextUtils.isEmpty(this.request.getMethod()) && TextUtils.isEmpty(this.request.getAction())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_API);
                    str = NetConstant.MSG_ALICOMNETWORK_API;
                } else {
                    if (!this.request.isSign() || !TextUtils.isEmpty(this.request.getAccessKeySecret())) {
                        try {
                            String strCallHttpsApi = this.request.getBaseUrl().startsWith("https://") ? AlicomHttpUtils.callHttpsApi(this.request, this.connectTime, this.readTime, this.type) : AlicomHttpUtils.callApi(this.request, this.connectTime, this.readTime, this.type);
                            if (TextUtils.isEmpty(strCallHttpsApi) || "{}".equals(strCallHttpsApi)) {
                                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_DATAERROR);
                                resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_DATAERROR);
                                resultMsg.setSuccess(false);
                            } else {
                                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SUCCESS);
                                resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_SUCCESS);
                                resultMsg.setSuccess(true);
                                resultMsg.setResult(strCallHttpsApi);
                            }
                            requestCallback.onResult(resultMsg);
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_NETWORK);
                            resultMsg.setMsg(e.getLocalizedMessage());
                            resultMsg.setSuccess(false);
                            requestCallback.onResult(resultMsg);
                            return;
                        }
                    }
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SECREKEY);
                    str = NetConstant.MSG_ALICOMNETWORK_SECREKEY;
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
            }
        }
        resultMsg.setMsg(str);
        resultMsg.setSuccess(false);
        requestCallback.onResult(resultMsg);
    }

    public ResultMsg excusePopRequest() {
        String localizedMessage = NetConstant.MSG_ALICOMNETWORK_HOST;
        ResultMsg resultMsg = new ResultMsg();
        Request request = this.request;
        if (request == null) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_PARAMAERROR);
            localizedMessage = NetConstant.MSG_ALICOMNETWORK_PARAMAERROR;
        } else if (TextUtils.isEmpty(request.getBaseUrl())) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_URL);
            localizedMessage = NetConstant.MSG_ALICOMNETWORK_URL;
        } else {
            try {
                if (TextUtils.isEmpty(new URL(this.request.getBaseUrl()).getHost())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
                    resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_HOST);
                    resultMsg.setSuccess(false);
                    return resultMsg;
                }
                if (TextUtils.isEmpty(this.request.getMethod()) && TextUtils.isEmpty(this.request.getAction())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_API);
                    localizedMessage = NetConstant.MSG_ALICOMNETWORK_API;
                } else if (this.request.isSign() && TextUtils.isEmpty(this.request.getAccessKeySecret())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SECREKEY);
                    localizedMessage = NetConstant.MSG_ALICOMNETWORK_SECREKEY;
                } else {
                    try {
                        this.request.setRequestMethod("POST");
                        this.type = 0;
                        String strCallHttpsApi = this.request.getBaseUrl().startsWith("https://") ? AlicomHttpUtils.callHttpsApi(this.request, this.connectTime, this.readTime, this.type) : AlicomHttpUtils.callApi(this.request, this.connectTime, this.readTime, this.type);
                        if (TextUtils.isEmpty(strCallHttpsApi) || "{}".equals(strCallHttpsApi)) {
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_DATAERROR);
                            resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_DATAERROR);
                            resultMsg.setSuccess(false);
                        } else {
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SUCCESS);
                            resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_SUCCESS);
                            resultMsg.setSuccess(true);
                            resultMsg.setResult(strCallHttpsApi);
                        }
                        return resultMsg;
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_NETWORK);
                        localizedMessage = e.getLocalizedMessage();
                    }
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
            }
        }
        resultMsg.setMsg(localizedMessage);
        resultMsg.setSuccess(false);
        return resultMsg;
    }

    public void excusePopRequest(RequestCallback requestCallback) {
        String str = NetConstant.MSG_ALICOMNETWORK_HOST;
        if (requestCallback == null) {
            return;
        }
        ResultMsg resultMsg = new ResultMsg();
        Request request = this.request;
        if (request == null) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_PARAMAERROR);
            str = NetConstant.MSG_ALICOMNETWORK_PARAMAERROR;
        } else if (TextUtils.isEmpty(request.getBaseUrl())) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_URL);
            str = NetConstant.MSG_ALICOMNETWORK_URL;
        } else {
            try {
                if (TextUtils.isEmpty(new URL(this.request.getBaseUrl()).getHost())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
                    resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_HOST);
                    resultMsg.setSuccess(false);
                    requestCallback.onResult(resultMsg);
                    return;
                }
                if (TextUtils.isEmpty(this.request.getMethod()) && TextUtils.isEmpty(this.request.getAction())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_API);
                    str = NetConstant.MSG_ALICOMNETWORK_API;
                } else {
                    if (!this.request.isSign() || !TextUtils.isEmpty(this.request.getAccessKeySecret())) {
                        try {
                            this.type = 0;
                            String strCallHttpsApi = this.request.getBaseUrl().startsWith("https://") ? AlicomHttpUtils.callHttpsApi(this.request, this.connectTime, this.readTime, this.type) : AlicomHttpUtils.callApi(this.request, this.connectTime, this.readTime, this.type);
                            if (TextUtils.isEmpty(strCallHttpsApi) || "{}".equals(strCallHttpsApi)) {
                                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_DATAERROR);
                                resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_DATAERROR);
                                resultMsg.setSuccess(false);
                            } else {
                                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SUCCESS);
                                resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_SUCCESS);
                                resultMsg.setSuccess(true);
                                resultMsg.setResult(strCallHttpsApi);
                            }
                            requestCallback.onResult(resultMsg);
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_NETWORK);
                            resultMsg.setMsg(e.getLocalizedMessage());
                            resultMsg.setSuccess(false);
                            requestCallback.onResult(resultMsg);
                            return;
                        }
                    }
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SECREKEY);
                    str = NetConstant.MSG_ALICOMNETWORK_SECREKEY;
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
            }
        }
        resultMsg.setMsg(str);
        resultMsg.setSuccess(false);
        requestCallback.onResult(resultMsg);
    }

    public ResultMsg excuseTopRequest() {
        String localizedMessage = NetConstant.MSG_ALICOMNETWORK_HOST;
        ResultMsg resultMsg = new ResultMsg();
        Request request = this.request;
        if (request == null) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_PARAMAERROR);
            localizedMessage = NetConstant.MSG_ALICOMNETWORK_PARAMAERROR;
        } else if (TextUtils.isEmpty(request.getBaseUrl())) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_URL);
            localizedMessage = NetConstant.MSG_ALICOMNETWORK_URL;
        } else {
            try {
                if (TextUtils.isEmpty(new URL(this.request.getBaseUrl()).getHost())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
                    resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_HOST);
                    resultMsg.setSuccess(false);
                    return resultMsg;
                }
                if (TextUtils.isEmpty(this.request.getMethod()) && TextUtils.isEmpty(this.request.getAction())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_API);
                    localizedMessage = NetConstant.MSG_ALICOMNETWORK_API;
                } else if (this.request.isSign() && TextUtils.isEmpty(this.request.getAccessKeySecret())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SECREKEY);
                    localizedMessage = NetConstant.MSG_ALICOMNETWORK_SECREKEY;
                } else {
                    try {
                        this.type = 1;
                        String strCallHttpsApi = this.request.getBaseUrl().startsWith("https://") ? AlicomHttpUtils.callHttpsApi(this.request, this.connectTime, this.readTime, this.type) : AlicomHttpUtils.callApi(this.request, this.connectTime, this.readTime, this.type);
                        if (TextUtils.isEmpty(strCallHttpsApi) || "{}".equals(strCallHttpsApi)) {
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_DATAERROR);
                            resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_DATAERROR);
                            resultMsg.setSuccess(false);
                        } else {
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SUCCESS);
                            resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_SUCCESS);
                            resultMsg.setSuccess(true);
                            resultMsg.setResult(strCallHttpsApi);
                        }
                        return resultMsg;
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_NETWORK);
                        localizedMessage = e.getLocalizedMessage();
                    }
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
            }
        }
        resultMsg.setMsg(localizedMessage);
        resultMsg.setSuccess(false);
        return resultMsg;
    }

    public void excuseTopRequest(RequestCallback requestCallback) {
        String str = NetConstant.MSG_ALICOMNETWORK_HOST;
        if (requestCallback == null) {
            return;
        }
        ResultMsg resultMsg = new ResultMsg();
        Request request = this.request;
        if (request == null) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_PARAMAERROR);
            str = NetConstant.MSG_ALICOMNETWORK_PARAMAERROR;
        } else if (TextUtils.isEmpty(request.getBaseUrl())) {
            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_URL);
            str = NetConstant.MSG_ALICOMNETWORK_URL;
        } else {
            try {
                if (TextUtils.isEmpty(new URL(this.request.getBaseUrl()).getHost())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
                    resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_HOST);
                    resultMsg.setSuccess(false);
                    requestCallback.onResult(resultMsg);
                    return;
                }
                if (TextUtils.isEmpty(this.request.getMethod()) && TextUtils.isEmpty(this.request.getAction())) {
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_API);
                    str = NetConstant.MSG_ALICOMNETWORK_API;
                } else {
                    if (!this.request.isSign() || !TextUtils.isEmpty(this.request.getAccessKeySecret())) {
                        try {
                            this.type = 1;
                            String strCallHttpsApi = this.request.getBaseUrl().startsWith("https://") ? AlicomHttpUtils.callHttpsApi(this.request, this.connectTime, this.readTime, this.type) : AlicomHttpUtils.callApi(this.request, this.connectTime, this.readTime, this.type);
                            if (TextUtils.isEmpty(strCallHttpsApi) || "{}".equals(strCallHttpsApi)) {
                                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_DATAERROR);
                                resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_DATAERROR);
                                resultMsg.setSuccess(false);
                            } else {
                                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SUCCESS);
                                resultMsg.setMsg(NetConstant.MSG_ALICOMNETWORK_SUCCESS);
                                resultMsg.setSuccess(true);
                                resultMsg.setResult(strCallHttpsApi);
                            }
                            requestCallback.onResult(resultMsg);
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_NETWORK);
                            resultMsg.setMsg(e.getLocalizedMessage());
                            resultMsg.setSuccess(false);
                            requestCallback.onResult(resultMsg);
                            return;
                        }
                    }
                    resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_SECREKEY);
                    str = NetConstant.MSG_ALICOMNETWORK_SECREKEY;
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                resultMsg.setCode(NetConstant.CODE_ALICOMNETWORK_HOST);
            }
        }
        resultMsg.setMsg(str);
        resultMsg.setSuccess(false);
        requestCallback.onResult(resultMsg);
    }

    public RequestManager request(Request request) {
        this.request = request;
        return mInstance;
    }

    public RequestManager setConnectTime(int i) {
        this.connectTime = i;
        return mInstance;
    }

    public RequestManager setReadTime(int i) {
        this.readTime = i;
        return mInstance;
    }

    public RequestManager setType(int i) {
        this.type = i;
        return mInstance;
    }
}
