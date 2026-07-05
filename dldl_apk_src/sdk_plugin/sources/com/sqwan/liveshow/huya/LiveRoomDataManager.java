package com.sqwan.liveshow.huya;

import com.huya.berry.client.HuyaBerry;
import com.huya.berry.client.customui.CustomUICallback;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.base.L;
import com.sqwan.common.util.LogUtil;
import com.sqwan.liveshow.huya.bean.ConfigBean;
import com.sqwan.liveshow.huya.bean.LiveMenuBean;
import com.sqwan.liveshow.huya.bean.RecommenedAnchorBean;
import com.sqwan.liveshow.huya.request.LiveRoomRequestManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveRoomDataManager {
    public static final int PLATFORM_HUYA = 1;
    public static final int PLATFPRM_RECOMMENDED = 0;
    private static LiveRoomDataManager liveRoomDataManager = new LiveRoomDataManager();
    private ConfigBean configBean;
    private LiveRoomRequestManager liveRoomRequestManager = new LiveRoomRequestManager(L.getApplicationContext());
    private Map<Integer, List<LiveListInfo>> data = new HashMap();

    public interface RequestListener {
        void onFailure(int i, String str);

        void onHuyaAnchorInfoCallback(Map<Integer, List<LiveListInfo>> map);

        void onLiveMenuDataCallback(LiveMenuBean liveMenuBean);

        void onRecommendedAnchorDataCallback(Map<Integer, List<LiveListInfo>> map);
    }

    private LiveRoomDataManager() {
    }

    public static LiveRoomDataManager getInstance() {
        return liveRoomDataManager;
    }

    public ConfigBean getConfigBean() {
        return this.configBean;
    }

    public void setConfigBean(ConfigBean configBean) {
        this.configBean = configBean;
    }

    public void getConfigData(final SqHttpCallback<ConfigBean> sqHttpCallback) {
        this.liveRoomRequestManager.ReqGetConfigData(new SqHttpCallback<ConfigBean>() { // from class: com.sqwan.liveshow.huya.LiveRoomDataManager.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onResponseStateError(i, i2, str, str2);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(ConfigBean configBean) {
                if (sqHttpCallback != null) {
                    LiveRoomDataManager.this.configBean = configBean;
                    sqHttpCallback.onSuccess(configBean);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onFailure(i, str, volleyError);
                }
            }
        });
    }

    public boolean isHasplatformData(int i) {
        Map<Integer, List<LiveListInfo>> map = this.data;
        return (map == null || map.get(Integer.valueOf(i)) == null) ? false : true;
    }

    public List<LiveListInfo> getPlatformData(int i) {
        Map<Integer, List<LiveListInfo>> map = this.data;
        if (map != null) {
            return map.get(Integer.valueOf(i));
        }
        return null;
    }

    public void clearPlatformData() {
        if (this.data != null) {
            LogUtil.i("清除数据");
            this.data.clear();
        }
    }

    public void putPlatformData(int i, List<LiveListInfo> list) {
        Map<Integer, List<LiveListInfo>> map = this.data;
        if (map != null) {
            map.put(Integer.valueOf(i), list);
        }
    }

    public void getLiveMenuData(final RequestListener requestListener) {
        this.liveRoomRequestManager.ReqGetLiveMenuData(new SqHttpCallback<LiveMenuBean>() { // from class: com.sqwan.liveshow.huya.LiveRoomDataManager.2
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                RequestListener requestListener2 = requestListener;
                if (requestListener2 != null) {
                    requestListener2.onFailure(i2, str);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(LiveMenuBean liveMenuBean) {
                RequestListener requestListener2 = requestListener;
                if (requestListener2 != null) {
                    requestListener2.onLiveMenuDataCallback(liveMenuBean);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                RequestListener requestListener2 = requestListener;
                if (requestListener2 != null) {
                    requestListener2.onFailure(i, str);
                }
            }
        });
    }

    public void getRecommendedAnchorData(final RequestListener requestListener) {
        this.liveRoomRequestManager.ReqGetRecommendedAnchorData(new SqHttpCallback<RecommenedAnchorBean>() { // from class: com.sqwan.liveshow.huya.LiveRoomDataManager.3
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                RequestListener requestListener2 = requestListener;
                if (requestListener2 != null) {
                    requestListener2.onFailure(i2, str);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(RecommenedAnchorBean recommenedAnchorBean) {
                ArrayList arrayList = new ArrayList();
                for (RecommenedAnchorBean.ItemsBean itemsBean : recommenedAnchorBean.getItems()) {
                    if (itemsBean.getPlatform_id() == 1) {
                        arrayList.add(itemsBean.getAnchor_id());
                    }
                }
                LiveRoomDataManager.this.getHuyaAnchorLiveInfo(arrayList, requestListener, 0);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                RequestListener requestListener2 = requestListener;
                if (requestListener2 != null) {
                    requestListener2.onFailure(i, str);
                }
            }
        });
    }

    public void getHuyaAnchorLiveInfo(List<String> list, RequestListener requestListener, int i) {
        int i2;
        ConfigBean configBean = this.configBean;
        int times = 1;
        if (configBean == null || configBean.getItems() == null) {
            if (requestListener != null) {
                requestListener.onFailure(0, "获取直播接口初始化数据失败");
            }
            i2 = 1;
        } else {
            Iterator<ConfigBean.ItemsBean> it = this.configBean.getItems().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ConfigBean.ItemsBean next = it.next();
                if (next.getPlatform_id() == 1) {
                    times = next.getTimes();
                    break;
                }
            }
            i2 = times;
        }
        getanchorMessageFromPlatform(true, i2, list, new ArrayList<>(), new ArrayList<>(), requestListener, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getanchorMessageFromPlatform(boolean z, final int i, final List<String> list, final ArrayList<LiveListInfo> arrayList, final ArrayList<LiveListInfo> arrayList2, final RequestListener requestListener, final int i2) {
        if (i == 0) {
            for (String str : list) {
                for (LiveListInfo liveListInfo : arrayList) {
                    try {
                        if (Long.parseLong(str) == liveListInfo.uid && !arrayList2.contains(liveListInfo)) {
                            arrayList2.add(liveListInfo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (i2 == 0) {
                this.data.put(0, arrayList2);
                requestListener.onRecommendedAnchorDataCallback(this.data);
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                this.data.put(1, arrayList2);
                requestListener.onHuyaAnchorInfoCallback(this.data);
                return;
            }
        }
        HuyaBerry.instance().getLiveListData(z, new CustomUICallback<LiveListInfo>() { // from class: com.sqwan.liveshow.huya.LiveRoomDataManager.4
            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultCallback(int i3, LiveListInfo liveListInfo2) {
            }

            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultListCallback(int i3, List<LiveListInfo> list2) {
                if (i3 == 0) {
                    arrayList.addAll(list2);
                    LiveRoomDataManager.this.getanchorMessageFromPlatform(false, i - 1, list, arrayList, arrayList2, requestListener, i2);
                } else {
                    RequestListener requestListener2 = requestListener;
                    if (requestListener2 != null) {
                        requestListener2.onFailure(i3, "获取虎牙列表数据失败");
                    }
                }
            }
        });
    }
}
