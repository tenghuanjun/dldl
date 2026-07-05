package com.mobile.auth.k;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cmic.sso.sdk.AuthThemeConfig;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class w {

    static class a extends ClickableSpan {
        final /* synthetic */ Context a;
        final /* synthetic */ com.cmic.sso.sdk.widget.a b;

        a(Context context, com.cmic.sso.sdk.widget.a aVar) {
            this.a = context;
            this.b = aVar;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            com.cmic.sso.sdk.widget.a aVar = this.b;
            if (aVar == null || aVar.isShowing()) {
                return;
            }
            this.b.show();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            try {
                textPaint.setColor(com.mobile.auth.e.a.a(this.a).a().getClauseColor());
            } catch (Exception unused) {
                textPaint.setColor(-16007674);
            }
        }
    }

    static class b extends ClickableSpan {
        final /* synthetic */ Context a;
        final /* synthetic */ com.cmic.sso.sdk.widget.a b;

        b(Context context, com.cmic.sso.sdk.widget.a aVar) {
            this.a = context;
            this.b = aVar;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            com.cmic.sso.sdk.widget.a aVar = this.b;
            if (aVar == null || aVar.isShowing()) {
                return;
            }
            this.b.show();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            try {
                textPaint.setColor(com.mobile.auth.e.a.a(this.a).a().getClauseColor());
            } catch (Exception unused) {
                textPaint.setColor(-16007674);
            }
        }
    }

    static class c extends ClickableSpan {
        final /* synthetic */ Context a;
        final /* synthetic */ com.cmic.sso.sdk.widget.a b;

        c(Context context, com.cmic.sso.sdk.widget.a aVar) {
            this.a = context;
            this.b = aVar;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            com.cmic.sso.sdk.widget.a aVar = this.b;
            if (aVar == null || aVar.isShowing()) {
                return;
            }
            this.b.show();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            try {
                textPaint.setColor(com.mobile.auth.e.a.a(this.a).a().getClauseColor());
            } catch (Exception unused) {
                textPaint.setColor(-16007674);
            }
        }
    }

    public static int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static int a(Context context, float f) {
        if (f < 0.0f) {
            return (int) f;
        }
        try {
            return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        } catch (Exception unused) {
            return (int) f;
        }
    }

    public static SpannableString a(Context context, String str, String str2, com.cmic.sso.sdk.widget.a aVar, com.cmic.sso.sdk.widget.a aVar2, com.cmic.sso.sdk.widget.a aVar3) {
        int iIndexOf;
        int length;
        SpannableString spannableString = new SpannableString(str);
        try {
            a aVar4 = new a(context, aVar);
            b bVar = aVar2 != null ? new b(context, aVar2) : null;
            c cVar = aVar3 != null ? new c(context, aVar3) : null;
            AuthThemeConfig authThemeConfigA = com.mobile.auth.e.a.a(context).a();
            int iIndexOf2 = str.indexOf(str2);
            spannableString.setSpan(aVar4, iIndexOf2, str2.length() + iIndexOf2, 34);
            if (aVar2 == null || aVar3 == null) {
                if (aVar2 != null) {
                    String clauseName = authThemeConfigA.getClauseName();
                    int iIndexOf3 = str.indexOf(clauseName);
                    spannableString.setSpan(bVar, iIndexOf3, clauseName.length() + iIndexOf3, 34);
                } else if (aVar3 != null) {
                    String clauseName2 = authThemeConfigA.getClauseName2();
                    iIndexOf = str.indexOf(clauseName2);
                    length = clauseName2.length() + iIndexOf;
                }
                return spannableString;
            }
            String clauseName3 = authThemeConfigA.getClauseName();
            int iIndexOf4 = str.indexOf(clauseName3);
            spannableString.setSpan(bVar, iIndexOf4, clauseName3.length() + iIndexOf4, 34);
            int length2 = iIndexOf4 + clauseName3.length();
            String clauseName22 = authThemeConfigA.getClauseName2();
            iIndexOf = str.indexOf(clauseName22, length2);
            length = clauseName22.length() + iIndexOf;
            spannableString.setSpan(cVar, iIndexOf, length, 34);
            return spannableString;
        } catch (Exception e) {
            e.printStackTrace();
            return spannableString;
        }
    }

    public static RelativeLayout a(Context context, View view, int i, int i2, String str, View.OnClickListener onClickListener) {
        AuthThemeConfig authThemeConfigA = com.mobile.auth.e.a.a(context).a();
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, view != null ? -2 : a(context, 49.0f));
        layoutParams.addRule(10, -1);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setId(i);
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        textView.setLayoutParams(layoutParams2);
        textView.setTextColor(authThemeConfigA.getNavTextColor());
        textView.setTextSize(2, authThemeConfigA.getNavTextSize());
        textView.setText(str);
        if (view != null) {
            relativeLayout.addView(view);
            relativeLayout.addView(textView);
            return relativeLayout;
        }
        relativeLayout.addView(textView);
        ImageButton imageButton = new ImageButton(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a(context, authThemeConfigA.getNavReturnImgWidth()), a(context, authThemeConfigA.getNavReturnImgHeight()));
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.setMargins(a(context, 12.0f), 0, 0, 0);
        imageButton.setLayoutParams(layoutParams3);
        imageButton.setId(i2);
        imageButton.setOnClickListener(onClickListener);
        imageButton.setBackgroundColor(0);
        relativeLayout.addView(imageButton);
        try {
            relativeLayout.setBackgroundColor(com.mobile.auth.e.a.a(context).a().getNavColor());
        } catch (Exception unused) {
            relativeLayout.setBackgroundColor(-16742704);
        }
        imageButton.setImageResource(n.b(context, "umcsdk_return_bg"));
        return relativeLayout;
    }

    public static int b(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }
}
