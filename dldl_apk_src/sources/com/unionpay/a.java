package com.unionpay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.utils.UPUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class a implements Handler.Callback {
    a() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        String str = "0";
        switch (message.what) {
            case 1001:
                UPPayAssistEx.j();
                UPPayAssistEx.a(UPPayAssistEx.q(), UPPayAssistEx.ab, UPPayAssistEx.S);
                return true;
            case 1002:
                try {
                    if (message.obj != null) {
                        JSONObject jSONObject = new JSONObject((String) message.obj);
                        String strA = com.unionpay.utils.i.a(jSONObject, "sign");
                        int i = 0;
                        try {
                            i = Integer.parseInt(UPPayAssistEx.O);
                            break;
                        } catch (Exception unused) {
                        }
                        String str2 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                        String str3 = "";
                        String str4 = jSONObject.has("sePayConf") ? new String(Base64.decode(jSONObject.getString("sePayConf"), 2)) : "";
                        if (!TextUtils.isEmpty(str4)) {
                            str3 = str4;
                        }
                        String strB = com.unionpay.utils.b.b(UPUtils.a(str2 + str3 + UPPayAssistEx.L));
                        String strA2 = UPUtils.a(i, strA);
                        if (!TextUtils.isEmpty(strA2) && strA2.equals(strB)) {
                            UPUtils.a(UPPayAssistEx.q(), (String) message.obj, "configs" + UPPayAssistEx.G);
                            UPUtils.a(UPPayAssistEx.q(), UPPayAssistEx.O, "mode" + UPPayAssistEx.G);
                            UPUtils.a(UPPayAssistEx.q(), UPPayAssistEx.L, "or" + UPPayAssistEx.G);
                            if (!TextUtils.isEmpty(UPPayAssistEx.E)) {
                                UPUtils.a(UPPayAssistEx.q(), str3, "se_configs" + UPPayAssistEx.E);
                            }
                            if (!UPPayAssistEx.T) {
                                JSONArray unused2 = UPPayAssistEx.ab = UPPayAssistEx.b(new JSONArray(str2), "sort");
                                UPPayAssistEx.d(str3);
                            }
                        }
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!UPPayAssistEx.T) {
                    UPPayAssistEx.a(UPPayAssistEx.q(), UPPayAssistEx.ab, UPPayAssistEx.S);
                }
                return true;
            case 1003:
                UPPayAssistEx.W.removeMessages(1004);
                try {
                    if (message.obj instanceof Integer) {
                        if (((Integer) message.obj).intValue() == 1) {
                            str = "1";
                        }
                    }
                } catch (Exception unused3) {
                }
                if (!UPPayAssistEx.U) {
                    UPPayAssistEx.c(str);
                }
                return true;
            case 1004:
                com.unionpay.utils.j.c("uppay", "QUERY_CAPACITY_TIME_OUT");
                UPPayAssistEx.m();
                UPPayAssistEx.c(str);
                return true;
            default:
                return true;
        }
    }
}
