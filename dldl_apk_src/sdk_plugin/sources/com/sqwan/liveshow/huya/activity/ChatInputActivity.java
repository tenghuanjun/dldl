package com.sqwan.liveshow.huya.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.plugin.standard.BaseActivity;
import com.sqwan.common.util.KeyBoardUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.WindowManagerUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.danmu.LiveshowDanmuManager;
import com.sqwan.liveshow.huya.danmu.view.InputView;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.skin.manager.SkinManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ChatInputActivity extends BaseActivity implements KeyBoardUtils.OnSoftKeyBoardChangeListener {
    private static final String TAG = "ChatInputActivity";
    private Button btnInputCover;
    private InputView inputView;
    private LinearLayout llContainer;
    private KeyBoardUtils keyBoardUtils = new KeyBoardUtils();
    private boolean isFirstResume = true;
    private Task taskKeyboard = Task.create();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().getTheme().applyStyle(SqResUtils.getStyleId(getContext(), "chatInputDialog"), true);
        SkinManager.getInstance().inject(getContext());
        setContentView(SqResUtils.getLayoutId(getContext(), SqR.layout.activity_chat_input));
        initLayout();
        this.inputView = (InputView) findViewById(SqResUtils.getId(getContext(), SqR.id.inputview));
        this.llContainer = (LinearLayout) findViewById(SqResUtils.getId(getContext(), SqR.id.ll_chat_input));
        Button button = (Button) findViewById(SqResUtils.getId(getContext(), SqR.id.btnInputCover));
        this.btnInputCover = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.activity.ChatInputActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatInputActivity.this.hideKeyboard();
            }
        });
        this.inputView.setSentTextListener(new InputView.SendTextListener() { // from class: com.sqwan.liveshow.huya.activity.ChatInputActivity.2
            @Override // com.sqwan.liveshow.huya.danmu.view.InputView.SendTextListener
            public void sendTextContent(String str) {
                LogUtil.i(ChatInputActivity.TAG, "sendTextContent content:" + str);
                LiveshowDanmuManager.getInstance().sendIm(str);
                ChatInputActivity.this.getEtView().setText("");
                ChatInputActivity.this.hideKeyboard();
            }
        });
    }

    private void initLayout() {
        getWindow().setSoftInputMode(21);
        LiveshowManager.getInstance().getLiveshowFloatView();
        getWindow().setLayout(-1, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKeyboard() {
        this.taskKeyboard.oneShot(50L, new Task.TaskFunc() { // from class: com.sqwan.liveshow.huya.activity.ChatInputActivity.3
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                ViewUtils.show(ChatInputActivity.this.btnInputCover);
                return null;
            }
        });
        this.keyBoardUtils.showSoftInput(getEtView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideKeyboard() {
        this.taskKeyboard.oneShot(50L, new Task.TaskFunc() { // from class: com.sqwan.liveshow.huya.activity.ChatInputActivity.4
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                LiveshowDanmuManager.getInstance().setInputTxtTemp(ChatInputActivity.this.getEtView().getText().toString());
                ChatInputActivity.this.finish();
                return null;
            }
        });
        LiveshowDanmuManager.getInstance().setInputTxtTemp(getEtView().getText().toString());
        LiveshowDanmuManager.getInstance().hideSoftkeyboardPadding();
        ViewUtils.hidden(this.inputView);
        this.keyBoardUtils.hideSoftInput(getEtView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EditText getEtView() {
        return this.inputView.getLimitedEditText();
    }

    public void onResume() {
        super.onResume();
        if (this.isFirstResume) {
            WindowManagerUtil.handleHideSystemUI(getContext());
            this.keyBoardUtils.addListener(getContext(), this);
            this.isFirstResume = false;
            Task.postDelay(50L, new Runnable() { // from class: com.sqwan.liveshow.huya.activity.ChatInputActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    ChatInputActivity.this.showKeyboard();
                    String inputTxtTemp = LiveshowDanmuManager.getInstance().getInputTxtTemp();
                    ChatInputActivity.this.inputView.getLimitedEditText().setText(inputTxtTemp);
                    ChatInputActivity.this.inputView.getLimitedEditText().setSelection(inputTxtTemp.length());
                }
            });
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.keyBoardUtils.removeListener();
    }

    @Override // com.sqwan.common.util.KeyBoardUtils.OnSoftKeyBoardChangeListener
    public void keyBoardShow(int i) {
        LogUtil.i(TAG, "keyBoardShow height " + i);
        this.taskKeyboard.stop();
        ViewUtils.show(this.btnInputCover);
        LinearLayout linearLayout = this.llContainer;
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.llContainer.getPaddingTop(), this.llContainer.getPaddingRight(), i);
        LiveshowDanmuManager.getInstance().showSoftkeyboardPadding(i);
    }

    @Override // com.sqwan.common.util.KeyBoardUtils.OnSoftKeyBoardChangeListener
    public void keyBoardHide(int i) {
        LogUtil.i(TAG, "keyBoardHide height " + i);
        ViewUtils.hidden(this.inputView);
        ViewUtils.hidden(this.btnInputCover);
        this.taskKeyboard.stop();
        LiveshowDanmuManager.getInstance().setInputTxtTemp(getEtView().getText().toString());
        LiveshowDanmuManager.getInstance().hideSoftkeyboardPadding();
        finish();
    }

    @Override // com.sqwan.common.util.KeyBoardUtils.OnSoftKeyBoardChangeListener
    public void viewChanged(int i) {
        LogUtil.i(TAG, "viewChanged dheight " + i);
    }
}
