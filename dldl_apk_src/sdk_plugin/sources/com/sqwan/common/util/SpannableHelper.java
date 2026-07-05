package com.sqwan.common.util;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.data.SpannableEntity;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SpannableHelper {
    public static void handleSpannableHighLineClick(TextView textView, String str, SpannableEntity... spannableEntityArr) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            if (spannableEntityArr != null && spannableEntityArr.length >= 1) {
                for (final SpannableEntity spannableEntity : spannableEntityArr) {
                    String tag = spannableEntity.getTag();
                    int iIndexOf = str.indexOf(tag);
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.sqwan.common.util.SpannableHelper.1
                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            SpannableEntity.OnClickListener onClickListener = spannableEntity.getOnClickListener();
                            if (onClickListener != null) {
                                onClickListener.onClick();
                            }
                        }

                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor(spannableEntity.getColorValue()));
                            textPaint.setUnderlineText(false);
                        }
                    }, iIndexOf, tag.length() + iIndexOf, 33);
                }
                textView.setText(spannableStringBuilder);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
