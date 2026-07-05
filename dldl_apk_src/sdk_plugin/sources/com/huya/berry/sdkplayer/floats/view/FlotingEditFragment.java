package com.huya.berry.sdkplayer.floats.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import com.duowan.HUYA.SendMessageRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.kiwi.barrage.PubTextSenderDecorationStore;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.commonevent.Event_Biz;
import com.huya.berry.module.help.LiveHelper;
import com.huya.component.login.LoginProperties;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.sqwan.liveshow.huya.SqR;
import io.reactivex.android.schedulers.AndroidSchedulers;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FlotingEditFragment extends BaseDialogFragment implements View.OnClickListener, View.OnKeyListener {
    private TextWatcher mEditWatcher = new TextWatcher() { // from class: com.huya.berry.sdkplayer.floats.view.FlotingEditFragment.1
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            PlayerHelper.lastInputText = editable.toString();
            FlotingEditFragment.this.setTextNum(editable.length());
        }
    };
    private EditText mInputEt;
    private TextView mTextView;
    private View mView;
    public static String TAG = FlotingEditFragment.class.getSimpleName();
    private static int MAX_LEN = 30;

    public static FlotingEditFragment getInstance(FragmentManager fragmentManager) {
        FlotingEditFragment flotingEditFragment = (FlotingEditFragment) fragmentManager.findFragmentByTag(TAG);
        return flotingEditFragment == null ? new FlotingEditFragment() : flotingEditFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("Widget.FullScreenUp"));
        setCancelable(true);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Window window = getDialog().getWindow();
        if (window != null) {
            if (window.getDecorView() == null) {
                L.debug(TAG, "contentView is null");
            }
            window.setSoftInputMode(37);
            this.mView = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_floating_view_edit), viewGroup, false);
            window.setLayout(-1, -1);
        } else {
            L.debug(TAG, "window is null");
        }
        return this.mView;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.input_empty)).setOnClickListener(this);
        view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_send)).setOnClickListener(this);
        this.mInputEt = (EditText) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.input_et));
        this.mTextView = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_num));
        if (!TextUtils.isEmpty(PlayerHelper.lastInputText)) {
            this.mInputEt.setText(PlayerHelper.lastInputText);
            this.mInputEt.setSelection(PlayerHelper.lastInputText.length());
            setTextNum(PlayerHelper.lastInputText.length());
        } else {
            setTextNum(0);
        }
        this.mInputEt.setOnKeyListener(this);
        this.mInputEt.addTextChangedListener(this.mEditWatcher);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.input_empty)) {
            dismissAllowingStateLoss();
        } else if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.tv_send)) {
            enterInput();
        }
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ArkUtils.send(new CommonEvent.HideBottomUIMenu());
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 66 || keyEvent.getAction() != 1) {
            return false;
        }
        enterInput();
        dismissAllowingStateLoss();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextNum(int i) {
        this.mTextView.setText(i + "/" + MAX_LEN);
    }

    private void enterInput() {
        if (LoginProperties.uid.get().longValue() <= 0) {
            ArkToast.show("发言需要先登录哦");
            ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
            if (iLoginService != null) {
                iLoginService.login(getActivity());
            }
            dismissAllowingStateLoss();
            return;
        }
        final String string = this.mInputEt.getText().toString();
        PlayerHelper.lastInputText = "";
        ArkUtils.send(new Event_Biz.TextAboutToSendV2(string, -328967, -1, System.currentTimeMillis(), null, PubTextSenderDecorationStore.getInstance().getPrefixDecorations(), PubTextSenderDecorationStore.getInstance().getSuffixDecorations(), null));
        dismissAllowingStateLoss();
        if (SdkProperties.isPLayerFloating.get().booleanValue()) {
            Report.event(SdkReportConst.LIVE_SMALLWINDOW_SENDMSG);
        } else {
            Report.event(SdkReportConst.LIVE_FULLSCREEN_SENDMSG);
        }
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.sendMessage(new CommonEvent.SendPubText(string, PlayerHelper.presenterUid, PlayerHelper.sid, PlayerHelper.sid, LiveHelper.getUserId())).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<SendMessageRsp>() { // from class: com.huya.berry.sdkplayer.floats.view.FlotingEditFragment.2
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(SendMessageRsp sendMessageRsp) {
                    if (sendMessageRsp == null) {
                        FlotingEditFragment.this.reportSendMsgFail();
                        L.info(FlotingEditFragment.TAG, "send message success(%s),response == null", string);
                    } else if (sendMessageRsp.getTNotice() == null) {
                        FlotingEditFragment.this.reportSendMsgFail();
                        L.info(FlotingEditFragment.TAG, "send message success(%s),MessageNotice == null", string);
                    } else {
                        if (SdkProperties.isPLayerFloating.get().booleanValue()) {
                            Report.event(SdkReportConst.LIVE_SMALLWINDOW_SENDMSG_SUCCESS);
                        } else {
                            Report.event(SdkReportConst.LIVE_FULLSCREEN_SENDMSG_SUCCESS);
                        }
                        L.info(FlotingEditFragment.TAG, "send message success(%s)", string);
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    FlotingEditFragment.this.reportSendMsgFail();
                    L.error(FlotingEditFragment.TAG, "send message  error");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportSendMsgFail() {
        if (SdkProperties.isPLayerFloating.get().booleanValue()) {
            Report.event(SdkReportConst.LIVE_SMALLWINDOW_SENDMSG_FAIL);
        } else {
            Report.event(SdkReportConst.LIVE_FULLSCREEN_SENDMSG_FAIL);
        }
    }
}
