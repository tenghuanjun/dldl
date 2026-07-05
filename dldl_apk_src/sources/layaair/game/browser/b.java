package layaair.game.browser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.google.android.material.badge.BadgeDrawable;
import java.util.regex.Pattern;
import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    public EditText a;
    public String e;
    private Context g;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    public boolean b = false;
    private AbsoluteLayout.LayoutParams l = null;
    public float c = 1.0f;
    public float d = 1.0f;
    private boolean m = false;
    private boolean n = false;
    private int o = 16777215;
    private int p = 24;
    private boolean q = false;
    public boolean f = false;
    private int r = 0;
    private ak s = null;
    private String t = null;
    private String u = null;
    private Handler v = new Handler();

    public b(Context context) {
        this.a = null;
        this.g = null;
        this.g = context;
        this.a = null;
    }

    @SuppressLint({"NewApi"})
    private static void a(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        b();
    }

    @SuppressLint({"NewApi"})
    public static void b() {
        if (Build.VERSION.SDK_INT > 18) {
            int systemUiVisibility = ((Activity) LayaConch5.GetInstance().mCtx).getWindow().getDecorView().getSystemUiVisibility();
            Log.i("LayaBox", ">>>>>>>>>>>>>>>>>>>a:" + systemUiVisibility);
            if ((systemUiVisibility & 4096) == 4096) {
                LayaConch5.GetInstance().m_pAbsLayout.setSystemUiVisibility(2);
            }
        }
    }

    public void a() {
        this.g = null;
        ak akVar = this.s;
        if (akVar != null) {
            akVar.a = null;
            this.s = null;
        }
        EditText editText = this.a;
        if (editText != null) {
            editText.destroyDrawingCache();
            this.a = null;
        }
    }

    public void a(int i) {
        this.h = (int) (i * this.c);
        this.v.post(new ag(this));
    }

    public void a(int i, int i2) {
        this.h = (int) (i * this.c);
        this.i = (int) (i2 * this.d);
        this.v.post(new af(this));
    }

    public void a(String str) {
        this.t = str;
    }

    public void a(String str, String str2) {
        try {
            if (str.equals("left")) {
                if (str2.contains("px")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                this.h = Integer.parseInt(str2);
                return;
            }
            if (str.equals("top")) {
                if (str2.contains("px")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                this.i = Integer.parseInt(str2);
                return;
            }
            if (str.equals("width")) {
                if (str2.contains("px")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                this.j = Integer.parseInt(str2);
            } else if (str.equals("height")) {
                if (str2.contains("px")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                this.k = Integer.parseInt(str2);
            } else if (str.equals("font-size")) {
                if (str2.contains("px")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                this.p = Integer.parseInt(str2);
            }
        } catch (Exception e) {
            Log.e("", e.toString());
        }
    }

    public void a(boolean z) {
        this.v.post(new ad(this, z));
    }

    public void b(int i) {
        this.i = (int) (i * this.d);
        this.v.post(new ah(this));
    }

    public void b(int i, int i2) {
        this.j = (int) (i * this.c);
        this.k = (int) (i2 * this.d);
        this.v.post(new ai(this));
    }

    public void b(boolean z) {
        this.v.post(new ae(this, z));
    }

    public boolean b(String str) {
        String str2 = this.t;
        if (str2 == null || str2.equals("null")) {
            return true;
        }
        return Pattern.matches(this.t, str);
    }

    public String c() {
        return this.t;
    }

    public void c(int i) {
        this.j = (int) (i * this.c);
        this.v.post(new aj(this));
    }

    public void c(String str) {
        if (str == null) {
            str = "";
        }
        this.e = str;
        this.v.post(new r(this));
    }

    public void c(boolean z) {
        this.m = z;
        this.v.post(new t(this));
    }

    public void d() {
        this.a = new EditText(this.g);
        this.a.setBackgroundColor(-1);
        this.a.setBackgroundDrawable(null);
        this.a.setVisibility(4);
        this.a.setGravity(BadgeDrawable.TOP_START);
        this.a.setSingleLine(true);
        this.a.setGravity(16);
        this.s = new ak(this);
        this.a.addTextChangedListener(this.s);
        this.a.setPadding(0, 0, 0, 0);
        this.a.setOnEditorActionListener(this.s);
    }

    public void d(int i) {
        this.k = (int) (i * this.d);
        this.v.post(new s(this));
    }

    public void d(String str) {
        this.u = str;
        o();
    }

    public void d(boolean z) {
        this.n = z;
        this.v.post(new u(this));
    }

    public void e(int i) {
        this.o = i;
        this.v.post(new v(this));
    }

    public void e(boolean z) {
        this.q = z;
        this.v.post(new x(this));
    }

    public boolean e() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        AbsoluteLayout.LayoutParams layoutParams = this.l;
        if (layoutParams == null) {
            editText.setWidth(this.j);
            this.a.setHeight(this.k);
            this.l = new AbsoluteLayout.LayoutParams(this.j, this.k, this.h, this.i);
        } else {
            int i = this.j;
            layoutParams.width = i;
            layoutParams.height = this.k;
            layoutParams.x = this.h;
            layoutParams.y = this.i;
            editText.setWidth(i);
            this.a.setHeight(this.k);
        }
        this.a.setLayoutParams(this.l);
        if (!this.n) {
            return true;
        }
        i();
        this.a.setSelection(this.a.getText().length());
        return true;
    }

    public String f() {
        EditText editText = this.a;
        return editText != null ? editText.getText().toString() : "";
    }

    public void f(int i) {
        this.p = (int) (i * this.c);
        this.v.post(new w(this));
    }

    public void f(boolean z) {
        this.f = false;
        this.v.post(new y(this));
    }

    public void g(int i) {
        this.r = i;
        this.v.post(new aa(this));
    }

    public boolean g() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        editText.setText(this.e);
        return true;
    }

    public void h(int i) {
        this.q = true;
        this.f = true;
        this.v.post(new ac(this, i));
    }

    public boolean h() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        if (this.m) {
            editText.setInputType(2);
        } else {
            editText.setInputType(1);
        }
        return true;
    }

    public boolean i() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        editText.setInputType(this.n ? TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA : editText.getInputType() & (-129));
        return true;
    }

    public boolean j() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        editText.setTextColor(this.o);
        return true;
    }

    public boolean k() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        editText.setTextSize(0, this.p);
        return true;
    }

    public boolean l() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        if (this.q) {
            editText.setVisibility(0);
        } else {
            editText.setVisibility(4);
            this.a.clearFocus();
            a(this.g, this.a);
            ExportJavaFunction exportJavaFunctionGetInstance = ExportJavaFunction.GetInstance();
            if (exportJavaFunctionGetInstance != null && exportJavaFunctionGetInstance.m_pEngine != null) {
                exportJavaFunctionGetInstance.m_pEngine.setGameFocus();
            }
        }
        return true;
    }

    public boolean m() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        editText.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        if (this.f) {
            ExportJavaFunction exportJavaFunctionGetInstance = ExportJavaFunction.GetInstance();
            if ((exportJavaFunctionGetInstance == null || exportJavaFunctionGetInstance.m_pEngine == null) ? false : exportJavaFunctionGetInstance.m_pEngine.getInterceptKey()) {
                this.a.setOnKeyListener(new z(this));
            }
            this.a.requestFocus();
            ((InputMethodManager) this.g.getSystemService("input_method")).showSoftInput(this.a, 0);
            this.a.invalidate();
            Editable text = this.a.getText();
            if (text != null) {
                Selection.setSelection(text, text.length());
            }
        } else {
            this.a.clearFocus();
            a(this.g, this.a);
            ExportJavaFunction exportJavaFunctionGetInstance2 = ExportJavaFunction.GetInstance();
            if (exportJavaFunctionGetInstance2 != null && exportJavaFunctionGetInstance2.m_pEngine != null) {
                exportJavaFunctionGetInstance2.m_pEngine.setGameFocus();
            }
        }
        return true;
    }

    public boolean n() {
        EditText editText = this.a;
        if (editText == null) {
            return false;
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.r)});
        return true;
    }

    public void o() {
        if (this.u.length() <= 0) {
            return;
        }
        String[] strArrSplit = this.u.split(com.alipay.sdk.util.i.b);
        if (strArrSplit.length <= 0) {
            String[] strArrSplit2 = this.u.split(":");
            if (strArrSplit2.length < 2) {
                Log.e("LayaBox", "java setRealStyle error");
                return;
            }
            a(strArrSplit2[0], strArrSplit2[1]);
        } else {
            for (String str : strArrSplit) {
                String[] strArrSplit3 = str.split(":");
                if (strArrSplit3.length < 2) {
                    Log.e("LayaBox", "java setRealStyle error2");
                } else {
                    a(strArrSplit3[0], strArrSplit3[1]);
                }
            }
        }
        this.v.post(new ab(this));
    }
}
