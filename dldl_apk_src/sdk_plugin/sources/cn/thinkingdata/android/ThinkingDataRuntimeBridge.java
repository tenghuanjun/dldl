package cn.thinkingdata.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ThinkingDataRuntimeBridge {
    private static final String TAG = "ThinkingAnalytics.ThinkingDataRuntimeBridge";

    class a implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ Object a;

        a(Object obj) {
            this.a = obj;
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0090 A[Catch: JSONException -> 0x0120, TRY_ENTER, TryCatch #1 {JSONException -> 0x0120, blocks: (B:17:0x005a, B:20:0x006c, B:28:0x0089, B:31:0x0090, B:33:0x0098, B:35:0x009d, B:39:0x00bf, B:41:0x00c5, B:43:0x00d3, B:53:0x0110, B:45:0x00de, B:47:0x00ee, B:49:0x00f8, B:51:0x0106, B:54:0x0114, B:56:0x011a, B:36:0x00b4, B:38:0x00bc, B:24:0x0077, B:26:0x0081), top: B:63:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00b4 A[Catch: JSONException -> 0x0120, TryCatch #1 {JSONException -> 0x0120, blocks: (B:17:0x005a, B:20:0x006c, B:28:0x0089, B:31:0x0090, B:33:0x0098, B:35:0x009d, B:39:0x00bf, B:41:0x00c5, B:43:0x00d3, B:53:0x0110, B:45:0x00de, B:47:0x00ee, B:49:0x00f8, B:51:0x0106, B:54:0x0114, B:56:0x011a, B:36:0x00b4, B:38:0x00bc, B:24:0x0077, B:26:0x0081), top: B:63:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00c5 A[Catch: JSONException -> 0x0120, TryCatch #1 {JSONException -> 0x0120, blocks: (B:17:0x005a, B:20:0x006c, B:28:0x0089, B:31:0x0090, B:33:0x0098, B:35:0x009d, B:39:0x00bf, B:41:0x00c5, B:43:0x00d3, B:53:0x0110, B:45:0x00de, B:47:0x00ee, B:49:0x00f8, B:51:0x0106, B:54:0x0114, B:56:0x011a, B:36:0x00b4, B:38:0x00bc, B:24:0x0077, B:26:0x0081), top: B:63:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00de A[Catch: JSONException -> 0x0120, TryCatch #1 {JSONException -> 0x0120, blocks: (B:17:0x005a, B:20:0x006c, B:28:0x0089, B:31:0x0090, B:33:0x0098, B:35:0x009d, B:39:0x00bf, B:41:0x00c5, B:43:0x00d3, B:53:0x0110, B:45:0x00de, B:47:0x00ee, B:49:0x00f8, B:51:0x0106, B:54:0x0114, B:56:0x011a, B:36:0x00b4, B:38:0x00bc, B:24:0x0077, B:26:0x0081), top: B:63:0x005a }] */
        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void process(cn.thinkingdata.android.ThinkingAnalyticsSDK r9) {
            /*
                Method dump skipped, instruction units count: 296
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.ThinkingDataRuntimeBridge.a.process(cn.thinkingdata.android.ThinkingAnalyticsSDK):void");
        }
    }

    class b implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ JSONObject c;

        b(String str, String str2, JSONObject jSONObject) {
            this.a = str;
            this.b = str2;
            this.c = jSONObject;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            if (thinkingAnalyticsSDK.isAutoTrackEnabled()) {
                if (TextUtils.isEmpty(this.a) || thinkingAnalyticsSDK.getToken().equals(this.a)) {
                    thinkingAnalyticsSDK.track(this.b, this.c);
                }
            }
        }
    }

    class c implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ JSONObject c;

        c(String str, String str2, JSONObject jSONObject) {
            this.a = str;
            this.b = str2;
            this.c = jSONObject;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            if (thinkingAnalyticsSDK.isAutoTrackEnabled()) {
                if (TextUtils.isEmpty(this.a) || thinkingAnalyticsSDK.getToken().equals(this.a)) {
                    thinkingAnalyticsSDK.track(this.b, this.c);
                }
            }
        }
    }

    class d implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ Object a;
        final /* synthetic */ View b;

        d(Object obj, View view) {
            this.a = obj;
            this.b = view;
        }

        /* JADX WARN: Removed duplicated region for block: B:235:0x0479 A[Catch: Exception -> 0x04a0, TryCatch #0 {Exception -> 0x04a0, blocks: (B:3:0x0012, B:6:0x0019, B:9:0x0022, B:11:0x0026, B:13:0x002c, B:15:0x003a, B:18:0x0049, B:20:0x004f, B:22:0x005d, B:25:0x006c, B:27:0x0072, B:29:0x007c, B:31:0x0083, B:33:0x008f, B:35:0x0095, B:38:0x00a0, B:40:0x00a8, B:42:0x00ae, B:45:0x00b9, B:53:0x00e7, B:55:0x0102, B:58:0x010d, B:61:0x0116, B:63:0x0130, B:65:0x0138, B:67:0x013d, B:69:0x0145, B:71:0x015a, B:73:0x0162, B:85:0x0186, B:88:0x0198, B:228:0x045c, B:230:0x0462, B:232:0x046a, B:233:0x0471, B:235:0x0479, B:236:0x047c, B:238:0x0491, B:239:0x049a, B:93:0x01a9, B:95:0x01b1, B:97:0x01bb, B:99:0x01c3, B:103:0x01d8, B:100:0x01ca, B:102:0x01d2, B:107:0x01ea, B:128:0x0265, B:129:0x026a, B:131:0x0270, B:133:0x027a, B:135:0x0283, B:137:0x0289, B:134:0x027f, B:141:0x0294, B:144:0x029c, B:154:0x02c5, B:156:0x02cb, B:158:0x02d7, B:161:0x02df, B:163:0x02e9, B:164:0x02ee, B:166:0x02f6, B:168:0x02fc, B:170:0x0308, B:172:0x030e, B:174:0x031a, B:176:0x0320, B:178:0x032c, B:181:0x0334, B:183:0x0342, B:190:0x0361, B:184:0x0347, B:187:0x034f, B:189:0x035d, B:191:0x0367, B:193:0x036d, B:195:0x037d, B:197:0x0383, B:199:0x0393, B:211:0x03d5, B:213:0x03db, B:219:0x042b, B:215:0x03fe, B:217:0x0404, B:220:0x0431, B:209:0x03cf, B:52:0x00e4, B:201:0x0399, B:203:0x03ac, B:204:0x03ba, B:206:0x03c2, B:47:0x00d1, B:49:0x00dd), top: B:246:0x0012, inners: #1, #9 }] */
        /* JADX WARN: Removed duplicated region for block: B:238:0x0491 A[Catch: Exception -> 0x04a0, TryCatch #0 {Exception -> 0x04a0, blocks: (B:3:0x0012, B:6:0x0019, B:9:0x0022, B:11:0x0026, B:13:0x002c, B:15:0x003a, B:18:0x0049, B:20:0x004f, B:22:0x005d, B:25:0x006c, B:27:0x0072, B:29:0x007c, B:31:0x0083, B:33:0x008f, B:35:0x0095, B:38:0x00a0, B:40:0x00a8, B:42:0x00ae, B:45:0x00b9, B:53:0x00e7, B:55:0x0102, B:58:0x010d, B:61:0x0116, B:63:0x0130, B:65:0x0138, B:67:0x013d, B:69:0x0145, B:71:0x015a, B:73:0x0162, B:85:0x0186, B:88:0x0198, B:228:0x045c, B:230:0x0462, B:232:0x046a, B:233:0x0471, B:235:0x0479, B:236:0x047c, B:238:0x0491, B:239:0x049a, B:93:0x01a9, B:95:0x01b1, B:97:0x01bb, B:99:0x01c3, B:103:0x01d8, B:100:0x01ca, B:102:0x01d2, B:107:0x01ea, B:128:0x0265, B:129:0x026a, B:131:0x0270, B:133:0x027a, B:135:0x0283, B:137:0x0289, B:134:0x027f, B:141:0x0294, B:144:0x029c, B:154:0x02c5, B:156:0x02cb, B:158:0x02d7, B:161:0x02df, B:163:0x02e9, B:164:0x02ee, B:166:0x02f6, B:168:0x02fc, B:170:0x0308, B:172:0x030e, B:174:0x031a, B:176:0x0320, B:178:0x032c, B:181:0x0334, B:183:0x0342, B:190:0x0361, B:184:0x0347, B:187:0x034f, B:189:0x035d, B:191:0x0367, B:193:0x036d, B:195:0x037d, B:197:0x0383, B:199:0x0393, B:211:0x03d5, B:213:0x03db, B:219:0x042b, B:215:0x03fe, B:217:0x0404, B:220:0x0431, B:209:0x03cf, B:52:0x00e4, B:201:0x0399, B:203:0x03ac, B:204:0x03ba, B:206:0x03c2, B:47:0x00d1, B:49:0x00dd), top: B:246:0x0012, inners: #1, #9 }] */
        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void process(cn.thinkingdata.android.ThinkingAnalyticsSDK r18) {
            /*
                Method dump skipped, instruction units count: 1213
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.ThinkingDataRuntimeBridge.d.process(cn.thinkingdata.android.ThinkingAnalyticsSDK):void");
        }
    }

    class e implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ Context a;
        final /* synthetic */ View b;
        final /* synthetic */ View c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        e(Context context, View view, View view2, int i, int i2) {
            this.a = context;
            this.b = view;
            this.c = view2;
            this.d = i;
            this.e = i2;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            Object obj;
            try {
                if (thinkingAnalyticsSDK.isAutoTrackEnabled() && !thinkingAnalyticsSDK.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK)) {
                    Activity activityA = r.a(this.a);
                    if ((activityA != null && thinkingAnalyticsSDK.isActivityAutoTrackAppClickIgnored(activityA.getClass())) || ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, ExpandableListView.class) || ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, this.b) || ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, this.c)) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    r.a(activityA, this.c, jSONObject);
                    if (activityA != null && !TDPresetProperties.disableList.contains("#screen_name")) {
                        jSONObject.put("#screen_name", activityA.getClass().getCanonicalName());
                        String strA = r.a(activityA);
                        if (!TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#title")) {
                            jSONObject.put("#title", strA);
                        }
                    }
                    String strA2 = r.a(this.b);
                    if (!TextUtils.isEmpty(strA2) && !TDPresetProperties.disableList.contains("#element_id")) {
                        jSONObject.put("#element_id", strA2);
                    }
                    if (this.d < 0) {
                        if (!TDPresetProperties.disableList.contains("#element_position")) {
                            obj = String.format(Locale.CHINA, "%d", Integer.valueOf(this.e));
                            jSONObject.put("#element_position", obj);
                        }
                    } else if (!TDPresetProperties.disableList.contains("#element_position")) {
                        obj = String.format(Locale.CHINA, "%d:%d", Integer.valueOf(this.e), Integer.valueOf(this.d));
                        jSONObject.put("#element_position", obj);
                    }
                    if (!TDPresetProperties.disableList.contains("#element_type")) {
                        jSONObject.put("#element_type", "ExpandableListView");
                    }
                    String strA3 = null;
                    if (this.c instanceof ViewGroup) {
                        try {
                            strA3 = r.a(new StringBuilder(), (ViewGroup) this.c);
                            if (!TextUtils.isEmpty(strA3)) {
                                strA3 = strA3.substring(0, strA3.length() - 1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (this.c instanceof TextView) {
                        strA3 = (String) ((TextView) this.c).getText();
                    }
                    if (!TextUtils.isEmpty(strA3) && !TDPresetProperties.disableList.contains("#element_content")) {
                        jSONObject.put("#element_content", strA3);
                    }
                    r.a(this.b, jSONObject);
                    JSONObject jSONObject2 = (JSONObject) r.a(thinkingAnalyticsSDK.getToken(), this.c, R.id.thinking_analytics_tag_view_properties);
                    if (jSONObject2 != null) {
                        r.a(jSONObject2, jSONObject, thinkingAnalyticsSDK.mConfig.getDefaultTimeZone());
                    }
                    ExpandableListAdapter expandableListAdapter = ((ExpandableListView) this.b).getExpandableListAdapter();
                    if (expandableListAdapter != null && (expandableListAdapter instanceof ThinkingExpandableListViewItemTrackProperties)) {
                        try {
                            ThinkingExpandableListViewItemTrackProperties thinkingExpandableListViewItemTrackProperties = (ThinkingExpandableListViewItemTrackProperties) expandableListAdapter;
                            JSONObject thinkingGroupItemTrackProperties = this.d < 0 ? thinkingExpandableListViewItemTrackProperties.getThinkingGroupItemTrackProperties(this.e) : thinkingExpandableListViewItemTrackProperties.getThinkingChildItemTrackProperties(this.e, this.d);
                            if (thinkingGroupItemTrackProperties != null && cn.thinkingdata.android.utils.h.a(thinkingGroupItemTrackProperties)) {
                                r.a(thinkingGroupItemTrackProperties, jSONObject, thinkingAnalyticsSDK.mConfig.getDefaultTimeZone());
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                    thinkingAnalyticsSDK.autoTrack("ta_app_click", jSONObject);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                TDLog.i(ThinkingDataRuntimeBridge.TAG, " ExpandableListView.OnChildClickListener.onGroupClick AOP ERROR: " + e3.getMessage());
            }
        }
    }

    class f implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ Dialog a;
        final /* synthetic */ int b;

        f(Dialog dialog, int i) {
            this.a = dialog;
            this.b = i;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            Class<?> cls;
            Object item;
            Object text;
            try {
                if (thinkingAnalyticsSDK.isAutoTrackEnabled() && !thinkingAnalyticsSDK.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK)) {
                    Activity activityA = r.a(this.a.getContext());
                    if (activityA == null) {
                        activityA = this.a.getOwnerActivity();
                    }
                    if ((activityA == null || !thinkingAnalyticsSDK.isActivityAutoTrackAppClickIgnored(activityA.getClass())) && !ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, Dialog.class)) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            if (this.a.getWindow() != null) {
                                String str = (String) r.a(thinkingAnalyticsSDK.getToken(), this.a.getWindow().getDecorView(), R.id.thinking_analytics_tag_view_id);
                                if (!TextUtils.isEmpty(str) && !TDPresetProperties.disableList.contains("#element_id")) {
                                    jSONObject.put("#element_id", str);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (activityA != null && !TDPresetProperties.disableList.contains("#screen_name")) {
                            jSONObject.put("#screen_name", activityA.getClass().getCanonicalName());
                            String strA = r.a(activityA);
                            if (!TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#title")) {
                                jSONObject.put("#title", strA);
                            }
                        }
                        if (!TDPresetProperties.disableList.contains("#element_type")) {
                            jSONObject.put("#element_type", "Dialog");
                        }
                        Button button = null;
                        try {
                            cls = Class.forName("android.support.v7.app.AlertDialog)");
                        } catch (Exception unused) {
                            cls = null;
                        }
                        if (cls == null) {
                            try {
                                cls = Class.forName("androidx.appcompat.app.AlertDialog");
                            } catch (Exception unused2) {
                            }
                        }
                        if (this.a instanceof AlertDialog) {
                            AlertDialog alertDialog = (AlertDialog) this.a;
                            Button button2 = alertDialog.getButton(this.b);
                            if (button2 == null) {
                                ListView listView = alertDialog.getListView();
                                if (listView != null && (text = listView.getAdapter().getItem(this.b)) != null && (text instanceof String) && !TDPresetProperties.disableList.contains("#element_content")) {
                                    jSONObject.put("#element_content", text);
                                }
                            } else if (!TextUtils.isEmpty(button2.getText()) && !TDPresetProperties.disableList.contains("#element_content")) {
                                text = button2.getText();
                                jSONObject.put("#element_content", text);
                            }
                        } else if (cls != null && cls.isInstance(this.a)) {
                            try {
                                button = (Button) this.a.getClass().getMethod("getButton", Integer.TYPE).invoke(this.a, Integer.valueOf(this.b));
                            } catch (Exception unused3) {
                            }
                            if (button == null) {
                                try {
                                    ListView listView2 = (ListView) this.a.getClass().getMethod("getListView", new Class[0]).invoke(this.a, new Object[0]);
                                    if (listView2 != null && (item = listView2.getAdapter().getItem(this.b)) != null && (item instanceof String) && !TDPresetProperties.disableList.contains("#element_content")) {
                                        jSONObject.put("#element_content", item);
                                    }
                                } catch (Exception unused4) {
                                }
                            } else if (!TextUtils.isEmpty(button.getText()) && !TDPresetProperties.disableList.contains("#element_content")) {
                                text = button.getText();
                                jSONObject.put("#element_content", text);
                            }
                        }
                        thinkingAnalyticsSDK.autoTrack("ta_app_click", jSONObject);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                TDLog.i(ThinkingDataRuntimeBridge.TAG, " DialogInterface.OnClickListener.onClick AOP ERROR: " + e2.getMessage());
            }
        }
    }

    class g implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ View a;
        final /* synthetic */ View b;
        final /* synthetic */ int c;

        g(View view, View view2, int i) {
            this.a = view;
            this.b = view2;
            this.c = i;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            Context context;
            try {
                if (!thinkingAnalyticsSDK.isAutoTrackEnabled() || thinkingAnalyticsSDK.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK) || (context = this.a.getContext()) == null) {
                    return;
                }
                Activity activityA = r.a(context);
                if ((activityA == null || !thinkingAnalyticsSDK.isActivityAutoTrackAppClickIgnored(activityA.getClass())) && !ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, this.b.getClass())) {
                    JSONObject jSONObject = new JSONObject();
                    if (thinkingAnalyticsSDK.getIgnoredViewTypeList() != null) {
                        if ((this.b instanceof ListView) && !TDPresetProperties.disableList.contains("#element_type")) {
                            jSONObject.put("#element_type", "ListView");
                            if (ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, ListView.class)) {
                                return;
                            }
                        } else if ((this.b instanceof GridView) && !TDPresetProperties.disableList.contains("#element_type")) {
                            jSONObject.put("#element_type", "GridView");
                            if (ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, GridView.class)) {
                                return;
                            }
                        } else if ((this.b instanceof Spinner) && !TDPresetProperties.disableList.contains("#element_type")) {
                            jSONObject.put("#element_type", "Spinner");
                            if (ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, Spinner.class)) {
                                return;
                            }
                        }
                    }
                    Adapter adapter = ((AdapterView) this.b).getAdapter();
                    if (adapter instanceof ThinkingAdapterViewItemTrackProperties) {
                        try {
                            JSONObject thinkingItemTrackProperties = ((ThinkingAdapterViewItemTrackProperties) adapter).getThinkingItemTrackProperties(this.c);
                            if (thinkingItemTrackProperties != null && cn.thinkingdata.android.utils.h.a(thinkingItemTrackProperties)) {
                                r.a(thinkingItemTrackProperties, jSONObject, thinkingAnalyticsSDK.mConfig.getDefaultTimeZone());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    r.a(activityA, this.a, jSONObject);
                    String strA = r.a(this.b, thinkingAnalyticsSDK.getToken());
                    if (!TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#element_id")) {
                        jSONObject.put("#element_id", strA);
                    }
                    if (activityA != null && !TDPresetProperties.disableList.contains("#screen_name")) {
                        jSONObject.put("#screen_name", activityA.getClass().getCanonicalName());
                        String strA2 = r.a(activityA);
                        if (!TextUtils.isEmpty(strA2) && !TDPresetProperties.disableList.contains("#title")) {
                            jSONObject.put("#title", strA2);
                        }
                    }
                    if (!TDPresetProperties.disableList.contains("#element_position")) {
                        jSONObject.put("#element_position", String.valueOf(this.c));
                    }
                    String strA3 = null;
                    if (this.a instanceof ViewGroup) {
                        try {
                            strA3 = r.a(new StringBuilder(), (ViewGroup) this.a);
                            if (!TextUtils.isEmpty(strA3)) {
                                strA3 = strA3.substring(0, strA3.length() - 1);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else if (this.a instanceof TextView) {
                        strA3 = ((TextView) this.a).getText().toString();
                    }
                    if (!TextUtils.isEmpty(strA3) && !TDPresetProperties.disableList.contains("#element_content")) {
                        jSONObject.put("#element_content", strA3);
                    }
                    r.a(this.b, jSONObject);
                    JSONObject jSONObject2 = (JSONObject) r.a(thinkingAnalyticsSDK.getToken(), this.a, R.id.thinking_analytics_tag_view_properties);
                    if (jSONObject2 != null) {
                        r.a(jSONObject2, jSONObject, thinkingAnalyticsSDK.mConfig.getDefaultTimeZone());
                    }
                    thinkingAnalyticsSDK.autoTrack("ta_app_click", jSONObject);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                TDLog.i(ThinkingDataRuntimeBridge.TAG, " AdapterView.OnItemClickListener.onItemClick AOP ERROR: " + e3.getMessage());
            }
        }
    }

    class h implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ Object a;
        final /* synthetic */ MenuItem b;

        h(Object obj, MenuItem menuItem) {
            this.a = obj;
            this.b = menuItem;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            try {
                if (!thinkingAnalyticsSDK.isAutoTrackEnabled() || thinkingAnalyticsSDK.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK) || ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, MenuItem.class) || this.a == null) {
                    return;
                }
                String resourceEntryName = null;
                Context context = this.a instanceof Context ? (Context) this.a : null;
                if (context == null) {
                    return;
                }
                Activity activityA = r.a(context);
                if (activityA == null || !thinkingAnalyticsSDK.isActivityAutoTrackAppClickIgnored(activityA.getClass())) {
                    try {
                        resourceEntryName = context.getResources().getResourceEntryName(this.b.getItemId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JSONObject jSONObject = new JSONObject();
                    if (activityA != null && !TDPresetProperties.disableList.contains("#screen_name")) {
                        jSONObject.put("#screen_name", activityA.getClass().getCanonicalName());
                        String strA = r.a(activityA);
                        if (!TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#title")) {
                            jSONObject.put("#title", strA);
                        }
                    }
                    if (!TextUtils.isEmpty(resourceEntryName) && !TDPresetProperties.disableList.contains("#element_id")) {
                        jSONObject.put("#element_id", resourceEntryName);
                    }
                    if (!TextUtils.isEmpty(this.b.getTitle()) && !TDPresetProperties.disableList.contains("#element_content")) {
                        jSONObject.put("#element_content", this.b.getTitle());
                    }
                    if (!TDPresetProperties.disableList.contains("#element_type")) {
                        jSONObject.put("#element_type", "MenuItem");
                    }
                    thinkingAnalyticsSDK.autoTrack("ta_app_click", jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                TDLog.i(ThinkingDataRuntimeBridge.TAG, "track MenuItem click error: " + e2.getMessage());
            }
        }
    }

    class i implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ String a;

        i(String str) {
            this.a = str;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            try {
                if (!thinkingAnalyticsSDK.isAutoTrackEnabled() || thinkingAnalyticsSDK.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK) || ThinkingDataRuntimeBridge.isViewIgnored(thinkingAnalyticsSDK, TabHost.class)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                if (!TDPresetProperties.disableList.contains("#element_content")) {
                    jSONObject.put("#element_content", this.a);
                }
                if (!TDPresetProperties.disableList.contains("#element_type")) {
                    jSONObject.put("#element_type", "TabHost");
                }
                thinkingAnalyticsSDK.autoTrack("ta_app_click", jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                TDLog.i(ThinkingDataRuntimeBridge.TAG, " onTabChanged AOP ERROR: " + e.getMessage());
            }
        }
    }

    private static boolean fragmentGetUserVisibleHint(Object obj) {
        try {
            return ((Boolean) obj.getClass().getMethod("getUserVisibleHint", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean fragmentIsNotHidden(Object obj) {
        try {
            return !((Boolean) obj.getClass().getMethod("isHidden", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }

    private static boolean fragmentIsResumed(Object obj) {
        try {
            return ((Boolean) obj.getClass().getMethod("isResumed", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean isNotFragment(Object obj) {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            cls = Class.forName("android.support.v4.app.Fragment");
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.fragment.app.Fragment");
        } catch (Exception unused2) {
        }
        if (cls == null && cls2 == null) {
            return true;
        }
        if (cls != null) {
            try {
                if (cls.isInstance(obj)) {
                    return false;
                }
            } catch (Exception unused3) {
            }
        }
        if (cls2 != null) {
            if (cls2.isInstance(obj)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isViewIgnored(ThinkingAnalyticsSDK thinkingAnalyticsSDK, View view) {
        if (view == null) {
            return true;
        }
        try {
            List<Class> ignoredViewTypeList = thinkingAnalyticsSDK.getIgnoredViewTypeList();
            if (ignoredViewTypeList != null) {
                Iterator<Class> it = ignoredViewTypeList.iterator();
                while (it.hasNext()) {
                    if (it.next().isAssignableFrom(view.getClass())) {
                        return true;
                    }
                }
            }
            return "1".equals(r.a(thinkingAnalyticsSDK.getToken(), view, R.id.thinking_analytics_tag_view_ignored));
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isViewIgnored(ThinkingAnalyticsSDK thinkingAnalyticsSDK, Class cls) {
        if (cls == null) {
            return true;
        }
        try {
            List<Class> ignoredViewTypeList = thinkingAnalyticsSDK.getIgnoredViewTypeList();
            if (ignoredViewTypeList == null) {
                return false;
            }
            Iterator<Class> it = ignoredViewTypeList.iterator();
            while (it.hasNext()) {
                if (it.next().isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static void onAdapterViewItemClick(View view, View view2, int i2) {
        if (view == null || view2 == null || !(view instanceof AdapterView)) {
            return;
        }
        ThinkingAnalyticsSDK.allInstances(new g(view2, view, i2));
    }

    public static void onDialogClick(Object obj, int i2) {
        if (obj instanceof Dialog) {
            ThinkingAnalyticsSDK.allInstances(new f((Dialog) obj, i2));
        }
    }

    public static void onExpandableListViewOnChildClick(View view, View view2, int i2, int i3) {
        Context context;
        if (view == null || (context = view.getContext()) == null) {
            return;
        }
        ThinkingAnalyticsSDK.allInstances(new e(context, view, view2, i3, i2));
    }

    public static void onExpandableListViewOnGroupClick(View view, View view2, int i2) {
        onExpandableListViewOnChildClick(view, view2, i2, -1);
    }

    public static void onFragmentCreateView(Object obj, View view) {
        try {
            if (isNotFragment(obj)) {
                return;
            }
            String name = obj.getClass().getName();
            view.setTag(R.id.thinking_analytics_tag_view_fragment_name, name);
            if (view instanceof ViewGroup) {
                traverseView(name, (ViewGroup) view);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void onFragmentHiddenChanged(Object obj, boolean z) {
        if (isNotFragment(obj)) {
            return;
        }
        Object objInvoke = null;
        try {
            objInvoke = obj.getClass().getMethod("getParentFragment", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
        if (z) {
            return;
        }
        if ((objInvoke == null && fragmentIsResumed(obj) && fragmentIsNotHidden(obj)) || (fragmentIsResumed(obj) && fragmentIsNotHidden(obj) && fragmentGetUserVisibleHint(obj))) {
            trackFragmentViewScreen(obj);
        }
    }

    public static void onFragmentOnResume(Object obj) {
        if (isNotFragment(obj)) {
            return;
        }
        Object objInvoke = null;
        try {
            objInvoke = obj.getClass().getMethod("getParentFragment", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
        if (objInvoke == null) {
            if (!fragmentIsNotHidden(obj) || !fragmentGetUserVisibleHint(obj)) {
                return;
            }
        } else if (!fragmentIsNotHidden(obj) || !fragmentGetUserVisibleHint(obj) || !fragmentIsNotHidden(objInvoke) || !fragmentGetUserVisibleHint(objInvoke)) {
            return;
        }
        trackFragmentViewScreen(obj);
    }

    public static void onFragmentSetUserVisibleHint(Object obj, boolean z) {
        if (isNotFragment(obj)) {
            return;
        }
        Object objInvoke = null;
        try {
            objInvoke = obj.getClass().getMethod("getParentFragment", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
        if (z) {
            if ((objInvoke == null && fragmentIsResumed(obj) && fragmentIsNotHidden(obj)) || (fragmentIsResumed(obj) && fragmentIsNotHidden(obj) && fragmentGetUserVisibleHint(obj))) {
                trackFragmentViewScreen(obj);
            }
        }
    }

    public static void onMenuItemSelected(Object obj, MenuItem menuItem) {
        if (menuItem == null) {
            return;
        }
        ThinkingAnalyticsSDK.allInstances(new h(obj, menuItem));
    }

    public static void onTabHostChanged(String str) {
        ThinkingAnalyticsSDK.allInstances(new i(str));
    }

    public static void onViewOnClick(View view, Object obj) {
        if (view == null) {
            return;
        }
        ThinkingAnalyticsSDK.allInstances(new d(obj, view));
    }

    public static void trackEvent(Object obj) {
        if (obj instanceof ThinkingDataTrackEvent) {
            ThinkingDataTrackEvent thinkingDataTrackEvent = (ThinkingDataTrackEvent) obj;
            String strEventName = thinkingDataTrackEvent.eventName();
            String strProperties = thinkingDataTrackEvent.properties();
            String strAppId = thinkingDataTrackEvent.appId();
            if (TextUtils.isEmpty(strEventName)) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(strProperties)) {
                try {
                    r.a(new JSONObject(strProperties), jSONObject, (TimeZone) null);
                } catch (JSONException e2) {
                    TDLog.e(TAG, "Exception occurred in trackEvent");
                    e2.printStackTrace();
                }
            }
            ThinkingAnalyticsSDK.allInstances(new b(strAppId, strEventName, jSONObject));
        }
    }

    public static void trackEvent(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str2)) {
            try {
                r.a(new JSONObject(str2), jSONObject, (TimeZone) null);
            } catch (JSONException e2) {
                TDLog.e(TAG, "Exception occurred in trackEvent");
                e2.printStackTrace();
            }
        }
        ThinkingAnalyticsSDK.allInstances(new c(str3, str, jSONObject));
    }

    private static void trackFragmentViewScreen(Object obj) {
        ThinkingAnalyticsSDK.allInstances(new a(obj));
    }

    private static void traverseView(String str, ViewGroup viewGroup) {
        try {
            if (TextUtils.isEmpty(str) || viewGroup == null) {
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                childAt.setTag(R.id.thinking_analytics_tag_view_fragment_name, str);
                if (childAt instanceof ViewGroup) {
                    traverseView(str, (ViewGroup) childAt);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
