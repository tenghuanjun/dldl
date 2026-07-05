package com.aliyun.aliyunface.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.aliyun.aliyunface.ui.overlay.OcrLoadingOverlay;
import com.aliyun.aliyunface.ui.widget.OcrGuideStageView;
import com.aliyun.aliyunocr.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrGuideFaceActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ocr_guide_face);
        OcrLoadingOverlay ocrLoadingOverlay = (OcrLoadingOverlay) findViewById(R.id.ocr_loading_overlay);
        if (ocrLoadingOverlay != null) {
            ocrLoadingOverlay.setLoadingText(getString(R.string.ocr_face_guid_loading));
            ocrLoadingOverlay.setVisibility(0);
            ocrLoadingOverlay.postDelayed(new Runnable() { // from class: com.aliyun.aliyunface.ui.OcrGuideFaceActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        OcrGuideFaceActivity.this.startActivity(new Intent(OcrGuideFaceActivity.this, OcrGuideFaceActivity.class.getClassLoader().loadClass("com.aliyun.aliyunface.ui.ToygerPortActivity")));
                        OcrGuideFaceActivity.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 2000L);
        }
        OcrGuideStageView ocrGuideStageView = (OcrGuideStageView) findViewById(R.id.ocr_guide_stage_view);
        if (ocrGuideStageView != null) {
            ocrGuideStageView.setStage(2);
        }
        View viewFindViewById = findViewById(R.id.ocr_comm_back_button);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrGuideFaceActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                }
            });
        }
    }
}
