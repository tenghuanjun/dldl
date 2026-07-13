package com.hjq.shape.builder;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;
import com.hjq.shape.config.ITextColorStyleable;
import com.hjq.shape.config.ITextViewAttribute;
import com.hjq.shape.other.TextViewAttribute;
import com.hjq.shape.span.StrokeFontSpan;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class TextColorBuilder {
    public static final int GRADIENT_ORIENTATION_HORIZONTAL = 0;
    public static final int GRADIENT_ORIENTATION_VERTICAL = 1;
    private Integer mTextCheckedColor;
    private int mTextColor;
    private Integer mTextDisabledColor;
    private Integer mTextFocusedColor;
    private int[] mTextGradientColors;
    private int mTextGradientOrientation;
    private Integer mTextPressedColor;
    private Integer mTextSelectedColor;
    private int mTextStrokeColor;
    private int mTextStrokeSize;
    private final TextView mTextView;
    private final ITextViewAttribute mTextViewAttribute;

    public TextColorBuilder(TextView textView, TypedArray typedArray, ITextColorStyleable iTextColorStyleable) {
        this.mTextView = textView;
        this.mTextColor = typedArray.getColor(iTextColorStyleable.getTextColorStyleable(), textView.getTextColors().getDefaultColor());
        if (typedArray.hasValue(iTextColorStyleable.getTextPressedColorStyleable())) {
            this.mTextPressedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextPressedColorStyleable(), this.mTextColor));
        }
        if (iTextColorStyleable.getTextCheckedColorStyleable() > 0 && typedArray.hasValue(iTextColorStyleable.getTextCheckedColorStyleable())) {
            this.mTextCheckedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextCheckedColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextDisabledColorStyleable())) {
            this.mTextDisabledColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextDisabledColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextFocusedColorStyleable())) {
            this.mTextFocusedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextFocusedColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextSelectedColorStyleable())) {
            this.mTextSelectedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextSelectedColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextStartColorStyleable()) && typedArray.hasValue(iTextColorStyleable.getTextEndColorStyleable())) {
            if (typedArray.hasValue(iTextColorStyleable.getTextCenterColorStyleable())) {
                this.mTextGradientColors = new int[]{typedArray.getColor(iTextColorStyleable.getTextStartColorStyleable(), this.mTextColor), typedArray.getColor(iTextColorStyleable.getTextCenterColorStyleable(), this.mTextColor), typedArray.getColor(iTextColorStyleable.getTextEndColorStyleable(), this.mTextColor)};
            } else {
                this.mTextGradientColors = new int[]{typedArray.getColor(iTextColorStyleable.getTextStartColorStyleable(), this.mTextColor), typedArray.getColor(iTextColorStyleable.getTextEndColorStyleable(), this.mTextColor)};
            }
        }
        this.mTextGradientOrientation = typedArray.getColor(iTextColorStyleable.getTextGradientOrientationStyleable(), 0);
        if (typedArray.hasValue(iTextColorStyleable.getTextStrokeColorStyleable())) {
            this.mTextStrokeColor = typedArray.getColor(iTextColorStyleable.getTextStrokeColorStyleable(), 0);
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextStrokeSizeStyleable())) {
            this.mTextStrokeSize = typedArray.getDimensionPixelSize(iTextColorStyleable.getTextStrokeSizeStyleable(), 0);
        }
        this.mTextViewAttribute = new TextViewAttribute(textView);
    }

    public TextColorBuilder setTextColor(int i) {
        this.mTextColor = i;
        return this;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public TextColorBuilder setTextPressedColor(Integer num) {
        this.mTextPressedColor = num;
        return this;
    }

    public Integer getTextPressedColor() {
        return this.mTextPressedColor;
    }

    public TextColorBuilder setTextCheckedColor(Integer num) {
        this.mTextCheckedColor = num;
        return this;
    }

    public Integer getTextCheckedColor() {
        return this.mTextCheckedColor;
    }

    public TextColorBuilder setTextDisabledColor(Integer num) {
        this.mTextDisabledColor = num;
        return this;
    }

    public Integer getTextDisabledColor() {
        return this.mTextDisabledColor;
    }

    public TextColorBuilder setTextFocusedColor(Integer num) {
        this.mTextFocusedColor = num;
        return this;
    }

    public Integer getTextFocusedColor() {
        return this.mTextFocusedColor;
    }

    public TextColorBuilder setTextSelectedColor(Integer num) {
        this.mTextSelectedColor = num;
        return this;
    }

    public Integer getTextSelectedColor() {
        return this.mTextSelectedColor;
    }

    public TextColorBuilder setTextGradientColors(int i, int i2) {
        return setTextGradientColors(new int[]{i, i2});
    }

    public TextColorBuilder setTextGradientColors(int i, int i2, int i3) {
        return setTextGradientColors(new int[]{i, i2, i3});
    }

    public TextColorBuilder setTextGradientColors(int[] iArr) {
        this.mTextGradientColors = iArr;
        return this;
    }

    public int[] getTextGradientColors() {
        return this.mTextGradientColors;
    }

    public boolean isTextGradientColorsEnable() {
        int[] iArr = this.mTextGradientColors;
        return iArr != null && iArr.length > 0;
    }

    public TextColorBuilder setTextGradientOrientation(int i) {
        this.mTextGradientOrientation = i;
        return this;
    }

    public int getTextGradientOrientation() {
        return this.mTextGradientOrientation;
    }

    public TextColorBuilder setTextStrokeColor(int i) {
        this.mTextStrokeColor = i;
        return this;
    }

    public TextColorBuilder setTextStrokeSize(int i) {
        this.mTextStrokeSize = i;
        return this;
    }

    public int getTextStrokeColor() {
        return this.mTextStrokeColor;
    }

    public int getTextStrokeSize() {
        return this.mTextStrokeSize;
    }

    public boolean isTextStrokeColorEnable() {
        return this.mTextStrokeColor != 0 && this.mTextStrokeSize > 0;
    }

    public void clearTextGradientColor() {
        if (!isTextGradientColorsEnable()) {
            this.mTextView.setTextColor(this.mTextColor);
        }
        this.mTextGradientColors = null;
        this.mTextView.postInvalidate();
    }

    public void clearTextStrokeColor() {
        this.mTextStrokeColor = 0;
        TextView textView = this.mTextView;
        textView.setText(textView.getText().toString(), TextView.BufferType.NORMAL);
    }

    public SpannableStringBuilder buildStrokeFontSpannable(CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        StrokeFontSpan textStrokeSize = isTextStrokeColorEnable() ? new StrokeFontSpan(this.mTextViewAttribute).setTextStrokeColor(this.mTextStrokeColor).setTextStrokeSize(this.mTextStrokeSize) : null;
        if (textStrokeSize != null) {
            textStrokeSize.setTextSolidColor(this.mTextColor);
            spannableStringBuilder.setSpan(textStrokeSize, 0, spannableStringBuilder.length(), 33);
        }
        return spannableStringBuilder;
    }

    public ColorStateList buildColorState() {
        int i;
        Integer num = this.mTextPressedColor;
        if (num == null && this.mTextCheckedColor == null && this.mTextDisabledColor == null && this.mTextFocusedColor == null && this.mTextSelectedColor == null) {
            return ColorStateList.valueOf(this.mTextColor);
        }
        int[][] iArr = new int[6][];
        int[] iArr2 = new int[6];
        if (num != null) {
            iArr[0] = new int[]{R.attr.state_pressed};
            iArr2[0] = num.intValue();
            i = 1;
        } else {
            i = 0;
        }
        Integer num2 = this.mTextCheckedColor;
        if (num2 != null) {
            iArr[i] = new int[]{R.attr.state_checked};
            iArr2[i] = num2.intValue();
            i++;
        }
        Integer num3 = this.mTextDisabledColor;
        if (num3 != null) {
            iArr[i] = new int[]{-16842910};
            iArr2[i] = num3.intValue();
            i++;
        }
        Integer num4 = this.mTextFocusedColor;
        if (num4 != null) {
            iArr[i] = new int[]{R.attr.state_focused};
            iArr2[i] = num4.intValue();
            i++;
        }
        Integer num5 = this.mTextSelectedColor;
        if (num5 != null) {
            iArr[i] = new int[]{R.attr.state_selected};
            iArr2[i] = num5.intValue();
            i++;
        }
        iArr[i] = new int[0];
        iArr2[i] = this.mTextColor;
        int i2 = i + 1;
        if (i2 != 6) {
            int[][] iArr3 = new int[i2][];
            int[] iArr4 = new int[i2];
            System.arraycopy(iArr, 0, iArr3, 0, i2);
            System.arraycopy(iArr2, 0, iArr4, 0, i2);
            iArr = iArr3;
            iArr2 = iArr4;
        }
        return new ColorStateList(iArr, iArr2);
    }

    public void intoTextColor() {
        if (isTextGradientColorsEnable()) {
            this.mTextColor |= -16777216;
        }
        this.mTextView.setTextColor(buildColorState());
        if (isTextStrokeColorEnable()) {
            TextView textView = this.mTextView;
            textView.setText(buildStrokeFontSpannable(textView.getText().toString()), TextView.BufferType.SPANNABLE);
        }
        this.mTextView.postInvalidate();
    }

    public void onDraw(View view, Canvas canvas, Paint paint) {
        int[] iArrReverseArray;
        if (isTextGradientColorsEnable()) {
            if (this.mTextGradientOrientation == 0 && getLayoutDirectionByContext(view.getContext()) == 1) {
                iArrReverseArray = reverseArray(this.mTextGradientColors);
            } else {
                iArrReverseArray = this.mTextGradientColors;
            }
            paint.setShader(getLinearGradient(view, canvas, this.mTextGradientOrientation, iArrReverseArray));
            return;
        }
        if (paint.getShader() instanceof LinearGradient) {
            paint.setShader(null);
        }
    }

    private static LinearGradient getLinearGradient(View view, Canvas canvas, int i, int[] iArr) {
        if (i == 1) {
            float paddingLeft = view.getPaddingLeft();
            return new LinearGradient(paddingLeft, view.getPaddingTop(), paddingLeft, canvas.getHeight() - view.getPaddingBottom(), iArr, (float[]) null, Shader.TileMode.CLAMP);
        }
        float paddingTop = view.getPaddingTop();
        return new LinearGradient(view.getPaddingLeft(), paddingTop, canvas.getWidth() - view.getPaddingRight(), paddingTop, iArr, (float[]) null, Shader.TileMode.CLAMP);
    }

    private static int getLayoutDirectionByContext(Context context) {
        Resources resources = context != null ? context.getResources() : null;
        Configuration configuration = resources != null ? resources.getConfiguration() : null;
        if (configuration != null) {
            return configuration.getLayoutDirection();
        }
        return 0;
    }

    public static int[] reverseArray(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            iArr2[i] = iArr[(length - 1) - i];
        }
        return iArr2;
    }
}
