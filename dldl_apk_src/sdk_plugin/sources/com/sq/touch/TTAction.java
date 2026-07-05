package com.sq.touch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TTAction {
    private static final int MAX_UPLOAD_NUM = 700;
    private static final String SP_FILE_NAME_TT = "sp_file_name_tt";
    private static volatile TTAction sInstance;
    private Context context;
    private CollectCallback mapUpload;
    private List touchMapList = new ArrayList();
    private IGetEngineInterface ttGetEngine;
    private IGetUserInterface ttGetUid;

    private void a(Context context) {
        if (context instanceof Activity) {
            View decorView = ((Activity) context).getWindow().getDecorView();
            if (decorView instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) decorView;
                View viewFindViewByClass = findViewByClass(viewGroup, "com\\.unity3d\\.player\\.UnityPlayer");
                if (viewFindViewByClass == null) {
                    viewFindViewByClass = findViewByClass(viewGroup, "org\\.cocos2d\\.opengl\\.CCGLSurfaceView");
                }
                if (viewFindViewByClass == null) {
                    viewFindViewByClass = findViewByClass(viewGroup, "org\\.cocos2dx\\.lib\\.Cocos2dxGLSurfaceView");
                }
                if (viewFindViewByClass == null) {
                    viewFindViewByClass = findViewByClass(viewGroup, "layaair\\.game\\.browser\\.\\S+");
                }
                if (viewFindViewByClass == null) {
                    viewFindViewByClass = findViewByClass(viewGroup, "org\\.egret\\.launcher\\.hycq\\.view\\.GameWebView");
                }
                if (viewFindViewByClass == null) {
                    viewFindViewByClass = findViewByClass(viewGroup, "com\\.eyugame\\.mysdk\\.UnityPlayer1");
                }
                if (viewFindViewByClass == null) {
                    viewFindViewByClass = findViewByClass(viewGroup, this.ttGetEngine.getEngine().isEmpty() ? AbstractJsonLexerKt.NULL : this.ttGetEngine.getEngine());
                }
                if (viewFindViewByClass != null) {
                    viewFindViewByClass.setOnTouchListener(new View.OnTouchListener() { // from class: com.sq.touch.-$$Lambda$TTAction$Urm9skhYEkRNYdzjStXyEaj1l_A
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            return this.f$0.a(view, motionEvent);
                        }
                    });
                } else {
                    this.mapUpload.onFail();
                    Log.v("ContentValues", "没有找到所有view");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean a(View view, MotionEvent motionEvent) {
        String str;
        HashMap map = new HashMap();
        map.put("time_stamp", String.valueOf(System.currentTimeMillis()));
        map.put("coordinate_view", motionEvent.getX() + "," + motionEvent.getY());
        map.put("coordinate_screen", motionEvent.getRawX() + "," + motionEvent.getRawY());
        map.put("pressure", String.valueOf(motionEvent.getPressure()));
        map.put("range", String.valueOf(motionEvent.getSize()));
        map.put("machine_time", String.valueOf(motionEvent.getEventTime()));
        map.put("machine_press_time", String.valueOf(motionEvent.getDownTime()));
        int action = motionEvent.getAction();
        if (action == 0) {
            str = "press_event";
        } else {
            if (action != 1) {
                if (action == 2) {
                    str = "move_event";
                }
                this.touchMapList.add(map);
                u();
                return false;
            }
            str = "lift_event";
        }
        map.put("click_event_type", str);
        this.touchMapList.add(map);
        u();
        return false;
    }

    private View findViewByClass(ViewGroup viewGroup, String str) {
        return findViewByClass(viewGroup, str, 0);
    }

    private View findViewByClass(ViewGroup viewGroup, String str, int i) {
        View viewFindViewByClass;
        if (Pattern.matches(str, viewGroup.getClass().getName())) {
            return viewGroup;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < i; i3++) {
                sb.append("\t");
            }
            Log.v("ContentValues", "View " + ((Object) sb) + childAt.getClass() + "@" + childAt.hashCode());
            if (Pattern.matches(str, childAt.getClass().getName())) {
                return childAt;
            }
            if ((childAt instanceof ViewGroup) && (viewFindViewByClass = findViewByClass((ViewGroup) childAt, str, i + 1)) != null) {
                return viewFindViewByClass;
            }
        }
        return null;
    }

    private boolean getBooleanValue(Context context, String str) {
        return getSharePreferences(context).getBoolean(str, false);
    }

    public static TTAction getInstance() {
        if (sInstance == null) {
            synchronized (TTAction.class) {
                if (sInstance == null) {
                    sInstance = new TTAction();
                }
            }
        }
        return sInstance;
    }

    private int getIntValue(Context context, String str) {
        return getSharePreferences(context).getInt(str, 0);
    }

    private SharedPreferences getSharePreferences(Context context) {
        return context.getSharedPreferences(SP_FILE_NAME_TT, 0);
    }

    private String getStrValue(Context context, String str) {
        return getSharePreferences(context).getString(str, "");
    }

    private JSONArray mapListToJson() {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.touchMapList.size(); i++) {
            Map map = (Map) this.touchMapList.get(i);
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : map.entrySet()) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private void putBooleanValue(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = getSharePreferences(context).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    private void putIntValue(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = getSharePreferences(context).edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    private void putStringValue(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = getSharePreferences(context).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    private void resetUploadNum() {
        int i = Calendar.getInstance().get(6);
        if (i != getIntValue(this.context, "last_day")) {
            putIntValue(this.context, "upload_num" + this.ttGetUid.getUid(), 0);
            putIntValue(this.context, "last_day", i);
        }
    }

    private void u() {
        String uid = this.ttGetUid.getUid();
        if (getIntValue(this.context, "upload_num" + uid) > MAX_UPLOAD_NUM) {
            this.touchMapList.clear();
            return;
        }
        int intValue = getIntValue(this.context, "upload_num" + uid);
        if (this.touchMapList.size() > 6) {
            String string = mapListToJson().toString();
            HashMap map = new HashMap();
            map.put("TouchMapList", string);
            map.put("userId", uid);
            this.mapUpload.onSuccess(map);
            putIntValue(this.context, "upload_num" + uid, intValue + 1);
            this.touchMapList.clear();
        }
    }

    public void initCollect(Context context, CollectCallback collectCallback) {
        this.context = context;
        this.mapUpload = collectCallback;
        resetUploadNum();
        a(this.context);
    }

    public void setGetEngineInterface(IGetEngineInterface iGetEngineInterface) {
        this.ttGetEngine = iGetEngineInterface;
    }

    public void setGetUserInterface(IGetUserInterface iGetUserInterface) {
        this.ttGetUid = iGetUserInterface;
    }
}
