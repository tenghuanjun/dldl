package com.aliyun.aliyunface.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrGuideStageView extends FrameLayout {
    public OcrGuideStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.ocr_section_layout_stage, this);
    }

    public void setStage(int i) {
        TextView textView;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int color;
        View viewFindViewById = findViewById(R.id.ocr_stage_line_left);
        View viewFindViewById2 = findViewById(R.id.ocr_stage_line_right);
        TextView textView2 = (TextView) findViewById(R.id.img_stage_idcard_front);
        TextView textView3 = (TextView) findViewById(R.id.img_stage_idcard_back);
        TextView textView4 = (TextView) findViewById(R.id.img_stage_livness);
        TextView textView5 = (TextView) findViewById(R.id.txt_stage_idcard_front);
        TextView textView6 = (TextView) findViewById(R.id.txt_stage_idcard_back);
        TextView textView7 = (TextView) findViewById(R.id.txt_stage_livness);
        String str = "2";
        String str2 = "3";
        String str3 = "";
        int color2 = 0;
        if (i == 0) {
            color2 = getResources().getColor(R.color.ocr_gray_line);
            int color3 = getResources().getColor(R.color.ocr_gray_line);
            int i8 = R.mipmap.comm_stage_icon;
            int color4 = getResources().getColor(R.color.ocr_black_text);
            i6 = R.mipmap.comm_stage_gray_icon;
            int color5 = getResources().getColor(R.color.ocr_gray_text);
            int i9 = R.mipmap.comm_stage_gray_icon;
            color = getResources().getColor(R.color.ocr_gray_text);
            i7 = color5;
            i2 = color3;
            textView = textView7;
            i4 = color4;
            i5 = i9;
            str3 = "1";
            i3 = i8;
        } else if (1 == i) {
            color2 = getResources().getColor(R.color.ocr_orange);
            int color6 = getResources().getColor(R.color.ocr_gray_line);
            int i10 = R.mipmap.comm_stage_finish_icon;
            int color7 = getResources().getColor(R.color.ocr_black_text);
            i6 = R.mipmap.comm_stage_icon;
            int color8 = getResources().getColor(R.color.ocr_black_text);
            int i11 = R.mipmap.comm_stage_gray_icon;
            color = getResources().getColor(R.color.ocr_gray_text);
            i3 = i10;
            i7 = color8;
            i2 = color6;
            textView = textView7;
            i4 = color7;
            i5 = i11;
        } else if (2 == i) {
            color2 = getResources().getColor(R.color.ocr_orange);
            int color9 = getResources().getColor(R.color.ocr_orange);
            int i12 = R.mipmap.comm_stage_finish_icon;
            int color10 = getResources().getColor(R.color.ocr_black_text);
            int i13 = R.mipmap.comm_stage_finish_icon;
            int color11 = getResources().getColor(R.color.ocr_black_text);
            int i14 = R.mipmap.comm_stage_icon;
            color = getResources().getColor(R.color.ocr_black_text);
            i7 = color11;
            i3 = i12;
            i2 = color9;
            textView = textView7;
            i4 = color10;
            str = "";
            i6 = i13;
            i5 = i14;
        } else {
            textView = textView7;
            str = "";
            str2 = str;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            color = 0;
        }
        if (viewFindViewById != null) {
            viewFindViewById.setBackgroundColor(color2);
        }
        if (viewFindViewById2 != null) {
            viewFindViewById2.setBackgroundColor(i2);
        }
        if (textView2 != null) {
            textView2.setText(str3);
            textView2.setBackgroundResource(i3);
        }
        if (textView3 != null) {
            textView3.setText(str);
            textView3.setBackgroundResource(i6);
        }
        if (textView4 != null) {
            textView4.setText(str2);
            textView4.setBackgroundResource(i5);
        }
        if (textView5 != null) {
            textView5.setTextColor(i4);
        }
        if (textView6 != null) {
            textView6.setTextColor(i7);
        }
        if (textView != null) {
            textView.setTextColor(color);
        }
    }
}
