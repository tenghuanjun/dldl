package com.sy37sdk.account.floatview;

import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tools.Logger;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.UrlUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.floatview.data.RedDot;
import com.sy37sdk.account.floatview.request.bean.FloatUserInfo;
import com.sy37sdk.account.uagree.UAgreeManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatViewDataManager {
    private static volatile FloatViewDataManager instance;
    private long lastRequestUserTime;
    private long MAX_REQUEST_USER_INFO_INTERVAL = 3000;
    private AccountRequestManager requestManager = new AccountRequestManager(SQContextWrapper.getApplicationContext());

    private FloatViewDataManager() {
    }

    public static FloatViewDataManager getInstance() {
        if (instance == null) {
            synchronized (UAgreeManager.class) {
                if (instance == null) {
                    instance = new FloatViewDataManager();
                }
            }
        }
        return instance;
    }

    public void requestPtUserInfo(final SqHttpCallback<JSONObject> sqHttpCallback) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastRequestUserTime > this.MAX_REQUEST_USER_INFO_INTERVAL) {
            final String userid = AccountCache.getUserid(SQContextWrapper.getApplicationContext());
            this.requestManager.getPtUserInfo(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.FloatViewDataManager.1
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    FloatViewDataManager.this.savePtUserInfo(userid, jSONObject.toString());
                    SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                    if (sqHttpCallback2 != null) {
                        sqHttpCallback2.onSuccess(jSONObject);
                    }
                }
            });
            this.lastRequestUserTime = jCurrentTimeMillis;
        }
    }

    public void requestRedDot(List<MenuConfig> list, SqHttpCallback.SimpleSqHttpCallback<JSONObject> simpleSqHttpCallback) {
        StringBuilder sb = new StringBuilder();
        String valueFromUrlStrByParamName = "";
        if (list != null && list.size() > 0) {
            for (MenuConfig menuConfig : list) {
                if (menuConfig.needRedDot()) {
                    sb.append(menuConfig.title);
                    sb.append(",");
                    if (TextUtils.isEmpty(valueFromUrlStrByParamName)) {
                        valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(menuConfig.openUrl, "page_uuid");
                    }
                }
            }
        }
        if (sb.length() <= 0) {
            Logger.info("不需要显示红点", new Object[0]);
        } else {
            this.requestManager.floatRedPoint(valueFromUrlStrByParamName, sb.substring(0, sb.length() - 1), simpleSqHttpCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePtUserInfo(String str, String str2) {
        try {
            FloatUserInfo floatUserInfo = FloatUserInfo.parse(str2);
            floatUserInfo.setUid(str);
            FloatViewDataCacheHelper.saveFloatUserInfo(floatUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyPtUserInfo(String str, String str2) {
        try {
            FloatUserInfo floatUserInfo = FloatViewDataCacheHelper.getFloatUserInfo();
            if (!TextUtils.isEmpty(str)) {
                floatUserInfo.setNickName(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                floatUserInfo.setAvatar(str2);
            }
            FloatViewDataCacheHelper.saveFloatUserInfo(floatUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FloatUserInfo getPtUserInfo() {
        return FloatViewDataCacheHelper.getFloatUserInfo();
    }

    public void requestAvatarList() {
        this.requestManager.getAvatarList(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.FloatViewDataManager.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                FloatViewDataManager.this.saveAvatars(jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAvatars(JSONObject jSONObject) {
        try {
            FloatViewDataCacheHelper.saveAvatars(jSONObject.optString("items"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAvatars() {
        return FloatViewDataCacheHelper.getAvatars();
    }

    public void modifyPersonInfo(String str, String str2, SqHttpCallback<Void> sqHttpCallback) {
        this.requestManager.modifyPersonInfo(str, str2, sqHttpCallback);
    }

    public List<RedDot> getRedDotCache() {
        return FloatViewDataCacheHelper.getRedDotCache();
    }

    public void saveRedDot(List<RedDot> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<RedDot> redDotCache = FloatViewDataCacheHelper.getRedDotCache();
        for (int i = 0; i < list.size(); i++) {
            RedDot redDot = list.get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= redDotCache.size()) {
                    i2 = -1;
                    break;
                }
                if (redDot.getTitle().equals(redDotCache.get(i2).getTitle())) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 != -1) {
                RedDot redDot2 = list.get(i2);
                redDot2.setRedDotTpe(redDot2.getRedDotTpe());
                redDot2.setNum(redDot2.getNum());
                arrayList.add(redDot2);
            } else {
                arrayList.add(redDot);
            }
        }
        FloatViewDataCacheHelper.saveRedDotCache(arrayList);
    }

    public RedDot getRedDotByKey(String str) {
        List<RedDot> redDotCache;
        if (!TextUtils.isEmpty(str) && (redDotCache = getRedDotCache()) != null && redDotCache.size() >= 1) {
            for (RedDot redDot : redDotCache) {
                if (str.equals(redDot.getTitle())) {
                    return redDot;
                }
            }
        }
        return null;
    }

    public void clearRedDotByKey(String str) {
        List<RedDot> redDotCache;
        if (TextUtils.isEmpty(str) || (redDotCache = getRedDotCache()) == null || redDotCache.size() < 1) {
            return;
        }
        for (int i = 0; i < redDotCache.size(); i++) {
            RedDot redDot = redDotCache.get(i);
            if (str.equals(redDot.getTitle())) {
                redDot.setNum(0);
                redDotCache.set(i, redDot);
                saveRedDot(redDotCache);
                return;
            }
        }
    }

    public void updateRedDotNum(RedDot redDot) {
        List<RedDot> redDotCache = getRedDotCache();
        if (redDotCache == null || redDotCache.size() < 1) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= redDotCache.size()) {
                i = -1;
                break;
            } else if (redDotCache.get(i).getTitle().equals(redDot.getTitle())) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            RedDot redDot2 = redDotCache.get(i);
            redDot2.setNum(redDot.getNum() + redDot2.getNum());
            redDotCache.set(i, redDot2);
            saveRedDot(redDotCache);
        }
    }

    public void clearRedDot() {
        FloatViewDataCacheHelper.clearRedDotCache();
    }

    public boolean hasRedDot() {
        List<RedDot> redDotCache = getRedDotCache();
        if (redDotCache != null && redDotCache.size() >= 1) {
            Iterator<RedDot> it = redDotCache.iterator();
            while (it.hasNext()) {
                if (it.next().getNum() > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
