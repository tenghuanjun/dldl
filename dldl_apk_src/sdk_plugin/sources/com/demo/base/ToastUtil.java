package com.demo.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ToastUtil {
    private static final String TAG = "sqsdk_ToastUtil";

    public static void showToast(Context context, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        try {
            Log.i(TAG, "Toast Log -> " + ((Object) charSequence));
            new Builder(context).setBackgroundColor(Color.parseColor("#BB000000")).addText(charSequence.toString()).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private int mBackgroundColor;
        private Context mContext;
        private int mGravity = 17;
        private List<String> mTextList = new ArrayList();
        private Map<Integer, CharacterStyle> mSpannable = new HashMap();

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setBackgroundColor(int i) {
            this.mBackgroundColor = i;
            return this;
        }

        public Builder addText(String str) {
            this.mTextList.add(str);
            return this;
        }

        public Builder addText(String str, CharacterStyle characterStyle) {
            this.mTextList.add(str);
            this.mSpannable.put(Integer.valueOf(this.mTextList.size() - 1), characterStyle);
            return this;
        }

        public Builder setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        public void show() {
            if (this.mTextList.size() == 0) {
                Log.e(ToastUtil.TAG, "toast msg is empty!");
                return;
            }
            Toast toastMakeText = Toast.makeText(this.mContext, "", 0);
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.mContext).inflate(SqResUtils.getLayoutId(this.mContext, "sy37_toast_layout"), (ViewGroup) null, false);
            TextView textView = (TextView) viewGroup.findViewById(SqResUtils.getId(this.mContext, "tv_msg"));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(this.mBackgroundColor);
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(DisplayUtil.dip2px(this.mContext, 6.0f));
            viewGroup.setBackground(gradientDrawable);
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = this.mTextList.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            SpannableString spannableString = new SpannableString(sb.toString());
            int i = 0;
            for (int i2 = 0; i2 < this.mTextList.size(); i2++) {
                int length = this.mTextList.get(i2).length();
                if (this.mSpannable.get(Integer.valueOf(i2)) != null) {
                    spannableString.setSpan(this.mSpannable.get(Integer.valueOf(i2)), i, i + length, 33);
                }
                i += length;
            }
            textView.setText(spannableString);
            toastMakeText.setView(viewGroup);
            toastMakeText.setGravity(this.mGravity, 0, 0);
            toastMakeText.show();
        }
    }
}
