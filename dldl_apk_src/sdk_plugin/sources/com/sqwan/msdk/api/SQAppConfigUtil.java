package com.sqwan.msdk.api;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.util.ChannelUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpUtils;
import com.sqwan.msdk.SQReportCore;
import com.sqwan.msdk.config.MultiSdkManager;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQAppConfigUtil {
    public static String CONFIG_NODE_GAMEID = "gameid";
    public static String CONFIG_NODE_PARTNER = "partner";
    public static String CONFIG_NODE_REFER = "referer";
    private static final String CONFIG_REFER_CONTENT = "sy00000_1";
    private static final String SP_KEY_GID = "gid";
    private static final String SP_KEY_PID = "pid";
    private static final String SP_KEY_REFER = "refer";
    private Context context;
    private String gameid = "1000001";
    private String partner = "1";
    private String refer = "";

    public SQAppConfigUtil(Context context) {
        this.context = context;
    }

    public void init() {
        getGameInfo();
        getChannelInfoFromAPK(this.context);
        getAdvertiseMediaInfo();
        saveConfigValue();
    }

    public SQAppConfig getSQAppConfig() {
        return new SQAppConfig(this.gameid, this.partner, this.refer);
    }

    private void saveConfigValue() {
        SpUtils.get(this.context).put("gid", this.gameid);
        SpUtils.get(this.context).put("pid", this.partner);
        SpUtils.get(this.context).put("refer", this.refer);
    }

    private void getGameInfo() {
        try {
            InputStream inputStreamOpen = this.context.getAssets().open(MultiSdkManager.getInstance().getConfig());
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamOpen, "utf-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    if (xmlPullParserNewPullParser.getName().equals(CONFIG_NODE_GAMEID)) {
                        this.gameid = xmlPullParserNewPullParser.nextText().trim();
                    }
                    if (xmlPullParserNewPullParser.getName().equals(CONFIG_NODE_PARTNER)) {
                        this.partner = xmlPullParserNewPullParser.nextText().trim();
                    }
                    if (xmlPullParserNewPullParser.getName().equals(CONFIG_NODE_REFER)) {
                        this.refer = getLocalRefer(this.context, xmlPullParserNewPullParser.nextText().trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("解析配置文件出错");
        }
    }

    public String getLocalRefer(Context context, String str) {
        String string = SpUtils.get(context).getString("refer", CONFIG_REFER_CONTENT);
        return (!str.equals(CONFIG_REFER_CONTENT) || TextUtils.isEmpty(string)) ? str : string;
    }

    private void getChannelInfoFromAPK(Context context) {
        if (!"1".equals(this.partner)) {
            LogUtil.e("pid is not 1");
            return;
        }
        try {
            String channelFromApk = ChannelUtil.getChannelFromApk(context);
            LogUtil.e("channel info is " + channelFromApk);
            if ("".equals(channelFromApk)) {
                return;
            }
            String[] strArrSplit = channelFromApk.split("-");
            if (strArrSplit.length == 3) {
                this.gameid = strArrSplit[0];
                this.partner = strArrSplit[1];
                this.refer = strArrSplit[2];
            }
        } catch (Exception e) {
            LogUtil.e("读取 apk 渠道信息失败！" + e.getMessage());
        }
    }

    private void getAdvertiseMediaInfo() {
        LogUtil.i("从广告媒体中读取渠道号");
        String refer = SQReportCore.getInstance().getRefer();
        if (!TextUtils.isEmpty(refer)) {
            LogUtil.i("广告媒体读取的refer为 " + refer);
            this.refer = this.partner + "_" + this.gameid + "_" + refer;
            return;
        }
        LogUtil.i("广告媒体读取的refer为空");
    }
}
