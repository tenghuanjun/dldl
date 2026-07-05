package layaair.game.browser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import layaair.game.PlatformInterface.LayaPlatformGlue;
import layaair.game.conch.LayaConch5;
import layaair.game.device.DevID;
import layaair.game.network.NetworkReceiver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ExportJavaFunction {
    public static int m_nState = 0;
    public static String m_sHref = "";
    private static ExportJavaFunction ms_pExportJavaFunction;
    public static HashMap id2objMap = new HashMap();
    public static HashMap obj2idMap = new HashMap();
    public static Class[] strClass = {String.class};
    private Paint m_Paint = new Paint();
    private String m_sAlertMsg = "";
    private int m_nAlertCallbackType = 0;
    private boolean m_bShowLoading = false;
    private boolean m_bScreenWakeLock = false;
    private boolean m_bShowWating = false;
    public Handler m_Handler = new Handler();
    public LayaConch5 m_pEngine = null;
    public boolean m_bShowAssistantTouch = false;

    public static void CallBackToJS(Object obj, String str, Object obj2) {
        String name;
        JSONObject jSONObject = new JSONObject();
        int iIntValue = -1;
        if (obj instanceof Class) {
            name = ((Class) obj).getName();
        } else if (obj instanceof String) {
            name = (String) obj;
        } else {
            Integer num = (Integer) obj2idMap.get(obj);
            if (num == null) {
                Log.e("LayaBox", "该对象不是通过脚本生成的");
                return;
            } else {
                iIntValue = num.intValue();
                name = null;
            }
        }
        try {
            jSONObject.put("objId", iIntValue);
            jSONObject.put("cName", name);
            jSONObject.put("mName", str);
            jSONObject.put("v", obj2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ConchJNI.RunJS("conch.platCallBack(" + jSONObject.toString() + ")");
    }

    public static void DelInstance() {
        Log.e("", "DELETE ExportJavaFunction instance!");
        m_nState = 2;
        ms_pExportJavaFunction = null;
        obj2idMap.clear();
        id2objMap.clear();
    }

    public static String GetDeviceInfo() {
        DevID devID;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (devID = exportJavaFunctionGetInstance.m_pEngine.getDevID()) == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("unknown");
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put("unknown");
        try {
            int[] iArrGetResolutionArray = devID.GetResolutionArray();
            jSONObject.put("dpi", iArrGetResolutionArray[2]);
            jSONObject.put("resolution", iArrGetResolutionArray[1] + "*" + iArrGetResolutionArray[0]);
            jSONObject.put("guid", "unknown");
            jSONObject.put("imei", jSONArray);
            jSONObject.put("imsi", jSONArray2);
            jSONObject.put("os", "android");
            jSONObject.put("osversion", devID.GetOSVersion());
            jSONObject.put("phonemodel", devID.GetPhoneModelAndSDK());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static ExportJavaFunction GetInstance() {
        if (ms_pExportJavaFunction == null && m_nState != 2) {
            ms_pExportJavaFunction = new ExportJavaFunction();
            m_nState = 1;
        }
        return ms_pExportJavaFunction;
    }

    public static void ShowMessage(String str) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance._showMessage(str);
        }
    }

    private static Object _callMethod(Class[] clsArr, Object[] objArr, String str, Object obj, Class cls) {
        if (cls == null) {
            Log.e("", ">>>>>>>>>>>>>>>>>>>>null");
            return "";
        }
        try {
            try {
                return cls.getMethod(str, clsArr).invoke(obj, objArr);
            } catch (Exception e) {
                return e.toString();
            }
        } catch (NoSuchMethodException e2) {
            return e2.toString();
        }
    }

    public static void alert(String str) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_sAlertMsg = str;
            exportJavaFunctionGetInstance.m_nAlertCallbackType = 0;
            exportJavaFunctionGetInstance.JSAlert();
        }
    }

    public static void alertCallback(String str) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_sAlertMsg = str;
            exportJavaFunctionGetInstance.m_nAlertCallbackType = 1;
            exportJavaFunctionGetInstance.JSAlert();
        }
    }

    public static void alertExit(String str) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_sAlertMsg = str;
            exportJavaFunctionGetInstance.m_nAlertCallbackType = 2;
            exportJavaFunctionGetInstance.JSAlert();
        }
    }

    public static void authorize(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.authorize(str);
        }
    }

    public static void buyProps(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.buyProps(str);
        }
    }

    public static String callMethod(int i, boolean z, String str, String str2, String str3) {
        JSONArray jSONArray;
        Object[] objArr;
        String strReplaceAll = str.replaceAll("/", ".");
        Object string = null;
        if (str3 == null) {
            objArr = new Object[0];
        } else {
            try {
                jSONArray = new JSONArray(str3);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONArray = null;
            }
            int length = jSONArray != null ? jSONArray.length() : 0;
            Object[] objArr2 = new Object[length];
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    objArr2[i2] = jSONArray.get(i2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            objArr = objArr2;
        }
        Class[] clsArr = new Class[objArr.length];
        int i3 = 0;
        for (Object obj : objArr) {
            Class<?> cls = obj.getClass();
            if (cls == Integer.class) {
                cls = Integer.TYPE;
            } else if (cls == Float.class) {
                cls = Float.TYPE;
            } else if (cls == Double.class) {
                cls = Float.TYPE;
                objArr[i3] = Float.valueOf(Float.parseFloat(objArr[i3].toString()));
            } else if (cls == Boolean.class) {
                cls = Boolean.TYPE;
            }
            clsArr[i3] = cls;
            i3++;
        }
        Object obj2 = id2objMap.get(Integer.valueOf(i));
        try {
            Class<?> cls2 = obj2 != null ? obj2.getClass() : Class.forName(strReplaceAll);
            if ("<init>".equals(str2)) {
                newObj(clsArr, objArr, i, cls2);
            } else {
                string = _callMethod(clsArr, objArr, str2, obj2, cls2);
            }
        } catch (ClassNotFoundException e3) {
            string = e3.toString();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("v", string);
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String callMethod(String str, String str2, String str3) {
        Object objInvoke;
        try {
            try {
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            objInvoke = Class.forName(str.replaceAll("/", ".")).getMethod(str2, str3 != null ? strClass : new Class[0]).invoke(null, str3 != null ? new Object[]{str3} : new Object[0]);
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            objInvoke = null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            objInvoke = null;
        }
        if (objInvoke != null) {
            return objInvoke.toString();
        }
        return null;
    }

    public static void callWebViewJS(String str, String str2, String str3) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.callWebViewJSFunction(str, str2, str3);
        }
    }

    private void callWebViewJSFunction(String str, String str2, String str3) {
        this.m_Handler.post(new n(this, str, str2, str3));
    }

    public static int canSendToDesktop(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            return layaPlatformGlueGetInstance.canSendToDesktop(str);
        }
        return 0;
    }

    public static void captureScreen() {
        LayaConch5.GetInstance().getCanvas().a();
    }

    public static void closeExternalLink() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.closeWebView();
        }
    }

    private void closeWebView() {
        this.m_Handler.post(new k(this));
    }

    private void doExit() {
        this.m_Handler.post(new m(this));
    }

    public static void enterAccountMgr(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.enterAccountMgr(str);
        }
    }

    public static void enterBBS(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.enterBBS(str);
        }
    }

    public static void enterFeedback(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.enterFeedback(str);
        }
    }

    public static void enterInvite(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.invite(str);
        }
    }

    public static void enterPlatform(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.enterPlatform(str);
        }
    }

    public static void enterShareAndFeed(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.shareAndFeed(str);
        }
    }

    public static void exit() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.doExit();
        }
    }

    public static String getAndroidModel() {
        DevID devID;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        return (exportJavaFunctionGetInstance == null || (devID = exportJavaFunctionGetInstance.m_pEngine.getDevID()) == null) ? "" : devID.GetPhoneModelAndSDK();
    }

    public static void getAvailableLoginType(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.getAvailableLoginType(str);
        }
    }

    public static int getChargeType() {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            return layaPlatformGlueGetInstance.getChargeType();
        }
        return 0;
    }

    public static int getContextedType() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null) {
            return 5;
        }
        exportJavaFunctionGetInstance.m_pEngine.getNetworkReceiver();
        return NetworkReceiver.a(exportJavaFunctionGetInstance.m_pEngine.getGameContext());
    }

    public static String getEditBoxValue() {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        return (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) ? "" : editBox$6b0f844e.f();
    }

    public static int getEnterPlatformType() {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            return layaPlatformGlueGetInstance.getEnterPlatformType();
        }
        return 0;
    }

    public static void getGameFriends(String str) {
        Log.i("0", ">>>>>>>getGameFriends =" + str);
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.getGameFriends(str);
        }
    }

    public static int getLoginType() {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            return layaPlatformGlueGetInstance.getLoginType();
        }
        return 0;
    }

    public static String getMarketName() {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            return layaPlatformGlueGetInstance.getMarketName();
        }
        return null;
    }

    public static String getMarketValue(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        return layaPlatformGlueGetInstance != null ? layaPlatformGlueGetInstance.getMarketValue(str) : "";
    }

    public static int getPayType() {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            return layaPlatformGlueGetInstance.getPayType();
        }
        return 0;
    }

    public static float getScreenInch() {
        return 4.0f;
    }

    public static int getScreenOrientation() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || exportJavaFunctionGetInstance.m_pEngine.getIsPlug()) {
            return 0;
        }
        return ((Activity) exportJavaFunctionGetInstance.m_pEngine.mCtx).getRequestedOrientation();
    }

    public static int[] getTextBuffer(String str, String str2, int i, int i2, int i3, boolean z, int i4, int i5) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            return exportJavaFunctionGetInstance.getTextPixelBuffer(str, str2, i, i2, i3, z, i4, i5);
        }
        return null;
    }

    public static int getTextWidth(String str, String str2, int i, int i2, boolean z) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            return exportJavaFunctionGetInstance._getTextWidth(str, str2, i, i2, z);
        }
        return 1;
    }

    public static void getUserInfo(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.getUserInfo(str);
        }
    }

    public static String getWifiMac() {
        return GetInstance() == null ? "" : "";
    }

    public static void hideExternalLink() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.hideWebView();
        }
    }

    private void hideWebView() {
        this.m_Handler.post(new j(this));
    }

    public static void login(String str) {
        Log.e("", ">>>>>>>>>>>>>>> login param = " + str);
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.login(str);
        }
    }

    public static void logout(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.logout(str);
        }
    }

    private static void newObj(Class[] clsArr, Object[] objArr, int i, Class cls) {
        try {
            try {
                try {
                    Object objNewInstance = cls.getConstructor(clsArr).newInstance(objArr);
                    id2objMap.put(Integer.valueOf(i), objNewInstance);
                    obj2idMap.put(objNewInstance, Integer.valueOf(i));
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        }
    }

    public static void openTopicCircle(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.openTopicCircle(str);
        }
    }

    public static void postCmdToMain(int i, int i2, int i3) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance._postCmdToMain(i, i2, i3);
        }
    }

    public static void recharge(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.recharge(str);
        }
    }

    public static void refreshToken(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            Log.i("0", ">>>>>>>refreshToken =" + str);
            layaPlatformGlueGetInstance.refreshToken(str);
        }
    }

    public static void reloadApp() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance._reloadApp();
        }
    }

    public static void sendMessageToPlatform(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.sendMessageToPlatform(str);
        }
    }

    public static void sendToDesktop(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.sendToDesktop(str);
        }
    }

    public static void setEditBoxBlur() {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.f = false;
        editBox$6b0f844e.e(false);
    }

    public static void setEditBoxColor(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.e(i);
    }

    public static void setEditBoxCursorPosition(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.h(i);
    }

    public static void setEditBoxFocus() {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.h(editBox$6b0f844e.a.getText().length());
    }

    public static void setEditBoxFontSize(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.f(i);
    }

    public static void setEditBoxHeight(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.d(i);
    }

    public static void setEditBoxMaxLength(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.g(i);
    }

    public static void setEditBoxNumberOnly(boolean z) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.c(z);
    }

    public static void setEditBoxPassword(boolean z) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.d(z);
    }

    public static void setEditBoxPos(int i, int i2) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.a(i, i2);
    }

    public static void setEditBoxPosX(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.a(i);
    }

    public static void setEditBoxPosY(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.b(i);
    }

    public static void setEditBoxRegular(String str) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.a(str);
    }

    public static void setEditBoxSize(int i, int i2) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.b(i, i2);
    }

    public static void setEditBoxStyle(String str) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.d(str);
    }

    public static void setEditBoxValue(String str) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.c(str);
    }

    public static void setEditBoxVisible(boolean z) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.e(z);
    }

    public static void setEditBoxWidth(int i) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.c(i);
    }

    public static void setExternalLink(String str, int i, int i2, int i3, int i4, int i5) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.showExternalWeb(str, i, i2, i3, i4, i5);
        }
    }

    public static void setForbidEdit(boolean z) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.b(z);
    }

    public static void setHrefToJava(String str) {
        m_sHref = str;
    }

    public static void setMarketValue(String str, String str2) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.setMarketValue(str, str2);
        }
    }

    public static void setMultiAble(boolean z) {
        b editBox$6b0f844e;
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null || (editBox$6b0f844e = exportJavaFunctionGetInstance.m_pEngine.getEditBox$6b0f844e()) == null) {
            return;
        }
        editBox$6b0f844e.a(z);
    }

    public static void setRechargeInfo(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.setRechargeInfo(str);
        }
    }

    public static void setResolution(int i, int i2) {
        Log.i("fix", "js setRes " + i + com.igexin.push.core.b.aj + i2);
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance._setResolution(i, i2);
        }
    }

    public static void setScreenOrientation(int i) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null) {
            return;
        }
        if (!exportJavaFunctionGetInstance.m_pEngine.getIsPlug()) {
            ((Activity) exportJavaFunctionGetInstance.m_pEngine.mCtx).setRequestedOrientation(i);
        }
        Log.i("0", ">>>>>>ExportJavaFunction setScreenOrientation=" + i);
        boolean z = false;
        switch (i) {
            case 0:
            case 6:
            case 8:
                z = true;
                break;
        }
        exportJavaFunctionGetInstance.m_pEngine.m_bHorizontalScreen = z;
    }

    public static void setScreenWakeLock(boolean z) {
        LayaConch5.GetInstance().setRuntimeExt();
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_bScreenWakeLock = z;
            exportJavaFunctionGetInstance.setScreenWakeLock();
        }
    }

    public static void setSensorAble(boolean z) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null) {
            return;
        }
        exportJavaFunctionGetInstance.m_pEngine.setSensorAble(z);
    }

    public static void setTouchMoveRange(float f) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_pEngine.getCanvas().a.a(f);
        }
    }

    public static void showAssistantTouch(boolean z) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance._showAssistantTouch(z);
        }
    }

    public static void showExternalLink() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.showWebView();
        }
    }

    private void showExternalWeb(String str, int i, int i2, int i3, int i4, int i5) {
        this.m_Handler.post(new e(this, str, i, i2, i3, i4, i5));
    }

    public static void showLoadingView(boolean z) {
        Log.i("LayaBox", "showLoadingview" + z);
    }

    private void showWebView() {
        this.m_Handler.post(new l(this));
    }

    public static void switchUser(String str) {
        LayaPlatformGlue layaPlatformGlueGetInstance = LayaPlatformGlue.GetInstance();
        if (layaPlatformGlueGetInstance != null) {
            layaPlatformGlueGetInstance.switchUser(str);
        }
    }

    public static void useChoreographer(int i) {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance._useChoreographer(i > 0);
        }
    }

    public static void vibrate() {
        ExportJavaFunction exportJavaFunctionGetInstance = GetInstance();
        if (exportJavaFunctionGetInstance == null) {
            return;
        }
        exportJavaFunctionGetInstance._vibrate();
    }

    public void Init(Context context) {
        ConchJNI.exportStaticMethodToC(getClass().getName().replaceAll("\\.", "/"));
    }

    public void JSAlert() {
        this.m_Handler.post(new p(this));
    }

    public int _getTextWidth(String str, String str2, int i, int i2, boolean z) {
        this.m_Paint.setStyle(Paint.Style.FILL);
        this.m_Paint.setTextSize(i);
        this.m_Paint.setAntiAlias(true);
        return (int) this.m_Paint.measureText(str);
    }

    public void _postCmdToMain(int i, int i2, int i3) {
        this.m_Handler.post(new i(this, i, i2, i3));
    }

    public void _reloadApp() {
        this.m_Handler.post(new f(this));
    }

    public void _setResolution(int i, int i2) {
        this.m_Handler.post(new h(this, i, i2));
    }

    public void _showAssistantTouch(boolean z) {
        this.m_Handler.post(new o(this, z));
    }

    public void _showMessage(String str) {
        this.m_Handler.post(new g(this, str));
    }

    public void _useChoreographer(boolean z) {
    }

    public void _vibrate() {
        ((Vibrator) this.m_pEngine.getGameContext().getSystemService("vibrator")).vibrate(new long[]{10, 100, 100, 200}, -1);
    }

    public Bitmap drawTextToBitmap(String str, int i, int i2, Point point, int i3, int i4) {
        int i5 = i3 > 0 ? 1 : 0;
        int i6 = i5 << 1;
        int i7 = i + i6;
        this.m_Paint.setStyle(Paint.Style.FILL);
        this.m_Paint.setTextSize(i);
        this.m_Paint.setAntiAlias(true);
        int iMeasureText = ((int) this.m_Paint.measureText(str)) + i6;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMeasureText, i7, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(0);
        Paint.FontMetricsInt fontMetricsInt = this.m_Paint.getFontMetricsInt();
        int i8 = ((((i - fontMetricsInt.bottom) + fontMetricsInt.top) / 2) + 0) - fontMetricsInt.top;
        if (i5 > 0) {
            this.m_Paint.setColor(i4);
            float f = i8 - 1;
            canvas.drawText(str, 0.0f, f, this.m_Paint);
            canvas.drawText(str, 2.0f, f, this.m_Paint);
            float f2 = i8 + 1;
            canvas.drawText(str, 0.0f, f2, this.m_Paint);
            canvas.drawText(str, 2.0f, f2, this.m_Paint);
        }
        this.m_Paint.setColor(i2);
        canvas.drawText(str, i5, i8, this.m_Paint);
        point.x = iMeasureText;
        point.y = i7;
        return bitmapCreateBitmap;
    }

    public int[] getTextPixelBuffer(String str, String str2, int i, int i2, int i3, boolean z, int i4, int i5) {
        Point point = new Point();
        Bitmap bitmapDrawTextToBitmap = drawTextToBitmap(str, i, i2, point, i4, i5);
        int i6 = (point.x * point.y) + 2;
        int[] iArr = new int[i6];
        int i7 = 0;
        int i8 = 0;
        while (i7 < point.y) {
            int i9 = i8;
            for (int i10 = 0; i10 < point.x; i10++) {
                iArr[i9] = bitmapDrawTextToBitmap.getPixel(i10, i7);
                i9++;
            }
            i7++;
            i8 = i9;
        }
        iArr[i6 - 2] = iArr[0];
        iArr[i6 - 1] = iArr[1];
        iArr[0] = point.x;
        iArr[1] = point.y;
        return iArr;
    }

    public void setScreenWakeLock() {
        this.m_Handler.post(new q(this));
    }
}
