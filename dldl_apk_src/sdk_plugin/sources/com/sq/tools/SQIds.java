package com.sq.tools;

import android.content.Context;
import android.util.Xml;
import com.huya.statistics.core.StatisticsContent;
import com.sqwan.bugless.core.ParamsManager;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum SQIds {
    ID;

    private volatile boolean isInit;
    protected final String CONFIG_FILE_NAME = ParamsManager.CONFIG_FILE_NAME;
    protected final String CONFIG_NODE_GAMEID = ParamsManager.CONFIG_NODE_GAMEID;
    protected final String CONFIG_NODE_PARTNER = ParamsManager.CONFIG_NODE_PARTNER;
    protected final String CONFIG_NODE_REFER = "referer";
    protected final String CONFIG_NODE_KEY = StatisticsContent.APPKEY;
    public String gameId = "1000001";
    public String partner = "1";
    public String refer = "";
    public String appkey = "";
    public String cid = "";
    public String pgid = "";
    public String tgid = "";

    SQIds() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v39 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public void init(Context context) throws Throwable {
        InputStreamReader inputStreamReader;
        if (this.isInit) {
            return;
        }
        ?? r2 = 0;
        InputStreamReader inputStreamReader2 = null;
        InputStreamReader inputStreamReader3 = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(context.getAssets().open(ParamsManager.CONFIG_FILE_NAME, 3), StandardCharsets.UTF_8);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (XmlPullParserException e2) {
            e = e2;
        }
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamReader);
            int eventType = xmlPullParserNewPullParser.getEventType();
            while (eventType != 1) {
                if (eventType != 2) {
                    eventType = xmlPullParserNewPullParser.next();
                } else {
                    if (ParamsManager.CONFIG_NODE_GAMEID.equals(xmlPullParserNewPullParser.getName())) {
                        this.gameId = xmlPullParserNewPullParser.nextText().trim();
                    }
                    if (ParamsManager.CONFIG_NODE_PARTNER.equals(xmlPullParserNewPullParser.getName())) {
                        this.partner = xmlPullParserNewPullParser.nextText().trim();
                    }
                    if ("referer".equals(xmlPullParserNewPullParser.getName())) {
                        this.refer = xmlPullParserNewPullParser.nextText().trim();
                    }
                    if (StatisticsContent.APPKEY.equals(xmlPullParserNewPullParser.getName())) {
                        this.appkey = xmlPullParserNewPullParser.nextText().trim();
                    }
                    eventType = xmlPullParserNewPullParser.next();
                }
            }
            this.isInit = true;
            try {
                inputStreamReader.close();
                r2 = eventType;
            } catch (IOException e3) {
                Object[] objArr = {"IdKeeper InputStreamReader", e3};
                Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", objArr);
                r2 = objArr;
            }
        } catch (IOException e4) {
            e = e4;
            inputStreamReader2 = inputStreamReader;
            Logger.error("Init failed, miss 37wan_config.xml or 37wan_config.xml was damaged", e);
            r2 = inputStreamReader2;
            if (inputStreamReader2 != null) {
                try {
                    inputStreamReader2.close();
                    r2 = inputStreamReader2;
                } catch (IOException e5) {
                    Object[] objArr2 = {"IdKeeper InputStreamReader", e5};
                    Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", objArr2);
                    r2 = objArr2;
                }
            }
        } catch (XmlPullParserException e6) {
            e = e6;
            inputStreamReader3 = inputStreamReader;
            Logger.error("Init failed, parse 37wan_config exception", e);
            r2 = inputStreamReader3;
            if (inputStreamReader3 != null) {
                try {
                    inputStreamReader3.close();
                    r2 = inputStreamReader3;
                } catch (IOException e7) {
                    Object[] objArr3 = {"IdKeeper InputStreamReader", e7};
                    Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", objArr3);
                    r2 = objArr3;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            r2 = inputStreamReader;
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e8) {
                    Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", "IdKeeper InputStreamReader", e8);
                }
            }
            throw th;
        }
    }
}
