package com.aliyun.aliyunface.ui;

import android.content.Intent;
import android.os.Bundle;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrGuideBackActivity extends OcrGuideBaseActivity {
    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public boolean isIDCardBack() {
        return true;
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public void onClickClose() {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "userBack", "loc", "ocrBack");
        sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_USER_BACK);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public void onClickNext() {
        startActivity(new Intent(this, (Class<?>) OcrGuideFaceActivity.class));
        finish();
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public void onClickTakePhoto() {
        Intent intent = new Intent(this, (Class<?>) OcrTakePhotoActivity.class);
        intent.putExtra("takePhotoFront", false);
        startActivityForResult(intent, 0);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public String getTopTips() {
        return getString(R.string.ocr_top_tips_back);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public String getBottomTips() {
        return getString(R.string.ocr_bottom_tips_back);
    }
}
