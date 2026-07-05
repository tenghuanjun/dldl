package com.sy37sdk.order;

import android.content.Context;
import android.os.Bundle;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqnetwork.voly.VolleyLog;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class OrderRequestManager {
    private final Context mContext;

    public OrderRequestManager(Context context) {
        this.mContext = context;
    }

    public void checkPay(String str, String str2, SqHttpCallback<Void> sqHttpCallback) {
        SqRequest.of(OrderUrl.PAY_CHECK).params(generateCommonParamsOfPay(this.mContext, str)).addParam("uuid", str).addParam(SqConstants.PAYWAY, str2).post(sqHttpCallback, Void.class);
    }

    public static Map<String, String> generateCommonParamsOfPay(Context context, String str) {
        HashMap map = new HashMap();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
        String gameid = sQAppConfig.getGameid();
        String partner = sQAppConfig.getPartner();
        String appKey = ConfigManager.getInstance(context).getAppKey();
        String refer = sQAppConfig.getRefer();
        String str2 = (System.currentTimeMillis() / 1000) + "";
        String str3 = partner + gameid + str2 + appKey + ((IAccountMod) ModHelper.get(IAccountMod.class)).getUid() + str;
        String lowerCase = MD5Util.Md5(str3).toLowerCase();
        if (VolleyLog.VERBOSE) {
            VolleyLog.i("[Vn]签名原串: %s\n签名结果: %s", str3, lowerCase);
        }
        map.put("gid", gameid);
        map.put("pid", partner);
        map.put("refer", refer);
        map.put("dev", DevLogic.getInstance(context).getValue());
        map.put("time", str2);
        map.put(SqConstants.SIGN, lowerCase);
        map.put("scut", ConfigManager.getInstance(context).getLoginCode() + "");
        map.put("token", ((IAccountMod) ModHelper.get(IAccountMod.class)).getToken());
        map.put("gwversion", "4.6.7");
        map.put("sversion", VersionUtil.sdkVersion);
        map.put(SqConstants.HOST_SDK_VERSION, VersionUtil.getOriginalVersion());
        return map;
    }

    public static Bundle addCommonParamsOfPay(Context context, Bundle bundle, String str, String str2) {
        SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
        String gameid = sQAppConfig.getGameid();
        String partner = sQAppConfig.getPartner();
        String appKey = ConfigManager.getInstance(context).getAppKey();
        String refer = sQAppConfig.getRefer();
        String str3 = "" + DevLogic.getInstance(context).getValue();
        String str4 = "" + (System.currentTimeMillis() / 1000);
        String lowerCase = MD5Util.Md5((partner + gameid + str4 + appKey) + str + str2).toLowerCase();
        bundle.putString("gid", gameid);
        bundle.putString("pid", partner);
        bundle.putString("refer", refer);
        bundle.putString("dev", str3);
        bundle.putString("time", str4);
        bundle.putString(SqConstants.SIGN, lowerCase);
        bundle.putString("scut", ConfigManager.getInstance(context).getLoginCode() + "");
        if (MultiSdkManager.getInstance().isScut3()) {
            bundle.putString("scut3", MultiSdkManager.getInstance().getScut3());
        }
        return bundle;
    }
}
