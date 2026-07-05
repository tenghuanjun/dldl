package com.sqwan.liveshow.huya.danmu.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.view.SkinImageView;
import com.sqwan.liveshow.huya.skin.view.SkinLinearLayout;
import com.sqwan.supportview.LimitedEditText;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class InputView extends SkinLinearLayout {
    private Context context;
    private LimitedEditText limitedEditText;
    private SendTextListener sendTextListener;
    private SkinImageView skinImageView;
    private String textContent;

    public interface SendTextListener {
        void sendTextContent(String str);
    }

    public InputView(Context context) {
        this(context, null);
        this.context = context;
    }

    public InputView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InputView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public InputView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context);
    }

    private void initView(final Context context) {
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_item_send_view), this);
        this.limitedEditText = (LimitedEditText) findViewById(SqResUtils.getId(context, "et_input_message"));
        SkinImageView skinImageView = (SkinImageView) findViewById(SqResUtils.getId(context, SqR.id.tv_send_message));
        this.skinImageView = skinImageView;
        skinImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.danmu.view.InputView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (InputView.this.sendTextListener == null || TextUtils.isEmpty(InputView.this.textContent)) {
                    return;
                }
                InputView.this.sendTextListener.sendTextContent(InputView.this.textContent);
            }
        });
        this.limitedEditText.setNumberFilter(30, new LimitedEditText.TextWatcherAdapter() { // from class: com.sqwan.liveshow.huya.danmu.view.InputView.2
            @Override // com.sqwan.supportview.LimitedEditText.TextWatcherAdapter
            public void emptyChange(Boolean bool) {
            }

            @Override // com.sqwan.supportview.LimitedEditText.TextWatcherAdapter, android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                InputView.this.textContent = editable.toString().trim();
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.liveshow.huya.danmu.view.InputView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!TextUtils.isEmpty(InputView.this.textContent)) {
                            InputView.this.skinImageView.setImageResource(SqResUtils.getDrawableId(context, SqR.drawable.sy37_liveshow_iv_send));
                        } else {
                            InputView.this.skinImageView.setImageResource(SqResUtils.getDrawableId(context, SqR.drawable.sy37_liveshow_iv_not_send));
                        }
                    }
                });
            }

            @Override // com.sqwan.supportview.LimitedEditText.TextWatcherAdapter
            public void maxCharactersCallback() {
                ToastUtil.showToast("内容过长~");
            }
        });
    }

    public LimitedEditText getLimitedEditText() {
        return this.limitedEditText;
    }

    public void setSentTextListener(SendTextListener sendTextListener) {
        this.sendTextListener = sendTextListener;
    }

    public SkinImageView getTextView() {
        return this.skinImageView;
    }
}
