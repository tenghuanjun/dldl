package com.aliyun.aliyunface.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.aliyun.aliyunface.ToygerConst;
import com.aliyun.aliyunface.ToygerPresenter;
import com.aliyun.aliyunface.log.RecordLevel;
import com.aliyun.aliyunface.log.RecordService;
import com.aliyun.aliyunface.network.model.OCRInfo;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrGuideFrontActivity extends OcrGuideBaseActivity {
    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public boolean isIDCardFront() {
        return true;
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public void onClickTakePhoto() {
        Intent intent = new Intent(this, (Class<?>) OcrTakePhotoActivity.class);
        intent.putExtra("takePhotoFront", true);
        startActivityForResult(intent, 0);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public void onClickClose() {
        RecordService.getInstance().recordEvent(RecordLevel.LOG_ERROR, "userBack", "loc", "ocrFront");
        sendErrorCode(ToygerConst.ZcodeConstants.ZCODE_USER_BACK);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public void onClickNext() {
        String certName = getCertName();
        if (!MiscUtil.isIdCardNameValid(certName)) {
            Toast.makeText(this, "身份证姓名格式错误，请检查", 0).show();
            return;
        }
        String certNo = getCertNo();
        if (!MiscUtil.isIdCardNoValid(certNo)) {
            Toast.makeText(this, "身份证号码格式错误，请检查", 0).show();
            return;
        }
        OCRInfo ocrInfo = ToygerPresenter.getInstance().getOcrInfo();
        ocrInfo.certName = certName;
        ocrInfo.certNo = certNo;
        ToygerPresenter.getInstance().setOcrInfo(ocrInfo);
        startActivity(new Intent(this, (Class<?>) OcrGuideBackActivity.class));
        finish();
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public String getTopTips() {
        return getString(R.string.ocr_top_tips_front);
    }

    @Override // com.aliyun.aliyunface.ui.OcrGuideBaseActivity
    public String getBottomTips() {
        return getString(R.string.ocr_bottom_tips_front);
    }
}
