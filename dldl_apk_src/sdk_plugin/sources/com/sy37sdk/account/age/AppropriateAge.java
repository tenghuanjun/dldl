package com.sy37sdk.account.age;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppropriateAge {
    public static final int LOCATE_LEFT_BOTTOM = 3;
    public static final int LOCATE_LEFT_TOP = 1;
    public static final int LOCATE_RIGHT_BOTTOM = 4;
    public static final int LOCATE_RIGHT_TOP = 2;
    public static final String TIMING_LOGIN = "1";
    public static final String TIMING_ROLE = "2";
    private String desc;
    private String icon;
    private int location;
    private boolean status;
    private List<String> timing;

    public List<String> getTiming() {
        return this.timing;
    }

    public void setTiming(List<String> list) {
        this.timing = list;
    }

    public int getLocation() {
        return this.location;
    }

    public void setLocation(int i) {
        this.location = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }

    public static String objectToJson(AppropriateAge appropriateAge) {
        if (appropriateAge == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("location", Integer.valueOf(appropriateAge.getLocation()));
            jSONObject.putOpt("desc", appropriateAge.getDesc());
            jSONObject.putOpt("icon", appropriateAge.getIcon());
            jSONObject.putOpt("status", Boolean.valueOf(appropriateAge.isStatus()));
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < appropriateAge.getTiming().size(); i++) {
                jSONArray.put(appropriateAge.getTiming().get(i));
            }
            jSONObject.putOpt("timing", jSONArray);
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static AppropriateAge jsonToObject(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("location");
            String strOptString = jSONObject.optString("desc");
            String strOptString2 = jSONObject.optString("icon");
            boolean zOptBoolean = jSONObject.optBoolean("status");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("timing");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
            AppropriateAge appropriateAge = new AppropriateAge();
            appropriateAge.setDesc(strOptString);
            appropriateAge.setIcon(strOptString2);
            appropriateAge.setStatus(zOptBoolean);
            appropriateAge.setLocation(iOptInt);
            appropriateAge.setTiming(arrayList);
            return appropriateAge;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AppropriateAge parse(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("location");
            String strOptString2 = jSONObject.optString("desc");
            String strOptString3 = jSONObject.optString("icon");
            String strOptString4 = jSONObject.optString("status");
            AppropriateAge appropriateAge = new AppropriateAge();
            appropriateAge.setDesc(strOptString2);
            appropriateAge.setIcon(strOptString3);
            try {
                i = Integer.parseInt(strOptString4);
            } catch (Exception e) {
                e.printStackTrace();
                i = 0;
            }
            int i2 = 1;
            appropriateAge.setStatus(i == 1);
            try {
                i2 = Integer.parseInt(strOptString);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            appropriateAge.setLocation(i2);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("timing");
            ArrayList arrayList = new ArrayList();
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                for (int i3 = 0; i3 < jSONArrayOptJSONArray.length(); i3++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i3));
                }
            }
            appropriateAge.setTiming(arrayList);
            return appropriateAge;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
